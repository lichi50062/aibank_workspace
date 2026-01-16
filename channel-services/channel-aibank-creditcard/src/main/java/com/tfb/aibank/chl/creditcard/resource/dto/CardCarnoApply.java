/*
 * ===========================================================================
 * IBM Confidential
 * AIS Source Materials
 *
 *
 * (C) Copyright IBM Corp. 2013.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.Date;


// @formatter:off
/**
 * @(#)CardCarnoApply.java
 *
 * <p>Description:道路救援車號登錄 Model</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/10/02
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CardCarnoApply {

    /**
     * 主檔鍵值
     */
    private Integer carnoKey;

    /**
     * 公司鍵值
     */
    private Integer companyKey;

    /**
     * 使用者代碼
     */
    private String userId;

    /**
     * 使用者鍵值
     */
    private Integer userKey;

    /**
     * 交易存取記錄鍵值
     */
    private Integer accessLogKey;

    /**
     * 交易錯誤訊息
     */
    private String txErrorDesc;

    /**
     * 錯誤系統代碼
     */
    private String txErrorSystemId;

    /**
     * 車號
     */
    private String carNo;

    /**
     * 信用卡卡號
     */
    private String cardNo;

    /**
     * 建立時間
     */
    private Date createTime;

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
     * 交易種類 0：新增 1：變更 2：刪除
     */
    private String txKind;

    /**
     * 交易狀態 0：交易成功 1：交易失敗 4：交易未明
     */
    private String txStatus;

    /**
     * 更新時間
     */
    private Date updateTime;

    /**
     * 交易來源
     */
    private String txSource;

    /**
     * 取得車號
     *
     * @return String 車號
     */
    public String getCarNo() {
        return this.carNo;
    }

    /**
     * 設定車號
     *
     * @param carNo 要設定的車號
     */
    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    /**
     * 取得信用卡卡號
     *
     * @return String 信用卡卡號
     */
    public String getCardNo() {
        return this.cardNo;
    }

    /**
     * 設定信用卡卡號
     *
     * @param cardNo 要設定的信用卡卡號
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * 取得主檔鍵值
     *
     * @return Integer 主檔鍵值
     */
    public Integer getCarnoKey() {
        return this.carnoKey;
    }

    /**
     * 設定主檔鍵值
     *
     * @param carnoKey 要設定的主檔鍵值
     */
    public void setCarnoKey(Integer carnoKey) {
        this.carnoKey = carnoKey;
    }

    /**
     * 取得公司鍵值
     *
     * @return Integer 公司鍵值
     */
    public Integer getCompanyKey() {
        return this.companyKey;
    }

    /**
     * 設定公司鍵值
     *
     * @param companyKey 要設定的公司鍵值
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * 取得建立時間
     *
     * @return Date 建立時間
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 設定建立時間
     *
     * @param createTime 要設定的建立時間
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得上送主機交易時間
     *
     * @return Date 上送主機交易時間
     */
    public Date getHostTxTime() {
        return this.hostTxTime;
    }

    /**
     * 設定上送主機交易時間
     *
     * @param hostTxTime 要設定的上送主機交易時間
     */
    public void setHostTxTime(Date hostTxTime) {
        this.hostTxTime = hostTxTime;
    }

    /**
     * 取得用戶代碼
     *
     * @return String 用戶代碼
     */
    public String getNameCode() {
        return this.nameCode;
    }

    /**
     * 設定用戶代碼
     *
     * @param nameCode 要設定的用戶代碼
     */
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    /**
     * 取得交易日期
     *
     * @return Date 交易日期
     */
    public Date getTxDate() {
        return this.txDate;
    }

    /**
     * 設定交易日期
     *
     * @param txDate 要設定的交易日期
     */
    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    /**
     * 取得交易錯誤代碼
     *
     * @return String 交易錯誤代碼
     */
    public String getTxErrorCode() {
        return this.txErrorCode;
    }

    /**
     * 設定交易錯誤代碼
     *
     * @param txErrorCode 要設定的交易錯誤代碼
     */
    public void setTxErrorCode(String txErrorCode) {
        this.txErrorCode = txErrorCode;
    }

    /**
     * 取得交易種類 0：新增 1：變更 2：刪除
     *
     * @return String 交易種類 0：新增 1：變更 2：刪除
     */
    public String getTxKind() {
        return this.txKind;
    }

    /**
     * 設定交易種類 0：新增 1：變更 2：刪除
     *
     * @param txKind 要設定的交易種類 0：新增 1：變更 2：刪除
     */
    public void setTxKind(String txKind) {
        this.txKind = txKind;
    }

    /**
     * 取得交易狀態 0：交易成功 1：交易失敗 4：交易未明
     *
     * @return String 交易狀態 0：交易成功 1：交易失敗 4：交易未明
     */
    public String getTxStatus() {
        return this.txStatus;
    }

    /**
     * 設定交易狀態 0：交易成功 1：交易失敗 4：交易未明
     *
     * @param txStatus 要設定的交易狀態 0：交易成功 1：交易失敗 4：交易未明
     */
    public void setTxStatus(String txStatus) {
        this.txStatus = txStatus;
    }

    /**
     * 取得更新時間
     *
     * @return Date 更新時間
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 設定更新時間
     *
     * @param updateTime 要設定的更新時間
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 取得使用者代碼
     *
     * @return String 使用者代碼
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 設定使用者代碼
     *
     * @param userId 要設定的使用者代碼
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 取得使用者鍵值
     *
     * @return Integer 使用者鍵值
     */
    public Integer getUserKey() {
        return this.userKey;
    }

    /**
     * 設定使用者鍵值
     *
     * @param userKey 要設定的使用者鍵值
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public Integer getAccessLogKey() {
        return accessLogKey;
    }

    public void setAccessLogKey(Integer accessLogKey) {
        this.accessLogKey = accessLogKey;
    }

    public String getTxErrorDesc() {
        return txErrorDesc;
    }

    public void setTxErrorDesc(String txErrorDesc) {
        this.txErrorDesc = txErrorDesc;
    }

    public String getTxErrorSystemId() {
        return txErrorSystemId;
    }

    public void setTxErrorSystemId(String txErrorSystemId) {
        this.txErrorSystemId = txErrorSystemId;
    }

    /**
     * 取得 txSource
     *
     * @return txSource
     */
    public String getTxSource() {
        return txSource;
    }

    /**
     * 設定 txSource
     *
     * @param txSource
     */
    public void setTxSource(String txSource) {
        this.txSource = txSource;
    }
}
