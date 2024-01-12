package org.example.port.adapter.event;


/**
 * 持久性规则添加时事件
 */
public class RulePersistedEvent {

	private final Object source;

	public RulePersistedEvent(Object source) {
		this.source = source;
	}

	private Object getSource() {
		return source;
	}
}
