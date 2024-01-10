package org.example.domain.entity;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class BusinessRuleJPARepoTest {

	@Resource
	private BusinessRuleJPARepo businessRuleJPARepo;

	@Test
	@Transactional
	void name() {
		BusinessRule businessRule = new BusinessRule();
		businessRule.setName("mysql黑名单规则");
		businessRule.setDescription("黑名单规则描述");
		businessRule.setCondition("1231");
		businessRule.setFunctions("12312");
		businessRule.setPriority(1);
		businessRule.setWarning("黑名单警告");
		businessRuleJPARepo.save(businessRule);
		List<BusinessRule> result = businessRuleJPARepo.findAll();
		System.out.println(result);
		Assertions.assertEquals(1, result.size());
	}
}
