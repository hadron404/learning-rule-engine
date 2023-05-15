package org.example.business.call;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuleServiceTest {

    @Test
    void call() {
        RuleService ruleService = new RuleService();
        Customer fact = new Customer();
        fact.setType("黑名单");
        ruleService.call(fact);
        Assertions.assertEquals("1", fact.getCallStrategy());
    }
}