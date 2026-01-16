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

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.annotations.FormatDate;

// @formatter:off
/**
 * @(#)TxnResult.java
 * 
 * <p>Description:交易結果資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/07, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TxnResult {

    public TxnResult() {
        // default constructor
    }

    public TxnResult(String txStatus, String txStatusDesc, Date hostTxTime, String txErrorDesc) {
        this.txStatus = txStatus;
        this.txStatusDesc = txStatusDesc;
        this.hostTxTime = hostTxTime;
        this.txErrorDesc = txErrorDesc;
    }

    /** 交易狀態，0:交易成功、1:交易失敗、4:交易未明 */
    private String txStatus;

    /** 交易結果 */
    private String txStatusDesc;

    /** 交易時間 */
    @FormatDate(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date hostTxTime;

    /** 交易錯誤訊息 */
    private String txErrorDesc;

    /** 轉導按鈕名稱1 */
    private String directButtonName1;

    /** 轉導交易1 */
    private String directTaskId1;

    /** 轉導按鈕名稱2 */
    private String directButtonName2;

    /** 轉導交易2 */
    private String directTaskId2;

    public String getTxStatus() {
        return txStatus;
    }

    public void setTxStatus(String txStatus) {
        this.txStatus = txStatus;
    }

    public String getTxStatusDesc() {
        return txStatusDesc;
    }

    public void setTxStatusDesc(String txStatusDesc) {
        this.txStatusDesc = txStatusDesc;
    }

    public Date getHostTxTime() {
        return hostTxTime;
    }

    public void setHostTxTime(Date hostTxTime) {
        this.hostTxTime = hostTxTime;
    }

    public String getTxErrorDesc() {
        return txErrorDesc;
    }

    public void setTxErrorDesc(String txErrorDesc) {
        this.txErrorDesc = txErrorDesc;
    }

    public String getDirectButtonName1() {
        return directButtonName1;
    }

    public void setDirectButtonName1(String directButtonName1) {
        this.directButtonName1 = directButtonName1;
    }

    public String getDirectTaskId1() {
        return directTaskId1;
    }

    public void setDirectTaskId1(String directTaskId1) {
        this.directTaskId1 = directTaskId1;
    }

    public String getDirectButtonName2() {
        return directButtonName2;
    }

    public void setDirectButtonName2(String directButtonName2) {
        this.directButtonName2 = directButtonName2;
    }

    public String getDirectTaskId2() {
        return directTaskId2;
    }

    public void setDirectTaskId2(String directTaskId2) {
        this.directTaskId2 = directTaskId2;
    }

    public boolean isSuccess() {
        return StringUtils.equals("0", this.txStatus);
    }

    public boolean isUnknown() {
        return StringUtils.equals("4", this.txStatus);
    }

    public boolean isFail() {
        return StringUtils.equals("1", this.txStatus);
    }
}
