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
package com.tfb.aibank.chl.system.resource.dto;

import java.io.Serializable;

// @formatter:off
/**
 * @(#)EB1202006KYCModel.java
 * 
 * <p>Description:KYC填答結果查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/08, Jackie Chien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class KYCAnswerResponse implements Serializable {

    private String email;

    private String eduCod;

    private String occupation;

    private String marrage;

    private String childCod;

    //========EB032675===========
    public String sickType;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEduCod() {
        return eduCod;
    }

    public void setEduCod(String eduCod) {
        this.eduCod = eduCod;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getMarrage() {
        return marrage;
    }

    public void setMarrage(String marrage) {
        this.marrage = marrage;
    }

    public String getChildCod() {
        return childCod;
    }

    public void setChildCod(String childCod) {
        this.childCod = childCod;
    }

    public String getSickType() {
        return sickType;
    }

    public void setSickType(String sickType) {
        this.sickType = sickType;
    }
}
