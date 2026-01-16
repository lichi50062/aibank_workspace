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
 * 指定消費分期設定交易下行電文 Body
 * 
 * @author Evan Wang
 */
public class CE4153RResponse {

    /** 傳送序號 */
    private String sprefid;

    /** ID */
    private String id;

    /** ID */
    private List<CE4153RRepeat> repeats;

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
     * @return the repeats
     */
    public List<CE4153RRepeat> getRepeats() {
        return repeats;
    }

    /**
     * @param repeats
     *            the repeats to set
     */
    public void setRepeats(List<CE4153RRepeat> repeats) {
        this.repeats = repeats;
    }

}
