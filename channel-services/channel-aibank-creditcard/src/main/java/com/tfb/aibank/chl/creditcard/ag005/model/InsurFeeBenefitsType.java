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
package com.tfb.aibank.chl.creditcard.ag005.model;
import java.util.ArrayList;
import java.util.List;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.type.I18NEnum;
import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)InsurFeeBenefitsType.java
 * 
 * <p>Description:保費權益類型</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/31, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum InsurFeeBenefitsType implements IEnum, I18NEnum {

    /** 0.5%回饋 */
    FEEDBACK("0", "0.5%回饋", "nccag005.insur_fee_benefits.feedback"),

    /** 3期0利率 */
    THREE_PERIODS_ZERO_INTEREST_RATE("A", "3期0利率", "nccag005.insur_fee_benefits.three_periods"),

    /** 6期0利率 */
    SIX_PERIODS_ZERO_INTEREST_RATE("B", "6期0利率", "nccag005.insur_fee_benefits.six_periods"),

    /** 9期0利率 */
    NINE_PERIODS_ZERO_INTEREST_RATE("C", "9期0利率", "nccag005.insur_fee_benefits.nine_periods"),

    /** 12期0利率 */
    TWELVE_PERIODS_ZERO_INTEREST_RATE("D", "12期0利率", "nccag005.insur_fee_benefits.twelve_periods");

    InsurFeeBenefitsType(String code, String memo, String i18nKey) {
        this.code = code;
        this.memo = memo;
        this.i18nKey = i18nKey;
    }

    /** 代碼 **/
    private String code;

    /** 說明* */
    private String memo;

    /** i18n 鍵值 */
    private String i18nKey;

    public String getCode() {
        return code;
    }

    @Override
    public String getMemo() {
        return this.memo;
    }

    public static InsurFeeBenefitsType findByCode(String code) {
        for (InsurFeeBenefitsType type : InsurFeeBenefitsType.values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String getI18NMemo() {
        return I18NUtils.getMessage(this.i18nKey);
    }
	
	  /**
     * 排除枚舉 9期，12期的資料
     * @return
     */
    public static List<InsurFeeBenefitsType> getSomeInsurFeeBenefits(){
    	List<InsurFeeBenefitsType> someInsurFeeBenefitsTypes = new ArrayList<>();
    	for (InsurFeeBenefitsType type : InsurFeeBenefitsType.values()) {
    		if(type!=InsurFeeBenefitsType.NINE_PERIODS_ZERO_INTEREST_RATE 
    				&& type!=InsurFeeBenefitsType.TWELVE_PERIODS_ZERO_INTEREST_RATE) {
    			someInsurFeeBenefitsTypes.add(type);
    		}
    	}
    	return someInsurFeeBenefitsTypes;
    }
}
