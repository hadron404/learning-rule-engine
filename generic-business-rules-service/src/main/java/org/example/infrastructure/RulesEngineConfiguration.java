package org.example.infrastructure;

import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RulesEngineConfiguration {

	@Bean
	DefaultRulesEngine getDefaultRulesEngine() {
		return new DefaultRulesEngine();
	}
}
