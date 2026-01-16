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

// @formatter:off
/**
 * @(#)HSM2521.java
 * 
 * <p>Description:WebATM 晶片卡前置系統 電文格式 xml 設定檔 mapping</p>
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
public class HSM2521 {

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
    private String RQ_BANK_CODE = "";
    private String RQ_TX_SN = "";
    private String RQ_MEMO = "";
    private String RQ_IN_ACCT = "";
    private String RQ_ORDER_NO = "";
    private String RQ_STORE_NO = "";
    private String RQ_TX_DATE_TIME2 = "";
    private String RQ_IN_BANK_CODE = "";
    private String RQ_BATCH_DATA_LEN = "";
    private String RQ_BATCH_NO = "";
    private String RQ_VER_NO = "";
    private String RQ_TAC_LEN = "";
    private String RQ_TAC_DATA = "";

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
    private String RS_BATCH_DATA_LEN = "";
    private String RS_BATCH_NO = "";
    private String RS_VER_NO = "";

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

    public String getRQ_BANK_CODE() {
        return RQ_BANK_CODE;
    }

    public void setRQ_BANK_CODE(String rQ_BANK_CODE) {
        RQ_BANK_CODE = rQ_BANK_CODE;
    }

    public String getRQ_TX_SN() {
        return RQ_TX_SN;
    }

    public void setRQ_TX_SN(String rQ_TX_SN) {
        RQ_TX_SN = rQ_TX_SN;
    }

    public String getRQ_MEMO() {
        return RQ_MEMO;
    }

    public void setRQ_MEMO(String rQ_MEMO) {
        RQ_MEMO = rQ_MEMO;
    }

    public String getRQ_IN_ACCT() {
        return RQ_IN_ACCT;
    }

    public void setRQ_IN_ACCT(String rQ_IN_ACCT) {
        RQ_IN_ACCT = rQ_IN_ACCT;
    }

    public String getRQ_ORDER_NO() {
        return RQ_ORDER_NO;
    }

    public void setRQ_ORDER_NO(String rQ_ORDER_NO) {
        RQ_ORDER_NO = rQ_ORDER_NO;
    }

    public String getRQ_STORE_NO() {
        return RQ_STORE_NO;
    }

    public void setRQ_STORE_NO(String rQ_STORE_NO) {
        RQ_STORE_NO = rQ_STORE_NO;
    }

    public String getRQ_TX_DATE_TIME2() {
        return RQ_TX_DATE_TIME2;
    }

    public void setRQ_TX_DATE_TIME2(String rQ_TX_DATE_TIME2) {
        RQ_TX_DATE_TIME2 = rQ_TX_DATE_TIME2;
    }

    public String getRQ_IN_BANK_CODE() {
        return RQ_IN_BANK_CODE;
    }

    public void setRQ_IN_BANK_CODE(String rQ_IN_BANK_CODE) {
        RQ_IN_BANK_CODE = rQ_IN_BANK_CODE;
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
        return RS_RESP_CODE;
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
        return RS_FESC_RESP_CODE;
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
