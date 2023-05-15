package org.example.business.call;

import org.jeasy.rules.annotation.*;

@Rule(name = "Rule of Black List for customer", description = "Define a rule about a customer who is black list and a strategy who should be executed")
public class BlackListRule {


    @Condition
    public boolean when(@Fact("customer") Customer fact) {
        //my rule conditions
        return "黑名单".equals(fact.getType());
    }

    @Action(order = 1)
    public void then(@Fact("customer") Customer fact) throws Exception {
        //my actions
        // fact.setCallStrategy("不允许转人工，挂机");
        fact.setCallStrategy("1");
    }

    @Priority
    public int getPriority() {
        return 1;
    }

}
