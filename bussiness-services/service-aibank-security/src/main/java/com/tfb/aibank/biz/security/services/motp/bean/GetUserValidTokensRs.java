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
package com.tfb.aibank.biz.security.services.motp.bean;

// @formatter:off
/**
 * @(#)GetUserValidTokensRs.java
 * 
 * <p>Description:全景MOTP - API介接服務 - GetUserValidTokens RS</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/10, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class GetUserValidTokensRs extends MotpRs {

    /** 回應代碼 */
    private String code;

    /** 回應訊息 */
    private String message;

    /** data */
    private GetUserValidTokensDataRs data;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the data
     */
    public GetUserValidTokensDataRs getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(GetUserValidTokensDataRs data) {
        this.data = data;
    }

}
