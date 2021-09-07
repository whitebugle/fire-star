package com.example.gmall_publisher.mapper.clickhouse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * create by young
 * date:21/6/9
 * desc: 对订单宽表进行操作的接口
 */
public interface OrderWideMapper {
    //获取指定日期的交易额
    BigDecimal selectOrderAmountTotal(String date);

    //获取指定日期的分时交易额
    List<Map> selectOrderAmountHour(String date);
}