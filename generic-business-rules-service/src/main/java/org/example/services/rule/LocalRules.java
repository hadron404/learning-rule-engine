package org.example.services.rule;


import org.example.common.func.Functions;
import org.example.domain.model.rule.BusinessRule;
import org.example.domain.model.rule.BusinessRuleId;

import java.util.Set;

enum LocalRules {
	RULE_1 {
		public BusinessRule businessRule() {
			BusinessRule businessRule = new BusinessRule();
			businessRule.setName(new BusinessRuleId(this.name()));
			businessRule.setDescription("旅游黑名单规则");
			businessRule.setCondition("fn:execute(lhs,'a','b','c')");
			businessRule.setFunctions(Set.of(Functions.INTERSECTION));
			businessRule.setWarning("当前客户为旅游黑名单客户，不可下单旅游产品，如有疑问请联系旅游部！");
			return businessRule;
		}
	};

	public BusinessRule businessRule() {
		throw new UninitializedLocalRuleException(this.name());
	}
}
