package com.xishui.beeger.datap.common.spi;

import java.util.Map;

public interface Checker<T> {

    void check(T param,Map<String,String> extendParams) throws Exception;
}
