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
package com.tfb.aibank.common.util;

import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;

// @formatter:off
/**
 * @(#)BizExceptionUtils.java
 * 
 * <p>Description:Biz Exception 處理共用程式</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/14, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BizExceptionUtils extends ExceptionUtils {

    private static IBLog logger = IBLog.getLog(BizExceptionUtils.class);

    /**
     * fortify: Code Correctness: Hidden Method
     */
    private BizExceptionUtils() {
        // prevent create instance
    }
    
    /**
     * 將 EAIException 轉換為 ActionException
     * 
     * @param eaiException
     * 
     * @return
     */
    public static ActionException getActionException(EAIException eaiException) {
        ActionException aex = new ActionException(IBSystemId.AI.getSystemId(), eaiException.getExtraCode(), SeverityType.ERROR, eaiException.getMessage());
        return getBizActionException(aex);
    }

    /**
     * 將 EAIResponseException 轉換為 ActionException
     * 
     * @param eaiResponseException
     * 
     * @return
     */
    public static ActionException getActionException(EAIResponseException eaiResponseException) {
        ActionException aex = new ActionException(eaiResponseException.getErrorSystemId(), eaiResponseException.getErrorCode(), SeverityType.ERROR, eaiResponseException.getErrorMessage());
        return getBizActionException(aex);
    }

    /**
     * 用於未指定的 Exception
     * 
     * @param t
     * @return
     */
    public static ActionException getBizActionException(Throwable t) {
        logger.error("processing action exception: ", t);
        if (t instanceof EAIException) {
            return getActionException((EAIException) t);
        }
        else if (t instanceof EAIResponseException) {
            return getActionException((EAIResponseException) t);
        }
        else if (StringUtils.equals("XmlException", t.getClass().getSimpleName())) {
            return getBizActionException(new ActionException(CommonErrorCode.XML_EXCEPTION));
        }
        else {
            return ExceptionUtils.getActionException(t);
        }
    }

}
