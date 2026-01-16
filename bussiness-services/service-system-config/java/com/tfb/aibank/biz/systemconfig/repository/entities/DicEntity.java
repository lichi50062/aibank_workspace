package com.tfb.aibank.biz.systemconfig.repository.entities;

import java.io.Serializable;

import com.tfb.aibank.biz.systemconfig.repository.entities.pk.DicEntityPk;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

//@formatter:off
/**
 * @(#)DicEntity.java   
 * 
 * <p>Description:AIBank Dic  Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/13 John Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@Entity
@Table(name = "DIC")
@IdClass(DicEntityPk.class)
public class DicEntity implements Serializable {

    private static final long serialVersionUID = 6239614460284546197L;

    /**
     * 分類
     */
    @Id
    @Column(name = "CATEGORY")
    private String category;

    /**
     * 鍵值
     */
    @Id
    @Column(name = "DIC_KEY")
    private String dicKey;

    /**
     * 參數值
     */
    @Basic
    @Column(name = "DIC_VALUE")
    private String dicValue;

    /**
     * 順序
     */
    @Basic
    @Column(name = "INDEX_NO")
    private int indexNo;

    /**
     * 說明
     */
    @Basic
    @Column(name = "MEMO")
    private String memo;

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category
     *            the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the dicKey
     */
    public String getDicKey() {
        return dicKey;
    }

    /**
     * @param dicKey
     *            the dicKey to set
     */
    public void setDicKey(String dicKey) {
        this.dicKey = dicKey;
    }

    /**
     * @return the dicValue
     */
    public String getDicValue() {
        return dicValue;
    }

    /**
     * @param dicValue
     *            the dicValue to set
     */
    public void setDicValue(String dicValue) {
        this.dicValue = dicValue;
    }

    /**
     * @return the indexNo
     */
    public int getIndexNo() {
        return indexNo;
    }

    /**
     * @param indexNo
     *            the indexNo to set
     */
    public void setIndexNo(int indexNo) {
        this.indexNo = indexNo;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

}