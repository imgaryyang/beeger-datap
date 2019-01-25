package com.xishui.beeger.datap.engine.adaptor.execution.jdbc;

import com.xishui.beeger.datap.engine.adaptor.execution.GenericEngineExecution;
import com.xishui.beeger.datap.engine.adaptor.mapper.EngineMapper;
import com.xishui.beeger.datap.model.compute.EngineParamKeys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * 做一层抽象，防止类似mysql、kylin等jdbc类型等resultset集合在mapper等时候，发现connection 以及statement关闭问题
 * 注入解析mapper
 */
public abstract class JdbcEngineExecution extends GenericEngineExecution<List<Map<String, Object>>, Connection, ResultSet> {
    //特殊情况，覆盖父类等方法
    @Override
    public List<Map<String, Object>> execution(Connection connection, Map<String, Object> params, EngineMapper<ResultSet, List<Map<String, Object>>> engineMapper) {
        String sql = (String) params.get(EngineParamKeys.SQL);
        PreparedStatement state = null;
        try {
            state = connection.prepareStatement(sql);
            ResultSet resultSet = doExecution(state, params);
            if (null != resultSet) {
                return engineMapper.mapper(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != state) {
                    state.close();
                }
                if (null != connection) {
                    connection.close();
                }
            } catch (Exception e) {

            }
        }
        return null;
    }

    @Override
    public final ResultSet doExecution(Connection connection, Map<String, Object> params) {
        throw new UnsupportedOperationException("JDBC Engine Type Not Support.");
    }


    /**
     * 具体等执行方法，可能包含？ 等参数等set
     *
     * @param statement
     * @param params
     * @return
     */
    public abstract ResultSet doExecution(PreparedStatement statement, Map<String, Object> params);
}
