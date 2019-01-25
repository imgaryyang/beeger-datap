package com.xishui.beeger.datap.router.engine;

import com.xishui.beeger.datap.common.spi.ResumeChecker;
import com.xishui.beeger.datap.engine.adaptor.processor.DataProcessorAdaptor;
import com.xishui.beeger.datap.engine.kylin.KylinDataProcessorAdaptor;
import com.xishui.beeger.datap.engine.mysql.MysqlDataProcessorAdaptor;
import com.xishui.beeger.datap.model.compute.EngineModel;
import com.xishui.beeger.datap.router.spi.RouterModel;

import java.util.Map;

/**
 * 默认的engine实现
 */
public class DefaultEngineAdaptor implements EngineAdaptor, ResumeChecker<EngineModel, Boolean> {

    @Override
    public DataProcessorAdaptor adaptor(RouterModel routerModel) {

        if (!check(routerModel.getEngineModel(), null).booleanValue()) {
            // 执行引擎没有初始化
            throw new RuntimeException("EngineModel is not initinalize.");
        }
        switch (routerModel.getEngineModel()) {
            case KYLIN:
                return new KylinDataProcessorAdaptor();
            case MYSQL:
                return new MysqlDataProcessorAdaptor();
            case ELASTICSEARCH:
                break;
            case MONGODB:
                break;
            case SPARK_SQL:
                break;
            default:
                break;
        }
        return null;
    }

    // default true
    @Override
    public final Boolean check(EngineModel param, Map<String, String> extendParams) {
        return Boolean.TRUE;
    }
}
