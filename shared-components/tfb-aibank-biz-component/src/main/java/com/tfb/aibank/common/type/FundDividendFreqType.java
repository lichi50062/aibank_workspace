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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)FundDividendFreqType.java
 *
 * Description:FundDividendFreqType
 * 基金配息頻率類型
 * <pre>
 * "不配息", "月配", "雙月配", "季配", "半年配", "年配", "兩年配"
 * Modify History:
 * v1.0, 2024/07/27, Marty Pan, 初版
 *
 * </pre>
 */
// @formatter:on
public enum FundDividendFreqType implements IEnum {

    NO_DIV("A", "不配息", "fund.option.dividend_frequency_01"),

    MONTH_DIV("B", "月配", "fund.option.dividend_frequency_02"),

    TWO_MONTH_DIV("C", "雙月配", "fund.option.dividend_frequency_03"),

    QUARTER_DIV("D", "季配", "fund.option.dividend_frequency_04"),

    HALF_YEAR_DIV("E", "半年配", "fund.option.dividend_frequency_05"),

    YEAR_DIV("F", "年配", "fund.option.dividend_frequency_06"),

    TWO_YEAR_DIV("G", "兩年配", "fund.option.dividend_frequency_07"),

    UNKNOWN(UNKNOWN_STR_CODE, "未知", "未知");

    private String code;

    /** 向 DB 查詢時的中文 */
    private String queryName;

    private String memo;

    /**
     * 說明
     */

    FundDividendFreqType(String code, String queryName, String memo) {
        this.code = code;
        this.queryName = queryName;
        this.memo = memo;
    }

    public static List<FundDividendFreqType> getAll() {
        return Arrays.stream(FundDividendFreqType.values()).collect(Collectors.toList());
    }

    public static FundDividendFreqType findByCode(String code) {
        return getAll().stream().filter(x -> StringUtils.equals(x.getCode(), code)).findAny().orElse(UNKNOWN);
    }

    public static FundDividendFreqType findByQueryName(String queryName) {
        return getAll().stream().filter(x -> StringUtils.equals(x.getQueryName(), queryName)).findAny().orElse(UNKNOWN);
    }

    public String getQueryName() {
        return this.queryName;
    }

    public String getCode() {
        return this.code;
    }

    @Override
    public String getMemo() {
        return I18NUtils.getMessage(this.memo);
    }

    public boolean isUnknown() {
        return equals(UNKNOWN);
    }

    /**
     * 不配息
     */
    public boolean isNoDividend() {
        return equals(NO_DIV);
    }
}
