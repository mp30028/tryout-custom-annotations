package com.zonesoft.annotations.usage.models;

import com.zonesoft.annotations.e2e_testing.PageModel;
import com.zonesoft.annotations.e2e_testing.PageModelElement;
import com.zonesoft.modelling.IPageModel;
import com.zonesoft.modelling.framework.PageElementType;
import com.zonesoft.modelling.framework.SelectBy;

@PageModel(pagePath = "/")
public interface Colours extends IPageModel {

	@PageModelElement(
			elementType = PageElementType.HEADING, 
			elementBy = SelectBy.ID, elementHaving = "blue-heading"
	) 
	public String BLUE();
	
	@PageModelElement(	
			elementType = PageElementType.INPUT_BOX, 
			elementBy = SelectBy.XPATH, elementHaving = "green-input-box",
			promptBy = SelectBy.ID, promptHaving = "prompt-for-green"
	) 	
	public String GREEN(); 	
	
	@PageModelElement(	
			elementType = PageElementType.INPUT_BOX, 
			elementBy = SelectBy.XPATH, elementHaving = "yellow-input-box",
			promptBy = SelectBy.ID, promptHaving = "prompt-for-yellow"
	) 	
	public String YELLOW(); 

}
