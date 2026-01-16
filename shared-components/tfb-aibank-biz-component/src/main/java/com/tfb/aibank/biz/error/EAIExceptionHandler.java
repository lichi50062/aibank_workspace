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
package com.tfb.aibank.biz.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.base.Constants;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.biz.component.error.ServiceExceptionHandler;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.PibEaiErrorCode;

// @formatter:off
/**
 * @(#)EAIExceptionHandler.java
 * 
 * <p>Description:EAIExceptionHandler handler</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/26, Horance Chou
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class EAIExceptionHandler implements ServiceExceptionHandler<EAIException> {

    @Override
    public ResponseEntity<BaseServiceResponse<ErrorStatus>> doHandle(Throwable t) {

        // 其它 EAIException
        EAIException eaiException = (EAIException) t;
        if (eaiException.getErrorCode() == PibEaiErrorCode.TIMEOUT_ERROR) {
            ErrorStatus errorStatus = CommonErrorCode.HOST_TIME_OUT.getError();
            // 勿傳送 HttpStatus.SERVICE_UNAVAILABLE，因為當因為其他因素造成的 SERVICE_UNAVAILABLE ，會送出 HTML 格式的 White Page Error，使得更前端的處理程式失敗。
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseServiceResponse.of(errorStatus, null));
        }

        Throwable cause = ExceptionUtils.getRootCause(t);
        if (cause instanceof EAIException) {
            eaiException = (EAIException) t;
        }
        ErrorStatus status = new ErrorStatus(IBSystemId.AI.getSystemId(), eaiException.getErrorCode().getError().getErrorCode(), SeverityType.ERROR, eaiException.getErrorCode().getError().getErrorDesc());
        status.setExtraCode(eaiException.getExtraCode());

        if (StringUtils.isNotBlank(eaiException.getExtraCode())) {
            Map<String, String> extras = new HashMap<>();
            extras.put(Constants.EXTRA_CODE_REFERENCE, eaiException.getExtraCode());
            status.setExtras(extras);
        }
        return ResponseEntity.status(status.getHttpStatus()).body(BaseServiceResponse.of(status, null));
    }
}
