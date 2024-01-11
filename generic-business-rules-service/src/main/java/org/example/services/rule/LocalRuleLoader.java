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
		for (Rules rule : Rules.values()) {
			AbstractRuleLoader.load(RuleFactory.of(rule));
		}
		System.out.printf("从应用枚举里初始化%s条规则%n", Rules.values().length);
	}
}
