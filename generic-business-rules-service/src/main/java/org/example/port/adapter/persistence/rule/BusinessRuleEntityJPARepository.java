package org.example.port.adapter.persistence.rule;

import org.springframework.data.repository.ListCrudRepository;

interface BusinessRuleEntityJPARepository extends ListCrudRepository<BusinessRuleEntity, Long> {

}
