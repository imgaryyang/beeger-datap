package com.xishui.beeger.datap.mgrment.plugin.spi;

import com.xishui.beeger.datap.model.Params;
import com.xishui.beeger.datap.model.Result;
import com.xishui.beeger.datap.netty.spi.MessageExecution;

public interface PluginMessageExecution extends MessageExecution<Params,Result<String>> {
}
