/*
 * ===========================================================================
 * 
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.ibm.tw.ibmb.component.context;

// @formatter:off
/**
 * @(#)MDCKey.java
 * 
 * <p>Description:放入 MDC 的 key 值，都用小寫，因為利用 http header 一路傳到 biz service 後會變小寫</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/25, Horance Chou	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum MDCKey {

    sessionid("spring http session id (x-auth-token)", false),

    channelid("channel id", false),

    trackingid("每個 request 的 id", false),

    clientip("client ip", false),

    frompage("請求當下的頁面代碼", false),

    deviceid("device id", false),

    seed("seed", false),

    custid("custID", true),

    springinstanceid("spring 服務名稱 (instance id)", false),

    pageid("請求發起交易頁面", false),

    locale("app locale", false),

    languagetag("app locale(language tag)", false),

    userid("user id", true),

    httpMethod("httpMethod 由 log filter 填入", false),

    requestURI("requestURI 由 log filter 填入", false),

    httpStatus("http status 由 log filter 填入", false),

    traceId("交易存取紀錄追蹤編號", false),

    clientport("client port", false),

    eaichannel("EAI Channel", false),

    appVersion("X-App-Version header", false),

    dup("誤別碼", false);

    private String memo;

    private boolean needMask;

    MDCKey(String memo, boolean needMask) {
        this.memo = memo;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    public boolean isNeedMask() {
        return needMask;
    }

}
