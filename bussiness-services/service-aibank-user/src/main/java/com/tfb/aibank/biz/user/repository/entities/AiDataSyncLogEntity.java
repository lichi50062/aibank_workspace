
/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

//@formatter:off
/**
* @(#)AiDataSyncLogEntity.java
* 
* <p>Description: 資料彙整狀態歷程檔</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/06, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Entity
@Table(name = "AI_DATA_SYNC_LOG")
public class AiDataSyncLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公司鍵值
     */
    @Basic
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /**
     * 建立時間
     */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 資料彙整項目(1:發送富證彙整註記異動、2:發送富壽彙整註記異動)
     */
    @Basic
    @Column(name = "ITEM")
    private Integer item;

    /**
     * 流水號
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AI_DATA_SYNC_LOG_SEQ")
    @SequenceGenerator(name = "AI_DATA_SYNC_LOG_SEQ", sequenceName = "AI_DATA_SYNC_LOG_SEQ", allocationSize = 1)
    @Column(name = "LOG_SEQ")
    private Integer logSeq;

    /**
     * 異動結果代碼
     */
    @Basic
    @Column(name = "RESULT_CODE")
    private String resultCode;

    /**
     * 異動結果說明
     */
    @Basic
    @Column(name = "RESULT_DESC")
    private String resultDesc;

    /**
     * 彙整狀態註記(Y:同意彙整、N:不同意彙整)
     */
    @Basic
    @Column(name = "STATUS")
    private String status;

    /**
     * 異動情境
     */
    @Basic
    @Column(name = "USE_TYPE")
    private Integer useType;

    /**
     * 使用者鍵值
     */
    @Basic
    @Column(name = "USER_KEY")
    private Integer userKey;

    /**
     *  通路代碼  Fubon+ : 1  網銀: 2
     */
    @Basic
    @Column(name = "CHANNEL_ID")
    private String channelId;

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
     * 取得資料彙整項目(1:發送富證彙整註記異動、2:發送富壽彙整註記異動)
     * 
     * @return Integer 資料彙整項目(1:發送富證彙整註記異動、2:發送富壽彙整註記異動)
     */
    public Integer getItem() {
        return this.item;
    }

    /**
     * 設定資料彙整項目(1:發送富證彙整註記異動、2:發送富壽彙整註記異動)
     * 
     * @param item
     *            要設定的資料彙整項目(1:發送富證彙整註記異動、2:發送富壽彙整註記異動)
     */
    public void setItem(Integer item) {
        this.item = item;
    }

    /**
     * 取得流水號
     * 
     * @return Integer 流水號
     */
    public Integer getLogSeq() {
        return this.logSeq;
    }

    /**
     * 設定流水號
     * 
     * @param logSeq
     *            要設定的流水號
     */
    public void setLogSeq(Integer logSeq) {
        this.logSeq = logSeq;
    }

    /**
     * 取得異動結果代碼
     * 
     * @return String 異動結果代碼
     */
    public String getResultCode() {
        return this.resultCode;
    }

    /**
     * 設定異動結果代碼
     * 
     * @param resultCode
     *            要設定的異動結果代碼
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * 取得異動結果說明
     * 
     * @return String 異動結果說明
     */
    public String getResultDesc() {
        return this.resultDesc;
    }

    /**
     * 設定異動結果說明
     * 
     * @param resultDesc
     *            要設定的異動結果說明
     */
    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    /**
     * 取得彙整狀態註記(Y:同意彙整、N:不同意彙整)
     * 
     * @return String 彙整狀態註記(Y:同意彙整、N:不同意彙整)
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * 設定彙整狀態註記(Y:同意彙整、N:不同意彙整)
     * 
     * @param status
     *            要設定的彙整狀態註記(Y:同意彙整、N:不同意彙整)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 取得異動情境
     * 
     * @return Integer 異動情境
     */
    public Integer getUseType() {
        return this.useType;
    }

    /**
     * 設定異動情境
     * 
     * @param useType
     *            要設定的異動情境
     */
    public void setUseType(Integer useType) {
        this.useType = useType;
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

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
