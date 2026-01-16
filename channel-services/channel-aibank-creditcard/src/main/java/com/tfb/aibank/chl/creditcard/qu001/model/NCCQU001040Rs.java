package com.tfb.aibank.chl.creditcard.qu001.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCQU001040Rs.java
* 
* <p>Description:信用卡總覽 歷史帳單頁 RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU001040Rs implements RsData {
    /** 識別資訊(唯一值) */
    private String cardKey;
    /**
     * 歷史帳單
     */
    private List<NCCQQU001HistoricalBill> historicalBills;
    /**
     * costco錯誤
     */
    private boolean costcoError;
    /**
     * 正卡
     */
    private boolean primaryCard;

    /** 紅利回饋資料發生錯誤 */
    private boolean cew306rError;

    /** 紅利回饋 */
    private NCCQQU001BonusRewards bonusRewards;

    /**
     * @return the historicalBills
     */
    public List<NCCQQU001HistoricalBill> getHistoricalBills() {
        return historicalBills;
    }

    /**
     * @param historicalBills
     *            the historicalBills to set
     */
    public void setHistoricalBills(List<NCCQQU001HistoricalBill> historicalBills) {
        this.historicalBills = historicalBills;
    }

    /**
     * @return the costcoError
     */
    public boolean isCostcoError() {
        return costcoError;
    }

    /**
     * @param costcoError
     *            the costcoError to set
     */
    public void setCostcoError(boolean costcoError) {
        this.costcoError = costcoError;
    }

    /**
     * @return the primaryCard
     */
    public boolean isPrimaryCard() {
        return primaryCard;
    }

    /**
     * @param primaryCard
     *            the primaryCard to set
     */
    public void setPrimaryCard(boolean primaryCard) {
        this.primaryCard = primaryCard;
    }

    /**
     * @return the cew306rError
     */
    public boolean isCew306rError() {
        return cew306rError;
    }

    /**
     * @param cew306rError
     *            the cew306rError to set
     */
    public void setCew306rError(boolean cew306rError) {
        this.cew306rError = cew306rError;
    }

    /**
     * @return the cardKey
     */
    public String getCardKey() {
        return cardKey;
    }

    /**
     * @param cardKey
     *            the cardKey to set
     */
    public void setCardKey(String cardKey) {
        this.cardKey = cardKey;
    }

    /**
     * @return the bonusRewards
     */
    public NCCQQU001BonusRewards getBonusRewards() {
        return bonusRewards;
    }

    /**
     * @param bonusRewards
     *            the bonusRewards to set
     */
    public void setBonusRewards(NCCQQU001BonusRewards bonusRewards) {
        this.bonusRewards = bonusRewards;
    }

}
