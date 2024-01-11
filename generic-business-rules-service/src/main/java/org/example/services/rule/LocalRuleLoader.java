package org.example.services.rule;

import org.example.port.adapter.factory.RuleFactory;
import org.example.services.AbstractRuleLoader;
import org.springframework.stereotype.Component;

/**
 * 从本地加载规则
 */
@Component
class LocalRuleLoader extends AbstractRuleLoader {

	@Override
	public void load() {
		for (LocalRules localRule : LocalRules.values()) {
			AbstractRuleLoader.load(RuleFactory.of(localRule.businessRule()));
		}
		System.out.printf("从应用枚举里初始化%s条规则%n", LocalRules.values().length);
	}
}
