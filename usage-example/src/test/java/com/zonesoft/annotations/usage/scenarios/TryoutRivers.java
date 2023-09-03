package com.zonesoft.annotations.usage.scenarios;

import java.util.Map.Entry;

import com.zonesoft.annotations.usage.models.Rivers;
import com.zonesoft.modelling.framework.ModelParameter;
import com.zonesoft.modelling.framework.Factory;

public class TryoutRivers {

	public static void main(String[] args) {
		Rivers rivers = Factory.createPageModel(Rivers.class);
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
