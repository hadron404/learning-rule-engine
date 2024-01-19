package org.example.infrastructure;

import jakarta.annotation.Nonnull;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.example.common.func.Functions;
import org.example.domain.model.rule.BusinessRule;
import org.jeasy.rules.jexl.JexlRule;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

// 演化成 businessRule 领域模型的builder
public class EasyRuleFactory {
	private EasyRuleFactory() {
	}

	public static JexlRule of(BusinessRule aRule) {
		return EasyRuleFactory.getRule(
			aRule.functions(), aRule.name().value(), aRule.description(),
			aRule.priority(), aRule.condition());
	}

	private static JexlRule getRule(
		@Nonnull Set<Functions> functions, @Nonnull String name,
		String description, int priority, String condition) {
		JexlBuilder jexlBuilder = new JexlBuilder();
		Map<String, Object> func = new LinkedHashMap<>();
		functions.forEach(i -> func.put(i.key(), i));
		jexlBuilder.namespaces(func);
		JexlEngine jexl = jexlBuilder.create();
		return new JexlRule(jexl)
			.name(name)
			.description(description)
			.priority(priority)
			.when(condition);
	}
}
