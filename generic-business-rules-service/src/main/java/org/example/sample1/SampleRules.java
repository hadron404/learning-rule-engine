package org.example.sample1;


public enum SampleRules {
	RULE_1 {
		public String warning() {
			return "当前客户为旅游黑名单客户，不可下单旅游产品，如有疑问请联系旅游部！";
		}
		public String description() {
			return "旅游黑名单规则";
		}
	};

	public String warning() {
		return "";
	}
	public String description() {
		return "";
	}
}
