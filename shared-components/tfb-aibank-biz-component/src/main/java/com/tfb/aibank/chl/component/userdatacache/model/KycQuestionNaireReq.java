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
 * @(#)KycQuestionNaireReq.java
 *
 * <p>Description:KYC 填答資訊->業管 Request</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/06/20, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class KycQuestionNaireReq {
    private String method;
    private String branch;
    private String ip;
    private String userID;
    private String custID;
    private String examID;
    private String generalQuestion;
    private String generalAns;
    private String confirm;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getExamID() {
        return examID;
    }

    public void setExamID(String examID) {
        this.examID = examID;
    }

    public String getGeneralQuestion() {
        return generalQuestion;
    }

    public void setGeneralQuestion(String generalQuestion) {
        this.generalQuestion = generalQuestion;
    }

    public String getGeneralAns() {
        return generalAns;
    }

    public void setGeneralAns(String generalAns) {
        this.generalAns = generalAns;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}
