package com.github.shanks.rabbit.consumer.core;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class InitListener<T> {

	private Map<String, ListenerRegister<T>> handlerMapper = new HashMap<String, ListenerRegister<T>>();
	
	public void handler(String queue, T messageModel) throws Exception {
		handlerMapper.get(queue).receive(messageModel);
	}
	
	public void init(String queue, ListenerRegister<T> listener) throws Exception {
		handlerMapper.put(queue, listener);
	}
	
}
