package org.example.business.call;

import org.jeasy.rules.annotation.*;

@Rule(name = "Rule of customer who has not a owner", description = "Define a rule about a customer who has not have a owner and a strategy who should be executed")
public class WithOutOwnerRule {


	@Condition
	public boolean when(@Fact("customer") Customer fact) {
		//my rule conditions
		return fact.getOwner() == null || fact.getOwner().length() == 0;
	}

	@Action(order = 1)
	public void then(@Fact("customer") Customer fact) throws Exception {
		//my actions
		// fact.setCallStrategy("不允许转人工，移动客户至自取池，关闭客资任务");
		fact.setCallStrategy("3");
	}

	@Priority
	public int getPriority() {
		return 4;
	}

}
