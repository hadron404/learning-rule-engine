package org.example.sample1;

import org.example.AbstractRuleLoader;
import org.springframework.stereotype.Component;

/**
 * 从本地加载规则
 */
@Component
class LocalRuleLoader extends AbstractRuleLoader {

	@Override
	public void load() {
		AbstractRuleLoader.load(RuleFactory.rule1());
	}
}
