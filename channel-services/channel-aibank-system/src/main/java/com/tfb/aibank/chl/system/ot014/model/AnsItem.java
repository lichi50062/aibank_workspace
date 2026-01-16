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
package com.tfb.aibank.chl.system.ot014.model;

// @formatter:off
/**
 * @(#)AnsItem.java
 * 
 * <p>Description:填答資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/08, Jackie Chien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class AnsItem {
    /**
     * 問題
     */
    private String title;

    /**
     * 回答
     */
    private String answer;

    public AnsItem() {
    }

    public AnsItem(String title, String answer) {
        this.title = title;
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
