/**
 * 
 */
package com.tfb.aibank.chl.component.userdatacache.model;

import java.math.BigDecimal;

//@formatter:off
/**
* @(#)FC032155Response.java
* 
* <p>Description:貸款總額</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/11/20, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class LaonAcountAmount {

    /** 約當 */
    private boolean isEstimate;

    /**
     * @return the isEstimate
     */
    public boolean isEstimate() {
        return isEstimate;
    }

    /**
     * @param isEstimate
     *            the isEstimate to set
     */
    public void setEstimate(boolean isEstimate) {
        this.isEstimate = isEstimate;
    }

    /** 總額 */
    private BigDecimal totalAmount;

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

}
