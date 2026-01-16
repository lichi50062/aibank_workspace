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

// @formatter:off
/**
 * @(#)KYCAnswerResponse.java
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
public class KycAnswerResponse implements Serializable {
    private String title;

    private String company;

    private String cell;

    private String carCod;

    private String email;

    private String eduCod;

    private String occupation;

    private String marrage;

    private String childCod;

    //========EB032675===========
    public String sickType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

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

    public String getCarCod() {
        return carCod;
    }

    public void setCarCod(String carCod) {
        this.carCod = carCod;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }
}
