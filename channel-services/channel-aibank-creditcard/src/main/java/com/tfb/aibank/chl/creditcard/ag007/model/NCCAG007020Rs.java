package com.tfb.aibank.chl.creditcard.ag007.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCAG007020Rs.java
 * 
 * <p>Description:一鍵綁定行動支付 020 判斷信用卡卡號是否可綁定</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/12, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG007020Rs implements RsData {

    /** 檢查綁定狀態 */
    private boolean checkBindingStatus;

    /** 卡片名稱 */
    private String cardName;

    /** 卡片效期 */
    private String cardExpire;

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

}
