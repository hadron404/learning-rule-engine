package org.example.domain.services;

import org.example.application.AbstractRuleLoader;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 使用默认规则引擎的规则引擎服务
 */
@Service
public class RulesEngineService {

	private static final RulesEngine DEFAULT_RULES_ENGINE = new DefaultRulesEngine();

	public Map<Rule, Boolean> checkOne(String name, Map<String, Object> context) {
		Rules rules = new Rules();
		if (AbstractRuleLoader.RULE_CACHE.get(name) == null) {
			throw new UnknownRuleException(name);
		}
		rules.register(AbstractRuleLoader.RULE_CACHE.get(name));
		Facts facts = new Facts();
		context.forEach(facts::put);
		return DEFAULT_RULES_ENGINE.check(rules, facts);
	}

	public Map<Rule, Boolean> checkAll(Map<String, Object> context) {
		Rules rules = new Rules();
		AbstractRuleLoader.RULE_CACHE.forEach((k, v) -> rules.register(v));
		Facts facts = new Facts();
		context.forEach(facts::put);
		return DEFAULT_RULES_ENGINE.check(rules, facts);
	}
}
