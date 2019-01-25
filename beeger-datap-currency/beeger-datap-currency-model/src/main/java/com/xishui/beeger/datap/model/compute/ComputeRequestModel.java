package com.xishui.beeger.datap.model.compute;


import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author xishui.hb
 * 计算请求模型，固化
 */
public class ComputeRequestModel implements Serializable{
    /**
     * 某一个计算模块
     **/
    private String computeModel;
    /**
     * 某个计算模块的方法,其实是固定的,可以不用管，只是为了扩展单个类实现多个计算功能预留
     */
    private String computeMethod = "compute";
    /**
     * 某个计算模块的请求参数 JSON格式
     */
    private String paramJSON;
    /**
     * 可以指定一个计算启动组件进行服务，不必填
     */
    private String computeIp;

    public String getComputeModel() {
        return computeModel;
    }

    public void setComputeModel(String computeModel) {
        this.computeModel = computeModel;
    }

    public String getComputeMethod() {
        return computeMethod;
    }

    public void setComputeMethod(String computeMethod) {
        this.computeMethod = computeMethod;
    }

    public String getParamJSON() {
        return paramJSON;
    }

    public void setParamJSON(String paramJSON) {
        this.paramJSON = paramJSON;
    }

    public String getComputeIp() {
        return computeIp;
    }

    public void setComputeIp(String computeIp) {
        this.computeIp = computeIp;
    }

    public String toJsonRequest(){
        return JSON.toJSONString(this);
    }

    @Override
    public String toString() {
        return "ComputeRequestModel{" +
                "computeModel='" + computeModel + '\'' +
                ", computeMethod='" + computeMethod + '\'' +
                ", paramJSON='" + paramJSON + '\'' +
                ", computeIp='" + computeIp + '\'' +
                '}';
    }
}
