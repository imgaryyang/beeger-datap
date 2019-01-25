package com.xishui.beeger.datap.plugin.api.config;

public class PluginConfig {
    private String serverUrl;
    private int exportPort;
    private String scanPackage;
    private String modelType;

    public static PluginConfig newConfig(){
        return new PluginConfig();
    }

    public PluginConfig serverUrl(String serverUrl){
        this.serverUrl = serverUrl;
        return this;
    }

    public PluginConfig exportPort(int exportPort){
        this.exportPort = exportPort;
        return this;
    }
    public PluginConfig scanPackage(String scanPackage){
        this.scanPackage = scanPackage;
        return this;
    }
    public PluginConfig modelType(String modelType){
        this.modelType= modelType;
        return this;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public int getExportPort() {
        return exportPort;
    }

    public void setExportPort(int exportPort) {
        this.exportPort = exportPort;
    }

    public String getScanPackage() {
        return scanPackage;
    }

    public void setScanPackage(String scanPackage) {
        this.scanPackage = scanPackage;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }
}
