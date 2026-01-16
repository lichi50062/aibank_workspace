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

import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)EbillApplyType.java
 * 
 * <p>Description:申請項目</p>
 * 0：申請
 * 1：終止
 * 2：補寄
 * 3：密碼設定-身份證字號改自設密碼
 * 4：密碼設定-變更新自設密碼
 * 5：密碼設定-自設密碼改身分證字號
 * <p>Modify History:</p>
 * v1.0, 2023/08/21, leiley 
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum EbillApplyType {
    // 0：申請
    APPLY("0", "申請"),

    // 1：終止
    CANCEL("1", "終止"),

    // 2：補寄
    RESEND("2", "補寄"),

    // 3：密碼設定-身份證字號改自設密碼
    SETSECRET("3", "密碼設定-身份證字號改自設密碼"),

    // 4：密碼設定-變更新自設密碼
    CHGSECRET("4", "密碼設定-變更新自設密碼"),

    // 5：密碼設定-自設密碼改身分證字號
    CELSECRET("5", "密碼設定-自設密碼改身分證字號"),

    UNKNOWN("-1", "未知");

    /** 狀態碼* */
    private String code;

    /** 狀態說明* */
    private String memo;

    /**
     * Constructor
     *
     * @param statusCode
     * @param memo
     */
    EbillApplyType(String code, String memo) {
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
    public String getMemo() {
        return memo;
    }

    public static EbillApplyType find(String code) {
        for (EbillApplyType type : EbillApplyType.values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }
        return EbillApplyType.UNKNOWN;
    }

    /**
     * 申請
     */
    public boolean isApply() {
        return equals(APPLY);
    }

    /**
     * 終止
     */
    public boolean isCancel() {
        return equals(CANCEL);
    }

    /**
     * 補寄
     */
    public boolean isResend() {
        return equals(RESEND);
    }

    /**
     * 設定帳單開啟密碼
     */
    public boolean isSetSecret() {
        return equals(SETSECRET);
    }

    /**
     * 變更帳單開啟密碼
     */
    public boolean isChgSecret() {
        return equals(CHGSECRET);
    }

    /**
     * 取消帳單開啟密碼
     */
    public boolean isCelSecret() {
        return equals(CELSECRET);
    }

}