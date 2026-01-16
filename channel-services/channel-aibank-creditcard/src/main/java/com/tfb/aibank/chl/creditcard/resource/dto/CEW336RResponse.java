package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.List;

//@formatter:off
/**
* @(#)CEW336RResponse.java
* 
* <p>Description:CEW336R 虛擬卡資訊查詢 API下行欄位.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW336RResponse {

    /** 資料筆數 X(2) */
    private int occur;

    /** txRepeats */
    private List<CEW336RRepeat> txRepeats;

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
     * @return the txRepeats
     */
    public List<CEW336RRepeat> getTxRepeats() {
        return txRepeats;
    }

    /**
     * @param txRepeats
     *            the txRepeats to set
     */
    public void setTxRepeats(List<CEW336RRepeat> txRepeats) {
        this.txRepeats = txRepeats;
    }

}
