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
 * @(#)OpenAPIUserQueryRq.java
 * 
 * <p>Description:取消客戶綁定 Request</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/20, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class OpenAPIUserUnbindRq implements OpenAPIRq {

    @Override
    public String getPath() {
        return "/user/unbind";
    }

    /** (與手機門號不可同時空白）身分證字號，11碼，含誤別碼 */
    private String customerUid;

    /** (與身分證字不可同時空白）手機門號，10碼 */
    private String mobilePhone;

    /**
     * @return the customerUid
     */
    public String getCustomerUid() {
        return customerUid;
    }

    /**
     * @param customerUid
     *            the customerUid to set
     */
    public void setCustomerUid(String customerUid) {
        this.customerUid = customerUid;
    }

    /**
     * @return the mobilePhone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * @param mobilePhone
     *            the mobilePhone to set
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

}
