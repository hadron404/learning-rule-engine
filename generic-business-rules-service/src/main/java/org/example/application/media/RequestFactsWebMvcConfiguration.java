package org.example.application.media;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import org.example.domain.services.DomainRegistry;
import org.example.infrastructure.InitializationBeanConfiguration;
import org.jeasy.rules.api.Fact;
import org.jeasy.rules.api.Facts;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionException;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
class RequestFactsWebMvcConfiguration implements WebMvcConfigurer {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(new JsonStyleFactsFormatter());
		WebMvcConfigurer.super.addFormatters(registry);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new RequestFactsMethodArgumentResolver());
		WebMvcConfigurer.super.addArgumentResolvers(resolvers);
	}

	static class RequestFactsMethodArgumentResolver implements HandlerMethodArgumentResolver {
		private final static ArrayStringMapToFactsConverter
			ARRAY_STRING_MAP_TO_FACTS_CONVERTER = new ArrayStringMapToFactsConverter();

		@Override
		public boolean supportsParameter(MethodParameter parameter) {
			return parameter.hasParameterAnnotation(RequestFacts.class);
		}

		@Override
		public Object resolveArgument(@Nonnull MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
			return ARRAY_STRING_MAP_TO_FACTS_CONVERTER.convert(webRequest.getParameterMap());
		}
	}

	static class ArrayStringMapToFactsConverter implements Converter<Map<String, String[]>, Facts> {
		private final ConversionService conversionService = DomainRegistry.conversionService();

		@Override
		public Facts convert(Map<String, String[]> source) {
			Facts result = new Facts();
			source.forEach((k, v) -> arrayStringToFact(v)
				.ifPresent(i -> result.add(new Fact<>(k, i)))
			);
			return result;
		}

		private final LinkedHashSet<Class<?>> DEFAULT_SUPPORT_CONVERT_TYPES = new LinkedHashSet<>() {
			{
				add(Boolean.class);
				// add(Double.class);
				// add(Integer.class);
				add(String.class);
			}
		};

		private Optional<?> arrayStringToFact(String[] str) {
			if (str.length == 0) {
				return Optional.empty();
			}
			if (str.length == 1) {
				for (Class<?> type : DEFAULT_SUPPORT_CONVERT_TYPES) {
					try {
						return Optional.ofNullable(this.conversionService.convert(str[0], type));
					} catch (ConversionException | IllegalArgumentException ignored) {
					}
				}
			}
			List<Class<?>> supportConvertTypes = Arrays.stream(str)
				.map(i -> {
					for (Class<?> type : DEFAULT_SUPPORT_CONVERT_TYPES) {
						try {
							this.conversionService.convert(i, type);
							return type;
						} catch (ConversionException | IllegalArgumentException ignored) {
						}
					}
					return null;
				})
				.filter(Objects::nonNull)
				.distinct()
				.collect(Collectors.toList());
			if (supportConvertTypes.isEmpty()) {
				return Optional.empty();
			}
			Class<?> finalType = supportConvertTypes.size() == 1 ? supportConvertTypes.get(0) : String.class;
			return Optional.of(Arrays.stream(str)
				.map(i -> this.conversionService.convert(i, finalType))
				.collect(Collectors.toList()));
		}
	}

	static class JsonStyleFactsFormatter implements Formatter<Facts> {
		@Override
		public @Nonnull Facts parse(@Nonnull String text, @Nonnull Locale locale) throws ParseException {
			try {
				Facts facts = new Facts();
				Map<String, ?> result = InitializationBeanConfiguration.objectMapper()
					.readValue(text, new TypeReference<>() {
					});
				result.entrySet().stream()
					.map(i -> new Fact<>(i.getKey(), i.getValue()))
					.forEach(facts::add);
				return facts;
			} catch (JsonProcessingException e) {
				throw new ParseException(text, e.getLocation().getLineNr());
			}
		}

		@Override
		public @Nonnull String print(Facts object, @Nonnull Locale locale) {
			try {
				return InitializationBeanConfiguration.objectMapper().writeValueAsString(object.asMap());
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
