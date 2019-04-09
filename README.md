# Xlink

### xlink-1.6.2

#### xlink-table_2.11

##### 特性

* **支持在Janino编译Flink SQL生成的Java源代码时传递一个能够加载由cglib动态生成的类的字节码类加载器**


##### 源码改动说明

* [在Compiler接口中将Configuration传递下来](https://github.com/dongjiaqiang/Xlink/blob/master/xlink-1.6.2/xlink-table_2.11/src/main/scala/org/apache/flink/table/codegen/Compiler.scala)
* [xlink包中存放用于支持加载器实现的代码](https://github.com/dongjiaqiang/Xlink/tree/master/xlink-1.6.2/xlink-table_2.11/src/main/scala/org/apache/flink/table/xlink)


##### 参数说明

**为支持创建能够加载由cglib动态生成的类的字节码类加载器 需要通过配置将给UDF动态添加的方法相关信息(方法名,返回值类型,参数类型)添加进来**

* **键名字为flink.dsl.stream.dynamic.udfs.info**

* **值为一个JSON字符串**

```json

```