/**
 * 
 */
package com.tfb.aibank.common.type;

import com.ibm.tw.commons.type.IEnum;

//@formatter:off
/**
* @(#)IntPayType.java
* 
* <p>Description:貸款帳號類型</p>
* <p>Modify History:</p>
* v1.0, 2023/11/14,John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum LoanAccountType implements IEnum {

    /** 勞工紓困貸款 */
    LABOR_RELIEF_LOAN("1", "勞工紓困貸款", "63", "03"),

    DEPOSIT_PLEDGE_LOAN("1", "存單質借", "70", "01"),

    SAVING_PLEDGE_LOAN("1", "綜存質借", "67", "01"),

    HOUSE_QUATA_LOAN("1", "循環型房貸(額度式)", "69", "01"),

    HOUSER_OVERDRAFT_LOAN("1", "循環型房貸(支存透支)", "69", "03"),

    HOUSE_CYCLE_LOAN("1", "循環型房貸(回復式)", "69", "02"),

    HOUSE_INSTALLMENT_LOAN("1", "分期型房貸", "61", "01"),

    HOUSE_GOVERNMENT_LOAN("1", "房貸-公教", "61", "02"),

    HOUSE_PUBLIC_LOAN("1", "房貸-國宅", "61", "03"),

    CREDIT_CYCLE_LOAN("1", "循環型信貸", "73", "01"),

    CREDIT_INSTALLMENT_LOAN("1", "分期型信貸", "62", "01"),

    EDUCATION_LOAN("1", "就學貸款", "63", "01"),

    FOREIGN_STUDY_LOAN("1", "留學貸款", "63", "02"),

    PERSONAL_LOAN("1", "個人週轉金-一般周轉", "64", "01"),

    PERSONAL_POLICY_LOAN("1", "個人週轉金-政策性貸款", "64", "03"),

    PERSONAL_HOUSE_LOAN("1", "個人週轉金-購屋貸款", "64", "04"),

    ENTERPRISE_LOAN("1", "企業貸款-一般企業貸款", "65", "01"),

    ENTERPRISE_POLICY_LOAN("1", "企業貸款-政策性貸款", "65", "03"),

    ENTERPRISE_FINANCING_LOAN("1", "企業貸款-貿易融資", "65", "04"),

    GUARANTEED_LOAN("1", "保證業務", "65", "05"),

    ENTERPRISE_NEW_LOAN("1", "企業貸款-新興貸款", "71", "01"),

    TRUST_NONTRUST_LOAN("1", "非循環型信託質借", "90", "01"),

    TRUST_FOREIGN_LOAN("1", "非循環型外幣信託質借", "90", "02"),

    TRUST_TRUST_LOAN("1", "循環型信託質借", "90", "03");

    /** 狀態碼* */
    private String code;

    /** 狀態說明* */
    private String memo;

    /** 貸款項目 */
    private String type;

    /** 貸款子項目 */
    private String loanTyp;

    /**
     * Constructor
     *
     * @param statusCode
     * @param memo
     */
    LoanAccountType(String code, String memo, String type, String loanTyp) {
        this.code = code;
        this.memo = memo;
        this.type = type;
        this.loanTyp = loanTyp;
    }

    @Override
    public String getMemo() {

        return null;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the loanTyp
     */
    public String getLoanTyp() {
        return loanTyp;
    }

    /**
     * @param loanTyp
     *            the loanTyp to set
     */
    public void setLoanTyp(String loanTyp) {
        this.loanTyp = loanTyp;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

}