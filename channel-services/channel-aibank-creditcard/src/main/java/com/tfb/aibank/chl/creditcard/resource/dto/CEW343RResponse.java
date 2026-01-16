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
package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.List;

/**
 * 分期查詢交易 Body
 * 
 * @author Evan
 */
public class CEW343RResponse {

    /** 傳送序號 */
    private String sprefid;

    /** 資料筆數 */
    private String occur;

    /** 分期未入帳金額資料 */
    private List<CEW343RRepeat> repeats;

    /**
     * @return the sprefid
     */
    public String getSprefid() {
        return sprefid;
    }

    /**
     * @param sprefid
     *            the sprefid to set
     */
    public void setSprefid(String sprefid) {
        this.sprefid = sprefid;
    }

    /**
     * @return the occur
     */
    public String getOccur() {
        return occur;
    }

    /**
     * @param occur
     *            the occur to set
     */
    public void setOccur(String occur) {
        this.occur = occur;
    }

    /**
     * @return the repeats
     */
    public List<CEW343RRepeat> getRepeats() {
        return repeats;
    }

    /**
     * @param repeats
     *            the repeats to set
     */
    public void setRepeats(List<CEW343RRepeat> repeats) {
        this.repeats = repeats;
    }

}
