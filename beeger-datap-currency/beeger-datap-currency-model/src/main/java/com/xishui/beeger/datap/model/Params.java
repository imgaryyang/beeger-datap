package com.xishui.beeger.datap.model;

import com.alibaba.fastjson.JSON;
import com.xishui.beeger.datap.model.compute.EngineModel;
import com.xishui.beeger.datap.model.compute.EngineParamKeys;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public class Params {

    public static Params create() {
        return new Params();
    }

    public static ParamBuilder builder() {
        return new ParamBuilder(Params.create());
    }

    public static ParamBuilder builder(Params params) {
        return new ParamBuilder(params);
    }

    private final Map<String, String> paramMap = new HashMap<>();

    public Params addParam(String key, String value) {
        this.paramMap.put(key, value);
        return this;
    }

    public Params addParam(String key, Object value) {
        this.paramMap.put(key, JSON.toJSONString(value));
        return this;
    }

    public String getValueString(String key) {
        return this.paramMap.containsKey(key) ? this.paramMap.get(key) : null;
    }

    public Integer getValueInteger(String key) {
        return this.paramMap.containsKey(key) ? Integer.valueOf(this.paramMap.get(key)) : null;
    }

    public boolean containKey(String key) {
        return paramMap.containsKey(key);
    }

    public Double getValueDouble(String key) {
        return this.paramMap.containsKey(key) ? Double.valueOf(this.paramMap.get(key)) : null;
    }


    public Map<String, String> getParamMap() {
        return paramMap;
    }

    @Data
    public static class ParamBuilder {
        private String requestType;
        private String engineModel;
        private String connectionUrl;
        private String connectionUserName;
        private String connectionPassword;

        private String sql;

        private String param;//JSON(Map<>)
        private Params params;

        public ParamBuilder requestType(String requestType) {
            this.requestType = requestType;
            return this;
        }

        public ParamBuilder engineModel(String engineModel) {
            this.engineModel = engineModel;
            return this;
        }

        public ParamBuilder connectionUrl(String connectionUrl) {
            this.connectionUrl = connectionUrl;
            return this;
        }

        public ParamBuilder connectionUserName(String connectionUserName) {
            this.connectionUserName = connectionUserName;
            return this;
        }

        public ParamBuilder connectionPassword(String connectionPassword) {
            this.connectionPassword = connectionPassword;
            return this;
        }

        public ParamBuilder sql(String sql) {
            this.sql = sql;
            return this;
        }

        public ParamBuilder mapJsonMapParam(String mapJsonMapParam) {
            this.param = mapJsonMapParam;
            return this;
        }

        public ParamBuilder(Params params) {
            this.params = params;
        }

        public Params build() {
            if (null == params) {
                params = Params.create();
            }
            if (null != requestType) {
                params.addParam(ParamKeys.REQUEST_TYPE, requestType);
            }
            if (null != engineModel) {
                params.addParam(ParamKeys.ENGINE_MODEL, engineModel);
                if (EngineModel.KYLIN.getModelName().equals(engineModel)) {
                    if (null != connectionUrl) {
                        params.addParam(EngineParamKeys.KylinParam.KYLIN_CONNECTION_URL, connectionUrl);
                    }
                    if (null != connectionUserName) {
                        params.addParam(EngineParamKeys.KylinParam.KYLIN_CONNECTION_USERNAME, connectionUserName);
                    }
                    if (null != connectionPassword) {
                        params.addParam(EngineParamKeys.KylinParam.KYLIN_CONNECTION_PASSWORD, connectionPassword);
                    }
                }
                if (EngineModel.MYSQL.getModelName().equals(engineModel)) {
                    if (null != connectionUrl) {
                        params.addParam(EngineParamKeys.MysqlParam.MYSQL_CONNECTION_URL, connectionUrl);
                    }
                    if (null != connectionUserName) {
                        params.addParam(EngineParamKeys.MysqlParam.MYSQL_CONNECTION_USERNAME, connectionUserName);
                    }
                    if (null != connectionPassword) {
                        params.addParam(EngineParamKeys.MysqlParam.MYSQL_CONNECTION_PASSWORD, connectionPassword);
                    }
                }
            }
            if (null != sql) {
                params.addParam(EngineParamKeys.SQL, sql);
            }
            if (null != param) {
                params.addParam(EngineParamKeys.PARAMS, param);
            }
            return params;
        }
    }
}
