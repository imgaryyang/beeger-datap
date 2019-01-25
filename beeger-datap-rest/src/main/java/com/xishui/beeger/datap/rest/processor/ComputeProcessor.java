package com.xishui.beeger.datap.rest.processor;

import com.alibaba.fastjson.JSON;
import com.xishui.beeger.datap.mgrment.plugin.diapatcher.PluginMessageDispather;
import com.xishui.beeger.datap.model.ParamKeys;
import com.xishui.beeger.datap.model.Params;
import com.xishui.beeger.datap.model.Result;
import com.xishui.beeger.datap.model.compute.ComputeRequestModel;
import com.xishui.beeger.datap.model.compute.ComputeResponseModel;
import com.xishui.beeger.datap.model.compute.EngineModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComputeProcessor {
    private final Logger logger = LoggerFactory.getLogger(ComputeProcessor.class);
    public ComputeResponseModel processor(ComputeRequestModel computeRequestModel){
        logger.info("ComputeRest Request :" + computeRequestModel.toJsonRequest());
        Params params = Params.create()
                .addParam(ParamKeys.REQUEST_COMPUTE_MODEL_PARAM, JSON.toJSONString(computeRequestModel))
                .addParam(ParamKeys.COMPUTE_PROVIDER_IP, computeRequestModel.getComputeIp())
                .addParam(ParamKeys.REQUEST_TYPE, ParamKeys.RequestValueKeys.REQUEST_TYPE_ENGINE_COMPUTE)
                .addParam(ParamKeys.ENGINE_MODEL, EngineModel.PLUGIN.getModelName());
        Result<String> result = new PluginMessageDispather().dispather(params).execution(params, null);
        logger.info("ComputeRest Response :" + JSON.toJSONString(result));
        return result.isSuccess() ? ComputeResponseModel.ofSucess(result.getData()) :
                ComputeResponseModel.ofFailure(result.getDescription());
    }
}
