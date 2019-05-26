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

* **解决自定义UDF匹配多个eval方法歧义问题**


### 源码改动说明

* [在UserDefinedFunctionUtils中计算最佳匹配的eval方法](https://github.com/dongjiaqiang/Xlink/blob/master/xlink-1.6.2/xlink-table_2.11/src/main/scala/org/apache/flink/table/functions/utils/UserDefinedFunctionUtils.scala)


### 改动说明

自定义如下UDF 重载eval方法 当SQL语句匹配到Array[Int]参数的eval方法时 必然也会匹配Array[Any]参数 在原生Flink实现中会报错 可以实现为匹配一个最佳的方法
```scala
class  ArraySize extends ScalarFunction{
   def eval(array: Array[Int]):Int = {
        array.length
      }
   def eval(array: Array[Any]):Int = {
        array.length
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