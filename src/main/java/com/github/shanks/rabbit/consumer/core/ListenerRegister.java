package com.github.shanks.rabbit.consumer.core;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ListenerRegister<T> implements InitializingBean {

	@Autowired
	private InitListener<T> initListener;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		initListener.init(getQueue(),this);
	}

	public abstract String getQueue();
	
	public abstract void receive(T messageModel) throws Exception;
	
}
