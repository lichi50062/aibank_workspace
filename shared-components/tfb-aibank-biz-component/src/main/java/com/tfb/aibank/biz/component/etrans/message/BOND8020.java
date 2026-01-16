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
 * @(#)BOND8020.java
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
public class BOND8020 {

    private String RQ_MSG_TYPE = "";
    private String RQ_PROC_CODE = "";
    private String RQ_TRACE_NO = "";
    private String RQ_TXN_DID = "";
    private String RQ_TXN_SID = "";
    private String RQ_TXN_NO = "";
    private String RQ_TXN_INI_DT = "";
    private String RQ_RESP_CODE = "";
    private String RQ_SYNC_CHK_ITEM = "";
    private String RQ_AMOUNT = "";
    private String RQ_PAY_TYPE = "";
    private String RQ_CANCEL_NO = "";
    private String RQ_PAY_DEADLINE = "";
    private String RQ_ID = "";
    private String RQ_HND_CHG = "";
    private String RQ_HND_CHG_UNIT = "";
    private String RQ_BIZ_DATE = "";
    private String RQ_IN_ACCT = "";
    private String RQ_RCODE = "";
    private String RQ_TRX_NO = "";
    private String RS_MSG_TYPE = "";
    private String RS_PROC_CODE = "";
    private String RS_TRACE_NO = "";
    private String RS_TXN_DID = "";
    private String RS_TXN_SID = "";
    private String RS_TXN_NO = "";
    private String RS_TXN_INI_DT = "";
    private String RS_RESP_CODE = "";
    private String RS_SYNC_CHK_ITEM = "";
    private String RS_AMOUNT = "";
    private String RS_PAY_TYPE = "";
    private String RS_CANCEL_NO = "";
    private String RS_PAY_DEADLINE = "";
    private String RS_ID = "";
    private String RS_HND_CHG = "";
    private String RS_HND_CHG_UNIT = "";
    private String RS_BIZ_DATE = "";
    private String RS_IN_ACCT = "";
    private String RS_RCODE = "";
    private String RS_TRX_NO = "";

    public String getRQ_MSG_TYPE() {
        return RQ_MSG_TYPE;
    }

    public void setRQ_MSG_TYPE(String rQ_MSG_TYPE) {
        RQ_MSG_TYPE = rQ_MSG_TYPE;
    }

    public String getRQ_PROC_CODE() {
        return RQ_PROC_CODE;
    }

    public void setRQ_PROC_CODE(String rQ_PROC_CODE) {
        RQ_PROC_CODE = rQ_PROC_CODE;
    }

    public String getRQ_TRACE_NO() {
        return RQ_TRACE_NO;
    }

    public void setRQ_TRACE_NO(String rQ_TRACE_NO) {
        RQ_TRACE_NO = rQ_TRACE_NO;
    }

    public String getRQ_TXN_DID() {
        return RQ_TXN_DID;
    }

    public void setRQ_TXN_DID(String rQ_TXN_DID) {
        RQ_TXN_DID = rQ_TXN_DID;
    }

    public String getRQ_TXN_SID() {
        return RQ_TXN_SID;
    }

    public void setRQ_TXN_SID(String rQ_TXN_SID) {
        RQ_TXN_SID = rQ_TXN_SID;
    }

    public String getRQ_TXN_NO() {
        return RQ_TXN_NO;
    }

    public void setRQ_TXN_NO(String rQ_TXN_NO) {
        RQ_TXN_NO = rQ_TXN_NO;
    }

    public String getRQ_TXN_INI_DT() {
        return RQ_TXN_INI_DT;
    }

    public void setRQ_TXN_INI_DT(String rQ_TXN_INI_DT) {
        RQ_TXN_INI_DT = rQ_TXN_INI_DT;
    }

    public String getRQ_RESP_CODE() {
        return RQ_RESP_CODE;
    }

    public void setRQ_RESP_CODE(String rQ_RESP_CODE) {
        RQ_RESP_CODE = rQ_RESP_CODE;
    }

    public String getRQ_SYNC_CHK_ITEM() {
        return RQ_SYNC_CHK_ITEM;
    }

    public void setRQ_SYNC_CHK_ITEM(String rQ_SYNC_CHK_ITEM) {
        RQ_SYNC_CHK_ITEM = rQ_SYNC_CHK_ITEM;
    }

    public String getRQ_AMOUNT() {
        return RQ_AMOUNT;
    }

    public void setRQ_AMOUNT(String rQ_AMOUNT) {
        RQ_AMOUNT = rQ_AMOUNT;
    }

    public String getRQ_PAY_TYPE() {
        return RQ_PAY_TYPE;
    }

    public void setRQ_PAY_TYPE(String rQ_PAY_TYPE) {
        RQ_PAY_TYPE = rQ_PAY_TYPE;
    }

    public String getRQ_CANCEL_NO() {
        return RQ_CANCEL_NO;
    }

    public void setRQ_CANCEL_NO(String rQ_CANCEL_NO) {
        RQ_CANCEL_NO = rQ_CANCEL_NO;
    }

    public String getRQ_PAY_DEADLINE() {
        return RQ_PAY_DEADLINE;
    }

    public void setRQ_PAY_DEADLINE(String rQ_PAY_DEADLINE) {
        RQ_PAY_DEADLINE = rQ_PAY_DEADLINE;
    }

    public String getRQ_ID() {
        return RQ_ID;
    }

    public void setRQ_ID(String rQ_ID) {
        RQ_ID = rQ_ID;
    }

    public String getRQ_HND_CHG() {
        return RQ_HND_CHG;
    }

    public void setRQ_HND_CHG(String rQ_HND_CHG) {
        RQ_HND_CHG = rQ_HND_CHG;
    }

    public String getRQ_HND_CHG_UNIT() {
        return RQ_HND_CHG_UNIT;
    }

    public void setRQ_HND_CHG_UNIT(String rQ_HND_CHG_UNIT) {
        RQ_HND_CHG_UNIT = rQ_HND_CHG_UNIT;
    }

    public String getRQ_BIZ_DATE() {
        return RQ_BIZ_DATE;
    }

    public void setRQ_BIZ_DATE(String rQ_BIZ_DATE) {
        RQ_BIZ_DATE = rQ_BIZ_DATE;
    }

    public String getRQ_IN_ACCT() {
        return RQ_IN_ACCT;
    }

    public void setRQ_IN_ACCT(String rQ_IN_ACCT) {
        RQ_IN_ACCT = rQ_IN_ACCT;
    }

    public String getRQ_RCODE() {
        return RQ_RCODE;
    }

    public void setRQ_RCODE(String rQ_RCODE) {
        RQ_RCODE = rQ_RCODE;
    }

    public String getRQ_TRX_NO() {
        return RQ_TRX_NO;
    }

    public void setRQ_TRX_NO(String rQ_TRX_NO) {
        RQ_TRX_NO = rQ_TRX_NO;
    }

    public String getRS_MSG_TYPE() {
        return RS_MSG_TYPE;
    }

    public void setRS_MSG_TYPE(String rS_MSG_TYPE) {
        RS_MSG_TYPE = rS_MSG_TYPE;
    }

    public String getRS_PROC_CODE() {
        return RS_PROC_CODE;
    }

    public void setRS_PROC_CODE(String rS_PROC_CODE) {
        RS_PROC_CODE = rS_PROC_CODE;
    }

    public String getRS_TRACE_NO() {
        return RS_TRACE_NO;
    }

    public void setRS_TRACE_NO(String rS_TRACE_NO) {
        RS_TRACE_NO = rS_TRACE_NO;
    }

    public String getRS_TXN_DID() {
        return RS_TXN_DID;
    }

    public void setRS_TXN_DID(String rS_TXN_DID) {
        RS_TXN_DID = rS_TXN_DID;
    }

    public String getRS_TXN_SID() {
        return RS_TXN_SID;
    }

    public void setRS_TXN_SID(String rS_TXN_SID) {
        RS_TXN_SID = rS_TXN_SID;
    }

    public String getRS_TXN_NO() {
        return RS_TXN_NO;
    }

    public void setRS_TXN_NO(String rS_TXN_NO) {
        RS_TXN_NO = rS_TXN_NO;
    }

    public String getRS_TXN_INI_DT() {
        return RS_TXN_INI_DT;
    }

    public void setRS_TXN_INI_DT(String rS_TXN_INI_DT) {
        RS_TXN_INI_DT = rS_TXN_INI_DT;
    }

    public String getRS_RESP_CODE() {
        return RS_RESP_CODE;
    }

    public void setRS_RESP_CODE(String rS_RESP_CODE) {
        RS_RESP_CODE = rS_RESP_CODE;
    }

    public String getRS_SYNC_CHK_ITEM() {
        return RS_SYNC_CHK_ITEM;
    }

    public void setRS_SYNC_CHK_ITEM(String rS_SYNC_CHK_ITEM) {
        RS_SYNC_CHK_ITEM = rS_SYNC_CHK_ITEM;
    }

    public String getRS_AMOUNT() {
        return RS_AMOUNT;
    }

    public void setRS_AMOUNT(String rS_AMOUNT) {
        RS_AMOUNT = rS_AMOUNT;
    }

    public String getRS_PAY_TYPE() {
        return RS_PAY_TYPE;
    }

    public void setRS_PAY_TYPE(String rS_PAY_TYPE) {
        RS_PAY_TYPE = rS_PAY_TYPE;
    }

    public String getRS_CANCEL_NO() {
        return RS_CANCEL_NO;
    }

    public void setRS_CANCEL_NO(String rS_CANCEL_NO) {
        RS_CANCEL_NO = rS_CANCEL_NO;
    }

    public String getRS_PAY_DEADLINE() {
        return RS_PAY_DEADLINE;
    }

    public void setRS_PAY_DEADLINE(String rS_PAY_DEADLINE) {
        RS_PAY_DEADLINE = rS_PAY_DEADLINE;
    }

    public String getRS_ID() {
        return RS_ID;
    }

    public void setRS_ID(String rS_ID) {
        RS_ID = rS_ID;
    }

    public String getRS_HND_CHG() {
        return RS_HND_CHG;
    }

    public void setRS_HND_CHG(String rS_HND_CHG) {
        RS_HND_CHG = rS_HND_CHG;
    }

    public String getRS_HND_CHG_UNIT() {
        return RS_HND_CHG_UNIT;
    }

    public void setRS_HND_CHG_UNIT(String rS_HND_CHG_UNIT) {
        RS_HND_CHG_UNIT = rS_HND_CHG_UNIT;
    }

    public String getRS_BIZ_DATE() {
        return RS_BIZ_DATE;
    }

    public void setRS_BIZ_DATE(String rS_BIZ_DATE) {
        RS_BIZ_DATE = rS_BIZ_DATE;
    }

    public String getRS_IN_ACCT() {
        return RS_IN_ACCT;
    }

    public void setRS_IN_ACCT(String rS_IN_ACCT) {
        RS_IN_ACCT = rS_IN_ACCT;
    }

    public String getRS_RCODE() {
        return RS_RCODE;
    }

    public void setRS_RCODE(String rS_RCODE) {
        RS_RCODE = rS_RCODE;
    }

    public String getRS_TRX_NO() {
        return RS_TRX_NO;
    }

    public void setRS_TRX_NO(String rS_TRX_NO) {
        RS_TRX_NO = rS_TRX_NO;
    }

}
