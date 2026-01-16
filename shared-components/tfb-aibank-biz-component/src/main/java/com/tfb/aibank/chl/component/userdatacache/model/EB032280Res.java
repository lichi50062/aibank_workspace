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
package com.tfb.aibank.chl.component.userdatacache.model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB032280Res.java
 * 
 * <p>Description:客戶辦理投資有價證券資訊提供聲明書維護 下行資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/13, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "客戶辦理投資有價證券資訊提供聲明書維護 下行資料")
public class EB032280Res implements Serializable {

    private static final long serialVersionUID = 4141886202258078648L;

    /** 客戶姓名 */
    @Schema(description = "客戶姓名")
    private String custName;

    /** 非專業投資人 */
    @Schema(description = "非專業投資人")
    private String custTyp;

    /** 已簽特定金錢信託客戶辦理投資有價證券資訊提供聲明書 */
    @Schema(description = "已簽特定金錢信託客戶辦理投資有價證券資訊提供聲明書")
    private String typCod02;

    /** 簽署/註銷分行 */
    @Schema(description = "簽署/註銷分行")
    private String mtnBrh;

    /** 簽署/註銷日期 */
    @Schema(description = "簽署/註銷日期")
    private String mtnDate;

    /** 近一年信託投資交易筆數大於等於5筆 */
    @Schema(description = "近一年信託投資交易筆數大於等於5筆")
    private String typCod03;

    /** 年齡小於70 */
    @Schema(description = "年齡小於70")
    private String typCod04;

    /** 教育程度國中以上 */
    @Schema(description = "教育程度國中以上")
    private String typCod05;

    /** 未領有全民健保重大傷病證明 */
    @Schema(description = "未領有全民健保重大傷病證明")
    private String typCod06;

    /** 已簽訂ETF商品說明暨風險預告書註記 */
    @Schema(description = "已簽訂etfs商品說明暨風險預告書註記")
    private String etfFlag;

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustTyp() {
        return custTyp;
    }

    public void setCustTyp(String custTyp) {
        this.custTyp = custTyp;
    }

    public String getTypCod02() {
        return typCod02;
    }

    public void setTypCod02(String typCod02) {
        this.typCod02 = typCod02;
    }

    public String getMtnBrh() {
        return mtnBrh;
    }

    public void setMtnBrh(String mtnBrh) {
        this.mtnBrh = mtnBrh;
    }

    public String getMtnDate() {
        return mtnDate;
    }

    public void setMtnDate(String mtnDate) {
        this.mtnDate = mtnDate;
    }

    public String getTypCod03() {
        return typCod03;
    }

    public void setTypCod03(String typCod03) {
        this.typCod03 = typCod03;
    }

    public String getTypCod04() {
        return typCod04;
    }

    public void setTypCod04(String typCod04) {
        this.typCod04 = typCod04;
    }

    public String getTypCod05() {
        return typCod05;
    }

    public void setTypCod05(String typCod05) {
        this.typCod05 = typCod05;
    }

    public String getTypCod06() {
        return typCod06;
    }

    public void setTypCod06(String typCod06) {
        this.typCod06 = typCod06;
    }

    public String getEtfFlag() {
        return etfFlag;
    }

    public void setEtfFlag(String etfFlag) {
        this.etfFlag = etfFlag;
    }

}
