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

import java.util.List;

import com.tfb.aibank.common.model.TxHeadRs;

// @formatter:off
/**
 * @(#)NJW020Req.java
 * 
 * <p>Description: NJW020 債券營業時間交易</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/16, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NJW020Res extends TxHeadRs {

    private static final long serialVersionUID = 2022427697385147541L;

    /** 傳送序號 */
    private String spRefId;

    /** 帳號 */
    private String acctId16;

    /** 姓名 */
    private String name;

    /** 性別 */
    private String sex;

    /** 資料筆數 */
    private int occur;

    /** 資料 */
    private List<NJW020ResRep> repeats;

    public String getSpRefId() {
        return spRefId;
    }

    public void setSpRefId(String spRefId) {
        this.spRefId = spRefId;
    }

    public String getAcctId16() {
        return acctId16;
    }

    public void setAcctId16(String acctId16) {
        this.acctId16 = acctId16;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getOccur() {
        return occur;
    }

    public void setOccur(int occur) {
        this.occur = occur;
    }

    public List<NJW020ResRep> getRepeats() {
        return repeats;
    }

    public void setRepeats(List<NJW020ResRep> repeats) {
        this.repeats = repeats;
    }

}
