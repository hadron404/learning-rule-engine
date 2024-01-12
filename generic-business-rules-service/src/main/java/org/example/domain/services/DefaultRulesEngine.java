package org.example.domain.services;

import org.example.application.AbstractRuleLoader;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 使用默认规则引擎的规则引擎服务
 */
@Service
public class DefaultRulesEngine {

	private static final Map<String, Rule> RULE_CACHE = AbstractRuleLoader.RULE_CACHE;

	private static final RulesEngine DEFAULT_RULES_ENGINE = new org.jeasy.rules.core.DefaultRulesEngine();

	public Map<Rule, Boolean> checkOne(String name, Map<String, Object> context) {
		Rules rules = new Rules();
		if (RULE_CACHE.get(name) == null) {
			throw new UnknownRuleException(name);
		}
		rules.register(RULE_CACHE.get(name));
		Facts facts = new Facts();
		context.forEach(facts::put);
		return DEFAULT_RULES_ENGINE.check(rules, facts);
	}

	public Map<Rule, Boolean> checkAll(Map<String, Object> context) {
		Rules rules = new Rules();
		RULE_CACHE.forEach((k, v) -> rules.register(v));
		Facts facts = new Facts();
		context.forEach(facts::put);
		return DEFAULT_RULES_ENGINE.check(rules, facts);
	}
}
