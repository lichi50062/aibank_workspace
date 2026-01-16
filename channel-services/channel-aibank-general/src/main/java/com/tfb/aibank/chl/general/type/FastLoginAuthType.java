package com.tfb.aibank.chl.general.type;

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
public enum FastLoginAuthType implements IEnum, I18NEnum {
	
    PATTERN("1", "圖形密碼", ""),
    BIOMETRIC("2", "生物辨識", ""),
    THREE_LAYER("3", "三層式密碼", ""),
    SMS_OTP("4", "簡訊OTP", "");


	/** 代碼 **/
    private String code;

    /** 說明* */
    private String memo;

    /** i18n 鍵值 */
    private String i18nKey;

    FastLoginAuthType(String code, String memo, String i18nKey) {
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

    public static FastLoginAuthType find(String code) {
        for (FastLoginAuthType type : FastLoginAuthType.values()) {
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
