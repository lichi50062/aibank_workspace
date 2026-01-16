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

import java.io.Serializable;

// @formatter:off
/**
 * @(#)ProductsItem.java
 * 
 * <p>Description:投保商品</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/06, Jackie Chien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ProductsItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 險種代碼 */
    private String planCode;

    /** 優惠保費 */
    private String premium;

    /** 原始保費 */
    private String oriPremium;

    /** 保額(元) */
    private String faceAmt;

    /** 版本 */
    private String version;

    /** 參數1 */
    private String param1;

    /** 參數2 */
    private String param2;

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getOriPremium() {
        return oriPremium;
    }

    public void setOriPremium(String oriPremium) {
        this.oriPremium = oriPremium;
    }

    public String getFaceAmt() {
        return faceAmt;
    }

    public void setFaceAmt(String faceAmt) {
        this.faceAmt = faceAmt;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }
}
