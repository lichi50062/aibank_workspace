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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.code.CommonErrorCode;

@Component
public class ValidationExceptionHandler implements ServiceExceptionHandler<MethodArgumentNotValidException> {

    @Override
    public ResponseEntity<BaseServiceResponse<ErrorStatus>> doHandle(Throwable t) {
        MethodArgumentNotValidException mve = (MethodArgumentNotValidException) t;
        ErrorStatus s = new ErrorStatus(CommonErrorCode.TASK_FIELD_ERROR);
        s.setExtras(convertFieldMessages(mve.getFieldErrors()));
        return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(BaseServiceResponse.of(s, null));
    }

    private Map<String, String> convertFieldMessages(List<FieldError> fieldErrors) {
        Map<String, String> errorMap = new LinkedHashMap<>();
        fieldErrors.forEach(e -> {
            String key = e.getObjectName() + "." + e.getField();
            String fieldError = errorMap.get(key);
            String error = StringUtils.trimToEmptyEx(fieldError) + e.getDefaultMessage();
            errorMap.put(key, error);
        });
        return errorMap;
    }

}
