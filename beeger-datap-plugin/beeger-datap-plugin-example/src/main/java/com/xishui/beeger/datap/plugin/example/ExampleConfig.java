package com.xishui.beeger.datap.plugin.example;

import lombok.Data;

import java.util.Properties;

@Data
public class ExampleConfig {
    private String mysqlUrl;
    private String mysqlUserName;
    private String mysqlPassword;
    private String remoteType;
    private String serverUrl;
    private int exportPort;

    public static ExampleConfig parse(Properties properties) throws Exception{
        if(null ==  properties){
            throw new NullPointerException("Properties is null");
        }
        ExampleConfig config = new ExampleConfig();
        if(properties.containsKey("example.db.url")){
            config.setMysqlUrl(properties.getProperty("example.db.url"));
        }
        if(properties.containsKey("example.db.userName")){
            config.setMysqlUserName(properties.getProperty("example.db.userName"));
        }
        if(properties.containsKey("example.db.password")){
            config.setMysqlPassword(properties.getProperty("example.db.password"));
        }
        if(properties.containsKey("remote.type")){
            config.setRemoteType(properties.getProperty("remote.type"));
        }
        if(properties.containsKey("compute.server.url")){
            config.setServerUrl(properties.getProperty("compute.server.url"));
        }
        if(properties.containsKey("compute.export.port")){
            config.setExportPort(Integer.valueOf(properties.getProperty("compute.export.port")));
        }
        return config;
    }
}
