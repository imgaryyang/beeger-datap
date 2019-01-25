package com.xishui.beeger.datap.mgrment.container;

import com.xishui.beeger.datap.container.ContainerContext;
import com.xishui.beeger.datap.mgrment.engine.DatapEngineContainerContext;
import com.xishui.beeger.datap.mgrment.plugin.DatapPluginContainerContext;

import java.util.ArrayList;
import java.util.List;

public class Containers {
    //手动加载，先不做scanner方式的扫描
    private static List<ContainerContext> staticContainers = new ArrayList<>();

    static {
        staticContainers.add(new DatapEngineContainerContext());
        //plugin有netty server启动，会阻塞，所以必须放到最后
        addPluginContainerContext();
    }

    public static List<ContainerContext> containers() {
        return staticContainers;
    }

    private static void addPluginContainerContext() {
        staticContainers.add(new DatapPluginContainerContext());
    }
}
