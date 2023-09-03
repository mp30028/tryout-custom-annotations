package com.zonesoft.modelling.framework;

public interface IExpectation<T extends IPageModel> {
	
	IExpectation<?> expected(String elementName, String expectedValue);

	String getExpected(String elementName);
	
	void setExpected(String elementName, String expectedValue);

	boolean equals(String elementName, String valueToCompareAgainst);
	
	T compareWith(T model);

}