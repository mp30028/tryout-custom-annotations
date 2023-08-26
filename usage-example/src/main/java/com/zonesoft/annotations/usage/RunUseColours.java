package com.zonesoft.annotations.usage;

import com.zonesoft.annotations.e2e_testing.PageModelFactory;

public class RunUseColours {

	public static void main(String[] args) {
		Colours colours = PageModelFactory.createInstance(Colours.class);
		System.out.println("BLUE=" + colours.BLUE());
		System.out.println("PURPLE=" + colours.PURPLE());
		System.out.println("YELLOW=" + colours.YELLOW());
	}

}
