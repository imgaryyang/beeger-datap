package com.xishui.beeger.datap.plugin.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

public enum ConfigLoader {
    LOADER;
    private final Logger logger = LoggerFactory.getLogger(ConfigLoader.class);

    public Properties load(String fileName) {
        try {
            URL url = ConfigLoader.class.getClassLoader().getResource(fileName);
            if (null == url) {
                throw new NullPointerException("Can't Load Config by name " + fileName);
            }
            return loadProperties(url.getPath());
        } catch (Exception e) {
            logger.error("Config Load Err" + e);
        }
        return null;
    }

    private Properties loadProperties(String path) {
        try {
            File file = new File(path);
            Properties properties = new Properties();
            properties.load(new FileInputStream(file));
            return properties;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
