package com.xishui.beeger.datap.model.compute;

public final class EngineParamKeys {

    public static final String SQL = "sql";

    public static final String PARAMS = "params";

    public final class MysqlParam {
        public static final String MYSQL_CONNECTION_URL = "mysql.connection.url";
        public static final String MYSQL_CONNECTION_USERNAME = "mysql.connection.username";
        public static final String MYSQL_CONNECTION_PASSWORD = "mysql.connection.password";
    }

    public final class KylinParam {
        public static final String KYLIN_CONNECTION_URL = "kylin.connection.url";
        public static final String KYLIN_CONNECTION_USERNAME = "kylin.connection.username";
        public static final String KYLIN_CONNECTION_PASSWORD = "kylin.connection.password";
    }

    public final class ComputeParam {
        public static final String COMPUTE_NAME = "compute.name";
    }

    private EngineParamKeys() {
        super();
    }
}
