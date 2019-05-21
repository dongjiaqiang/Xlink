# Xlink

## xlink-yarn_2.11

### 版本

**1.6.2**

## 特性

* **支持动态将位于HDFS上的依赖jar传递到Flink Yarn作业的依赖目录上**

### 源码改动说明

* [在AbstractYarnClusterDescriptor读取依赖路径并将依赖jar传递到远程Yarn作业目录中](https://github.com/dongjiaqiang/Xlink/blob/master/xlink-1.6.2/xlink-yarn_2.11/src/main/java/org/apache/flink/yarn/AbstractYarnClusterDescriptor.java)
* [在Utils中实现依赖jar的远程传递](https://github.com/dongjiaqiang/Xlink/blob/master/xlink-1.6.2/xlink-yarn_2.11/src/main/java/org/apache/flink/yarn/Utils.java)

### 参数说明

**为实现动态依赖的加载 需要在Flink Configuration中配置一个参数 指明传递的路径信息**

* **键名为flink.hdfs.extend.lib.path**

* **值为一个逗号分隔的路径列表**

```
/home/path1,/home/path2
```

## xlink-table_2.11

### 版本

**1.6.2**

### 特性

* **支持在Janino编译Flink SQL生成的Java源代码时传递一个能够加载由cglib动态生成的类的字节码类加载器**


### 源码改动说明

* [在Compiler接口中将Configuration传递下来](https://github.com/dongjiaqiang/Xlink/blob/master/xlink-1.6.2/xlink-table_2.11/src/main/scala/org/apache/flink/table/codegen/Compiler.scala)
* [xlink包中存放用于支持加载器实现的代码](https://github.com/dongjiaqiang/Xlink/tree/master/xlink-1.6.2/xlink-table_2.11/src/main/scala/org/apache/flink/table/xlink)


### 参数说明

**为支持创建能够加载由cglib动态生成的类的字节码类加载器 需要通过配置将动态类的class对象添加进来**

* **键名字为flink.dsl.stream.dynamic.udfs.info**

* **值为一个JSON字符串**

```json
{
	"org.apache.flink.table.xlink.UserUdfFunction$$EnhancerByCGLIB$$63728015": {
		"data": "yv66vgAAAC4BpgEAR29yZy9hcGFjaGUvZmxaW9uQ29udGV4dDspVgEABG9wZW4MAi9jZ2xpYi9wcm94eS9DYWxsYmFjazspTGphdmEvbGFuZy9PYmplY3Q7AQAiamF2YS9sYW5nL0lsbGVnYWxBcmd1bWVudEV4Y2VwdGlBoARgAmAAAAGgBUACQAAAAaAFUAJgAAABoAYQAkAAAAGgBiACYAAAAaAG4AJAAAABoAbwAmAAAAGgB6ACQAAAAaAHsAJgAAABoAhysqEwFQEwFZEwF5uAFLswEFV7GxAAAAAAAQACk",
		"dependency": {
			"net.sf.cglib.empty.Object$$InterfaceMakerByCGLIB$$16d04d3d": "yv66vgAAAC4ACQEAOm5ldC9zZi9jZ2xpYi9lbXB0eS9PYmplY3QkJEludGVyZmFjZU1ha2VyQnlDR0xJQiQkMTZkMDRkM2QHAAEBABBqYXZhL2xhbmcvT2JqZWN0BwADAQALPGdlbmVyYXRlZD4BAARldmFsAQAEKEkpSgEAClNvdXJjZUZpbGUCAQACAAQAAAAAAAEEAQAGAAcAAAABAAgAAAACAAU",
			"net.sf.cglib.empty.Object$$InterfaceMakerByCGLIB$$36902638": "yv66vgAAAC4ACQEAOm5ldC9zZi9jZ2xpYi9lbXB0eS9PYmplY3QkJEludGVyZmFjZU1ha2VyQnlDR0xJQiQkMzY5MDI2MzgHAAEBABBqYXZhL2xhbmcvT2JqZWN0BwADAQALPGdlbmVyYXRlZD4BAARldmFsAQAUKClMamF2YS9sYW5nL1N0cmluZzsBAApTb3VyY2VGaWxlAgEAAgAEAAAAAAABBAEABgAHAAAAAQAIAAAAAgAF",
			"net.sf.cglib.empty.Object$$InterfaceMakerByCGLIB$$7b9a4292": "yv66vgAAAC4ACQEAOm5ldC9zZi9jZ2xpYi9lbXB0eS9PYmplY3QkJEludGVyZmFjZU1ha2VyQnlDR0xJQiQkN2I5YTQyOTIHAAEBABBqYXZhL2xhbmcvT2JqZWN0BwADAQALPGdlbmVyYXRlZD4BAARldmFsAQA4KExqYXZhL2xhbmcvU3RyaW5nO0xqYXZhL2xhbmcvU3RyaW5nOylMamF2YS9sYW5nL1N0cmluZzsBAApTb3VyY2VGaWxlAgEAAgAEAAAAAAABBAEABgAHAAAAAQAIAAAAAgAF",
			"net.sf.cglib.empty.Object$$InterfaceMakerByCGLIB$$1d2adfbe": "yv66vgAAAC4ACQEAOm5ldC9zZi9jZ2xpYi9lbXB0eS9PYmplY3QkJEludGVyZmFjZU1ha2VyQnlDR0xJQiQkMWQyYWRmYmUHAAEBABBqYXZhL2xhbmcvT2JqZWN0BwADAQALPGdlbmVyYXRlZD4BAARldmFsAQAHKFtJW0YpRgEAClNvdXJjZUZpbGUCAQACAAQAAAAAAAEEAQAGAAcAAAABAAgAAAACAAU",
			"net.sf.cglib.empty.Object$$InterfaceMakerByCGLIB$$49ec71f8": "yv66vgAAAC4ACQEAOm5ldC9zZi9jZ2xpYi9lbXB0eS9PYmplY3QkJEludGVyZmFjZU1ha2VyQnlDR0xJQiQkNDllYzcxZjgHAAEBABBqYXZhL2xhbmcvT2JqZWN0BwADAQALPGdlbmVyYXRlZD4BAARldmFsAQAFKEpEKUQBAApTb3VyY2VGaWxlAgEAAgAEAAAAAAABBAEABgAHAAAAAQAIAAAAAgAF"
		}
	}
}
```

## xlink-streaming-java_2.11

### 版本

**1.6.2**

### 特性

* **支持初始化用户函数时使用Flink作业配置参数**

### 源码改动

* [支持在初始化具有用户函数的算子时将Flink作业配置参入传入用于初始化用户函数](https://github.com/dongjiaqiang/Xlink/blob/master/xlink-1.6.2/xlink-streaming-java_2.1.1/src/main/java/org/apache/flink/streaming/api/operators/AbstractUdfStreamOperator.java)

## 例子

### 实现动态加载外部UDF

* **依赖**

```
<dependency>
            <groupId>com.ximalaya</groupId>
            <artifactId>xlink-streaming-java_2.11</artifactId>
            <version>1.6.2</version>
</dependency>
        
 <dependency>
            <groupId>com.ximalaya</groupId>
            <artifactId>xlink-table_2.11</artifactId>
            <version>1.6.2</version>
</dependency>        
```

* **代码**

```scala
//步骤一 实现自定义UDF 继承 AbstractUserUdf类 添加eval方法
class TestUdf extends AbstractUserUdf{
  def eval(i:Int):Long=System.currentTimeMillis()+i
}

//步骤二 生成自定义UDF的ScalarFunction实例
//参数为 UDF名字 自定义的UDF实现类 自定义UDF添加的方法 返回自定义UDF实例和自定义UDF的class对象的字符串表达式
val (udf,clazzStr,clazzMapInfo)=UserUdfFactory.createUserUdf("test",
      "com.ximalaya.recsys.stream.flink.task.app.TestUdf",
      List(EvalMethod(FieldType.LONG,Array(FieldType.INT))))

//步骤三 将自定义UDF的class作为参数传递给Flink作业
val config=new Configuration()
val jSONObject = new JSONObject()
clazzInfoMap.foreach(kv⇒{
   jSONObject.put(kv._1,kv._2)
})
val jSONObject1 = new JSONObject()
jSONObject1.put("data",clazzStr)
jSONObject1.put("dependency",jSONObject)
val jSONObject2 = new JSONObject()
jSONObject2.put(scalarFunction.getClass.getName,jSONObject1)

config.setString("flink.dsl.stream.dynamic.udfs.info",jSONObject2.toString)
environment.getConfig.setGlobalJobParameters(config)

//步骤四 注册自定义UDF
tEnv.registerFunction("test",udf)
```