package org.example.services.rule;

import org.example.domain.model.rule.BusinessRuleRepository;
import org.example.services.AbstractRuleLoader;
import org.springframework.stereotype.Component;

/**
 * 从MySQL中加载规则
 */
//@Component
class MySQLRuleLoader extends AbstractRuleLoader {

	private final BusinessRuleRepository businessRuleRepository;

	public MySQLRuleLoader(BusinessRuleRepository businessRuleRepository) {
		this.businessRuleRepository = businessRuleRepository;
	}

	@Override
	public void load() {
		businessRuleRepository.findAllRules().forEach(i-> AbstractRuleLoader.load(i.toJexlRule()));
	}
}
