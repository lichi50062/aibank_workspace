package com.tfb.aibank.chl.creditcard.ag009.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCAG009010Rs.java
 * 
 * <p>Description:信用卡掛失 首頁 RS</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/22, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@Component
public class NCCAG009010Rs implements RsData {
    /** 信用卡名稱 */
    private String cardName;
    /** 信用卡面 */
    private String cardType;
    /** 卡種 */
    private String cardCategory;
    /** 卡面 */
    private String cardFront;
    /** 傳入卡號 */
    private String cardNoInput;
    /** 正卡卡號 */
    private String primaryCardNo;
    /** 附卡卡號 */
    private List<String> supplementaryCardNos;
    /** 已綁定行動支付 */
    private Boolean isHceCard;
    /** 記名式悠遊卡 */
    private Boolean isTsccFlag;
    /** 卡號 */
    private String cardNo;

    /**
     * @return the cardName
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * @param cardName
     *            the cardName to set
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * @return the cardType
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * @param cardType
     *            the cardType to set
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     * @return the cardCategory
     */
    public String getCardCategory() {
        return cardCategory;
    }

    /**
     * @param cardCategory
     *            the cardCategory to set
     */
    public void setCardCategory(String cardCategory) {
        this.cardCategory = cardCategory;
    }

    /**
     * @return the cardFront
     */
    public String getCardFront() {
        return cardFront;
    }

    /**
     * @param cardFront
     *            the cardFront to set
     */
    public void setCardFront(String cardFront) {
        this.cardFront = cardFront;
    }

    /**
     * @return the cardNoInput
     */
    public String getCardNoInput() {
        return cardNoInput;
    }

    /**
     * @param cardNoInput
     *            the cardNoInput to set
     */
    public void setCardNoInput(String cardNoInput) {
        this.cardNoInput = cardNoInput;
    }

    /**
     * @return the primaryCardNo
     */
    public String getPrimaryCardNo() {
        return primaryCardNo;
    }

    /**
     * @param primaryCardNo
     *            the primaryCardNo to set
     */
    public void setPrimaryCardNo(String primaryCardNo) {
        this.primaryCardNo = primaryCardNo;
    }

    /**
     * @return the supplementaryCardNos
     */
    public List<String> getSupplementaryCardNos() {
        return supplementaryCardNos;
    }

    /**
     * @param supplementaryCardNos
     *            the supplementaryCardNos to set
     */
    public void setSupplementaryCardNos(List<String> supplementaryCardNos) {
        this.supplementaryCardNos = supplementaryCardNos;
    }

    /**
     * @return the isHceCard
     */
    public Boolean getIsHceCard() {
        return isHceCard;
    }

    /**
     * @param isHceCard
     *            the isHceCard to set
     */
    public void setIsHceCard(Boolean isHceCard) {
        this.isHceCard = isHceCard;
    }

    /**
     * @return the isTsccFlag
     */
    public Boolean getIsTsccFlag() {
        return isTsccFlag;
    }

    /**
     * @param isTsccFlag
     *            the isTsccFlag to set
     */
    public void setIsTsccFlag(Boolean isTsccFlag) {
        this.isTsccFlag = isTsccFlag;
    }

    /**
     * @return the cardNo
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * @param cardNo
     *            the cardNo to set
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

}
