package com.zonesoft.modelling.framework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;


public class AbstractExpectation<T extends IPageModel> implements IExpectation<T>{
	
	private Map<String, String> expectedValues = new LinkedHashMap<>();
	
	private final T model;
	
	public AbstractExpectation (T model){
		this.model = model;
		this.initialise();
	}

	private void initialise() {
		for(Entry<String, ModelParameter> entry: model.parameters().entrySet()) {
			setExpected(entry.getKey(), entry.getValue().getValue());
		}
	}
	
	@Override
	public IExpectation<T> expected(String elementName, String expectedValue) {
		expectedValues.put(elementName, expectedValue);
		return this;
	}

	@Override
	public String getExpected(String elementName) {
		return expectedValues.get(elementName);		
	}
	
	@Override
	public void setExpected(String elementName, String expectedValue) {
		expectedValues.put(elementName, expectedValue);		
	}
	
	@Override
	public boolean equals(String elementName, String valueToCompareAgainst) {
		return expectedValues.get(elementName).contentEquals(valueToCompareAgainst);		
	}
	
	@Override
	public T compareWith(final T colours) {
		assertEquals(this.model.getPagePath(), colours.getPagePath());
		for(Entry<String, ModelParameter> entry: colours.parameters().entrySet()) {
			String key = entry.getKey();
			ModelParameter parameter = entry.getValue();
			String value = parameter.getValue();
			String prompt = parameter.getPromptText();
			String expectedValue = this.expectedValues.get(key);
			String expectedPrompt = this.model.parameters().get(key).getPromptText();
			assertEquals(expectedValue,value);
			assertEquals(expectedPrompt,prompt);
		}
		return colours;
	}

}
