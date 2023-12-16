package org.example;

import org.jeasy.rules.api.Rule;
import org.jeasy.rules.jexl.JexlRule;
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
@RequestMapping("/rule/loader/temp")
public class TemporaryRuleController {
	/**
	 * 添加一个临时规则加入到正在运行的应用服务
	 * 直到应用服务关闭，所有添加的临时规则就会消失
	 */
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
		AbstractRuleLoader.load(rule);
	}
}
