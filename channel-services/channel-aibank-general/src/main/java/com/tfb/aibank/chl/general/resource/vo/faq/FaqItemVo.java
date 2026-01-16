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
package com.tfb.aibank.chl.general.resource.vo.faq;

// @formatter:off
/**
 * @(#)FaqsItemVo.java
 * 
 * <p>Description: 常見問題項目 (Duplicate From Channel Preference)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/06, Evan Wang (Duplicate From Channel Preference)
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FaqItemVo {
    /** 資料鍵值 */
    private Integer itemKey;

    /** 問題標號 */
    private String questionKey;

    /** 問題項目 */
    private String questionDesc;

    /** 問題說明 */
    private String questionHtml;

    /**
     * @return the itemKey
     */
    public Integer getItemKey() {
        return itemKey;
    }

    /**
     * @param itemKey
     *            the itemKey to set
     */
    public void setItemKey(Integer itemKey) {
        this.itemKey = itemKey;
    }

    /**
     * @return the questionKey
     */
    public String getQuestionKey() {
        return questionKey;
    }

    /**
     * @param questionKey
     *            the questionKey to set
     */
    public void setQuestionKey(String questionKey) {
        this.questionKey = questionKey;
    }

    /**
     * @return the questionDesc
     */
    public String getQuestionDesc() {
        return questionDesc;
    }

    /**
     * @param questionDesc
     *            the questionDesc to set
     */
    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    /**
     * @return the questionHtml
     */
    public String getQuestionHtml() {
        return questionHtml;
    }

    /**
     * @param questionHtml
     *            the questionHtml to set
     */
    public void setQuestionHtml(String questionHtml) {
        this.questionHtml = questionHtml;
    }

}
