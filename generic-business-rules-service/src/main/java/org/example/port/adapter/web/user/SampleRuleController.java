package org.example.port.adapter.web.user;

import org.example.domain.model.rule.BusinessRule;
import org.example.domain.model.rule.BusinessRuleFactory;
import org.example.infrastructure.EasyRuleEngine;
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
	private final EasyRuleEngine easyRuleEngine;

	public SampleRuleController(EasyRuleEngine easyRuleEngine) {
		this.easyRuleEngine = easyRuleEngine;
	}

	/**
	 * 对应用服务中的本地缓存规则选择一个校验，并且返回特定警告信息
	 */
	@PostMapping("/{name}/warning")
	public String checkOneWithWarning(
		@PathVariable String name,
		@RequestBody Map<String, Object> context) {
		Map<Rule, Boolean> result = easyRuleEngine.checkOne(name, context);
		return result.entrySet()
			.stream()
			.filter(Map.Entry::getValue)
			.map(Map.Entry::getKey)
			.filter(i -> i.getName().equals(name))
			.map(BusinessRuleFactory::of)
			.map(BusinessRule::warning)
			.limit(1)
			.collect(Collectors.joining(""));
	}
}
