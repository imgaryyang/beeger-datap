package com.xishui.beeger.datap.netty.server;

import com.alibaba.fastjson.JSON;
import com.xishui.beeger.datap.model.Params;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public abstract class GenericHttpMessageProcessor<T> implements HttpMessageProcessor {
    @Override
    public final void processor(HttpRequest message, Channel channel) {

        Params params = Params.create();
        try {
            //post request
            if (HttpMethod.POST.name().equals(message.method().name())) {
                HttpPostRequestDecoder postRequestDecoder = new HttpPostRequestDecoder(message);
                postRequestDecoder.offer((HttpContent) message);
                List<InterfaceHttpData> paramList = postRequestDecoder.getBodyHttpDatas();
                for (final InterfaceHttpData interfaceHttpData : paramList) {
                    Attribute attribute = (Attribute) interfaceHttpData;
                    params.addParam(attribute.getName(), attribute.getValue());
                }
            } else if (HttpMethod.GET.name().equals(message.method().name())) {
                QueryStringDecoder queryStringDecoder = new QueryStringDecoder(message.uri());
                Map<String, List<String>> paramList = queryStringDecoder.parameters();
                if (null != paramList && !paramList.isEmpty()) {
                    paramList.forEach((k, v) -> {
                        v.forEach(item -> {
                            params.addParam(k, item);
                        });
                    });
                }
            } else {
                //other doing
            }
            T result = doProcessor(params);
            writeChannelAndFlush(JSON.toJSONString(result), channel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // must return string ,example json.
    public abstract T doProcessor(Params params);


    private void writeChannelAndFlush(String value, Channel channel) {
        if (!channel.isActive() || !channel.isOpen() || !channel.isWritable()) {
            throw new RuntimeException("channel not active/open/writable");
        }
        byte[] result = value.getBytes(Charset.forName("UTF-8"));
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK, Unpooled.wrappedBuffer(result));
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain");
        response.headers().set(HttpHeaders.Names.CONTENT_LENGTH,
                response.content().readableBytes());
        channel.writeAndFlush(response);
    }
}
