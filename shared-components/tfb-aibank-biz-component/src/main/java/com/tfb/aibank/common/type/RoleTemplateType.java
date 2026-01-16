package com.tfb.aibank.common.type;

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)RoleTemplateType.java
 * 
 * <p>Description:角色模版類別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/25, Horance Chou	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum RoleTemplateType implements IEnum {

    AI_PERSON_ROLE_TEMPLATE("AI_PERSON_ROLE", "AIBank 個人戶角色模版"),

    AI_CARD_MEMBER_ROLE_TEMPLATE("AI_CARD_MEMBER_ROLE", "AIBank 信用卡會員角色模版");

    private String name;
    private String memo;

    private RoleTemplateType(String name, String memo) {
        this.name = name;
        this.memo = memo;
    }

    @Override
    public String getMemo() {
        return memo;
    }

    /**
     * 取得 name
     *
     * @return 傳回 name。
     */
    public String getName() {
        return name;
    }

    public static RoleTemplateType find(String name) {
        for (RoleTemplateType type : RoleTemplateType.values()) {
            if (StringUtils.equals(type.getName(), name)) {
                return type;
            }
        }
        return null;
    }

}
