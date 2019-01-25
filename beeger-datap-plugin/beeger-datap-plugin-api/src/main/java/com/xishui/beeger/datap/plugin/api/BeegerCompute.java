package com.xishui.beeger.datap.plugin.api;

import com.xishui.beeger.datap.model.Result;

public interface BeegerCompute {

    Result<String> compute(String requestParams) throws Exception;

}
