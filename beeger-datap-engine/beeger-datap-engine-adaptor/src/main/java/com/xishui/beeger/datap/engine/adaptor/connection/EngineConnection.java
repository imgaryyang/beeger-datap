package com.xishui.beeger.datap.engine.adaptor.connection;

import java.util.Map;

/**
 * 链接管理器，封装了链接基本操作<创建链接.释放链接>
 */
public interface EngineConnection<C> {

    C createConnection(Map<String,Object> params);

    void releaseConnection(C connection);
}
