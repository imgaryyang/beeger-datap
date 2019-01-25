package com.xishui.beeger.datap.mgrment.container;

import com.xishui.beeger.datap.container.DefaultListContainer;
import com.xishui.beeger.datap.container.GenericContainer;

public final class ContainerHolder {

    private static final class ContainerFactory {
        private final static GenericContainer CONTAINER = new DefaultListContainer();
    }

    public static GenericContainer container() {
        return ContainerFactory.CONTAINER;
    }

}
