/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.biz.type;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.type.I18NEnum;
import com.ibm.tw.commons.type.IEnum;

//@formatter:off
/**
* @(#)TwoFactorAuthResponseType.java
* 
* <p>Description: 雙重驗證登入 type</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/05/26, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum TwoFactorAuthResponseType implements IEnum, I18NEnum {

    SUCCESS("0000", "處理完成", "two_factor.success"),

    COMPANY_KEY_CANNOT_BE_EMPTY("0001", "CompanyKey不可為空白", "two_factor.company_key_cannot_be_empty"),

    USER_KEY_CANNOT_BE_EMPTY("0002", "UserKey不可為空白", "two_factor.user_key_cannot_be_empty"),

    PUSH_TITLE_CANNOT_BE_EMPTY("0003", "推播主旨不可為空白", "two_factor.push_title_cannot_be_empty"),

    PUSH_CONTENT_CANNOT_BE_EMPTY("0004", "推播內容不可為空白", "two_factor.push_content_cannot_be_empty"),

    UNABLE_TO_FIND_BIND_INFO("0005", "查無使用者綁定資訊", "two_factor.unable_to_find_bind_info"),

    PUSH_NOTIFICATION_DISABLED("0006", "使用者未開啟推播設定", "two_factor.push_notification_disabled"),

    UNKNOWN_ERROR("9999", "推送失敗", "two_factor.unknown_error");

    private TwoFactorAuthResponseType(String code, String memo, String i18nKey) {
        this.code = code;
        this.memo = memo;
        this.i18nKey = i18nKey;
    }

    private String code;

    private String memo;

    private String i18nKey;

    public static TwoFactorAuthResponseType find(String code) {
        for (TwoFactorAuthResponseType type : TwoFactorAuthResponseType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMemo() {
        return memo;
    }

    public String getI18nKey() {
        return i18nKey;
    }

    @Override
    public String getI18NMemo() {
        return I18NUtils.getMessage(i18nKey);
    }

}
