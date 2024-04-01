 package com.dicicip.starter.service;

 import java.util.HashMap;

 import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
 import org.apache.rocketmq.spring.core.RocketMQListener;
 import org.springframework.stereotype.Service;

 import com.fasterxml.jackson.databind.ObjectMapper;


 @Service
 @RocketMQMessageListener(topic = "1A0-s-88200049-01-0", consumerGroup = "broker-z")
 public class HashMapConsumer implements RocketMQListener<Object> {
     ObjectMapper mapper = new ObjectMapper();
     @Override
     public void onMessage(Object message) {
         try{
//             System.out.println("messageBody ==> " + mapper.writeValueAsString(message));
              System.out.println(message);
            
         } catch (Exception e) {
         }
     }
 }