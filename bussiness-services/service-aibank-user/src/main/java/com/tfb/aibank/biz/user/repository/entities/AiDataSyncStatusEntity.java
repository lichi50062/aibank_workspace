
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
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@formatter:off
/**
* @(#)AiDataSyncStatusEntity.java
* 
* <p>Description: 資料彙整狀態註記檔</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/06, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Entity
@Table(name = "AI_DATA_SYNC_STATUS")
public class AiDataSyncStatusEntity implements Serializable {

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
     * 富壽註記(Y：同意彙整、N：不同意彙整)
     */
    @Basic
    @Column(name = "INSUR_STATUS")
    private String insurStatus;

    /**
     * 異動情境(富壽)(0:未曾發過API、1:初次設定、2：使用者設定、3：系統自動關閉、4：銀行銷戶)
     */
    @Basic
    @Column(name = "INSUR_TYPE")
    private Integer insurType;

    /**
     * 證券註記(Y：同意彙整、N：不同意彙整)
     */
    @Basic
    @Column(name = "SECUR_STATUS")
    private String securStatus;

    /**
     * 異動情境(證券)(0:未曾發過API、1:初次設定、2：使用者設定、3：系統自動關閉、4：銀行銷戶)
     */
    @Basic
    @Column(name = "SECUR_TYPE")
    private Integer securType;

    /**
     * 更新時間
     */
    @Basic
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 使用者鍵值
     */
    @Basic
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
     * 取得富壽註記(Y：同意彙整、N：不同意彙整)
     * 
     * @return String 富壽註記(Y：同意彙整、N：不同意彙整)
     */
    public String getInsurStatus() {
        return this.insurStatus;
    }

    /**
     * 設定富壽註記(Y：同意彙整、N：不同意彙整)
     * 
     * @param insurStatus
     *            要設定的富壽註記(Y：同意彙整、N：不同意彙整)
     */
    public void setInsurStatus(String insurStatus) {
        this.insurStatus = insurStatus;
    }

    /**
     * 取得異動情境(富壽)(0:未曾發過API、1:初次設定、2：使用者設定、3：系統自動關閉、4：銀行銷戶)
     * 
     * @return Integer 異動情境(富壽)(0:未曾發過API、1:初次設定、2：使用者設定、3：系統自動關閉、4：銀行銷戶)
     */
    public Integer getInsurType() {
        return this.insurType;
    }

    /**
     * 設定異動情境(富壽)(0:未曾發過API、1:初次設定、2：使用者設定、3：系統自動關閉、4：銀行銷戶)
     * 
     * @param insurType
     *            要設定的異動情境(富壽)(0:未曾發過API、1:初次設定、2：使用者設定、3：系統自動關閉、4：銀行銷戶)
     */
    public void setInsurType(Integer insurType) {
        this.insurType = insurType;
    }

    /**
     * 取得證券註記(Y：同意彙整、N：不同意彙整)
     * 
     * @return String 證券註記(Y：同意彙整、N：不同意彙整)
     */
    public String getSecurStatus() {
        return this.securStatus;
    }

    /**
     * 設定證券註記(Y：同意彙整、N：不同意彙整)
     * 
     * @param securStatus
     *            要設定的證券註記(Y：同意彙整、N：不同意彙整)
     */
    public void setSecurStatus(String securStatus) {
        this.securStatus = securStatus;
    }

    /**
     * 取得異動情境(證券)(0:未曾發過API、1:初次設定、2：使用者設定、3：系統自動關閉、4：銀行銷戶)
     * 
     * @return Integer 異動情境(證券)(0:未曾發過API、1:初次設定、2：使用者設定、3：系統自動關閉、4：銀行銷戶)
     */
    public Integer getSecurType() {
        return this.securType;
    }

    /**
     * 設定異動情境(證券)(0:未曾發過API、1:初次設定、2：使用者設定、3：系統自動關閉、4：銀行銷戶)
     * 
     * @param securType
     *            要設定的異動情境(證券)(0:未曾發過API、1:初次設定、2：使用者設定、3：系統自動關閉、4：銀行銷戶)
     */
    public void setSecurType(Integer securType) {
        this.securType = securType;
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
     * @param updateTime
     *            要設定的更新時間
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
