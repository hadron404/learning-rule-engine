package org.example.domain.model.rule;

import org.jeasy.rules.api.Rule;

public class BusinessRuleFactory {
	private BusinessRuleFactory() {
	}

	public static BusinessRule of(Rule rule) {
		return new BusinessRule();
	}
}
