/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.userdatacache.model;

import com.tfb.aibank.common.model.TxHeadRq;

// @formatter:off
/**
 * @(#)NMP8YBReq.java
 * 
 * <p>Description:電文：NMP8YB 上送資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/12, leiley 
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NMP8YBReq extends TxHeadRq {

    private static final long serialVersionUID = -8467456447651861093L;

    /** 客戶ID */
    private String custId;

    /** 戶名代號 */
    private String curAcctName;

    /** 功能選項 */
    private String func;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCurAcctName() {
        return curAcctName;
    }

    public void setCurAcctName(String curAcctName) {
        this.curAcctName = curAcctName;
    }

    /**
     * @return the func
     */
    public String getFunc() {
        return func;
    }

    /**
     * @param func
     *            the func to set
     */
    public void setFunc(String func) {
        this.func = func;
    }

}
