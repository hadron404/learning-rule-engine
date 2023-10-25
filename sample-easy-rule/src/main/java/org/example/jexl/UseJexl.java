package org.example.jexl;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.jexl.JexlRule;

public class UseJexl {
	public void test() {
		Rule rule = new JexlRule()
			.name("myRule")
			.description("myRuleDescription")
			.priority(3)
			.when("c == a");
		Rules rules = new Rules();
		rules.register(rule);

		Facts facts = new Facts();
		facts.put("c", "a");
		RulesEngine rulesEngine = new DefaultRulesEngine();
		rulesEngine.fire(rules, facts);
	}
}
