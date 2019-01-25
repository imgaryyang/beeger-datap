package com.xishui.beeger.datap.engine.adaptor.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public abstract class GenericSqlEngineConnection implements EngineConnection<Connection>{
    @Override
    public Connection createConnection(Map<String, Object> params) {

        final ConnectionModel connectionModel = buildConnectionParam(params);
        try {
            return connection(connectionModel.getUrl(), connectionModel.getUserName(), connectionModel.getPassword());
        } catch (Exception e) {
            if (e instanceof SQLException) {
                try {
                    Class.forName(driverClass());
                    return connection(connectionModel.getUrl(), connectionModel.getUserName(), connectionModel.getPassword());
                } catch (Exception e1) {
                    e1.printStackTrace();
                    return null;
                }
            }
            e.printStackTrace();
        }
        return null;
    }

    private Connection connection(String url, String userName, String password) throws Exception {
        return DriverManager.getConnection(url, userName, password);
    }

    @Override
    public void releaseConnection(Connection connection) {
        if (null != connection) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract ConnectionModel buildConnectionParam(Map<String,Object> params);

    public abstract String driverClass();
}
