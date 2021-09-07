package com.example.bean

/**
 * create by young
 * date:21/6/5
 * desc: 用于映射用户状态表的样例类
 */
case class UserStatus(
                       userId:String,  //用户id
                       ifConsumed:String //是否消费过   0首单   1非首单
                     )