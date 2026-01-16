package com.tfb.aibank.biz.user.repository.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)DebitCardEntity.java
 * 
 * <p>Description:簽帳卡卡面 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/04, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "DEBIT_CARD")
@IdClass(com.tfb.aibank.biz.user.repository.entities.pk.DebitCardEntityPk.class)
public class DebitCardEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 卡片中文名稱
     */
    @Basic
    @Column(name = "CARD_NAME")
    private String cardName;

    /**
     * 圖片來源
     */
    @Basic
    @Column(name = "CARD_PICTURE")
    private String cardPicture;

    /**
     * 卡片種類
     */
    @Id
    @Column(name = "CARD_TYPE")
    private int cardType;

    /**
     * 新增時間
     */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 語系
     */
    @Id
    @Column(name = "LOCALE")
    private String locale;

    /**
     * 新增時間
     */
    @Basic
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 取得卡片中文名稱
     * 
     * @return String 卡片中文名稱
     */
    public String getCardName() {
        return this.cardName;
    }

    /**
     * 設定卡片中文名稱
     * 
     * @param cardName
     *            要設定的卡片中文名稱
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * 取得圖片來源
     * 
     * @return String 圖片來源
     */
    public String getCardPicture() {
        return this.cardPicture;
    }

    /**
     * 設定圖片來源
     * 
     * @param cardPicture
     *            要設定的圖片來源
     */
    public void setCardPicture(String cardPicture) {
        this.cardPicture = cardPicture;
    }

    /**
     * 取得卡片種類
     * 
     * @return int 卡片種類
     */
    public int getCardType() {
        return this.cardType;
    }

    /**
     * 設定卡片種類
     * 
     * @param cardType
     *            要設定的卡片種類
     */
    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    /**
     * 取得新增時間
     * 
     * @return Date 新增時間
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 設定新增時間
     * 
     * @param createTime
     *            要設定的新增時間
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得語系
     * 
     * @return String 語系
     */
    public String getLocale() {
        return this.locale;
    }

    /**
     * 設定語系
     * 
     * @param locale
     *            要設定的語系
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * 取得新增時間
     * 
     * @return Date 新增時間
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 設定新增時間
     * 
     * @param updateTime
     *            要設定的新增時間
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
