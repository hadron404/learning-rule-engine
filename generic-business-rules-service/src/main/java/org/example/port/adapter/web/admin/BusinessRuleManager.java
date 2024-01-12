package org.example.port.adapter.web.admin;

import org.example.domain.model.rule.BusinessRule;
import org.example.domain.model.rule.BusinessRuleRepository;
import org.example.port.adapter.event.RulePersistedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// business rule 的 CRUD manager
@RestController
@RequestMapping("/admin/rule")
class BusinessRuleManager {

	private final BusinessRuleRepository businessRuleRepository;

	private final ApplicationEventPublisher applicationEventPublisher;

	public BusinessRuleManager(BusinessRuleRepository businessRuleRepository, ApplicationEventPublisher applicationEventPublisher) {
		this.businessRuleRepository = businessRuleRepository;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@PostMapping
	public void save(@RequestBody BusinessRule businessRule) {
		businessRuleRepository.save(businessRule);
		applicationEventPublisher.publishEvent(new RulePersistedEvent(null));
	}
}
