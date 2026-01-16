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
package com.tfb.aibank.chl.component.satisfaction;

import java.io.Serializable;

// @formatter:off
/**
 * @(#)AibankServiceQuestionOptionModel.java
 * 
 * <p>Description:AI Bank滿意度問卷選項</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/20, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AibankServiceQuestionOption implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 問卷資料鍵值 */
    private Integer questionKey;

    /** 頁面代號 */
    private String pageId;

    /** 問卷類型，星星數3以下：1；超過3：2 */
    private Integer questionType;

    /** 選項代號 */
    private Integer optionKey;

    /** 選項內容 */
    private String optionName;

    /** 選項排序 */
    private Integer optionSort;

    /** 語系 */
    private String locale;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public Integer getOptionKey() {
        return optionKey;
    }

    public void setOptionKey(Integer optionKey) {
        this.optionKey = optionKey;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public Integer getOptionSort() {
        return optionSort;
    }

    public void setOptionSort(Integer optionSort) {
        this.optionSort = optionSort;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Integer getQuestionKey() {
        return questionKey;
    }

    public void setQuestionKey(Integer questionKey) {
        this.questionKey = questionKey;
    }

}
