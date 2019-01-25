package com.xishui.beeger.datap.engine.kylin;

import com.xishui.beeger.datap.engine.adaptor.mapper.GenericSqlEngineMapper;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class KylinEngineMapper extends GenericSqlEngineMapper {
    @Override
    public void beforeResultSet(ResultSet resultSet) {

    }

    @Override
    public void afterResult(List<Map<String, Object>> result) {

    }

    @Override
    public void exception(Exception e) {

    }
}
