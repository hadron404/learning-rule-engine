package org.example.domain.services;

import org.example.domain.model.rule.BusinessRuleRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;

public class DomainRegistry implements ApplicationContextAware {
	private DomainRegistry() {
	}

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
		DomainRegistry.applicationContext = applicationContext;
	}

	public static BusinessRuleRepository businessRuleRepository() {
		return applicationContext.getBean(BusinessRuleRepository.class);
	}

	public static ApplicationEventPublisher applicationEventPublisher() {
		return applicationContext.getBean(ApplicationEventPublisher.class);
	}

}
