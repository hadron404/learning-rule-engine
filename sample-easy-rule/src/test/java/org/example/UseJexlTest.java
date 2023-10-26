package org.example;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.example.jexl.func.diyFunctions;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.jexl.JexlRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class UseJexlTest {
	private final static RulesEngine rulesEngine = new DefaultRulesEngine();

	private final Rules rules = new Rules();
	private final Facts facts = new Facts();

	@Test
	void number_eq() {
		Rule rule = new JexlRule()
			.name("myRule")
			.description("myRuleDescription")
			.priority(3)
			.when("lhs == 1");
		rules.register(rule);
		facts.put("lhs", 1);
		rulesEngine.check(rules, facts);
		Assertions.assertTrue(rulesEngine.check(rules, facts).get(rule));
	}

	@Test
	void number_lt() {
		Rule rule = new JexlRule()
			.name("myRule")
			.description("myRuleDescription")
			.priority(3)
			.when("lhs < 1");
		rules.register(rule);
		facts.put("lhs", 0);
		rulesEngine.check(rules, facts);
		Assertions.assertTrue(rulesEngine.check(rules, facts).get(rule));
	}


	@Test
	void number_gt() {
		Rule rule = new JexlRule()
			.name("myRule")
			.description("myRuleDescription")
			.priority(3)
			.when("lhs > 1");
		rules.register(rule);
		facts.put("lhs", 33);
		rulesEngine.check(rules, facts);
		Assertions.assertTrue(rulesEngine.check(rules, facts).get(rule));
	}


	@Test
	void string_contain() {
		Rule rule = new JexlRule()
			.name("myRule")
			.description("myRuleDescription")
			.priority(3)
			.when("lhs.contains('某个字符')");
		rules.register(rule);
		facts.put("lhs", "啊实打实某个字符阿松大");
		rulesEngine.check(rules, facts);
		Assertions.assertTrue(rulesEngine.check(rules, facts).get(rule));
	}

	@Test
	void string_eq() {
		Rule rule = new JexlRule()
			.name("myRule")
			.description("myRuleDescription")
			.priority(3)
			.when("lhs eq '某个字符'");
		rules.register(rule);
		facts.put("lhs", "某个字符");
		rulesEngine.check(rules, facts);
		Assertions.assertTrue(rulesEngine.check(rules, facts).get(rule));
	}

	@Test
	void array_string_contains() {
		Rule rule = new JexlRule()
			.name("myRule")
			.description("myRuleDescription")
			.priority(3)
			// 属于数组中的任意一个
			.when("lhs =~ ['c','b','d']");
		rules.register(rule);
		facts.put("lhs", "f");
		rulesEngine.check(rules, facts);
		Assertions.assertTrue(rulesEngine.check(rules, facts).get(rule));
	}

	@Test
	void array_nonDisjoint() {
		JexlBuilder jexlBuilder = new JexlBuilder();
		// 2、用户自定义函数（调用方式--》函数名:方法名）
		Map<String, Object> func = new LinkedHashMap<>();
		func.put("fn", new diyFunctions());
		jexlBuilder.namespaces(func);
		JexlEngine jexl = jexlBuilder.create();
		Rule rule = new JexlRule(jexl)
			.name("myRule")
			.description("myRuleDescription")
			.priority(3)
			// 属于数组中的任意一个
			.when("fn:nonDisjoint(lhs,'a','b','c')");
		rules.register(rule);
		facts.put("lhs", List.of("f", "v", "c"));
		rulesEngine.check(rules, facts);
		Assertions.assertTrue(rulesEngine.check(rules, facts).get(rule));
	}
}
