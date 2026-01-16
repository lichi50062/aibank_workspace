package com.tfb.aibank.chl.creditcard.qu009.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCQU009011Rs.java
 * 
 * <p>Description:簽帳金融卡消費明細 011 取資料明細</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/18, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU009011Rs implements RsData {

    /**
     * 單月消費明細
     */
    private NCCQU009PurchaseModel purchaseDatas;

    /**
     * 簽帳卡資料
     */
    private List<NCCQU009DebitCardModel> debitCardModels;

    /**
     * @return the purchaseDatas
     */
    public NCCQU009PurchaseModel getPurchaseDatas() {
        return purchaseDatas;
    }

    /**
     * @param purchaseDatas
     *            the purchaseDatas to set
     */
    public void setPurchaseDatas(NCCQU009PurchaseModel purchaseDatas) {
        this.purchaseDatas = purchaseDatas;
    }

    /**
     * @return the debitCardModels
     */
    public List<NCCQU009DebitCardModel> getDebitCardModels() {
        return debitCardModels;
    }

    /**
     * @param debitCardModels
     *            the debitCardModels to set
     */
    public void setDebitCardModels(List<NCCQU009DebitCardModel> debitCardModels) {
        this.debitCardModels = debitCardModels;
    }

}
