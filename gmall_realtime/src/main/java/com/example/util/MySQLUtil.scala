package com.example.util

import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet, ResultSetMetaData}
import com.alibaba.fastjson.JSONObject
import scala.collection.mutable.ListBuffer

/**
 * create by young
 * date:21/6/9
 * desc: 从MySQL中查询数据的工具类
 */
object MySQLUtil {

  def main(args: Array[String]): Unit = {
    val list: List[JSONObject] = queryList("select * from offset_2021")
    println(list)
  }
  def queryList(sql:String): List[JSONObject] ={
    val rsList: ListBuffer[JSONObject] = new ListBuffer[JSONObject]
    //注册驱动
    Class.forName("com.mysql.jdbc.Driver")
    //建立连接
    val conn: Connection = DriverManager.getConnection(
      "jdbc:mysql://hadoop102:3306/gmall2021_rs?characterEncoding=utf-8&useSSL=false",
      "root",
      "root")

    //创建数据库操作对象
    val ps: PreparedStatement = conn.prepareStatement(sql)
    //执行SQL语句
    val rs: ResultSet = ps.executeQuery()
    val rsMetaData: ResultSetMetaData = rs.getMetaData
    //处理结果集
    while(rs.next()){
      val userStatusJsonObj = new JSONObject()
      for(i <-1 to rsMetaData.getColumnCount){
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