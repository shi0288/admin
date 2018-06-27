package com.xyauto.qa.core.annotation;

import com.xyauto.qa.cons.LogAction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Created by shiqm on 2017-05-17.
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface QaLog {
    String content() default "";
    String cmd() default "";
    LogAction action() default LogAction.NONE;
}
