package org.example.application.media;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import org.example.infrastructure.InitializationBeanConfiguration;
import org.jeasy.rules.api.Fact;
import org.jeasy.rules.api.Facts;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Map;

@Configuration
class RequestFactsWevMvcConfiguration implements WebMvcConfigurer {
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new RequestFactsConverter());
		WebMvcConfigurer.super.addFormatters(registry);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new RequestFactsWevMvcConfiguration.ContextCommandHandlerMethodArgumentResolver());
		WebMvcConfigurer.super.addArgumentResolvers(resolvers);
	}

	static class ContextCommandHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
		@Override
		public boolean supportsParameter(MethodParameter parameter) {
			return parameter.hasParameterAnnotation(RequestFacts.class);
		}

		@Override
		public Object resolveArgument(@Nonnull MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
			HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
			assert request != null;
			// System.out.println(URLDecoder.decode(request.getQueryString()));
			System.out.println(request.getParameterMap().toString());
			// JsonNode jsonNode = InitializationBeanConfiguration.objectMapper()
			// 	.readTree(Base64.getDecoder().decode(request.getQueryString()));

			return null;
		}
	}

	// @Component
	public static class RequestFactsConverter implements Converter<String, Facts> {
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
}