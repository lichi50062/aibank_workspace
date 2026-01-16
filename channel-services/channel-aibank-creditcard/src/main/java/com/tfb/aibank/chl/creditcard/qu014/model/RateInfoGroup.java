/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2013.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.creditcard.qu014.model;

import com.tfb.aibank.chl.creditcard.resource.dto.RateInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @formatter:off
/**
 * @(#)RateInfoGroup.java
 *
 * <p>Description:RateInfo群組: 以effect_date為組</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/06/14
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class RateInfoGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    private String effectDateStr;

    private Boolean newest;

    private List<RateInfo> rateInfos;


    public String getEffectDateStr() {
        return effectDateStr;
    }

    public void setEffectDateStr(String effectDateStr) {
        this.effectDateStr = effectDateStr;
    }

    public List<RateInfo> getRateInfos() {
        return rateInfos;
    }

    public void setRateInfos(List<RateInfo> rateInfos) {
        this.rateInfos = rateInfos;
    }

    public Boolean getNewest() {
        return newest;
    }

    public void setNewest(Boolean newest) {
        this.newest = newest;
    }
}
