package com.github.shanks.rabbit.consumer;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.shanks.rabbit.consumer.mail.SendMailService;
import com.github.shanks.rabbit.msg.MessageModel;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReviceMessage implements ChannelAwareMessageListener {

	@Autowired
	private SendMailService sendMailService;
	
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		try {
			MessageModel messageModel = SerializationUtils.deserialize(message.getBody());
			sendMailService.send(messageModel);
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
		}
	}

}
