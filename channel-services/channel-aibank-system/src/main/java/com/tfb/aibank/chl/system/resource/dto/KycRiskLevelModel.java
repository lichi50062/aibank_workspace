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
 * @(#)KycRiskLevelModel.java
 * 
 * <p>Description:KycRiskLevel model</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/08, Jackie Chien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class KycRiskLevelModel implements Serializable {

    /**
     *
     */
    private String locale;

    /**
     *
     */
    private String riskCode;

    /**
     *
     */
    private String riskDescription;

    /**
     *
     */
    private String riskName;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getRiskCode() {
        return riskCode;
    }

    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }

    public String getRiskDescription() {
        return riskDescription;
    }

    public void setRiskDescription(String riskDescription) {
        this.riskDescription = riskDescription;
    }

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }
}
