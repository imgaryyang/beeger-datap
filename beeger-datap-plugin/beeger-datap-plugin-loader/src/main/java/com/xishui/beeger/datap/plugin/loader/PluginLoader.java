package com.xishui.beeger.datap.plugin.loader;

import java.util.List;

public interface PluginLoader {

    /**
     * 根据jar路径加载响应的包,并启动,给予启动日志
     * 返回启动id
     *
     * @param pluginJars
     * @throws Exception
     */
    List<String> loader(List<String> pluginJars) throws Exception;

    /**
     * 加载的进度
     *
     * @param loaderId
     * @return
     */
    String loaderRate(String loaderId);
}
