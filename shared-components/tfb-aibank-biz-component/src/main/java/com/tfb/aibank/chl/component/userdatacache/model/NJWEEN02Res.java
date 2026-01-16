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
package com.tfb.aibank.chl.component.userdatacache.model;

import java.util.List;

import com.tfb.aibank.common.model.TxHeadRs;

// @formatter:off
/**
 * @(#)NJWEEN01Res.java
 * 
 * <p>Description:NJWEEN01Res</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/09, Charlie Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NJWEEN02Res extends TxHeadRs{

    /** 傳送序號 */
    private String sPRefId;

    /** 帳號 */
    private String acctId16;

    /** 姓名 */
    private String name;

    /** 性別 */
    private String sex;

    /** 資料筆數 */
    private int occur;

    /** 資料 */
    private List<NJWEEN02ResRep> repeate;

    /**
     * @return the sPRefId
     */
    public String getsPRefId() {
        return sPRefId;
    }

    /**
     * @param sPRefId
     *            the sPRefId to set
     */
    public void setsPRefId(String sPRefId) {
        this.sPRefId = sPRefId;
    }

    /**
     * @return the acctId16
     */
    public String getAcctId16() {
        return acctId16;
    }

    /**
     * @param acctId16
     *            the acctId16 to set
     */
    public void setAcctId16(String acctId16) {
        this.acctId16 = acctId16;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     *            the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
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
     * @return the repeate
     */
    public List<NJWEEN02ResRep> getRepeate() {
        return repeate;
    }

    /**
     * @param repeate
     *            the repeate to set
     */
    public void setRepeate(List<NJWEEN02ResRep> repeate) {
        this.repeate = repeate;
    }
}
