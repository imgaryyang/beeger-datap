package com.xishui.beeger.datap.router.engine;

import com.xishui.beeger.datap.engine.adaptor.Adaptor;
import com.xishui.beeger.datap.engine.adaptor.processor.DataProcessorAdaptor;
import com.xishui.beeger.datap.router.spi.RouterModel;

/**
 * 执行引擎的包装类，由此类获取具体的adaptor
 */
public interface EngineAdaptor extends Adaptor{

    DataProcessorAdaptor adaptor(RouterModel routerModel);
}
