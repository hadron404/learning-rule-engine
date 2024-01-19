package org.example.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

@Configuration
public class InitializationBeanConfiguration implements ApplicationContextAware{

	private static ApplicationContext APPLICATION_CONTEXT;

	@Override
	public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
		InitializationBeanConfiguration.APPLICATION_CONTEXT = applicationContext;
	}

	public static ObjectMapper objectMapper() {
		return InitializationBeanConfiguration.APPLICATION_CONTEXT.getBean(ObjectMapper.class);
	}
}
