package org.example.services;

public class UnknownRuleException extends IllegalArgumentException {

	public UnknownRuleException(String s) {
		super("未知的规则：" + s);
	}
}