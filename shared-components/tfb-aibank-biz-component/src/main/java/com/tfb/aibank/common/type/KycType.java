/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.common.type;

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)KycType.java
 * 
 * <p>Description:[KYC狀態: 0:未完成 1已過期 -1:重新測試 ]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/12, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum KycType implements IEnum {

    /** 不續存 */
    KYC_NOT_DONE("0", "未完成"),

    /** 本金續存 */
    KYC_EXPIRED("1", "已過期"),

    /** 本利續存 */
    KYC_RETEST("-1", "重新測試");

    /** 狀態碼* */
    private String code;

    /** 狀態說明* */
    private String memo;

    /**
     * Constructor
     *
     * @param code
     * @param memo
     */
    KycType(String code, String memo) {
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

    public static KycType find(String code) {
        for (KycType type : KycType.values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }
        return null;
    }
}
