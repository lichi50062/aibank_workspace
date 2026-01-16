/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.common.type;

import java.util.Arrays;

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)ServiceLocationType.java
 * 
 * <p>Description:服務據點</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/14, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum ServiceLocationType implements IEnum {

    ATM_CASH_FUNCTION("提款功能ATM", "提款機"),

    ATM_DEPOSIT_FUNCTION("存款功能ATM", "存款機"),

    COMPENSATION_FOLDING_MACHINE("補摺機", "補摺"),

    SAFE_DEPOSIT_BOX("保管箱", "保管箱"),

    CORPORATE_FINANCIAL_SERVICE("法人金融服務", "法金"),

    BILINGUAL_BRANCH("雙語分行", "雙語"),

    STUDENT_LOAN_GUARANTEE("就學貸款對保據點", "學貸"),

    OVERSEAS("海外地區", "海外"),

    TREASURY_AGENCY("國庫經辦行", "國庫"),

    ATM_FOREIGN_SERVICE("外幣ATM", "外幣ATM"),

    // ========== 以上10個屬性順序，不可變動 ==========

    BRANCH_OFFICE("分行據點", "分行據點"),

    UNKNOWN(UNKNOWN_STR_CODE, UNKNOWN_STR_CODE);

    ServiceLocationType(String nameF, String nameS) {
        this.nameF = nameF;
        this.nameS = nameS;
    }

    /** 服務據點名稱(完整) */
    private String nameF;

    /** 服務據點名稱(簡短) */
    private String nameS;

    /**
     * 以 服務據點名稱(完整) 反查
     * 
     * @param nameF
     * @return
     */
    public static ServiceLocationType findBy(String nameF) {
        return Arrays.asList(ServiceLocationType.values()).stream().filter(x -> StringUtils.equals(x.getNameF(), nameF)).findAny().orElse(UNKNOWN);
    }

    @Override
    public String getMemo() {
        return nameF;
    }

    public String getNameF() {
        return nameF;
    }

    public String getNameS() {
        return nameS;
    }

    public boolean isUnknown() {
        return equals(UNKNOWN);
    }
}
