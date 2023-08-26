package com.zonesoft.annotations.usage;

import com.zonesoft.annotations.e2e_testing.PageModel;
import com.zonesoft.annotations.e2e_testing.PageModelElement;

@PageModel(pagePath = "/rivers")
public class Rivers extends RiversExtender{
	
	@PageModelElement public static String ZAMBEZI;
	@PageModelElement public static String NILE;
	@PageModelElement public static String CONGO;
	public static String NIGER = "";
	

}
