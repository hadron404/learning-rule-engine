package org.example.services.rule;

import org.example.domain.model.RuleFactory;
import org.example.domain.model.rule.Rules;
import org.example.services.AbstractRuleLoader;
import org.springframework.stereotype.Component;

/**
 * 从本地加载规则
 */
@Component
class LocalRuleLoader extends AbstractRuleLoader {

	@Override
	public void load() {
		AbstractRuleLoader.load(RuleFactory.of(Rules.RULE_1));
	}
}
