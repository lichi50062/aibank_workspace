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
package com.tfb.aibank.chl.general.resource.dto;

import java.util.List;

// @formatter:off
/**
 * @(#)NMI001Res.java
 * 
 * <p>Description:NMP8YB 下行欄位 RS</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/31, Jackie Chien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NMP8YBRes {

    /** 傳送序號 */
    private String spRefId;

    /** 資料筆數 */
    private int occur;

    /** 資料筆數 */
    private List<NMP8YBResRep> repeats;

    /**
     * @return the spRefId
     */
    public String getSpRefId() {
        return spRefId;
    }

    /**
     * @param spRefId
     *            the spRefId to set
     */
    public void setSpRefId(String spRefId) {
        this.spRefId = spRefId;
    }

    /**
     * @return the occur
     */
    public int getOccur() {
        return occur;
    }

    /**
     * @param occur
     *            the occur to set
     */
    public void setOccur(int occur) {
        this.occur = occur;
    }

    /**
     * @return the repeats
     */
    public List<NMP8YBResRep> getRepeats() {
        return repeats;
    }

    /**
     * @param repeats
     *            the repeats to set
     */
    public void setRepeats(List<NMP8YBResRep> repeats) {
        this.repeats = repeats;
    }
}
