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

import java.util.List;
// @formatter:off
 /**
 * @(#)QAItem.java
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

public class QAItem {
    /**
     * 問題
     */
    private String question;

    /**
     * 回答
     */
    private String answer;

    /**
     * 回答
     */
    private List<AnsItem> answerList;

    public QAItem() {
    }

    public QAItem(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public QAItem(String question, List<AnsItem> answer) {
        this.question = question;
        this.answerList = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<AnsItem> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnsItem> answerList) {
        this.answerList = answerList;
    }
}
