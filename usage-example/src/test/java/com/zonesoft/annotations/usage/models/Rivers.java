package com.zonesoft.annotations.usage.models;

import com.zonesoft.annotations.e2e_testing.PageModel;
import com.zonesoft.annotations.e2e_testing.PageModelElement;
import com.zonesoft.modelling.framework.IPageModel;
import com.zonesoft.modelling.framework.PageElementType;
import com.zonesoft.modelling.framework.SelectBy;

@PageModel(pagePath = "/rivers")
public interface Rivers extends IPageModel {
	
	@PageModelElement(	
			elementType = PageElementType.INPUT_BOX, 
			selectElementBy = SelectBy.XPATH, selectElementWithValue = "zambezi-input-box",
			selectPromptBy = SelectBy.ID, selectPromptWithValue = "prompt-for-zambezi"
	)  
	public String ZAMBEZI();
		
	@PageModelElement(	
			elementType = PageElementType.INPUT_BOX, 
			selectElementBy = SelectBy.XPATH, selectElementWithValue = "nile-input-box",
			selectPromptBy = SelectBy.ID, selectPromptWithValue = "prompt-for-nile"
	)   
	public String NILE();
		
	@PageModelElement(	
			elementType = PageElementType.INPUT_BOX, 
			selectElementBy = SelectBy.XPATH, selectElementWithValue = "congo-input-box",
			selectPromptBy = SelectBy.ID, selectPromptWithValue = "prompt-for-congo"
	)    
	public String CONGO();
	
	public static String NIGER = "NIGER";	
}
