package com.xishui.beeger.datap.router.plugin;

import com.alibaba.fastjson.JSON;
import com.xishui.beeger.datap.currency.spring.SpringContextUtil;
import com.xishui.beeger.datap.model.ParamKeys;
import com.xishui.beeger.datap.model.Params;
import com.xishui.beeger.datap.model.Result;
import com.xishui.beeger.datap.model.compute.ComputeRequestModel;
import com.xishui.beeger.datap.mysql.constant.PluginStatus;
import com.xishui.beeger.datap.mysql.entity.ComputePluginEntity;
import com.xishui.beeger.datap.mysql.mapper.ComputePluginMapper;
import com.xishui.beeger.datap.netty.http.HttpClient;
import com.xishui.beeger.datap.router.spi.Router;
import com.xishui.beeger.datap.router.spi.RouterModel;

import java.util.List;
import java.util.Random;

/**
 * 调用插件计算
 */
public class ComputeRouter implements Router<Result<String>> {
    @Override
    public Result<String> router(RouterModel routerModel) {
        // not contains model param
        if (!routerModel.getParams().containsKey(ParamKeys.REQUEST_COMPUTE_MODEL_PARAM)) {
            return Result.ofFailure("Request Compute Model Param is null");
        }
        ComputeRequestModel computeRequestModel = JSON.parseObject(
                (String) routerModel.getParams().get(ParamKeys.REQUEST_COMPUTE_MODEL_PARAM), ComputeRequestModel.class);
        //from zk load ComputeRequestModel
        ComputePluginMapper computePluginMapper = SpringContextUtil.getBean(ComputePluginMapper.class);
        if (null == computePluginMapper) {
            return Result.ofFailure("ComputePluginMappper is null");
        }
        List<ComputePluginEntity> entities = computePluginMapper.selectByModelNameAndStatus(computeRequestModel.getComputeModel(), PluginStatus.ALIVE_ONLINE.getStatus());
        if (null == entities || entities.size() <= 0) {
            return Result.ofFailure("NO Active ComputePlugin can be Used.");
        }
        String computeIp = (String) routerModel.getParams().get(ParamKeys.COMPUTE_PROVIDER_IP);
        ComputePluginEntity entity = lookup(entities, computeIp);
        if (null == entity) {
            return Result.ofFailure("NO Found ComputePlugin Provider.");
        }
        try {

            String response = HttpClient.POST.send(Params.create().addParam(ParamKeys.REQUEST_COMPUTE_MODEL_PARAM,
                    (String) routerModel.getParams().get(ParamKeys.REQUEST_COMPUTE_MODEL_PARAM)),
                    entity.getAliveMachine());
            return JSON.parseObject(response, Result.class);
        } catch (Exception e) {
            return Result.ofSuccess(e.getMessage());
        }
    }

    private ComputePluginEntity lookup(List<ComputePluginEntity> entities, String computeIp) {
        if (null != computeIp) {
            for (final ComputePluginEntity entity : entities) {
                if (entity.getAliveMachine().equals(computeIp)) {
                    return entity;
                }
            }
        } else {
            return entities.get(new Random().nextInt(entities.size()));
        }
        return null;
    }
}
