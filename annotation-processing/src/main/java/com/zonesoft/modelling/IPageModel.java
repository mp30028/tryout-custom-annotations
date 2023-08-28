package com.zonesoft.modelling;

import java.util.Map;

import com.zonesoft.modelling.framework.ModelParameter;

public interface IPageModel {
	
	String getPagePath();
	Map<String, ModelParameter> parameters();

}