package com.tfb.aibank.chl.component.security.sso.type;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.type.I18NEnum;
import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

//@formatter:off
/**
* @(#)FastLoginAuthType.java
* 
* <p>Description: 快速登入認證種類</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/02/17, Benson 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum SsoLoginAuthType implements IEnum, I18NEnum {
	
    PATTERN("1", "圖形密碼", ""),
    BIOMETRIC("2", "生物辨識", ""),
    THREE_LAYER("3", "三層式密碼", ""),
    SMS_OTP("4", "簡訊OTP", ""),
    UNKNOWN("0", "未知", "");


	/** 代碼 **/
    private String code;

    /** 說明* */
    private String memo;

    /** i18n 鍵值 */
    private String i18nKey;

    SsoLoginAuthType(String code, String memo, String i18nKey) {
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
        return this.code.equals(code);
    }

    public boolean isQuickLogin() {
        if (SsoLoginAuthType.BIOMETRIC.getCode().equals(this.code) || 
                SsoLoginAuthType.PATTERN.getCode().equals(this.code) ) {
            return true;
        }
        return false;
    }
    
    public boolean isThreeLayer() {
        if (SsoLoginAuthType.THREE_LAYER.getCode().equals(this.code)) {
            return true;
        }
        return false;
    }
    
    public static SsoLoginAuthType find(String code) {
        for (SsoLoginAuthType type : SsoLoginAuthType.values()) {
            if (type.getCode().equals(code) ){
                return type;
            }
        }
        return SsoLoginAuthType.UNKNOWN;
    }

    @Override
    public String getI18NMemo() {
        return I18NUtils.getMessage(this.i18nKey);
    }


}
