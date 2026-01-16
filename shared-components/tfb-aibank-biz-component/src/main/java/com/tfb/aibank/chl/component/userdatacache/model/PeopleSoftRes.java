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

// @formatter:off
/**
 * @(#)PeopleSoftRes.java
 * 
 * <p>Description:前次填答結果頁 第二部分</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/09, Jackie Chien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class PeopleSoftRes {
    private String profileTestId;
    private String examId;
    private String qId;
    private String qSequence;
    private String qName;
    private String answer;
    private String qNameC;
    private String qaNo;

    public String getProfileTestId() {
        return profileTestId;
    }

    public void setProfileTestId(String profileTestId) {
        this.profileTestId = profileTestId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getqId() {
        return qId;
    }

    public void setqId(String qId) {
        this.qId = qId;
    }

    public String getqSequence() {
        return qSequence;
    }

    public void setqSequence(String qSequence) {
        this.qSequence = qSequence;
    }

    public String getqName() {
        return qName;
    }

    public void setqName(String qName) {
        this.qName = qName;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getqNameC() {
        return qNameC;
    }

    public void setqNameC(String qNameC) {
        this.qNameC = qNameC;
    }

    public String getQaNo() {
        return qaNo;
    }

    public void setQaNo(String qaNo) {
        this.qaNo = qaNo;
    }

}
