package com.zonesoft.annotation_processing_samples;

import com.zonesoft.annotations.e2e_testing.PageModel;
import com.zonesoft.annotations.e2e_testing.PageModelElement;

@PageModel(pagePath = "/")

public class ColoursLocal {
	
	@PageModelElement public static String GREEN;
	@PageModelElement public static String BLUE;
	@PageModelElement public static String YELLOW;
	public static String ORANGE;


}
