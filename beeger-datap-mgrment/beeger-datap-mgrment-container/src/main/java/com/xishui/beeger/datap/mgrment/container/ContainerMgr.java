package com.xishui.beeger.datap.mgrment.container;

import com.xishui.beeger.datap.container.ContainerContext;
import com.xishui.beeger.datap.container.GenericContainer;
import com.xishui.beeger.datap.plugin.loader.PluginLoaderProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public enum ContainerMgr {
    MGR;
    private final Logger logger = LoggerFactory.getLogger(ContainerMgr.class);

    public void starterContainer() throws RuntimeException {
        synchronized (this) {
            GenericContainer genericContainer = ContainerHolder.container();
            final List<ContainerContext> contexts = Containers.containers();
            contexts.forEach(containerContext -> {
                genericContainer.register(containerContext);
            });
            genericContainer.starter();
        }
    }

    public void stopContainer() throws RuntimeException {
        synchronized (this) {
            GenericContainer genericContainer = ContainerHolder.container();
            final List<ContainerContext> contexts = Containers.containers();
            contexts.forEach(containerContext -> {
                genericContainer.unregister(containerContext);
            });
            genericContainer.stop();
        }
    }

    public void startComputeLocalPlugin(String jarPath) {
        try {
            if (null == jarPath) {
                logger.info("ComputerLocalLoader JarPath is null.");
                return;
            }
            initComputerProvider(jarPath);
        } catch (Exception e) {
            logger.error("ComputerLocalLoader Err.", e);
        }
    }

    private final void initComputerProvider(String jarPath) throws Exception {
        logger.info("*************Computer Plugin Local Loader Started***********");
        File file = new File(jarPath);
        if (!file.exists()) {
            throw new IllegalArgumentException("FilePath Not Exist by " + jarPath);
        }
        if (file.isDirectory()) {
            throw new IllegalArgumentException("FilePath is Directory by " + jarPath);
        }

        final List<String> jarPaths = new ArrayList<>();
        jarPaths.add((file.getAbsolutePath()));
        PluginLoaderProvider.newLoader().loader(jarPaths);
        logger.info("*************Computer Plugin Local Loader Completed***********");
    }

    public GenericContainer currentContainer() {
        return ContainerHolder.container();
    }
}
