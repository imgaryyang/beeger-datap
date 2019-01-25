package com.xishui.beeger.datap.mgrment.engine;

import com.xishui.beeger.datap.container.ContainerContext;
import com.xishui.beeger.datap.container.ContainerModel;

import java.util.UUID;

public class DatapEngineContainerContext implements ContainerContext {
    @Override
    public String containerUniqueID() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String containerName() {
        return "Datap-Engine-Container";
    }

    @Override
    public ContainerModel model() {
        return new DatapEngineContainerModel();
    }
}
