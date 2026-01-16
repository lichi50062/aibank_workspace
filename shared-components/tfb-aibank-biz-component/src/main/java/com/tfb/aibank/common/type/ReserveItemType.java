/**
 * 
 */
package com.tfb.aibank.common.type;

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

//@formatter:off
/**
* @(#)OtpType.java
* 
* <p>Description:預約管理資料類別</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/21, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum ReserveItemType implements IEnum {

    TWD_TRANS("A", "臺幣轉帳"),

    FX_EXCHANGE("B", "換匯"),

    FX_TRANS("C", "外幣轉帳"),

    TWD_TIME_DEPOSIT("D", "臺幣定存"),

    FX_TIME_DEPOSIT("E", "外幣定存"),

    TIME_TERMINATE_DEPOSIT("F", "定存解約"),

    TAX_PAYMENT("G", "繳費/稅"),

    FX_REMIT("H", "外幣匯出匯款"),

    ALL("X", "全部"),

    ;

    /** 類型 */
    private String type;

    /** 狀態說明* */
    private String memo;

    ReserveItemType(String type, String memo) {
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

    public static ReserveItemType find(String type) {
        for (ReserveItemType aType : ReserveItemType.values()) {
            if (StringUtils.equals(aType.getType(), type)) {
                return aType;
            }
        }
        return null;
    }

    public static boolean isTwdTrans(String type) {
        return ReserveItemType.TWD_TRANS.getType().equals(type) || ReserveItemType.isALL(type);
    }

    public static boolean isFxExchange(String type) {
        return ReserveItemType.FX_EXCHANGE.getType().equals(type) || ReserveItemType.isALL(type);
    }

    public static boolean isFxTrans(String type) {
        return ReserveItemType.FX_TRANS.getType().equals(type) || ReserveItemType.isALL(type);
    }

    public static boolean isTwdTimeDeposit(String type) {
        return ReserveItemType.TWD_TIME_DEPOSIT.getType().equals(type) || ReserveItemType.isALL(type);
    }

    public static boolean isFxTimeDeposit(String type) {
        return ReserveItemType.FX_TIME_DEPOSIT.getType().equals(type) || ReserveItemType.isALL(type);
    }

    public static boolean isTimeTerminateDeposit(String type) {
        return ReserveItemType.TIME_TERMINATE_DEPOSIT.getType().equals(type) || ReserveItemType.isALL(type);
    }

    public static boolean isTaxPayment(String type) {
        return ReserveItemType.TAX_PAYMENT.getType().equals(type) || ReserveItemType.isALL(type);
    }

    public static boolean isFxRemit(String type) {
        return ReserveItemType.FX_REMIT.getType().equals(type) || ReserveItemType.isALL(type);
    }

    public static boolean isALL(String type) {
        return ReserveItemType.ALL.getType().equals(type);
    }
}
