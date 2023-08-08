package com.zonesoft.annotation.processor;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface BuilderProperty {
}
