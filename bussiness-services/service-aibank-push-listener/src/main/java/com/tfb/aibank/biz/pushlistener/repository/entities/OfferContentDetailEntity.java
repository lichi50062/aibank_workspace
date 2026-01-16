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
 * @(#)OfferContentDetailEntity.java
 * 
 * <p>Description:個人化情境文案檔 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/06, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@Entity
@Table(name = "OFFER_CONTENT_DETAIL")
public class OfferContentDetailEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 情境文案鍵值
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OFFER_CONTENT_KEY_SEQ")
    @SequenceGenerator(name = "OFFER_CONTENT_KEY_SEQ", sequenceName = "OFFER_CONTENT_KEY_SEQ", allocationSize = 1)
    @Column(name = "OFFER_CONTENT_KEY")
    private Integer offerContentKey;

    /**
     * 情境鍵值
     */
    @Basic
    @Column(name = "OFFER_MASTER_KEY")
    private Integer offerMasterKey;

    /**
     * 版型代號
     */
    @Basic
    @Column(name = "TEMPLATE_ID")
    private String templateId;

    /**
     * 屬性 以逗號,分隔，舉例：All,All
     */
    @Basic
    @Column(name = "BEHAVIOR_TAG")
    private String behaviorTag;

    /**
     * 顯示內容
     */
    @Basic
    @Column(name = "CONTENT")
    private String content;

    /**
     * @return the offerContentKey
     */
    public Integer getOfferContentKey() {
        return offerContentKey;
    }

    /**
     * @param offerContentKey
     *            the offerContentKey to set
     */
    public void setOfferContentKey(Integer offerContentKey) {
        this.offerContentKey = offerContentKey;
    }

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
     * @return the behaviorTag
     */
    public String getBehaviorTag() {
        return behaviorTag;
    }

    /**
     * @param behaviorTag
     *            the behaviorTag to set
     */
    public void setBehaviorTag(String behaviorTag) {
        this.behaviorTag = behaviorTag;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

}
