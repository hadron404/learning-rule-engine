package org.example.port.adapter.web;

import org.example.application.media.RequestContextCommand;
import org.example.infrastructure.EasyRuleEngine;
import org.jeasy.rules.api.Fact;
import org.jeasy.rules.api.Rule;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController()
@RequestMapping("/rule")
class RuleController {

	private final EasyRuleEngine easyRuleEngine;

	public RuleController(EasyRuleEngine easyRuleEngine) {
		this.easyRuleEngine = easyRuleEngine;
	}

	@GetMapping("/{name}")
	public Map<Rule, Boolean> checkOne(
		@PathVariable String name,
		@RequestContextCommand Set<Fact<?>> facts) {
		// return easyRuleEngine.checkOne(name, context);
		return Map.of();
	}

	@GetMapping()
	public Map<Rule, Boolean> checkAll(@RequestContextCommand Set<Fact<?>> facts) {
		// return easyRuleEngine.checkAll(context);
		return Map.of();
	}

}
