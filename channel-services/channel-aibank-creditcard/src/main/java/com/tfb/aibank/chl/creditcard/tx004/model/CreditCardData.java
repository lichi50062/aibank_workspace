package com.tfb.aibank.chl.creditcard.tx004.model;

//@formatter:off
import com.tfb.aibank.chl.creditcard.resource.dto.CEW316RRepeat; /**
* @(#)CreditCardData.java
* 
* <p>Description:道路救援登錄</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/24,
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CreditCardData {

    /**
     * 卡號
     */
    private String cardNo;

    /**
     * 卡號
     */
    private String cardNoMask;

    /** 外部傳入卡號的指定卡 */
    private boolean designateCard;

    /**
     * 卡別
     */
    private String cardType;

    /**
     * 信用卡名稱
     */
    private String cardName;
    /**
     * 卡種
     */
    private String cardCategory;
    /**
     * 卡面
     */
    private String cardFace;

    /**
     * 卡片效期
     */
    private String cardExpired;

    /**
     * 暱稱
     */
    private String cardNickName;

    /**
     * 正卡項下副卡
     */
    private boolean underPrimaryCard;

    /**
     * 附卡
     */
    private boolean additional;

    /**
     * 虛擬卡
     */
    private boolean virtualCard;

    /**
     * 正卡卡號
     */
    private String mCardNo;

    /**
     * 正卡項下附卡
     */
    private boolean parentCard;

    private CEW316RRepeat roadsideAssistData;

    /** 識別資訊(唯一值) */
    private String cardKey;

    /**
     * 已登錄道路救援
     */
    private boolean registered;

    /**
     * @return the cardNo
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * @param cardNo the cardNo to set
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * @return the cardName
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * @param cardName the cardName to set
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * @return the cardCategory
     */
    public String getCardCategory() {
        return cardCategory;
    }

    /**
     * @param cardCategory the cardCategory to set
     */
    public void setCardCategory(String cardCategory) {
        this.cardCategory = cardCategory;
    }

    /**
     * @return the cardExpired
     */
    public String getCardExpired() {
        return cardExpired;
    }

    /**
     * @param cardExpired the cardExpired to set
     */
    public void setCardExpired(String cardExpired) {
        this.cardExpired = cardExpired;
    }

    /**
     * @return the cardNickName
     */
    public String getCardNickName() {
        return cardNickName;
    }

    /**
     * @param cardNickName the cardNickName to set
     */
    public void setCardNickName(String cardNickName) {
        this.cardNickName = cardNickName;
    }

    /**
     * @return the additional
     */
    public boolean isAdditional() {
        return additional;
    }

    /**
     * @param additional the additional to set
     */
    public void setAdditional(boolean additional) {
        this.additional = additional;
    }

    /**
     * @return the virtualCard
     */
    public boolean isVirtualCard() {
        return virtualCard;
    }

    /**
     * @param virtualCard the virtualCard to set
     */
    public void setVirtualCard(boolean virtualCard) {
        this.virtualCard = virtualCard;
    }


    /**
     * @return the cardFace
     */
    public String getCardFace() {
        return cardFace;
    }

    /**
     * @param cardFace the cardFace to set
     */
    public void setCardFace(String cardFace) {
        this.cardFace = cardFace;
    }

    /**
     * @return the mCardNo
     */
    public String getMCardNo() {
        return mCardNo;
    }

    /**
     * @param mCardNo the mCardNo to set
     */
    public void setMCardNo(String mCardNo) {
        this.mCardNo = mCardNo;
    }


    /**
     * @return the parentCard
     */
    public boolean isParentCard() {
        return parentCard;
    }

    /**
     * @param parentCard the parentCard to set
     */
    public void setParentCard(boolean parentCard) {
        this.parentCard = parentCard;
    }


    /**
     * @return the cardType
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * @param cardType the cardType to set
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }


    /**
     * @return the underPrimaryCard
     */
    public boolean isUnderPrimaryCard() {
        return underPrimaryCard;
    }

    /**
     * @param underPrimaryCard the underPrimaryCard to set
     */
    public void setUnderPrimaryCard(boolean underPrimaryCard) {
        this.underPrimaryCard = underPrimaryCard;
    }

    public String getmCardNo() {
        return mCardNo;
    }

    public void setmCardNo(String mCardNo) {
        this.mCardNo = mCardNo;
    }

    public CEW316RRepeat getRoadsideAssistData() {
        return roadsideAssistData;
    }

    public void setRoadsideAssistData(CEW316RRepeat roadsideAssistData) {
        this.roadsideAssistData = roadsideAssistData;
    }

    public String getCardNoMask() {
        return cardNoMask;
    }

    public void setCardNoMask(String cardNoMask) {
        this.cardNoMask = cardNoMask;
    }

    public boolean isDesignateCard() {
        return designateCard;
    }

    public void setDesignateCard(boolean designateCard) {
        this.designateCard = designateCard;
    }

    public String getCardKey() {
        return cardKey;
    }

    public void setCardKey(String cardKey) {
        this.cardKey = cardKey;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }
}
