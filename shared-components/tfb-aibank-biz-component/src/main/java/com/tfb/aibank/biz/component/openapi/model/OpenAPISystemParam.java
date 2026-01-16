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
package com.tfb.aibank.biz.component.openapi.model;

import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.ibmb.component.systemparam.ISystemParam;

// @formatter:off
/**
 * @(#)OpenAPISystemParam.java
 * 
 * <p>Description:OpenAPI介接系統參數</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/18, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum OpenAPISystemParam implements ISystemParam {

    OPEN_API_SERVER_URL("OpenAPI Server URL"),

    OPEN_API_AUTH_TOKEN("OpenAPI 存取識別"),

    OPEN_API_CONNECTION_TIMEOUT("OpenAPI Connection Timeout");

    /** 狀態說明 */
    private String memo;

    OpenAPISystemParam(String memo) {
        this.memo = memo;
    }

    @Override
    public IBSystemId getCategory() {
        return IBSystemId.AIBANK;
    }

    @Override
    public String getMemo() {
        return memo;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public int getPinFlag() {
        return 0;
    }

}
