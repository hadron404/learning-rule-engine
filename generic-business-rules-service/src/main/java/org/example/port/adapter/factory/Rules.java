package org.example.port.adapter.factory;


import org.example.common.func.Functions;

import java.util.Set;

public enum Rules {
	RULE_1 {
		public String warning() {
			return "当前客户为旅游黑名单客户，不可下单旅游产品，如有疑问请联系旅游部！";
		}

		public String description() {
			return "旅游黑名单规则";
		}

		public String condition() {
			return "fn:execute(lhs,'a','b','c')";
		}
		public Set<Functions> functions(){
			return Set.of(Functions.INTERSECTION);
		}

		public int priority() {
			return 1;
		}
	};

	public String warning() {
		return "";
	}

	public String description() {
		return "";
	}

	public String condition() {
		return "";
	}

	public Set<Functions> functions(){
		return Set.of();
	}

	public int priority() {
		return -1;
	}
}
