package com.zonesoft.modelling.framework;

import static com.zonesoft.utilities.Stringify.*;

public class ModelParameter{

	private PageElementType elementType;
	private SelectBy elementBy;
	private String elementHaving;
	private SelectBy promptBy;
	private String promptHaving;
	
	public static ModelParameter modelParameter(){
		return new ModelParameter();
	}		
	
	public PageElementType getElementType() {
		return this.elementType;
	}
	
	public void setElementType(PageElementType type) {
		this.elementType = type;
	}	
	
	public ModelParameter elementType(PageElementType type) {
		this.elementType = type;
		return this;
	}			
	
	public SelectBy getElementBy() {
		return this.elementBy;
	}
	
	public void setElementBy(SelectBy by) {
		this.elementBy = by;
	}
	
	public ModelParameter elementBy(SelectBy by){
		this.elementBy = by;
		return this;
	}
	
	public String getElementHaving() {
		return this.elementHaving;
	}

	public void setElementHaving(String value) {
		this.elementHaving = value;
	}	
	
	public ModelParameter elementHaving(String value){
		this.elementHaving = value;
		return this;
	}

	public SelectBy getPromptBy() {
		return this.promptBy;
	}

	public void setPromptBy(SelectBy by) {
		this.promptBy = by;
	}	
	
	public ModelParameter promptBy(SelectBy by){
		this.promptBy = by;
		return this;
	}
	
	public String getPromptHaving() {
		return this.promptHaving;
	}
	
	public void setPromptHaving(String value) {
		this.promptHaving = value;
	}
	
	public ModelParameter promptHaving(String value){
		this.promptHaving = value;
		return this;
	}	
		
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{elementType=");sb.append(stringify(this.elementType)); sb.append(", ");
		sb.append("elementBy=");sb.append(stringify(this.elementBy));sb.append(", ");
		sb.append("elementHaving=");sb.append(stringify(this.elementHaving));sb.append(", ");
		sb.append("promptBy=");sb.append(stringify(this.promptBy));sb.append(", ");
		sb.append("promptHaving=");sb.append(stringify(this.promptHaving));sb.append("}");
		return sb.toString();		
	}
	
}
