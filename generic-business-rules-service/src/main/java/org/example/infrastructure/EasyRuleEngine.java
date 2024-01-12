package org.example.infrastructure;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 基于easy-rule规则引擎
 */
@Component
public class EasyRuleEngine {

	private final RulesEngine rulesEngine;

	public EasyRuleEngine(RulesEngine rulesEngine) {
		this.rulesEngine = rulesEngine;
	}

	public Map<Rule, Boolean> checkOne(String name, Map<String, Object> context) {
		Rules rules = new Rules();
		if (AbstractRuleLoader.RULE_CACHE.get(name) == null) {
			throw new UnknownRuleException(name);
		}
		rules.register(AbstractRuleLoader.RULE_CACHE.get(name));
		Facts facts = new Facts();
		context.forEach(facts::put);
		return rulesEngine.check(rules, facts);
	}

	public Map<Rule, Boolean> checkAll(Map<String, Object> context) {
		Rules rules = new Rules();
		AbstractRuleLoader.RULE_CACHE.forEach((k, v) -> rules.register(v));
		Facts facts = new Facts();
		context.forEach(facts::put);
		return rulesEngine.check(rules, facts);
	}

	static class UnknownRuleException extends IllegalArgumentException {
		public UnknownRuleException(String s) {
			super("未知的规则：" + s);
		}
	}
}
