package org.example.application.rule;

import org.example.domain.model.rule.BusinessRule;
import org.example.domain.model.rule.BusinessRuleId;

import java.util.Map;

public class SaveRuleCommand {

	private final Map<String, String> param;


	public SaveRuleCommand(Map<String, String> param) {
		this.param = param;
	}

	public BusinessRule toBusinessRule() {
		BusinessRule rule = new BusinessRule();
		rule.setName(new BusinessRuleId(param.get("name")));
		rule.setDescription(param.get("desc"));
		rule.setCondition(param.get("condition"));
		return rule;
	}

}
