package com.zonesoft.annotations.usage.scenarios;

import java.util.Map.Entry;

import com.zonesoft.annotations.e2e_testing.PageModelFactory;
import com.zonesoft.annotations.usage.models.Rivers;
import com.zonesoft.modelling.framework.ModelParameter;

public class TryoutRivers {

	public static void main(String[] args) {
		Rivers rivers = PageModelFactory.createPageModel(Rivers.class);
		System.out.println("CONGO=" + rivers.CONGO());
		System.out.println("NIGER=" + Rivers.NIGER);
		System.out.println("NILE=" + rivers.NILE());
		System.out.println("ZAMBEZI=" + rivers.ZAMBEZI());
		System.out.println("Page-Path=" + rivers.getPagePath());
		for (Entry<String, ModelParameter> entry: rivers.parameters().entrySet()) {
			System.out.println("parameter(" + entry.getKey()  + ") = " + entry.getValue().toString());
		}			
		
	}

}
