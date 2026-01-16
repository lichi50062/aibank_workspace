package com.tfb.aibank.chl.creditcard.ag012.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCAG012010Rs.java
 * 
 * <p>Description:信用卡交易設定 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/11, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG012010Rs implements RsData {
    /** 是否編輯頁 */
    private Boolean isGoToEdit = false;

    /** 卡片資訊 */
    private NCCAG012CardInfo cardInfo;

    /** 卡片資訊集 */
    private List<NCCAG012CardCollect> cardCollect;

    /**
     * @return the isGoToEdit
     */
    public Boolean getIsGoToEdit() {
        return isGoToEdit;
    }

    /**
     * @param isGoToEdit
     *            the isGoToEdit to set
     */
    public void setIsGoToEdit(Boolean isGoToEdit) {
        this.isGoToEdit = isGoToEdit;
    }

    /**
     * @return the cardInfo
     */
    public NCCAG012CardInfo getCardInfo() {
        return cardInfo;
    }

    /**
     * @param cardInfo
     *            the cardInfo to set
     */
    public void setCardInfo(NCCAG012CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    /**
     * @return the cardCollect
     */
    public List<NCCAG012CardCollect> getCardCollect() {
        return cardCollect;
    }

    /**
     * @param cardCollect
     *            the cardCollect to set
     */
    public void setCardCollect(List<NCCAG012CardCollect> cardCollect) {
        this.cardCollect = cardCollect;
    }

}
