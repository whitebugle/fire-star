package com.example.bean

/**
 * create by young
 * date:21/6/6
 * desc: 商品样例类
 */
case class SkuInfo(id:String,
                   spu_id:String,
                   price:String,
                   sku_name:String,
                   tm_id:String,
                   category3_id:String,
                   create_time:String,

                   var category3_name:String,
                   var spu_name:String,
                   var tm_name:String
                  )