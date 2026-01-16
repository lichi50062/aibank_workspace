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
package com.tfb.aibank.biz.user.repository.entities.pk;

// @formatter:off
/**
 * @(#)FinancialDataOrderEntityPk.java
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
public class FinancialDataOrderEntityPk {

    /** 公司鍵值 */
    private Integer companyKey;

    /** 使用者鍵值 */
    private Integer userKey;

    /** 交易代號 */
    private String taskId;

    /**
     * @return the companyKxy
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

}
