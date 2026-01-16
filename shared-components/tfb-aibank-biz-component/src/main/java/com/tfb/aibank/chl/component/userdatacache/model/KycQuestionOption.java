/*
 * ===========================================================================
 *
 * IBM Confidential
 *
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.userdatacache.model;

import java.io.Serializable;

// @formatter:off
/**
 * @(#)KYCQuestionOption.java
 * 
 * <p>Description:KYC填答結果查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/20, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class KycQuestionOption implements Serializable {

    private static final long serialVersionUID = 168822109191127681L;

    public KycQuestionOption() {
        // default constructor
    }

    public KycQuestionOption(String optionsCode, Integer optionsGrade, Integer optionsKey, String optionsName, String optionsType, Integer questionKey) {
        this.optionsCode = optionsCode;
        this.optionsGrade = optionsGrade;
        this.optionsKey = optionsKey;
        this.optionsName = optionsName;
        this.optionsType = optionsType;
        this.questionKey = questionKey;
    }

    /**
     * 選項代號
     */
    private String optionsCode;

    /**
     * 選項分數
     */
    private Integer optionsGrade;

    /**
     * 選項鍵值
     */
    private Integer optionsKey;

    /**
     * 選項名稱
     */
    private String optionsName;

    /**
     * 選項類型
     */
    private String optionsType;

    /**
     * 問題鍵值
     */
    private Integer questionKey;

    public String getOptionsCode() {
        return optionsCode;
    }

    public void setOptionsCode(String optionsCode) {
        this.optionsCode = optionsCode;
    }

    public Integer getOptionsGrade() {
        return optionsGrade;
    }

    public void setOptionsGrade(Integer optionsGrade) {
        this.optionsGrade = optionsGrade;
    }

    public Integer getOptionsKey() {
        return optionsKey;
    }

    public void setOptionsKey(Integer optionsKey) {
        this.optionsKey = optionsKey;
    }

    public String getOptionsName() {
        return optionsName;
    }

    public void setOptionsName(String optionsName) {
        this.optionsName = optionsName;
    }

    public String getOptionsType() {
        return optionsType;
    }

    public void setOptionsType(String optionsType) {
        this.optionsType = optionsType;
    }

    public Integer getQuestionKey() {
        return questionKey;
    }

    public void setQuestionKey(Integer questionKey) {
        this.questionKey = questionKey;
    }
}
