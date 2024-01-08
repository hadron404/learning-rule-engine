package org.example.domain.model.rule;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 业务规则资源库
 */
@Repository
public interface BusinessRuleRepository {


	List<BusinessRule> findAllRules();
}
