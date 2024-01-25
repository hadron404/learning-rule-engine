package org.example.domain.model.rule;


import org.example.common.func.Functions;

import java.util.Set;

public enum LocalRules {
	RULE_1 {

		static final BusinessRule businessRule = new BusinessRule();
		static {
			businessRule.setDescription("旅游黑名单规则");
			businessRule.setCondition("customerId =~ ['10053'] and fn:execute(orderGoodsCategoryIds,'240')");
			businessRule.setFunctions(Set.of(Functions.INTERSECTION));
			businessRule.setWarning("当前客户为旅游黑名单客户，不可下单旅游产品，如有疑问请联系旅游部！");
		}

		public BusinessRule businessRule() {
			businessRule.setName(new BusinessRuleId(this.name()));
			return businessRule;
		}
	};

	public BusinessRule businessRule() {
		throw new UninitializedLocalRuleException(this.name());
	}

	static class UninitializedLocalRuleException extends NullPointerException {
		public UninitializedLocalRuleException(String s) {
			super("未经初始化的规则：" + s);
		}
	}
}
