package com.xishui.beeger.datap.engine.kylin;

import com.xishui.beeger.datap.engine.adaptor.connection.ConnectionModel;
import com.xishui.beeger.datap.engine.adaptor.connection.GenericSqlEngineConnection;
import com.xishui.beeger.datap.model.DriverClass;
import com.xishui.beeger.datap.model.compute.EngineParamKeys;

import java.util.Map;

public class KylinEngineConnection extends GenericSqlEngineConnection {
    @Override
    public ConnectionModel buildConnectionParam(Map<String, Object> params) {
        if (!params.containsKey(EngineParamKeys.KylinParam.KYLIN_CONNECTION_URL) ||
                !params.containsKey(EngineParamKeys.KylinParam.KYLIN_CONNECTION_PASSWORD) ||
                !params.containsKey(EngineParamKeys.KylinParam.KYLIN_CONNECTION_USERNAME)) {
            return null;
        }
        String url = (String) params.get(EngineParamKeys.KylinParam.KYLIN_CONNECTION_URL);
        String userName = (String) params.get(EngineParamKeys.KylinParam.KYLIN_CONNECTION_USERNAME);
        String password = (String) params.get(EngineParamKeys.KylinParam.KYLIN_CONNECTION_PASSWORD);
        return new ConnectionModel(url, userName, password);
    }

    @Override
    public String driverClass() {
        return DriverClass.KYLIN.getDriver();
    }
}
