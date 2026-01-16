/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.repository.aib.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 推播訂閱 Entity
 * 
 * @author $author$
 */
@Entity
@Table(name = "PUSH_SUBSCRIPTION")
public class PushSubscriptionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 推播訂閱鍵值
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PUSH_SUBSCRIPTION_KEY")
    private Integer pushSubscriptionKey;

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
    private int deviceInfoKey;

    /**
     * 推播代碼
     */
    @Basic
    @Column(name = "PUSH_CODE")
    private String pushCode;

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
     * @return int 行動裝置設定檔鍵值
     */
    public int getDeviceInfoKey() {
        return this.deviceInfoKey;
    }

    /**
     * 設定行動裝置設定檔鍵值
     * 
     * @param deviceInfoKey
     *            要設定的行動裝置設定檔鍵值
     */
    public void setDeviceInfoKey(int deviceInfoKey) {
        this.deviceInfoKey = deviceInfoKey;
    }

    /**
     * 取得推播代碼
     * 
     * @return String 推播代碼
     */
    public String getPushCode() {
        return this.pushCode;
    }

    /**
     * 設定推播代碼
     * 
     * @param pushCode
     *            要設定的推播代碼
     */
    public void setPushCode(String pushCode) {
        this.pushCode = pushCode;
    }

    /**
     * 取得推播訂閱鍵值
     * 
     * @return int 推播訂閱鍵值
     */
    public Integer getPushSubscriptionKey() {
        return this.pushSubscriptionKey;
    }

    /**
     * 設定推播訂閱鍵值
     * 
     * @param pushSubscriptionKey
     *            要設定的推播訂閱鍵值
     */
    public void setPushSubscriptionKey(Integer pushSubscriptionKey) {
        this.pushSubscriptionKey = pushSubscriptionKey;
    }
}
