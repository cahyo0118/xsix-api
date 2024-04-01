//package com.dicicip.starter.service;
//
//import java.util.Date;
//import java.util.HashMap;
//
//import org.apache.rocketmq.common.message.Message;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQReplyListener;
//// import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.stereotype.Service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@Service
//@RocketMQMessageListener(topic = "1A0-s-88200049-01-0", consumerGroup = "broker-x")
//public class TayoMapConsumer implements RocketMQReplyListener<Object, Object> {
//    // ObjectMapper mapper = new ObjectMapper();
//    @Override
//    public Object onMessage(Object message) {
//        try {
//            System.out.println("\n");
//            System.out.println("\n");
//            System.out.println("\n");
//            System.out.println("======================== String Message ========================");
//            // Object jsonObject = mapper.readValue(message, Object.class);
//            // String prettyJson =
//            // mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
//
//            // System.out.println(prettyJson);
//
//            System.out.println(message);
//
//            // System.out.println("C_REPLY_TO ==> " + message.getProperty("REPLY_TO"));
//            // System.out.println("C_REPLY_TO ==> " + message.getUserProperty("REPLY_TO"));
//
//        } catch (Exception e) {
//        }
//
//        // return "{\"name\":\"172.21.26.235@8251-1A0-2.1.7-70588#V1_7_3#B2\"}";
//        // return new HashMap<String, String>(){{
//        // put("", "");
//        // }};
//
//        // Message returnMessage = new Message();
//        // returnMessage.setTopic("1A0-s-88200049-01-0");
//        // returnMessage.setDeliverTimeMs(new Date().getTime());
//        // // returnMessage.putUserProperty("REPLY_TO_CLIENT", "aaaa");
//        // System.out.println("REPLY_TO ==> " + returnMessage.getProperty("REPLY_TO"));
//        // System.out.println("REPLY_TO ==> " + returnMessage.getUserProperty("REPLY_TO"));
//        // System.out.println("REPLY_TO_CLIENT ==> " + returnMessage.getProperty("REPLY_TO_CLIENT"));
//        // System.out.println("REPLY_TO_CLIENT ==> " + returnMessage.getUserProperty("REPLY_TO_CLIENT"));
//        // // returnMessage.putUserProperty("REPLY_TO_CLIENT2", "tayoo");
//        // return returnMessage;
//
//        return new Object();
//
//    }
//}