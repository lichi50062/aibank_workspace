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
import java.util.List;

// @formatter:off
/**
 * @(#)KYCQuestion.java
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
public class KycQuestion implements Serializable {

    private static final long serialVersionUID = 8661804309381909715L;

    public KycQuestion() {
        // default constructor
    }

    public KycQuestion(String optionsType, String questionClass, String questionCode, Integer questionKey, String questionName, Integer titleCode, String titleName, String verision, Integer questionSort, List<KycQuestionOption> options) {
        this.optionsType = optionsType;
        this.questionClass = questionClass;
        this.questionCode = questionCode;
        this.questionKey = questionKey;
        this.questionName = questionName;
        this.titleCode = titleCode;
        this.titleName = titleName;
        this.verision = verision;
        this.questionSort = questionSort;
        this.options = options;
    }

    private List<KycQuestionOption> options;

    /**
     * 問題類型
     */
    private String optionsType;

    /**
     * 問題層級
     */
    private String questionClass;

    /**
     * 問題代碼
     */
    private String questionCode;

    /**
     * 問題鍵值
     */
    private Integer questionKey;

    /**
     * 問題名稱
     */
    private String questionName;

    /**
     * 標題代碼
     */
    private Integer titleCode;

    /**
     * 標題名稱
     */
    private String titleName;

    /**
     * 問卷版本
     */
    private String verision;

    /**
     * 顯示順序
     */
    private Integer questionSort;

    public String getOptionsType() {
        return optionsType;
    }

    public void setOptionsType(String optionsType) {
        this.optionsType = optionsType;
    }

    public String getQuestionClass() {
        return questionClass;
    }

    public void setQuestionClass(String questionClass) {
        this.questionClass = questionClass;
    }

    public String getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }

    public Integer getQuestionKey() {
        return questionKey;
    }

    public void setQuestionKey(Integer questionKey) {
        this.questionKey = questionKey;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public Integer getTitleCode() {
        return titleCode;
    }

    public void setTitleCode(Integer titleCode) {
        this.titleCode = titleCode;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getVerision() {
        return verision;
    }

    public void setVerision(String verision) {
        this.verision = verision;
    }

    public Integer getQuestionSort() {
        return questionSort;
    }

    public void setQuestionSort(Integer questionSort) {
        this.questionSort = questionSort;
    }

    public List<KycQuestionOption> getOptions() {
        return options;
    }

    public void setOptions(List<KycQuestionOption> options) {
        this.options = options;
    }
}
