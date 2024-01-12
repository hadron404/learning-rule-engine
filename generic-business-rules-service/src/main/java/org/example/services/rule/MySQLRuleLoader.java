package org.example.services.rule;

import org.example.domain.model.rule.BusinessRule;
import org.example.domain.model.rule.BusinessRuleRepository;
import org.example.port.adapter.event.RulePersistedEvent;
import org.example.services.AbstractRuleLoader;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 从MySQL中加载规则
 */
@Component
class MySQLRuleLoader extends AbstractRuleLoader {

	private final BusinessRuleRepository businessRuleRepository;

	public MySQLRuleLoader(BusinessRuleRepository businessRuleRepository) {
		this.businessRuleRepository = businessRuleRepository;
	}

	@Override
	public void load() {
		List<BusinessRule> rules =  businessRuleRepository.findAllRules();
		rules.forEach(i -> AbstractRuleLoader.load(i.toJexlRule()));
		System.out.printf("从MySQL里已缓存%s条规则，当前共%s条规则%n%n", rules.size(), AbstractRuleLoader.size());
	}

	@EventListener(RulePersistedEvent.class)
	public void refreshCache(RulePersistedEvent event) {
		this.load();
	}
}
