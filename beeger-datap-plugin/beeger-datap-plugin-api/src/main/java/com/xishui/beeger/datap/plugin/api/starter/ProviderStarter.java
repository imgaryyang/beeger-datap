package com.xishui.beeger.datap.plugin.api.starter;

public interface ProviderStarter {
    void startProvider() throws Exception;

    boolean isRemote() throws Exception;
}
