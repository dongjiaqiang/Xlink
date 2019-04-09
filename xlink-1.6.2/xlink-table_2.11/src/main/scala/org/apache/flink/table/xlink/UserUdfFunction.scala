package org.apache.flink.table.xlink

import java.lang.reflect.Method

import com.ximalaya.flink.dsl.stream.`type`.FieldType
import com.ximalaya.flink.dsl.stream.api.udf.{AbstractUserUdf, UserUdfContext}
import net.sf.cglib.proxy.{MethodInterceptor, MethodProxy}
import org.apache.commons.lang.ArrayUtils
import org.apache.flink.table.functions.ScalarFunction

import scala.collection.mutable.{Map ⇒ IMap}
/**
  *
  * @author martin.dong
  *
  **/
class UserUdfFunction extends ScalarFunction{
  override def isDeterministic: Boolean = false
}
class UdfMethodInterceptor(val name:String,
                                   val fullyQualifiedClassName:String) extends MethodInterceptor with  Serializable {

  private var userUdf: AbstractUserUdf = _
  private var evalMethods:IMap[MethodSignature,Method]=IMap()
  private var closeMethod:Method = _
  private var openMethod:Method = _

  override def intercept(o: scala.Any,
                         method: Method,
                         objects: Array[AnyRef], methodProxy: MethodProxy): AnyRef = {
    val methodName=method.getName
    methodName match {
      case "open"⇒
        this.userUdf = Class.forName(fullyQualifiedClassName).newInstance().asInstanceOf[AbstractUserUdf]
        this.userUdf.getClass.getDeclaredMethods.filter(_.getName=="eval").
          foreach(method ⇒ evalMethods.put(MethodSignature.createMethodSignature(method), method))
        this.closeMethod = classOf[AbstractUserUdf].getDeclaredMethod("close")
        this.openMethod = classOf[AbstractUserUdf].getDeclaredMethod("open",classOf[UserUdfContext])
        openMethod.invoke(userUdf,null)
      case "eval"⇒
        val methodSignature = MethodSignature.createMethodSignature(method)
        evalMethods(methodSignature).invoke(userUdf,objects:_*)
      case "close"⇒
        closeMethod.invoke(userUdf)
      case _⇒
        methodProxy.invokeSuper(o,objects)
    }
  }
}

class MethodSignature (val fieldTypes:Array[FieldType]){
  def this(clazzArray:Array[Class[_]]){
    this(clazzArray.map(clazz⇒FieldType.get(clazz)))
  }

  override def hashCode(): Int = fieldTypes.map(_.hashCode()).sum
  override def equals(obj: scala.Any): Boolean = {
    if(this.eq(obj.asInstanceOf[AnyRef])){
      return true
    }
    obj match {
      case _: MethodSignature⇒ ArrayUtils.isEquals(this.fieldTypes,obj.asInstanceOf[MethodSignature].fieldTypes)
      case _ ⇒ false
    }
  }

  override def toString: String =fieldTypes.map(_.toString).mkString(",")
}
object MethodSignature{
  def createMethodSignature(method:Method):MethodSignature={
    new MethodSignature(method.getParameterTypes)
  }
}
