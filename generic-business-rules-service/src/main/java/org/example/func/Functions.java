package org.example.func;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * 通用函数枚举类
 */
public enum Functions {

	NON_DISJOINT {
		@Override
		public boolean execute(Collection<?> lhs, Object... rhs) {
			return !Collections.disjoint(lhs, Arrays.asList(rhs));
		}
	},
	;

	public boolean execute(Collection<?> lhs, Object... rhs) {
		return false;
	}
}
