package com.zonesoft.modelling;

import static com.zonesoft.utilities.Stringify.*;

public abstract class AbstractPageModel implements IPageModel {
	
	protected final String pagePath;
	
	public AbstractPageModel(String pagePath) {	
		this.pagePath = pagePath;
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
