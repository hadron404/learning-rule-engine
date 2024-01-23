package org.example.port.adapter.web;

import org.example.application.media.RequestFacts;
import org.example.infrastructure.EasyRuleEngine;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
		@RequestFacts Facts facts) {
		// return easyRuleEngine.checkOne(name, context);
		return Map.of();
	}

	@GetMapping()
	public Map<Rule, Boolean> checkAll(@RequestParam Facts facts) {
		// return easyRuleEngine.checkAll(context);
		return Map.of();
	}

}
