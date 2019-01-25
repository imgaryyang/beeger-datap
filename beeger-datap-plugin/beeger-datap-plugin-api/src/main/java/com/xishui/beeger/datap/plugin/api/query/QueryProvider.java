package com.xishui.beeger.datap.plugin.api.query;

import com.xishui.beeger.datap.model.Result;
import com.xishui.beeger.datap.plugin.api.query.mysql.MysqlQueryProvider;

import java.util.List;

public interface QueryProvider {

    <T> Result<List<T>> query(String sql, QueryParam queryParam, DataMapper<T> dataMapper) throws Exception;

    <T> Result<List<T>> query(String sql, QueryParam queryParam, Class<T> clazz) throws Exception;

    static MysqlQueryProvider newMysqlProvider() {
        return new MysqlQueryProvider();
    }
}
