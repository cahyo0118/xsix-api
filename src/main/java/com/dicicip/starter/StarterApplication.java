package com.dicicip.starter;

import java.time.Instant;
import java.util.List;

import javax.annotation.Resource;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.core.MessagePostProcessor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.apache.rocketmq.spring.core.RocketMQTemplate;

@RequiredArgsConstructor
@SpringBootApplication
public class StarterApplication {

	// @Resource
	// private RocketMQTemplate rocketMQTemplate;

	// @Resource(name = "extRocketMQTemplate")
	// private RocketMQTemplate extRocketMQTemplate;

	// @Bean
	// ApplicationListener<ApplicationReadyEvent> ready(RocketMQTemplate template) {
	// 	return event -> {

	// 		Instant now = Instant.now();
	// 		String destination = "greetings-topic";

	// 		for (String name : "Tammie,Kimly,Josh,Rob,Mario,Mia".split(",")) {

	// 			Greeting payload = new Greeting("Hello @ " + name + " @ " + now.toString());
	// 			MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {

	// 				@Override
	// 				public Message<?> postProcessMessage(Message<?> message) {
	// 					String headerValue = Character.toString(name.toLowerCase().charAt(0));
	// 					return MessageBuilder
	// 							.fromMessage(message)
	// 							.setHeader("letter", headerValue)
	// 							.build();
	// 				}
	// 			};
	// 			template.convertAndSend(destination, payload, messagePostProcessor);
	// 		}
	// 	};
	// }

	public static void main(String[] args) {
		SpringApplication.run(StarterApplication.class, args);
	}

	// @Override
	// public void run(String... args) throws Exception {
	// 	// This is an example of pull consumer using rocketMQTemplate.
	// 	List<String> messages = rocketMQTemplate.receive(String.class);
	// 	System.out.printf("receive from rocketMQTemplate, messages=%s %n", messages);

	// 	// This is an example of pull consumer using extRocketMQTemplate.
	// 	// messages = extRocketMQTemplate.receive(String.class);
	// 	// System.out.printf("receive from extRocketMQTemplate, messages=%s %n", messages);
	// }

	// @Service
	// @RocketMQMessageListener(topic = "cart-item-add-topic", consumerGroup =
	// "cart-consumer_cart-item-add-topic")
	// public class CardItemAddConsumer implements RocketMQListener<Object> {
	// public void onMessage(Object addItemEvent) {
	// System.out.println("Adding item: {}" + addItemEvent);
	// // additional logic
	// }
	// }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Greeting {
	private String message;
}