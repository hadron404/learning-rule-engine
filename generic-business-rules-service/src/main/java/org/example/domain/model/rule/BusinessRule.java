package org.example.domain.model.rule;

import org.example.common.func.Functions;
import org.example.port.adapter.factory.RuleFactory;
import org.jeasy.rules.jexl.JexlRule;

import java.util.Set;

/**
 * 业务规则领域模型
 */
public class BusinessRule {

	private BusinessRuleId name;
	private String description;
	private String condition;
	private Set<Functions> functions;
	private int priority;
	private String warning;

	public JexlRule toJexlRule() {
		return RuleFactory.of(this);
	}

	public Set<Functions> functions() {
		return this.functions;
	}

	public BusinessRuleId name() {
		return this.name;
	}

	public String condition() {
		return this.condition;
	}

	public String description() {
		return this.description;
	}

	public int priority() {
		return this.priority;
	}

	public String warning() {
		return warning;
	}

	public void setName(BusinessRuleId name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public void setFunctions(Set<Functions> functions) {
		this.functions = functions;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

	//  规则已缓存事件（应用关闭 ，规则消失）
	public void publishNewCachedRuleEvent() {

	}

	// 规则已持久化事件（应用关闭，规则在应用重启时会重建）
	public void publishNewPersistedRuleEvent() {

	}
}
