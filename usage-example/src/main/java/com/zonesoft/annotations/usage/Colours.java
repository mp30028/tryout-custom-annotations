package com.zonesoft.annotations.usage;

import com.zonesoft.annotations.e2e_testing.PageModel;
import com.zonesoft.annotations.e2e_testing.PageModelElement;

@PageModel(pagePath = "/")
public class Colours extends ColoursExtender {
	
	@PageModelElement public static String BLUE;
	@PageModelElement public static String YELLOW;
	 public static String ORANGE = "orange";
	 public static String RED;
	@PageModelElement public static String PURPLE;
	 
	public Colours() {
		super();
	}


}
