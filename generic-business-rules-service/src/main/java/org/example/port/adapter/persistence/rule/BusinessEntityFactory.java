package org.example.port.adapter.persistence.rule;

import org.example.domain.model.rule.BusinessRule;

class BusinessEntityFactory {
	private BusinessEntityFactory() {
	}

	public static BusinessRuleEntity domainModelToEntity(BusinessRule rule) {
		return new BusinessRuleEntity();
	}
}
