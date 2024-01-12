package org.example.application;

import jakarta.annotation.PostConstruct;
import org.example.domain.model.rule.BusinessRule;
import org.example.port.adapter.factory.RuleFactory;
import org.jeasy.rules.api.Rule;

import java.util.HashMap;
import java.util.Map;


public abstract class AbstractRuleLoader {

	public static final Map<String, Rule> RULE_CACHE = new HashMap<>();
	protected static void load(BusinessRule rule) {
		RULE_CACHE.put(rule.name().value(), RuleFactory.of(rule));
	}
	public abstract void load();

	@PostConstruct
	private void init() {
		this.load();
	}

	public static int size() {
		return RULE_CACHE.size();
	}
}
