package com.xishui.beeger.datap.engine.adaptor.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GenericSqlEngineMapper implements EngineMapper<ResultSet, List<Map<String, Object>>> {
    @Override
    public List<Map<String, Object>> mapper(ResultSet result) {
        try {
            beforeResultSet(result);
            ResultSetMetaData metaData = result.getMetaData();
            final List<String> columns = new ArrayList<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columns.add(metaData.getColumnLabel(i));
            }
            List<Map<String, Object>> valueResult = new ArrayList<>();
            while (result.next()) {
                Map<String, Object> resultMap = new HashMap<>();
                columns.forEach((String column) -> {
                    try {
                        resultMap.put(column, result.getObject(column));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                valueResult.add(resultMap);
            }
            afterResult(valueResult);
            return valueResult;
        } catch (SQLException e) {
            exception(e);
        }
        return null;
    }

    public abstract void beforeResultSet(ResultSet resultSet);

    public abstract void afterResult(List<Map<String, Object>> result);

    public abstract void exception(Exception e);
}
