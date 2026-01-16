package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.List;

//@formatter:off
/**
* @(#)CEW345RResponse.java
* 
* <p>Description:CEW345R 保費權益設定查詢 API下行欄位.</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/02/10, leiping	
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW345RResponse {

    /** 傳送序號 X(5) */
    private String spRefId;
    /** 資料筆數 X(2) */
    private String occur;
    /** ACID X(11) */
    private String acid;

    /** txRepeats */
    private List<CEW345RRepeat> txRepeats;

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
     * @return the acid
     */
    public String getAcid() {
        return acid;
    }

    /**
     * @param acid
     *            the acid to set
     */
    public void setAcid(String acid) {
        this.acid = acid;
    }

    /**
     * @return the txRepeats
     */
    public List<CEW345RRepeat> getTxRepeats() {
        return txRepeats;
    }

    /**
     * @param txRepeats
     *            the txRepeats to set
     */
    public void setTxRepeats(List<CEW345RRepeat> txRepeats) {
        this.txRepeats = txRepeats;
    }

}
