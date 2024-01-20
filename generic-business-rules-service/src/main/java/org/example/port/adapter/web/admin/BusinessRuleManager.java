package org.example.port.adapter.web.admin;

import org.example.application.rule.media.RequestBusinessRuleCommand;
import org.example.domain.model.rule.BusinessRule;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// business rule 的 CRUD manager
@RestController
@RequestMapping("/admin/rule")
class BusinessRuleManager {

	@PostMapping
	public void save(@RequestBusinessRuleCommand BusinessRule rule) {
		rule.persistence();
	}
}
