package org.apache.flink.table.xlink

import com.alibaba.fastjson.{JSON, JSONObject}
import net.sf.cglib.asm.Type
import net.sf.cglib.core.Signature
import net.sf.cglib.proxy.{Enhancer, InterfaceMaker}

/**
  *
  * @author martin.dong
  *
  **/
class CglibEnhancerClassLoader(val parent:ClassLoader, val dynamicUDFInfo:String) extends ClassLoader(parent) {
  require(dynamicUDFInfo != "")
  private val udfInfoMap: Map[String, Class[_]] = {
    val jsonObject = JSON.parseObject(dynamicUDFInfo)
    import scala.collection.convert.wrapAsScala._
    jsonObject.keySet().map(key ⇒ {
      val value = jsonObject.
        getJSONObject(key)
      val udfClazz = value.getString("clazz")
      val udfName = value.getString("name")
      val methods = value.
        getJSONArray("methods").
        toJavaList(classOf[JSONObject]).map(method ⇒ {
        val methodName = method.getString("name")
        val returnType = Type.getType(method.getString("return"))
        val paramTypes = method.getJSONArray("params").toJavaList(classOf[String]).map(param ⇒ Type.getType(param))
          .toArray
        (methodName, returnType, paramTypes)
      }).toArray
      (key, createClass(udfName, udfClazz, methods))
    }).toMap
  }

  private def createClass(name: String, clazz: String, methods: Array[(String, Type, Array[Type])]): Class[_] = {
    val enhancer = new Enhancer
    enhancer.setSuperclass(classOf[UserUdfFunction])
    enhancer.setCallback(new UdfMethodInterceptor(name, clazz))
    val ims = methods.map(method ⇒ {
      val im = new InterfaceMaker
      im.add(new Signature(method._1, method._2, method._3), null)
      im
    })
    enhancer.setInterfaces(ims.map(_.create()))
    enhancer.create().getClass
  }

  override def findClass(name: String): Class[_] = {
    udfInfoMap.getOrElse(name, null)
  }
}
