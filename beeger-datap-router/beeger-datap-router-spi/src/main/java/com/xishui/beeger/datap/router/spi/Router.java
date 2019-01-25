package com.xishui.beeger.datap.router.spi;

public interface Router<T> {

    T router(RouterModel routerModel);
}
