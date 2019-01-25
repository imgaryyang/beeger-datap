package com.xishui.beeger.datap.engine.adaptor.processor;

import com.xishui.beeger.datap.engine.adaptor.Adaptor;
import com.xishui.beeger.datap.model.Result;

import java.util.Map;

public interface DataProcessorAdaptor<T> extends Adaptor {

    Result<T> dataProcessor(Map<String, Object> params) throws Exception;
}
