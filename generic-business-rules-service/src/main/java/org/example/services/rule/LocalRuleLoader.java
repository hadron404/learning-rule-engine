package org.example.services.rule;

import org.example.domain.model.rule.BusinessRule;
import org.example.port.adapter.event.RuleCachingEvent;
import org.example.port.adapter.factory.RuleFactory;
import org.example.services.AbstractRuleLoader;
import org.springframework.context.event.EventListener;
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
		System.out.printf("从应用枚举里已缓存%s条规则，当前共%s条规则%n", LocalRules.values().length, AbstractRuleLoader.size());
	}

	@EventListener(RuleCachingEvent.class)
	public void refreshCache(RuleCachingEvent<BusinessRule> event) {
		this.load();
	}
}
