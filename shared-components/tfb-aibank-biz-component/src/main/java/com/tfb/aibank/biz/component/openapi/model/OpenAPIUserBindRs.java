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

// @formatter:off
/**
 * @(#)OpenAPIUserQueryRs.java
 * 
 * <p>Description:客戶綁定 Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/29, Alex PY Li	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class OpenAPIUserBindRs implements OpenAPIRs {

    /**
     * 交易結果代碼
     */
    private String code;

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



}
