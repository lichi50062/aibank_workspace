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
package com.tfb.aibank.common.model;

import java.util.Date;

import com.tfb.aibank.common.model.TxHeadRq;

// @formatter:off
/**
 * @(#)FundTransDetailReq.java
 * 
 * <p>Description:NFEE082/AFEE082 基金交易明細查詢-明細 上送資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/29, Charlie Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FundTransDetailReq extends TxHeadRq {

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
    /** 查詢類別 */
    private String type;
    /** 生效日期 */
    private Date trscDate;
    /** 憑證號碼 */
    private String eviNum;
    /** 轉出基金代號 */
    private String fundNO;
    /** 轉入基金代號 */
    private String inFundNO;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTrscDate() {
        return trscDate;
    }

    public void setTrscDate(Date trscDate) {
        this.trscDate = trscDate;
    }

    public String getEviNum() {
        return eviNum;
    }

    public void setEviNum(String eviNum) {
        this.eviNum = eviNum;
    }

    public String getFundNO() {
        return fundNO;
    }

    public void setFundNO(String fundNO) {
        this.fundNO = fundNO;
    }

    public String getInFundNO() {
        return inFundNO;
    }

    public void setInFundNO(String inFundNO) {
        this.inFundNO = inFundNO;
    }

}
