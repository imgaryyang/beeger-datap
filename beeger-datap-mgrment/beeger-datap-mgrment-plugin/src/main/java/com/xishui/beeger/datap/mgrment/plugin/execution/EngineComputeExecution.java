package com.xishui.beeger.datap.mgrment.plugin.execution;

import com.xishui.beeger.datap.mgrment.plugin.spi.PluginMessageExecution;
import com.xishui.beeger.datap.model.ParamKeys;
import com.xishui.beeger.datap.model.Params;
import com.xishui.beeger.datap.model.Result;
import com.xishui.beeger.datap.model.compute.EngineModel;
import com.xishui.beeger.datap.router.plugin.PluginRouter;
import com.xishui.beeger.datap.router.spi.RouterModel;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * 提供计算服务的消息处理器
 */
public class EngineComputeExecution implements PluginMessageExecution {
    @Override
    public Result<String> execution(Params param, Map<String, Object> extendParams) {
        EngineModel engineModel = EngineModel.matchEngine(param.getValueString(ParamKeys.ENGINE_MODEL));
        if (null == engineModel) {
            return Result.ofFailure("EngineModel is null. Please Set it/ see EngineModel.class");
        }
        Map<String, Object> routerParams = param.getParamMap().entrySet().stream()
                .filter(map -> !ParamKeys.REQUEST_TYPE.equals(map.getKey()))
                .filter(map -> !ParamKeys.ENGINE_MODEL.equals(map.getKey()))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));

        return new PluginRouter().router(RouterModel.create().model(engineModel).attributeMap(routerParams));
    }
}
