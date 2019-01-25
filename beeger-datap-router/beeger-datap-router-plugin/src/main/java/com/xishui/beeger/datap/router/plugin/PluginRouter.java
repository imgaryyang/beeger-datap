package com.xishui.beeger.datap.router.plugin;

import com.xishui.beeger.datap.model.Result;
import com.xishui.beeger.datap.model.compute.EngineModel;
import com.xishui.beeger.datap.router.engine.EngineRouter;
import com.xishui.beeger.datap.router.spi.Router;
import com.xishui.beeger.datap.router.spi.RouterModel;

public class PluginRouter implements Router<Result<String>> {

    @Override
    public Result<String> router(RouterModel routerModel) {
        //如果是compute路由，则通过zk获取router->compute描述，进行调用
        //如果是正常的路由,则调用engine模块进行后续路由
        if (routerModel.getEngineModel() == EngineModel.PLUGIN) {
            return new ComputeRouter().router(routerModel);
        } else {
            return new EngineRouter().router(routerModel);
        }
    }
}
