package org.example.port.adapter.web;

import org.example.application.rule.media.RequestBusinessRuleCommand;
import org.example.domain.model.rule.BusinessRule;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 临时规则加载器
 */
@RestController()
@RequestMapping("/temp/rule")
class CacheRuleController {

	/**
	 * 添加一个临时规则加入到正在运行的应用服务
	 * 直到应用服务关闭，所有添加的临时规则就会消失
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBusinessRuleCommand BusinessRule rule) {
		rule.memoization();
	}
}
