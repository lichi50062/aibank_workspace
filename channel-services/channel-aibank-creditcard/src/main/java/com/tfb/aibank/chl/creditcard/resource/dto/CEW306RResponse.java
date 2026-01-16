package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.List;

//@formatter:off
/**
* @(#)CEW306RResponse.java
* 
* <p>Description:CEW306R 信用卡紅利積點查詢 API下行欄位.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW306RResponse {

    /** 傳送序號 X(5) */
    private String SPRefId;
    /** 資料筆數 X(2) */
    private String OCCUR;
    /** 折抵房貸成本 X(5) */
    private String BONAMT;

    /** txRepeats */
    private List<CEW306RRepeat> txRepeats;

    /**
     * @return the sPRefId
     */
    public String getSPRefId() {
        return SPRefId;
    }

    /**
     * @param sPRefId
     *            the sPRefId to set
     */
    public void setSPRefId(String sPRefId) {
        SPRefId = sPRefId;
    }

    /**
     * @return the oCCUR
     */
    public String getOCCUR() {
        return OCCUR;
    }

    /**
     * @param oCCUR
     *            the oCCUR to set
     */
    public void setOCCUR(String oCCUR) {
        OCCUR = oCCUR;
    }

    /**
     * @return the bONAMT
     */
    public String getBONAMT() {
        return BONAMT;
    }

    /**
     * @param bONAMT
     *            the bONAMT to set
     */
    public void setBONAMT(String bONAMT) {
        BONAMT = bONAMT;
    }

    /**
     * @return the txRepeats
     */
    public List<CEW306RRepeat> getTxRepeats() {
        return txRepeats;
    }

    /**
     * @param txRepeats
     *            the txRepeats to set
     */
    public void setTxRepeats(List<CEW306RRepeat> txRepeats) {
        this.txRepeats = txRepeats;
    }


}
