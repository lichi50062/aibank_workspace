package com.ibm.tw.ibmb.type;

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)MenuCategory.java
 * 
 * <p>Description:選單大類(category)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/26, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum MenuCategory implements IEnum {

    AIBANK_LOGIN("AIBANK_LOGIN", "AI Bank登入後選單"),

    AIDBU_PS("AIDBU_PS", "一般客戶設定用選單"),

    AICCU_PS("AICCU_PS", "信用卡客戶設定用選單"),

    AIDBU_PS_EN("AIDBU_PS_EN", "一般客戶設定用英文選單"),

    AICCU_PS_EN("AICCU_PS_EN", "信用卡客戶設定用英文選單"),

    AIDBU("AIDBU", "一般客戶用選單"),

    AICCU("AICCU", "信用卡網路會員選單"),

    AIDBU_EN("AIDBU_EN", "一般客戶用英文選單"),

    AICCU_EN("AICCU_EN", "信用卡網路會員英文選單");

    private String category;

    private String memo;

    MenuCategory(String category, String memo) {
        this.category = category;
        this.memo = memo;
    }

    @Override
    public String getMemo() {
        return memo;
    }

    public String getCategory() {
        return category;
    }

}
