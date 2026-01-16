package com.tfb.aibank.biz.user.repository.entities;

import java.io.Serializable;
import java.util.Date;

import com.tfb.aibank.biz.user.repository.entities.pk.BiometricsBlackListEntityPk;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)BiometricsBlackListEntity.java
 * 
 * <p>Description:生物辨識裝置黑名單 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "BIOMETRICS_BLACK_LIST")
@IdClass(BiometricsBlackListEntityPk.class)
public class BiometricsBlackListEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 
     * 
     */
    @Id
    @Column(name = "PHONE_LABEL")
    private String phoneLabel;

    /** 
     * 
     */
    @Id
    @Column(name = "PHONE_MODEL")
    private String phoneModel;

    /** 
     * 
     */
    @Id
    @Column(name = "PHONE_VERSION")
    private String phoneVersion;

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
    @Column(name = "MEMO")
    private String memo;

    /** 
     * 
     */
    @Basic
    @Column(name = "PHONE_NAME")
    private String phoneName;

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
     * @return String
     */
    public String getMemo() {
        return this.memo;
    }

    /**
     * 設定
     * 
     * @param memo
     *            要設定的
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getPhoneLabel() {
        return this.phoneLabel;
    }

    /**
     * 設定
     * 
     * @param phoneLabel
     *            要設定的
     */
    public void setPhoneLabel(String phoneLabel) {
        this.phoneLabel = phoneLabel;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getPhoneModel() {
        return this.phoneModel;
    }

    /**
     * 設定
     * 
     * @param phoneModel
     *            要設定的
     */
    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getPhoneName() {
        return this.phoneName;
    }

    /**
     * 設定
     * 
     * @param phoneName
     *            要設定的
     */
    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getPhoneVersion() {
        return this.phoneVersion;
    }

    /**
     * 設定
     * 
     * @param phoneVersion
     *            要設定的
     */
    public void setPhoneVersion(String phoneVersion) {
        this.phoneVersion = phoneVersion;
    }
}
