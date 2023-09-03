package com.zonesoft.annotations.e2e_testing;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface PageModel {
	   String pagePath() default "";	    
}
