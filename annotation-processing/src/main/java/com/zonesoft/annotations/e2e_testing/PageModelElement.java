package com.zonesoft.annotations.e2e_testing;

import java.lang.annotation.*;

//@Target(ElementType.FIELD)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface PageModelElement {
}
