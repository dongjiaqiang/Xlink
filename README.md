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
package com.ximalaya.recsys.stream.flink.task.app

import java.util.Properties

import com.alibaba.fastjson.JSONObject
import com.typesafe.config.ConfigFactory
import com.ximalaya.flink.dsl.stream.`type`.FieldType
import com.ximalaya.flink.dsl.stream.api.udf.AbstractUserUdf
import com.ximalaya.flink.dsl.stream.udf.{EvalMethod, UserUdfFactory}
import org.apache.flink.configuration.Configuration
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer08
import org.apache.flink.streaming.util.serialization.JSONDeserializationSchema
import org.apache.flink.table.api.TableConfig
import org.apache.flink.table.api.scala.StreamTableEnvironment

import org.apache.flink.table.api.scala._
/**
  * @author martin.dong
  **/
class TestUdf extends AbstractUserUdf{
  def eval(i:Int):Long=System.currentTimeMillis()+i
}

object UserAppsCollector {

  val userIdParser:ObjectNode⇒Option[String]=node⇒Option.apply(node.get("userId")).map(node⇒node.textValue())
  val deviceIdParser:ObjectNode⇒Option[String]=node⇒Option.apply(node.get("deviceInfo")).flatMap(node⇒Option.apply(node.get("deviceId"))).map(node⇒node.textValue())
  val appsParser:ObjectNode⇒Option[JsonNode]= node⇒Option.apply(node.get("props")).flatMap(node⇒Option.apply(node.get("userApps")))
  def main(args: Array[String]): Unit = {
    // get the execution environment
    val environment:StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

  //创建自定义UDF
  //参数为 UDF名字 自定义的UDF实现类 自定义UDF添加的方法 返回自定义UDF实例和自定义UDF的class对象
    val (udf,str)=UserUdfFactory.createUserUdf("test",
      "com.ximalaya.recsys.stream.flink.task.app.TestUdf",
      List(EvalMethod(FieldType.LONG,Array(FieldType.INT))))

  //将自定义UDF的class作为参数传递给Flink作业
    val config1=new Configuration()
    val json=new JSONObject
    json.put(udf.getClass.getName,str)

    config1.setString("flink.dsl.stream.dynamic.udfs.info",json.toString)

    environment.getConfig.setGlobalJobParameters(config1)

    val tEnv = new StreamTableEnvironment(environment,new TableConfig)
    val config=ConfigFactory.load("kafka.properties")
    val zkList=config.getString("warm.up.kafka.zookeeper.list")
    val topic=config.getString("outter.app.kafka.topics")
    val groupId=config.getString("outter.app.kafka.group.id")
    val bootstrap=config.getString("xdcs.kafka.metadata.broker.list")
//
    val properties=new Properties()
    properties.setProperty("zookeeper.connect",zkList)
    properties.setProperty("group.id",groupId)
    properties.setProperty("bootstrap.servers",bootstrap)
//
    import org.apache.flink.streaming.api.scala._
    val kafkaConsumer=new FlinkKafkaConsumer08[ObjectNode](topic,new JSONDeserializationSchema,properties).setStartFromLatest()
    val stream=environment.addSource(kafkaConsumer)


    val ds=stream.map(node⇒{

        val userId=userIdParser.apply(node)
        val deviceId=deviceIdParser.apply(node)
        val apps=UserAppsUtils.getApps(appsParser.apply(node).map(node⇒node.textValue()))
        (userId,deviceId,apps)
    }).filter(_._3.isDefined).filter(v⇒v._1.isDefined || v._2.isDefined).map(v⇒{
      (v._1.getOrElse(v._2.get),v._3.get.mkString(","))
    })

    tEnv.registerDataStream("testTable",ds,'id,'apps)

    tEnv.registerFunction("test",udf)

    val table=tEnv.sqlQuery("select id,apps,test(11) from testTable")

    table.addSink(row⇒{
      val v1=row.getField(0)
      val v2=row.getField(1)
      val v3=row.getField(2)
      println(s"v1: $v1 v2: $v2 v3: $v3")
    })
    environment.execute("test job")
  }
}
```