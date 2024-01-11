package org.example.port.adapter.persistence.rule;

import org.example.domain.model.rule.BusinessRule;
import org.example.domain.model.rule.BusinessRuleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BusinessRuleRepo implements BusinessRuleRepository {

	private final BusinessRuleJPARepository businessRuleJPARepository;

	BusinessRuleRepo(BusinessRuleJPARepository businessRuleJPARepository) {
		this.businessRuleJPARepository = businessRuleJPARepository;
	}

	@Override
	public List<BusinessRule> findAllRules() {
		return BusinessRuleFactory.entitiesToDomainModels(businessRuleJPARepository.findAll()).toList();
	}
}
