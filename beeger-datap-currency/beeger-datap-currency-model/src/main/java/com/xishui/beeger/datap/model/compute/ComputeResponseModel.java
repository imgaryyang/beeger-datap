package com.xishui.beeger.datap.model.compute;

import lombok.Data;

import java.io.Serializable;

@Data
public class ComputeResponseModel implements Serializable {

    private String data;

    private boolean isSuccess;

    private String description;

    public static ComputeResponseModel model() {
        return new ComputeResponseModel();
    }

    public static ComputeResponseModel ofSucess(String data) {
        ComputeResponseModel model = model();
        model.setSuccess(true);
        model.setData(data);
        return model;
    }

    public static ComputeResponseModel ofFailure(String description) {
        ComputeResponseModel model = model();
        model.setSuccess(false);
        model.setDescription(description);
        return model;
    }
}
