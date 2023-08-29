package com.zonesoft.annotations.usage.scenarios;

import java.util.Map.Entry;

import com.zonesoft.annotations.usage.models.Colours;
import com.zonesoft.modelling.framework.ModelParameter;
import com.zonesoft.modelling.framework.PageModelFactory;

public class TryoutColours {

	public static void main(String[] args) {
		Colours colours = PageModelFactory.createPageModel(Colours.class);
		System.out.println("BLUE=" + colours.BLUE());
		System.out.println("GREEN=" + colours.GREEN());
		System.out.println("YELLOW=" + colours.YELLOW());
		System.out.println("Page-Path=" + colours.getPagePath());
		for (Entry<String, ModelParameter> entry: colours.parameters().entrySet()) {
			System.out.println("parameter(" + entry.getKey()  + ") = " + entry.getValue().toString());
		}		
	}

}
