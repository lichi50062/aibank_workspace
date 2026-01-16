package com.tfb.aibank.chl.creditcard.qu001.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCQU001011Rs.java
* 
* <p>Description:信用卡總覽 功能首頁 取得本期未出帳消費明細 RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU001011Rs implements RsData {
    /** 目前刷卡消費金額 */
    private BigDecimal totalAmount;
    /** 下次結帳日 */
    private Date nextCheckoutDate;
    /** 未出帳消費明細 */
    private List<NCCQQU001Bill> bills;
    /** 未出帳消費明細發生錯誤 */
    private boolean cew205rError;

    /**
     * @return the totalAmount
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount
     *            the totalAmount to set
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @return the nextCheckoutDate
     */
    public Date getNextCheckoutDate() {
        return nextCheckoutDate;
    }

    /**
     * @param nextCheckoutDate
     *            the nextCheckoutDate to set
     */
    public void setNextCheckoutDate(Date nextCheckoutDate) {
        this.nextCheckoutDate = nextCheckoutDate;
    }

    /**
     * @return the bills
     */
    public List<NCCQQU001Bill> getBills() {
        return bills;
    }

    /**
     * @param bills
     *            the bills to set
     */
    public void setBills(List<NCCQQU001Bill> bills) {
        this.bills = bills;
    }

    /**
     * @return the cew205rError
     */
    public boolean isCew205rError() {
        return cew205rError;
    }

    /**
     * @param cew205rError
     *            the cew205rError to set
     */
    public void setCew205rError(boolean cew205rError) {
        this.cew205rError = cew205rError;
    }

}
