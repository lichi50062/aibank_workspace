package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;
import java.util.Date;

//@formatter:off
/**
* @(#)CEW230RRepeat.java
* 
* <p>Description:CEW230R 當期帳單已繳明細 API下行txRepeat.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW230RRepeat {
    /** 交易日期 X(7) */
    private Date rwdPday;
    /** 款項說明 X(40) */
    private String rwdtxt;
    /** 入帳日期 X(7) */
    private Date rwdNcday;
    /** 金額 X(9) */
    private BigDecimal rwdAmt;
    /** 金額正負號 X(1) */
    private String rwdAmtSign;

    /**
     * @return the rwdPday
     */
    public Date getRwdPday() {
        return rwdPday;
    }

    /**
     * @param rwdPday
     *            the rwdPday to set
     */
    public void setRwdPday(Date rwdPday) {
        this.rwdPday = rwdPday;
    }

    /**
     * @return the rwdtxt
     */
    public String getRwdtxt() {
        return rwdtxt;
    }

    /**
     * @param rwdtxt
     *            the rwdtxt to set
     */
    public void setRwdtxt(String rwdtxt) {
        this.rwdtxt = rwdtxt;
    }

    /**
     * @return the rwdNcday
     */
    public Date getRwdNcday() {
        return rwdNcday;
    }

    /**
     * @param rwdNcday
     *            the rwdNcday to set
     */
    public void setRwdNcday(Date rwdNcday) {
        this.rwdNcday = rwdNcday;
    }

    /**
     * @return the rwdAmt
     */
    public BigDecimal getRwdAmt() {
        return rwdAmt;
    }

    /**
     * @param rwdAmt
     *            the rwdAmt to set
     */
    public void setRwdAmt(BigDecimal rwdAmt) {
        this.rwdAmt = rwdAmt;
    }

    /**
     * @return the rwdAmtSign
     */
    public String getRwdAmtSign() {
        return rwdAmtSign;
    }

    /**
     * @param rwdAmtSign
     *            the rwdAmtSign to set
     */
    public void setRwdAmtSign(String rwdAmtSign) {
        this.rwdAmtSign = rwdAmtSign;
    }

}
