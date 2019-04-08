package org.apache.flink.table.functions

/**
  *
  * @author martin.dong
  **/

class UserUdfFunction extends ScalarFunction{
  override def open(context: FunctionContext): Unit = {}
  override def close(): Unit = {}
  override def isDeterministic: Boolean = false
}
