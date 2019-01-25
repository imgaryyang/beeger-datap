package com.xishui.beeger.datap.common.chain;

public interface Handler<T> {

    ChainContext<T> handler(ChainContext<T> chainContext);
}
