package com.zonesoft.modelling.framework;

import static com.zonesoft.utilities.Stringify.*;

public class ModelParameter{

	private PageElementType elementType;
	private SelectBy selectElementBy;
	private String selectElementWithValue;
	private SelectBy selectPromptBy;
	private String selectPromptWithValue;
	private String value;
	private String promptText;	
	
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
	
	public SelectBy getSelectElementBy() {
		return this.selectElementBy;
	}
	
	public void setSelectElementBy(SelectBy by) {
		this.selectElementBy = by;
	}
	
	public ModelParameter selectElementBy(SelectBy by){
		this.selectElementBy = by;
		return this;
	}
	
	public String getSelectElementWithValue() {
		return this.selectElementWithValue;
	}

	public void setSelectElementWithValue(String value) {
		this.selectElementWithValue = value;
	}	
	
	public ModelParameter selectElementWithValue(String value){
		this.selectElementWithValue = value;
		return this;
	}

	public SelectBy getSelectPromptBy() {
		return this.selectPromptBy;
	}

	public void setSelectPromptBy(SelectBy by) {
		this.selectPromptBy = by;
	}	
	
	public ModelParameter selectPromptBy(SelectBy by){
		this.selectPromptBy = by;
		return this;
	}
	
	public String getSelectPromptWithValue() {
		return this.selectPromptWithValue;
	}
	
	public void setSelectPromptWithValue(String value) {
		this.selectPromptWithValue = value;
	}
	
	public ModelParameter selectPromptWithValue(String value){
		this.selectPromptWithValue = value;
		return this;
	}	
	
	public String getValue() {
		return this.value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public ModelParameter value (String value){
		this.value = value;
		return this;
	}	
		
	public String getPromptText() {
		return this.promptText;
	}
	
	public void setPromptText(String value) {
		this.promptText = value;
	}
	
	public ModelParameter promptText(String value){
		this.promptText = value;
		return this;
	}	
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{elementType=");sb.append(stringify(this.elementType)); sb.append(", ");
		sb.append("selectElementBy=");sb.append(stringify(this.selectElementBy));sb.append(", ");
		sb.append("selectElementWithValue=");sb.append(stringify(this.selectElementWithValue));sb.append(", ");
		sb.append("selectPromptBy=");sb.append(stringify(this.selectPromptBy));sb.append(", ");
		sb.append("selectPromptWithValue=");sb.append(stringify(this.selectPromptWithValue));sb.append("}");
		return sb.toString();		
	}
	
}
