package org.example.jexl.func;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * 通用函数枚举类
 * <p>
 * 定义符合JEXL的自定义函数规则
 */
public enum Functions {

	INTERSECTION {
		@Override
		public boolean execute(Collection<?> lhs, Object... rhs) {
			return !Collections.disjoint(lhs, Arrays.asList(rhs));
		}

		@Override
		public String key() {
			return "fn";
		}
	},
	;

	@SuppressWarnings("unused")
	public boolean execute(Collection<?> lhs, Object... rhs) {
		return false;
	}

	public String key() {
		return null;
	}
}
