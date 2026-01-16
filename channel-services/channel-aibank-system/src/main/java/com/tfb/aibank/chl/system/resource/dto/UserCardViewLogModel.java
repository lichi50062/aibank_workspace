/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.system.resource.dto;

import java.util.Date;

//@formatter:off
/**
* @(#)UserCardViewLogModel.java
* 
* <p>Description: 用戶觀看廣告紀錄 model</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/05/14, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class UserCardViewLogModel {

    /**
     * 廣告牌卡鍵值
     */
    private Integer cardKey;

    /**
     * 牌卡應用交易
     */
    private String cardUsedTaskId;

    /**
     * 廣告牌卡版本
     */
    private Integer cardVersion;

    /**
     * 公司鍵值
     */
    private Integer companyKey;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 資料鍵值
     */
    private Integer userCardViewLogKey;

    /**
     * 使用者鍵值
     */
    private Integer userKey;

    public Integer getCardKey() {
        return cardKey;
    }

    public void setCardKey(Integer cardKey) {
        this.cardKey = cardKey;
    }

    public String getCardUsedTaskId() {
        return cardUsedTaskId;
    }

    public void setCardUsedTaskId(String cardUsedTaskId) {
        this.cardUsedTaskId = cardUsedTaskId;
    }

    public Integer getCardVersion() {
        return cardVersion;
    }

    public void setCardVersion(Integer cardVersion) {
        this.cardVersion = cardVersion;
    }

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserCardViewLogKey() {
        return userCardViewLogKey;
    }

    public void setUserCardViewLogKey(Integer userCardViewLogKey) {
        this.userCardViewLogKey = userCardViewLogKey;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }
}
