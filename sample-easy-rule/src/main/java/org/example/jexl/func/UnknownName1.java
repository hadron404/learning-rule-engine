package org.example.jexl.func;

import java.util.Arrays;
import java.util.Collection;

/**
 * 两个集合之间是否含有相同元素
 */
public class UnknownName1<T> {

	// public boolean exec(Collection<T> lhs, Collection<T> rhs) {
	// 	System.out.println(lhs);
	// 	System.out.println(rhs);
	// 	return true;
	// }
	public boolean exec(Collection<T> lhs, T... rhs) {
		return lhs.stream().anyMatch(l -> Arrays.asList(rhs).contains(l));
	}
}
