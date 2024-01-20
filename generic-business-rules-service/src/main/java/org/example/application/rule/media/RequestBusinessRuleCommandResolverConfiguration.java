package org.example.application.rule.media;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import org.example.common.func.Functions;
import org.example.domain.model.rule.BusinessRule;
import org.example.domain.model.rule.BusinessRuleId;
import org.example.infrastructure.InitializationBeanConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;

@Configuration
class RequestBusinessRuleCommandResolverConfiguration implements WebMvcConfigurer {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new BusinessRuleCommandHandlerMethodArgumentResolver());
		WebMvcConfigurer.super.addArgumentResolvers(resolvers);
	}

	static class BusinessRuleCommandHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

		@Override
		public boolean supportsParameter(MethodParameter parameter) {
			return parameter.hasParameterAnnotation(RequestBusinessRuleCommand.class);
		}

		@Override
		public Object resolveArgument(@Nonnull MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
			HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
			assert request != null;
			String jsonStrFromBody = StreamUtils.copyToString(request.getInputStream(), Charset.defaultCharset());
			return toBusinessRule(jsonStrFromBody);
		}

		public BusinessRule toBusinessRule(String str) throws IOException {
			// BusinessRule 在构造时,应考虑参数合法性: 条件里用了自定义函数,但是却没有提供已有的函数,条件没用函数,应提供空函数使用
			BusinessRule rule = new BusinessRule();
			JsonNode jsonNode = InitializationBeanConfiguration.objectMapper().readTree(str);
			rule.setName(new BusinessRuleId(jsonNode.get("name").asText()));
			rule.setDescription(jsonNode.get("desc").asText());
			rule.setCondition(jsonNode.get("condition").asText());
			JsonNode funcNode = jsonNode.get("functions");
			if (funcNode.isEmpty()) {
				return rule;
			}
			Set<Functions> func = InitializationBeanConfiguration.objectMapper()
				.readValue(funcNode.traverse(), new TypeReference<>() {
				});
			rule.setFunctions(func);
			return rule;
		}
	}
}
