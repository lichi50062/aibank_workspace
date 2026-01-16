package com.tfb.aibank.chl.creditcard.ag012.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCAG012020Rs.java
 * 
 * <p>Description:信用卡交易設定 020 設定頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/11, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG012020Rs implements RsData {

    /** 卡片資訊 */
    private NCCAG012CardInfo cardInfo;

    /** 國內標題 */
    private String remarkTitleInCountry;
    /** 國內內容 */
    private String remarkContentInCountry;
    /** 國外標題 */
    private String remarkTitleOutCountry;
    /** 國外內容 */
    private String remarkContentOutCountry;

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
     * @return the remarkTitleInCountry
     */
    public String getRemarkTitleInCountry() {
        return remarkTitleInCountry;
    }

    /**
     * @param remarkTitleInCountry
     *            the remarkTitleInCountry to set
     */
    public void setRemarkTitleInCountry(String remarkTitleInCountry) {
        this.remarkTitleInCountry = remarkTitleInCountry;
    }

    /**
     * @return the remarkContentInCountry
     */
    public String getRemarkContentInCountry() {
        return remarkContentInCountry;
    }

    /**
     * @param remarkContentInCountry
     *            the remarkContentInCountry to set
     */
    public void setRemarkContentInCountry(String remarkContentInCountry) {
        this.remarkContentInCountry = remarkContentInCountry;
    }

    /**
     * @return the remarkTitleOutCountry
     */
    public String getRemarkTitleOutCountry() {
        return remarkTitleOutCountry;
    }

    /**
     * @param remarkTitleOutCountry
     *            the remarkTitleOutCountry to set
     */
    public void setRemarkTitleOutCountry(String remarkTitleOutCountry) {
        this.remarkTitleOutCountry = remarkTitleOutCountry;
    }

    /**
     * @return the remarkContentOutCountry
     */
    public String getRemarkContentOutCountry() {
        return remarkContentOutCountry;
    }

    /**
     * @param remarkContentOutCountry
     *            the remarkContentOutCountry to set
     */
    public void setRemarkContentOutCountry(String remarkContentOutCountry) {
        this.remarkContentOutCountry = remarkContentOutCountry;
    }

}
