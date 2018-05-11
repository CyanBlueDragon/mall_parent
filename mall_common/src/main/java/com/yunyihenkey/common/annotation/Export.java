package com.yunyihenkey.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)//标注用来注解field类型
@Retention(RetentionPolicy.RUNTIME)//标注运行时注解
public @interface Export {

    /** 序号 */
    int index();
    /** 标题 */
    String title();
}
