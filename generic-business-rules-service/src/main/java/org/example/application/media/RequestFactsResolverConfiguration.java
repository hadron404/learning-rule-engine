package org.example.application.media;

import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
class RequestFactsResolverConfiguration implements WebMvcConfigurer {
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new RequestFactsResolverConfiguration.ContextCommandHandlerMethodArgumentResolver());
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
}
