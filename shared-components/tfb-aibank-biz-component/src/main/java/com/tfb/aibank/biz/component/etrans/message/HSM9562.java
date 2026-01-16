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

//@formatter:off
/**
* @(#)HSM9562.java
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
//@formatter:on
public class HSM9562 {

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
    private String RQ_PAY_TYPE = "";
    private String RQ_ORDER_NO = "";
    private String RQ_STORE_NO = "";
    private String RQ_TX_DATE_TIME2 = "";
    private String RQ_IN_BANK_CODE = "";
    private String RQ_COLL_NO = "";
    private String RQ_TX_MEMO = "";
    private String RQ_TX_MEMO2 = "";
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
    private String RS_REC_FEE = "";
    private String RS_IN_ACCT = "";
    private String RS_BATCH_DATA_LEN = "";
    private String RS_BATCH_NO = "";
    private String RS_VER_NO = "";

    /**
     * 取得 rQ_HEAD_TRACE_NO
     * 
     * @return 傳回 rQ_HEAD_TRACE_NO。
     */
    public String getRQ_HEAD_TRACE_NO() {
        return RQ_HEAD_TRACE_NO;
    }

    /**
     * 設定 rQ_HEAD_TRACE_NO
     * 
     * @param rQ_HEAD_TRACE_NO
     *            要設定的 rQ_HEAD_TRACE_NO。
     */
    public void setRQ_HEAD_TRACE_NO(String rQ_HEAD_TRACE_NO) {
        RQ_HEAD_TRACE_NO = rQ_HEAD_TRACE_NO;
    }

    /**
     * 取得 rQ_MSG_TYPE
     * 
     * @return 傳回 rQ_MSG_TYPE。
     */
    public String getRQ_MSG_TYPE() {
        return RQ_MSG_TYPE;
    }

    /**
     * 設定 rQ_MSG_TYPE
     * 
     * @param rQ_MSG_TYPE
     *            要設定的 rQ_MSG_TYPE。
     */
    public void setRQ_MSG_TYPE(String rQ_MSG_TYPE) {
        RQ_MSG_TYPE = rQ_MSG_TYPE;
    }

    /**
     * 取得 rQ_BIT_MAP_CONFIG
     * 
     * @return 傳回 rQ_BIT_MAP_CONFIG。
     */
    public String getRQ_BIT_MAP_CONFIG() {
        return RQ_BIT_MAP_CONFIG;
    }

    /**
     * 設定 rQ_BIT_MAP_CONFIG
     * 
     * @param rQ_BIT_MAP_CONFIG
     *            要設定的 rQ_BIT_MAP_CONFIG。
     */
    public void setRQ_BIT_MAP_CONFIG(String rQ_BIT_MAP_CONFIG) {
        RQ_BIT_MAP_CONFIG = rQ_BIT_MAP_CONFIG;
    }

    /**
     * 取得 rQ_ACCT_NO_LEN
     * 
     * @return 傳回 rQ_ACCT_NO_LEN。
     */
    public String getRQ_ACCT_NO_LEN() {
        return RQ_ACCT_NO_LEN;
    }

    /**
     * 設定 rQ_ACCT_NO_LEN
     * 
     * @param rQ_ACCT_NO_LEN
     *            要設定的 rQ_ACCT_NO_LEN。
     */
    public void setRQ_ACCT_NO_LEN(String rQ_ACCT_NO_LEN) {
        RQ_ACCT_NO_LEN = rQ_ACCT_NO_LEN;
    }

    /**
     * 取得 rQ_ACCT_NO
     * 
     * @return 傳回 rQ_ACCT_NO。
     */
    public String getRQ_ACCT_NO() {
        return RQ_ACCT_NO;
    }

    /**
     * 設定 rQ_ACCT_NO
     * 
     * @param rQ_ACCT_NO
     *            要設定的 rQ_ACCT_NO。
     */
    public void setRQ_ACCT_NO(String rQ_ACCT_NO) {
        RQ_ACCT_NO = rQ_ACCT_NO;
    }

    /**
     * 取得 rQ_HANDLE_CODE
     * 
     * @return 傳回 rQ_HANDLE_CODE。
     */
    public String getRQ_HANDLE_CODE() {
        return RQ_HANDLE_CODE;
    }

    /**
     * 設定 rQ_HANDLE_CODE
     * 
     * @param rQ_HANDLE_CODE
     *            要設定的 rQ_HANDLE_CODE。
     */
    public void setRQ_HANDLE_CODE(String rQ_HANDLE_CODE) {
        RQ_HANDLE_CODE = rQ_HANDLE_CODE;
    }

    /**
     * 取得 rQ_AMOUNT
     * 
     * @return 傳回 rQ_AMOUNT。
     */
    public String getRQ_AMOUNT() {
        return RQ_AMOUNT;
    }

    /**
     * 設定 rQ_AMOUNT
     * 
     * @param rQ_AMOUNT
     *            要設定的 rQ_AMOUNT。
     */
    public void setRQ_AMOUNT(String rQ_AMOUNT) {
        RQ_AMOUNT = rQ_AMOUNT;
    }

    /**
     * 取得 rQ_TX_DATE_TIME
     * 
     * @return 傳回 rQ_TX_DATE_TIME。
     */
    public String getRQ_TX_DATE_TIME() {
        return RQ_TX_DATE_TIME;
    }

    /**
     * 設定 rQ_TX_DATE_TIME
     * 
     * @param rQ_TX_DATE_TIME
     *            要設定的 rQ_TX_DATE_TIME。
     */
    public void setRQ_TX_DATE_TIME(String rQ_TX_DATE_TIME) {
        RQ_TX_DATE_TIME = rQ_TX_DATE_TIME;
    }

    /**
     * 取得 rQ_TRACE_NO
     * 
     * @return 傳回 rQ_TRACE_NO。
     */
    public String getRQ_TRACE_NO() {
        return RQ_TRACE_NO;
    }

    /**
     * 設定 rQ_TRACE_NO
     * 
     * @param rQ_TRACE_NO
     *            要設定的 rQ_TRACE_NO。
     */
    public void setRQ_TRACE_NO(String rQ_TRACE_NO) {
        RQ_TRACE_NO = rQ_TRACE_NO;
    }

    /**
     * 取得 rQ_TERM_NO
     * 
     * @return 傳回 rQ_TERM_NO。
     */
    public String getRQ_TERM_NO() {
        return RQ_TERM_NO;
    }

    /**
     * 設定 rQ_TERM_NO
     * 
     * @param rQ_TERM_NO
     *            要設定的 rQ_TERM_NO。
     */
    public void setRQ_TERM_NO(String rQ_TERM_NO) {
        RQ_TERM_NO = rQ_TERM_NO;
    }

    /**
     * 取得 rQ_CHANNEL_ID
     * 
     * @return 傳回 rQ_CHANNEL_ID。
     */
    public String getRQ_CHANNEL_ID() {
        return RQ_CHANNEL_ID;
    }

    /**
     * 設定 rQ_CHANNEL_ID
     * 
     * @param rQ_CHANNEL_ID
     *            要設定的 rQ_CHANNEL_ID。
     */
    public void setRQ_CHANNEL_ID(String rQ_CHANNEL_ID) {
        RQ_CHANNEL_ID = rQ_CHANNEL_ID;
    }

    /**
     * 取得 rQ_DATA_LEN
     * 
     * @return 傳回 rQ_DATA_LEN。
     */
    public String getRQ_DATA_LEN() {
        return RQ_DATA_LEN;
    }

    /**
     * 設定 rQ_DATA_LEN
     * 
     * @param rQ_DATA_LEN
     *            要設定的 rQ_DATA_LEN。
     */
    public void setRQ_DATA_LEN(String rQ_DATA_LEN) {
        RQ_DATA_LEN = rQ_DATA_LEN;
    }

    /**
     * 取得 rQ_P_CODE
     * 
     * @return 傳回 rQ_P_CODE。
     */
    public String getRQ_P_CODE() {
        return RQ_P_CODE;
    }

    /**
     * 設定 rQ_P_CODE
     * 
     * @param rQ_P_CODE
     *            要設定的 rQ_P_CODE。
     */
    public void setRQ_P_CODE(String rQ_P_CODE) {
        RQ_P_CODE = rQ_P_CODE;
    }

    /**
     * 取得 rQ_TERM_TYPE
     * 
     * @return 傳回 rQ_TERM_TYPE。
     */
    public String getRQ_TERM_TYPE() {
        return RQ_TERM_TYPE;
    }

    /**
     * 設定 rQ_TERM_TYPE
     * 
     * @param rQ_TERM_TYPE
     *            要設定的 rQ_TERM_TYPE。
     */
    public void setRQ_TERM_TYPE(String rQ_TERM_TYPE) {
        RQ_TERM_TYPE = rQ_TERM_TYPE;
    }

    /**
     * 取得 rQ_TERM_CHK_CODE
     * 
     * @return 傳回 rQ_TERM_CHK_CODE。
     */
    public String getRQ_TERM_CHK_CODE() {
        return RQ_TERM_CHK_CODE;
    }

    /**
     * 設定 rQ_TERM_CHK_CODE
     * 
     * @param rQ_TERM_CHK_CODE
     *            要設定的 rQ_TERM_CHK_CODE。
     */
    public void setRQ_TERM_CHK_CODE(String rQ_TERM_CHK_CODE) {
        RQ_TERM_CHK_CODE = rQ_TERM_CHK_CODE;
    }

    /**
     * 取得 rQ_BANK_CODE
     * 
     * @return 傳回 rQ_BANK_CODE。
     */
    public String getRQ_BANK_CODE() {
        return RQ_BANK_CODE;
    }

    /**
     * 設定 rQ_BANK_CODE
     * 
     * @param rQ_BANK_CODE
     *            要設定的 rQ_BANK_CODE。
     */
    public void setRQ_BANK_CODE(String rQ_BANK_CODE) {
        RQ_BANK_CODE = rQ_BANK_CODE;
    }

    /**
     * 取得 rQ_TX_SN
     * 
     * @return 傳回 rQ_TX_SN。
     */
    public String getRQ_TX_SN() {
        return RQ_TX_SN;
    }

    /**
     * 設定 rQ_TX_SN
     * 
     * @param rQ_TX_SN
     *            要設定的 rQ_TX_SN。
     */
    public void setRQ_TX_SN(String rQ_TX_SN) {
        RQ_TX_SN = rQ_TX_SN;
    }

    /**
     * 取得 rQ_MEMO
     * 
     * @return 傳回 rQ_MEMO。
     */
    public String getRQ_MEMO() {
        return RQ_MEMO;
    }

    /**
     * 設定 rQ_MEMO
     * 
     * @param rQ_MEMO
     *            要設定的 rQ_MEMO。
     */
    public void setRQ_MEMO(String rQ_MEMO) {
        RQ_MEMO = rQ_MEMO;
    }

    /**
     * 取得 rQ_PAY_TYPE
     * 
     * @return 傳回 rQ_PAY_TYPE。
     */
    public String getRQ_PAY_TYPE() {
        return RQ_PAY_TYPE;
    }

    /**
     * 設定 rQ_PAY_TYPE
     * 
     * @param rQ_PAY_TYPE
     *            要設定的 rQ_PAY_TYPE。
     */
    public void setRQ_PAY_TYPE(String rQ_PAY_TYPE) {
        RQ_PAY_TYPE = rQ_PAY_TYPE;
    }

    /**
     * 取得 rQ_ORDER_NO
     * 
     * @return 傳回 rQ_ORDER_NO。
     */
    public String getRQ_ORDER_NO() {
        return RQ_ORDER_NO;
    }

    /**
     * 設定 rQ_ORDER_NO
     * 
     * @param rQ_ORDER_NO
     *            要設定的 rQ_ORDER_NO。
     */
    public void setRQ_ORDER_NO(String rQ_ORDER_NO) {
        RQ_ORDER_NO = rQ_ORDER_NO;
    }

    /**
     * 取得 rQ_STORE_NO
     * 
     * @return 傳回 rQ_STORE_NO。
     */
    public String getRQ_STORE_NO() {
        return RQ_STORE_NO;
    }

    /**
     * 設定 rQ_STORE_NO
     * 
     * @param rQ_STORE_NO
     *            要設定的 rQ_STORE_NO。
     */
    public void setRQ_STORE_NO(String rQ_STORE_NO) {
        RQ_STORE_NO = rQ_STORE_NO;
    }

    /**
     * 取得 rQ_TX_DATE_TIME2
     * 
     * @return 傳回 rQ_TX_DATE_TIME2。
     */
    public String getRQ_TX_DATE_TIME2() {
        return RQ_TX_DATE_TIME2;
    }

    /**
     * 設定 rQ_TX_DATE_TIME2
     * 
     * @param rQ_TX_DATE_TIME2
     *            要設定的 rQ_TX_DATE_TIME2。
     */
    public void setRQ_TX_DATE_TIME2(String rQ_TX_DATE_TIME2) {
        RQ_TX_DATE_TIME2 = rQ_TX_DATE_TIME2;
    }

    /**
     * 取得 rQ_IN_BANK_CODE
     * 
     * @return 傳回 rQ_IN_BANK_CODE。
     */
    public String getRQ_IN_BANK_CODE() {
        return RQ_IN_BANK_CODE;
    }

    /**
     * 設定 rQ_IN_BANK_CODE
     * 
     * @param rQ_IN_BANK_CODE
     *            要設定的 rQ_IN_BANK_CODE。
     */
    public void setRQ_IN_BANK_CODE(String rQ_IN_BANK_CODE) {
        RQ_IN_BANK_CODE = rQ_IN_BANK_CODE;
    }

    /**
     * 取得 rQ_COLL_NO
     * 
     * @return 傳回 rQ_COLL_NO。
     */
    public String getRQ_COLL_NO() {
        return RQ_COLL_NO;
    }

    /**
     * 設定 rQ_COLL_NO
     * 
     * @param rQ_COLL_NO
     *            要設定的 rQ_COLL_NO。
     */
    public void setRQ_COLL_NO(String rQ_COLL_NO) {
        RQ_COLL_NO = rQ_COLL_NO;
    }

    /**
     * 取得 rQ_TX_MEMO
     * 
     * @return 傳回 rQ_TX_MEMO。
     */
    public String getRQ_TX_MEMO() {
        return RQ_TX_MEMO;
    }

    /**
     * 設定 rQ_TX_MEMO
     * 
     * @param rQ_TX_MEMO
     *            要設定的 rQ_TX_MEMO。
     */
    public void setRQ_TX_MEMO(String rQ_TX_MEMO) {
        RQ_TX_MEMO = rQ_TX_MEMO;
    }

    /**
     * 取得 rQ_TX_MEMO2
     * 
     * @return 傳回 rQ_TX_MEMO2。
     */
    public String getRQ_TX_MEMO2() {
        return RQ_TX_MEMO2;
    }

    /**
     * 設定 rQ_TX_MEMO2
     * 
     * @param rQ_TX_MEMO2
     *            要設定的 rQ_TX_MEMO2。
     */
    public void setRQ_TX_MEMO2(String rQ_TX_MEMO2) {
        RQ_TX_MEMO2 = rQ_TX_MEMO2;
    }

    /**
     * 取得 rQ_BATCH_DATA_LEN
     * 
     * @return 傳回 rQ_BATCH_DATA_LEN。
     */
    public String getRQ_BATCH_DATA_LEN() {
        return RQ_BATCH_DATA_LEN;
    }

    /**
     * 設定 rQ_BATCH_DATA_LEN
     * 
     * @param rQ_BATCH_DATA_LEN
     *            要設定的 rQ_BATCH_DATA_LEN。
     */
    public void setRQ_BATCH_DATA_LEN(String rQ_BATCH_DATA_LEN) {
        RQ_BATCH_DATA_LEN = rQ_BATCH_DATA_LEN;
    }

    /**
     * 取得 rQ_BATCH_NO
     * 
     * @return 傳回 rQ_BATCH_NO。
     */
    public String getRQ_BATCH_NO() {
        return RQ_BATCH_NO;
    }

    /**
     * 設定 rQ_BATCH_NO
     * 
     * @param rQ_BATCH_NO
     *            要設定的 rQ_BATCH_NO。
     */
    public void setRQ_BATCH_NO(String rQ_BATCH_NO) {
        RQ_BATCH_NO = rQ_BATCH_NO;
    }

    /**
     * 取得 rQ_VER_NO
     * 
     * @return 傳回 rQ_VER_NO。
     */
    public String getRQ_VER_NO() {
        return RQ_VER_NO;
    }

    /**
     * 設定 rQ_VER_NO
     * 
     * @param rQ_VER_NO
     *            要設定的 rQ_VER_NO。
     */
    public void setRQ_VER_NO(String rQ_VER_NO) {
        RQ_VER_NO = rQ_VER_NO;
    }

    /**
     * 取得 rQ_TAC_LEN
     * 
     * @return 傳回 rQ_TAC_LEN。
     */
    public String getRQ_TAC_LEN() {
        return RQ_TAC_LEN;
    }

    /**
     * 設定 rQ_TAC_LEN
     * 
     * @param rQ_TAC_LEN
     *            要設定的 rQ_TAC_LEN。
     */
    public void setRQ_TAC_LEN(String rQ_TAC_LEN) {
        RQ_TAC_LEN = rQ_TAC_LEN;
    }

    /**
     * 取得 rQ_TAC_DATA
     * 
     * @return 傳回 rQ_TAC_DATA。
     */
    public String getRQ_TAC_DATA() {
        return RQ_TAC_DATA;
    }

    /**
     * 設定 rQ_TAC_DATA
     * 
     * @param rQ_TAC_DATA
     *            要設定的 rQ_TAC_DATA。
     */
    public void setRQ_TAC_DATA(String rQ_TAC_DATA) {
        RQ_TAC_DATA = rQ_TAC_DATA;
    }

    /**
     * 取得 rS_HEAD_TRACE_NO
     * 
     * @return 傳回 rS_HEAD_TRACE_NO。
     */
    public String getRS_HEAD_TRACE_NO() {
        return RS_HEAD_TRACE_NO;
    }

    /**
     * 設定 rS_HEAD_TRACE_NO
     * 
     * @param rS_HEAD_TRACE_NO
     *            要設定的 rS_HEAD_TRACE_NO。
     */
    public void setRS_HEAD_TRACE_NO(String rS_HEAD_TRACE_NO) {
        RS_HEAD_TRACE_NO = rS_HEAD_TRACE_NO;
    }

    /**
     * 取得 rS_MSG_TYPE
     * 
     * @return 傳回 rS_MSG_TYPE。
     */
    public String getRS_MSG_TYPE() {
        return RS_MSG_TYPE;
    }

    /**
     * 設定 rS_MSG_TYPE
     * 
     * @param rS_MSG_TYPE
     *            要設定的 rS_MSG_TYPE。
     */
    public void setRS_MSG_TYPE(String rS_MSG_TYPE) {
        RS_MSG_TYPE = rS_MSG_TYPE;
    }

    /**
     * 取得 rS_BIT_MAP_CONFIG
     * 
     * @return 傳回 rS_BIT_MAP_CONFIG。
     */
    public String getRS_BIT_MAP_CONFIG() {
        return RS_BIT_MAP_CONFIG;
    }

    /**
     * 設定 rS_BIT_MAP_CONFIG
     * 
     * @param rS_BIT_MAP_CONFIG
     *            要設定的 rS_BIT_MAP_CONFIG。
     */
    public void setRS_BIT_MAP_CONFIG(String rS_BIT_MAP_CONFIG) {
        RS_BIT_MAP_CONFIG = rS_BIT_MAP_CONFIG;
    }

    /**
     * 取得 rS_ACCT_NO_LEN
     * 
     * @return 傳回 rS_ACCT_NO_LEN。
     */
    public String getRS_ACCT_NO_LEN() {
        return RS_ACCT_NO_LEN;
    }

    /**
     * 設定 rS_ACCT_NO_LEN
     * 
     * @param rS_ACCT_NO_LEN
     *            要設定的 rS_ACCT_NO_LEN。
     */
    public void setRS_ACCT_NO_LEN(String rS_ACCT_NO_LEN) {
        RS_ACCT_NO_LEN = rS_ACCT_NO_LEN;
    }

    /**
     * 取得 rS_ACCT_NO
     * 
     * @return 傳回 rS_ACCT_NO。
     */
    public String getRS_ACCT_NO() {
        return RS_ACCT_NO;
    }

    /**
     * 設定 rS_ACCT_NO
     * 
     * @param rS_ACCT_NO
     *            要設定的 rS_ACCT_NO。
     */
    public void setRS_ACCT_NO(String rS_ACCT_NO) {
        RS_ACCT_NO = rS_ACCT_NO;
    }

    /**
     * 取得 rS_HANDLE_CODE
     * 
     * @return 傳回 rS_HANDLE_CODE。
     */
    public String getRS_HANDLE_CODE() {
        return RS_HANDLE_CODE;
    }

    /**
     * 設定 rS_HANDLE_CODE
     * 
     * @param rS_HANDLE_CODE
     *            要設定的 rS_HANDLE_CODE。
     */
    public void setRS_HANDLE_CODE(String rS_HANDLE_CODE) {
        RS_HANDLE_CODE = rS_HANDLE_CODE;
    }

    /**
     * 取得 rS_AMOUNT
     * 
     * @return 傳回 rS_AMOUNT。
     */
    public String getRS_AMOUNT() {
        return RS_AMOUNT;
    }

    /**
     * 設定 rS_AMOUNT
     * 
     * @param rS_AMOUNT
     *            要設定的 rS_AMOUNT。
     */
    public void setRS_AMOUNT(String rS_AMOUNT) {
        RS_AMOUNT = rS_AMOUNT;
    }

    /**
     * 取得 rS_TX_DATE_TIME
     * 
     * @return 傳回 rS_TX_DATE_TIME。
     */
    public String getRS_TX_DATE_TIME() {
        return RS_TX_DATE_TIME;
    }

    /**
     * 設定 rS_TX_DATE_TIME
     * 
     * @param rS_TX_DATE_TIME
     *            要設定的 rS_TX_DATE_TIME。
     */
    public void setRS_TX_DATE_TIME(String rS_TX_DATE_TIME) {
        RS_TX_DATE_TIME = rS_TX_DATE_TIME;
    }

    /**
     * 取得 rS_TRACE_NO
     * 
     * @return 傳回 rS_TRACE_NO。
     */
    public String getRS_TRACE_NO() {
        return RS_TRACE_NO;
    }

    /**
     * 設定 rS_TRACE_NO
     * 
     * @param rS_TRACE_NO
     *            要設定的 rS_TRACE_NO。
     */
    public void setRS_TRACE_NO(String rS_TRACE_NO) {
        RS_TRACE_NO = rS_TRACE_NO;
    }

    /**
     * 取得 rS_RESP_CODE
     * 
     * @return 傳回 rS_RESP_CODE。
     */
    public String getRS_RESP_CODE() {
        return RS_RESP_CODE;
    }

    /**
     * 設定 rS_RESP_CODE
     * 
     * @param rS_RESP_CODE
     *            要設定的 rS_RESP_CODE。
     */
    public void setRS_RESP_CODE(String rS_RESP_CODE) {
        RS_RESP_CODE = rS_RESP_CODE;
    }

    /**
     * 取得 rS_TERM_NO
     * 
     * @return 傳回 rS_TERM_NO。
     */
    public String getRS_TERM_NO() {
        return RS_TERM_NO;
    }

    /**
     * 設定 rS_TERM_NO
     * 
     * @param rS_TERM_NO
     *            要設定的 rS_TERM_NO。
     */
    public void setRS_TERM_NO(String rS_TERM_NO) {
        RS_TERM_NO = rS_TERM_NO;
    }

    /**
     * 取得 rS_CHANNEL_ID
     * 
     * @return 傳回 rS_CHANNEL_ID。
     */
    public String getRS_CHANNEL_ID() {
        return RS_CHANNEL_ID;
    }

    /**
     * 設定 rS_CHANNEL_ID
     * 
     * @param rS_CHANNEL_ID
     *            要設定的 rS_CHANNEL_ID。
     */
    public void setRS_CHANNEL_ID(String rS_CHANNEL_ID) {
        RS_CHANNEL_ID = rS_CHANNEL_ID;
    }

    /**
     * 取得 rS_DATA_LEN
     * 
     * @return 傳回 rS_DATA_LEN。
     */
    public String getRS_DATA_LEN() {
        return RS_DATA_LEN;
    }

    /**
     * 設定 rS_DATA_LEN
     * 
     * @param rS_DATA_LEN
     *            要設定的 rS_DATA_LEN。
     */
    public void setRS_DATA_LEN(String rS_DATA_LEN) {
        RS_DATA_LEN = rS_DATA_LEN;
    }

    /**
     * 取得 rS_STAN
     * 
     * @return 傳回 rS_STAN。
     */
    public String getRS_STAN() {
        return RS_STAN;
    }

    /**
     * 設定 rS_STAN
     * 
     * @param rS_STAN
     *            要設定的 rS_STAN。
     */
    public void setRS_STAN(String rS_STAN) {
        RS_STAN = rS_STAN;
    }

    /**
     * 取得 rS_FESC_RESP_CODE
     * 
     * @return 傳回 rS_FESC_RESP_CODE。
     */
    public String getRS_FESC_RESP_CODE() {
        return RS_FESC_RESP_CODE;
    }

    /**
     * 設定 rS_FESC_RESP_CODE
     * 
     * @param rS_FESC_RESP_CODE
     *            要設定的 rS_FESC_RESP_CODE。
     */
    public void setRS_FESC_RESP_CODE(String rS_FESC_RESP_CODE) {
        RS_FESC_RESP_CODE = rS_FESC_RESP_CODE;
    }

    /**
     * 取得 rS_BUSINESS_DATE
     * 
     * @return 傳回 rS_BUSINESS_DATE。
     */
    public String getRS_BUSINESS_DATE() {
        return RS_BUSINESS_DATE;
    }

    /**
     * 設定 rS_BUSINESS_DATE
     * 
     * @param rS_BUSINESS_DATE
     *            要設定的 rS_BUSINESS_DATE。
     */
    public void setRS_BUSINESS_DATE(String rS_BUSINESS_DATE) {
        RS_BUSINESS_DATE = rS_BUSINESS_DATE;
    }

    /**
     * 取得 rS_AVAIL_BALANCE
     * 
     * @return 傳回 rS_AVAIL_BALANCE。
     */
    public String getRS_AVAIL_BALANCE() {
        return RS_AVAIL_BALANCE;
    }

    /**
     * 設定 rS_AVAIL_BALANCE
     * 
     * @param rS_AVAIL_BALANCE
     *            要設定的 rS_AVAIL_BALANCE。
     */
    public void setRS_AVAIL_BALANCE(String rS_AVAIL_BALANCE) {
        RS_AVAIL_BALANCE = rS_AVAIL_BALANCE;
    }

    /**
     * 取得 rS_ACCT_BALANCE
     * 
     * @return 傳回 rS_ACCT_BALANCE。
     */
    public String getRS_ACCT_BALANCE() {
        return RS_ACCT_BALANCE;
    }

    /**
     * 設定 rS_ACCT_BALANCE
     * 
     * @param rS_ACCT_BALANCE
     *            要設定的 rS_ACCT_BALANCE。
     */
    public void setRS_ACCT_BALANCE(String rS_ACCT_BALANCE) {
        RS_ACCT_BALANCE = rS_ACCT_BALANCE;
    }

    /**
     * 取得 rS_PROMOTE_MSG
     * 
     * @return 傳回 rS_PROMOTE_MSG。
     */
    public String getRS_PROMOTE_MSG() {
        return RS_PROMOTE_MSG;
    }

    /**
     * 設定 rS_PROMOTE_MSG
     * 
     * @param rS_PROMOTE_MSG
     *            要設定的 rS_PROMOTE_MSG。
     */
    public void setRS_PROMOTE_MSG(String rS_PROMOTE_MSG) {
        RS_PROMOTE_MSG = rS_PROMOTE_MSG;
    }

    /**
     * 取得 rS_TX_FEE
     * 
     * @return 傳回 rS_TX_FEE。
     */
    public String getRS_TX_FEE() {
        return RS_TX_FEE;
    }

    /**
     * 設定 rS_TX_FEE
     * 
     * @param rS_TX_FEE
     *            要設定的 rS_TX_FEE。
     */
    public void setRS_TX_FEE(String rS_TX_FEE) {
        RS_TX_FEE = rS_TX_FEE;
    }

    /**
     * 取得 rS_REC_FEE
     * 
     * @return 傳回 rS_REC_FEE。
     */
    public String getRS_REC_FEE() {
        return RS_REC_FEE;
    }

    /**
     * 設定 rS_REC_FEE
     * 
     * @param rS_REC_FEE
     *            要設定的 rS_REC_FEE。
     */
    public void setRS_REC_FEE(String rS_REC_FEE) {
        RS_REC_FEE = rS_REC_FEE;
    }

    /**
     * 取得 rS_IN_ACCT
     * 
     * @return 傳回 rS_IN_ACCT。
     */
    public String getRS_IN_ACCT() {
        return RS_IN_ACCT;
    }

    /**
     * 設定 rS_IN_ACCT
     * 
     * @param rS_IN_ACCT
     *            要設定的 rS_IN_ACCT。
     */
    public void setRS_IN_ACCT(String rS_IN_ACCT) {
        RS_IN_ACCT = rS_IN_ACCT;
    }

    /**
     * 取得 rS_BATCH_DATA_LEN
     * 
     * @return 傳回 rS_BATCH_DATA_LEN。
     */
    public String getRS_BATCH_DATA_LEN() {
        return RS_BATCH_DATA_LEN;
    }

    /**
     * 設定 rS_BATCH_DATA_LEN
     * 
     * @param rS_BATCH_DATA_LEN
     *            要設定的 rS_BATCH_DATA_LEN。
     */
    public void setRS_BATCH_DATA_LEN(String rS_BATCH_DATA_LEN) {
        RS_BATCH_DATA_LEN = rS_BATCH_DATA_LEN;
    }

    /**
     * 取得 rS_BATCH_NO
     * 
     * @return 傳回 rS_BATCH_NO。
     */
    public String getRS_BATCH_NO() {
        return RS_BATCH_NO;
    }

    /**
     * 設定 rS_BATCH_NO
     * 
     * @param rS_BATCH_NO
     *            要設定的 rS_BATCH_NO。
     */
    public void setRS_BATCH_NO(String rS_BATCH_NO) {
        RS_BATCH_NO = rS_BATCH_NO;
    }

    /**
     * 取得 rS_VER_NO
     * 
     * @return 傳回 rS_VER_NO。
     */
    public String getRS_VER_NO() {
        return RS_VER_NO;
    }

    /**
     * 設定 rS_VER_NO
     * 
     * @param rS_VER_NO
     *            要設定的 rS_VER_NO。
     */
    public void setRS_VER_NO(String rS_VER_NO) {
        RS_VER_NO = rS_VER_NO;
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

}
