package com.zonesoft.modelling.framework;

import java.util.Map;

public interface IPageModel {
	
	String getPagePath();
	Map<String, ModelParameter> parameters();

}