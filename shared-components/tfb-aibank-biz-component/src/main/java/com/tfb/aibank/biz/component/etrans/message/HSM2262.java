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

import com.ibm.tw.ibmb.util.IBUtils;

// @formatter:off
/**
 * @(#)HSM2262.java
 * 
 * <p>Description:e化繳費網 晶片卡前置系統 電文格式 xml 設定檔 mapping</p>
 * <p>欄位請比照 .xml 設定檔 </p>
 * <p><RECORD id="sendToHost" > 裡面的Field "RQ_" + 欄位 id = 此JavaBean的欄位名稱</p> 
 * <p><RECORD id="rcvFromHost"> 裡面的Field "RS_" + 欄位 id = 此JavaBean的欄位名稱</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/16, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class HSM2262 extends HSM_COMMON_RS {

    private String RQ_HEAD_TRACE_NO = "";
    private String RQ_MSG_TYPE = "";
    private String RQ_BIT_MAP_CONFIG = "";
    private String RQ_ACCT_NO_LEN = "";
    private String RQ_ACCT_NO = "";
    private String RQ_HANDLE_CODE = "";
    private String RQ_AMOUNT = "";
    private String RQ_TX_DATE_TIME = "";
    private String RQ_TRACE_NO = "";
    private String RQ_TERM_NO = "";
    private String RQ_CHANNEL_ID = "";
    private String RQ_DATA_LEN = "";
    private String RQ_P_CODE = "";
    private String RQ_TERM_TYPE = "";
    private String RQ_TERM_CHK_CODE = "";
    private String RQ_USER_ID = "";
    private String RQ_BANK_CODE = "";
    private String RQ_IN_BANK_CODE = "";
    private String RQ_PAY_TYPE = "";
    private String RQ_COLL_NO = "";
    private String RQ_TX_MEMO = "";
    private String RQ_BATCH_DATA_LEN = "";
    private String RQ_BATCH_NO = "";
    private String RQ_VER_NO = "";
    private String RQ_TAC_LEN = "";
    private String RQ_TAC_DATA = "";

    public String getRQ_HEAD_TRACE_NO() {
        return RQ_HEAD_TRACE_NO;
    }

    public void setRQ_HEAD_TRACE_NO(String rQ_HEAD_TRACE_NO) {
        RQ_HEAD_TRACE_NO = rQ_HEAD_TRACE_NO;
    }

    public String getRQ_MSG_TYPE() {
        return RQ_MSG_TYPE;
    }

    public void setRQ_MSG_TYPE(String rQ_MSG_TYPE) {
        RQ_MSG_TYPE = rQ_MSG_TYPE;
    }

    public String getRQ_BIT_MAP_CONFIG() {
        return RQ_BIT_MAP_CONFIG;
    }

    public void setRQ_BIT_MAP_CONFIG(String rQ_BIT_MAP_CONFIG) {
        RQ_BIT_MAP_CONFIG = rQ_BIT_MAP_CONFIG;
    }

    public String getRQ_ACCT_NO_LEN() {
        return RQ_ACCT_NO_LEN;
    }

    public void setRQ_ACCT_NO_LEN(String rQ_ACCT_NO_LEN) {
        RQ_ACCT_NO_LEN = rQ_ACCT_NO_LEN;
    }

    public String getRQ_ACCT_NO() {
        return RQ_ACCT_NO;
    }

    public void setRQ_ACCT_NO(String rQ_ACCT_NO) {
        RQ_ACCT_NO = rQ_ACCT_NO;
    }

    public String getRQ_HANDLE_CODE() {
        return RQ_HANDLE_CODE;
    }

    public void setRQ_HANDLE_CODE(String rQ_HANDLE_CODE) {
        RQ_HANDLE_CODE = rQ_HANDLE_CODE;
    }

    public String getRQ_AMOUNT() {
        return RQ_AMOUNT;
    }

    public void setRQ_AMOUNT(String rQ_AMOUNT) {
        RQ_AMOUNT = rQ_AMOUNT;
    }

    public String getRQ_TX_DATE_TIME() {
        return RQ_TX_DATE_TIME;
    }

    public void setRQ_TX_DATE_TIME(String rQ_TX_DATE_TIME) {
        RQ_TX_DATE_TIME = rQ_TX_DATE_TIME;
    }

    public String getRQ_TRACE_NO() {
        return RQ_TRACE_NO;
    }

    public void setRQ_TRACE_NO(String rQ_TRACE_NO) {
        RQ_TRACE_NO = rQ_TRACE_NO;
    }

    public String getRQ_TERM_NO() {
        return RQ_TERM_NO;
    }

    public void setRQ_TERM_NO(String rQ_TERM_NO) {
        RQ_TERM_NO = rQ_TERM_NO;
    }

    public String getRQ_CHANNEL_ID() {
        return RQ_CHANNEL_ID;
    }

    public void setRQ_CHANNEL_ID(String rQ_CHANNEL_ID) {
        RQ_CHANNEL_ID = rQ_CHANNEL_ID;
    }

    public String getRQ_DATA_LEN() {
        return RQ_DATA_LEN;
    }

    public void setRQ_DATA_LEN(String rQ_DATA_LEN) {
        RQ_DATA_LEN = rQ_DATA_LEN;
    }

    public String getRQ_P_CODE() {
        return RQ_P_CODE;
    }

    public void setRQ_P_CODE(String rQ_P_CODE) {
        RQ_P_CODE = rQ_P_CODE;
    }

    public String getRQ_TERM_TYPE() {
        return RQ_TERM_TYPE;
    }

    public void setRQ_TERM_TYPE(String rQ_TERM_TYPE) {
        RQ_TERM_TYPE = rQ_TERM_TYPE;
    }

    public String getRQ_TERM_CHK_CODE() {
        return RQ_TERM_CHK_CODE;
    }

    public void setRQ_TERM_CHK_CODE(String rQ_TERM_CHK_CODE) {
        RQ_TERM_CHK_CODE = rQ_TERM_CHK_CODE;
    }

    public String getRQ_USER_ID() {
        return RQ_USER_ID;
    }

    public void setRQ_USER_ID(String rQ_USER_ID) {
        RQ_USER_ID = rQ_USER_ID;
    }

    public String getRQ_BANK_CODE() {
        return RQ_BANK_CODE;
    }

    public void setRQ_BANK_CODE(String rQ_BANK_CODE) {
        RQ_BANK_CODE = rQ_BANK_CODE;
    }

    public String getRQ_IN_BANK_CODE() {
        return RQ_IN_BANK_CODE;
    }

    public void setRQ_IN_BANK_CODE(String rQ_IN_BANK_CODE) {
        RQ_IN_BANK_CODE = rQ_IN_BANK_CODE;
    }

    public String getRQ_PAY_TYPE() {
        return RQ_PAY_TYPE;
    }

    public void setRQ_PAY_TYPE(String rQ_PAY_TYPE) {
        RQ_PAY_TYPE = rQ_PAY_TYPE;
    }

    public String getRQ_COLL_NO() {
        return RQ_COLL_NO;
    }

    public void setRQ_COLL_NO(String rQ_COLL_NO) {
        RQ_COLL_NO = rQ_COLL_NO;
    }

    public String getRQ_TX_MEMO() {
        return RQ_TX_MEMO;
    }

    public void setRQ_TX_MEMO(String rQ_TX_MEMO) {
        RQ_TX_MEMO = rQ_TX_MEMO;
    }

    public String getRQ_BATCH_DATA_LEN() {
        return RQ_BATCH_DATA_LEN;
    }

    public void setRQ_BATCH_DATA_LEN(String rQ_BATCH_DATA_LEN) {
        RQ_BATCH_DATA_LEN = rQ_BATCH_DATA_LEN;
    }

    public String getRQ_BATCH_NO() {
        return RQ_BATCH_NO;
    }

    public void setRQ_BATCH_NO(String rQ_BATCH_NO) {
        RQ_BATCH_NO = rQ_BATCH_NO;
    }

    public String getRQ_VER_NO() {
        return RQ_VER_NO;
    }

    public void setRQ_VER_NO(String rQ_VER_NO) {
        RQ_VER_NO = rQ_VER_NO;
    }

    public String getRQ_TAC_LEN() {
        return RQ_TAC_LEN;
    }

    public void setRQ_TAC_LEN(String rQ_TAC_LEN) {
        RQ_TAC_LEN = rQ_TAC_LEN;
    }

    public String getRQ_TAC_DATA() {
        return RQ_TAC_DATA;
    }

    public void setRQ_TAC_DATA(String rQ_TAC_DATA) {
        RQ_TAC_DATA = rQ_TAC_DATA;
    }

    @Override
    public String toString() {
        return IBUtils.attribute2Str(this);
    }

}
