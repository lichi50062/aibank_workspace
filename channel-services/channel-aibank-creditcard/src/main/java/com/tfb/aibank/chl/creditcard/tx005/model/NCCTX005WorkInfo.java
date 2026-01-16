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
package com.tfb.aibank.chl.creditcard.tx005.model;

import com.tfb.aibank.chl.creditcard.resource.dto.CEW332RResponse;

// @formatter:off
/**
 * @(#)NCCTX005WorkInfo.java
 * 
 * <p>Description:工作資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/17, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCTX005WorkInfo {

    public NCCTX005WorkInfo() {
        // default constructor
    }

    public NCCTX005WorkInfo(CEW332RResponse cew332rRes) {
        // 歸戶ID
        this.acid = cew332rRes.getVnAcid();
        // 公司名稱
        this.company = cew332rRes.getVnCpnm();
        // 職稱
        this.jobTitle = cew332rRes.getVnTitl();
        // 區碼
        this.officeTelArea = cew332rRes.getVnTlo1();
        // 電話號碼
        this.officeTel = cew332rRes.getVnTlo2();
        // 分機
        this.officeTelExt = cew332rRes.getVnTel2();
        // 電子信箱
        this.email = cew332rRes.getVnMail();
    }

    /** 歸戶ID */
    private String acid;
    /** 公司名稱 */
    private String company;
    /** 職稱 */
    private String jobTitle;
    /** 公司電話-區碼 */
    private String officeTelArea;
    /** 公司電話 */
    private String officeTel;
    /** 公司電話-分機 */
    private String officeTelExt;
    /** 電子信箱 */
    private String email;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getOfficeTelArea() {
        return officeTelArea;
    }

    public void setOfficeTelArea(String officeTelArea) {
        this.officeTelArea = officeTelArea;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public String getOfficeTelExt() {
        return officeTelExt;
    }

    public void setOfficeTelExt(String officeTelExt) {
        this.officeTelExt = officeTelExt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAcid() {
        return acid;
    }

    public void setAcid(String acid) {
        this.acid = acid;
    }

}
