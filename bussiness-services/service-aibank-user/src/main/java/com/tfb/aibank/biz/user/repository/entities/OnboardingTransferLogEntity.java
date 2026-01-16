/*
 * =========================================================================== IBM Confidential AIS Source Materials
 *
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.repository.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

/**
 * 無痛移轉資料檔 Entity
 * 
 * @author $author$
 */
@Entity
@Table(name = "ONBOARDING_TRANSFER_LOG")
@IdClass(com.tfb.aibank.biz.user.repository.entities.pk.OnboardingTransferLogEntityPk.class)
public class OnboardingTransferLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公司鍵值
     */
    @Id
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /**
     * 建立時間
     */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 行動裝置設定檔鍵值
     */
    @Basic
    @Column(name = "DEVICE_INFO_KEY")
    private Integer deviceInfoKey;

    /**
     * 行動裝置UUID
     */
    @Id
    @Column(name = "DEVICE_UUID")
    private String deviceUuid;

    /**
     * 無痛移轉用戶旅程
     */
    @Basic
    @Column(name = "ONBOARDING_TYPE")
    private String onboardingType;

    /**
     * 原行動銀行設定項目
     */
    @Basic
    @Column(name = "ORIGIN_ITEM")
    private String originItem;

    /**
     * 原行動銀行推播項目
     */
    @Basic
    @Column(name = "ORIGIN_PUSH_ITEM")
    private String originPushItem;

    /**
     * 無痛移轉失敗項目
     */
    @Basic
    @Column(name = "TRANS_ITEM_FAIL")
    private String transItemFail;

    /**
     * 無痛移轉成功項目
     */
    @Basic
    @Column(name = "TRANS_ITEM_SUCCESS")
    private String transItemSuccess;

    /**
     * 無痛移轉失敗推播項目
     */
    @Basic
    @Column(name = "TRANS_PUSH_ITEM_FAIL")
    private String transPushItemFail;

    /**
     * 無痛移轉成功推播項目
     */
    @Basic
    @Column(name = "TRANS_PUSH_ITEM_SUCCESS")
    private String transPushItemSuccess;

    /**
     * 使用者鍵值
     */
    @Id
    @Column(name = "USER_KEY")
    private Integer userKey;

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
     * @param companyKey
     *            要設定的公司鍵值
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
     * @param createTime
     *            要設定的建立時間
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得行動裝置設定檔鍵值
     * 
     * @return Integer 行動裝置設定檔鍵值
     */
    public Integer getDeviceInfoKey() {
        return this.deviceInfoKey;
    }

    /**
     * 設定行動裝置設定檔鍵值
     * 
     * @param deviceInfoKey
     *            要設定的行動裝置設定檔鍵值
     */
    public void setDeviceInfoKey(Integer deviceInfoKey) {
        this.deviceInfoKey = deviceInfoKey;
    }

    /**
     * 取得行動裝置UUID
     * 
     * @return String 行動裝置UUID
     */
    public String getDeviceUuid() {
        return this.deviceUuid;
    }

    /**
     * 設定行動裝置UUID
     * 
     * @param deviceUuid
     *            要設定的行動裝置UUID
     */
    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    /**
     * 取得無痛移轉用戶旅程
     * 
     * @return String 無痛移轉用戶旅程
     */
    public String getOnboardingType() {
        return this.onboardingType;
    }

    /**
     * 設定無痛移轉用戶旅程
     * 
     * @param onboardingType
     *            要設定的無痛移轉用戶旅程
     */
    public void setOnboardingType(String onboardingType) {
        this.onboardingType = onboardingType;
    }

    /**
     * 取得原行動銀行設定項目
     * 
     * @return String 原行動銀行設定項目
     */
    public String getOriginItem() {
        return this.originItem;
    }

    /**
     * 設定原行動銀行設定項目
     * 
     * @param originItem
     *            要設定的原行動銀行設定項目
     */
    public void setOriginItem(String originItem) {
        this.originItem = originItem;
    }

    /**
     * 取得原行動銀行推播項目
     * 
     * @return String 原行動銀行推播項目
     */
    public String getOriginPushItem() {
        return this.originPushItem;
    }

    /**
     * 設定原行動銀行推播項目
     * 
     * @param originPushItem
     *            要設定的原行動銀行推播項目
     */
    public void setOriginPushItem(String originPushItem) {
        this.originPushItem = originPushItem;
    }

    /**
     * 取得無痛移轉失敗項目
     * 
     * @return String 無痛移轉失敗項目
     */
    public String getTransItemFail() {
        return this.transItemFail;
    }

    /**
     * 設定無痛移轉失敗項目
     * 
     * @param transItemFail
     *            要設定的無痛移轉失敗項目
     */
    public void setTransItemFail(String transItemFail) {
        this.transItemFail = transItemFail;
    }

    /**
     * 取得無痛移轉成功項目
     * 
     * @return String 無痛移轉成功項目
     */
    public String getTransItemSuccess() {
        return this.transItemSuccess;
    }

    /**
     * 設定無痛移轉成功項目
     * 
     * @param transItemSuccess
     *            要設定的無痛移轉成功項目
     */
    public void setTransItemSuccess(String transItemSuccess) {
        this.transItemSuccess = transItemSuccess;
    }

    /**
     * 取得無痛移轉失敗推播項目
     * 
     * @return String 無痛移轉失敗推播項目
     */
    public String getTransPushItemFail() {
        return this.transPushItemFail;
    }

    /**
     * 設定無痛移轉失敗推播項目
     * 
     * @param transPushItemFail
     *            要設定的無痛移轉失敗推播項目
     */
    public void setTransPushItemFail(String transPushItemFail) {
        this.transPushItemFail = transPushItemFail;
    }

    /**
     * 取得無痛移轉成功推播項目
     * 
     * @return String 無痛移轉成功推播項目
     */
    public String getTransPushItemSuccess() {
        return this.transPushItemSuccess;
    }

    /**
     * 設定無痛移轉成功推播項目
     * 
     * @param transPushItemSuccess
     *            要設定的無痛移轉成功推播項目
     */
    public void setTransPushItemSuccess(String transPushItemSuccess) {
        this.transPushItemSuccess = transPushItemSuccess;
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
     * @param userKey
     *            要設定的使用者鍵值
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }
}
