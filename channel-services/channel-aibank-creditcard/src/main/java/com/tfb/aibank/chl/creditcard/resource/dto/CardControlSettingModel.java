package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.Date;

//@formatter:off
/**
* @(#)CardControlSettingModel.java
*
* <p>Description:信用卡交易設定交易檔 </p>
*
* <p>Modify History:</p>
* v1.0, 2025/03/11
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CardControlSettingModel {

    /**
     * 信用卡卡號
     */
    private String cardNo;

    /**
     * 公司鍵值
     */
    private Integer companyKey;

    /**
     * 資料鍵值
     */
    private Integer controlKey;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 國內行動支付
     */
    private String domMob;

    /**
     * 國內行動支付交易金額
     */
    private Integer domMobAmt;

    /**
     * 國內非實體卡交易
     */
    private String domNphy;

    /**
     * 國內非實體卡交易金額
     */
    private Integer domNphyAmt;

    /**
     * 國內實體卡交易
     */
    private String domPhy;

    /**
     * 國內實體卡交易金額
     */
    private Integer domPhyAmt;

    /**
     * 國外行動支付
     */
    private String forMob;

    /**
     * 國外行動支付交易金額
     */
    private Integer forMobAmt;

    /**
     * 國外非實體卡交易
     */
    private String forNphy;

    /**
     * 國外非實體卡交易金額
     */
    private Integer forNphyAmt;

    /**
     * 國外實體卡交易
     */
    private String forPhy;

    /**
     * 國外實體卡交易金額
     */
    private Integer forPhyAmt;

    /**
     * 上送主機交易時間
     */
    private Date hostTxTime;

    /**
     * 用戶代碼
     */
    private String nameCode;

    /**
     * 交易日期
     */
    private Date txDate;

    /**
     * 交易錯誤代碼
     */
    private String txErrorCode;

    /**
     * 交易錯誤訊息
     */
    private String txErrorDesc;

    /**
     * 錯誤系統代碼
     */
    private String txErrorSystemId;

    /**
     * 交易來源
     */
    private String txSource;

    /**
     * 交易狀態
     */
    private String txStatus;

    /**
     * 更新時間
     */
    private Date updateTime;

    /**
     * 使用者代碼
     */
    private String userId;

    /**
     * 使用者鍵值
     */
    private Integer userKey;

    /**
     * traceId
     */
    private String traceId;

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
     * @return the companyKey
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * @param companyKey
     *            the companyKey to set
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * @return the controlKey
     */
    public Integer getControlKey() {
        return controlKey;
    }

    /**
     * @param controlKey
     *            the controlKey to set
     */
    public void setControlKey(Integer controlKey) {
        this.controlKey = controlKey;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the domMob
     */
    public String getDomMob() {
        return domMob;
    }

    /**
     * @param domMob
     *            the domMob to set
     */
    public void setDomMob(String domMob) {
        this.domMob = domMob;
    }

    /**
     * @return the domMobAmt
     */
    public Integer getDomMobAmt() {
        return domMobAmt;
    }

    /**
     * @param domMobAmt
     *            the domMobAmt to set
     */
    public void setDomMobAmt(Integer domMobAmt) {
        this.domMobAmt = domMobAmt;
    }

    /**
     * @return the domNphy
     */
    public String getDomNphy() {
        return domNphy;
    }

    /**
     * @param domNphy
     *            the domNphy to set
     */
    public void setDomNphy(String domNphy) {
        this.domNphy = domNphy;
    }

    /**
     * @return the domNphyAmt
     */
    public Integer getDomNphyAmt() {
        return domNphyAmt;
    }

    /**
     * @param domNphyAmt
     *            the domNphyAmt to set
     */
    public void setDomNphyAmt(Integer domNphyAmt) {
        this.domNphyAmt = domNphyAmt;
    }

    /**
     * @return the domPhy
     */
    public String getDomPhy() {
        return domPhy;
    }

    /**
     * @param domPhy
     *            the domPhy to set
     */
    public void setDomPhy(String domPhy) {
        this.domPhy = domPhy;
    }

    /**
     * @return the domPhyAmt
     */
    public Integer getDomPhyAmt() {
        return domPhyAmt;
    }

    /**
     * @param domPhyAmt
     *            the domPhyAmt to set
     */
    public void setDomPhyAmt(Integer domPhyAmt) {
        this.domPhyAmt = domPhyAmt;
    }

    /**
     * @return the forMob
     */
    public String getForMob() {
        return forMob;
    }

    /**
     * @param forMob
     *            the forMob to set
     */
    public void setForMob(String forMob) {
        this.forMob = forMob;
    }

    /**
     * @return the forMobAmt
     */
    public Integer getForMobAmt() {
        return forMobAmt;
    }

    /**
     * @param forMobAmt
     *            the forMobAmt to set
     */
    public void setForMobAmt(Integer forMobAmt) {
        this.forMobAmt = forMobAmt;
    }

    /**
     * @return the forNphy
     */
    public String getForNphy() {
        return forNphy;
    }

    /**
     * @param forNphy
     *            the forNphy to set
     */
    public void setForNphy(String forNphy) {
        this.forNphy = forNphy;
    }

    /**
     * @return the forNphyAmt
     */
    public Integer getForNphyAmt() {
        return forNphyAmt;
    }

    /**
     * @param forNphyAmt
     *            the forNphyAmt to set
     */
    public void setForNphyAmt(Integer forNphyAmt) {
        this.forNphyAmt = forNphyAmt;
    }

    /**
     * @return the forPhy
     */
    public String getForPhy() {
        return forPhy;
    }

    /**
     * @param forPhy
     *            the forPhy to set
     */
    public void setForPhy(String forPhy) {
        this.forPhy = forPhy;
    }

    /**
     * @return the forPhyAmt
     */
    public Integer getForPhyAmt() {
        return forPhyAmt;
    }

    /**
     * @param forPhyAmt
     *            the forPhyAmt to set
     */
    public void setForPhyAmt(Integer forPhyAmt) {
        this.forPhyAmt = forPhyAmt;
    }

    /**
     * @return the hostTxTime
     */
    public Date getHostTxTime() {
        return hostTxTime;
    }

    /**
     * @param hostTxTime
     *            the hostTxTime to set
     */
    public void setHostTxTime(Date hostTxTime) {
        this.hostTxTime = hostTxTime;
    }

    /**
     * @return the nameCode
     */
    public String getNameCode() {
        return nameCode;
    }

    /**
     * @param nameCode
     *            the nameCode to set
     */
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    /**
     * @return the txDate
     */
    public Date getTxDate() {
        return txDate;
    }

    /**
     * @param txDate
     *            the txDate to set
     */
    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    /**
     * @return the txErrorCode
     */
    public String getTxErrorCode() {
        return txErrorCode;
    }

    /**
     * @param txErrorCode
     *            the txErrorCode to set
     */
    public void setTxErrorCode(String txErrorCode) {
        this.txErrorCode = txErrorCode;
    }

    /**
     * @return the txErrorDesc
     */
    public String getTxErrorDesc() {
        return txErrorDesc;
    }

    /**
     * @param txErrorDesc
     *            the txErrorDesc to set
     */
    public void setTxErrorDesc(String txErrorDesc) {
        this.txErrorDesc = txErrorDesc;
    }

    /**
     * @return the txErrorSystemId
     */
    public String getTxErrorSystemId() {
        return txErrorSystemId;
    }

    /**
     * @param txErrorSystemId
     *            the txErrorSystemId to set
     */
    public void setTxErrorSystemId(String txErrorSystemId) {
        this.txErrorSystemId = txErrorSystemId;
    }

    /**
     * @return the txSource
     */
    public String getTxSource() {
        return txSource;
    }

    /**
     * @param txSource
     *            the txSource to set
     */
    public void setTxSource(String txSource) {
        this.txSource = txSource;
    }

    /**
     * @return the txStatus
     */
    public String getTxStatus() {
        return txStatus;
    }

    /**
     * @param txStatus
     *            the txStatus to set
     */
    public void setTxStatus(String txStatus) {
        this.txStatus = txStatus;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     *            the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the userKey
     */
    public Integer getUserKey() {
        return userKey;
    }

    /**
     * @param userKey
     *            the userKey to set
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    /**
     * @return the traceId
     */
    public String getTraceId() {
        return traceId;
    }

    /**
     * @param traceId
     *            the traceId to set
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

}
