package com.xishui.beeger.datap.plugin.example;

import com.alibaba.fastjson.JSON;
import com.xishui.beeger.datap.model.ParamKeys;
import com.xishui.beeger.datap.model.Params;
import com.xishui.beeger.datap.model.Result;
import com.xishui.beeger.datap.model.compute.EngineModel;
import com.xishui.beeger.datap.model.compute.EngineParamKeys;
import com.xishui.beeger.datap.netty.http.HttpClient;
import com.xishui.beeger.datap.plugin.api.BeegerCompute;
import com.xishui.beeger.datap.plugin.api.annotation.ComputeMethod;
import com.xishui.beeger.datap.plugin.api.annotation.ComputeModel;
import com.xishui.beeger.datap.plugin.api.query.QueryParam;
import com.xishui.beeger.datap.plugin.api.query.QueryProvider;

import java.util.List;

@ComputeModel(modelName = "example_name",
        modelCnName = "一个例子",
        modelDescription = "能好好玩吗")
public class ExampleCompute implements BeegerCompute {
    @ComputeMethod(requestParams = ExampleModel.REQUEST,
            responseValue = ExampleModel.RESPONSE,
            methodDescription = "先这样看看吧")
    @Override
    public Result<String> compute(String requestParams) throws Exception {
        System.out.println("Request:" + requestParams);
        //not need
        ExampleUserRequest request = JSON.parseObject(requestParams,ExampleUserRequest.class);

        String sql = "select id,user_name,age,phone from example_user";
        Result<List<ExampleUser>> result = QueryProvider.newMysqlProvider().query(sql,
                QueryParam.newQueryParam().connectionKey(Keys.NORMAL_MYSQL_CONNECTION_KEY)
                        .engineModel(EngineModel.MYSQL), ExampleUser.class);
        return Result.ofSuccess(JSON.toJSONString(result.getData()));
    }
}
