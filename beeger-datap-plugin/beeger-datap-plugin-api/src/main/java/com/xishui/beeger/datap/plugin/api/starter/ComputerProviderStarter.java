package com.xishui.beeger.datap.plugin.api.starter;

import com.xishui.beeger.datap.model.IPModel;
import com.xishui.beeger.datap.model.ModelType;
import com.xishui.beeger.datap.plugin.api.config.ConfigLoader;
import com.xishui.beeger.datap.plugin.api.config.PluginConfig;
import com.xishui.beeger.datap.plugin.api.log.LoggerInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public abstract class ComputerProviderStarter implements ProviderStarter {
    private final Logger logger = LoggerFactory.getLogger(ComputerProviderStarter.class);

    @Override
    public void startProvider() throws Exception {
        //init logback
        LoggerInit.LOGGER.init(logbackFileName());
        Properties config = ConfigLoader.LOADER.load(configFileName());
        PluginConfig pluginConfig = builderConfig(config);
        checkPluginConfig(pluginConfig);
        ComputeStarter.COMPUTE.starter(pluginConfig);
        logger.info("Compute Plugin Started by Port:" + pluginConfig.getExportPort());
    }

    public abstract String configFileName();

    public abstract PluginConfig builderConfig(Properties properties);


    private void checkPluginConfig(PluginConfig pluginConfig) throws Exception {
        if (null == pluginConfig) {
            throw new NullPointerException("ComputeProvider Config is null.");
        }
        if (pluginConfig.getExportPort() <= 3000) {
            throw new IllegalStateException("Export Port Must Large Than 3000");
        }
        if (null == pluginConfig.getScanPackage()) {
            throw new NullPointerException("ComputeProvider ScanPackage is null.");
        }
        if (null == pluginConfig.getModelType()) {
            throw new NullPointerException("ComputeProvider ModelType is null.");
        }
        if (ModelType.REMOTE.getType().equals(pluginConfig.getModelType())) {
            if (null == pluginConfig.getServerUrl()) {
                throw new NullPointerException("ComputeProvider ModelType is REMOTE, ServerUrl is null.");
            }
        } else {
            //本地模型
            pluginConfig.setServerUrl("http://" + IPModel.IP.localIP() + ":3000");
        }
    }

    //can be override
    public String logbackFileName() {
        return LoggerInit.DEFAULT_LOGGER_NAME;
    }

    @Override
    public boolean isRemote() throws Exception {
        return false;
    }

}
