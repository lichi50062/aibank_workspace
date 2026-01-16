package com.tfb.aibank.chl.general.type;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.type.I18NEnum;
import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

//@formatter:off
/**
* @(#)TwoFactorChannelType.java
* 
* <p>Description: 雙重驗證通;備</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/02/17, Benson 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:TwoFactorChannelType.java
public enum TwoFactorChannelType implements IEnum, I18NEnum {
	
    APP("APP", "APP", "");

	/** 代碼 **/
    private String code;

    /** 說明* */
    private String memo;

    /** i18n 鍵值 */
    private String i18nKey;

    TwoFactorChannelType(String code, String memo, String i18nKey) {
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

    public static TwoFactorChannelType find(String code) {
        for (TwoFactorChannelType type : TwoFactorChannelType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String getI18NMemo() {
        return I18NUtils.getMessage(this.i18nKey);
    }


}
