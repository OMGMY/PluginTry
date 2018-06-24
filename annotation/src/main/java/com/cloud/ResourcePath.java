package com.cloud;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hanzhang on 2017/12/4.
 * 给出插件包的地址
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface ResourcePath {
    String value();
}
