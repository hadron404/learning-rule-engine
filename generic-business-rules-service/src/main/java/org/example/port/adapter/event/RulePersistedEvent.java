package org.example.port.adapter.event;

import org.springframework.context.ApplicationEvent;


/**
 * 持久性规则添加时事件
 */
public class RulePersistedEvent extends ApplicationEvent {
	public RulePersistedEvent(Object source) {
		super(source);
	}
}
