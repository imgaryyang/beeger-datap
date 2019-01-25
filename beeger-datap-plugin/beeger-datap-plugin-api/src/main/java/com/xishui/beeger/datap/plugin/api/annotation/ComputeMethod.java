package com.xishui.beeger.datap.plugin.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xishui.hb
 * 混算的具体的方法，会通过model+method定位，进行反射调用
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ComputeMethod {

    String methodName() default "compute";

    String methodDescription() default "";

    String requestParams() default "";

    String responseValue() default "";
}
