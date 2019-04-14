package org.apache.flink.table.xlink

import com.alibaba.fastjson.JSON
import net.sf.cglib.core.ReflectUtils
import org.apache.commons.codec.binary.Base64

/**
  *
  * @author martin.dong
  *
  **/
class CglibEnhancerClassLoader(val parent:ClassLoader, val dynamicUDFInfo:String) extends ClassLoader(parent) {
  require(dynamicUDFInfo != "")
  private val udfInfoMap: Map[String, String] = {
    val jsonObject = JSON.parseObject(dynamicUDFInfo)
    import scala.collection.convert.wrapAsScala._
    jsonObject.keySet().map(key ⇒ {
      val value = jsonObject.
        getString(key)
      (key, value)
    }).toMap
  }

  override def findClass(name: String): Class[_] = {
    if(name.contains("EnhancerByCGLIB")) {
      udfInfoMap.keySet.filter(_ != name).foreach(c ⇒ this.loadClass(c))
    }
    udfInfoMap.get(name).map(value ⇒ {
      val byteValue = Base64.decodeBase64(value)
      ReflectUtils.defineClass(name,byteValue, parent)
    }).orNull
  }
}
