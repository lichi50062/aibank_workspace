package com.tfb.aibank.common.type;

import java.math.BigDecimal;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)YtmytcOptionType.java
 * 
 * <p>Description:到期/提前買回殖利率選單</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/21, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum YtmytcOptionType implements IEnum {

    LEVEL1(BigDecimal.ONE, new BigDecimal("3"), "bond_query.option.ytm_ytc_01"), // 1%(含) ~ 3%

    LEVEL2(new BigDecimal("3"), new BigDecimal("5"), "bond_query.option.ytm_ytc_03"), // 3%(含) ~ 5%

    LEVEL3(new BigDecimal("5"), new BigDecimal("7"), "bond_query.option.ytm_ytc_05"), // 5%(含) ~ 7%

    LEVEL4(new BigDecimal("7"), new BigDecimal("999.99"), "bond_query.option.ytm_ytc_07"); // 7%(含)以上

    YtmytcOptionType(BigDecimal lower, BigDecimal upper, String memo) {
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
