package com.xishui.beeger.datap.model;

public class Result<T> {

    private T data;
    private boolean isSuccess;
    private String errorCode;
    private String description;


    public static <T> Result<T> create() {
        return new <T>Result();
    }

    public static <T> Result<T> ofSuccess(T data) {
        Result<T> result = create();
        result.setData(data);
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> ofFail(String errorCode, String description) {
        Result<T> result = create();
        result.setSuccess(false);
        result.setErrorCode(errorCode);
        result.setDescription(description);
        return result;
    }

    public static <T> Result<T> ofFailure(String description) {
        Result<T> result = create();
        result.setSuccess(false);
        result.setDescription(description);
        return result;
    }

    public static void check(Result<?> result) throws Exception {
        if (null == result) {
            throw new NullPointerException("结果为空.");
        }
        if (!result.isSuccess()) {
            throw new IllegalStateException("操作失败：" + result.getDescription());
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
