package org.example.domain.entity;


import jakarta.persistence.*;

@Entity
class BusinessRule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rule_id", nullable = false)
	private Long ruleId;

	private String name;
	private String description;
	@Column(name = "`condition`") // 加``处理关键字问题
	private String condition;
	private String functions;
	private int priority;
	private String warning;

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getFunctions() {
		return functions;
	}

	public void setFunctions(String functions) {
		this.functions = functions;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}
}
