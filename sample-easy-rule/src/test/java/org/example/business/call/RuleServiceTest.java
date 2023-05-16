package org.example.business.call;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class RuleServiceTest {

	@Test
	void givenOneCustomer_whenCall_thenExecuteBlackListStrategy() {
		RuleService ruleService = new RuleService();
		Customer fact = new Customer();
		fact.setType("黑名单");
		ruleService.call(fact);
		Assertions.assertEquals("1", fact.getCallStrategy());
	}


	@Test
	void givenSomeCustomers_whenCall_thenExecuteStrategy() {
		RuleService ruleService = new RuleService();
		Customer fact = new Customer();
		fact.setType("黑名单");
		Customer fact1 = new Customer();
		fact1.setType("红名单");
		Customer fact2 = new Customer();
		fact1.setOwner("13155");
		List.of(fact, fact1, fact2)
			.forEach(ruleService::call);
		Assertions.assertEquals("1", fact.getCallStrategy());
	}
}
