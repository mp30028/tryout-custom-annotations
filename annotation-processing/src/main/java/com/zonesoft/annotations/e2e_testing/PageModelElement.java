package com.zonesoft.annotations.e2e_testing;

import java.lang.annotation.*;

import com.zonesoft.modelling.framework.PageElementType;
import com.zonesoft.modelling.framework.SelectBy;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface PageModelElement {
	PageElementType elementType();
	SelectBy elementBy();
	String elementHaving();
	SelectBy promptBy() default SelectBy.NONE;
	String promptHaving() default "";	
}
