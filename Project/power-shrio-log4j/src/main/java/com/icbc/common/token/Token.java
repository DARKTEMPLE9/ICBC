package com.icbc.common.token;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {
    /*
    * 新增token
    * */
    boolean save() default false;

    /*
    * 移出token
    * */
    boolean remove() default false;
}
