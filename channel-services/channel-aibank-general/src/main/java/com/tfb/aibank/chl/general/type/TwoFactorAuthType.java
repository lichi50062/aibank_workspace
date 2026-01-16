package com.tfb.aibank.chl.general.type;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.type.I18NEnum;
import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

//@formatter:off
/**
* @(#)TwoFactorAuthStatus.java
* 
* <p>Description: 雙重驗證驗驗方式</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/02/17, Benson 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum TwoFactorAuthType implements IEnum, I18NEnum {
	
    DEVICE("DEVICE", "裝置驗證", ""),
    OTP("OTP", "OTP", "");
    
    

	/** 代碼 **/
    private String type;

    /** 說明* */
    private String memo;

    /** i18n 鍵值 */
    private String i18nKey;

    TwoFactorAuthType(String type, String memo, String i18nKey) {
        this.type = type;
        this.memo = memo;
        this.i18nKey = i18nKey;
    }

    @Override
    public String getMemo() {
        return this.memo;
    }

    public String getType() {
        return this.type;
    }

    public boolean isEquals(String type) {
        return StringUtils.equals(this.type, type);
    }

    public static TwoFactorAuthType find(String code) {
        for (TwoFactorAuthType type : TwoFactorAuthType.values()) {
            if (type.getType().equals(code)) {
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
