package com.zonesoft.annotations.usage;

import com.zonesoft.annotations.e2e_testing.PageModel;
import com.zonesoft.annotations.e2e_testing.PageModelElement;
import com.zonesoft.modelling.IPageModel;

@PageModel(pagePath = "/")
public interface Colours extends IPageModel {
	@PageModelElement public String BLUE();
	@PageModelElement public String YELLOW();
	@PageModelElement public String PURPLE();
}
