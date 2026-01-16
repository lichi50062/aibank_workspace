package com.tfb.aibank.chl.creditcard.qu011.model;

import java.util.List;

// @formatter:off
/**
 * @(#)NCCQU011Output.java
 * 
 * <p>Description:好多金總覧 輸出專用物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU011Output {

    /** 好多金餘額 */
    private NCCQU011CostcoBalance costcoBalance;

    /** 好多金明細 */
    private List<NCCQU011CostcoDetail> costcoDetails;

    /** 期數 */
    private List<String> periodList;

    /** 信用卡清單 */
    private List<NCCQU011CreditCard> cardInfos;

    public NCCQU011CostcoBalance getCostcoBalance() {
        return costcoBalance;
    }

    public void setCostcoBalance(NCCQU011CostcoBalance costcoBalance) {
        this.costcoBalance = costcoBalance;
    }

    public List<String> getPeriodList() {
        return periodList;
    }

    public void setPeriodList(List<String> periodList) {
        this.periodList = periodList;
    }

    public List<NCCQU011CostcoDetail> getCostcoDetails() {
        return costcoDetails;
    }

    public void setCostcoDetails(List<NCCQU011CostcoDetail> costcoDetails) {
        this.costcoDetails = costcoDetails;
    }

    public List<NCCQU011CreditCard> getCardInfos() {
        return cardInfos;
    }

    public void setCardInfos(List<NCCQU011CreditCard> cardInfos) {
        this.cardInfos = cardInfos;
    }

}
