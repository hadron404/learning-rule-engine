package org.example.port.adapter.persistence.rule;

import org.springframework.data.repository.ListCrudRepository;

interface BusinessRuleJPARepository extends ListCrudRepository<BusinessRuleEntity, Long> {

}
