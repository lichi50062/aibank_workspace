package com.ibm.tw.ibmb.base.model;

import com.ibm.tw.commons.error.ErrorStatus;

public class BaseServiceResponse<T> {
    // Poor Style: Confusing Naming
    private ErrorStatus errorStatus = ErrorStatus.success();
    private T data;

    public BaseServiceResponse() {
        this.data = null;
    }
    
    private BaseServiceResponse(T body) {
        this.data = body;
    }

    private BaseServiceResponse(ErrorStatus errorStatus, T body) {
        this.errorStatus = errorStatus;
        this.data = body;
    }
    
    public static <V> BaseServiceResponse<V> of(V data) {
        return new BaseServiceResponse<V>(data);
    }

    public static <V> BaseServiceResponse<V> of(ErrorStatus errorStatus, V data) {
        return new BaseServiceResponse<V>(errorStatus, data);
    }

    public BaseServiceResponse<T> status(ErrorStatus errorStatus) {
        setStatus(errorStatus);
        return this;
    }
    
    public ErrorStatus getStatus() {
        return this.errorStatus;
    }

    public void setStatus(ErrorStatus errorStatus) {
        this.errorStatus = errorStatus;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
