package com.cloud.model;

import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * Created by hanzhang on 2017/12/13.
 */

public class ProxyMethod {

    /**
     * 类名
     */
    public TypeElement mClassElement;
    public String mMethodInfo;
    public String[] parameters;
    /**
     * 元素相关的辅助类
     */
    public Elements mElementUtils;
    public String methodName;

    public ProxyMethod(TypeElement classElement, Elements elementUtils) {
        this.mClassElement = classElement;
        this.mElementUtils = elementUtils;
    }

    public String getFullClassName() {
        return mClassElement.getQualifiedName().toString();
    }
}
