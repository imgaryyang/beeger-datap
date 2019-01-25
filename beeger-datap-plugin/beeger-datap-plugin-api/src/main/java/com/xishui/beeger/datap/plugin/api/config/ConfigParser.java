package com.xishui.beeger.datap.plugin.api.config;

import java.util.Properties;

public enum ConfigParser {
    PARSER;

    public <T> T parse(Properties properties, Class<T> clazz) throws Exception {
        throw new UnsupportedOperationException("Not Support Parse Property to Object.");
    }

    public <T> T parse(String fileName, Class<T> clazz) throws Exception {
        Properties properties = ConfigLoader.LOADER.load(fileName);
        return parse(properties, clazz);
    }
}
