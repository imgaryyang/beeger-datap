package com.xishui.beeger.datap.plugin.example;

import com.xishui.beeger.datap.plugin.api.connection.ConnectionHolder;
import com.xishui.beeger.datap.plugin.api.connection.MysqlConnection;
import com.xishui.beeger.datap.plugin.api.starter.ComputerProviderStarter;
import com.xishui.beeger.datap.plugin.api.config.PluginConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ExampleProviderStarter extends ComputerProviderStarter {
    private final Logger logger = LoggerFactory.getLogger(ExampleProviderStarter.class);

    @Override
    public String configFileName() {
        return "example-config.properties";
    }

    @Override
    public PluginConfig builderConfig(Properties properties) {
        try {
            ExampleConfig config = ExampleConfig.parse(properties);
            PluginConfig pluginConfig = new PluginConfig();
            pluginConfig.setServerUrl(config.getServerUrl());
            pluginConfig.setScanPackage("com.xishui.beeger.datap.plugin.example");
            pluginConfig.setExportPort(config.getExportPort());
            pluginConfig.setModelType(config.getRemoteType());

            MysqlConnection mysqlConnection = new MysqlConnection();
            mysqlConnection.setServerUrl(config.getServerUrl());
            mysqlConnection.setUrl(config.getMysqlUrl());
            mysqlConnection.setUserName(config.getMysqlUserName());
            mysqlConnection.setPassword(config.getMysqlPassword());
            ConnectionHolder.HOLDER.cacheConnection(Keys.NORMAL_MYSQL_CONNECTION_KEY, mysqlConnection);
            return pluginConfig;
        } catch (Exception e) {
            logger.error("example Start Err", e);
            return null;
        }
    }
}
