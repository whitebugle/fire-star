package com.example.demo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping()
public class LoggerController {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @RequestMapping("/applog")
    public String applog(@RequestBody String mockLog) throws JsonProcessingException {

        log.info(mockLog);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(mockLog);
        JsonNode start = jsonNode.get("start");
        if (start != null) {
            kafkaTemplate.send("gmall_start_00",mockLog);
        }else {
            kafkaTemplate.send("gmall_event_00",mockLog);
        }

        return "success";
    }
}
