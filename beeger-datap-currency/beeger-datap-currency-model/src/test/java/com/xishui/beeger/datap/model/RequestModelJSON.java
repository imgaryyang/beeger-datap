package com.xishui.beeger.datap.model;

import com.alibaba.fastjson.JSON;
import com.xishui.beeger.datap.model.compute.ComputeRequestModel;

public class RequestModelJSON {
    public static void main(String... args){
        ComputeRequestModel model = new ComputeRequestModel();
        model.setComputeModel("example_compute");
        model.setComputeMethod("compute");
        model.setParamJSON("{\"companyName\":\"锦江麦德龙现购自运有限公司重庆南岸商场\",\"endDate\":\"20180801\",\"startDate\":\"20180701\"}");

        System.out.println(JSON.toJSONString(model));
    }
}
