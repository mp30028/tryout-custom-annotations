package com.zonesoft.annotations.utilities;

import java.util.Objects;

public class Stringify {

	public static <T> String stringify(T itemToStringify){
		return stringify(itemToStringify, "--null--");
	}	
	
	public static <T> String stringify(T itemToStringify, String valueIfNull){
		if(Objects.isNull(itemToStringify)) {
			return valueIfNull;
		}else {
			return itemToStringify.toString();
		}
	}	
	
}