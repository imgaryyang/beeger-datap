package com.xishui.beeger.datap.container;

import java.util.ArrayList;
import java.util.List;

public class DefaultListContainer extends AbstractGenericContainer {

    private final List<ContainerContext> starterContainer = new ArrayList<>();
    private final List<ContainerContext> stoperContainer = new ArrayList<>();

    @Override
    public List<ContainerContext> starterContainerContexts() {
        final List<ContainerContext> starters = new ArrayList<>();
        starterContainer.forEach(containerContext -> {
            starters.add(containerContext);
        });
        starterContainer.clear();
        return starters;
    }

    @Override
    public List<ContainerContext> stoperContainerContexts() {
        final List<ContainerContext> stoper = new ArrayList<>();
        stoperContainer.forEach(containerContext -> {
            stoper.add(containerContext);
        });
        stoperContainer.clear();
        return stoper;
    }

    @Override
    public Container register(ContainerContext containerContext) {
        starterContainer.add(containerContext);
        return this;
    }

    @Override
    public Container unregister(ContainerContext containerContext) {
        stoperContainer.add(containerContext);
        return this;
    }
}
