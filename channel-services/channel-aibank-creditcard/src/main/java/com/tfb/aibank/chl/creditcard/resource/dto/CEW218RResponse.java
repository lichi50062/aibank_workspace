package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.Date;
import java.util.List;

//@formatter:off
/**
* @(#)CEW218RResponse.java
* 
* <p>Description:CEW218R 附卡:帳單明細查詢 API下行欄位.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW218RResponse {

    /** 帳務年月 X(5) */
    private Date vnlsnd;
    /** 傳送序號 X(5) */
    private String vnseqn;
    /** 資料筆數 X(2) */
    private String vncnt0;
    /** 台幣總金額 X(10) */
    private String vntwdt;

    /** txRepeats */
    private List<CEW218RRepeat> txRepeats;

    /**
     * @return the vnlsnd
     */
    public Date getVnlsnd() {
        return vnlsnd;
    }

    /**
     * @param vnlsnd
     *            the vnlsnd to set
     */
    public void setVnlsnd(Date vnlsnd) {
        this.vnlsnd = vnlsnd;
    }

    /**
     * @return the vnseqn
     */
    public String getVnseqn() {
        return vnseqn;
    }

    /**
     * @param vnseqn
     *            the vnseqn to set
     */
    public void setVnseqn(String vnseqn) {
        this.vnseqn = vnseqn;
    }

    /**
     * @return the vncnt0
     */
    public String getVncnt0() {
        return vncnt0;
    }

    /**
     * @param vncnt0
     *            the vncnt0 to set
     */
    public void setVncnt0(String vncnt0) {
        this.vncnt0 = vncnt0;
    }

    /**
     * @return the vntwdt
     */
    public String getVntwdt() {
        return vntwdt;
    }

    /**
     * @param vntwdt
     *            the vntwdt to set
     */
    public void setVntwdt(String vntwdt) {
        this.vntwdt = vntwdt;
    }

    /**
     * @return the txRepeats
     */
    public List<CEW218RRepeat> getTxRepeats() {
        return txRepeats;
    }

    /**
     * @param txRepeats the txRepeats to set
     */
    public void setTxRepeats(List<CEW218RRepeat> txRepeats) {
        this.txRepeats = txRepeats;
    }

}
