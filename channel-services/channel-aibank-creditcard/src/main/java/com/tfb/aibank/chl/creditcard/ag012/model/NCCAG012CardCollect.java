package com.tfb.aibank.chl.creditcard.ag012.model;

import java.util.List;

// @formatter:off
/**
 * @(#)NCCAG012CardCollect.java
 * 
 * <p>Description:卡片集合</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/11, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCAG012CardCollect {

    /** 卡面 */
    private String cardImg;

    /** 卡片類型名稱 */
    private String cardTypeName;

    /** 正卡片資訊 */
    private NCCAG012CardInfo primaryCardInfo;

    /** 附卡片資訊 */
    private List<NCCAG012CardInfo> suppleCardInfos;

    /**
     * @return the cardImg
     */
    public String getCardImg() {
        return cardImg;
    }

    /**
     * @param cardImg
     *            the cardImg to set
     */
    public void setCardImg(String cardImg) {
        this.cardImg = cardImg;
    }

    /**
     * @return the cardTypeName
     */
    public String getCardTypeName() {
        return cardTypeName;
    }

    /**
     * @param cardTypeName
     *            the cardTypeName to set
     */
    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    /**
     * @return the primaryCardInfo
     */
    public NCCAG012CardInfo getPrimaryCardInfo() {
        return primaryCardInfo;
    }

    /**
     * @param primaryCardInfo
     *            the primaryCardInfo to set
     */
    public void setPrimaryCardInfo(NCCAG012CardInfo primaryCardInfo) {
        this.primaryCardInfo = primaryCardInfo;
    }

    /**
     * @return the suppleCardInfos
     */
    public List<NCCAG012CardInfo> getSuppleCardInfos() {
        return suppleCardInfos;
    }

    /**
     * @param suppleCardInfos
     *            the suppleCardInfos to set
     */
    public void setSuppleCardInfos(List<NCCAG012CardInfo> suppleCardInfos) {
        this.suppleCardInfos = suppleCardInfos;
    }

}
