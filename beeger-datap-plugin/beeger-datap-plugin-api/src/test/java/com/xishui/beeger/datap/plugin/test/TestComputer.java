package com.xishui.beeger.datap.plugin.test;

import com.xishui.beeger.datap.model.Result;
import com.xishui.beeger.datap.plugin.api.BeegerCompute;
import com.xishui.beeger.datap.plugin.api.annotation.ComputeMethod;
import com.xishui.beeger.datap.plugin.api.annotation.ComputeModel;

@ComputeModel(modelName = "test_compute", modelDescription = "测试的compute",modelCnName = "")
public class TestComputer implements BeegerCompute {
    @ComputeMethod(responseValue = "aldajldkja", requestParams = "adadad")
    @Override
    public Result<String> compute(String requestParams) {
        System.out.println("receive client request json:" + requestParams);
        return Result.ofSuccess("this is response json");
    }
}
