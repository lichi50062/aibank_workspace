package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;
import java.util.Calendar;

//@formatter:off
/**
* @(#)CEW314RB060Repeat.java
* 
* <p>Description:CEW314RB060Repeat API下行txRepeat.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW314RB060Repeat {
    /** 消費日期 X(7) */
    private Calendar b1phdy;
    /** 消費說明 X(38) */
    private String b1txt0;
    /** 入帳日期 X(7) */
    private Calendar b1stdy;
    /** 台幣金額 X(9) */
    private BigDecimal b1lcam;
    /** 正負號 X(1) */
    private String b1sing;

    /** 台幣金額-顯示 */
    private String amountDisplay;
    /** 入帳日期-顯示 */
    private String accountDayDisplay;
    /**
     * @return the b1phdy
     */
    public Calendar getB1phdy() {
        return b1phdy;
    }

    /**
     * @param b1phdy
     *            the b1phdy to set
     */
    public void setB1phdy(Calendar b1phdy) {
        this.b1phdy = b1phdy;
    }

    /**
     * @return the b1txt0
     */
    public String getB1txt0() {
        return b1txt0;
    }

    /**
     * @param b1txt0
     *            the b1txt0 to set
     */
    public void setB1txt0(String b1txt0) {
        this.b1txt0 = b1txt0;
    }

    /**
     * @return the b1stdy
     */
    public Calendar getB1stdy() {
        return b1stdy;
    }

    /**
     * @param b1stdy
     *            the b1stdy to set
     */
    public void setB1stdy(Calendar b1stdy) {
        this.b1stdy = b1stdy;
    }

    /**
     * @return the b1lcam
     */
    public BigDecimal getB1lcam() {
        return b1lcam;
    }

    /**
     * @param b1lcam
     *            the b1lcam to set
     */
    public void setB1lcam(BigDecimal b1lcam) {
        this.b1lcam = b1lcam;
    }

    /**
     * @return the b1sing
     */
    public String getB1sing() {
        return b1sing;
    }

    /**
     * @param b1sing
     *            the b1sing to set
     */
    public void setB1sing(String b1sing) {
        this.b1sing = b1sing;
    }

    public String getAccountDayDisplay() {
        return accountDayDisplay;
    }

    public void setAccountDayDisplay(String accountDayDisplay) {
        this.accountDayDisplay = accountDayDisplay;
    }

    public String getAmountDisplay() {
        return amountDisplay;
    }

    public void setAmountDisplay(String amountDisplay) {
        this.amountDisplay = amountDisplay;
    }
}
