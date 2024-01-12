package org.example.port.adapter.web.admin;

import org.example.application.rule.SaveRuleCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// business rule 的 CRUD manager
@RestController
@RequestMapping("/admin/rule")
class BusinessRuleManager {

	@PostMapping
	public void save(@RequestBody SaveRuleCommand command) {
		command.toBusinessRule().persistence();
	}
}
