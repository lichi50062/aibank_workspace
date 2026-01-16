package com.tfb.aibank.chl.creditcard.resource.dto;

import java.io.Serializable;

// @formatter:off
/**
 * @(#)CEW317RRequest.java
 * 
 * <p>Description:帳單分期申請電文(CEW317R) 上行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/22, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class CEW317RRequest implements Serializable {

    private static final long serialVersionUID = 8812810868426412630L;

    /** 帳單年月 */
    private String billYyyymm;

    /** 分期期數 */
    private String period;

    /** 通路別 */
    private String chanel;

    /**
     * @return the billYyyymm
     */
    public String getBillYyyymm() {
        return billYyyymm;
    }

    /**
     * @param billYyyymm
     *            the billYyyymm to set
     */
    public void setBillYyyymm(String billYyyymm) {
        this.billYyyymm = billYyyymm;
    }

    /**
     * @return the period
     */
    public String getPeriod() {
        return period;
    }

    /**
     * @param period
     *            the period to set
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * @return the chanel
     */
    public String getChanel() {
        return chanel;
    }

    /**
     * @param chanel
     *            the chanel to set
     */
    public void setChanel(String chanel) {
        this.chanel = chanel;
    }

}
