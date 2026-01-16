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
package com.ibm.tw.ibmb.component.context;

import java.util.HashMap;
import java.util.Map;

import com.ibm.tw.commons.util.StringUtils;

/**
 * Keep request header information in request scope
 * 
 * @author horance
 *
 */
public class ExecutionContext {
    private Map<String, String> contextMap = new HashMap<>();

    public String getSessionId() {
        return contextMap.get(MDCKey.sessionid.name());
    }

    public String getIp() {
        return contextMap.get(MDCKey.clientip.name());
    }

    public String getExternalServiceName() {
        return contextMap.get(MDCKey.springinstanceid.name());
    }

    /**
     * 取得 channel id
     * 
     * @return
     */
    public String getChannelId() {
        return contextMap.get(MDCKey.channelid.name());
    }

    public String getPageId() {
        return contextMap.get(MDCKey.pageid.name());
    }

    public String getLocale() {
        return contextMap.get(MDCKey.locale.name());
    }

    public String get(String key) {
        return contextMap.get(key);
    }

    public String get(String key, String defaultValue) {
        return StringUtils.defaultIfBlank(contextMap.get(key), defaultValue);
    }

    public void put(String key, String value) {
        this.contextMap.put(key, value);
    }

    public String getCustId() {
        return contextMap.get(MDCKey.custid.name());
    }

    public String getUserId() {
        return contextMap.get(MDCKey.userid.name());
    }

}
