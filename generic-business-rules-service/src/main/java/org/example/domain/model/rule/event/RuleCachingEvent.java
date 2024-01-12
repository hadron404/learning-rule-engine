package org.example.domain.model.rule.event;

/**
 * 临时性规则添加时事件
 */
public record RuleCachingEvent<T>(T source) {
}
