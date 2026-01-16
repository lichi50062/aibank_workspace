package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;
import java.util.Date;

//@formatter:off
/**
* @(#)CEW321RResponse.java
* 
* <p>Description:CEW321R 年度累積消費查詢 API下行欄位.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW321RResponse {

    /** 卡號 X(16) */
    private String cardno;
    /** 累計起算年月 X(5) */
    private Date strday;
    /** 累計迄期年月 X(5) */
    private Date endday;
    /** 免年費消費金額標準 X(7) */
    private BigDecimal outamt1;
    /** 免年費消費次數標準 X(3) */
    private String outcnt1;
    /** 累積年消費金額 X(9) */
    private String outamt2;
    /** 累積年消費次數 X(7) */
    private String outcnt2;

    /**
     * @return the cardno
     */
    public String getCardno() {
        return cardno;
    }

    /**
     * @param cardno
     *            the cardno to set
     */
    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    /**
     * @return the strday
     */
    public Date getStrday() {
        return strday;
    }

    /**
     * @param strday
     *            the strday to set
     */
    public void setStrday(Date strday) {
        this.strday = strday;
    }

    /**
     * @return the endday
     */
    public Date getEndday() {
        return endday;
    }

    /**
     * @param endday
     *            the endday to set
     */
    public void setEndday(Date endday) {
        this.endday = endday;
    }

    /**
     * @return the outamt1
     */
    public BigDecimal getOutamt1() {
        return outamt1;
    }

    /**
     * @param outamt1
     *            the outamt1 to set
     */
    public void setOutamt1(BigDecimal outamt1) {
        this.outamt1 = outamt1;
    }

    /**
     * @return the outcnt1
     */
    public String getOutcnt1() {
        return outcnt1;
    }

    /**
     * @param outcnt1
     *            the outcnt1 to set
     */
    public void setOutcnt1(String outcnt1) {
        this.outcnt1 = outcnt1;
    }

    /**
     * @return the outamt2
     */
    public String getOutamt2() {
        return outamt2;
    }

    /**
     * @param outamt2
     *            the outamt2 to set
     */
    public void setOutamt2(String outamt2) {
        this.outamt2 = outamt2;
    }

    /**
     * @return the outcnt2
     */
    public String getOutcnt2() {
        return outcnt2;
    }

    /**
     * @param outcnt2
     *            the outcnt2 to set
     */
    public void setOutcnt2(String outcnt2) {
        this.outcnt2 = outcnt2;
    }

}
