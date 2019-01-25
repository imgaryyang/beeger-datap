package com.xishui.beeger.datap.container;

import java.util.List;

public abstract class AbstractGenericContainer extends GenericContainer {
    @Override
    public void starter() throws RuntimeException {
        final List<ContainerContext> containers = starterContainerContexts();
        if (null == containers || containers.size() <= 0) {
            return;
        }
        containers.forEach(containerContext -> {
            startSingleContainer(containerContext);
        });
    }

    @Override
    public void stop() throws RuntimeException {
        final List<ContainerContext> containers = stoperContainerContexts();
        if (null == containers || containers.size() <= 0) {
            // TODO LOGGER
            return;
        }
        containers.forEach(containerContext -> {
            stopSingleContainer(containerContext);
        });

    }

    public abstract List<ContainerContext> starterContainerContexts();

    public abstract List<ContainerContext> stoperContainerContexts();
}
