package com.tfb.aibank.biz.systemconfig.repository.entities;

import java.io.Serializable;

import com.tfb.aibank.biz.systemconfig.repository.entities.pk.SystemParamEntityPk;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)SystemParamEntity.java
 * 
 * <p>Description:系統參數檔 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "AI_SYSTEM_PARAM")
@IdClass(SystemParamEntityPk.class)
public class SystemParamEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分類
     */
    @Id
    @Column(name = "CATEGORY")
    private String category;

    /**
     * 說明
     */
    @Basic
    @Column(name = "MEMO")
    private String memo;

    /**
     * 鍵值
     */
    @Id
    @Column(name = "PARAM_KEY")
    private String paramKey;

    /**
     * 參數值
     */
    @Basic
    @Column(name = "PARAM_VALUE")
    private String paramValue;

    /**
     * 是否為密碼欄位0：否1：是
     */
    @Basic
    @Column(name = "PASSWORD_FLAG")
    private int passwordFlag;

    /**
     * 取得分類
     * 
     * @return String 分類
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * 設定分類
     * 
     * @param category
     *            要設定的分類
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 取得說明
     * 
     * @return String 說明
     */
    public String getMemo() {
        return this.memo;
    }

    /**
     * 設定說明
     * 
     * @param memo
     *            要設定的說明
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 取得鍵值
     * 
     * @return String 鍵值
     */
    public String getParamKey() {
        return this.paramKey;
    }

    /**
     * 設定鍵值
     * 
     * @param paramKey
     *            要設定的鍵值
     */
    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    /**
     * 取得參數值
     * 
     * @return String 參數值
     */
    public String getParamValue() {
        return this.paramValue;
    }

    /**
     * 設定參數值
     * 
     * @param paramValue
     *            要設定的參數值
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    /**
     * 取得是否為密碼欄位0：否1：是
     * 
     * @return int 是否為密碼欄位0：否1：是
     */
    public int getPasswordFlag() {
        return this.passwordFlag;
    }

    /**
     * 設定是否為密碼欄位0：否1：是
     * 
     * @param passwordFlag
     *            要設定的是否為密碼欄位0：否1：是
     */
    public void setPasswordFlag(int passwordFlag) {
        this.passwordFlag = passwordFlag;
    }
}
