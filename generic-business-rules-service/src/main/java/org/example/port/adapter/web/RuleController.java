package org.example.port.adapter.web;

import org.example.domain.services.RulesEngineService;
import org.jeasy.rules.api.Rule;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("/rule")
class RuleController {

	private final RulesEngineService rulesEngineService;

	public RuleController(RulesEngineService rulesEngineService) {
		this.rulesEngineService = rulesEngineService;
	}

	@GetMapping("/{name}")
	public Map<Rule, Boolean> checkOne(
		@PathVariable String name,
		@RequestParam Map<String, Object> context) {
		return rulesEngineService.checkOne(name, context);
	}

	@GetMapping()
	public Map<Rule, Boolean> checkAll(@RequestParam Map<String, Object> context) {
		return rulesEngineService.checkAll(context);
	}

}
