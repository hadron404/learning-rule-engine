package org.example.application;

import org.example.domain.model.rule.Rules;
import org.example.services.DefaultRulesEngine;
import org.jeasy.rules.api.Rule;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * sample 规则的应用接口
 */
@RestController()
@RequestMapping("/rule/sample")
class SampleRuleController {
	private final DefaultRulesEngine defaultRulesEngine;

	public SampleRuleController(DefaultRulesEngine defaultRulesEngine) {
		this.defaultRulesEngine = defaultRulesEngine;
	}

	/**
	 * 对应用服务中的本地缓存规则选择一个校验，并且返回特定警告信息
	 */
	@PostMapping("/{name}/warning")
	public String checkOneWithWarning(
		@PathVariable String name,
		@RequestBody Map<String, Object> context) {
		Map<Rule, Boolean> result = defaultRulesEngine.checkOne(name, context);
		return result.entrySet()
			.stream()
			.filter(Map.Entry::getValue)
			.map(Map.Entry::getKey)
			.filter(i -> i.getName().equals(name))
			.map(i -> Rules.valueOf(i.getName()))
			.map(Rules::warning)
			.limit(1)
			.collect(Collectors.joining(""));
	}
}
