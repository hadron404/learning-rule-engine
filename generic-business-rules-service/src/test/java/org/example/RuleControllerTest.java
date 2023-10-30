package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.*;

class RuleControllerTest {

	@Test
	void name() {
		System.out.println(
			UriComponentsBuilder.fromHttpUrl("http://localhost:8080/rule/name?c=1&c=2")
			.build().getQuery()
		);
	}
}
