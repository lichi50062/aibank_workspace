/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.ibm.tw.ibmb.biz.component.error;

import java.lang.reflect.ParameterizedType;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;

import org.springframework.http.ResponseEntity;

public interface ServiceExceptionHandler<T extends Throwable> {

    @SuppressWarnings("unchecked")
    public default boolean canHandle(Throwable t) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        return clazz.isAssignableFrom(t.getClass());
    }

    public ResponseEntity<BaseServiceResponse<ErrorStatus>> doHandle(Throwable t);
}
