package com.xishui.beeger.datap.model.compute;

import java.util.List;

/**
 * @author xishui
 * 一个计算能力的描述信息,包含computemodel,computemethod描述
 */
public class ComputeDescriptionModel {

    private String computeModelName;//key

    private String computeModelNameCn;

    private String computeModelDescription;

    private String computeModelClass;

    private List<ComputeDescriptionMethod> computeDescriptionMethods;

    public String getComputeModelName() {
        return computeModelName;
    }

    public void setComputeModelName(String computeModelName) {
        this.computeModelName = computeModelName;
    }

    public String getComputeModelDescription() {
        return computeModelDescription;
    }

    public void setComputeModelDescription(String computeModelDescription) {
        this.computeModelDescription = computeModelDescription;
    }

    public String getComputeModelClass() {
        return computeModelClass;
    }

    public void setComputeModelClass(String computeModelClass) {
        this.computeModelClass = computeModelClass;
    }

    public List<ComputeDescriptionMethod> getComputeDescriptionMethods() {
        return computeDescriptionMethods;
    }

    public void setComputeDescriptionMethods(List<ComputeDescriptionMethod> computeDescriptionMethods) {
        this.computeDescriptionMethods = computeDescriptionMethods;
    }

    public String getComputeModelNameCn() {
        return computeModelNameCn;
    }

    public void setComputeModelNameCn(String computeModelNameCn) {
        this.computeModelNameCn = computeModelNameCn;
    }
}
