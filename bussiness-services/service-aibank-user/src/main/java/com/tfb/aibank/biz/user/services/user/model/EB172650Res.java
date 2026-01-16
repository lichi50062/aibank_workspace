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
package com.tfb.aibank.biz.user.services.user.model;

import java.util.List;

import com.tfb.aibank.common.model.TxHeadRq;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB172650Res.java
 * 
 * <p>Description:EB172650 電文下行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/30, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "EB172650 電文下行")
public class EB172650Res extends TxHeadRq {

    private static final long serialVersionUID = 436446809701124360L;

    @Schema(description = "存款帳號")
    private String acNoFs;
    @Schema(description = "存摺號碼")
    private String fsNo;
    @Schema(description = "戶名代碼")
    private String nameCod;
    @Schema(description = "戶名")
    private String name1;
    @Schema(description = "通訊地址代碼")
    private String addrCod1;
    @Schema(description = "通訊地址(一)")
    private String addr1;
    @Schema(description = "通訊地址(二)")
    private String addr2;
    @Schema(description = "戶籍地址代碼")
    private String addrCod2;
    @Schema(description = "戶籍地址(一)")
    private String addr3;
    @Schema(description = "戶籍地址(二)")
    private String addr4;
    @Schema(description = "電話日代碼")
    private String teldCod;
    @Schema(description = "電話日")
    private String teldNo;
    @Schema(description = "電話日分機")
    private String teldExt;
    @Schema(description = "電話夜代碼")
    private String telnCod;
    @Schema(description = "電話夜")
    private String telnNo;
    @Schema(description = "電話夜分機")
    private String telnExt;
    @Schema(description = "E_MAIL")
    private String eMail;
    @Schema(description = "大哥大")
    private String mobilPhone;
    @Schema(description = "客戶統編")
    private String idno;
    @Schema(description = "負責人統編")
    private String respId;
    @Schema(description = "傳真機代碼")
    private String faxCod;
    @Schema(description = "傳真機")
    private String faxno;
    @Schema(description = "行業別")
    private String iduCod;
    @Schema(description = "國別")
    private String ctryCod;
    @Schema(description = "組織型態")
    private String orgType;
    @Schema(description = "個人分類")
    private String custType;
    @Schema(description = "稅率別")
    private String taxCod;
    @Schema(description = "稅率別名稱")
    private String taxType;
    @Schema(description = "代收分行別")
    private String txBrh;
    @Schema(description = "交易分行別")
    private String txBrh1;
    @Schema(description = "開戶日期")
    private String opnDate;
    @Schema(description = "居留證核發日期")
    private String visaIsuDate;
    @Schema(description = "印鑑卡留存行")
    private String sealBrh;
    @Schema(description = "外資投資身份編碼")
    private String fcIdno;
    @Schema(description = "結清日")
    private String closeDate;
    @Schema(description = "年度代扣健保費")
    private String yearHFee;
    @Schema(description = "居留證有效日期")
    private String visaEnddate;
    @Schema(description = "戶況1")
    private String nameSts1;
    @Schema(description = "戶況2")
    private String nameSts2;
    @Schema(description = "上半年年度所得稅")
    private String yearTaxL;
    @Schema(description = "下半年年度所得稅")
    private String yearTaxC;
    @Schema(description = "年度稅前利息")
    private String yearIntL;
    @Schema(description = "存摺餘額累計數")
    private String fsLtBal;
    @Schema(description = "綜活轉綜定帳號")
    private String fdAcno;
    @Schema(description = "備查簿內容")
    private String warnMsg;
    @Schema(description = "版塊")
    private String block;
    @Schema(description = "推廣行")
    private String tbrBrh;
    @Schema(description = "推廣人員")
    private String tbrId;
    @Schema(description = "存款實積1")
    private String accumPrd1;
    @Schema(description = "存款實積2")
    private String accumPrd2;
    @Schema(description = "存款實積3")
    private String accumPrd3;
    @Schema(description = "存款實積4")
    private String accumPrd4;
    @Schema(description = "存款實積5")
    private String accumPrd5;
    @Schema(description = "存款實積6")
    private String accumPrd6;
    @Schema(description = "存款實積7")
    private String accumPrd7;
    @Schema(description = "資料")
    private List<EB172650ResRep> repeats;

    public String getAcNoFs() {
        return acNoFs;
    }

    public void setAcNoFs(String acNoFs) {
        this.acNoFs = acNoFs;
    }

    public String getFsNo() {
        return fsNo;
    }

    public void setFsNo(String fsNo) {
        this.fsNo = fsNo;
    }

    public String getNameCod() {
        return nameCod;
    }

