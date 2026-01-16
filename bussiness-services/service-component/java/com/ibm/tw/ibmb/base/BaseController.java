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
package com.ibm.tw.ibmb.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.biz.component.error.ErrorStatusHandler;
import com.ibm.tw.ibmb.biz.component.error.ServiceExceptionHandler;
import com.ibm.tw.ibmb.util.ValidateParamUtils;

@RestController
@Validated
public class BaseController implements InitializingBean {

    protected static IBLog logger = IBLog.getLog(BaseController.class);

    @Autowired
    private ServiceExceptionHandler<?>[] handlers;

    private void registerExceptionHandler() {
        for (ServiceExceptionHandler<?> handler : handlers) {
            ErrorStatusHandler.registerExceptionHandler(handler);
        }
    }

    /**
     * exceptionHandler
     * 
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseServiceResponse<ErrorStatus>> handleException(Exception e) {
        logger.error("handleException", e);
        return ErrorStatusHandler.createErrorStatus(e);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.registerExceptionHandler();
    }

    /**
     * 控制 Spring 數據綁定行為 - for Fortify - Mass Assignment: Insecure Binder Configuration
     * 
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields(new String[] {});
    }

    /**
     * [FORTIFY] Access Control: Database
     */
    protected <T> T accessControl(T obj, Class<T> clazz) {
        try {
            String objStr = JsonUtils.getJson(obj);
            String validateObjStr = ValidateParamUtils.validParam(objStr);
            return JsonUtils.getObject(validateObjStr, clazz);
        }
        catch (Exception e) {
            logger.info(e.getMessage(), e);
        }
        return obj;
    }

    /**
     * [FORTIFY] Access Control: Database
     */
    public static <T> List<T> accessControl(List<T> objs, Class<T> clazz) {
        try {
            List<T> validatedObjs = new ArrayList<>(objs.size());

            for (T obj : objs) {
                String objStr = JsonUtils.getJson(obj);
                String validateObjStr = ValidateParamUtils.validParam(objStr);
                T validatedObj = JsonUtils.getObject(validateObjStr, clazz);
                validatedObjs.add(validatedObj);
            }
            return validatedObjs;
        }
        catch (Exception e) {
            logger.info(e.getMessage(), e);
        }
        return objs;
    }

}
