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

import com.tfb.aibank.biz.pushlistener.repository.entities.pk.OfferNotificationInfoEntityPk;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

//@formatter:off
/**
* @(#)OfferNotificationInfoEntity.java
* 
* <p>Description:個人化情境推播設定資料 Entity</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/06, Evan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Entity
@Table(name = "OFFER_NOTIFICATION_INFO")
@IdClass(OfferNotificationInfoEntityPk.class)
public class OfferNotificationInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 情境鍵值
     */
    @Id
    @Column(name = "OFFER_MASTER_KEY")
    private Integer offerMasterKey;

    /**
     * 版型代號
     */
    @Id
    @Column(name = "TEMPLATE_ID")
    private String templateId;

    /**
     * 訊息類型
     */
    @Basic
    @Column(name = "TYPE")
    private String type;

    /**
     * 是否為必須發送 Y/N
     */
    @Basic
    @Column(name = "MUST_SEND")
    private String mustSend;

    /**
     * 發送時間
     */
    @Basic
    @Column(name = "PUSH_TIME")
    private String pushTime;

    /**
     * 生效日期
     */
    @Basic
    @Column(name = "EFFECTIVE_DATE")
    private String effectiveDate;

    /**
     * 優先序
     */
    @Basic
    @Column(name = "PRIORITY")
    private int priority;

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
     * @return the templateId
     */
    public String getTemplateId() {
        return templateId;
    }

    /**
     * @param templateId
     *            the templateId to set
     */
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the mustSend
     */
    public String getMustSend() {
        return mustSend;
    }

    /**
     * @param mustSend
     *            the mustSend to set
     */
    public void setMustSend(String mustSend) {
        this.mustSend = mustSend;
    }

    /**
     * @return the pushTime
     */
    public String getPushTime() {
        return pushTime;
    }

    /**
     * @param pushTime
     *            the pushTime to set
     */
    public void setPushTime(String pushTime) {
        this.pushTime = pushTime;
    }

    /**
     * @return the effectiveDate
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * @param effectiveDate
     *            the effectiveDate to set
     */
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @param priority
     *            the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

}
