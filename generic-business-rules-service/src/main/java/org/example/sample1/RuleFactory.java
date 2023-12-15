package org.example.sample1;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.jexl.JexlRule;

import java.util.LinkedHashMap;
import java.util.Map;

public class RuleFactory {

	public static Rule instance() {
		JexlBuilder jexlBuilder = new JexlBuilder();
		Map<String, Object> func = new LinkedHashMap<>();
		func.put("fn", Functions.NON_DISJOINT);
		jexlBuilder.namespaces(func);
		JexlEngine jexl = jexlBuilder.create();
		return new JexlRule(jexl)
			.name(SampleRules.RULE_1.name())
			.description(SampleRules.RULE_1.description())
			.priority(1)
			.when("fn:execute(lhs,'a','b','c')");
	}
}
