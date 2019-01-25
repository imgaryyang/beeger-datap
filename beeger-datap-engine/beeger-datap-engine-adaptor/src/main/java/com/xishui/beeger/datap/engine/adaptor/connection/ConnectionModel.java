package com.xishui.beeger.datap.engine.adaptor.connection;

import lombok.Data;

@Data
public class ConnectionModel {
    private String url;
    private String userName;
    private String password;

    public ConnectionModel(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }
}
