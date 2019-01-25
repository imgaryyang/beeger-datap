package com.xishui.beeger.datap.plugin.test;

import com.xishui.beeger.datap.plugin.api.starter.ComputeStarter;
import com.xishui.beeger.datap.plugin.api.config.PluginConfig;

public class ServerMain {

    public static void main(String... args){
        PluginConfig pluginConfig = new PluginConfig();
        pluginConfig.setExportPort(2010);
        pluginConfig.setServerUrl("http://localhost:2010");
        pluginConfig.setScanPackage("com.xishui.beeger.datap.plugin");
        ComputeStarter.COMPUTE.starter(pluginConfig);
    }
}
