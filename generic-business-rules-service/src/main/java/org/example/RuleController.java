package org.example;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.jexl.JexlRule;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/rule")
public class RuleController {

	private final Map<String, Rule> RULE_CACHE = new HashMap<>();

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody Map<String, String> param) {
		String name = param.get("name");
		String desc = param.get("desc");
		String condition = param.get("condition");
		Rule rule = new JexlRule()
			.name(name)
			.description(desc)
			.priority(3)
			.when(condition);
		RULE_CACHE.put(name, rule);
	}

	@GetMapping("/{name}")
	public Map<Rule, Boolean> check(@PathVariable String name,
		@RequestParam Map<String, String> context) {
		Rules rules = new Rules();
		rules.register(RULE_CACHE.get(name));
		Facts facts = new Facts();
		context.forEach(facts::put);
		RulesEngine rulesEngine = new DefaultRulesEngine();
		return rulesEngine.check(rules, facts);
	}

	@GetMapping()
	public Map<Rule, Boolean> check(@RequestParam Map<String, Object> context) {
		Rules rules = new Rules();
		RULE_CACHE.forEach((k, v) -> rules.register(v));
		Facts facts = new Facts();
		context.forEach(facts::put);
		RulesEngine rulesEngine = new DefaultRulesEngine();
		return rulesEngine.check(rules, facts);
	}
}
