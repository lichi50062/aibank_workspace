package com.tfb.aibank.chl.creditcard.qu001.model;

//@formatter:off
/**
* @(#)NCCQQU001CardData.java
* 
* <p>Description:信用卡總覽交易暫存資料</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCQQU001CardData {

    /** 識別資訊(唯一值) */
    private String cardKey;

    /** 卡號 */
    private String cardNo;

    /** 卡別 */
    private String cardType;

    /** 信用卡名稱 */
    private String cardName;
    /** 卡種 */
    private String cardCategory;
    /** 卡面 */
    private String cardFace;

    /** 卡片效期 */
    private String cardExpired;

    /** 暱稱 */
    private String cardNickName;

    /** 正卡項下副卡 */
    private boolean underPrimaryCard;

    /** 附卡 */
    private boolean additional;

    /** 虛擬卡 */
    private boolean virtualCard;

    /** 保費權益 */
    private boolean insuFlag;

    /** 已綁定行動支付 */
    private boolean hceCard;

    /** 信用卡已啟用 */
    private boolean cardActiveFlag;

    /** 信用卡停用 */
    private boolean stop;

    /** 正卡卡號 */
    private String mCardNo;

    /** 正卡有消費明細 */
    private boolean hasCew205r;

    /** 正卡項下附卡 */
    private boolean parentCard;

    /** 是否可申請國際行動支付卡號判斷方式 */
    private boolean tknFlag;

    /** costco卡 */
    private boolean isCostco;

    /** 副卡已發送CEW205R資料 */
    private boolean sendCew205r;

    /** 是否公採卡 */
    private boolean isVpsCard;

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
     * @return the cardExpired
     */
    public String getCardExpired() {
        return cardExpired;
    }

    /**
     * @param cardExpired
     *            the cardExpired to set
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
     * @param cardNickName
     *            the cardNickName to set
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
     * @param additional
     *            the additional to set
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
     * @param virtualCard
     *            the virtualCard to set
     */
    public void setVirtualCard(boolean virtualCard) {
        this.virtualCard = virtualCard;
    }

    /**
     * @return the insuFlag
     */
    public boolean isInsuFlag() {
        return insuFlag;
    }

    /**
     * @param insuFlag
     *            the insuFlag to set
     */
    public void setInsuFlag(boolean insuFlag) {
        this.insuFlag = insuFlag;
    }

    /**
     * @return the hceCard
     */
    public boolean isHceCard() {
        return hceCard;
    }

    /**
     * @param hceCard
     *            the hceCard to set
     */
    public void setHceCard(boolean hceCard) {
        this.hceCard = hceCard;
    }

    /**
     * @return the cardActiveFlag
     */
    public boolean isCardActiveFlag() {
        return cardActiveFlag;
    }

    /**
     * @param cardActiveFlag
     *            the cardActiveFlag to set
     */
    public void setCardActiveFlag(boolean cardActiveFlag) {
        this.cardActiveFlag = cardActiveFlag;
    }

    /**
     * @return the cardFace
     */
    public String getCardFace() {
        return cardFace;
    }

    /**
     * @param cardFace
     *            the cardFace to set
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
     * @param mCardNo
     *            the mCardNo to set
     */
    public void setMCardNo(String mCardNo) {
        this.mCardNo = mCardNo;
    }

    /**
     * @return the hasCew205r
     */
    public boolean isHasCew205r() {
        return hasCew205r;
    }

    /**
     * @param hasCew205r
     *            the hasCew205r to set
     */
    public void setHasCew205r(boolean hasCew205r) {
        this.hasCew205r = hasCew205r;
    }

    /**
     * @return the parentCard
     */
    public boolean isParentCard() {
        return parentCard;
    }

    /**
     * @param parentCard
     *            the parentCard to set
     */
    public void setParentCard(boolean parentCard) {
        this.parentCard = parentCard;
    }

    /**
     * @return the stop
     */
    public boolean isStop() {
        return stop;
    }

    /**
     * @param stop
     *            the stop to set
     */
    public void setStop(boolean stop) {
        this.stop = stop;
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
     * @return the tknFlag
     */
    public boolean isTknFlag() {
        return tknFlag;
    }

    /**
     * @param tknFlag
     *            the tknFlag to set
     */
    public void setTknFlag(boolean tknFlag) {
        this.tknFlag = tknFlag;
    }

    /**
     * @return the isCostco
     */
    public boolean isCostco() {
        return isCostco;
    }

    /**
     * @param isCostco
     *            the isCostco to set
     */
    public void setCostco(boolean isCostco) {
        this.isCostco = isCostco;
    }

    /**
     * @return the sendCew205r
     */
    public boolean isSendCew205r() {
        return sendCew205r;
    }

    /**
     * @param sendCew205r
     *            the sendCew205r to set
     */
    public void setSendCew205r(boolean sendCew205r) {
        this.sendCew205r = sendCew205r;
    }

    /**
     * @return the underPrimaryCard
     */
    public boolean isUnderPrimaryCard() {
        return underPrimaryCard;
    }

    /**
     * @param underPrimaryCard
     *            the underPrimaryCard to set
     */
    public void setUnderPrimaryCard(boolean underPrimaryCard) {
        this.underPrimaryCard = underPrimaryCard;
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
     * @return the isVpsCard
     */
    public boolean isVpsCard() {
        return isVpsCard;
    }

    /**
     * @param isVpsCard
     *            the isVpsCard to set
     */
    public void setVpsCard(boolean isVpsCard) {
        this.isVpsCard = isVpsCard;
    }

}
