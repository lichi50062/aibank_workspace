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
 * 取得分期未入帳金額下行電文 Body
 * 
 * @author Evan
 */
public class CEW326RResponse {

    /** 傳送序號 */
    private String sprefid;

    /** ID */
    private String id;

    /** 資料筆數 */
    private String occur;

    /** 分期未入帳金額資料 */
    private List<CEW326RRepeat> repeats;

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
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
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
    public List<CEW326RRepeat> getRepeats() {
        return repeats;
    }

    /**
     * @param repeats
     *            the repeats to set
     */
    public void setRepeats(List<CEW326RRepeat> repeats) {
        this.repeats = repeats;
    }

}
