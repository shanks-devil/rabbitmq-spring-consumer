package com.github.shanks.rabbit.consumer.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.github.shanks.rabbit.consumer.core.ListenerRegister;
import com.github.shanks.rabbit.msg.MailMessage;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NotifyListener extends ListenerRegister<MailMessage> {

	@Value("${mail.queue}")
	@Getter
	protected String queue;

	@Value("${mail.from}")
	private String from;

	@Value("${mail.to}")
	private String to;

//	@Autowired
//	private MailSender mailSender;

	@Override
	public void receive(MailMessage messageModel) throws Exception {
		if (messageModel.getMessageId() == 0) {
			log.info("mail sleep 2000");
			Thread.sleep(2000);
		}
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(from);
		mail.setTo(to);
		mail.setSubject(String.valueOf(messageModel.getMessageId()));
		mail.setText("this is sent by rabbit " + messageModel);
//		mailSender.send(mail);
		log.info("mail form {}", messageModel);
	}
}
