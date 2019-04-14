# Xlink

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
	"org.apache.flink.table.xlink.UserUdfFunction$$EnhancerByCGLIB$$fb7e7f2b": "BTKzAKQrKhMBMhMBMRMBSbgBR7MAplkGMrMAtisqEwE0EwEzEwFKuAFHswC4VxAGvQBtWQMTAUtTWQQTAUxTWQUTAU1TWQYTAS5TWQcTAU5TWQgTAU9TEwFRuAEsWUy2ATq4AUBZAzKzAGkrKhMBTBMBSxMBUrgBR7MAa1kEMrMAdysqEwEuEwFNEwFTuAFHswB5WQUyswCCKyoTAU8TAU4TAVS4AUezAIRXBb0AbVkDEwFVU1kEEwFWUxMBWLgBLFlMtgE6uAFAWQMyswDaKyoTAVYTAVUTAVm4AUezAOZXBb0AbVkDEwFaU1kEEwFbUxMBXbgBLFlMtgE6uAFAWQMyswA1KyoTAVsTAVoTAV64AUezADlXB70AbVkDEwFfU1kEEwFgU1kFEwFhU1kGEwFiUxMBZLgBLFlMtgE6uAFAWQMyswBNKyoTAWATAV8TAWW4AUezAFFZBDKzAFwrKhMBYhMBYRMBZrgBR7MAXlcFvQBtWQMTAVVTWQQTAWdTEwFpuAEsWUy2ATq4AUBZAzKzAPIrKhMBZxMBVRMBargBR7MA9FcFvQBtWQMTAVVTWQQTAWtTEwFtuAEsWUy2ATq4AUBZAzKzAMErKhMBaxMBVRMBbrgBR7MAyFcFvQBtWQMTAVVTWQQTAW9TEwFxuAEsWUy2ATq4AUBZAzKzANArKhMBbxMBVRMBcrgBR7MA0lcFvQBtWQMTAVVTWQQTAUxTEwF0uAEsWUy2ATq4AUBZAzKzAP8rKhMBTBMBVRMBdbgBR7MBAVexsQAAAAAAEAApACoAAQGfAAAAEQABAAEAAAAFKrcALawAAAAAABEAKwAqAAEBnwAAAEUABQABAAAAOSq0AC9ZxwAMVyq4ADMqtAAvWcYAIiqyADWyADeyADm5AD8FAFnHAAhXA6cACcAAQbYARKwqtwAtrAAAAAAAEABHAEgAAQGfAAAAEgACAAIAAAAGKiu3AEuwAAAAAAARAEkASAABAZ8AAAA_AAcAAgAAADMqtAAvWccADFcquAAzKrQAL1nGABsqsgBNBL0AT1kDK1OyAFG5AD8FAMAAU7AqK7cAS7AAAAAAABAAVgBXAAEBnwAAABIAAgACAAAABiortwBasAAAAAAAEQBYAFcAAQGfAAAAPwAHAAIAAAAzKrQAL1nHAAxXKrgAMyq0AC9ZxgAbKrIAXAS9AE9ZAytTsgBeuQA_BQDAAGCwKiu3AFqwAAAAAAAQAGMAZAABAZ8AAAARAAEAAQAAAAUqtwBnsAAAAAAAEQBlAGQAAQGfAAAAOQAFAAEAAAAtKrQAL1nHAAxXKrgAMyq0AC9ZxgAWKrIAabIAN7IAa7kAPwUAwABtsCq3AGewAAAAAAAQAHAAGwACAZ8AAAARAAEAAQAAAAUqtwB1sQAAAAABoAAAAAQAAQByABEAcwAbAAIBnwAAADYABQABAAAAKiq0AC9ZxwAMVyq4ADMqtAAvWcYAEyqyAHeyADeyAHm5AD8FALEqtwB1sQAAAAABoAAAAAQAAQByABAAfAB9AAIBnwAAABIAAgACAAAABiortwCAsQAAAAABoAAAAAQAAQByABEAfgB9AAIBnwAAADwAAGgAAAABAABAIkAAFnAzKrQAL1nGACIqsgCksgA3sgCmuQA_BQBZxwAIVwOnAAnAAKi2AKusKrcAoqwAAAAAABAArgCvAAIBnwAAABEAAQABAAAABSq3ALSwAAAAAAGgAAAABAABALEAFACyAK8AAgGfAAAANgAFAAEAAAAqKrQAL1nHAAxXKrgAMyq0AC9ZxgATKrIAtrIAN7IAuLkAPwUAsCq3ALSwAAAAAAGgAAAABAABALEAEAC7ALwAAQGfAAAAEwADAAMAAAAHKhsctwC_rAAAAAAAEQC9ALwAAQGfAAAAYAAJAAMAAABUKrQAL1nHAAxXKrgAMyq0AC9ZxgA7KrIAwQW9AE9ZAxu7AMNaX7cAxlNZBBy7AMNaX7cAxlOyAMi5AD8FAFnHAAhXA6cACcAAqLYAq6wqGxy3AL-sAAAAAAAQAMsAzAABAZ8AAAATAAMAAwAAAAcqKyy3AM6wAAAAAAARAL0AzAABAZ8AAABEAAcAAwAAADgqtAAvWccADFcquAAzKrQAL1nGAB8qsgDQBb0AT1kDK1N"
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
            <version>1.0</version>
</dependency>
        
 <dependency>
            <groupId>com.ximalaya</groupId>
            <artifactId>xlink-table_2.11</artifactId>
            <version>1.0</version>
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
val (udf,clazzStr)=UserUdfFactory.createUserUdf("test",
      "com.ximalaya.recsys.stream.flink.task.app.TestUdf",
      List(EvalMethod(FieldType.LONG,Array(FieldType.INT))))

//步骤三 将自定义UDF的class作为参数传递给Flink作业
val config=new Configuration()
val json=new JSONObject
json.put(udf.getClass.getName,str)
config.setString("flink.dsl.stream.dynamic.udfs.info",json.toString)
environment.getConfig.setGlobalJobParameters(config)

//步骤四 注册自定义UDF
tEnv.registerFunction("test",udf)
```