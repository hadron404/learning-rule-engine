package org.example;

import org.jeasy.rules.api.Rule;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRuleLoader {

	public static final Map<String, Rule> RULE_CACHE = new HashMap<>();
	public static void load(Rule arule) {
		RULE_CACHE.put(arule.getName(), arule);
	}
	public abstract void load();
}
