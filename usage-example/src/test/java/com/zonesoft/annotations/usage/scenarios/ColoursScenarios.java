package com.zonesoft.annotations.usage.scenarios;

import org.junit.jupiter.api.Test;

import com.zonesoft.modelling.framework.IExpectation ;
import com.zonesoft.annotations.usage.models.Colours;
//import com.zonesoft.annotations.usage.models.expectations.ExpectedColours;
import com.zonesoft.modelling.framework.Factory;

class ColoursScenarios {

	@Test
	void scenarioOne() {
		Colours colours = Factory.createPageModel(Colours.class);
		IExpectation<Colours> expectedColours = Factory.createExpectation(Colours.class, colours);
		expectedColours.compareWith(colours);
		String elementOneValue = "This is the value of ELEMENT-1";
		expectedColours.expected(colours.BLUE(), elementOneValue);
//		expectedColours.compareWith(colours);
		
//		String elementOne = "ELEMENT-1";

//		
//		String elementTwo = "ELEMENT-2";
//		String elementTwoValue = "This is the value of ELEMENT-2";
//		
//		expectedColours
//			.expected(elementOne, elementOneValue)
//			.expected(elementTwo, elementTwoValue);
//		
//
//		assertEquals(expectedColours.getExpected(elementOne), elementOneValue); 		
//		assertTrue(expectedColours.equals(elementOne, elementOneValue));
//		
//		assertEquals(expectedColours.getExpected(elementTwo), elementTwoValue); 		
//		assertTrue(expectedColours.equals(elementTwo, elementTwoValue));

	}

}
