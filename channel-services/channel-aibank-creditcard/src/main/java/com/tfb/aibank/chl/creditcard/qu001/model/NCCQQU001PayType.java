package com.tfb.aibank.chl.creditcard.qu001.model;

//@formatter:off
/**
* @(#)NCCQQU001BillingDetail.java
* 
* <p>Description:信用卡總覽 支付方式</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCQQU001PayType {
    /** 虛擬卡號 X(16) */
    private String hchcen;
    /** 行動支付種類 X(1) */
    private String hcttyp;
    /** 排序 */
    private Integer orderNo;

    /**
     * @return the hchcen
     */
    public String getHchcen() {
        return hchcen;
    }
    
    /**
     * @param hchcen
     *            the hchcen to set
     */
    public void setHchcen(String hchcen) {
        this.hchcen = hchcen;
    }
    
    /**
     * @return the hcttyp
     */
    public String getHcttyp() {
        return hcttyp;
    }
    
    /**
     * @param hcttyp
     *            the hcttyp to set
     */
    public void setHcttyp(String hcttyp) {
        this.hcttyp = hcttyp;
    }

    /**
     * @return the orderNo
     */
    public Integer getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo
     *            the orderNo to set
     */
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

}
