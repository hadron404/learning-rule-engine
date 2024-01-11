package org.example.application;

import org.example.domain.model.rule.BusinessRule;
import org.example.domain.model.rule.BusinessRuleRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// business rule 的 CRUD manager
@RestController
@RequestMapping("/admin/rule")
class BusinessRuleManager {

	private final BusinessRuleRepository businessRuleRepository;

	public BusinessRuleManager(BusinessRuleRepository businessRuleRepository) {
		this.businessRuleRepository = businessRuleRepository;
	}

	public void save(BusinessRule businessRule) {
		businessRuleRepository.save(businessRule);
		businessRule.publishNewPersistedRuleEvent();
	}
}
