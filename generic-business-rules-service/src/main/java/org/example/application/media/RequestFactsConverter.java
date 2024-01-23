package org.example.application.media;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import org.example.infrastructure.InitializationBeanConfiguration;
import org.jeasy.rules.api.Fact;
import org.jeasy.rules.api.Facts;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RequestFactsConverter implements Converter<String, Facts> {
	@Override
	public Facts convert(@Nonnull String value) {
		try {
			Facts facts = new Facts();
			Map<String, ?> result = InitializationBeanConfiguration.objectMapper()
				.readValue(value, new TypeReference<>() {
				});
			result.entrySet().stream()
				.map(i -> new Fact<>(i.getKey(), i.getValue()))
				.forEach(facts::add);
			return facts;
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
