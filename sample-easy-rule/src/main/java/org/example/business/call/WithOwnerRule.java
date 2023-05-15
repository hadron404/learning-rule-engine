package org.example.business.call;

import org.jeasy.rules.annotation.*;

@Rule(name = "Rule of customer who has a owner", description = "Define a rule about a customer who has a owner and a strategy who should be executed")
public class WithOwnerRule {


    @Condition
    public boolean when(@Fact("customer") Customer fact) {
        //my rule conditions
        return fact.getOwner() != null && fact.getOwner().length() != 0;
    }

    @Action(order = 1)
    public void then(@Fact("customer") Customer fact) throws Exception {
        //my actions
        // fact.setCallStrategy("不允许转人工，生成所属人回电任务");
        fact.setCallStrategy("4");
    }

    @Priority
    public int getPriority() {
        return 3;
    }

}
