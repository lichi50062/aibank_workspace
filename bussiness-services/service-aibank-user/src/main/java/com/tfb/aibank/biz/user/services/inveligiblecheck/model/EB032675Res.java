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
package com.tfb.aibank.biz.user.services.inveligiblecheck.model;

import java.util.List;

import com.tfb.aibank.common.model.TxHeadRs;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB032675Res.java
 * 
 * <p>Description:查詢客戶各類註記(禁銷、FATCA、弱勢、專業投資人法人)，電文(EB032675)下行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/14, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "查詢客戶各類註記(禁銷、FATCA、弱勢、專業投資人法人)，電文(EB032675)下行")
public class EB032675Res extends TxHeadRs {

    private static final long serialVersionUID = 6082230342115600980L;

    /** 功能碼 */
    @Schema(description = "功能碼")
    private String function;
    /** 範圍 */
    @Schema(description = "範圍")
    private String range;
    /** 次數 */
    @Schema(description = "次數")
    private String occur;

    /** 資料資料類別 */
    @Schema(description = "資料資料類別")
    private String action;
    /** 筆數 */
    @Schema(description = "筆數")
    private String cnt;
    /** 狀態 */
    @Schema(description = "狀態")
    private String rcCod;
    /** 資料來源 */
    @Schema(description = "資料來源")
    private String srcTyp;
    /** 交易日期 */
    @Schema(description = "交易日期")
    private String mtnDate;
    /** (非)專業投資人 Y/N/X */
    @Schema(description = "(非)專業投資人 Y/N/X")
    private String txFlg;
    /** 專業投資人法人註記 */
    @Schema(description = "專業投資人法人註記")
    private String desc;
    /** 商品拒銷註記 */
    @Schema(description = "商品拒銷註記")
    private String prodTyp;
    /** 死亡戶註記 */
    @Schema(description = "死亡戶註記")
    private String deadTyp;
    /** 商品禁銷註記 */
    @Schema(description = "商品禁銷註記")
    private String rejTyp;
    /** 銀行DM註記 */
    @Schema(description = "銀行DM註記")
    private String dmFlg;
    /** 銀行EDM註記 */
    @Schema(description = "銀行EDM註記")
    private String edmFlg;
    /** 銀行SMS註記 */
    @Schema(description = "銀行SMS註記")
    private String smsFlg;
    /** 銀行TM註記 */
    @Schema(description = "銀行TM註記")
    private String tmFlg;
    /** 基本資料共同行銷註記 */
    @Schema(description = "基本資料共同行銷註記")
    private String infoFlg;
    /** 帳戶資料-產物共同行銷註記 */
    @Schema(description = "帳戶資料-產物共同行銷註記")
    private String acc1Flg;
    /** 帳戶資料-人壽共同行銷註記 */
    @Schema(description = "帳戶資料-人壽共同行銷註記")
    private String acc2Flg;
    /** 帳戶資料-證券共同行銷註記 */
    @Schema(description = "帳戶資料-證券共同行銷註記")
    private String acc3Flg;
    /** 帳戶資料-投信共同行銷註記 */
    @Schema(description = "帳戶資料-投信共同行銷註記")
    private String acc4Flg;
    /** 帳戶資料-直效行銷共同行銷註記 */
    @Schema(description = "帳戶資料-直效行銷共同行銷註記")
    private String acc5Flg;
    /** 帳戶資料-期貨共同行銷註記 */
    @Schema(description = "帳戶資料-期貨共同行銷註記")
    private String acc6Flg;
    /** 弱勢客戶註記 */
    @Schema(description = "弱勢客戶註記")
    private String vulFlag;
    /** 信託交易經驗 */
    @Schema(description = "信託交易經驗")
    private String trustFlag;
    /** 年齡小於70 */
    @Schema(description = "年齡小於70")
    private String ageUn70Flag;
    /** 教育程度國中以上(不含國中) */
    @Schema(description = "教育程度國中以上(不含國中)")
    private String eduOvJrFlag;
    /** 未領有全民健保重大傷病證明 */
    @Schema(description = "未領有全民健保重大傷病證明")
    private String healthFlag;
    /** 帳單註記 */
    @Schema(description = "帳單註記")
    private String billsCheck;
    /** OBU 客戶FLG */
    @Schema(description = "OBU 客戶FLG")
    private String obuFlg;
    /** 重大傷病等級 */
    @Schema(description = "重大傷病等級")
    private String sickType;
    /** 結構型商品客戶交易經驗或行業經驗註記 */
    @Schema(description = "結構型商品客戶交易經驗或行業經驗註記")
    private String bondFlag;
    /** KYC到期日 */
    @Schema(description = "KYC到期日")
    private String endDate;
    /** 保留欄位 */
    @Schema(description = "保留欄位")
    private String filler;
    /** 禁銷註記 */
    @Schema(description = "禁銷註記")
    private String bn01Flg;
    /** 法人授權辦理交易人員記錄(032288)是否已建檔 (Y/N) */
    @Schema(description = "法人授權辦理交易人員記錄(032288)是否已建檔 (Y/N)")
    private String legalFlg;
    /** 授權辦理交易人員(032287)是否具備專業資格(Y/N) */
    @Schema(description = "授權辦理交易人員(032287)是否具備專業資格(Y/N)")
    private String f2287Flg;
    /** 目前FATCA身分 */
    @Schema(description = "目前FATCA身分")
    private String idnF;
    /** 客戶申購戶況 */
    @Schema(description = "客戶申購戶況")
    private String investFlg;
    /** 特定客戶 */
    @Schema(description = "特定客戶")
    private String investTyp;
    /** 是否具備結構複雜商品之投資經驗 */
    @Schema(description = "是否具備結構複雜商品之投資經驗")
    private String investExp;
    /** 申請日距今是否滿兩週 */
    @Schema(description = "申請日距今是否滿兩週")
    private String investDue;

