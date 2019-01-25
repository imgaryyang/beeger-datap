package com.xishui.beeger.datap.plugin.api.processor;


import com.alibaba.fastjson.JSON;
import com.xishui.beeger.datap.model.ParamKeys;
import com.xishui.beeger.datap.model.Params;
import com.xishui.beeger.datap.model.Result;
import com.xishui.beeger.datap.model.compute.ComputeDescriptionMethod;
import com.xishui.beeger.datap.model.compute.ComputeDescriptionModel;
import com.xishui.beeger.datap.model.compute.ComputeRequestModel;
import com.xishui.beeger.datap.netty.common.Holders;
import com.xishui.beeger.datap.netty.server.GenericHttpMessageProcessor;
import com.xishui.beeger.datap.plugin.api.holder.ComputePluginInvokeHolder;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 服务端
 */
public class BeegerDataComputeProcessor extends GenericHttpMessageProcessor<Result<String>> {
    @Override
    public Result<String> doProcessor(Params params) {
        if (!params.containKey(ParamKeys.REQUEST_COMPUTE_MODEL_PARAM)) {
            return Result.ofFailure("Request Compute Model is null.");
        }
        String queryParam = params.getValueString(ParamKeys.REQUEST_COMPUTE_MODEL_PARAM);
        ComputeRequestModel computeRequestModel = JSON.parseObject(queryParam, ComputeRequestModel.class);
        //router to compute and reflect method doing.
        String computeHolder = Holders.pullHolderValue(computeRequestModel.getComputeModel());
        if (null == computeHolder) {
            return Result.ofFailure("ERR. NO Compute match Request.ComputeModel");
        }
        ComputeDescriptionModel computeDescriptionModel = JSON.parseObject(computeHolder, ComputeDescriptionModel.class);
        List<ComputeDescriptionMethod> computeDescriptionMethods = computeDescriptionModel.getComputeDescriptionMethods();
        if (null == computeDescriptionMethods || computeDescriptionMethods.size() <= 0) {
            return Result.ofFailure("ERR. ComputeModel non-data for method");
        }
        String computeClass = computeDescriptionModel.getComputeModelClass();
        ComputeDescriptionMethod computeMethod = null;
        for (final ComputeDescriptionMethod method : computeDescriptionMethods) {
            if (method.getComputeMethodName().equals(computeRequestModel.getComputeMethod())) {
                computeMethod = method;
                break;
            }
        }
        if (null == computeMethod) {
            return Result.ofFailure("ERR. No Match Invoke computeMethod:" + computeRequestModel.getComputeMethod());
        }
        try {
            //做一下缓存,避免一直加载
            Class<?> cls = ComputePluginInvokeHolder.HOLDER.getInvoke(computeClass);
            if(null == cls) {
                cls = Class.forName(computeClass, false, this.getClass().getClassLoader());
                ComputePluginInvokeHolder.HOLDER.putInvoke(computeClass,cls);
            }
            Method method = cls.getMethod(computeMethod.getRealMethodName(), String.class);
            method.setAccessible(true);
            return (Result<String>) method.invoke(cls.newInstance(), computeRequestModel.getParamJSON());
        } catch (Exception e) {
            return Result.ofFailure(e.getMessage());
        }
    }
}
