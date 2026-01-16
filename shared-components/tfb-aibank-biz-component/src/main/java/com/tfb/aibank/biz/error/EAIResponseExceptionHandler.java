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

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.base.Constants;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.biz.component.error.ServiceExceptionHandler;
import com.tfb.aibank.integration.eai.EAIResponseException;

// @formatter:off
/**
 * @(#)EAIResponseExceptionHandler.java
 * 
 * <p>Description:EAIResponseException handler</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/26, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class EAIResponseExceptionHandler implements ServiceExceptionHandler<EAIResponseException> {
    @Override
    public ResponseEntity<BaseServiceResponse<ErrorStatus>> doHandle(Throwable t) {
        EAIResponseException eaiException = (EAIResponseException) t;
        // fortify: Redundant Null Check
        ErrorStatus status = new ErrorStatus(IBSystemId.AI.getSystemId(), eaiException == null ? "X9999" : eaiException.getErrorCode(), SeverityType.ERROR, eaiException == null ? "unknown error" : eaiException.getErrorMessage());
        // 電文結果失敗，收集電文代號，供錯誤訊息處理時使用
        if (eaiException != null && StringUtils.isNotBlank(eaiException.getTxId())) {
            Map<String, String> extras = new HashMap<>();
            extras.put(Constants.SERVICE_CODE_REFERENCE, eaiException.getTxId());
            status.setExtras(extras);
        }
        return ResponseEntity.status(status.getHttpStatus()).body(BaseServiceResponse.of(status, null));
    }
}
