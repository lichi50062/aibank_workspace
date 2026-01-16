package com.tfb.aibank.chl.creditcard.tx004.model;

// @formatter:off
import java.util.Locale; /**
 * @(#)NCCQU014Output.java
 * 
 * <p>Description:輸出專用物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/08
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCTX004Input {

    private Locale locale;

    /**
     * CEW316R
     * 交易別
     * (1) 	上送A：新增車號
     * (2) 	上送U：變更車號
     * (3) 	上送D：刪除車號
     * */
    private String txtType;

    /**
     * 進入功能時外部傳入的卡號
     */
    private String cardKey;

    private String carNo;

    private CreditCardData creditCardData;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getTxKindForCardCarnoApply() {
        var result = switch (getTxtType()) {
            case "A" -> "0";
            case "U" -> "1";
            case "D" -> "2";
            default -> "";
        };
        return result;
    }

    public String getTxtType() {
        return txtType;
    }

    public void setTxtType(String txtType) {
        this.txtType = txtType;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public CreditCardData getCreditCardData() {
        return creditCardData;
    }

    public void setCreditCardData(CreditCardData creditCardData) {
        this.creditCardData = creditCardData;
    }

    public String getCardKey() {
        return cardKey;
    }

    public void setCardKey(String cardKey) {
        this.cardKey = cardKey;
    }
}
