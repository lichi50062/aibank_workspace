package com.tfb.aibank.common.type;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)MiniBuyAmtOptionType.java
 * 
 * <p>Description:最低申購面額選項</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/21, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum MiniBuyAmtOptionType implements IEnum {

    LEVEL1(0L, 5000L, "bond_query.option.mini_buy_amt_01"), // 5,000(含)以下

    LEVEL2(5000L, 10000L, "bond_query.option.mini_buy_amt_02"), // 5,000 ~ 10,000(含)

    LEVEL3(10000L, 100000L, "bond_query.option.mini_buy_amt_03"), // 10,000 ~ 100,000(含)

    LEVEL4(100000L, 200000L, "bond_query.option.mini_buy_amt_04"), // 100,000 ~ 200,000(含)

    LEVEL5(200000L, 9999999999999L, "bond_query.option.mini_buy_amt_05"); // 200,000(不含)以上

    MiniBuyAmtOptionType(Long lower, Long upper, String memo) {
        this.lower = lower;
        this.upper = upper;
        this.memo = memo;
    }

    private Long lower;

    private Long upper;

    private String memo;

    @Override
    public String getMemo() {
        return I18NUtils.getMessage(this.memo);
    }

    public Long getLower() {
        return lower;
    }

    public Long getUpper() {
        return upper;
    }

}
