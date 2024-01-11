package org.example.services.rule;


import org.example.domain.model.rule.BusinessRule;

enum LocalRules {
	RULE_1 {
		public BusinessRule businessRule() {
			return new BusinessRule();
		}
		//public String warning() {
		//	return "当前客户为旅游黑名单客户，不可下单旅游产品，如有疑问请联系旅游部！";
		//}
		//
		//public String description() {
		//	return "旅游黑名单规则";
		//}
		//
		//public String condition() {
		//	return "fn:execute(lhs,'a','b','c')";
		//}
		//public Set<Functions> functions(){
		//	return Set.of(Functions.INTERSECTION);
		//}
	};

		public BusinessRule businessRule() {
		throw new UninitializedLocalRuleException(this.name());
	}
}
