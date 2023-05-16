package org.example.business.call;

import org.jeasy.rules.annotation.*;

@Rule(name = "Rule of red List for customer", description = "Define a rule about a customer who is red list and a strategy who should be executed")
public class RedListRule {


	@Condition
	public boolean when(@Fact("customer") Customer fact) {
		//my rule conditions
		return "红名单".equals(fact.getType());
	}

	@Action(order = 1)
	public void then(@Fact("customer") Customer fact) throws Exception {
		//my actions
		// fact.setCallStrategy("不允许转人工，生成红名单任务");
		fact.setCallStrategy("2");
	}

	@Priority
	public int getPriority() {
		return 2;
	}

}
