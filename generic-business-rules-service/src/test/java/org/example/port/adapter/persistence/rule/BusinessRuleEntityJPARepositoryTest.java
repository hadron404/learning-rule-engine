package org.example.port.adapter.persistence.rule;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class BusinessRuleEntityJPARepositoryTest {

	@Resource
	private BusinessRuleJPARepository businessRuleJPARepository;

	@Test
	@Transactional
	void name() {
		BusinessRuleEntity businessRuleEntity = new BusinessRuleEntity();
		businessRuleEntity.setName("mysql黑名单规则");
		businessRuleEntity.setDescription("黑名单规则描述");
		businessRuleEntity.setCondition("1231");
		businessRuleEntity.setFunctions("12312");
		businessRuleEntity.setPriority(1);
		businessRuleEntity.setWarning("黑名单警告");
		businessRuleJPARepository.save(businessRuleEntity);
		List<BusinessRuleEntity> result = businessRuleJPARepository.findAll();
		System.out.println(result);
		Assertions.assertEquals(1, result.size());
	}
}
