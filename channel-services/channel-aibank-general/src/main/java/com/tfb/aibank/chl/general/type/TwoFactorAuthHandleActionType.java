package com.tfb.aibank.chl.general.type;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.type.I18NEnum;
import com.ibm.tw.commons.type.IEnum;

//@formatter:off
/**
* @(#)TwoFactorAuthActionType.java
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
public enum TwoFactorAuthHandleActionType implements IEnum, I18NEnum {
	
    INIT(1, "新增", ""),
    QUERY(2, "查詢", ""),
    UPDATE(3, "更新", ""),
    UNKNOWN(0, "未知", "");
    

	/** 代碼 **/
    private Integer code;

    /** 說明* */
    private String memo;

    /** i18n 鍵值 */
    private String i18nKey;

    TwoFactorAuthHandleActionType(Integer code, String memo, String i18nKey) {
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

    public static TwoFactorAuthHandleActionType find(Integer code) {
        for (TwoFactorAuthHandleActionType type : TwoFactorAuthHandleActionType.values()) {
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
