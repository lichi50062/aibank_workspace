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

import com.tfb.aibank.common.model.TxHeadRs;

//@formatter:off
/**
* @(#)CEW222RResponse.java
* 
* <p>Description:單筆分期查詢(CEW222R) 下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/07/20, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW222RResponse extends TxHeadRs {

    private static final long serialVersionUID = 6632305472539165184L;

    /** 傳送序號 */
    private String sprefld;

    /** 資料筆數 */
    private String occur;

    /** repeats 資料 */
    private List<CEW222RRepeat> repeats;

    /**
     * @return the sprefld
     */
    public String getSprefld() {
        return sprefld;
    }

    /**
     * @param sprefld
     *            the sprefld to set
     */
    public void setSprefld(String sprefld) {
        this.sprefld = sprefld;
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
    public List<CEW222RRepeat> getRepeats() {
        return repeats;
    }

    /**
     * @param repeats
     *            the repeats to set
     */
    public void setRepeats(List<CEW222RRepeat> repeats) {
        this.repeats = repeats;
    }

}
