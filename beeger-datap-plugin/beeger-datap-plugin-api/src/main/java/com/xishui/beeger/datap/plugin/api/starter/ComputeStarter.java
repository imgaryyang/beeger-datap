package com.xishui.beeger.datap.plugin.api.starter;

import com.alibaba.fastjson.JSON;
import com.xishui.beeger.datap.model.IPModel;
import com.xishui.beeger.datap.model.ParamKeys;
import com.xishui.beeger.datap.model.Params;
import com.xishui.beeger.datap.model.compute.ComputeDescriptionModel;
import com.xishui.beeger.datap.netty.common.Holders;
import com.xishui.beeger.datap.netty.http.HttpClient;
import com.xishui.beeger.datap.netty.starter.NettyConfig;
import com.xishui.beeger.datap.netty.starter.NettyStarter;
import com.xishui.beeger.datap.plugin.api.config.PluginConfig;
import com.xishui.beeger.datap.plugin.api.processor.BeegerDataComputeProcessor;
import com.xishui.beeger.datap.plugin.api.scan.PluginScanner;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.util.List;

public enum ComputeStarter {
    COMPUTE;

    private final InternalLogger logger = InternalLoggerFactory.getInstance(ComputeStarter.class);

    public void starter(PluginConfig pluginConfig) {
        try {
            checkPluginConfig(pluginConfig);
            //启动扫描，并注册服务至 plugin管理端(默认读取配置中心配置)
            List<ComputeDescriptionModel> computes = PluginScanner.SCANNER.scanner(pluginConfig.getScanPackage());
            String localIP = IPModel.IP.localIP();
            if (null == localIP) {
                throw new NullPointerException("Got Local IP Fail:" + localIP);
            }
            //远程注册
            logger.info("Compute Plugin Started Completed");
            //绑定netty server 端口固定
            Holders.putHolderValue(Holders.Keys.SERVER_URL, pluginConfig.getServerUrl());
            //注册扫描到的计算组件
            String msg = HttpClient.POST.send(Params.create()
                    .addParam(ParamKeys.REQUEST_IP, "http://" + localIP + ":" + pluginConfig.getExportPort())
                    .addParam(ParamKeys.MODEL_TYPE, pluginConfig.getModelType())
                    .addParam(ParamKeys.REQUEST_TYPE, ParamKeys.RequestValueKeys.REQUEST_TYPE_REGISTER_COMPUTE)
                    .addParam(ParamKeys.PLUGIN_JAR_PATH,
                            this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath())
                    .addParam(ParamKeys.PLUGIN_REGISTER_KEY, JSON.toJSONString(computes))
            );
            logger.info("register result:" + msg);
            //启动netty server
            NettyStarter.HTTP.startServer(NettyConfig.createNettyServerConfig().exportPort(pluginConfig.getExportPort())
                    .messageProcessor(new BeegerDataComputeProcessor()));
            //如果失败，需要取消注册信息
        } catch (Exception e) {
            logger.error("Computer Starter Err", e);
        }
    }

    private void checkPluginConfig(PluginConfig pluginConfig) throws Exception {
        if (null == pluginConfig) {
            throw new NullPointerException("PluginConfig is null.");
        }
        if (null == pluginConfig.getServerUrl() || "".equals(pluginConfig.getServerUrl())) {
            throw new NullPointerException("PluginConfig.serverUrl is null.");
        }
        if (pluginConfig.getExportPort() <= 3000) {
            throw new IllegalArgumentException("PluginConfig.exportPort less than 3000.");
        }
        if (null == pluginConfig.getScanPackage() || "".equals(pluginConfig.getScanPackage())) {
            throw new NullPointerException("PluginConfig.scanPackage is null.");
        }
    }
}
