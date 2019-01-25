package com.xishui.beeger.datap.currency.zk;

import com.xishui.beeger.datap.currency.zk.holder.ZkClient;
import lombok.Data;

/**
 */
@Data
public class ZkProperties {
    private String connect;//连接的url
    private int connectionTimeOut = 30000;//连接的超时时间,毫秒
    private int sessionTimeOut = 600000;//连接session的超时时间,毫秒
    private String clientKey = ZkClient.DEFAULT_CLIENT;
}
