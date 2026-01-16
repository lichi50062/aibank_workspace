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
package com.tfb.aibank.biz.pushlistener.repository.entities;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

//@formatter:off
/**
 * @(#)OfferMasterEntity.java
 * 
 * <p>Description:個人化情境主檔 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/06,Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@Entity
@Table(name = "OFFER_MASTER")
public class OfferMasterEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 情境鍵值
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OFFER_MASTER_KEY_SEQ")
    @SequenceGenerator(name = "OFFER_MASTER_KEY_SEQ", sequenceName = "OFFER_MASTER_KEY_SEQ", allocationSize = 1)
    @Column(name = "OFFER_MASTER_KEY")
    private Integer offerMasterKey;

    /**
     * 情境類型 C：Convenient X：X-sell A：Advisory
     */
    @Basic
    @Column(name = "OFFER_TYPE")
    private String offerType;

    /**
     * 情境編號
     */
    @Basic
    @Column(name = "OFFER_ID")
    private String offerId;

    /**
     * 情境說明
     */
    @Basic
    @Column(name = "OFFER_DESC")
    private String offerDesc;

    /**
     * 觸發條件
     */
    @Basic
    @Column(name = "CONDITION")
    private String condition;

    /**
     * 情境分數
     */
    @Basic
    @Column(name = "SCORE")
    private Integer score;

    /**
     * 開始日期時間
     */
    @Basic
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    /**
     * 結束日期時間
     */
    @Basic
    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    /**
     * 是否為預設情境
     */
    @Basic
    @Column(name = "IS_DEFAULT")
    private String isDefault;

    /**
     * 更新時間
     */
    @Basic
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 建立時間
     */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 產品類型
     */
    @Basic
    @Column(name = "PRODUCT_TYPE_1")
    private String productType1;

    /**
     * @return the offerMasterKey
     */
    public Integer getOfferMasterKey() {
        return offerMasterKey;
    }

    /**
     * @param offerMasterKey
     *            the offerMasterKey to set
     */
    public void setOfferMasterKey(Integer offerMasterKey) {
        this.offerMasterKey = offerMasterKey;
    }

    /**
     * @return the offerType
     */
    public String getOfferType() {
        return offerType;
    }

    /**
     * @param offerType
     *            the offerType to set
     */
    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    /**
     * @return the offerId
     */
    public String getOfferId() {
        return offerId;
    }

    /**
     * @param offerId
     *            the offerId to set
     */
    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    /**
     * @return the offerDesc
     */
    public String getOfferDesc() {
        return offerDesc;
    }

    /**
     * @param offerDesc
     *            the offerDesc to set
     */
    public void setOfferDesc(String offerDesc) {
        this.offerDesc = offerDesc;
    }

    /**
     * @return the condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * @param condition
     *            the condition to set
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * @return the score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * @param score
     *            the score to set
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     *            the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the isDefault
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * @param isDefault
     *            the isDefault to set
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
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
     * @return the productType1
     */
    public String getProductType1() {
        return productType1;
    }

    /**
     * @param productType1
     *            the productType1 to set
     */
    public void setProductType1(String productType1) {
        this.productType1 = productType1;
    }

}