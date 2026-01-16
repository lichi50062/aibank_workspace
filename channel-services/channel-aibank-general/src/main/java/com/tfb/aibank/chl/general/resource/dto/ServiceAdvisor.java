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
package com.tfb.aibank.chl.general.resource.dto;


// @formatter:off
/**
 * @(#)ServiceAdvisor.java
 * 
 * <p>Description:專屬服務專員</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/25,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class ServiceAdvisor {

    /**
     * 理專員編
     */
    private String advisorEmpId;

    /**
     * 理專姓名
     */
    private String advisorName;


    /**
     * 所屬分行
     */
    private String branchId;

    /**
     * 理專電話
     */
    private String advisorPhoneNo;

    /**
     * 理專Email
     */
    private String advisorEmail;

    // ===============
    private String branchName;

    private String branchPhoneNo;

    public String getAdvisorEmail() {
        return advisorEmail;
    }

    public void setAdvisorEmail(String advisorEmail) {
        this.advisorEmail = advisorEmail;
    }

    public String getAdvisorEmpId() {
        return advisorEmpId;
    }

    public void setAdvisorEmpId(String advisorEmpId) {
        this.advisorEmpId = advisorEmpId;
    }

    public String getAdvisorName() {
        return advisorName;
    }

    public void setAdvisorName(String advisorName) {
        this.advisorName = advisorName;
    }

    public String getAdvisorPhoneNo() {
        return advisorPhoneNo;
    }

    public void setAdvisorPhoneNo(String advisorPhoneNo) {
        this.advisorPhoneNo = advisorPhoneNo;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchPhoneNo() {
        return branchPhoneNo;
    }

    public void setBranchPhoneNo(String branchPhoneNo) {
        this.branchPhoneNo = branchPhoneNo;
    }
}
