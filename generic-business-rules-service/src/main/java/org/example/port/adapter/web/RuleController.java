package org.example.port.adapter.web;

import org.example.domain.services.DefaultRulesEngine;
import org.jeasy.rules.api.Rule;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("/rule")
class RuleController {

	private final DefaultRulesEngine defaultRulesEngine;

	public RuleController(DefaultRulesEngine defaultRulesEngine) {
		this.defaultRulesEngine = defaultRulesEngine;
	}

	@GetMapping("/{name}")
	public Map<Rule, Boolean> checkOne(
		@PathVariable String name,
		@RequestParam Map<String, Object> context) {
		return defaultRulesEngine.checkOne(name, context);
	}

	@GetMapping()
	public Map<Rule, Boolean> checkAll(@RequestParam Map<String, Object> context) {
		return defaultRulesEngine.checkAll(context);
	}

}
