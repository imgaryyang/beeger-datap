package com.xishui.beeger.datap.plugin.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ComputeModel {
    /**
     * 计算模块的名字,必须英文，会以此来做路由处理
     **/
    String modelName() ;

    String modelCnName();
    /**
     * 计算模块的描述
     **/
    String modelDescription() default "";

}
