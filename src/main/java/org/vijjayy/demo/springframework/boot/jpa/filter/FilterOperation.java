package org.vijjayy.demo.springframework.boot.jpa.filter;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FilterOperation {

	EQUAL						("eq"),
	NOT_EQUAL					("neq"),
	GREATER_THAN				("gt"),
	GREATER_THAN_OR_EQUAL_TO	("gte"),
	LESS_THAN					("lt"),
	LESSTHAN_OR_EQUAL_TO		("lte"),
	IN							("in"),
	NOT_IN						("nin"),
	BETWEEN						("btn"),
	CONTAINS					("like");
	
	private String value;

	FilterOperation(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	public static FilterOperation fromValue(String value) {
		for (FilterOperation op : FilterOperation.values()) {
			if (String.valueOf(op.value).equals(value)) {
				return op;
			}
		}
		return null;
	}

}
