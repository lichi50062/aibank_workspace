/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.bondrecommend.model;

import java.math.BigDecimal;
import java.util.Date;

//@formatter:off
/**
* @(#)OverseasBondReferPriceModel
* 
* <p>Description: 海外債商品申購報價 Model</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/04/01, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class OverseasBondReferPriceModel {

    /**
     * 債券代號
     */
    private String bondNo;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 申購價格
     */
    private BigDecimal referPrice;

    /**
     * 報價日期
     */
    private Date referPriceDate;

    /**
     * 取得債券代號
     * 
     * @return String 債券代號
     */
    public String getBondNo() {
        return this.bondNo;
    }

    /**
     * 設定債券代號
     * 
     * @param bondNo
     *            要設定的債券代號
     */
    public void setBondNo(String bondNo) {
        this.bondNo = bondNo;
    }

    /**
     * 取得建立時間
     * 
     * @return Date 建立時間
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 設定建立時間
     * 
     * @param createTime
     *            要設定的建立時間
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得申購價格
     * 
     * @return BigDecimal 申購價格
     */
    public BigDecimal getReferPrice() {
        return this.referPrice;
    }

    /**
     * 設定申購價格
     * 
     * @param referPrice
     *            要設定的申購價格
     */
    public void setReferPrice(BigDecimal referPrice) {
        this.referPrice = referPrice;
    }

    /**
     * 取得報價日期
     * 
     * @return Date 報價日期
     */
    public Date getReferPriceDate() {
        return this.referPriceDate;
    }

    /**
     * 設定報價日期
     * 
     * @param referPriceDate
     *            要設定的報價日期
     */
    public void setReferPriceDate(Date referPriceDate) {
        this.referPriceDate = referPriceDate;
    }
}
