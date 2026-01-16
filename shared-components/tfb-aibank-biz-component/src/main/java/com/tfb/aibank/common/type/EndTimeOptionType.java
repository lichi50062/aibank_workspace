package com.tfb.aibank.common.type;

import java.math.BigDecimal;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)EndTimeOptionType.java
 * 
 * <p>Description:剩餘年期選單</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/21, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum EndTimeOptionType implements IEnum {

    LEVEL1(BigDecimal.ZERO, BigDecimal.ONE, "bond_query.option.end_time_01"), // 1年以下

    LEVEL2(BigDecimal.ONE, new BigDecimal("5"), "bond_query.option.end_time_02"), // 1年(含) ~ 5年

    LEVEL3(new BigDecimal("5"), new BigDecimal("10"), "bond_query.option.end_time_03"), // 5年(含) ~ 10年

    LEVEL4(new BigDecimal("10"), new BigDecimal("15"), "bond_query.option.end_time_04"), // 10年(含) ~ 15年

    LEVEL5(new BigDecimal("15"), new BigDecimal("999.99"), "bond_query.option.end_time_05"); // 15年(含)以上

    EndTimeOptionType(BigDecimal lower, BigDecimal upper, String memo) {
        this.lower = lower;
        this.upper = upper;
        this.memo = memo;
    }

    private BigDecimal lower;

    private BigDecimal upper;

    private String memo;

    @Override
    public String getMemo() {
        return I18NUtils.getMessage(this.memo);
    }

    public BigDecimal getLower() {
        return lower;
    }

    public BigDecimal getUpper() {
        return upper;
    }

}
