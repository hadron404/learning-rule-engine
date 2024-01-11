package org.example.services.rule;

class UninitializedLocalRuleException extends NullPointerException {

	public UninitializedLocalRuleException(String s) {
		super("未经初始化的规则：" + s);
	}
}
