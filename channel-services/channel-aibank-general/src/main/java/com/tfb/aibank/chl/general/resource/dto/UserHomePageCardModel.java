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
package com.tfb.aibank.chl.general.resource.dto;

import java.util.Date;

//@formatter:off
/**
* @(#)UserHomePageCardModel.java
* 
* <p>Description:使用者首頁牌卡設定</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class UserHomePageCardModel {

    /** 公司鍵值 */
    private Long companyKey;

    /** 使用者鍵值 */
    private Long userKey;

    /** 牌卡編號 */
    private Integer cardId;

    /** 設定順序 */
    private Integer cardSort;

    /** 建立時間 */
    private Date createTime;

    /** 更新時間 */
    private Date updateTime;

    /**
     * @return the companyKey
     */
    public Long getCompanyKey() {
        return companyKey;
    }

    /**
     * @param companyKey
     *            the companyKey to set
     */
    public void setCompanyKey(Long companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * @return the userKey
     */
    public Long getUserKey() {
        return userKey;
    }

    /**
     * @param userKey
     *            the userKey to set
     */
    public void setUserKey(Long userKey) {
        this.userKey = userKey;
    }

    /**
     * @return the cardId
     */
    public Integer getCardId() {
        return cardId;
    }

    /**
     * @param cardId
     *            the cardId to set
     */
    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    /**
     * @return the cardSort
     */
    public Integer getCardSort() {
        return cardSort;
    }

    /**
     * @param cardSort
     *            the cardSort to set
     */
    public void setCardSort(Integer cardSort) {
        this.cardSort = cardSort;
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

}
