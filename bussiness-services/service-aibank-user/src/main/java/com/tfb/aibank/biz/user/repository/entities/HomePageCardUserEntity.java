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

import com.tfb.aibank.biz.user.repository.entities.pk.HomePageCardUserPk;

import jakarta.persistence.Basic;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

//@formatter:off
/**
* @(#)HomePageCardUserEntity.java
* 
* <p>Description:使用者首頁牌卡設定 - Entity</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Cacheable(false)
@Entity
@Table(name = "HOMEPAGE_CARD_USER")
@IdClass(HomePageCardUserPk.class)
public class HomePageCardUserEntity {

    /** 公司鍵值 */
    @Id
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /** 使用者鍵值 */
    @Id
    @Column(name = "USER_KEY")
    private Integer userKey;

    /** 牌卡編號 */
    @Id
    @Column(name = "CARD_ID")
    private Integer cardId;

    /** 設定順序 */
    @Basic
    @Column(name = "CARD_SORT")
    private Integer cardSort;

    /** 建立時間 */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /** 更新時間 */
    @Basic
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

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
