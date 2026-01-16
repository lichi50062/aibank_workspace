package com.tfb.aibank.chl.creditcard.qu009.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tfb.aibank.chl.creditcard.qu009.model.NCCQU009DebitCardModel;
import com.tfb.aibank.chl.creditcard.qu009.model.NCCQU009PurchaseModel;
import com.tfb.aibank.chl.creditcard.resource.dto.FEP852835Repeat;

// @formatter:off
/**
 * @(#)NCCQU009CacheData.java
 * 
 * <p>Description:簽帳金融卡消費明細 cache</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/22, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on

public class NCCQU009CacheData {

    /** 信用卡性質 */
    private List<FEP852835Repeat> cardStatusList;

    /** 簽帳卡資料 ex: 最新=><tabId+accno,資料> , 本期=><tabId+accno,資料> */
    private Map<String, List<NCCQU009DebitCardModel>> debitCardModelMap = new HashMap<>();

    /** 單月資料Map ex: 最新=><tabId+accno,資料> */
    private Map<String, NCCQU009PurchaseModel> purchaseByMonthMap = new HashMap<>();

    /**
     * @return the cardStatusList
     */
    public List<FEP852835Repeat> getCardStatusList() {
        return cardStatusList;
    }

    /**
     * @param cardStatusList
     *            the cardStatusList to set
     */
    public void setCardStatusList(List<FEP852835Repeat> cardStatusList) {
        this.cardStatusList = cardStatusList;
    }

    /**
     * @return the debitCardModelMap
     */
    public Map<String, List<NCCQU009DebitCardModel>> getDebitCardModelMap() {
        return debitCardModelMap;
    }

    /**
     * @param debitCardModelMap
     *            the debitCardModelMap to set
     */
    public void setDebitCardModelMap(Map<String, List<NCCQU009DebitCardModel>> debitCardModelMap) {
        this.debitCardModelMap = debitCardModelMap;
    }

    /**
     * @return the purchaseByMonthMap
     */
    public Map<String, NCCQU009PurchaseModel> getPurchaseByMonthMap() {
        return purchaseByMonthMap;
    }

    /**
     * @param purchaseByMonthMap
     *            the purchaseByMonthMap to set
     */
    public void setPurchaseByMonthMap(Map<String, NCCQU009PurchaseModel> purchaseByMonthMap) {
        this.purchaseByMonthMap = purchaseByMonthMap;
    }

}
