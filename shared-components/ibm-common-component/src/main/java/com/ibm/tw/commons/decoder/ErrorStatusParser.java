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
package com.ibm.tw.commons.decoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;

public class ErrorStatusParser {
    private static final Log log = LogFactory.getLog(ErrorStatusParser.class);

    private ErrorStatusParser() {
    }

    public static ErrorStatus parse(String errorMessage) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        ErrorStatus errorStatus = null;
        try {
            BaseServiceResponse<?> res = mapper.readValue(errorMessage, BaseServiceResponse.class);
            errorStatus = res.getStatus();
        }
        catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            // 解析失敗處裡
            String errorLogMsg = "Json convert to ErrorStatus failed (" + errorMessage + ")";
            log.error(errorLogMsg, e);
            errorStatus = new ErrorStatus(ServiceErrorDecoder.FEIGN_CLIENT_SYSTEM_ID, e.getClass().getName(), SeverityType.ERROR, errorLogMsg);
        }

        return errorStatus;
    }

}
