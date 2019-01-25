package com.xishui.beeger.datap.plugin.example;

import com.xishui.beeger.datap.model.ParamKeys;
import com.xishui.beeger.datap.model.Params;
import com.xishui.beeger.datap.model.Result;
import com.xishui.beeger.datap.model.compute.EngineModel;
import com.xishui.beeger.datap.model.compute.EngineParamKeys;
import com.xishui.beeger.datap.netty.http.HttpClient;
import com.xishui.beeger.datap.plugin.api.BeegerCompute;
import com.xishui.beeger.datap.plugin.api.annotation.ComputeMethod;
import com.xishui.beeger.datap.plugin.api.annotation.ComputeModel;

@ComputeModel(modelName = "invoice_validator",
        modelCnName = "发票校验",
        modelDescription = "做发票校验的计算模型,发票可能是假的哟")
public class ExampleCompute implements BeegerCompute {
    @ComputeMethod(requestParams = ExampleModel.REQUEST,
            responseValue = ExampleModel.RESPONSE,
            methodDescription = "稍微真实点吧，测试下")
    @Override
    public Result<String> compute(String requestParams) throws Exception {
        System.out.println("ExampleCompute.compute:got request param:" + requestParams);
        //调用远程到pluginmrg，去查询kylin
        //load config from configcenter
        String serverUrl = "http://localhost:3000";
        return Result.ofSuccess(HttpClient.POST.send(Params.create()
                        .addParam(ParamKeys.REQUEST_TYPE, ParamKeys.RequestValueKeys.REQUEST_TYPE_ENGINE_COMPUTE)
                        .addParam(ParamKeys.ENGINE_MODEL, EngineModel.KYLIN.getModelName())
                        .addParam(EngineParamKeys.KylinParam.KYLIN_CONNECTION_URL, "jdbc:kylin://localhost:7070/xx-data")
                        .addParam(EngineParamKeys.KylinParam.KYLIN_CONNECTION_USERNAME, "root")
                        .addParam(EngineParamKeys.KylinParam.KYLIN_CONNECTION_PASSWORD, "admin")
                        .addParam(EngineParamKeys.SQL, "select round(sum(taxamount),0) as taxmt from ODS_MONGODB_xishui_BILLING.INVOICE_MERGER")
                , serverUrl));
    }
}
