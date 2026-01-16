package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.List;

//@formatter:off
/**
* @(#)CEW230RResponse.java
* 
* <p>Description:CEW230R 當期帳單已繳明細 API下行欄位.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW230RResponse {

    /** 傳送序號 X(5) */
    private String spRefId;
    /** 資料筆數 X(2) */
    private String occur;

    /** txRepeats */
    private List<CEW230RRepeat> txRepeats;

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
     * @return the txRepeats
     */
    public List<CEW230RRepeat> getTxRepeats() {
        return txRepeats;
    }

    /**
     * @param txRepeats
     *            the txRepeats to set
     */
    public void setTxRepeats(List<CEW230RRepeat> txRepeats) {
        this.txRepeats = txRepeats;
    }

}
