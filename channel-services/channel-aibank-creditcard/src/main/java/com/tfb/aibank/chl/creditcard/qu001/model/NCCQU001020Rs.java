package com.tfb.aibank.chl.creditcard.qu001.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCQU001020Rs.java
* 
* <p>Description:信用卡總覽 卡片管理頁 RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU001020Rs implements RsData {
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
    /** 是否可申請國際行動支付卡號判斷方式 */
    private boolean tknFlag;

    /** 發送電文CEW321R取得年度累積消費資料失敗 */
    private boolean cew321rError;
    /** 累計起算年月 X(5) */
    private Date strday;
    /** 累計迄期年月 X(5) */
    private Date endday;
    /** 免年費消費金額標準 X(7) */
    private BigDecimal outamt1;
    /** 免年費消費次數標準 X(3) */
    private String outcnt1;
    /** 累積年消費金額 X(9) */
    private String outamt2;
    /** 累積年消費次數 X(7) */
    private String outcnt2;
    /** 發送電文CEW327R取得保費權益資訊失敗 */
    private boolean cew327rError;
    /** 保費權益種類 */
    private String insuType;
    /** 5萬元(含)以上 保費權益種類 */
    private String insuTypeShow;
    /** 單筆未達5萬元 保費權益種類 */
    private String insuTypeUnderFiveShow;
    /** 發送電文CEW329R取得已綁定支付資訊失敗 */
    private boolean cew329rError;
    /** 信用卡綁定行動支付類型 */
    private List<NCCQQU001PayType> payTypes;
    /** 附卡資訊 */
    private List<NCCQQU001CardData> additionalCards;

    /** #5146 是否DB版本號>=APP版本號 */
    private Boolean isVersionCanApplePay;

    /** #5146 是否DB版本號>=APP版本號 */
    private Boolean isVersionCanLinePay;
    /** 是否已是好市多正卡會員 */
    private Boolean isCostcoMember;
    /** 是否已設定好市多代繳 */
    private Boolean isApplyCostco;
    /** 不需判斷CE6220R_RS.TKN_FLAG = Y 才顯示applePay */
    private Boolean isNeedCheckTknFlagForApplePay;
    /** 卡片鎖定狀態 */
    private NCCQU001CardLockStatus cardLockStatus;

    /** 附卡 */
    private boolean additional;

    /** 是否公採卡 */
    private boolean isVpsCard;

    /**
     * @return the isCostcoMember
     */
    public Boolean getIsCostcoMember() {
        return isCostcoMember;
    }

    /**
     * @param isCostcoMember
     *            the isCostcoMember to set
     */
    public void setIsCostcoMember(Boolean isCostcoMember) {
        this.isCostcoMember = isCostcoMember;
    }

    /**
     * @return the isApplyCostco
     */
    public Boolean getIsApplyCostco() {
        return isApplyCostco;
    }

    /**
     * @param isApplyCostco
     *            the isApplyCostco to set
     */
    public void setIsApplyCostco(Boolean isApplyCostco) {
        this.isApplyCostco = isApplyCostco;
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
     * @return the insuType
     */
    public String getInsuType() {
        return insuType;
    }

    /**
     * @param insuType
     *            the insuType to set
     */
    public void setInsuType(String insuType) {
        this.insuType = insuType;
    }

    /**
     * @return the insuTypeShow
     */
    public String getInsuTypeShow() {
        return insuTypeShow;
    }

    /**
     * @param insuTypeShow
     *            the insuTypeShow to set
     */
    public void setInsuTypeShow(String insuTypeShow) {
        this.insuTypeShow = insuTypeShow;
    }

    /**
     * @return the insuTypeUnderFiveShow
     */
    public String getInsuTypeUnderFiveShow() {
        return insuTypeUnderFiveShow;
    }

    /**
     * @param insuTypeUnderFiveShow
     *            the insuTypeUnderFiveShow to set
     */
    public void setInsuTypeUnderFiveShow(String insuTypeUnderFiveShow) {
        this.insuTypeUnderFiveShow = insuTypeUnderFiveShow;
    }

    /**
     * @return the cew321rError
     */
    public boolean isCew321rError() {
        return cew321rError;
    }

    /**
     * @param cew321rError
     *            the cew321rError to set
     */
    public void setCew321rError(boolean cew321rError) {
        this.cew321rError = cew321rError;
    }

    /**
     * @return the cew327rError
     */
    public boolean isCew327rError() {
        return cew327rError;
    }

    /**
     * @param cew327rError
     *            the cew327rError to set
     */
    public void setCew327rError(boolean cew327rError) {
        this.cew327rError = cew327rError;
    }

    /**
     * @return the strday
     */
    public Date getStrday() {
        return strday;
    }

    /**
     * @param strday
     *            the strday to set
     */
    public void setStrday(Date strday) {
        this.strday = strday;
    }

    /**
     * @return the endday
     */
    public Date getEndday() {
        return endday;
    }

    /**
     * @param endday
     *            the endday to set
     */
    public void setEndday(Date endday) {
        this.endday = endday;
    }

    /**
     * @return the outamt1
     */
    public BigDecimal getOutamt1() {
        return outamt1;
    }

    /**
     * @param outamt1
     *            the outamt1 to set
     */
    public void setOutamt1(BigDecimal outamt1) {
        this.outamt1 = outamt1;
    }

    /**
     * @return the outcnt1
     */
    public String getOutcnt1() {
        return outcnt1;
    }

    /**
     * @param outcnt1
     *            the outcnt1 to set
     */
    public void setOutcnt1(String outcnt1) {
        this.outcnt1 = outcnt1;
    }

    /**
     * @return the outamt2
     */
    public String getOutamt2() {
        return outamt2;
    }

    /**
     * @param outamt2
     *            the outamt2 to set
     */
    public void setOutamt2(String outamt2) {
        this.outamt2 = outamt2;
    }

    /**
     * @return the outcnt2
     */
    public String getOutcnt2() {
        return outcnt2;
    }

    /**
     * @param outcnt2
     *            the outcnt2 to set
     */
    public void setOutcnt2(String outcnt2) {
        this.outcnt2 = outcnt2;
    }

    /**
     * @return the cew329rError
     */
    public boolean isCew329rError() {
        return cew329rError;
    }

    /**
     * @param cew329rError
     *            the cew329rError to set
     */
    public void setCew329rError(boolean cew329rError) {
        this.cew329rError = cew329rError;
    }

    /**
     * @return the payTypes
     */
    public List<NCCQQU001PayType> getPayTypes() {
        return payTypes;
    }

    /**
     * @param payTypes
     *            the payTypes to set
     */
    public void setPayTypes(List<NCCQQU001PayType> payTypes) {
        this.payTypes = payTypes;
    }

    /**
     * @return the additionalCards
     */
    public List<NCCQQU001CardData> getAdditionalCards() {
        return additionalCards;
    }

    /**
     * @param additionalCards
     *            the additionalCards to set
     */
    public void setAdditionalCards(List<NCCQQU001CardData> additionalCards) {
        this.additionalCards = additionalCards;
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
     * @return the isVersionCanApplePay
     */
    public Boolean getIsVersionCanApplePay() {
        return isVersionCanApplePay;
    }

    /**
     * @param isVersionCanApplePay
     *            the isVersionCanApplePay to set
     */
    public void setIsVersionCanApplePay(Boolean isVersionCanApplePay) {
        this.isVersionCanApplePay = isVersionCanApplePay;
    }

    /**
     * @return the isVersionCanLinePay
     */
    public Boolean getIsVersionCanLinePay() {
        return isVersionCanLinePay;
    }

    /**
     * @param isVersionCanLinePay
     *            the isVersionCanLinePay to set
     */
    public void setIsVersionCanLinePay(Boolean isVersionCanLinePay) {
        this.isVersionCanLinePay = isVersionCanLinePay;
    }

    /**
     * @return the isNeedCheckTknFlagForApplePay
     */
    public Boolean getIsNeedCheckTknFlagForApplePay() {
        return isNeedCheckTknFlagForApplePay;
    }

    /**
     * @param isNeedCheckTknFlagForApplePay
     *            the isNeedCheckTknFlagForApplePay to set
     */
    public void setIsNeedCheckTknFlagForApplePay(Boolean isNeedCheckTknFlagForApplePay) {
        this.isNeedCheckTknFlagForApplePay = isNeedCheckTknFlagForApplePay;

    }
    /**
     * @return the cardLockStatus
     */
    public NCCQU001CardLockStatus getCardLockStatus() {
        return cardLockStatus;
    }

    /**
     * @param cardLockStatus
     *            the cardLockStatus to set
     */
    public void setCardLockStatus(NCCQU001CardLockStatus cardLockStatus) {
        this.cardLockStatus = cardLockStatus;
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
