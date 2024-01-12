package org.example.port.adapter.persistence.rule;

import org.example.domain.model.rule.BusinessRule;
import org.example.domain.model.rule.BusinessRuleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BusinessRuleRepo implements BusinessRuleRepository {

	private final BusinessRuleEntityJPARepository businessRuleEntityJPARepository;

	BusinessRuleRepo(BusinessRuleEntityJPARepository businessRuleEntityJPARepository) {
		this.businessRuleEntityJPARepository = businessRuleEntityJPARepository;
	}

	@Override
	public List<BusinessRule> findAllRules() {
		return BusinessRuleFactory.entitiesToDomainModels(businessRuleEntityJPARepository.findAll()).toList();
	}

	@Override
	public void save(BusinessRule businessRule) {
		businessRuleEntityJPARepository.save(BusinessEntityFactory.domainModelToEntity(businessRule));
	}
}
