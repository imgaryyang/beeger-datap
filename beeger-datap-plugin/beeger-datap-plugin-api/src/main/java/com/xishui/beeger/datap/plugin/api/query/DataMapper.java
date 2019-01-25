package com.xishui.beeger.datap.plugin.api.query;

import java.util.Map;

public interface DataMapper<T>{

    T mapper(Map values);
}
