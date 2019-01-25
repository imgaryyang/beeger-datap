package com.xishui.beeger.datap.netty.server;

import com.xishui.beeger.datap.netty.spi.MessageProcessor;
import io.netty.handler.codec.http.HttpRequest;

public interface HttpMessageProcessor extends MessageProcessor<HttpRequest> {

}
