/**
 * 
 */
package com.tfb.aibank.common.type;

import com.ibm.tw.commons.type.IEnum;

//@formatter:off
/**
* @(#)AtferLoginJobType.java
* 
* <p>Description:登入後作業</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/27, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum AtferLoginJobType implements IEnum {

    CHANG_USERID_PINBLOCK("1", "變更使用者代號及密碼"),

    CHANG_USERID("2", "變更使用者代號"),

    CHANG_PINBLOCK("3", "變更密碼"),

    RE_LOGIN("4", "解除快登，重新以帳密登入");

    /** 作業別 */
    private String type;

    /** 狀態說明* */
    private String memo;

    AtferLoginJobType(String type, String memo) {
        this.type = type;
        this.memo = memo;
    }

    @Override
    public String getMemo() {
        return this.memo;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    public static boolean isChangeUseridAndPinBlock(String type) {
        if (AtferLoginJobType.CHANG_USERID_PINBLOCK.getType().equals(type))
            return true;
        return false;
    }

    public static boolean isChangeUserid(String type) {
        if (AtferLoginJobType.CHANG_USERID.getType().equals(type))
            return true;
        return false;
    }

    public static boolean isChangePinBlock(String type) {
        if (AtferLoginJobType.CHANG_PINBLOCK.getType().equals(type))
            return true;
        return false;
    }

    public static boolean isReLogin(String type) {
        if (AtferLoginJobType.RE_LOGIN.getType().equals(type))
            return true;
        return false;
    }

    public static AtferLoginJobType find(String type) {
        for (AtferLoginJobType group : AtferLoginJobType.values()) {
            if (group.getType().equals(type)) {
                return group;
            }
        }
        return null;
    }

}