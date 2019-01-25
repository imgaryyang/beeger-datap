package com.xishui.beeger.datap.plugin.api.connection;

import lombok.Data;

@Data
public class MysqlConnection extends GenericConnection {
    private String url;
    private String userName;
    private String password;
}
