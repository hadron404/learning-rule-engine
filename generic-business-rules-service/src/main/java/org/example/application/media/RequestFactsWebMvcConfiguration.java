package org.example.application.media;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import org.example.infrastructure.InitializationBeanConfiguration;
import org.jeasy.rules.api.Fact;
import org.jeasy.rules.api.Facts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Configuration
class RequestFactsWebMvcConfiguration implements WebMvcConfigurer {

	@Autowired
	private ConversionService conversionService;


	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(new JsonStyleFactsFormatter());
		WebMvcConfigurer.super.addFormatters(registry);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new RequestFactsMethodArgumentResolver(conversionService));
		WebMvcConfigurer.super.addArgumentResolvers(resolvers);
	}

	static class RequestFactsMethodArgumentResolver implements HandlerMethodArgumentResolver {

		private final ConversionService conversionService;

		public RequestFactsMethodArgumentResolver(ConversionService conversionService) {
			this.conversionService = conversionService;
		}

		@Override
		public boolean supportsParameter(MethodParameter parameter) {
			return parameter.hasParameterAnnotation(RequestFacts.class);
		}

		@Override
		public Object resolveArgument(@Nonnull MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
			HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
			assert request != null;
			Facts facts = new Facts();
			request.getParameterMap()
				.forEach((k, v) -> {
					if (v.length == 1) {
						conversionService.canConvert(String.class, Integer.class);
						conversionService.canConvert(String.class, Long.class);
						conversionService.canConvert(String.class, Double.class);
						facts.add(new Fact<>(k, v[0]));
					}
					conversionService.canConvert(String.class, List.class);
				});

			return new RequestQueryStyleFactsFormatter().parse(request.getQueryString(), Locale.CHINA);
		}
	}

	static class RequestQueryStyleFactsFormatter implements Formatter<Facts> {
		@Override
		public @Nonnull Facts parse(@Nonnull String text, @Nonnull Locale locale) throws ParseException {
			Facts result = new Facts();


			return result;
		}

		@Override
		public @Nonnull String print(Facts object, @Nonnull Locale locale) {
			return null;
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