    public void setNameCod(String nameCod) {
        this.nameCod = nameCod;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getAddrCod1() {
        return addrCod1;
    }

    public void setAddrCod1(String addrCod1) {
        this.addrCod1 = addrCod1;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getAddrCod2() {
        return addrCod2;
    }

    public void setAddrCod2(String addrCod2) {
        this.addrCod2 = addrCod2;
    }

    public String getAddr3() {
        return addr3;
    }

    public void setAddr3(String addr3) {
        this.addr3 = addr3;
    }

    public String getAddr4() {
        return addr4;
    }

    public void setAddr4(String addr4) {
        this.addr4 = addr4;
    }

    public String getTeldCod() {
        return teldCod;
    }

    public void setTeldCod(String teldCod) {
        this.teldCod = teldCod;
    }

    public String getTeldNo() {
        return teldNo;
    }

    public void setTeldNo(String teldNo) {
        this.teldNo = teldNo;
    }

    public String getTeldExt() {
        return teldExt;
    }

    public void setTeldExt(String teldExt) {
        this.teldExt = teldExt;
    }

    public String getTelnCod() {
        return telnCod;
    }

    public void setTelnCod(String telnCod) {
        this.telnCod = telnCod;
    }

    public String getTelnNo() {
        return telnNo;
    }

    public void setTelnNo(String telnNo) {
        this.telnNo = telnNo;
    }

    public String getTelnExt() {
        return telnExt;
    }

    public void setTelnExt(String telnExt) {
        this.telnExt = telnExt;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getMobilPhone() {
        return mobilPhone;
    }

    public void setMobilPhone(String mobilPhone) {
        this.mobilPhone = mobilPhone;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getRespId() {
        return respId;
    }

    public void setRespId(String respId) {
        this.respId = respId;
    }

    public String getFaxCod() {
        return faxCod;
    }

    public void setFaxCod(String faxCod) {
        this.faxCod = faxCod;
    }

    public String getFaxno() {
        return faxno;
    }

    public void setFaxno(String faxno) {
        this.faxno = faxno;
    }

    public String getIduCod() {
        return iduCod;
    }

    public void setIduCod(String iduCod) {
        this.iduCod = iduCod;
    }

    public String getCtryCod() {
        return ctryCod;
    }

    public void setCtryCod(String ctryCod) {
        this.ctryCod = ctryCod;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getTaxCod() {
        return taxCod;
    }

    public void setTaxCod(String taxCod) {
        this.taxCod = taxCod;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getTxBrh() {
        return txBrh;
    }

    public void setTxBrh(String txBrh) {
        this.txBrh = txBrh;
    }

    public String getTxBrh1() {
        return txBrh1;
    }

    public void setTxBrh1(String txBrh1) {
        this.txBrh1 = txBrh1;
    }

    public String getOpnDate() {
        return opnDate;
    }

    public void setOpnDate(String opnDate) {
        this.opnDate = opnDate;
    }

    public String getVisaIsuDate() {
        return visaIsuDate;
    }

    public void setVisaIsuDate(String visaIsuDate) {
        this.visaIsuDate = visaIsuDate;
    }

    public String getSealBrh() {
        return sealBrh;
    }

    public void setSealBrh(String sealBrh) {
        this.sealBrh = sealBrh;
    }

    public String getFcIdno() {
        return fcIdno;
    }

    public void setFcIdno(String fcIdno) {
        this.fcIdno = fcIdno;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public String getYearHFee() {
        return yearHFee;
    }

    public void setYearHFee(String yearHFee) {
        this.yearHFee = yearHFee;
    }

    public String getVisaEnddate() {
        return visaEnddate;
    }

    public void setVisaEnddate(String visaEnddate) {
        this.visaEnddate = visaEnddate;
    }

    public String getNameSts1() {
        return nameSts1;
    }

    public void setNameSts1(String nameSts1) {
        this.nameSts1 = nameSts1;
    }

    public String getNameSts2() {
        return nameSts2;
    }

    public void setNameSts2(String nameSts2) {
        this.nameSts2 = nameSts2;
    }

    public String getYearTaxL() {
        return yearTaxL;
    }

    public void setYearTaxL(String yearTaxL) {
        this.yearTaxL = yearTaxL;
    }

    public String getYearTaxC() {
        return yearTaxC;
    }

    public void setYearTaxC(String yearTaxC) {
        this.yearTaxC = yearTaxC;
    }

    public String getYearIntL() {
        return yearIntL;
    }

    public void setYearIntL(String yearIntL) {
        this.yearIntL = yearIntL;
    }

    public String getFsLtBal() {
        return fsLtBal;
    }

    public void setFsLtBal(String fsLtBal) {
        this.fsLtBal = fsLtBal;
    }

    public String getFdAcno() {
        return fdAcno;
    }

    public void setFdAcno(String fdAcno) {
        this.fdAcno = fdAcno;
    }

    public String getWarnMsg() {
        return warnMsg;
    }

    public void setWarnMsg(String warnMsg) {
        this.warnMsg = warnMsg;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getTbrBrh() {
        return tbrBrh;
    }

    public void setTbrBrh(String tbrBrh) {
        this.tbrBrh = tbrBrh;
    }

    public String getTbrId() {
        return tbrId;
    }

    public void setTbrId(String tbrId) {
        this.tbrId = tbrId;
    }

    public String getAccumPrd1() {
        return accumPrd1;
    }

    public void setAccumPrd1(String accumPrd1) {
        this.accumPrd1 = accumPrd1;
    }

    public String getAccumPrd2() {
        return accumPrd2;
    }

    public void setAccumPrd2(String accumPrd2) {
        this.accumPrd2 = accumPrd2;
    }

    public String getAccumPrd3() {
        return accumPrd3;
    }

    public void setAccumPrd3(String accumPrd3) {
        this.accumPrd3 = accumPrd3;
    }

    public String getAccumPrd4() {
        return accumPrd4;
    }

    public void setAccumPrd4(String accumPrd4) {
        this.accumPrd4 = accumPrd4;
    }

    public String getAccumPrd5() {
        return accumPrd5;
    }

    public void setAccumPrd5(String accumPrd5) {
        this.accumPrd5 = accumPrd5;
    }

    public String getAccumPrd6() {
        return accumPrd6;
    }

    public void setAccumPrd6(String accumPrd6) {
        this.accumPrd6 = accumPrd6;
    }

    public String getAccumPrd7() {
        return accumPrd7;
    }

    public void setAccumPrd7(String accumPrd7) {
        this.accumPrd7 = accumPrd7;
    }

    public List<EB172650ResRep> getRepeats() {
        return repeats;
    }

    public void setRepeats(List<EB172650ResRep> repeats) {
        this.repeats = repeats;
    }

}
