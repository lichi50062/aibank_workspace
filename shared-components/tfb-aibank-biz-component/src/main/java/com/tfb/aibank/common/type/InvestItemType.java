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
 * @(#)InvestItemType.java
 * 
 * <p>Description:[持有商品牌卡 ]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/12, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum InvestItemType implements IEnum {

    NMI("奈米投", "NNFOT001"),

    FUND("基金", "NBOQU015"),

    ETF("海外ETF/股票", "NBOQU006"),

    BOND("海外債券", "NBOQU005"),

    BONDFM("外國債券 (自營)", "NBOQU018"),

    SN("境內外結構型商品", "NBOQU008"),

    SIDCI("組合式商品", "NBOQU007"),

    GOLD("黃金存摺", "NBOQU009");

    /** 說明* */
    private String memo;

    /** target* */
    private String target;

    /**
     * Constructor
     *
     * @param memo
     * @param target
     */
    InvestItemType(String memo, String target) {
        this.memo = memo;
        this.target = target;
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

    /**
     * @return the target
     */
    public String getTarget() {
        return target;
    }

    public static InvestItemType find(String code) {
        for (InvestItemType type : InvestItemType.values()) {
            if (StringUtils.equals(type.name(), code)) {
                return type;
            }
        }
        return null;
    }

    public boolean isNMI() {
        return equals(NMI);
    }

    public boolean isFUND() {
        return equals(FUND);
    }

    public boolean isETF() {
        return equals(ETF);
    }

    public boolean isBOND() {
        return equals(BOND);
    }

    public boolean isBONDFM() {
        return equals(BONDFM);
    }

    public boolean isSN() {
        return equals(SN);
    }

    public boolean isSIDCI() {
        return equals(SIDCI);
    }

    public boolean isGOLD() {
        return equals(GOLD);
    }

}