    /** 商品別_1 */
    @Schema(description = "商品別_1")
    private String productType1;
    /** 授權辦理交易人證件號碼_1 */
    @Schema(description = "授權辦理交易人證件號碼_1")
    private String traderId1;
    /** 商品別_2 */
    @Schema(description = "商品別_2")
    private String productType2;
    /** 授權辦理交易人證件號碼_2 */
    @Schema(description = "授權辦理交易人證件號碼_2")
    private String traderId2;
    /** 商品別_3 */
    @Schema(description = "商品別_3")
    private String productType3;
    /** 授權辦理交易人證件號碼_3 */
    @Schema(description = "授權辦理交易人證件號碼_3")
    private String traderId3;
    /** 商品別_4 */
    @Schema(description = "商品別_4")
    private String productType4;
    /** 授權辦理交易人證件號碼_4 */
    @Schema(description = "授權辦理交易人證件號碼_4")
    private String traderId4;
    /** 商品別_5 */
    @Schema(description = "商品別_5")
    private String productType5;
    /** 授權辦理交易人證件號碼_5 */
    @Schema(description = "授權辦理交易人證件號碼_5")
    private String traderId5;
    /** 商品別_6 */
    @Schema(description = "商品別_6")
    private String productType6;
    /** 授權辦理交易人證件號碼_6 */
    @Schema(description = "授權辦理交易人證件號碼_6")
    private String traderId6;
    /** 商品別_7 */
    @Schema(description = "商品別_7")
    private String productType7;
    /** 授權辦理交易人證件號碼_7 */
    @Schema(description = "授權辦理交易人證件號碼_7")
    private String traderId7;
    /** 商品別_8 */
    @Schema(description = "商品別_8")
    private String productType8;
    /** 授權辦理交易人證件號碼_8 */
    @Schema(description = "授權辦理交易人證件號碼_8")
    private String traderId8;
    /** 商品別_9 */
    @Schema(description = "商品別_9")
    private String productType9;
    /** 授權辦理交易人證件號碼_9 */
    @Schema(description = "授權辦理交易人證件號碼_9")
    private String traderId9;
    /** 商品別_10 */
    @Schema(description = "商品別_10")
    private String productType10;
    /** 授權辦理交易人證件號碼_10 */
    @Schema(description = "授權辦理交易人證件號碼_10")
    private String traderId10;
    /** 商品別_11 */
    @Schema(description = "商品別_11")
    private String productType11;
    /** 授權辦理交易人證件號碼_11 */
    @Schema(description = "授權辦理交易人證件號碼_11")
    private String traderId11;
    /** 商品別_12 */
    @Schema(description = "商品別_12")
    private String productType12;
    /** 授權辦理交易人證件號碼_12 */
    @Schema(description = "授權辦理交易人證件號碼_12")
    private String traderId12;
    /** 商品別_13 */
    @Schema(description = "商品別_13")
    private String productType13;
    /** 授權辦理交易人證件號碼_13 */
    @Schema(description = "授權辦理交易人證件號碼_13")
    private String traderId13;
    /** 商品別_14 */
    @Schema(description = "商品別_14")
    private String productType14;
    /** 授權辦理交易人證件號碼_14 */
    @Schema(description = "授權辦理交易人證件號碼_14")
    private String traderId14;
    /** 商品別_15 */
    @Schema(description = "商品別_15")
    private String productType15;
    /** 授權辦理交易人證件號碼_15 */
    @Schema(description = "授權辦理交易人證件號碼_15")
    private String traderId15;
    /** 商品別_16 */
    @Schema(description = "商品別_16")
    private String productType16;
    /** 授權辦理交易人證件號碼_16 */
    @Schema(description = "授權辦理交易人證件號碼_16")
    private String traderId16;
    /** 商品別_17 */
    @Schema(description = "商品別_17")
    private String productType17;
    /** 授權辦理交易人證件號碼_17 */
    @Schema(description = "授權辦理交易人證件號碼_17")
    private String traderId17;
    /** 商品別_18 */
    @Schema(description = "商品別_18")
    private String productType18;
    /** 授權辦理交易人證件號碼_18 */
    @Schema(description = "授權辦理交易人證件號碼_18")
    private String traderId18;
    /** 商品別_19 */
    @Schema(description = "商品別_19")
    private String productType19;
    /** 授權辦理交易人證件號碼_19 */
    @Schema(description = "授權辦理交易人證件號碼_19")
    private String traderId19;
    /** 商品別_20 */
    @Schema(description = "商品別_20")
    private String productType20;
    /** 授權辦理交易人證件號碼_20 */
    @Schema(description = "授權辦理交易人證件號碼_20")
    private String traderId20;

