package com.zonesoft.modelling.framework;

import static com.zonesoft.utilities.Stringify.*;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPageModel implements IPageModel {
	
	private final Map<String, ModelParameter> parameters = new HashMap<>();
	protected final String pagePath;
	
	public AbstractPageModel(String pagePath) {	
		this.pagePath = pagePath;
	}
	
	@Override
	public Map<String, ModelParameter> parameters(){
		return this.parameters;
	}
	
	@Override
	public String getPagePath() {
		return pagePath;
	}	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("pagePath=");sb.append(stringify(this.pagePath));
		sb.append("}");
		return sb.toString();				
	}	
}
