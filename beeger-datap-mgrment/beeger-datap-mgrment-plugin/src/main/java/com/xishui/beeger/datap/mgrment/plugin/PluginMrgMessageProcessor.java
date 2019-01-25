package com.xishui.beeger.datap.mgrment.plugin;

import com.xishui.beeger.datap.mgrment.plugin.diapatcher.PluginMessageDispather;
import com.xishui.beeger.datap.model.Params;
import com.xishui.beeger.datap.model.Result;
import com.xishui.beeger.datap.netty.server.GenericHttpMessageProcessor;

public class PluginMrgMessageProcessor extends GenericHttpMessageProcessor<Result<String>> {
    @Override
    public Result<String> doProcessor(Params params) {
        try {
            return new PluginMessageDispather().dispather(params).execution(params, null);
        } catch (Exception e) {
            return Result.ofFailure(e.getMessage());
        }
    }
}

