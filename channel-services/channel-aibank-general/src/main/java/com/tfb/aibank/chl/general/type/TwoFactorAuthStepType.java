package com.tfb.aibank.chl.general.type;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.type.I18NEnum;
import com.ibm.tw.commons.type.IEnum;

//@formatter:off
/**
* @(#)TwoFactorAuthStepType.java
* 
* <p>Description: 雙重驗證操作</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/02/17, Benson 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum TwoFactorAuthStepType implements IEnum, I18NEnum {
	
    INIT(1, "初始", ""),
    SEND_SUCCESS(2, "發送通知成功", ""),
    SEND_FAIL(3, "發送通知失敗", ""),
    WAIT(4, "待驗證", ""),
    SUCCESS(5, "成功", ""),
    FAIL(6, "失敗", ""),
    TIMEOUT(7, "TIMEOUT",""),
    CANCEL(8, "取消驗證", ""),
    ERROR(9,"錯誤", ""),
    
    INIT_EMAIL_SMS(11, "EMAIL SMS 初始", ""),
    WAIT_EMAIL_SMS(12, "EMAIL SMS 待驗證", ""),
    SUCCESS_EMAIL_SMS(13, "EMAIL SMS 成功", ""),
    FAIL_EMAIL_SMS(14, "EMAIL SMS 失敗", ""),
    TIMEOUT_EMAIL_SMS(15, "EMAIL SMS TIMEOUT", ""),
    CANCEL_EMAIL_SMS(16, "EMAIL SMS 取消驗證", ""),

    UNKNOWN(0, "未知", "");
    

	/** 代碼 **/
    private Integer code;

    /** 說明* */
    private String memo;

    /** i18n 鍵值 */
    private String i18nKey;

    TwoFactorAuthStepType(Integer code, String memo, String i18nKey) {
        this.code = code;
        this.memo = memo;
        this.i18nKey = i18nKey;
    }

    @Override
    public String getMemo() {
        return this.memo;
    }

    public Integer getCode() {
        return this.code;
    }

    public boolean isEquals(Integer code) {
        return this.code == code;
    }

    public static TwoFactorAuthStepType find(Integer code) {
        if (code == null) {
            return UNKNOWN;
        }
        for (TwoFactorAuthStepType type : TwoFactorAuthStepType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    @Override
    public String getI18NMemo() {
        return I18NUtils.getMessage(this.i18nKey);
    }


}
