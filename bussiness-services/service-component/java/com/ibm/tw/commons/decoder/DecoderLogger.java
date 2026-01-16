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

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import feign.Response;

public class DecoderLogger {
    private static final Log log = LogFactory.getLog(ServiceErrorDecoder.class);

    private DecoderLogger() {
        super();
    }

    public static String logAndGetResponseBody(String methodKey, Response response) {
        String responseBodyStr = "";
        StringBuilder logMsgStringBuilder = new StringBuilder();

        // 先寫 LOG
        logMsgStringBuilder.append("\n######## Feign Client Error decoder ########")//
                .append("\nmethodKey:" + methodKey)//
                .append("\nresponse:\n" + response);//

        if (response.body() != null) {
            try {
                responseBodyStr = IOUtils.toString(response.body().asInputStream(), "UTF-8");
                logMsgStringBuilder.append("\nresponse body:" + responseBodyStr);
            }
            catch (IOException e) {
                responseBodyStr = "Feign Client Error Decoder 取得錯誤訊息 - 失敗";
                log.error(responseBodyStr);
                logMsgStringBuilder.append("\n" + responseBodyStr);
            }
        }

        logMsgStringBuilder.append("\n############################################");

        log.warn(logMsgStringBuilder);

        return responseBodyStr;
    }
}
