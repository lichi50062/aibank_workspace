/**
 * 
 */
package com.tfb.aibank.common.type;

import com.ibm.tw.commons.type.IEnum;

//@formatter:off
/**
* @(#)ChangLogChangeType.java
* 
* <p>Description:CHANGE_PASSWORD_RECORD CHANGE_ITEM 項目</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/27, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum ChangRecordItemType implements IEnum {

    CHANG_PINBLOCK("0", "網銀密碼"),

    CHANG_USERID("1", "網銀使用者代碼"),

    CHANG_USERID_PINBLOCK("2", "網銀使用者代碼與網銀密碼"),;

    ChangRecordItemType(String type, String memo) {
        this.type = type;
        this.memo = memo;
    }

    /** 作業別 */
    private String type;

    /** 狀態說明* */
    private String memo;

    /*
     * (non-Javadoc)
     * 
     * @see com.ibm.tw.commons.type.IEnum#getMemo()
     */
    @Override
    public String getMemo() {
        // TODO Auto-generated method stub
        return null;
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
        if (ChangRecordItemType.CHANG_USERID_PINBLOCK.getType().equals(type))
            return true;
        return false;
    }

    public static boolean isChangeUserid(String type) {
        if (ChangRecordItemType.CHANG_USERID.getType().equals(type))
            return true;
        return false;
    }

    public static boolean isChangePinBlock(String type) {
        if (ChangRecordItemType.CHANG_PINBLOCK.getType().equals(type))
            return true;
        return false;
    }

}
