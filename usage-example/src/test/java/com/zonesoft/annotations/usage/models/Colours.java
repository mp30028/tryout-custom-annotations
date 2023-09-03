package com.zonesoft.annotations.usage.models;

import com.zonesoft.annotations.e2e_testing.PageModel;
import com.zonesoft.annotations.e2e_testing.PageModelElement;
import com.zonesoft.modelling.framework.IPageModel;
import com.zonesoft.modelling.framework.PageElementType;
import com.zonesoft.modelling.framework.SelectBy;

@PageModel(pagePath = "/")
public interface Colours extends IPageModel {

	@PageModelElement(
			elementType = PageElementType.HEADING, 
			selectElementBy = SelectBy.ID, selectElementWithValue = "blue-heading",
			promptText = "PROMPT-FOR-BLUE"
	) 
	public String BLUE();
	
	@PageModelElement(	
			elementType = PageElementType.INPUT_BOX, 
			selectElementBy = SelectBy.XPATH, selectElementWithValue = "green-input-box",
			selectPromptBy = SelectBy.ID, selectPromptWithValue = "prompt-for-green",
			defaultValue = "", promptText = "PROMPT-FOR-GREEN"
	) 	
	public String GREEN(); 	
	
	@PageModelElement(	
			elementType = PageElementType.INPUT_BOX, 
			selectElementBy = SelectBy.XPATH, selectElementWithValue = "yellow-input-box",
			selectPromptBy = SelectBy.ID, selectPromptWithValue = "prompt-for-yellow",
			defaultValue = "", promptText = "PROMPT-FOR-YELLOW"
	) 	
	public String YELLOW(); 

}
