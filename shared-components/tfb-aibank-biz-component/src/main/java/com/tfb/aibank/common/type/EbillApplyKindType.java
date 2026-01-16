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
 * @(#)EbillApplyKindType.java
 * 
 * <p>Description:Ebill申請種類</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/21, leiley 
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum EbillApplyKindType {
    // 0：綜合電子對帳單
    COMPOSITE("0", "綜合電子對帳單"),

    // 1：白金綜合電子對帳單
    GOLD("1", "白金綜合電子對帳單"),

    // 2：合併後的銀行電子對帳單
    MERGE("2", "合併後的銀行電子對帳單"),

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
    EbillApplyKindType(String code, String memo) {
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

    public static EbillApplyKindType find(String code) {
        for (EbillApplyKindType type : EbillApplyKindType.values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }
        return EbillApplyKindType.UNKNOWN;
    }

    /**
     * 綜合電子對帳單
     * 
     * @return
     */
    public boolean isComposite() {
        return equals(COMPOSITE);
    }

    /**
     * 白金綜合電子對帳單
     */
    public boolean isGold() {
        return equals(GOLD);
    }

    /**
     * 合併後的銀行電子對帳單
     */
    public boolean isMerge() {
        return equals(MERGE);
    }

}