package com.xishui.beeger.datap.container;

/**
 * ----------------------------
 * 容器根,提供一套基于容器的管理spi|
 * ----------------------------
 * 1.装载/卸载能力
 * 2.容器生命周期能力
 */
public interface Container {
    /**
     * 注册容器
     * @param containerContext
     */
    Container register(ContainerContext containerContext);

    /**
     * 卸载容器
     * @param containerContext
     */
    Container unregister(ContainerContext containerContext);
    /**
     * 生命周期管理
     */
    void mgrLifecycle(ContainerContext containerContext);
}
