package com.zonesoft.annotations.usage.expectations;

import java.util.LinkedHashMap;
import java.util.Map;


public class PROTOTYPE_ExpectedColours{
	private Map<String, String> expectedValues = new LinkedHashMap<>();
		
	public PROTOTYPE_ExpectedColours setExpectedValue(String elementName, String expectedValue) {
		expectedValues.put(elementName, expectedValue);
		return this;
	}

	public String getExpectedValue(String elementName) {
		return expectedValues.get(elementName);		
	}

	public boolean equals(String elementName, String valueToCompareAgainst) {
		return expectedValues.get(elementName).contentEquals(valueToCompareAgainst);		
	}
	
}
