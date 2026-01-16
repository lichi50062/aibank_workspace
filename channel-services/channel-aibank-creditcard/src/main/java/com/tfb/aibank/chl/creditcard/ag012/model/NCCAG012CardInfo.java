package com.tfb.aibank.chl.creditcard.ag012.model;

import java.math.BigDecimal;

// @formatter:off
/**
 * @(#)NCCAG012CardInfo.java
 * 
 * <p>Description:卡片資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/11, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCAG012CardInfo {
    /** 卡Key */
    private String cardKey;

    /** 暱稱 */
    private String nickName;

    // /** 卡號 */
    // private String cardNo;
    /** 卡號 */
    private String cardNoForDisplay;

    /** Ce5552r 發查失敗 */
    private Boolean isCe5552rError;

    /** Ce5552r 發查失敗2次 */
    private Boolean isCe5552rError2Time;

    /** 最近變更日期 YYYY/MM/DD */
    private String pichDy;

    /** 是否啟用 CE6220R_Rs.CARD_ACTIVE_FLAG = Y。 */
    private Boolean cardActiveFlag;

    /** 所有狀態 0: 全鎖定 1:部分鎖定 2:未鎖定 */
    private Integer allStatus;

    /** 國內實體卡交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定 */
    private Integer pilPStatus;

    /** 國內實體卡交易金額限制 */
    private BigDecimal pilPMoney;

    /** 國外實體卡交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定 */
    private Integer pifPStatus;

    /** 國外實體卡交易金額限制 */
    private BigDecimal pifPMoney;

    /** 國內非實體卡交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定 */
    private Integer pilCStatus;

    /** 國內非實體卡交易金額限制 */
    private BigDecimal pilCMoney;

    /** 國外非實體卡交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定 */
    private Integer pifCStatus;

    /** 國外非實體卡交易金額限制 */
    private BigDecimal pifCMoney;

    /** 國內行動支付交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定 */
    private Integer pilTStatus;
    /** 國內行動支付交易金額限制 */
    private BigDecimal pilTCMoney;

    /** 國外行動支付交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定 */
    private Integer pifTStatus;
    /** 國外行動支付交易金額限制 */
    private BigDecimal pifTMoney;

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
     * @return the nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName
     *            the nickName to set
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return the cardNoForDisplay
     */
    public String getCardNoForDisplay() {
        return cardNoForDisplay;
    }

    /**
     * @param cardNoForDisplay
     *            the cardNoForDisplay to set
     */
    public void setCardNoForDisplay(String cardNoForDisplay) {
        this.cardNoForDisplay = cardNoForDisplay;
    }

    /**
     * @return the isCe5552rError
     */
    public Boolean getIsCe5552rError() {
        return isCe5552rError;
    }

    /**
     * @param isCe5552rError
     *            the isCe5552rError to set
     */
    public void setIsCe5552rError(Boolean isCe5552rError) {
        this.isCe5552rError = isCe5552rError;
    }

    /**
     * @return the pichDy
     */
    public String getPichDy() {
        return pichDy;
    }

    /**
     * @param pichDy
     *            the pichDy to set
     */
    public void setPichDy(String pichDy) {
        this.pichDy = pichDy;
    }

    /**
     * @return the cardActiveFlag
     */
    public Boolean getCardActiveFlag() {
        return cardActiveFlag;
    }

    /**
     * @param cardActiveFlag
     *            the cardActiveFlag to set
     */
    public void setCardActiveFlag(Boolean cardActiveFlag) {
        this.cardActiveFlag = cardActiveFlag;
    }

    /**
     * @return the allStatus
     */
    public Integer getAllStatus() {
        return allStatus;
    }

    /**
     * @param allStatus
     *            the allStatus to set
     */
    public void setAllStatus(Integer allStatus) {
        this.allStatus = allStatus;
    }

    /**
     * @return the pilPStatus
     */
    public Integer getPilPStatus() {
        return pilPStatus;
    }

    /**
     * @param pilPStatus
     *            the pilPStatus to set
     */
    public void setPilPStatus(Integer pilPStatus) {
        this.pilPStatus = pilPStatus;
    }

    /**
     * @return the pilPMoney
     */
    public BigDecimal getPilPMoney() {
        return pilPMoney;
    }

    /**
     * @param pilPMoney
     *            the pilPMoney to set
     */
    public void setPilPMoney(BigDecimal pilPMoney) {
        this.pilPMoney = pilPMoney;
    }

    /**
     * @return the pifPStatus
     */
    public Integer getPifPStatus() {
        return pifPStatus;
    }

    /**
     * @param pifPStatus
     *            the pifPStatus to set
     */
    public void setPifPStatus(Integer pifPStatus) {
        this.pifPStatus = pifPStatus;
    }

    /**
     * @return the pifPMoney
     */
    public BigDecimal getPifPMoney() {
        return pifPMoney;
    }

    /**
     * @param pifPMoney
     *            the pifPMoney to set
     */
    public void setPifPMoney(BigDecimal pifPMoney) {
        this.pifPMoney = pifPMoney;
    }

    /**
     * @return the pilCStatus
     */
    public Integer getPilCStatus() {
        return pilCStatus;
    }

    /**
     * @param pilCStatus
     *            the pilCStatus to set
     */
    public void setPilCStatus(Integer pilCStatus) {
        this.pilCStatus = pilCStatus;
    }

    /**
     * @return the pilCMoney
     */
    public BigDecimal getPilCMoney() {
        return pilCMoney;
    }

    /**
     * @param pilCMoney
     *            the pilCMoney to set
     */
    public void setPilCMoney(BigDecimal pilCMoney) {
        this.pilCMoney = pilCMoney;
    }

    /**
     * @return the pifCStatus
     */
    public Integer getPifCStatus() {
        return pifCStatus;
    }

    /**
     * @param pifCStatus
     *            the pifCStatus to set
     */
    public void setPifCStatus(Integer pifCStatus) {
        this.pifCStatus = pifCStatus;
    }

    /**
     * @return the pifCMoney
     */
    public BigDecimal getPifCMoney() {
        return pifCMoney;
    }

    /**
     * @param pifCMoney
     *            the pifCMoney to set
     */
    public void setPifCMoney(BigDecimal pifCMoney) {
        this.pifCMoney = pifCMoney;
    }

    /**
     * @return the pilTStatus
     */
    public Integer getPilTStatus() {
        return pilTStatus;
    }

    /**
     * @param pilTStatus
     *            the pilTStatus to set
     */
    public void setPilTStatus(Integer pilTStatus) {
        this.pilTStatus = pilTStatus;
    }

    /**
     * @return the pilTCMoney
     */
    public BigDecimal getPilTCMoney() {
        return pilTCMoney;
    }

    /**
     * @param pilTCMoney
     *            the pilTCMoney to set
     */
    public void setPilTCMoney(BigDecimal pilTCMoney) {
        this.pilTCMoney = pilTCMoney;
    }

    /**
     * @return the pifTStatus
     */
    public Integer getPifTStatus() {
        return pifTStatus;
    }

    /**
     * @param pifTStatus
     *            the pifTStatus to set
     */
    public void setPifTStatus(Integer pifTStatus) {
        this.pifTStatus = pifTStatus;
    }

    /**
     * @return the pifTMoney
     */
    public BigDecimal getPifTMoney() {
        return pifTMoney;
    }

    /**
     * @param pifTMoney
     *            the pifTMoney to set
     */
    public void setPifTMoney(BigDecimal pifTMoney) {
        this.pifTMoney = pifTMoney;
    }

    /**
     * @return the isCe5552rError2Time
     */
    public Boolean getIsCe5552rError2Time() {
        return isCe5552rError2Time;
    }

    /**
     * @param isCe5552rError2Time
     *            the isCe5552rError2Time to set
     */
    public void setIsCe5552rError2Time(Boolean isCe5552rError2Time) {
        this.isCe5552rError2Time = isCe5552rError2Time;
    }

}
