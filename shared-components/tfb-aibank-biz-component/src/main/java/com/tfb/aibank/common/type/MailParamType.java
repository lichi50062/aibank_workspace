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
package com.tfb.aibank.common.type;

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)MailHunterRspCode.java
 * 
 * <p>Description:Mail資料參數</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum MailParamType implements IEnum {

    TXN_NAME("txnName", "交易名稱"),

    MASTER_KEY("masterKey", "交易主檔鍵值"),

    DETAIL_KEY("detailKey", "交易明細檔鍵值"),

    TX_STATUS("txStatus", "交易結果代碼"),

    TX_STATUS_DESC("txStatusDesc", "交易結果說明"),

    TX_ERROR_DESC("txErrorDesc", "錯誤訊息"),

    TX_ERROR_SYSTEM_ID("txErrorSystemId", "錯誤系統別"),

    TX_ERROR_CODE("txErrorCode", "錯誤代碼"),

    HOST_TX_TIME("hostTxTime", "主機交易時間"),

    TEMPLATE_NAME("templateName", "模版名稱"),

    TXN_ID("txnId", "交易代號"),

    ATTACHMENT("attachMent", "附件"),

    TX_TYPE("txType", "交易類型");

    /** 參數 */
    private String code;

    /** 狀態說明* */
    private String memo;

    /**
     * Constructor
     *
     * @param statusCode
     * @param memo
     */
    MailParamType(String code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public String getCode() {
        return code;
    }

    /**
     * 取得 memo
     *
     * @return 傳回 memo。
     */
    @Override
    public String getMemo() {
        return memo;
    }

}