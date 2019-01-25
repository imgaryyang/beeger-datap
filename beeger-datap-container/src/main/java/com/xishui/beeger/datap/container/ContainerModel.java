package com.xishui.beeger.datap.container;

public interface ContainerModel {
    /**
     * 启动容器
     * @throws Exception
     */
    void starter() throws  RuntimeException;

    /**
     * 停止容器
     * @throws Exception
     */
    void stop() throws RuntimeException;
}
