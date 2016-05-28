package com.github.shanks.rabbit.consumer.core;

import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;

import com.github.shanks.rabbit.msg.MessageModel;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QueueListener  {

	@Autowired
	private InitListener<Object> listener;
	
	protected void reciviceMessage(Channel channel, Message message, @Payload MessageModel messageModel) throws Exception{
		try {
			listener.handler(getQueue(message), messageModel);
			channel.basicAck(getDeliveryTag(message), false);
		} catch (Exception e) {
			channel.basicNack(getDeliveryTag(message), false, true);
			log.error(e.getMessage(), e);
		}
	}
	
	private Long getDeliveryTag(Message message) {
		return message.getMessageProperties().getDeliveryTag();
	}

	private String getQueue(Message message) {
		return message.getMessageProperties().getConsumerQueue();
	}
}
