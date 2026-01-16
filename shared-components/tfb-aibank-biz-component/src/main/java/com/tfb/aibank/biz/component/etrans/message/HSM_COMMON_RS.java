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
package com.tfb.aibank.biz.component.etrans.message;

import java.math.BigDecimal;

import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)HSM_COMMON_RS.java
 * 
 * <p>Description:e化繳費網 晶片卡前置系統 電文回傳共用 Value Object HSM2262 HSM2263 HSM2562 HSM2563 回傳RS , 欄位都相同</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/16, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class HSM_COMMON_RS {

    private String RS_HEAD_TRACE_NO = "";
    private String RS_MSG_TYPE = "";
    private String RS_BIT_MAP_CONFIG = "";
    private String RS_ACCT_NO_LEN = "";
    private String RS_ACCT_NO = "";
    private String RS_HANDLE_CODE = "";
    private String RS_AMOUNT = "";
    private String RS_TX_DATE_TIME = "";
    private String RS_TRACE_NO = "";
    private String RS_RESP_CODE = "";
    private String RS_TERM_NO = "";
    private String RS_CHANNEL_ID = "";
    private String RS_DATA_LEN = "";
    private String RS_STAN = "";
    private String RS_FESC_RESP_CODE = "";
    private String RS_BUSINESS_DATE = "";
    private String RS_AVAIL_BALANCE = "";
    private String RS_ACCT_BALANCE = "";
    private String RS_PROMOTE_MSG = "";
    private String RS_TX_FEE = "";
    private String RS_REC_FEE = "";
    private String RS_IN_ACCT = "";
    private String RS_BATCH_DATA_LEN = "";
    private String RS_BATCH_NO = "";
    private String RS_VER_NO = "";

    /**
     * 030813 -> 1030813，表示民國年 103/08/13
     * 
     * @return
     */
    public String getRS_BUSINESS_DATE_AFT_FMT() {
        if (StringUtils.isNotBlank(RS_BUSINESS_DATE)) {
            return "1" + RS_BUSINESS_DATE;
        }
        else {
            return RS_BUSINESS_DATE;
        }
    }

    public BigDecimal getRS_AVAIL_BALANCE_AFT_FMT() {
        if (StringUtils.isBlank(RS_AVAIL_BALANCE)) {
            return BigDecimal.ZERO;
        }
        else {
            return new BigDecimal(RS_AVAIL_BALANCE).divide(new BigDecimal(100));
        }
    }

    public BigDecimal getRS_ACCT_BALANCE_AFT_FMT() {
        if (StringUtils.isBlank(RS_ACCT_BALANCE)) {
            return BigDecimal.ZERO;
        }
        else {
            return new BigDecimal(RS_ACCT_BALANCE).divide(new BigDecimal(100));
        }
    }

    public String getRS_HEAD_TRACE_NO() {
        return RS_HEAD_TRACE_NO;
    }

    public void setRS_HEAD_TRACE_NO(String rS_HEAD_TRACE_NO) {
        RS_HEAD_TRACE_NO = rS_HEAD_TRACE_NO;
    }

    public String getRS_MSG_TYPE() {
        return RS_MSG_TYPE;
    }

    public void setRS_MSG_TYPE(String rS_MSG_TYPE) {
        RS_MSG_TYPE = rS_MSG_TYPE;
    }

    public String getRS_BIT_MAP_CONFIG() {
        return RS_BIT_MAP_CONFIG;
    }

    public void setRS_BIT_MAP_CONFIG(String rS_BIT_MAP_CONFIG) {
        RS_BIT_MAP_CONFIG = rS_BIT_MAP_CONFIG;
    }

    public String getRS_ACCT_NO_LEN() {
        return RS_ACCT_NO_LEN;
    }

    public void setRS_ACCT_NO_LEN(String rS_ACCT_NO_LEN) {
        RS_ACCT_NO_LEN = rS_ACCT_NO_LEN;
    }

    public String getRS_ACCT_NO() {
        return RS_ACCT_NO;
    }

    public void setRS_ACCT_NO(String rS_ACCT_NO) {
        RS_ACCT_NO = rS_ACCT_NO;
    }

    public String getRS_HANDLE_CODE() {
        return RS_HANDLE_CODE;
    }

    public void setRS_HANDLE_CODE(String rS_HANDLE_CODE) {
        RS_HANDLE_CODE = rS_HANDLE_CODE;
    }

    public String getRS_AMOUNT() {
        return RS_AMOUNT;
    }

    public void setRS_AMOUNT(String rS_AMOUNT) {
        RS_AMOUNT = rS_AMOUNT;
    }

    public String getRS_TX_DATE_TIME() {
        return RS_TX_DATE_TIME;
    }

    public void setRS_TX_DATE_TIME(String rS_TX_DATE_TIME) {
        RS_TX_DATE_TIME = rS_TX_DATE_TIME;
    }

    public String getRS_TRACE_NO() {
        return RS_TRACE_NO;
    }

    public void setRS_TRACE_NO(String rS_TRACE_NO) {
        RS_TRACE_NO = rS_TRACE_NO;
    }

    public String getRS_RESP_CODE() {
        if (null == RS_RESP_CODE) {
            return null;
        }
        else {
            return RS_RESP_CODE.trim();
        }
    }

    public void setRS_RESP_CODE(String rS_RESP_CODE) {
        RS_RESP_CODE = rS_RESP_CODE;
    }

    public String getRS_TERM_NO() {
        return RS_TERM_NO;
    }

    public void setRS_TERM_NO(String rS_TERM_NO) {
        RS_TERM_NO = rS_TERM_NO;
    }

    public String getRS_CHANNEL_ID() {
        return RS_CHANNEL_ID;
    }

    public void setRS_CHANNEL_ID(String rS_CHANNEL_ID) {
        RS_CHANNEL_ID = rS_CHANNEL_ID;
    }

    public String getRS_DATA_LEN() {
        return RS_DATA_LEN;
    }

    public void setRS_DATA_LEN(String rS_DATA_LEN) {
        RS_DATA_LEN = rS_DATA_LEN;
    }

    public String getRS_STAN() {
        return RS_STAN;
    }

    public void setRS_STAN(String rS_STAN) {
        RS_STAN = rS_STAN;
    }

    public String getRS_FESC_RESP_CODE() {
        if (null == RS_FESC_RESP_CODE) {
            return null;
        }
        else {
            return RS_FESC_RESP_CODE.trim();
        }
    }

    public void setRS_FESC_RESP_CODE(String rS_FESC_RESP_CODE) {
        RS_FESC_RESP_CODE = rS_FESC_RESP_CODE;
    }

    public String getRS_BUSINESS_DATE() {
        return RS_BUSINESS_DATE;
    }

    public void setRS_BUSINESS_DATE(String rS_BUSINESS_DATE) {
        RS_BUSINESS_DATE = rS_BUSINESS_DATE;
    }

    public String getRS_AVAIL_BALANCE() {
        return RS_AVAIL_BALANCE;
    }

    public void setRS_AVAIL_BALANCE(String rS_AVAIL_BALANCE) {
        RS_AVAIL_BALANCE = rS_AVAIL_BALANCE;
    }

    public String getRS_ACCT_BALANCE() {
        return RS_ACCT_BALANCE;
    }

    public void setRS_ACCT_BALANCE(String rS_ACCT_BALANCE) {
        RS_ACCT_BALANCE = rS_ACCT_BALANCE;
    }

    public String getRS_PROMOTE_MSG() {
        return RS_PROMOTE_MSG;
    }

    public void setRS_PROMOTE_MSG(String rS_PROMOTE_MSG) {
        RS_PROMOTE_MSG = rS_PROMOTE_MSG;
    }

    public String getRS_TX_FEE() {
        return RS_TX_FEE;
    }

    public void setRS_TX_FEE(String rS_TX_FEE) {
        RS_TX_FEE = rS_TX_FEE;
    }

    public String getRS_REC_FEE() {
        return RS_REC_FEE;
    }

    public void setRS_REC_FEE(String rS_REC_FEE) {
        RS_REC_FEE = rS_REC_FEE;
    }

    public String getRS_IN_ACCT() {
        return RS_IN_ACCT;
    }

    public void setRS_IN_ACCT(String rS_IN_ACCT) {
        RS_IN_ACCT = rS_IN_ACCT;
    }

    public String getRS_BATCH_DATA_LEN() {
        return RS_BATCH_DATA_LEN;
    }

    public void setRS_BATCH_DATA_LEN(String rS_BATCH_DATA_LEN) {
        RS_BATCH_DATA_LEN = rS_BATCH_DATA_LEN;
    }

    public String getRS_BATCH_NO() {
        return RS_BATCH_NO;
    }

    public void setRS_BATCH_NO(String rS_BATCH_NO) {
        RS_BATCH_NO = rS_BATCH_NO;
    }

    public String getRS_VER_NO() {
        return RS_VER_NO;
    }

    public void setRS_VER_NO(String rS_VER_NO) {
        RS_VER_NO = rS_VER_NO;
    }

}
