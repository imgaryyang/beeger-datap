package com.xishui.beeger.datap.common.spi;

import java.util.Map;

public interface ResumeChecker<P, R> {

    R check(P param, Map<String, String> extendParams);
}
