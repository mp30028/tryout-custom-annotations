package com.zonesoft.annotations.usage;

import com.zonesoft.annotations.e2e_testing.PageModel;
import com.zonesoft.annotations.e2e_testing.PageModelElement;
import com.zonesoft.modelling.IPageModel;

@PageModel(pagePath = "/rivers")
public interface Rivers extends IPageModel {
	
	@PageModelElement public String ZAMBEZI();
	@PageModelElement public String NILE();
	@PageModelElement public String CONGO();
	public static String NIGER = "NIGER";
	

}
