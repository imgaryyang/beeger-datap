package com.xishui.beeger.datap.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class GenericContainer implements Container, SingleContainerModel {

    private final Map<String, ContainerContext> startContainers = new ConcurrentHashMap<>();
    private final Map<String, ContainerContext> stopContainers = new ConcurrentHashMap<>();

    private final Object lock = new Object();

    @Override
    public void startSingleContainer(ContainerContext container) throws RuntimeException{
        //同步进行
        synchronized (lock) {
            if (startContainers.containsKey(container.containerUniqueID())) {
                // TODO being statered
                return;
            } else {
                //挨着启动所有插件
                container.model().starter();
                startContainers.put(container.containerUniqueID(), container);
                if (stopContainers.containsKey(container.containerUniqueID())) {
                    stopContainers.remove(container.containerUniqueID());
                }
                System.out.println("Container [ " + container.containerName() + " ] start Completed");
            }
        }
    }

    @Override
    public void stopSingleContainer(ContainerContext container) throws RuntimeException{
        synchronized (lock) {
            //挨着停止所有插件
            if (startContainers.containsKey(container.containerUniqueID())) {
                container.model().stop();
                if (stopContainers.containsKey(container.containerUniqueID())) {
                    stopContainers.put(container.containerUniqueID(), container);
                }
                startContainers.remove(container.containerUniqueID());
            } else {
                // TODO no started
                return;
            }
        }
    }


    @Override
    public void mgrLifecycle(ContainerContext containerContext) {
        //TODO
        printContainerStatus();
    }

    private void printContainerStatus() {
        //打印容器当前情况

    }
}
