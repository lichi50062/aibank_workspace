/*
 * ===========================================================================
 * IBM Confidential
 * AIS Source Materials
 *
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.mfdpunchline;

import java.io.Serializable;
import java.util.Map;

//@formatter:off
/**
* @(#)MfdPunchline.java
* 
* <p>基金警語Model  MfdPunchline + MfdRemuerationEntity </p>
* 
* <p>Modify History:</p>
* v1.0, 2024/02/17,
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class MfdPunchline implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 語系
     */
    private String locale;
    /**
     * 基金代碼
     */
    private String fundCode;

    /**
     * 基金警語代碼
     */
    private String punchlineCode;
    /**
     * 基金高收益債警語註記
     */
    private String highBenefitBondFlag;

    /**
     * 基金警語說明(punchlineDescription) Map By punchlineType(基金警語類別)
     */
    private Map<String, String> punchlineTypeMap;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getPunchlineCode() {
        return punchlineCode;
    }

    public void setPunchlineCode(String punchlineCode) {
        this.punchlineCode = punchlineCode;
    }

    public String getHighBenefitBondFlag() {
        return highBenefitBondFlag;
    }

    public void setHighBenefitBondFlag(String highBenefitBondFlag) {
        this.highBenefitBondFlag = highBenefitBondFlag;
    }

    public Map<String, String> getPunchlineTypeMap() {
        return punchlineTypeMap;
    }

    public void setPunchlineTypeMap(Map<String, String> punchlineTypeMap) {
        this.punchlineTypeMap = punchlineTypeMap;
    }
}
