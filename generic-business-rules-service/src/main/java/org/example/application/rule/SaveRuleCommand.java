package org.example.application.rule;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.example.common.func.Functions;
import org.example.domain.model.rule.BusinessRule;
import org.example.domain.model.rule.BusinessRuleId;
import org.example.infrastructure.InitializationBeanConfiguration;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public record SaveRuleCommand(Map<String, String> param) {


	public BusinessRule toBusinessRule() {
		// BusinessRule 在构造时,应考虑参数合法性: 条件里用了自定义函数,但是却没有提供已有的函数,条件没用函数,应提供空函数使用
		BusinessRule rule = new BusinessRule();
		rule.setName(new BusinessRuleId(param.get("name")));
		rule.setDescription(param.get("desc"));
		rule.setCondition(param.get("condition"));
		String func = param.get("functions");
		if(StringUtils.hasText(func)){
			return rule;
		}
        try {
			rule.setFunctions(InitializationBeanConfiguration
				.objectMapper()
				.readValue(func, new TypeReference<>() {
				}));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
		return rule;
	}

}
