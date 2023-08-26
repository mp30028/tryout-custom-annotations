package com.zonesoft.annotations.usage;

import com.zonesoft.annotations.e2e_testing.PageModel;
import com.zonesoft.annotations.e2e_testing.PageModelElement;

@PageModel(pagePath = "/")
public interface Colours {
	@PageModelElement public String BLUE();
	@PageModelElement public String YELLOW();
	@PageModelElement public String PURPLE();
}
