package org.example.port.adapter.event;

/**
 * 临时性规则添加时事件
 */
public record RuleCachingEvent<T>(T source) {
}
