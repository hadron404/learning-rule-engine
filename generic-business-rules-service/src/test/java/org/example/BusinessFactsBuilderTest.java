package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

class BusinessFactsBuilderTest {
	@Test
	void name() {
		Set<String> items = Set.of("department", "customerLevel", "departments", "workAge");
		Map<String, Object> parameters = Map.of(
			"userId", 13155,
			"customerId", 12580
		);
		Map<String, Object> context = BusinessFactsBuilder.newInstance(items)
			.parameters(parameters)
			.customizeLoaders(Map.of(
				"department", (a) -> "c",
				"workAge", (a) -> "b"
			))
			.load();
		Assertions.assertEquals(context.get("department"), "c");
	}
}
