package com.xishui.beeger.datap.container;

public interface SingleContainerModel extends ContainerModel {

    void startSingleContainer(ContainerContext containerContext) throws RuntimeException;

    void stopSingleContainer(ContainerContext containerContext) throws RuntimeException;
}
