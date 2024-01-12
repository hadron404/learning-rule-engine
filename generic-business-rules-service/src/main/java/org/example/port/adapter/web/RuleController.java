package org.example.port.adapter.web;

import org.example.infrastructure.EasyRuleEngine;
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
		@RequestParam Map<String, Object> context) {
		return easyRuleEngine.checkOne(name, context);
	}

	@GetMapping()
	public Map<Rule, Boolean> checkAll(@RequestParam Map<String, Object> context) {
		return easyRuleEngine.checkAll(context);
	}

}
