package com.tfb.aibank.biz.user.repository.entities;

import java.util.Date;

import com.tfb.aibank.biz.user.repository.entities.support.CardUserProfileEntityListener;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)CardUserProfileEntity.java
 * 
 * <p>Description:信用卡專區會員資料 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "CARD_USER_PROFILE")
@EntityListeners(CardUserProfileEntityListener.class)
public class CardUserProfileEntity {

    public static final String SEQUENCE_NAME = "CARD_USER_PROFILE_SEQ";

    /**
     * 主檔鍵值
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @Column(name = "CARD_KEY")
    private Integer cardKey;

    /**
     * 交易存取記錄鍵值
     */
    @Basic
    @Column(name = "ACCESS_LOG_KEY")
    private Integer accessLogKey;

    /**
     * 信用卡CVV2 AES加密
     */
    @Basic
    @Column(name = "CARD_CVV2")
    private String cardCvv2;

    /**
     * 信用卡有效期限 MM/YYYY
     */
    @Basic
    @Column(name = "CARD_EXPIRED")
    private String cardExpired;

    /**
     * 正附卡別 0：正卡 1：附卡
     */
    @Basic
    @Column(name = "CARD_MASTER_FLAG")
    private String cardMasterFlag;

    /**
     * 信用卡卡號 AES加密
     */
    @Basic
    @Column(name = "CARD_NO")
    private String cardNo;

    /**
     * 公司鍵值
     */
    @Basic
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /**
     * 信用卡契約書版次
     */
    @Basic
    @Column(name = "CONTRACTVERSION")
    private String contractversion;

    /**
     *
     */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     *
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
     * 取得
     *
     * @return Integer
     */
    public Integer getAccessLogKey() {
        return this.accessLogKey;
    }

    /**
     * 設定
     *
     * @param accessLogKey
     *            要設定的
     */
    public void setAccessLogKey(Integer accessLogKey) {
        this.accessLogKey = accessLogKey;
    }

    /**
     * 取得信用卡CVV2 AES加密
     *
     * @return String 信用卡CVV2 AES加密
     */
    public String getCardCvv2() {
        return this.cardCvv2;
    }

    /**
     * 設定信用卡CVV2 AES加密
     *
     * @param cardCvv2
     *            要設定的信用卡CVV2 AES加密
     */
    public void setCardCvv2(String cardCvv2) {
        this.cardCvv2 = cardCvv2;
    }

    /**
     * 取得信用卡有效期限 MM/YYYY
     *
     * @return String 信用卡有效期限 MM/YYYY
     */
    public String getCardExpired() {
        return this.cardExpired;
    }

    /**
     * 設定信用卡有效期限 MM/YYYY
     *
     * @param cardExpired
     *            要設定的信用卡有效期限 MM/YYYY
     */
    public void setCardExpired(String cardExpired) {
        this.cardExpired = cardExpired;
    }

    /**
     * 取得主檔鍵值
     *
     * @return Integer 主檔鍵值
     */
    public Integer getCardKey() {
        return this.cardKey;
    }

    /**
     * 設定主檔鍵值
     *
     * @param cardKey
     *            要設定的主檔鍵值
     */
    public void setCardKey(Integer cardKey) {
        this.cardKey = cardKey;
    }

    /**
     * 取得正附卡別 0：正卡 1：附卡
     *
     * @return Integer 正附卡別 0：正卡 1：附卡
     */
    public String getCardMasterFlag() {
        return this.cardMasterFlag;
    }

    /**
     * 設定正附卡別 0：正卡 1：附卡
     *
     * @param cardMasterFlag
     *            要設定的正附卡別 0：正卡 1：附卡
     */
    public void setCardMasterFlag(String cardMasterFlag) {
        this.cardMasterFlag = cardMasterFlag;
    }

    /**
     * 取得信用卡卡號 AES加密
     *
     * @return String 信用卡卡號 AES加密
     */
    public String getCardNo() {
        return this.cardNo;
    }

    /**
     * 設定信用卡卡號 AES加密
     *
     * @param cardNo
     *            要設定的信用卡卡號 AES加密
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

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
     * 取得信用卡契約書版次
     *
     * @return String 信用卡契約書版次
     */
    public String getContractversion() {
        return this.contractversion;
    }

    /**
     * 設定信用卡契約書版次
     *
     * @param contractversion
     *            要設定的信用卡契約書版次
     */
    public void setContractversion(String contractversion) {
        this.contractversion = contractversion;
    }

    /**
     * 取得
     *
     * @return Date
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 設定
     *
     * @param createTime
     *            要設定的
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得
     *
     * @return Date
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 設定
     *
     * @param updateTime
     *            要設定的
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
