package com.xishui.beeger.datap.model;

public enum DriverClass {
    KYLIN("org.apache.kylin.jdbc.Driver"),
    MYSQL("com.mysql.jdbc.Driver");

    private String driver;

    DriverClass(String driver) {
        this.driver = driver;
    }

    public String getDriver() {
        return driver;
    }
}
