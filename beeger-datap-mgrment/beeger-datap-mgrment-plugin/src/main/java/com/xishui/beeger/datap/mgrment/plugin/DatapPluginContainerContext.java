package com.xishui.beeger.datap.mgrment.plugin;

import com.xishui.beeger.datap.container.ContainerContext;
import com.xishui.beeger.datap.container.ContainerModel;

import java.util.UUID;

public class DatapPluginContainerContext implements ContainerContext {
    @Override
    public String containerUniqueID() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String containerName() {
        return "Datap-Plugin-Container";
    }

    @Override
    public ContainerModel model() {
        return new DatapPluginContainerModel();
    }
}
