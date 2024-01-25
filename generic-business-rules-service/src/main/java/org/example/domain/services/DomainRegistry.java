package org.example.domain.services;

import org.example.domain.model.rule.BusinessRuleRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.core.convert.ConversionService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class DomainRegistry
	implements ApplicationContextAware, ApplicationEventPublisherAware {
	private DomainRegistry() {
	}

	private static ApplicationContext applicationContext;
	public static ApplicationEventPublisher APPLICATION_EVENT_PUBLISHER;

	@Override
	public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
		DomainRegistry.applicationContext = applicationContext;
	}

	@Override
	public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher applicationEventPublisher) {
		DomainRegistry.APPLICATION_EVENT_PUBLISHER = applicationEventPublisher;
	}

	public static BusinessRuleRepository businessRuleRepository() {
		return applicationContext.getBean(BusinessRuleRepository.class);
	}

	public static ConversionService conversionService() {
		return applicationContext.getBean(ConversionService.class);
	}
}
