package com.tfb.aibank.chl.creditcard.qu011.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCQU011010Rs.java
 * 
 * <p>Description:好多金總覧 010 好多金總覧-功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU011010Rs implements RsData {

    /** 好多金餘額 */
    private NCCQU011CostcoBalance costcoBalance;

    /** 好多金明細 */
    private List<NCCQU011CostcoDetail> costcoDetails;

    /** 是否「資料查詢失敗」 */
    private boolean isQueryFailed;

    /** 紀錄當前發查VB0052的「查詢月份」 */
    private String montyp;

    /** 信用卡清單 */
    private List<NCCQU011CreditCard> cardInfos;

    public NCCQU011CostcoBalance getCostcoBalance() {
        return costcoBalance;
    }

    public void setCostcoBalance(NCCQU011CostcoBalance costcoBalance) {
        this.costcoBalance = costcoBalance;
    }

    public boolean isQueryFailed() {
        return isQueryFailed;
    }

    public void setQueryFailed(boolean isQueryFailed) {
        this.isQueryFailed = isQueryFailed;
    }

    public String getMontyp() {
        return montyp;
    }

    public void setMontyp(String montyp) {
        this.montyp = montyp;
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
