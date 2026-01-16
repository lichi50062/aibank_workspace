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
 * @(#)LoanType.java
 * 
 * <p>Description:ACCOUNT_INFO_LOAN_NAME 對應</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/20, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum LoanType implements IEnum {

    /** A01 分期型房貸. */
    HOUSE_INSTALLMENT_LOAN("A01", "分期型房貸"),

    /** A02 國宅房貸 . */
    HOUSE_PUBLIC_LOAN("A02", "房貸-國宅"),

    /** A03 公教房貸 . */
    HOUSE_GOVERNMENT_LOAN("A03", "房貸-公教"),

    /** B01 分期型信貸 . */
    CREDIT_INSTALLMENT_LOAN("B01", "分期型信貸"),

    /** B02 自辦留學貸款 . */
    FOREIGN_STUDY_SELF_LOAN("B02", "留學貸款"),

    // C-存款透支帳號
    /** C01 額度式透支. */
    HOUSE_QUATA_LOAN("C01", "額度式透支"),

    /** C02 支存透支. */
    HOUSER_OVERDRAFT_LOAN("C02", "支存透支"),

    /** C03 回復式透支. */
    HOUSE_CYCLE_LOAN("C03", "回復式透支"),

    /** C04 信貸 . */
    CREDIT_CYCLE_LOAN("C04", "循環型信貸"),

    /** C05 綜存質借 . */
    SAVING_PLEDGE_LOAN("C05", "綜存質借"),

    /** C06 信託質借循環. */
    REVOLVING_TRUST_LOAN("C06", "循環型信託質借"),

    /** C07 政策性貸款 . */
    PERSONAL_POLICY_LOAN("C07", "個人週轉金-政策性貸款"), // 循環型企貸

    /** D01 就學貸款 . */
    EDUCATION_LOAN("D01", "就學貸款"),

    /** E01 留學貸款 . */
    FOREIGN_STUDY_LOAN("E01", "留學貸款"),

    /** F01 勞工紓困貸款. */
    LABOR_RELIEF_LOAN("F01", "勞工紓困貸款"),

    /** G01 存單質借 . */
    DEPOSIT_PLEDGE_LOAN("G01", "存單質借"),

    /** H01 購屋貸款 . */
    PERSONAL_HOUSE_LOAN("H01", "個人週轉金-購屋貸款"),

    /** I01 政策性貸款 . */
    ENTERPRISE_POLICY_LOAN("I01", "企業貸款-政策性貸款"),

    /** I02 新興. */
    ENTERPRISE_NEW_LOAN("I02", "企業貸款-新興貸款"),

    /** I04 保證業務 . */
    GUARANTEED_LOAN("I04", "保證業務"),

    /** I05 一般企業貸款 . */
    ENTERPRISE_LOAN("I05", "企業貸款-一般企業貸款"),

    /** I06 貿易融資 . */
    ENTERPRISE_FINANCING_LOAN("I06", "企業貸款-貿易融資"),

    /** I07 購屋貸款 . */
    ENTERPRISE_HOUSE_LOAN("I07", "企業貸款-不動產"),

    /** I08 透支 . */
    OVERDRAFT_LOAN("I08", "透支"),

    /** J01 信託質借. */
    TRUST_LOAN("J01", "信託質借"),

    UNKNOWN(UNKNOWN_STR_CODE, UNKNOWN_STR_CODE);

    /**
     * @param code
     * @param memo
     */
    private LoanType(String code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    /** The code. */
    private String code;

    /** The memo. */
    private String memo;

    public static LoanType find(String code) {
        for (LoanType type : LoanType.values()) {
            if (StringUtils.equals(code, type.getCode())) {
                return type;
            }
        }
        return LoanType.UNKNOWN;
    }

    /**
     * @return {@link #memo}
     */
    @Override
    public String getMemo() {
        return memo;
    }

    /**
     * @return {@link #code}
     */
    public String getCode() {
        return code;
    }

    // @formatter:off
    
    public boolean isEducationLoan() {
        return equals(EDUCATION_LOAN);
    }
    
    public boolean isProductC() {
        return equals(CREDIT_CYCLE_LOAN) || equals(HOUSE_QUATA_LOAN) || equals(HOUSER_OVERDRAFT_LOAN) ||
               equals(HOUSE_CYCLE_LOAN) || equals(CREDIT_CYCLE_LOAN) || equals(SAVING_PLEDGE_LOAN) ||
               equals(REVOLVING_TRUST_LOAN) || equals(PERSONAL_POLICY_LOAN);
    }
    
    public boolean isProductH() {
        return equals(PERSONAL_HOUSE_LOAN);
    }
    
    public boolean isProductI() {
        return equals(ENTERPRISE_POLICY_LOAN) || equals(ENTERPRISE_NEW_LOAN) || equals(GUARANTEED_LOAN) ||
               equals(ENTERPRISE_LOAN) || equals(ENTERPRISE_FINANCING_LOAN) || equals(ENTERPRISE_HOUSE_LOAN) || equals(OVERDRAFT_LOAN);
    }
    
    public boolean isPayLoanDetailPermission() {
        return equals(ENTERPRISE_NEW_LOAN) || !(isProductC() || isProductH() || isProductI());
    }


    /**
     * 是否為國宅房貸
     *
     * @return
     */
    public boolean isHousePublicLoan() {
        return equals(HOUSE_PUBLIC_LOAN);
    }

    /**
     * 是否為公教房貸
     *
     * @return
     */
    public boolean isHouseGovernmentLoan() {
        return equals(HOUSE_GOVERNMENT_LOAN);
    }
    
    /**
     * 是否為自辦留學貸款
     * 
     * @return
     */
    public boolean isForeignStudySelfLoan() {
        return equals(FOREIGN_STUDY_SELF_LOAN);
    }
    // @formatter:on
}
