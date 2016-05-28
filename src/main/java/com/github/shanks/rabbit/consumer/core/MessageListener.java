package com.github.shanks.rabbit.consumer.core;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.github.shanks.rabbit.msg.MessageModel;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageListener extends QueueListener {

	@RabbitListener(bindings = {
			@QueueBinding(exchange = @Exchange(value = "${sport.exchange}", type = ExchangeTypes.TOPIC, durable = "true"), key = "${sport.routingkey}", value = @Queue(value = "${sport.queue}", autoDelete = "false", durable = "true", exclusive = "false")) })
	public void recivice1(Channel channel, Message message, @Payload MessageModel messageModel)
			throws Exception {
		log.info("sport recivice");
		reciviceMessage(channel, message, messageModel);
	}

	@RabbitListener(bindings = {
			@QueueBinding(exchange = @Exchange(value = "${sport.exchange}", type = ExchangeTypes.TOPIC, durable = "true"), key = "${football.routingkey}", value = @Queue(value = "${football.queue}", autoDelete = "false", durable = "true", exclusive = "false")) })
	public void recivice2(Channel channel, Message message, @Payload MessageModel messageModel)
			throws Exception {
		log.info("football recivice");
		reciviceMessage(channel, message, messageModel);
	}

	@RabbitListener(bindings = {
			@QueueBinding(exchange = @Exchange(value = "${sport.exchange}", type = ExchangeTypes.TOPIC, durable = "true"), key = "${basketball.routingkey}", value = @Queue(value = "${basketball.queue}", autoDelete = "false", durable = "true", exclusive = "false")), })
	public void recivice3(Channel channel, Message message, @Payload MessageModel messageModel)
			throws Exception {
		log.info("basketball recivice");
		reciviceMessage(channel, message, messageModel);
	}

	@RabbitListener(bindings = {
			@QueueBinding(exchange = @Exchange(value = "", type = ExchangeTypes.DIRECT, durable = "true"), key = "${mail.routingKey}", value = @Queue(value = "${mail.queue}", autoDelete = "false", durable = "true", exclusive = "false")) })
	public void recivice4(Channel channel, Message message, @Payload MessageModel messageModel)
			throws Exception {
		log.info("mail recivice");
		reciviceMessage(channel, message, messageModel);
	}

}
