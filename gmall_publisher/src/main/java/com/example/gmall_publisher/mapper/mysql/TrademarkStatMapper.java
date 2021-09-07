package com.example.gmall_publisher.mapper.mysql;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * create by young
 * date:21/6/9
 * desc:
 */

public interface TrademarkStatMapper {

    List<Map> selectTradeSum(
            @Param("start_time") String startTime,
            @Param("end_time") String endTime,
            @Param("topN") int topN);
}