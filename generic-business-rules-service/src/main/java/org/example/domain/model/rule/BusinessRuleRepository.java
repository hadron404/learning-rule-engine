package org.example.domain.model.rule;

import java.util.List;

/**
 * 业务规则资源库
 */
public interface BusinessRuleRepository {


	List<BusinessRule> findAllRules();

	void save(BusinessRule businessRule);
}
