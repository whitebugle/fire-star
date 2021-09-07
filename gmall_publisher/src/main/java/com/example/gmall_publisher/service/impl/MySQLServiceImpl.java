package com.example.gmall_publisher.service.impl;


import com.example.gmall_publisher.mapper.mysql.TrademarkStatMapper;
import com.example.gmall_publisher.service.MySQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * create by young
 * date:21/6/9
 * desc:
 */
@Service
public class MySQLServiceImpl implements MySQLService {

    @Autowired
    TrademarkStatMapper trademarkStatMapper;

    @Override
    public List<Map> getTradeAmount(String startTime, String endTime, int topN) {
        return trademarkStatMapper.selectTradeSum(startTime,endTime,topN);
    }
}