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
package com.ibm.tw.commons.httpclient;

import java.io.IOException;
import java.io.InputStream;

import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import com.ibm.tw.commons.util.StringUtils;

/**
 * 客製化 HttpClientResponseHandler
 * 
 * @author Edward Tien
 */
public class CustomHttpClientResponseHandler implements HttpClientResponseHandler<CustomHttpResponse> {

    @Override
    public CustomHttpResponse handleResponse(ClassicHttpResponse response) throws HttpException, IOException {
        final HttpEntity entity = response.getEntity();
        // 取得 HTTP 狀態碼
        int code = response.getCode();
        String reasonPhrase = response.getReasonPhrase();

        // 自定義響應物件
        try (CustomHttpResponse httpResponse = new CustomHttpResponse(code, reasonPhrase)) {

            httpResponse.setEntity(entity);
            // 取得 HTTP 響應信息
            String contentType = response.getHeader("Content-Type").getValue();
            if (StringUtils.isNotBlank(contentType) && contentType.startsWith("application/pdf")) {
                // 串流資料處理
                try (InputStream streamContent = entity.getContent()) {
                    httpResponse.setStreamResponse(streamContent.readAllBytes());
                }
            }
            else {
                // 其他類型的響應信息
                String body = EntityUtils.toString(entity);
                httpResponse.setResponse(body);
            }
            return httpResponse;
        }

    }

}
