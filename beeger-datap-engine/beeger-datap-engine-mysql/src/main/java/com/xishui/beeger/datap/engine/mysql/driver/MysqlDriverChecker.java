package com.xishui.beeger.datap.engine.mysql.driver;

import com.xishui.beeger.datap.engine.adaptor.DriverChecker;

import java.util.Map;

public class MysqlDriverChecker implements DriverChecker<String> {
    @Override
    public void check(String param, Map<String, String> extendParams) throws Exception {
        Class.forName(param);
    }
}
