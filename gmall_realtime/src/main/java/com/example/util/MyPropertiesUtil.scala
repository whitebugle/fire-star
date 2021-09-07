package com.example.util

import java.io.InputStreamReader
import java.util.Properties

object MyPropertiesUtil {

  def main(args: Array[String]): Unit = {
    val properties = MyPropertiesUtil.load("config.properties")
    println(properties.getProperty("kafka.broker.list"))
  }

  def load(propertiesName:String): Properties ={
    val prop = new Properties()
    prop.load(new InputStreamReader(
      Thread.currentThread().getContextClassLoader.getResourceAsStream(propertiesName),
    "UTF-8"))

    prop
  }

}
