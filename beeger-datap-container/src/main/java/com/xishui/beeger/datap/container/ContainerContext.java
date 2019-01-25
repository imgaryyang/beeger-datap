package com.xishui.beeger.datap.container;

/**
 * 容器上下文
 * 1.
 */
public interface ContainerContext {
    /**
     * 容器唯一id
     *
     * @return
     */
    String containerUniqueID();

    /**
     * 容器名字
     *
     * @return
     */
    String containerName();

    /**
     * 获取获取容器模型
     *
     * @return
     */
    ContainerModel model();
}
