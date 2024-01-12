package org.example.port.adapter.event;

import org.springframework.context.ApplicationEvent;

/**
 * 临时性规则添加时事件
 * */
public class RuleCachingEvent<T> extends ApplicationEvent {

	public RuleCachingEvent(T source) {
		super(source);
	}
}
