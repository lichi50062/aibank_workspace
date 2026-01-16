package com.tfb.aibank.chl.creditcard.ag007.model;

// @formatter:off
/**
 * @(#)NCCAG007Output.java
 * 
 * <p>Description:一鍵綁定行動支付 輸出專用物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/08, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCAG007Output {

    /** 檢查信用卡狀態，判斷是否為「特殊戶」或「未持有信用卡」 */
    private boolean checkCreditCardStatus;

    /** SYSTEM_PARAM.PARAM_VALUE */
    private String paramValue;

    /** 檢查綁定狀態 */
    private boolean checkBindingStatus;

    /** 卡片名稱 */
    private String cardName;

    /** 卡片效期 */
    private String cardExpire;

    /** 英文姓名 */
    private String engName;

    public boolean isCheckCreditCardStatus() {
        return checkCreditCardStatus;
    }

    public void setCheckCreditCardStatus(boolean checkCreditCardStatus) {
        this.checkCreditCardStatus = checkCreditCardStatus;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public boolean isCheckBindingStatus() {
        return checkBindingStatus;
    }

    public void setCheckBindingStatus(boolean checkBindingStatus) {
        this.checkBindingStatus = checkBindingStatus;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardExpire() {
        return cardExpire;
    }

    public void setCardExpire(String cardExpire) {
        this.cardExpire = cardExpire;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

}
