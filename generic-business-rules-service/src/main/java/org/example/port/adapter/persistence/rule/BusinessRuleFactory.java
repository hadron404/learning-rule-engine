package org.example.port.adapter.persistence.rule;

import org.example.common.func.Functions;
import org.example.domain.model.rule.BusinessRule;
import org.example.domain.model.rule.BusinessRuleId;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class BusinessRuleFactory {
	private BusinessRuleFactory() {
	}

	static BusinessRule entityToDomainModel(BusinessRuleEntity entity) {
		BusinessRule result = new BusinessRule();
		result.setName(new BusinessRuleId(entity.getName()));
		result.setDescription(entity.getDescription());
		result.setCondition(entity.getCondition());
		result.setFunctions(
			Optional.ofNullable(entity.getFunctions())
				.map(f -> Stream.of(f.split(","))
					.map(Functions::valueOf)
					.collect(Collectors.toSet()))
				.orElse(Set.of()));
		result.setPriority(entity.getPriority());
		result.setWarning(entity.getWarning());
		return result;
	}

	static Stream<BusinessRule> entitiesToDomainModels(Collection<BusinessRuleEntity> entities) {
		return entities.stream().map(BusinessRuleFactory::entityToDomainModel);
	}
}
