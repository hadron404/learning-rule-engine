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

	public Map<Rule, Boolean> checkOne(String name, Facts facts) {
		Rules rules = new Rules();
		Rule rule = AbstractRuleLoader.RULE_CACHE.get(name);
		if (rule == null) {
			throw new UnknownRuleException(name);
		}
		rules.register(rule);
		return rulesEngine.check(rules, facts);
	}

	public Map<Rule, Boolean> checkAll(Facts facts) {
		Rules rules = new Rules();
		AbstractRuleLoader.RULE_CACHE.forEach((k, v) -> rules.register(v));
		return rulesEngine.check(rules, facts);
	}

	static class UnknownRuleException extends IllegalArgumentException {
		public UnknownRuleException(String s) {
			super("未知的规则：" + s);
		}
	}
}
