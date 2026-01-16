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
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)TxSourceType.java
 * 
 * <p>Description:交易來源</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum TxSourceType implements IEnum {

    INTERNET_BANKING("1", "網路銀行"),

    MOBILE_BANKING("2", "行動銀行"),

    AI_BANK("3", "AI Bank"),

    AI_BANK_FOR_NOTIFICATION("5", "AIBank 僅針對寫入 DB.NOTIFICATION 使用");

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
    TxSourceType(String code, String memo) {
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

    public static TxSourceType find(String code) {
        for (TxSourceType group : TxSourceType.values()) {
            if (StringUtils.equals(group.getCode(), code)) {
                return group;
            }
        }
        return null;
    }

    public boolean isInternetBanking() {
        return equals(INTERNET_BANKING);
    }

    public boolean isMobileBanking() {
        return equals(MOBILE_BANKING);
    }

    public boolean isAIBankBank() {
        return equals(AI_BANK) || equals(AI_BANK_FOR_NOTIFICATION);
    }

}