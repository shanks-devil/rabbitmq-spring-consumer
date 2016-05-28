package com.github.shanks.rabbit.consumer.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.shanks.rabbit.consumer.core.ListenerRegister;
import com.github.shanks.rabbit.msg.FootballMessage;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FootballListener extends ListenerRegister<FootballMessage> {

	@Value("${football.queue}")
	@Getter
	private String queue;

	@Override
	public void receive(FootballMessage messageModel) throws Exception {
		if (messageModel.getMessageId() == 1) {
			log.info("football sleep 3000");
			Thread.sleep(3000);
		}
		log.info("football from {}", messageModel);
	}

}
