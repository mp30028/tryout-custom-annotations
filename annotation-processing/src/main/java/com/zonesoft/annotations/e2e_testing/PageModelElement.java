package com.zonesoft.annotations.e2e_testing;

import java.lang.annotation.*;

import com.zonesoft.modelling.framework.PageElementType;
import com.zonesoft.modelling.framework.SelectBy;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface PageModelElement {
	PageElementType elementType();
	SelectBy selectElementBy();
	String selectElementWithValue();
	SelectBy selectPromptBy() default SelectBy.NONE;
	String selectPromptWithValue() default "";
	String defaultValue() default "";
	String promptText() default "";	
}
