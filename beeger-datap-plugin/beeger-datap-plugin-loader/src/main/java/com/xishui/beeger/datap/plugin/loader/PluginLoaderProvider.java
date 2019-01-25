package com.xishui.beeger.datap.plugin.loader;

import com.xishui.beeger.datap.plugin.loader.processor.DefaultPluginLoader;

import java.util.List;

public class PluginLoaderProvider implements PluginLoader {
    private PluginLoader pluginLoader;

    public PluginLoaderProvider(PluginLoader pluginLoader) {
        this.pluginLoader = pluginLoader;
    }

    public static PluginLoader newLoader() {
        return new PluginLoaderProvider(new DefaultPluginLoader());
    }


    @Override
    public List<String> loader(List<String> pluginJars) throws Exception {
        return pluginLoader.loader(pluginJars);
    }

    @Override
    public String loaderRate(String loaderId) {
        return pluginLoader.loaderRate(loaderId);
    }
}
