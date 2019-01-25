package com.xishui.beeger.datap.engine.kylin.driver;

import com.xishui.beeger.datap.engine.adaptor.DriverChecker;

import java.util.Map;

public class KylinDriverChecker implements DriverChecker<String> {

    @Override
    public void check(String param, Map<String, String> extendParams) throws Exception {
        Class.forName(param);
    }
}
