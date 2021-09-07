package com.example.gmall_publisher.controller;


import com.example.gmall_publisher.service.MySQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by young
 * date:21/6/9
 * desc:
 */
@RestController
public class DataVController {
    @Autowired
    MySQLService mySQLService;

    //http://localhost:8070/trademark-sum?startTime=2021-06-08&endTime=2021-07-10&topN=5
    @RequestMapping("/trademark-sum")
    public Object trademarkSum(String startTime,String endTime,int topN){
        List<Map> mapList = mySQLService.getTradeAmount(startTime, endTime, topN);

      /*
        [{"amount":447505.66,"trademark_id":"6","trademark_name":"联想"},
         {"amount":275088.95,"trademark_id":"3","trademark_name":"apple"},
         {"amount":223659.21,"trademark_id":"2","trademark_name":"荣耀"},
         {"amount":118092.50,"trademark_id":"1","trademark_name":"小米"},
         {"amount":69754.00,"trademark_id":"4","trademark_name":"tcl"}]
       */

        //Map:{"trademark_id":"001", "trademark_name":"XXXX", "amount":1000}
        ArrayList<Map> resList = new ArrayList<>();

        for (Map map : mapList) {
            HashMap resMap = new HashMap<>();
            resMap.put("x",map.get("trademark_name"));
            resMap.put("y",map.get("amount"));
            resMap.put("s",1);

            resList.add(resMap);
        }

        /*
        [
          {
            "x": "上海",
            "y": 29,
            "s": "1"
          },
          {
            "x": "广州",
            "y": 27,
            "s": "1"
          }
         ]
         */

        return resList;
    }
}