package com.xishui.beeger.datap.plugin.example;

import com.xishui.beeger.datap.plugin.api.starter.ComputerProviderStarter;
import com.xishui.beeger.datap.plugin.api.config.PluginConfig;

import java.util.Properties;

public class ExampleProviderStarter extends ComputerProviderStarter {


    @Override
    public String configFileName() {
        return null;
    }

    @Override
    public PluginConfig builderConfig(Properties properties) {
        return null;
    }
}
