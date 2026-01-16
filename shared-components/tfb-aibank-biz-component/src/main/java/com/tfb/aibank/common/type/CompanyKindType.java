/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2011.
 *
 * ===========================================================================
 */
package com.tfb.aibank.common.type;

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)CompanyKindType.java
 * 
 * <p>Description:公司類型</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum CompanyKindType implements IEnum {

    COMPANY(1, "企業戶"),

    PERSONAL(2, "個人戶"),

    PRIMARY(3, "信用卡會員之正卡人"),

    SECONDARY(4, "信用卡會員之附卡人"),

    FINI(5, "FINI員工持股信託"),

    WEBATM(6, "WebATM"),

    CES_COMPANY(7, "CES-員工儲蓄信託專區"),

    CAF_COMPANY(8, "CAF-信用貸款線上申請平台"),

    CAH_COMPANY(9, "CAH-房貸增貸線上申請平台"),

    CAC_COMPANY(10, "CAC-信用卡線上申請平台"),

    CST_COMPANY(11, "媒體薪轉e區"),

    CME_COMPANY(11, "富邦新興商務網"),

    CAA_COMPANY(12, "CAA-線上開立數位存款帳戶平台"),

    COA_COMPANY(13, "COA-企業戶線上同意查詢聯徵"),

    UNKNOWN(UNKNOWN_INT_CODE, "未知");

    /** 代碼 */
    private int code;
    /** 說明 */
    private String memo;

    CompanyKindType(int code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public static CompanyKindType find(int code) {
        for (CompanyKindType group : CompanyKindType.values()) {
            if (group.getCode() == code) {
                return group;
            }
        }
        return CompanyKindType.UNKNOWN;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 是否為公司戶
     *
     * @return
     */
    public boolean isCompany() {
        return equals(CompanyKindType.COMPANY);
    }

    /**
     * 是否為個人戶
     *
     * @return
     */
    public boolean isPersonal() {
        return equals(CompanyKindType.PERSONAL);
    }

    /**
     * 是否為信用卡正卡人
     * 
     * @return
     */
    public boolean isPrimary() {
        return equals(CompanyKindType.PRIMARY);
    }

    /**
     * 是否為信用卡附卡人
     * 
     * @return
     */
    public boolean isSecondary() {
        return equals(CompanyKindType.SECONDARY);
    }

    /**
     * 是否為信用卡會員
     * 
     * @return
     */
    public boolean isCreditCardMember() {
        return (equals(CompanyKindType.PRIMARY) || equals(CompanyKindType.SECONDARY));
    }

    /**
     * 是否為 WebATM
     * 
     * @return
     */
    public boolean isWebATM() {
        return equals(CompanyKindType.WEBATM);
    }

    /**
     * 是否為 FINI
     * 
     * @return
     */
    public boolean isFINI() {
        return equals(CompanyKindType.FINI);
    }

}
