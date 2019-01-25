package com.xishui.beeger.datap.engine.mysql;

import com.xishui.beeger.datap.engine.adaptor.connection.ConnectionModel;
import com.xishui.beeger.datap.engine.adaptor.connection.GenericSqlEngineConnection;
import com.xishui.beeger.datap.model.DriverClass;
import com.xishui.beeger.datap.model.compute.EngineParamKeys;

import java.util.Map;

public class MysqlEngineConnection extends GenericSqlEngineConnection {
    @Override
    public ConnectionModel buildConnectionParam(Map<String, Object> params) {
        if (!params.containsKey(EngineParamKeys.MysqlParam.MYSQL_CONNECTION_URL) ||
                !params.containsKey(EngineParamKeys.MysqlParam.MYSQL_CONNECTION_USERNAME) ||
                !params.containsKey(EngineParamKeys.MysqlParam.MYSQL_CONNECTION_PASSWORD)) {
            return null;
        }
        String url = (String) params.get(EngineParamKeys.MysqlParam.MYSQL_CONNECTION_URL);
        String userName = (String) params.get(EngineParamKeys.MysqlParam.MYSQL_CONNECTION_USERNAME);
        String password = (String) params.get(EngineParamKeys.MysqlParam.MYSQL_CONNECTION_PASSWORD);
        return new ConnectionModel(url, userName, password);
    }

    @Override
    public String driverClass() {
        return DriverClass.MYSQL.getDriver();
    }

}
