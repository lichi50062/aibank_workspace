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
package com.tfb.aibank.component.productsriskproperty;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)ProductsRiskProperty.java
 * 
 * <p>Description:海外ETF/海外 商品風險屬性</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/19, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "海外ETF/海外 商品風險屬性")
public class ProductsRiskProperty {
    /**
     * 建立時間
     */
    @Schema(description = "建立時間")
    private Date createTime;

    /**
     * 風險承受度=C1客戶可否申購
     */
    @Schema(description = "風險承受度=C1客戶可否申購")
    private String kycFlagC1;

    /**
     * 風險承受度=C2客戶可否申購
     */
    @Schema(description = "風險承受度=C2客戶可否申購")
    private String kycFlagC2;

    /**
     * 風險承受度=C3客戶可否申購
     */
    @Schema(description = "風險承受度=C3客戶可否申購")
    private String kycFlagC3;

    /**
     * 風險承受度=C4客戶可否申購
     */
    @Schema(description = "風險承受度=C4客戶可否申購")
    private String kycFlagC4;

    /**
     * 風險承受度=C5客戶可否申購
     */
    @Schema(description = "風險承受度=C5客戶可否申購")
    private String kycFlagC5;

    /**
     * 語系
     */
    @Schema(description = "語系")
    private String locale;

    /**
     * 商品風險屬性代碼
     */
    @Schema(description = "商品風險屬性代碼")
    private String riskCode;

    /**
     * 商品風險屬性說明
     */
    @Schema(description = "商品風險屬性說明")
    private String riskDescription;

    /**
     * 商品風險屬性名稱
     */
    @Schema(description = "商品風險屬性名稱")
    private String riskName;

    /**
     * 更新時間
     */
    @Schema(description = "更新時間")
    private Date updateTime;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private int orderSeq;

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
     * 取得風險承受度=C1客戶可否申購
     * 
     * @return String 風險承受度=C1客戶可否申購
     */
    public String getKycFlagC1() {
        return this.kycFlagC1;
    }

    /**
     * 設定風險承受度=C1客戶可否申購
     * 
     * @param kycFlagC1
     *            要設定的風險承受度=C1客戶可否申購
     */
    public void setKycFlagC1(String kycFlagC1) {
        this.kycFlagC1 = kycFlagC1;
    }

    /**
     * 取得風險承受度=C2客戶可否申購
     * 
     * @return String 風險承受度=C2客戶可否申購
     */
    public String getKycFlagC2() {
        return this.kycFlagC2;
    }

    /**
     * 設定風險承受度=C2客戶可否申購
     * 
     * @param kycFlagC2
     *            要設定的風險承受度=C2客戶可否申購
     */
    public void setKycFlagC2(String kycFlagC2) {
        this.kycFlagC2 = kycFlagC2;
    }

    /**
     * 取得風險承受度=C3客戶可否申購
     * 
     * @return String 風險承受度=C3客戶可否申購
     */
    public String getKycFlagC3() {
        return this.kycFlagC3;
    }

    /**
     * 設定風險承受度=C3客戶可否申購
     * 
     * @param kycFlagC3
     *            要設定的風險承受度=C3客戶可否申購
     */
    public void setKycFlagC3(String kycFlagC3) {
        this.kycFlagC3 = kycFlagC3;
    }

    /**
     * 取得風險承受度=C4客戶可否申購
     * 
     * @return String 風險承受度=C4客戶可否申購
     */
    public String getKycFlagC4() {
        return this.kycFlagC4;
    }

    /**
     * 設定風險承受度=C4客戶可否申購
     * 
     * @param kycFlagC4
     *            要設定的風險承受度=C4客戶可否申購
     */
    public void setKycFlagC4(String kycFlagC4) {
        this.kycFlagC4 = kycFlagC4;
    }

    /**
     * 取得風險承受度=C5客戶可否申購
     * 
     * @return String 風險承受度=C5客戶可否申購
     */
    public String getKycFlagC5() {
        return this.kycFlagC5;
    }

    /**
     * 設定風險承受度=C5客戶可否申購
     * 
     * @param kycFlagC5
     *            要設定的風險承受度=C5客戶可否申購
     */
    public void setKycFlagC5(String kycFlagC5) {
        this.kycFlagC5 = kycFlagC5;
    }

    /**
     * 取得語系
     * 
     * @return String 語系
     */
    public String getLocale() {
        return this.locale;
    }

    /**
     * 設定語系
     * 
     * @param locale
     *            要設定的語系
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * 取得商品風險屬性代碼
     * 
     * @return String 商品風險屬性代碼
     */
    public String getRiskCode() {
        return this.riskCode;
    }

    /**
     * 設定商品風險屬性代碼
     * 
     * @param riskCode
     *            要設定的商品風險屬性代碼
     */
    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }

    /**
     * 取得商品風險屬性說明
     * 
     * @return String 商品風險屬性說明
     */
    public String getRiskDescription() {
        return this.riskDescription;
    }

    /**
     * 設定商品風險屬性說明
     * 
     * @param riskDescription
     *            要設定的商品風險屬性說明
     */
    public void setRiskDescription(String riskDescription) {
        this.riskDescription = riskDescription;
    }

    /**
     * 取得商品風險屬性名稱
     * 
     * @return String 商品風險屬性名稱
     */
    public String getRiskName() {
        return this.riskName;
    }

    /**
     * 設定商品風險屬性名稱
     * 
     * @param riskName
     *            要設定的商品風險屬性名稱
     */
    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    /**
     * 取得更新時間
     * 
     * @return Date 更新時間
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 設定更新時間
     * 
     * @param updateTime
     *            要設定的更新時間
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(int orderSeq) {
        this.orderSeq = orderSeq;
    }
}
