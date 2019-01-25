package com.xishui.beeger.datap.plugin.api.query;

import com.alibaba.fastjson.JSON;
import com.xishui.beeger.datap.model.compute.EngineModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Data
public class QueryParam {
    private Map<Integer, Object> queryParams = new TreeMap<>();
    private EngineModel engineModel;
    private String connectionKey;
    private volatile int index = 0;

    public static QueryParam newQueryParam() {
        return new QueryParam();
    }

    public QueryParam connectionKey(String connectionKey) {
        this.connectionKey = connectionKey;
        return this;
    }

    public QueryParam engineModel(EngineModel engineModel) {
        this.engineModel = engineModel;
        return this;
    }

    public QueryParam addParam(Object value) {
        if(null == value){
            return this;
        }
        this.queryParams.putIfAbsent(index, value);
        this.index++;
        return this;
    }

//    public QueryParam addParams(Object... params) {
//        if (params.length > 0) {
//            for (int i = 0; i < params.length; i++) {
//                addParam(params[i]);
//            }
//        }
//        return this;
//    }

    public QueryParam addParams(List<Object> params) {
        if (null != params && params.size() > 0) {
            params.forEach(p -> addParam(p));
        }
        return this;
    }

    public QueryParam addStringParams(List<String> params){
        if (null != params && params.size() > 0) {
            params.forEach(p -> addParam(p));
        }
        return this;
    }

    public boolean emptyParam() {
        return queryParams.isEmpty();
    }

    public String toSortListJSONParam() {
        List<Object> values = new ArrayList<>();
        this.queryParams.forEach((k, v) -> {
            values.add(v);
        });
        return JSON.toJSONString(values);
    }

}
