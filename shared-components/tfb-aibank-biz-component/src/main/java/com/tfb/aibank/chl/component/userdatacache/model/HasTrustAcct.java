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

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)NR048NReq.java
 * 
 * <p>Description:確認是否有信託帳號 上送欄位</p>
 * 
 * <p>Modify History:</p>
 * v1.0;2024/02/15;Alex PY Li	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "確認是否有信託帳號 上送欄位 RQ")
public class HasTrustAcct extends TxHeadRq {

    private static final long serialVersionUID = -3757285873065783341L;

    /** 帳號 */
    @Schema(description = "帳號")
   private String acctId16;
    /** 帳號 */
    @Schema(description = "密碼")
    private  String custPswd32;
    /** 帳號 */
    @Schema(description = "客戶ID")
    private String custIxd;
    /** 帳號 */
    @Schema(description = "使用者代號")
    private String curAcctId;
    /** 帳號 */
    @Schema(description = "戶名代號")
    private String curAcctName;
    /** 帳號 */
    @Schema(description = "信託帳號")
    private String curAcc;
    /** 帳號 */
    @Schema(description = "交易起日")
    private String startDt;
    /** 帳號 */
    @Schema(description = "交易迄日")
    private String endDt;
    /** 帳號 */
    @Schema(description = "交易類型")
    private String trustAcct;

    /**
     * @return the acctId16
     */
    public String getAcctId16() {
        return acctId16;
    }

    /**
     * @param acctId16
     *            the acctId16 to set
     */
    public void setAcctId16(String acctId16) {
        this.acctId16 = acctId16;
    }

    /**
     * @return the custPswd32
     */
    public String getCustPswd32() {
        return custPswd32;
    }

    /**
     * @param custPswd32
     *            the custPswd32 to set
     */
    public void setCustPswd32(String custPswd32) {
        this.custPswd32 = custPswd32;
    }


    /**
     * @return the custIxd
     */
    public String getCustIxd() {
        return custIxd;
    }

    /**
     * @param custIxd
     *            the custIxd to set
     */
    public void setCustIxd(String custIxd) {
        this.custIxd = custIxd;
    }

    /**
     * @return the curAcctId
     */
    public String getCurAcctId() {
        return curAcctId;
    }

    /**
     * @param curAcctId
     *            the curAcctId to set
     */
    public void setCurAcctId(String curAcctId) {
        this.curAcctId = curAcctId;
    }

    /**
     * @return the curAcctName
     */
    public String getCurAcctName() {
        return curAcctName;
    }

    /**
     * @param curAcctName
     *            the curAcctName to set
     */
    public void setCurAcctName(String curAcctName) {
        this.curAcctName = curAcctName;
    }

    /**
     * @return the curAcc
     */
    public String getCurAcc() {
        return curAcc;
    }

    /**
     * @param curAcc
     *            the curAcc to set
     */
    public void setCurAcc(String curAcc) {
        this.curAcc = curAcc;
    }

    /**
     * @return the startDt
     */
    public String getStartDt() {
        return startDt;
    }

    /**
     * @param startDt
     *            the startDt to set
     */
    public void setStartDt(String startDt) {
        this.startDt = startDt;
    }

    /**
     * @return the endDt
     */
    public String getEndDt() {
        return endDt;
    }

    /**
     * @param endDt
     *            the endDt to set
     */
    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

    /**
     * @return the trustAcct
     */
    public String getTrustAcct() {
        return trustAcct;
    }

    /**
     * @param trustAcct
     *            the trustAcct to set
     */
    public void setTrustAcct(String trustAcct) {
        this.trustAcct = trustAcct;
    }

}
