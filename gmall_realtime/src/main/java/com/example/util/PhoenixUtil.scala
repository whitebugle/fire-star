package com.example.util

import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet, ResultSetMetaData}

import com.alibaba.fastjson.JSONObject

import scala.collection.mutable.ListBuffer

/**
 * create by young
 * date:21/6/5
 *
 * Desc:  用于从Phoenix中查询数据
 * User_id     if_consumerd
 *   zs            1
 *   ls            1
 *   ww            1
 *
 *  期望结果：
 *  {"user_id":"zs","if_consumerd":"1"}
 *  {"user_id":"zs","if_consumerd":"1"}
 *  {"user_id":"zs","if_consumerd":"1"}
 */
object PhoenixUtil {

  def main(args: Array[String]): Unit = {
    val list: List[JSONObject] = queryList("select * from user_status2021")
    println(list)
  }

  def queryList(sql:String): List[JSONObject] ={

    val rsList: ListBuffer[JSONObject] = new ListBuffer[JSONObject]
    //注册驱动
    Class.forName("org.apache.phoenix.jdbc.PhoenixDriver")
    //建立连接
    val conn: Connection = DriverManager.getConnection("jdbc:phoenix:hadoop102,hadoop103,hadoop104:2181")
    //创建数据库操作对象
    val ps: PreparedStatement = conn.prepareStatement(sql)
    //执行SQL语句
    val rs: ResultSet = ps.executeQuery()

    val rsMetaData: ResultSetMetaData = rs.getMetaData  //获取表头

    //处理结果集
    while(rs.next()){
      val userStatusJsonObj = new JSONObject()

      //{"user_id":"zs","if_consumerd":"1"}
      for(i <- 1 to rsMetaData.getColumnCount){
//        println("rsMetaDataColumnName: "+ rsMetaData.getColumnName(i))
        //{"user_id":"zs","if_consumerd":"1"}
        userStatusJsonObj.put(rsMetaData.getColumnName(i),rs.getObject(i))
      }
      rsList.append(userStatusJsonObj)
    }

    //释放资源
    rs.close()
    ps.close()
    conn.close()
    rsList.toList
  }

}