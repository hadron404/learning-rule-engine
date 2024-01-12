package org.example.application.rule;

import org.example.application.AbstractRuleLoader;
import org.example.domain.model.rule.BusinessRule;
import org.example.port.adapter.event.RuleCachingEvent;
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
			AbstractRuleLoader.load(localRule.businessRule());
		}
		System.out.printf("从应用枚举里已缓存[ %s ]条规则，当前共[ %s ]条规则%n", LocalRules.values().length, AbstractRuleLoader.size());
	}

	@EventListener(RuleCachingEvent.class)
	public void refreshCache(RuleCachingEvent<BusinessRule> event) {
		AbstractRuleLoader.load(event.source());
		System.out.printf("处理临时性规则添加时事件，添加[ %s ]规则，当前共[ %s ]条规则%n", event.source().name(), AbstractRuleLoader.size());
	}
}
