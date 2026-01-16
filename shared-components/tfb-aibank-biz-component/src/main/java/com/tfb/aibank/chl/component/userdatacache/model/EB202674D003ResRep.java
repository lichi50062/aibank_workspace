package com.tfb.aibank.chl.component.userdatacache.model;

import java.math.BigDecimal;

import com.tfb.aibank.common.model.TxHeadRs;

//@formatter:off
/**
* @(#)EB202674Repeat.java
* 
* <p>Description: 活存帳號即時餘額下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/07, Edward Tien  
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB202674D003ResRep extends TxHeadRs {

    private static final long serialVersionUID = -8161816120593305861L;

    /** 產品1.活存 2 定存 */
    private String product;

    /** 幣別 */
    private String cur;

    /** 存款餘額 */
    private BigDecimal nddActBal;

    /** 可用餘額 */
    private BigDecimal avlBal;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur;
    }

    public BigDecimal getNddActBal() {
        return nddActBal;
    }

    public void setNddActBal(BigDecimal nddActBal) {
        this.nddActBal = nddActBal;
    }

    public BigDecimal getAvlBal() {
        return avlBal;
    }

    public void setAvlBal(BigDecimal avlBal) {
        this.avlBal = avlBal;
    }

}
