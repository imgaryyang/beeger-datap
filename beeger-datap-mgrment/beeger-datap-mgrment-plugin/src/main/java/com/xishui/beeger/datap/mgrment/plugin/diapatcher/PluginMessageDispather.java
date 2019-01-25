package com.xishui.beeger.datap.mgrment.plugin.diapatcher;

import com.xishui.beeger.datap.mgrment.plugin.execution.EngineComputeExecution;
import com.xishui.beeger.datap.mgrment.plugin.execution.RegisterPluginExecution;
import com.xishui.beeger.datap.model.ParamKeys;
import com.xishui.beeger.datap.model.Params;
import com.xishui.beeger.datap.model.Result;
import com.xishui.beeger.datap.netty.spi.MessageDispather;
import com.xishui.beeger.datap.netty.spi.MessageExecution;

public class PluginMessageDispather implements MessageDispather<Params, Result<String>> {
    @Override
    public MessageExecution<Params, Result<String>> dispather(Params param) {
        if (!param.containKey(ParamKeys.REQUEST_TYPE)) {
            throw new RuntimeException("NON-Request type be Set.");
        }
        String requestType = param.getValueString(ParamKeys.REQUEST_TYPE);
        if (ParamKeys.RequestValueKeys.REQUEST_TYPE_REGISTER_COMPUTE.equals(requestType)) {
            //注册类型
            return new RegisterPluginExecution();
        } else if (ParamKeys.RequestValueKeys.REQUEST_TYPE_ENGINE_COMPUTE.equals(requestType)) {
            //计算类型
            return new EngineComputeExecution();
        } else {
            throw new RuntimeException("NON-Support RequestType :" + requestType);
        }
    }
}