    /** 明細資料 */
    @Schema(description = "明細資料")
    private List<EB032675ResRep> repeats;

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getOccur() {
        return occur;
    }

    public void setOccur(String occur) {
        this.occur = occur;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public String getRcCod() {
        return rcCod;
    }

    public void setRcCod(String rcCod) {
        this.rcCod = rcCod;
    }

    public String getSrcTyp() {
        return srcTyp;
    }

    public void setSrcTyp(String srcTyp) {
        this.srcTyp = srcTyp;
    }

    public String getMtnDate() {
        return mtnDate;
    }

    public void setMtnDate(String mtnDate) {
        this.mtnDate = mtnDate;
    }

    public String getTxFlg() {
        return txFlg;
    }

    public void setTxFlg(String txFlg) {
        this.txFlg = txFlg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getProdTyp() {
        return prodTyp;
    }

    public void setProdTyp(String prodTyp) {
        this.prodTyp = prodTyp;
    }

    public String getDeadTyp() {
        return deadTyp;
    }

    public void setDeadTyp(String deadTyp) {
        this.deadTyp = deadTyp;
    }

    public String getRejTyp() {
        return rejTyp;
    }

    public void setRejTyp(String rejTyp) {
        this.rejTyp = rejTyp;
    }

    public String getDmFlg() {
        return dmFlg;
    }

    public void setDmFlg(String dmFlg) {
        this.dmFlg = dmFlg;
    }

    public String getEdmFlg() {
        return edmFlg;
    }

    public void setEdmFlg(String edmFlg) {
        this.edmFlg = edmFlg;
    }

    public String getSmsFlg() {
        return smsFlg;
    }

    public void setSmsFlg(String smsFlg) {
        this.smsFlg = smsFlg;
    }

    public String getTmFlg() {
        return tmFlg;
    }

    public void setTmFlg(String tmFlg) {
        this.tmFlg = tmFlg;
    }

    public String getInfoFlg() {
        return infoFlg;
    }

    public void setInfoFlg(String infoFlg) {
        this.infoFlg = infoFlg;
    }

    public String getAcc1Flg() {
        return acc1Flg;
    }

    public void setAcc1Flg(String acc1Flg) {
        this.acc1Flg = acc1Flg;
    }

    public String getAcc2Flg() {
        return acc2Flg;
    }

    public void setAcc2Flg(String acc2Flg) {
        this.acc2Flg = acc2Flg;
    }

    public String getAcc3Flg() {
        return acc3Flg;
    }

    public void setAcc3Flg(String acc3Flg) {
        this.acc3Flg = acc3Flg;
    }

    public String getAcc4Flg() {
        return acc4Flg;
    }

    public void setAcc4Flg(String acc4Flg) {
        this.acc4Flg = acc4Flg;
    }

    public String getAcc5Flg() {
        return acc5Flg;
    }

    public void setAcc5Flg(String acc5Flg) {
        this.acc5Flg = acc5Flg;
    }

    public String getAcc6Flg() {
        return acc6Flg;
    }

    public void setAcc6Flg(String acc6Flg) {
        this.acc6Flg = acc6Flg;
    }

    public String getVulFlag() {
        return vulFlag;
    }

    public void setVulFlag(String vulFlag) {
        this.vulFlag = vulFlag;
    }

    public String getTrustFlag() {
        return trustFlag;
    }

    public void setTrustFlag(String trustFlag) {
        this.trustFlag = trustFlag;
    }

    public String getAgeUn70Flag() {
        return ageUn70Flag;
    }

    public void setAgeUn70Flag(String ageUn70Flag) {
        this.ageUn70Flag = ageUn70Flag;
    }

    public String getEduOvJrFlag() {
        return eduOvJrFlag;
    }

    public void setEduOvJrFlag(String eduOvJrFlag) {
        this.eduOvJrFlag = eduOvJrFlag;
    }

    public String getHealthFlag() {
        return healthFlag;
    }

    public void setHealthFlag(String healthFlag) {
        this.healthFlag = healthFlag;
    }

    public String getBillsCheck() {
        return billsCheck;
    }

    public void setBillsCheck(String billsCheck) {
        this.billsCheck = billsCheck;
    }

    public String getObuFlg() {
        return obuFlg;
    }

    public void setObuFlg(String obuFlg) {
        this.obuFlg = obuFlg;
    }

    public String getSickType() {
        return sickType;
    }

    public void setSickType(String sickType) {
        this.sickType = sickType;
    }

    public String getBondFlag() {
        return bondFlag;
    }

    public void setBondFlag(String bondFlag) {
        this.bondFlag = bondFlag;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public String getBn01Flg() {
        return bn01Flg;
    }

    public void setBn01Flg(String bn01Flg) {
        this.bn01Flg = bn01Flg;
    }

    public String getLegalFlg() {
        return legalFlg;
    }

    public void setLegalFlg(String legalFlg) {
        this.legalFlg = legalFlg;
    }

    public String getF2287Flg() {
        return f2287Flg;
    }

    public void setF2287Flg(String f2287Flg) {
        this.f2287Flg = f2287Flg;
    }

    public String getIdnF() {
        return idnF;
    }

    public void setIdnF(String idnF) {
        this.idnF = idnF;
    }

    public String getInvestFlg() {
        return investFlg;
    }

    public void setInvestFlg(String investFlg) {
        this.investFlg = investFlg;
    }

    public String getInvestTyp() {
        return investTyp;
    }

    public void setInvestTyp(String investTyp) {
        this.investTyp = investTyp;
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

    public String getProductType1() {
        return productType1;
    }

    public void setProductType1(String productType1) {
        this.productType1 = productType1;
    }

    public String getTraderId1() {
        return traderId1;
    }

    public void setTraderId1(String traderId1) {
        this.traderId1 = traderId1;
    }

    public String getProductType2() {
        return productType2;
    }

    public void setProductType2(String productType2) {
        this.productType2 = productType2;
    }

    public String getTraderId2() {
        return traderId2;
    }

    public void setTraderId2(String traderId2) {
        this.traderId2 = traderId2;
    }

    public String getProductType3() {
        return productType3;
    }

    public void setProductType3(String productType3) {
        this.productType3 = productType3;
    }

    public String getTraderId3() {
        return traderId3;
    }

    public void setTraderId3(String traderId3) {
        this.traderId3 = traderId3;
    }

    public String getProductType4() {
        return productType4;
    }

    public void setProductType4(String productType4) {
        this.productType4 = productType4;
    }

    public String getTraderId4() {
        return traderId4;
    }

    public void setTraderId4(String traderId4) {
        this.traderId4 = traderId4;
    }

    public String getProductType5() {
        return productType5;
    }

    public void setProductType5(String productType5) {
        this.productType5 = productType5;
    }

    public String getTraderId5() {
        return traderId5;
    }

    public void setTraderId5(String traderId5) {
        this.traderId5 = traderId5;
    }

    public String getProductType6() {
        return productType6;
    }

    public void setProductType6(String productType6) {
        this.productType6 = productType6;
    }

    public String getTraderId6() {
        return traderId6;
    }

    public void setTraderId6(String traderId6) {
        this.traderId6 = traderId6;
    }

    public String getProductType7() {
        return productType7;
    }

    public void setProductType7(String productType7) {
        this.productType7 = productType7;
    }

    public String getTraderId7() {
        return traderId7;
    }

    public void setTraderId7(String traderId7) {
        this.traderId7 = traderId7;
    }

    public String getProductType8() {
        return productType8;
    }

    public void setProductType8(String productType8) {
        this.productType8 = productType8;
    }

    public String getTraderId8() {
        return traderId8;
    }

    public void setTraderId8(String traderId8) {
        this.traderId8 = traderId8;
    }

    public String getProductType9() {
        return productType9;
    }

    public void setProductType9(String productType9) {
        this.productType9 = productType9;
    }

    public String getTraderId9() {
        return traderId9;
    }

    public void setTraderId9(String traderId9) {
        this.traderId9 = traderId9;
    }

    public String getProductType10() {
        return productType10;
    }

    public void setProductType10(String productType10) {
        this.productType10 = productType10;
    }

    public String getTraderId10() {
        return traderId10;
    }

    public void setTraderId10(String traderId10) {
        this.traderId10 = traderId10;
    }

    public String getProductType11() {
        return productType11;
    }

    public void setProductType11(String productType11) {
        this.productType11 = productType11;
    }

    public String getTraderId11() {
        return traderId11;
    }

    public void setTraderId11(String traderId11) {
        this.traderId11 = traderId11;
    }

    public String getProductType12() {
        return productType12;
    }

    public void setProductType12(String productType12) {
        this.productType12 = productType12;
    }

    public String getTraderId12() {
        return traderId12;
    }

    public void setTraderId12(String traderId12) {
        this.traderId12 = traderId12;
    }

    public String getProductType13() {
        return productType13;
    }

    public void setProductType13(String productType13) {
        this.productType13 = productType13;
    }

    public String getTraderId13() {
        return traderId13;
    }

    public void setTraderId13(String traderId13) {
        this.traderId13 = traderId13;
    }

    public String getProductType14() {
        return productType14;
    }

    public void setProductType14(String productType14) {
        this.productType14 = productType14;
    }

    public String getTraderId14() {
        return traderId14;
    }

    public void setTraderId14(String traderId14) {
        this.traderId14 = traderId14;
    }

    public String getProductType15() {
        return productType15;
    }

    public void setProductType15(String productType15) {
        this.productType15 = productType15;
    }

    public String getTraderId15() {
        return traderId15;
    }

    public void setTraderId15(String traderId15) {
        this.traderId15 = traderId15;
    }

    public String getProductType16() {
        return productType16;
    }

    public void setProductType16(String productType16) {
        this.productType16 = productType16;
    }

    public String getTraderId16() {
        return traderId16;
    }

    public void setTraderId16(String traderId16) {
        this.traderId16 = traderId16;
    }

    public String getProductType17() {
        return productType17;
    }

    public void setProductType17(String productType17) {
        this.productType17 = productType17;
    }

    public String getTraderId17() {
        return traderId17;
    }

    public void setTraderId17(String traderId17) {
        this.traderId17 = traderId17;
    }

    public String getProductType18() {
        return productType18;
    }

    public void setProductType18(String productType18) {
        this.productType18 = productType18;
    }

    public String getTraderId18() {
        return traderId18;
    }

    public void setTraderId18(String traderId18) {
        this.traderId18 = traderId18;
    }

    public String getProductType19() {
        return productType19;
    }

    public void setProductType19(String productType19) {
        this.productType19 = productType19;
    }

    public String getTraderId19() {
        return traderId19;
    }

    public void setTraderId19(String traderId19) {
        this.traderId19 = traderId19;
    }

    public String getProductType20() {
        return productType20;
    }

    public void setProductType20(String productType20) {
        this.productType20 = productType20;
    }

    public String getTraderId20() {
        return traderId20;
    }

    public void setTraderId20(String traderId20) {
        this.traderId20 = traderId20;
    }

    public List<EB032675ResRep> getRepeats() {
        return repeats;
    }

    public void setRepeats(List<EB032675ResRep> repeats) {
        this.repeats = repeats;
    }
}
