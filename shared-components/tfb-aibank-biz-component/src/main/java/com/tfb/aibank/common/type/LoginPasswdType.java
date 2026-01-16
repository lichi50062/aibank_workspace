package com.tfb.aibank.common.type;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)LoginPasswdType.java
 * 
 * <p>Description:裝置驗證方式</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/13, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum LoginPasswdType implements IEnum {

    /** 手勢登入 */
    GRAPH(1, "login_type.graph"),

    /** Touch ID */
    TOUCHID(2, "Touch ID"),

    /** Face ID */
    FACEID(3, "Face ID"),

    /** 生物辨識 */
    BIOMETRICS(4, "login_type.biometrics"),

    UNKNOWN(UNKNOWN_INT_CODE, "Unknown");

    private int code;

    private String memo;

    LoginPasswdType(int code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public static LoginPasswdType find(int code) {
        for (LoginPasswdType type : LoginPasswdType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return LoginPasswdType.UNKNOWN;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMemo() {
        if (equals(GRAPH) || equals(BIOMETRICS)) {
            return I18NUtils.getMessage(this.memo);
        }
        else {
            return memo;
        }
    }
}
