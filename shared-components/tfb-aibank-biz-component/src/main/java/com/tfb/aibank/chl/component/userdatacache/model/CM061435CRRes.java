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
package com.tfb.aibank.chl.component.userdatacache.model;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)CM061435CRRes.java
 * 
 * <p>Description:高資產客戶資訊查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/14,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "高資產客戶資訊查詢，電文(CM061435CR)下行")
public class CM061435CRRes implements Serializable {

    private static final long serialVersionUID = 1519744721971415847L;

    /** 高資產客戶註記 HNWC_FLG */
    @Schema(description = "高資產客戶註記")
    private String hnwcFlg;

    /** 高資產客戶資格到期日 HNWC_DUE_DATE */
    @Schema(description = "高資產客戶資格到期日")
    private String hnwcDueDate;

    /** 高資產客戶資格註銷日期 HNWC_INVALID_DATE */
    @Schema(description = "高資產客戶資格註銷日期")
    private String hnwcInvalidDate;

    /** 高資產客戶身份註記 HNWC_ID_TYP */
    @Schema(description = "高資產客戶身份註記")
    private String hnwcIdTyp;

    /** 可提供高資產商品或服務 HNWC_SERV */
    @Schema(description = "可提供高資產商品或服務")
    private String hnwcServ;

    /** 客戶申購戶況客戶不保本結構型經驗筆數MAX : 4 (包含空白輸出)擴成 4 位，前台判斷有幾個A */
    @Schema(name = "客戶申購戶況客戶不保本結構型經驗筆數MAX : 4 (包含空白輸出)擴成 4 位，前台判斷有幾個A")
    private String investFlg;

    /** 是否具備結構複雜商品之投資經驗結構複雜經驗筆數(保本)MAX : 4 (包含空白輸出)擴成 4 位，前台判斷有幾個B */
    @Schema(name = "是否具備結構複雜商品之投資經驗結構複雜經驗筆數(保本)MAX : 4 (包含空白輸出)擴成 4 位，前台判斷有幾個B")
    private String investExp;

    /** 申請日距今是否滿兩週特定客戶第一次申請專投之申請日距今是否滿兩週 */
    @Schema(name = "申請日距今是否滿兩週特定客戶第一次申請專投之申請日距今是否滿兩週")
    private String investDue;

    /**
     * 高資產客戶資訊查詢商品
     */
    private List<CM061435CRResRep> prodInfos;

    /** 授權辦理交易人員(032287)是否具備專業資格(Y/N) */
    @Schema(name = "授權辦理交易人員(032287)是否具備專業資格(Y/N)")
    private String f2287Flg;

    /** 是否具備專業知識註記(空白/ 1~5) (限專投申購之結構型商品註記) */
    @Schema(name = "是否具備專業知識註記(空白/ 1~5) (限專投申購之結構型商品註記)")
    private String professionalFlg;

    /** 是否為高資產特定客戶註記 */
    @Schema(description = "是否為高資產特定客戶註記")
    private String spFlg;

    public String getHnwcFlg() {
        return hnwcFlg;
    }

    public void setHnwcFlg(String hnwcFlg) {
        this.hnwcFlg = hnwcFlg;
    }

    public String getHnwcDueDate() {
        return hnwcDueDate;
    }

    public void setHnwcDueDate(String hnwcDueDate) {
        this.hnwcDueDate = hnwcDueDate;
    }

    public String getHnwcInvalidDate() {
        return hnwcInvalidDate;
    }

    public void setHnwcInvalidDate(String hnwcInvalidDate) {
        this.hnwcInvalidDate = hnwcInvalidDate;
    }

    public String getHnwcIdTyp() {
        return hnwcIdTyp;
    }

    public void setHnwcIdTyp(String hnwcIdTyp) {
        this.hnwcIdTyp = hnwcIdTyp;
    }

    public String getHnwcServ() {
        return hnwcServ;
    }

    public void setHnwcServ(String hnwcServ) {
        this.hnwcServ = hnwcServ;
    }

    public String getSpFlg() {
        return spFlg;
    }

    public void setSpFlg(String spFlg) {
        this.spFlg = spFlg;
    }

    public String getInvestFlg() {
        return investFlg;
    }

    public void setInvestFlg(String investFlg) {
        this.investFlg = investFlg;
    }

    public String getInvestExp() {
        return investExp;
    }

    public void setInvestExp(String investExp) {
        this.investExp = investExp;
    }

    public String getInvestDue() {
        return investDue;
    }

    public void setInvestDue(String investDue) {
        this.investDue = investDue;
    }

    public List<CM061435CRResRep> getProdInfos() {
        return prodInfos;
    }

    public void setProdInfos(List<CM061435CRResRep> prodInfos) {
        this.prodInfos = prodInfos;
    }

    public String getF2287Flg() {
        return f2287Flg;
    }

    public void setF2287Flg(String f2287Flg) {
        this.f2287Flg = f2287Flg;
    }

    public String getProfessionalFlg() {
        return professionalFlg;
    }

    public void setProfessionalFlg(String professionalFlg) {
        this.professionalFlg = professionalFlg;
    }

}
