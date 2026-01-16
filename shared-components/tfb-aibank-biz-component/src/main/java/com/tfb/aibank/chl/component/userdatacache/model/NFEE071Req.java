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
package com.tfb.aibank.chl.component.userdatacache.model;

import com.tfb.aibank.common.model.TxHeadRq;

// @formatter:off
/**
 * @(#)NFEE071Req.java
 * 
 * <p>Description:NFEE071 基金OBU帳戶總覽 上送資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/31, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NFEE071Req extends TxHeadRq {

    private static final long serialVersionUID = 7711452358863982333L;

    /** 帳號 */
    private String acctId16;
    /** 密碼 */
    private String custPswd32;
    /** 客戶ID */
    private String custId;
    /** 使用者代號 */
    private String curAcctId;
    /** 戶名代號 */
    private String curAcctName;
    /** 分行別 */
    private String branchNO;
    /** 身份証ID */
    private String id;
    /** 內容 */
    private String type;

    public String getAcctId16() {
        return acctId16;
    }

    public void setAcctId16(String acctId16) {
        this.acctId16 = acctId16;
    }

    public String getCustPswd32() {
        return custPswd32;
    }

    public void setCustPswd32(String custPswd32) {
        this.custPswd32 = custPswd32;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCurAcctId() {
        return curAcctId;
    }

    public void setCurAcctId(String curAcctId) {
        this.curAcctId = curAcctId;
    }

    public String getCurAcctName() {
        return curAcctName;
    }

    public void setCurAcctName(String curAcctName) {
        this.curAcctName = curAcctName;
    }

    public String getBranchNO() {
        return branchNO;
    }

    public void setBranchNO(String branchNO) {
        this.branchNO = branchNO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
