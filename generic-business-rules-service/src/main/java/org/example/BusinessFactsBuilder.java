package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BusinessFactsBuilder {

	/**
	 * 想要获取的事实数据项
	 */
	private final Set<String> factItems;

	/**
	 * 获取事实数据的必要参数
	 */
	private final Map<String, Object> parameters = new HashMap<>();

	/**
	 * 获取事实数据加载器
	 */
	private final Map<String, Function<Map<String, Object>, String>> factDataLoaders = new HashMap<>();

	public BusinessFactsBuilder(Set<String> factItems,
		Map<String, Object> parameters,
		Map<String, Function<Map<String, Object>, String>> factDataLoaders) {
		this.factItems = factItems;
		this.parameters.putAll(parameters);
		this.factDataLoaders.putAll(factDataLoaders);
	}

	public static BusinessFactsBuilder newInstance(Set<String> factItems) {
		return new BusinessFactsBuilder(factItems, Map.of(), Map.of());
	}

	public BusinessFactsBuilder parameters(Map<String, Object> parameters) {
		this.parameters.putAll(parameters);
		return this;
	}

	public BusinessFactsBuilder customizeLoaders(Map<String, Function<Map<String, Object>, String>> factDataLoaders) {
		this.factDataLoaders.putAll(factDataLoaders);
		return this;
	}

	public Map<String, Object> load() {
		return factItems.stream()
			.filter(i -> factDataLoaders.get(i) != null)
			.collect(Collectors.toMap(
				i -> i,
				i -> factDataLoaders.get(i).apply(parameters)
			));
	}
}
