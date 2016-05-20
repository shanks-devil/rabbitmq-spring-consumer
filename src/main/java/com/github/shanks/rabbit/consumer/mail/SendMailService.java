package com.github.shanks.rabbit.consumer.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.github.shanks.rabbit.msg.MessageModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SendMailService {

	@Value("${mail.from}")
	private String from;
	
	@Value("${mail.to}")
	private String to;
	
	@Autowired
	private MailSender mailSender;
	
	public void send(MessageModel message) {
		log.info("start send mail");
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(from);
		mail.setTo(to);
		mail.setSubject(String.valueOf(message.getMessageId()));
		mail.setText("this is sent by rabbit " + message);
		mailSender.send(mail);
		log.info("end send mail");
	}
	
}
