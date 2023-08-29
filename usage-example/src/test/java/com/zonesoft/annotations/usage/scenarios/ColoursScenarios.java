package com.zonesoft.annotations.usage.scenarios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.zonesoft.annotations.usage.expectations.PROTOTYPE_ExpectedColours;

class ColoursScenarios {

	@Test
	void scenarioOne() {
		PROTOTYPE_ExpectedColours expectedColours = new PROTOTYPE_ExpectedColours();
		String elementOne = "ELEMENT-1";
		String elementOneValue = "This is the value of ELEMENT-1";
		
		String elementTwo = "ELEMENT-2";
		String elementTwoValue = "This is the value of ELEMENT-2";
		
		expectedColours
			.setExpectedValue(elementOne, elementOneValue)
			.setExpectedValue(elementTwo, elementTwoValue);
		

		assertEquals(expectedColours.getExpectedValue(elementOne), elementOneValue); 		
		assertTrue(expectedColours.equals(elementOne, elementOneValue));
		
		assertEquals(expectedColours.getExpectedValue(elementTwo), elementTwoValue); 		
		assertTrue(expectedColours.equals(elementTwo, elementTwoValue));

	}

}
