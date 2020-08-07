package com.gu.log.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author FastG
 * @date 2020/8/3 14:00
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    /**
     *
     */
    String value() default "";

    /**
     * 是否启用
     */
    boolean enable() default true;
}
