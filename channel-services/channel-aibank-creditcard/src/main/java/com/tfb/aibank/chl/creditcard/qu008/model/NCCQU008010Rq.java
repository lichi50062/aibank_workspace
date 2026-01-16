package com.tfb.aibank.chl.creditcard.qu008.model;

import org.springframework.stereotype.Component;

import com.google.gson.annotations.SerializedName;
import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCQU008010Rq.java
 * 
 * <p>Description:信用卡分期管理 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/02, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU008010Rq implements RqData {

    /** 分期類型 BILL or UNBILL */
    @SerializedName("Type")
    private String type;

    /** 消費資訊 */
    @SerializedName("TxnData")
    private NCCQU008TxnDataRq txnData;

    /**
     * 1:已分期資訊,2:未分期資訊單筆分期未分期未請款,3:單筆分期未分期已請款,4:帳單分期未分期
     */
    private String reSearchFlag;

    /**
     * 預設 可分期交易Tab 0:已分期 , 1:可分期
     */
    private String txnTabDefault = "0";

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the txnData
     */
    public NCCQU008TxnDataRq getTxnData() {
        return txnData;
    }

    /**
     * @param txnData
     *            the txnData to set
     */
    public void setTxnData(NCCQU008TxnDataRq txnData) {
        this.txnData = txnData;
    }

    /**
     * @return the reSearchFlag
     */
    public String getReSearchFlag() {
        return reSearchFlag;
    }

    /**
     * @param reSearchFlag
     *            the reSearchFlag to set
     */
    public void setReSearchFlag(String reSearchFlag) {
        this.reSearchFlag = reSearchFlag;
    }

    /**
     * @return the txnTabDefault
     */
    public String getTxnTabDefault() {
        return txnTabDefault;
    }

    /**
     * @param txnTabDefault
     *            the txnTabDefault to set
     */
    public void setTxnTabDefault(String txnTabDefault) {
        this.txnTabDefault = txnTabDefault;
    }

}
