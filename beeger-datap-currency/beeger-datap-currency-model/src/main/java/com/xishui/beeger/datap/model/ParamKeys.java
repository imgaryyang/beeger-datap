package com.xishui.beeger.datap.model;

public final class ParamKeys {
    /**
     * netty请求类型
     */
    public static final String REQUEST_TYPE = "request.type";
    /**
     * 请求参数中的engine_model
     */
    public static final String ENGINE_MODEL = "engine.model";
    /**
     * 插件启动，注册自己的计算模块的信息的key
     */
    public static final String PLUGIN_REGISTER_KEY = "plugin.register.key";
    /**
     * 插件启动的机器ip
     */
    public static final String REQUEST_IP = "request.ip";
    /**
     * 是远程启动还是本地启动
     */
    public static final String MODEL_TYPE = "model.type";
    /**
     * 本地启动到jar的地址
     */
    public static final String PLUGIN_JAR_PATH = "plugin.jar.path";
    /**
     * 用于请求请求计算插件的ComputeRequestModel的参数传递 key
     */
    public static final String REQUEST_COMPUTE_MODEL_PARAM = "request.compute.model.param";

    public static final String COMPUTE_PROVIDER_IP = "compute.provider.ip";

    public class RequestValueKeys {
        /**
         * 注册类型的value
         */
        public static final String REQUEST_TYPE_REGISTER_COMPUTE = "request.type.register.compute";
        /**
         * 计算类型的value
         */
        public static final String REQUEST_TYPE_ENGINE_COMPUTE = "request.type.engine.compute";
    }


    private ParamKeys() {
        super();
    }
}
