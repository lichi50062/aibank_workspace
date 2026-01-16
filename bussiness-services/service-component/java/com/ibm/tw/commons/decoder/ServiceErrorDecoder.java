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

import java.util.Map;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;

import feign.Response;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;

/**
 * 在 Spring context 中若存在 ErrorDecoder bean 時，Feign Client 會用這個bean來處理錯誤訊息。 當 Feign Client 收到的 status != 200 時，會被轉到這個別處理。 並再拋出 ServiceException 讓 Channel 進行後續的處裡。
 * 
 * @author guojun
 *
 */
public class ServiceErrorDecoder implements ErrorDecoder {

    public static final String FEIGN_CLIENT_SYSTEM_ID = "FEIGN";

    private static final Log log = LogFactory.getLog(ServiceErrorDecoder.class);

    final Decoder decoder;
    final ErrorDecoder defaultDecoder = new ErrorDecoder.Default();

    // 已經不再使用，為保持相容所以繼續保留
    public ServiceErrorDecoder(Decoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        log.error(methodKey + " Response HTTP " + response.status());

        // 這段 Channel service 的程式，耦合於 Business Service 使用的 ErrorStatusHandler，因為它只會送出以下的 400 or 500 status
        if (response.status() == 500 || response.status() == 400) {

            String responseBodyString = DecoderLogger.logAndGetResponseBody(methodKey, response);
            ErrorStatus errorStatus = ErrorStatusParser.parse(responseBodyString);

            if (StringUtils.isBlank(errorStatus.getSystemId())) {
                String errCode = String.valueOf(response.status());
                errorStatus = new ErrorStatus(ServiceErrorDecoder.FEIGN_CLIENT_SYSTEM_ID, errCode, SeverityType.ERROR, errCode + response.reason());
            }

            String systemId = errorStatus.getSystemId();
            // 讓錯誤訊包含來自哪個 Feign Client
            log.error("Feign Client: " + methodKey + ", Error: " + errorStatus.getErrorDesc());
            String errorDesc = errorStatus.getErrorDesc();
            SeverityType severity = errorStatus.getSeverity();
            String errorCode = errorStatus.getErrorCode();
            ServiceException serviceException = new ServiceException(systemId, errorDesc, severity, errorCode);
            Map<String, String> resHeaderMap = errorStatus.getExtras();
            if (resHeaderMap != null) {
                serviceException.setExtraInfo(resHeaderMap);
            }
            return serviceException;
        }
        if (response.status() == HttpStatus.BAD_GATEWAY.value()) {
            ErrorStatus sua = CommonErrorCode.SERVICE_UNAVALIBLE.getError();
            return new ServiceException(sua.getSystemId(), sua.getErrorDesc(), sua.getSeverity(), sua.getErrorCode());
        }
        // 其他 http status 直接送 F+status
        return new ServiceException(FEIGN_CLIENT_SYSTEM_ID, response.reason(), SeverityType.FATAL, "F" + response.status());
    }

}
