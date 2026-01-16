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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

// @formatter:off
/**
 * @(#)BOND8030.java
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
public class BOND8030 {

    private String RQ_MSG_TYPE = "";
    private String RQ_PROC_CODE = "";
    private String RQ_TRACE_NO = "";
    private String RQ_TXN_DID = "";
    private String RQ_TXN_SID = "";
    private String RQ_TXN_NO = "";
    private String RQ_TXN_INI_DT = "";
    private String RQ_RESP_CODE = "";
    private String RQ_SYNC_CHK_ITEM = "";
    private String RQ_ID = "";
    private String RQ_QUERY_ITEM = "";
    private String RQ_QUERY_DATA = "";

    private String RS_MSG_TYPE = "";
    private String RS_PROC_CODE = "";
    private String RS_TRACE_NO = "";
    private String RS_TXN_DID = "";
    private String RS_TXN_SID = "";
    private String RS_TXN_NO = "";
    private String RS_TXN_INI_DT = "";
    private String RS_RESP_CODE = "";
    private String RS_SYNC_CHK_ITEM = "";
    private String RS_ID = "";
    private String RS_TRX_NO = "";
    private String RS_CANCEL_NO = "";
    private String RS_FEE_NO = "";
    private String RS_SHOW_DATA = "";
    private String RS_COUNT = "";

    private List<BOND8030_ITERATION> RS_ITERATION_LIST = new ArrayList<BOND8030_ITERATION>();

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

    public String getRQ_ID() {
        return RQ_ID;
    }

    public void setRQ_ID(String rQ_ID) {
        RQ_ID = rQ_ID;
    }

    public String getRQ_QUERY_ITEM() {
        return RQ_QUERY_ITEM;
    }

    public void setRQ_QUERY_ITEM(String rQ_QUERY_ITEM) {
        RQ_QUERY_ITEM = rQ_QUERY_ITEM;
    }

    public String getRQ_QUERY_DATA() {
        return RQ_QUERY_DATA;
    }

    public void setRQ_QUERY_DATA(String rQ_QUERY_DATA) {
        RQ_QUERY_DATA = rQ_QUERY_DATA;
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

    public String getRS_ID() {
        return RS_ID;
    }

    public void setRS_ID(String rS_ID) {
        RS_ID = rS_ID;
    }

    public List<BOND8030_ITERATION> getRS_ITERATION_LIST() {
        return RS_ITERATION_LIST;
    }

    public void setRS_ITERATION_LIST(List<BOND8030_ITERATION> rS_ITERATION_LIST) {
        RS_ITERATION_LIST = rS_ITERATION_LIST;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE).toString();
    }

    /**
     * 取得 rS_TRX_NO
     * 
     * @return 傳回 rS_TRX_NO。
     */
    public String getRS_TRX_NO() {
        return RS_TRX_NO;
    }

    /**
     * 設定 rS_TRX_NO
     * 
     * @param rS_TRX_NO
     *            要設定的 rS_TRX_NO。
     */
    public void setRS_TRX_NO(String rS_TRX_NO) {
        RS_TRX_NO = rS_TRX_NO;
    }

    /**
     * 取得 rS_CANCEL_NO
     * 
     * @return 傳回 rS_CANCEL_NO。
     */
    public String getRS_CANCEL_NO() {
        return RS_CANCEL_NO;
    }

    /**
     * 設定 rS_CANCEL_NO
     * 
     * @param rS_CANCEL_NO
     *            要設定的 rS_CANCEL_NO。
     */
    public void setRS_CANCEL_NO(String rS_CANCEL_NO) {
        RS_CANCEL_NO = rS_CANCEL_NO;
    }

    /**
     * 取得 rS_FEE_NO
     * 
     * @return 傳回 rS_FEE_NO。
     */
    public String getRS_FEE_NO() {
        return RS_FEE_NO;
    }

    /**
     * 設定 rS_FEE_NO
     * 
     * @param rS_FEE_NO
     *            要設定的 rS_FEE_NO。
     */
    public void setRS_FEE_NO(String rS_FEE_NO) {
        RS_FEE_NO = rS_FEE_NO;
    }

    /**
     * 取得 rS_SHOW_DATA
     * 
     * @return 傳回 rS_SHOW_DATA。
     */
    public String getRS_SHOW_DATA() {
        return RS_SHOW_DATA;
    }

    /**
     * 設定 rS_SHOW_DATA
     * 
     * @param rS_SHOW_DATA
     *            要設定的 rS_SHOW_DATA。
     */
    public void setRS_SHOW_DATA(String rS_SHOW_DATA) {
        RS_SHOW_DATA = rS_SHOW_DATA;
    }

    /**
     * 取得 rS_COUNT
     * 
     * @return 傳回 rS_COUNT。
     */
    public String getRS_COUNT() {
        return RS_COUNT;
    }

    /**
     * 設定 rS_COUNT
     * 
     * @param rS_COUNT
     *            要設定的 rS_COUNT。
     */
    public void setRS_COUNT(String rS_COUNT) {
        RS_COUNT = rS_COUNT;
    }

}
