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

import org.apache.hc.core5.http.message.BasicClassicHttpResponse;

/**
 * 客製化 HttpResponse
 * 
 * @author Edward Tien
 */
public class CustomHttpResponse extends BasicClassicHttpResponse {

    private static final long serialVersionUID = 1L;

    public CustomHttpResponse(int code, String reasonPhrase) {
        super(code, reasonPhrase);
    }

    /** 響應信息 */
    private String response;

    /** 響應信息 for 串流 */
    private byte[] streamResponse;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public byte[] getStreamResponse() {
        return streamResponse;
    }

    public void setStreamResponse(byte[] streamResponse) {
        this.streamResponse = streamResponse;
    }

}
