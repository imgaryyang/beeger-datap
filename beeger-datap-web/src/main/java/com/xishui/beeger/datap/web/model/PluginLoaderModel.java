package com.xishui.beeger.datap.web.model;

import lombok.Data;

@Data
public class PluginLoaderModel {
    private String ip;
    private String pluginJar;

    public PluginLoaderModel() {
    }

    public PluginLoaderModel(String ip, String pluginJar) {
        this.ip = ip;
        this.pluginJar = pluginJar;
    }
}
