/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.cardpaytype;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)CardType.java
 * 
 * <p>Description:信用卡支付類型</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "信用卡支付類型")
public class CardPaytype implements Serializable {

    private static final long serialVersionUID = 589013345157227049L;

    /** PAY_TYPE*/
    @Schema(description = "支付方式代碼")
    private String payType;

    /** LOCALE */
    @Schema(description = "語系")
    private String locale;

    /** PAY_NAME */
    @Schema(description = "支付方式名稱")
    private String payName;

    /** ORDER_NO */
    @Schema(description = "顯示順序")
    private Integer orderNo;

    /**
     * @return the payType
     */
    public String getPayType() {
        return payType;
    }

    /**
     * @param payType
     *            the payType to set
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @param locale
     *            the locale to set
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * @return the payName
     */
    public String getPayName() {
        return payName;
    }

    /**
     * @param payName
     *            the payName to set
     */
    public void setPayName(String payName) {
        this.payName = payName;
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

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
