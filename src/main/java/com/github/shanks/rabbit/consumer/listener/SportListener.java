package com.github.shanks.rabbit.consumer.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.shanks.rabbit.consumer.core.ListenerRegister;
import com.github.shanks.rabbit.msg.MessageModel;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SportListener extends ListenerRegister<MessageModel> {

	@Value("${sport.queue}")
	@Getter
	private String queue;
	
	@Override
	public void receive(MessageModel messageModel) throws Exception {
		if (messageModel.getMessageId() == 2) {
			log.info("sport sleep 1000");
			Thread.sleep(1000);
		}
		log.info("sport from {}", messageModel);
	}

}
