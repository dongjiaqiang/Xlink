package org.apache.flink.table.xlink

import com.alibaba.fastjson.{JSON, JSONObject}
import net.sf.cglib.core.ReflectUtils
import org.apache.commons.codec.binary.Base64
import scala.collection.convert.wrapAsScala._
/**
  *
  * @author martin.dong
  *
  **/
class CglibEnhancerClassLoader(val parent:ClassLoader, val dynamicUDFInfo:String) extends ClassLoader(parent){
  require(dynamicUDFInfo != "")

  private def resolveUDFInfo(dynamicUDFInfo:String):Map[String,JSONObject]={
      val resolveObject = JSON.parseObject(dynamicUDFInfo)
      resolveObject.keySet().map(key⇒{
        (key,resolveObject.getJSONObject(key))
      }).toMap
  }

  private val dependencyUdfInfoMap:Map[String,String]= {
    resolveUDFInfo(dynamicUDFInfo).toList.flatMap { case (_, data) ⇒
      val dependencies = data.getJSONObject("dependency")
      dependencies.keys.map(dependency ⇒ {
        val dependencyData = dependencies.getString(dependency)
        (dependency, dependencyData)
      })
    }.toMap
  }

  private val udfInfoMap: Map[String,(String,Set[String])] = {
    resolveUDFInfo(dynamicUDFInfo).toList.map { case (name, jsonObject) ⇒
      val bytes = jsonObject.getString("data")
      val dependencies = jsonObject.getJSONObject("dependency")
      (name, (bytes, dependencies.keySet().toSet))
    }.toMap
  }

  override def findClass(className: String): Class[_] = {
    val name = className.replace("/", ".")
    if (name.contains("EnhancerByCGLIB")) {
      udfInfoMap(name)._2.foreach(c ⇒ this.loadClass(c))
      val byteValue = Base64.decodeBase64(udfInfoMap(name)._1)
      try {
        ReflectUtils.defineClass(name, byteValue, parent)
      } catch {
        case _: Exception ⇒ parent.loadClass(name)
      }
    } else if(dependencyUdfInfoMap.contains(name)){
      val byteValue = Base64.decodeBase64(dependencyUdfInfoMap(name))
      try {
        ReflectUtils.defineClass(name, byteValue, parent)
      } catch {
        case _: Exception ⇒ parent.loadClass(name)
      }
    }else{
      null
    }
  }
}
