package com.xishui.beeger.datap.model.compute;

public class ComputeDescriptionMethod {
    private String computeMethodName;
    private String realMethodName;
    private String computeMethodDescription;
    private String computeRequestParams;
    private String computeResponseValue;

    public String getComputeMethodName() {
        return computeMethodName;
    }

    public void setComputeMethodName(String computeMethodName) {
        this.computeMethodName = computeMethodName;
    }

    public String getRealMethodName() {
        return realMethodName;
    }

    public void setRealMethodName(String realMethodName) {
        this.realMethodName = realMethodName;
    }

    public String getComputeMethodDescription() {
        return computeMethodDescription;
    }

    public void setComputeMethodDescription(String computeMethodDescription) {
        this.computeMethodDescription = computeMethodDescription;
    }

    public String getComputeRequestParams() {
        return computeRequestParams;
    }

    public void setComputeRequestParams(String computeRequestParams) {
        this.computeRequestParams = computeRequestParams;
    }

    public String getComputeResponseValue() {
        return computeResponseValue;
    }

    public void setComputeResponseValue(String computeResponseValue) {
        this.computeResponseValue = computeResponseValue;
    }
}
