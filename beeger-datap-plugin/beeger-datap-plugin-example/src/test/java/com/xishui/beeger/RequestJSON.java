package com.xishui.beeger;

import com.alibaba.fastjson.JSON;
import com.xishui.beeger.datap.model.compute.ComputeRequestModel;
import com.xishui.beeger.datap.plugin.example.ExampleUserRequest;

public class RequestJSON {

    public static void main(String... args){
        ComputeRequestModel computeRequestModel = new ComputeRequestModel();
        computeRequestModel.setComputeModel("example_name");

        ExampleUserRequest request = new ExampleUserRequest();
        request.setUserName("xishui");
        computeRequestModel.setParamJSON(JSON.toJSONString(request));
        System.out.println(JSON.toJSONString(computeRequestModel));
    }
}
