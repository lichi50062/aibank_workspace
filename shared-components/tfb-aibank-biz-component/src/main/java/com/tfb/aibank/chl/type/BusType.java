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
package com.tfb.aibank.chl.type;

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)BusType.java
 * 
 * <p>Description:個人化通知訊息 - 業務別類型</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/04, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum BusType implements IEnum {

    DEPOSIT("1", "存款"),

    INVESTMENT("2", "投資"),

    CREDIT_CARD("3", "信用卡"),

    PERSONAL("4", "個人"),

    FUND("5", "基金"),

    LOAN("6", "貸款"),

    DEBIT_CARD("7", "簽帳卡"),

    INSURANCE("8", "保險"),

    SECURITIES("9", "證券"),

    PAYMENT("10", "繳費"),

    UNKNOWN(UNKNOWN_STR_CODE, "未知"),

    ;

    private String code;

    private String memo;

    private BusType(String code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public static BusType findByCode(String code) {
        for (BusType type : BusType.values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    @Override
    public String getMemo() {
        return this.memo;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

}
