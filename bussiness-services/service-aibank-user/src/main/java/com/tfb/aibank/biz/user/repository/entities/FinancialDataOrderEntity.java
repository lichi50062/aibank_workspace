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
package com.tfb.aibank.biz.user.repository.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)FinancialDataOrderEntity.java
 * 
 * <p>Description:[投資理財資料排序]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/26, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "FINANCIAL_DATA_ORDER")
@IdClass(com.tfb.aibank.biz.user.repository.entities.pk.FinancialDataOrderEntityPk.class)
public class FinancialDataOrderEntity {

    /** 公司鍵值 */
    @Id
    @Column(name = "COMPANY_KEY", nullable = false)
    private Integer companyKey;

    /** 使用者鍵值 */
    @Id
    @Column(name = "USER_KEY", nullable = false)
    private Integer userKey;

    /** 交易代號 */
    @Id
    @Column(name = "TASK_ID", nullable = false)
    private String taskId;

    /**
     * 資料排序 <br/>
     * DATA_ORDER = NMI,FUND,ETF 以逗號分隔[牌卡名稱]
     */
    @Column(name = "DATA_ORDER")
    private String dataOrder;

    /** 建立時間 */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * @return the companyKxy
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * @param companyKxy
     *            the companyKxy to set
     */
    public void setCompanyKey(Integer companyKxy) {
        this.companyKey = companyKxy;
    }

    /**
     * @return the userKey
     */
    public Integer getUserKey() {
        return userKey;
    }

    /**
     * @param userKey
     *            the userKxy to set
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    /**
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * @param taskId
     *            the taskId to set
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the dataOrder
     */
    public String getDataOrder() {
        return dataOrder;
    }

    /**
     * @param dataOrder
     *            the dataOrder to set
     */
    public void setDataOrder(String dataOrder) {
        this.dataOrder = dataOrder;
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

}
