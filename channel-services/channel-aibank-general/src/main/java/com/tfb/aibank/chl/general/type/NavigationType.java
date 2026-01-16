package com.tfb.aibank.chl.general.type;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.type.I18NEnum;
import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

//@formatter:off
/**
* @(#)NavigationType.java
* 
* <p>Description: 導頁種類</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/02/17, Benson 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum NavigationType implements IEnum, I18NEnum {
	
    LINK_SSO("1", "SSO外開", ""),
    LINK("2", "外開", ""),
    UNKNOWN("", "未知", "");
    
	/** 代碼 **/
    private String code;

    /** 說明* */
    private String memo;

    /** i18n 鍵值 */
    private String i18nKey;

    NavigationType(String code, String memo, String i18nKey) {
        this.code = code;
        this.memo = memo;
        this.i18nKey = i18nKey;
    }

    @Override
    public String getMemo() {
        return this.memo;
    }

    public String getCode() {
        return this.code;
    }

    public boolean isEquals(String code) {
        return StringUtils.equals(this.code, code);
    }

    public static NavigationType find(String code) {
        if (StringUtils.isBlank(code)) {
            return NavigationType.UNKNOWN;
        }
        for (NavigationType type : NavigationType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return NavigationType.UNKNOWN;
    }

    @Override
    public String getI18NMemo() {
        return I18NUtils.getMessage(this.i18nKey);
    }


}
