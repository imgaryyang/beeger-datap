package com.xishui.beeger.datap.engine.adaptor.execution.jdbc;

import com.alibaba.fastjson.JSON;
import com.xishui.beeger.datap.model.compute.EngineParamKeys;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DefaultJdbcEngineExecution extends JdbcEngineExecution {
    @Override
    public ResultSet doExecution(PreparedStatement statement, Map<String, Object> params) {
        try {
            List<Object> sortedParams = new ArrayList<>();
            if (params.containsKey(EngineParamKeys.PARAMS)) {
                Object paramValue = JSON.parse((String) params.get(EngineParamKeys.PARAMS));

                if (paramValue instanceof List) {
                    sortedParams.addAll((List<Object>) paramValue);
                }
            }
            for (int i = 1; i <= sortedParams.size(); i++) {
                Object param = sortedParams.get(i - 1);
                if (param instanceof String) {
                    statement.setString(i, (String) param);
                    continue;
                }
                if (param instanceof Integer) {
                    statement.setInt(i, (Integer) param);
                    continue;
                }
                if (param instanceof Double) {
                    statement.setDouble(i, (Double) param);
                    continue;
                }
                if (param instanceof BigDecimal) {
                    statement.setBigDecimal(i, (BigDecimal) param);
                    continue;
                }
                if (param instanceof Float) {
                    statement.setFloat(i, (Float) param);
                    continue;
                }
                if (param instanceof Byte) {
                    statement.setByte(i, (Byte) param);
                    continue;
                }
                if (param instanceof Date) {
                    statement.setDate(i, new java.sql.Date(((Date) param).getTime()));
                    continue;
                }
                if (param instanceof Long) {
                    statement.setLong(i, (Long) param);
                    continue;
                }
                if (param instanceof Boolean) {
                    statement.setBoolean(i, (Boolean) param);
                    continue;
                }
            }
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
