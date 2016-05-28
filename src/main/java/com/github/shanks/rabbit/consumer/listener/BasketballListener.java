package com.github.shanks.rabbit.consumer.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.shanks.rabbit.consumer.core.ListenerRegister;
import com.github.shanks.rabbit.msg.BasketBallMessage;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BasketballListener extends ListenerRegister<BasketBallMessage> {

	@Value("${basketball.queue}")
	@Getter
	private String queue;
	
	@Override
	public void receive(BasketBallMessage messageModel) throws Exception {
		if (messageModel.getMessageId() == 0) {
			log.info("basketball sleep 4000");
			Thread.sleep(4000);
		}
		log.info("basketball from {}",messageModel);
	}

}
