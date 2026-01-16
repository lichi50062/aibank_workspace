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

import static org.springframework.http.ResponseEntity.status;

import java.util.LinkedHashSet;
import java.util.Set;

import org.owasp.encoder.Encode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.util.HtmlUtils;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.ValidateParamUtils;

public class ErrorStatusHandler {

    private static IBLog logger = IBLog.getLog(ErrorStatusHandler.class);

    // allow customize
    private static Set<ServiceExceptionHandler<?>> handlers = new LinkedHashSet<>();

    private ErrorStatusHandler() {

    }

    public static void registerExceptionHandler(ServiceExceptionHandler<?> handler) {
        if (logger.isDebugEnabled()) {
            logger.debug("registering {}", handler);
        }
        handlers.add(handler);
    }

    public static ResponseEntity<BaseServiceResponse<ErrorStatus>> createErrorStatus(Exception e) {
        logger.error("handling service exception:", e);
        for (ServiceExceptionHandler<?> handler : handlers) {
            if (handler.canHandle(e)) {
                return handler.doHandle(e);
            }
        }
        // 到這裡還沒被處理
        if (e instanceof AccessDeniedException) {
            ErrorStatus status = new ErrorStatus(IBSystemId.SVC.getSystemId(), "H401", SeverityType.FATAL, HtmlUtils.htmlEscape(e.getLocalizedMessage()));
            status.setHttpStatus(HttpStatus.UNAUTHORIZED);
            return status(HttpStatus.UNAUTHORIZED).body(BaseServiceResponse.of(status, null));
        }

        // 至此，不屬於 ServiceExceptionHandler 的範疇，也不是 AccessDeniedException，統一轉換成 ActionException
        ActionException aex = null;
        // 針對 XmlException 特殊處理，轉換成，避免經過 ExceptionUtils.getActionException(e) 變成 SVCX9999
        if (StringUtils.equals("XmlException", e.getClass().getSimpleName())) {
            aex = new ActionException(CommonErrorCode.XML_EXCEPTION);
        }
        else {
            aex = ExceptionUtils.getActionException(e);
        }
        // Cross-Site Scripting: Reflected - Fortify - Critical
        // 雖然資料來源皆為內部 但是級別為 Critical 使用 HtmlUtils.htmlEscape 解決
        String escapeSystemId = Encode.forHtml(aex.getSystemId());
        String escapeErrorCode = Encode.forHtml(aex.getErrorCode());
        String escapeErrorDesc = Encode.forHtml(aex.getErrorDesc());
        ErrorStatus errorStatus = createErrorStatus(escapeSystemId, escapeErrorCode, escapeErrorDesc);
        String objStr = JsonUtils.getJson(errorStatus);
        String validateObjStr = ValidateParamUtils.validParam(objStr);
        errorStatus = JsonUtils.getObject(validateObjStr, ErrorStatus.class);
        if (e instanceof RuntimeException) {
            return status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseServiceResponse.of(errorStatus, null));
        }
        else {
            // 非 RuntimeException 當作自定義錯誤資訊處理 用 400 回覆
            return status(HttpStatus.BAD_REQUEST).body(BaseServiceResponse.of(errorStatus, null));
        }
    }

    private static ErrorStatus createErrorStatus(String escapeSystemId, String escapeErrorCode, String escapeErrorDesc) {
        if (StringUtils.isAllBlank(escapeSystemId, escapeErrorCode, escapeErrorDesc)) {
            ErrorStatus status = new ErrorStatus();
            status.setSeverity(SeverityType.FATAL);
            return status;
        }
        return new ErrorStatus(escapeSystemId, escapeErrorCode, SeverityType.FATAL, escapeErrorDesc);
    }

}
