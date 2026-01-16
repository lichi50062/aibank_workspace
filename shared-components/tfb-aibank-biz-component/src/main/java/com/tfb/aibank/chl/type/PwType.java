/**
 * 
 */
package com.tfb.aibank.chl.type;

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

//@formatter:off
/**
* @(#)PwType.java
* 
* <p>Description:個人化通知訊息 - 密碼檢核類型</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/04, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum PwType implements IEnum {

    PASSWORD("0", "帳密"),

    PATTERN("1", "圖型"),

    FACI_ID("2", "Face ID"),

    TOUCH_ID("3", "Touch ID"),

    FINGER("4", "指紋"),

    BIO_PASSORD("5", "綁定下的密碼"),

    UNKNOWN(UNKNOWN_STR_CODE, "未知"),

    ;

    private String code;

    private String memo;

    private PwType(String code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public static PwType findByCode(String code) {
        for (PwType type : PwType.values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public static boolean isPattern(String code) {
        return findByCode(code) == PwType.PATTERN;
    }

    public static boolean isPattern(int code) {
        return findByCode(Integer.toString(code)) == PwType.PATTERN;
    }

    public static boolean isBio(String code) {
        return (findByCode(code) == PwType.FACI_ID || findByCode(code) == PwType.TOUCH_ID || findByCode(code) == PwType.FINGER);
    }

    public static boolean isPassword(String code) {
        return findByCode(code) == PwType.PASSWORD;
    }

    @Override
    public String getMemo() {
        return this.memo;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

}