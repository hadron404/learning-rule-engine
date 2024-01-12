package org.example.port.adapter.web;

import org.example.domain.model.rule.BusinessRule;
import org.example.domain.model.rule.BusinessRuleId;
import org.example.port.adapter.event.RuleCachingEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 临时规则加载器
 */
@RestController()
@RequestMapping("/temp/rule")
class CacheRuleController {

	private final ApplicationEventPublisher applicationEventPublisher;

	public CacheRuleController(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	/**
	 * 添加一个临时规则加入到正在运行的应用服务
	 * 直到应用服务关闭，所有添加的临时规则就会消失
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody Map<String, String> param) {
		BusinessRule rule = new BusinessRule();
		rule.setName(new BusinessRuleId(param.get("name")));
		rule.setDescription(param.get("desc"));
		rule.setCondition(param.get("condition"));
		applicationEventPublisher.publishEvent(new RuleCachingEvent<>(rule));
	}
}
