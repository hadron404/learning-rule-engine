package org.example.business.call;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;

import java.util.Collection;

public class RuleService {
	public static final RulesEngine RULES_ENGINE;

	static {
		RulesEngineParameters parameters = new RulesEngineParameters()
			.skipOnFirstAppliedRule(true);
		RULES_ENGINE = new DefaultRulesEngine(parameters);
	}

	public Rules registerCallRules() {
		Rules rules = new Rules();
		rules.register(
			new BlackListRule(),
			new RedListRule(),
			new WithOutOwnerRule(),
			new WithOwnerRule()
		);
		return rules;
	}

	public void call(Customer fact) {
		Facts facts = new Facts();
		facts.put("customer", fact);
		RULES_ENGINE.fire(registerCallRules(), facts);
	}
}
