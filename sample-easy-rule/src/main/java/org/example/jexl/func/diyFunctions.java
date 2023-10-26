package org.example.jexl.func;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


public class diyFunctions {

	/**
	 * 判断两个集合是否有相同元素
	 */
	public boolean nonDisjoint(Collection<?> lhs, Object... rhs) {
		return !Collections.disjoint(lhs, Arrays.asList(rhs));
		// return lhs.stream().anyMatch(l -> Arrays.asList(rhs).contains(l));
	}
}
