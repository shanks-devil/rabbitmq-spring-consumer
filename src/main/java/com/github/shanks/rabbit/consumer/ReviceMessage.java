package com.github.shanks.rabbit.consumer;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.github.shanks.rabbit.msg.MessageModel;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@RabbitListener
@Slf4j
public class ReviceMessage implements ChannelAwareMessageListener {

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		try {
			MessageModel messageModel = SerializationUtils.deserialize(message.getBody());
			System.out.println(messageModel);
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, true);
		}
	}

}
