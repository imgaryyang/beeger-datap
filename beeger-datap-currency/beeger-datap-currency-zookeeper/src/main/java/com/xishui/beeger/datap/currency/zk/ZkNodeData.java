package com.xishui.beeger.datap.currency.zk;

import lombok.Data;

/**
 */
@Data
public class ZkNodeData<T> {
    private String path;
    private T data;

    public ZkNodeData(String path, T data) {
        this.path = path;
        this.data = data;
    }

}
