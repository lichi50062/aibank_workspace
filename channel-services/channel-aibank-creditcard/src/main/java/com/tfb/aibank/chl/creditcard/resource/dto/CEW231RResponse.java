package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.List;

//@formatter:off
/**
* @(#)CEW231RResponse.java
* 
* <p>Description:CEW231R 當期帳單已繳明細 API下行欄位.</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/07/10 Rick
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW231RResponse {

    private String spRefId;
    private String occur;

    private List<CEW231RRepeat> txRepeats;

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
    public List<CEW231RRepeat> getTxRepeats() {
        return txRepeats;
    }

    /**
     * @param txRepeats
     *            the txRepeats to set
     */
    public void setTxRepeats(List<CEW231RRepeat> txRepeats) {
        this.txRepeats = txRepeats;
    }

}
