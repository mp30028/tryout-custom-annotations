package com.zonesoft.annotations.usage.models;

import com.zonesoft.annotations.e2e_testing.PageModel;
import com.zonesoft.annotations.e2e_testing.PageModelElement;
import com.zonesoft.modelling.IPageModel;
import com.zonesoft.modelling.framework.PageElementType;
import com.zonesoft.modelling.framework.SelectBy;

@PageModel(pagePath = "/rivers")
public interface Rivers extends IPageModel {
	
	@PageModelElement(	
			elementType = PageElementType.INPUT_BOX, 
			elementBy = SelectBy.XPATH, elementHaving = "zambezi-input-box",
			promptBy = SelectBy.ID, promptHaving = "prompt-for-zambezi"
	)  
	public String ZAMBEZI();
		
	@PageModelElement(	
			elementType = PageElementType.INPUT_BOX, 
			elementBy = SelectBy.XPATH, elementHaving = "nile-input-box",
			promptBy = SelectBy.ID, promptHaving = "prompt-for-nile"
	)   
	public String NILE();
		
	@PageModelElement(	
			elementType = PageElementType.INPUT_BOX, 
			elementBy = SelectBy.XPATH, elementHaving = "congo-input-box",
			promptBy = SelectBy.ID, promptHaving = "prompt-for-congo"
	)    
	public String CONGO();
	
	public static String NIGER = "NIGER";	
}
