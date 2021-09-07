package com.example.gmall_publisher.service;

import java.util.List;
import java.util.Map;

/**
 * create by young
 * date:21/6/9
 * desc:
 */
public interface MySQLService {
    List<Map> getTradeAmount(String startTime, String endTime, int topN);
}