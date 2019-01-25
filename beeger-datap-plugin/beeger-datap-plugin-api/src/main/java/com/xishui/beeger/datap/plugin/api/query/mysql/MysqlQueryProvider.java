package com.xishui.beeger.datap.plugin.api.query.mysql;

import com.alibaba.fastjson.JSON;
import com.xishui.beeger.datap.model.ParamKeys;
import com.xishui.beeger.datap.model.Params;
import com.xishui.beeger.datap.model.Result;
import com.xishui.beeger.datap.model.compute.EngineModel;
import com.xishui.beeger.datap.model.compute.EngineParamKeys;
import com.xishui.beeger.datap.netty.http.HttpClient;
import com.xishui.beeger.datap.plugin.api.connection.ConnectionHolder;
import com.xishui.beeger.datap.plugin.api.connection.GenericConnection;
import com.xishui.beeger.datap.plugin.api.connection.MysqlConnection;
import com.xishui.beeger.datap.plugin.api.query.DataMapper;
import com.xishui.beeger.datap.plugin.api.query.QueryParam;
import com.xishui.beeger.datap.plugin.api.query.QueryProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MysqlQueryProvider implements QueryProvider {
    private final Logger logger = LoggerFactory.getLogger(MysqlQueryProvider.class);

    /**
     * 手动做mapper
     */
    @Override
    public <T> Result<List<T>> query(String querySql, QueryParam queryParam, DataMapper<T> dataMapper) throws Exception {
        try {
            logger.info("Mysql Provider sql:" + querySql);
            logger.info("Mysql Provider params:" + queryParam.toSortListJSONParam());
            checkParam(querySql, queryParam, dataMapper);
            GenericConnection genericConnection = ConnectionHolder.HOLDER.getConnection(queryParam.getConnectionKey());
            if (null == genericConnection) {
                throw new NullPointerException("MysqlProvider Connection Info Not Found By ConnectionKey "
                        + queryParam.getConnectionKey());
            }
            MysqlConnection mysqlConnection = null;
            if (genericConnection instanceof MysqlConnection) {
                mysqlConnection = (MysqlConnection) genericConnection;
            }
            if (null == mysqlConnection) {
                throw new IllegalStateException("MysqlProvider getConnection Convert is null:"
                        + genericConnection.getClass().getSimpleName());
            }
            Params params = Params.builder().requestType(ParamKeys.RequestValueKeys.REQUEST_TYPE_ENGINE_COMPUTE)
                    .engineModel(EngineModel.MYSQL.getModelName())
                    .connectionUrl(mysqlConnection.getUrl())
                    .connectionUserName(mysqlConnection.getUserName())
                    .connectionPassword(mysqlConnection.getPassword())
                    .sql(querySql).build();
            if (!queryParam.emptyParam()) {
                params.addParam(EngineParamKeys.PARAMS, queryParam.toSortListJSONParam());
            }
            String result = HttpClient.POST.send(params, genericConnection.getServerUrl());
            Result<String> dataMap = JSON.parseObject(result, Result.class);
            if (!dataMap.isSuccess()) {
                return Result.ofFailure(dataMap.getDescription());
            }
            List<Map> dataList = JSON.parseArray(dataMap.getData(), Map.class);
            if (null == result || dataList.size() <= 0) {
                return Result.ofSuccess(null);
            }
            List<T> responses = new ArrayList<>();
            for (Map map : dataList) {
                responses.add(dataMapper.mapper(map));
            }
            return Result.ofSuccess(responses);
        } catch (Exception e) {
            return Result.ofFailure(e.getMessage());
        }
    }

    @Override
    public <T> Result<List<T>> query(String querySql, QueryParam queryParam, Class<T> clazz) throws Exception {
        return query(querySql, queryParam, new DefaultDataMapper<>(clazz));
    }

    private <T> void checkParam(String querySql, QueryParam queryParam, DataMapper<T> dataMapper) throws Exception {
        if (null == querySql || "".equals(querySql.trim())) {
            throw new NullPointerException("MysqlQueryProvider querySql is null.");
        }
        if (null == dataMapper) {
            throw new NullPointerException("MysqlQueryProvider dataMapper is null.");
        }
        if (null == queryParam) {
            throw new NullPointerException("MysqlQueryProvider queryParam is null.");
        }
        if (null == queryParam.getEngineModel()) {
            throw new NullPointerException("MysqlQueryProvider queryParam.EngineModel is null.");
        }
        if (null == queryParam.getConnectionKey()) {
            throw new NullPointerException("MysqlQueryProvider queryParam.ConnectionKey is null.");
        }
        if (!EngineModel.MYSQL.equals(queryParam.getEngineModel())) {
            throw new UnsupportedOperationException("MysqlQueryProvider Not Support EngineModel "
                    + queryParam.getEngineModel().getModelName());
        }
    }

}
