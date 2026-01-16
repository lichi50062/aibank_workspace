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
 * @(#)UpdateEB12020006.java
 * 
 * <p>Description: 更新KYC填答結果</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/21, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class UpdateEB12020006 implements Serializable {
    /** 功能代碼 */
    private String funcCod;

    /** 功能 */
    private String func;

    /** 統編 */
    private String idno;

    /** 戶名代碼 */
    private String nameCod;

    /** Email */
    private String email;

    /** 手機號碼 */
    private String cell;

    /** 變更後婚姻代碼 */
    private String marrCod;

    /** 變更後職業代碼 */
    private String carCod;

    /** 變更後子女人數 */
    private String childCod;

    /** 變更後教育代碼 */
    private String eduCod;

    /** 公司 */
    private String company;

    /** 職稱 */
    private String title;

    /** FILLER */
    private String filler;

    // getters and setters
    public String getFuncCod() {
        return funcCod;
    }

    public void setFuncCod(String funcCod) {
        this.funcCod = funcCod;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getNameCod() {
        return nameCod;
    }

    public void setNameCod(String nameCod) {
        this.nameCod = nameCod;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getMarrCod() {
        return marrCod;
    }

    public void setMarrCod(String marrCod) {
        this.marrCod = marrCod;
    }

    public String getCarCod() {
        return carCod;
    }

    public void setCarCod(String carCod) {
        this.carCod = carCod;
    }

    public String getChildCod() {
        return childCod;
    }

    public void setChildCod(String childCod) {
        this.childCod = childCod;
    }

    public String getEduCod() {
        return eduCod;
    }

    public void setEduCod(String eduCod) {
        this.eduCod = eduCod;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }
}
