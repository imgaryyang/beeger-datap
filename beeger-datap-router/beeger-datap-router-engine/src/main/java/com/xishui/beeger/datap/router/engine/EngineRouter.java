package com.xishui.beeger.datap.router.engine;

import com.alibaba.fastjson.JSON;
import com.xishui.beeger.datap.model.Result;
import com.xishui.beeger.datap.router.spi.Router;
import com.xishui.beeger.datap.router.spi.RouterModel;

import java.util.List;
import java.util.Map;

public class EngineRouter implements Router<Result<String>> {
    @Override
    public Result<String> router(RouterModel routerModel) {
        try {
            Result<List<Map<String, Object>>> result = new DefaultEngineAdaptor().adaptor(routerModel)
                    .dataProcessor(routerModel.getParams());
            if (result.isSuccess()) {
                return Result.ofSuccess(JSON.toJSONString(result.getData()));
            }
            return Result.ofFailure(result.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ofFailure(e.getMessage());
        }
    }
}
