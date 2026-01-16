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

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.type.I18NEnum;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)TxStatusType.java
 * 
 * <p>Description:交易結果</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/29, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum TxStatusType implements I18NEnum {
    // =================================
    // 交易執行結果
    // =================================
    SUCCESS("0", "交易成功", "common.txn_status_type.success"),

    FAIL("1", "交易失敗", "common.txn_status_type.fail"),

    // =================================
    // 流程狀態
    // =================================

    RESERVED("2", "預約完成", "common.txn_status_type.reservsed"),

    CANCELED("3", "預約註銷", "common.txn_status_type.canceled"),

    // =================================
    // 特殊狀態
    // =================================
    PROCESS("4", "交易未明", "common.txn_status_type.process"),

    PROCESSED("5", "受理成功(銀行處理中)", "common.txn_status_type.processed"),

    ACCT_DATE_CHANGED("9", "換日，交易取消", "common.txn_status_type.acct_date_changed"),

    PARTIALLY_FAILED("9010", "交易部分失敗", "common.txn_status_type.partially_failed"),

    UNKNOWN("-1", "未知", "UNKNOWN");

    /** 狀態碼* */
    private String code;

    /** 狀態說明* */
    private String memo;

    /** i18n 鍵值 */
    private String i18nKey;


    /**
     * Constructor
     *
     * @param statusCode
     * @param memo
     */
    TxStatusType(String code, String memo, String i18nKey) {
        this.code = code;
        this.memo = memo;
        this.i18nKey = i18nKey;
    }

    public String getCode() {
        return code;
    }

    /**
     * 取得 memo
     *
     * @return 傳回 memo。
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 取得i18n訊息
     *
     * @return i18n訊息
     */
    public String getI18nKey() {
        return i18nKey;
    }

    public static TxStatusType find(String code) {
        for (TxStatusType type : TxStatusType.values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }
        return TxStatusType.UNKNOWN;
    }

    public boolean isSuccess() {
        return equals(SUCCESS);
    }

    public boolean isFail() {
        return equals(FAIL);
    }

    public boolean isProcess() {
        return equals(PROCESS);
    }

    public boolean isReserved() {
        return equals(RESERVED);
    }

    @Override
    public String getI18NMemo() {
        if (equals(UNKNOWN)) {
            return StringUtils.EMPTY;
        }
        return I18NUtils.getMessage(this.i18nKey);
    }

}
