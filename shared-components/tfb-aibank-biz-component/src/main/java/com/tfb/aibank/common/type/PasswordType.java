/**
 * 
 */
package com.tfb.aibank.common.type;

import com.ibm.tw.commons.type.IEnum;

//@formatter:off
/**
* @(#)OtpType.java
* 
* <p>Description:OTP安控類別</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/27, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum PasswordType implements IEnum {
    PLAINTEXT(0, "文字密碼"), PATTERN(1, "手勢密碼"), FINGER(2, "指紋"), FACEID(3, "臉部"), ANDROID(4, "Android生物辨識"), UNKNOWN(-1, "未知");

    /** 代碼 */
    private int type;

    /** 說明 */
    private String memo;

    PasswordType(int type, String memo) {
        this.type = type;
        this.memo = memo;
    }

    public static PasswordType find(int type) {

        for (PasswordType group : PasswordType.values()) {

            if (group.getType() == type) {
                return group;
            }
        }

        return PasswordType.UNKNOWN;

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
