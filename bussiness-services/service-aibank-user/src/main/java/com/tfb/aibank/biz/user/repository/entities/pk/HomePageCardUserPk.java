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

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

//@formatter:off
/**
* @(#)HomePageCardUserPk.java
* 
* <p>Description:使用者首頁牌卡設定 - PK</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class HomePageCardUserPk implements Serializable {

    private static final long serialVersionUID = 2009951617773758772L;

    /** 公司鍵值 */
    private Integer companyKey;

    /** 使用者鍵值 */
    private Integer userKey;

    /** 牌卡編號 */
    private Integer cardId;

    /**
     * constructor
     */
    public HomePageCardUserPk() {
        super();
    }

    /**
     * constructor
     * 
     * @param companyKey
     * @param userKey
     * @param cardId
     */
    public HomePageCardUserPk(Integer companyKey, Integer userKey, Integer cardId) {
        super();
        this.companyKey = companyKey;
        this.userKey = userKey;
        this.cardId = cardId;
    }

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

    @Override
    public int hashCode() {

        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {

        return EqualsBuilder.reflectionEquals(this, obj);
    }

}
