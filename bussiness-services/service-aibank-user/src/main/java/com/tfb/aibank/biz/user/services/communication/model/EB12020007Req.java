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
package com.tfb.aibank.biz.user.services.communication.model;

import com.tfb.aibank.common.model.TxHeadRq;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB12020007Req.java
 * 
 * <p>Description:查詢/變更客戶通訊資料 上送</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/11, Edward	Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "查詢/變更客戶通訊資料 上送")
public class EB12020007Req extends TxHeadRq {

    private static final long serialVersionUID = -7110199859262679659L;

    @Schema(description = "功能")
    private String funcCod;

    @Schema(description = "功能")
    private String func;

    @Schema(description = "統編")
    private String idno;

    @Schema(description = "戶名代碼")
    private String nameCod;

    @Schema(description = "帳號")
    private String acno;

    @Schema(description = "證件類型")
    private String idtype;

    @Schema(description = "通訊地址郵遞區號")
    private String zipCod;

    @Schema(description = "通訊地址1")
    private String addr1;

    @Schema(description = "通訊地址2")
    private String addr2;

    @Schema(description = "電話")
    private String tel;

    @Schema(description = "分機號碼")
    private String ext;

    @Schema(description = "客戶號")
    private String custNumber1;

    @Schema(description = "客戶名稱")
    private String custName1;

    @Schema(description = "OffsetsFiller")
    private String offsetsFiller;

    @Schema(description = "證件類型/號碼")
    private String idNo1;

    @Schema(description = "證件類型/號碼")
    private String idType1;

    @Schema(description = "帳號_01")
    private String acctNumber01;

    @Schema(description = "通訊地址失聯註記_01")
    private String address1UncFlag01;

    @Schema(description = "通訊地址A_01")
    private String address1A01;

    @Schema(description = "通訊地址B_01")
    private String address1B01;

    @Schema(description = "通訊電話失聯註記_01")
    private String phoneRes1UncFlag01;

    @Schema(description = "通訊電話_01")
    private String phoneRes101;

    @Schema(description = "通訊電話分機_01")
    private String phoneResExt101;

    @Schema(description = "公司電話失聯註記_01")
    private String phoneBus1UncFlag01;

    @Schema(description = "公司電話_01")
    private String phoneBus101;

    @Schema(description = "公司電話分機_01")
    private String phoneBusExt101;

    @Schema(description = "戶籍地址失聯註記_01")
    private String addressUncFlag201;

    @Schema(description = "戶籍地址A_01")
    private String address2A01;

    @Schema(description = "戶籍地址B_01")
    private String address2B01;

    @Schema(description = "行動電話失聯註記_01")
    private String mobileNo1UncFlag01;

    @Schema(description = "行動電話_01")
    private String mobileNo101;

    @Schema(description = "傳真電話失聯註記_01")
    private String faxNo1UncFlag01;

    @Schema(description = "傳真電話_01")
    private String faxNo101;

    @Schema(description = "通訊地址郵遞區號_01")
    private String postCode11;

    @Schema(description = "戶籍地址郵遞區號_01")
    private String postCode12;

    @Schema(description = "帳號_02")
    private String acctNumber02;

    @Schema(description = "通訊地址失聯註記_02")
    private String address1UncFlag02;

    @Schema(description = "通訊地址A_02")
    private String address1A02;

    @Schema(description = "通訊地址B_02")
    private String address1B02;

    @Schema(description = "通訊電話失聯註記_02")
    private String phoneRes1UncFlag02;

    @Schema(description = "通訊電話_02")
    private String phoneRes102;

    @Schema(description = "通訊電話分機_02")
    private String phoneResExt102;

    @Schema(description = "公司電話失聯註記_02")
    private String phoneBus1UncFlag02;

    @Schema(description = "公司電話_02")
    private String phoneBus102;

    @Schema(description = "公司電話分機_02")
    private String phoneBusExt102;

    @Schema(description = "戶籍地址失聯註記_02")
    private String addressUncFlag202;

    @Schema(description = "戶籍地址A_02")
    private String address2A02;

    @Schema(description = "戶籍地址B_02")
    private String address2B02;

    @Schema(description = "行動電話失聯註記_02")
    private String mobileNo1UncFlag02;

    @Schema(description = "行動電話_02")
    private String mobileNo102;

    @Schema(description = "傳真電話失聯註記_02")
    private String faxNo1UncFlag02;

    @Schema(description = "傳真電話_02")
    private String faxNo102;

    @Schema(description = "通訊地址郵遞區號_02")
    private String postCode21;

    @Schema(description = "戶籍地址郵遞區號_02")
    private String postCode22;

    @Schema(description = "帳號_03")
    private String acctNumber03;

    @Schema(description = "通訊地址失聯註記_03")
    private String address1UncFlag03;

    @Schema(description = "通訊地址A_03")
    private String address1A03;

    @Schema(description = "通訊地址B_03")
    private String address1B03;

    @Schema(description = "通訊電話失聯註記_03")
    private String phoneRes1UncFlag03;

    @Schema(description = "通訊電話_03")
    private String phoneRes103;

    @Schema(description = "通訊電話分機_03")
    private String phoneResExt103;

    @Schema(description = "公司電話失聯註記_03")
    private String phoneBus1UncFlag03;

    @Schema(description = "公司電話_03")
    private String phoneBus103;

    @Schema(description = "公司電話分機_03")
    private String phoneBusExt103;

    @Schema(description = "戶籍地址失聯註記_03")
    private String addressUncFlag203;

    @Schema(description = "戶籍地址A_03")
    private String address2A03;

    @Schema(description = "戶籍地址B_03")
    private String address2B03;

    @Schema(description = "行動電話失聯註記_03")
    private String mobileNo1UncFlag03;

    @Schema(description = "行動電話_03")
    private String mobileNo103;

    @Schema(description = "傳真電話失聯註記_03")
    private String faxNo1UncFlag03;

    @Schema(description = "傳真電話_03")
    private String faxNo103;

    @Schema(description = "通訊地址郵遞區號_03")
    private String postCode31;

    @Schema(description = "戶籍地址郵遞區號_03")
    private String postCode32;

    @Schema(description = "帳號_04")
    private String acctNumber04;

    @Schema(description = "通訊地址失聯註記_04")
    private String address1UncFlag04;

    @Schema(description = "通訊地址A_04")
    private String address1A04;

    @Schema(description = "通訊地址B_04")
    private String address1B04;

    @Schema(description = "通訊電話失聯註記_04")
    private String phoneRes1UncFlag04;

    @Schema(description = "通訊電話_04")
    private String phoneRes104;

    @Schema(description = "通訊電話分機_04")
    private String phoneResExt104;

    @Schema(description = "公司電話失聯註記_04")
    private String phoneBus1UncFlag04;

    @Schema(description = "公司電話_04")
    private String phoneBus104;

    @Schema(description = "公司電話分機_04")
    private String phoneBusExt104;

    @Schema(description = "戶籍地址失聯註記_04")
    private String addressUncFlag204;

    @Schema(description = "戶籍地址A_04")
    private String address2A04;

    @Schema(description = "戶籍地址B_04")
    private String address2B04;

    @Schema(description = "行動電話失聯註記_04")
    private String mobileNo1UncFlag04;

    @Schema(description = "行動電話_04")
    private String mobileNo104;

    @Schema(description = "傳真電話失聯註記_04")
    private String faxNo1UncFlag04;

    @Schema(description = "傳真電話_04")
    private String faxNo104;

    @Schema(description = "通訊地址郵遞區號_04")
    private String postCode41;

    @Schema(description = "戶籍地址郵遞區號_04")
    private String postCode42;

    @Schema(description = "帳號_05")
    private String acctNumber05;

    @Schema(description = "通訊地址失聯註記_05")
    private String address1UncFlag05;

    @Schema(description = "通訊地址A_05")
    private String address1A05;

    @Schema(description = "通訊地址B_05")
    private String address1B05;

    @Schema(description = "通訊電話失聯註記_05")
    private String phoneRes1UncFlag05;

    @Schema(description = "通訊電話_05")
    private String phoneRes105;

    @Schema(description = "通訊電話分機_05")
    private String phoneResExt105;

    @Schema(description = "公司電話失聯註記_05")
    private String phoneBus1UncFlag05;

    @Schema(description = "公司電話_05")
    private String phoneBus105;

    @Schema(description = "公司電話分機_05")
    private String phoneBusExt105;

    @Schema(description = "戶籍地址失聯註記_05")
    private String addressUncFlag205;

    @Schema(description = "戶籍地址A_05")
    private String address2A05;

    @Schema(description = "戶籍地址B_05")
    private String address2B05;

    @Schema(description = "行動電話失聯註記_05")
    private String mobileNo1UncFlag05;

    @Schema(description = "行動電話_05")
    private String mobileNo105;

    @Schema(description = "傳真電話失聯註記_05")
    private String faxNo1UncFlag05;

    @Schema(description = "傳真電話_05")
    private String faxNo105;

    @Schema(description = "通訊地址郵遞區號_05")
    private String postCode51;

    @Schema(description = "戶籍地址郵遞區號_05")
    private String postCode52;

    @Schema(description = "帳號_06")
    private String acctNumber06;

    @Schema(description = "通訊地址失聯註記_06")
    private String address1UncFlag06;

    @Schema(description = "通訊地址A_06")
    private String address1A06;

    @Schema(description = "通訊地址B_06")
    private String address1B06;

    @Schema(description = "通訊電話失聯註記_06")
    private String phoneRes1UncFlag06;

    @Schema(description = "通訊電話_06")
    private String phoneRes106;

    @Schema(description = "通訊電話分機_06")
    private String phoneResExt106;

    @Schema(description = "公司電話失聯註記_06")
    private String phoneBus1UncFlag06;

    @Schema(description = "公司電話_06")
    private String phoneBus106;

    @Schema(description = "公司電話分機_06")
    private String phoneBusExt106;

    @Schema(description = "戶籍地址失聯註記_06")
    private String addressUncFlag206;

    @Schema(description = "戶籍地址A_06")
    private String address2A06;

    @Schema(description = "戶籍地址B_06")
    private String address2B06;

    @Schema(description = "行動電話失聯註記_06")
    private String mobileNo1UncFlag06;

    @Schema(description = "行動電話_06")
    private String mobileNo106;

    @Schema(description = "傳真電話失聯註記_06")
    private String faxNo1UncFlag06;

    @Schema(description = "傳真電話_06")
    private String faxNo106;

    @Schema(description = "通訊地址郵遞區號_06")
    private String postCode61;

    @Schema(description = "戶籍地址郵遞區號_06")
    private String postCode62;

    @Schema(description = "帳號_07")
    private String acctNumber07;

    @Schema(description = "通訊地址失聯註記_07")
    private String address1UncFlag07;

    @Schema(description = "通訊地址A_07")
    private String address1A07;

    @Schema(description = "通訊地址B_07")
    private String address1B07;

    @Schema(description = "通訊電話失聯註記_07")
    private String phoneRes1UncFlag07;

    @Schema(description = "通訊電話_07")
    private String phoneRes107;

    @Schema(description = "通訊電話分機_07")
    private String phoneResExt107;

    @Schema(description = "公司電話失聯註記_07")
    private String phoneBus1UncFlag07;

    @Schema(description = "公司電話_07")
    private String phoneBus107;

    @Schema(description = "公司電話分機_07")
    private String phoneBusExt107;

    @Schema(description = "戶籍地址失聯註記_07")
    private String addressUncFlag207;

    @Schema(description = "戶籍地址A_07")
    private String address2A07;

    @Schema(description = "戶籍地址B_07")
    private String address2B07;

    @Schema(description = "行動電話失聯註記_07")
    private String mobileNo1UncFlag07;

    @Schema(description = "行動電話_07")
    private String mobileNo107;

    @Schema(description = "傳真電話失聯註記_07")
    private String faxNo1UncFlag07;

    @Schema(description = "傳真電話_07")
    private String faxNo107;

    @Schema(description = "通訊地址郵遞區號_07")
    private String postCode71;

    @Schema(description = "戶籍地址郵遞區號_07")
    private String postCode72;

    @Schema(description = "帳號_08")
    private String acctNumber08;

    @Schema(description = "通訊地址失聯註記_08")
    private String address1UncFlag08;

    @Schema(description = "通訊地址A_08")
    private String address1A08;

    @Schema(description = "通訊地址B_08")
    private String address1B08;

    @Schema(description = "通訊電話失聯註記_08")
    private String phoneRes1UncFlag08;

    @Schema(description = "通訊電話_08")
    private String phoneRes108;

    @Schema(description = "通訊電話分機_08")
    private String phoneResExt108;

    @Schema(description = "公司電話失聯註記_08")
    private String phoneBus1UncFlag08;

    @Schema(description = "公司電話_08")
    private String phoneBus108;

    @Schema(description = "公司電話分機_08")
    private String phoneBusExt108;

    @Schema(description = "戶籍地址失聯註記_08")
    private String addressUncFlag208;

    @Schema(description = "戶籍地址A_08")
    private String address2A08;

    @Schema(description = "戶籍地址B_08")
    private String address2B08;

    @Schema(description = "行動電話失聯註記_08")
    private String mobileNo1UncFlag08;

    @Schema(description = "行動電話_08")
    private String mobileNo108;

    @Schema(description = "傳真電話失聯註記_08")
    private String faxNo1UncFlag08;

    @Schema(description = "傳真電話_08")
    private String faxNo108;

    @Schema(description = "通訊地址郵遞區號_08")
    private String postCode81;

    @Schema(description = "戶籍地址郵遞區號_08")
    private String postCode82;

    @Schema(description = "帳號_09")
    private String acctNumber09;

    @Schema(description = "通訊地址失聯註記_09")
    private String address1UncFlag09;

    @Schema(description = "通訊地址A_09")
    private String address1A09;

    @Schema(description = "通訊地址B_09")
    private String address1B09;

    @Schema(description = "通訊電話失聯註記_09")
    private String phoneRes1UncFlag09;

    @Schema(description = "通訊電話_09")
    private String phoneRes109;

    @Schema(description = "通訊電話分機_09")
    private String phoneResExt109;

    @Schema(description = "公司電話失聯註記_09")
    private String phoneBus1UncFlag09;

    @Schema(description = "公司電話_09")
    private String phoneBus109;

    @Schema(description = "公司電話分機_09")
    private String phoneBusExt109;

    @Schema(description = "戶籍地址失聯註記_09")
    private String addressUncFlag209;

    @Schema(description = "戶籍地址A_09")
    private String address2A09;

    @Schema(description = "戶籍地址B_09")
    private String address2B09;

    @Schema(description = "行動電話失聯註記_09")
    private String mobileNo1UncFlag09;

    @Schema(description = "行動電話_09")
    private String mobileNo109;

    @Schema(description = "傳真電話失聯註記_09")
    private String faxNo1UncFlag09;

    @Schema(description = "傳真電話_09")
    private String faxNo109;

    @Schema(description = "通訊地址郵遞區號_09")
    private String postCode91;

    @Schema(description = "戶籍地址郵遞區號_09")
    private String postCode92;

    @Schema(description = "帳號_10")
    private String acctNumber10;

    @Schema(description = "通訊地址失聯註記_10")
    private String address1UncFlag10;

    @Schema(description = "通訊地址A_10")
    private String address1A10;

    @Schema(description = "通訊地址B_10")
    private String address1B10;

    @Schema(description = "通訊電話失聯註記_10")
    private String phoneRes1UncFlag10;

    @Schema(description = "通訊電話_10")
    private String phoneRes110;

    @Schema(description = "通訊電話分機_10")
    private String phoneResExt110;

    @Schema(description = "公司電話失聯註記_10")
    private String phoneBus1UncFlag10;

    @Schema(description = "公司電話_10")
    private String phoneBus110;

    @Schema(description = "公司電話分機_10")
    private String phoneBusExt110;

    @Schema(description = "戶籍地址失聯註記_10")
    private String addressUncFlag210;

    @Schema(description = "戶籍地址A_10")
    private String address2A10;

    @Schema(description = "戶籍地址B_10")
    private String address2B10;

    @Schema(description = "行動電話失聯註記_10")
    private String mobileNo1UncFlag10;

    @Schema(description = "行動電話_10")
    private String mobileNo110;

    @Schema(description = "傳真電話失聯註記_10")
    private String faxNo1UncFlag10;

    @Schema(description = "傳真電話_10")
    private String faxNo110;

    @Schema(description = "通訊地址郵遞區號_10")
    private String postCode101;

    @Schema(description = "戶籍地址郵遞區號_10")
    private String postCode102;

    @Schema(description = "lastAcctseq")
    private String lastAcctseq;

    @Schema(description = "子功能")
    private String funcSw;

    public String getFuncCod() {
        return funcCod;
    }

    public void setFuncCod(String funcCod) {
        this.funcCod = funcCod;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getNameCod() {
        return nameCod;
    }

    public void setNameCod(String nameCod) {
        this.nameCod = nameCod;
    }

    public String getAcno() {
        return acno;
    }

    public void setAcno(String acno) {
        this.acno = acno;
    }

    public String getIdtype() {
        return idtype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    public String getZipCod() {
        return zipCod;
    }

    public void setZipCod(String zipCod) {
        this.zipCod = zipCod;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getCustNumber1() {
        return custNumber1;
    }

    public void setCustNumber1(String custNumber1) {
        this.custNumber1 = custNumber1;
    }

    public String getCustName1() {
        return custName1;
    }

    public void setCustName1(String custName1) {
        this.custName1 = custName1;
    }

    public String getOffsetsFiller() {
        return offsetsFiller;
    }

    public void setOffsetsFiller(String offsetsFiller) {
        this.offsetsFiller = offsetsFiller;
    }

    public String getIdNo1() {
        return idNo1;
    }

    public void setIdNo1(String idNo1) {
        this.idNo1 = idNo1;
    }

    public String getIdType1() {
        return idType1;
    }

    public void setIdType1(String idType1) {
        this.idType1 = idType1;
    }

    public String getAcctNumber01() {
        return acctNumber01;
    }

    public void setAcctNumber01(String acctNumber01) {
        this.acctNumber01 = acctNumber01;
    }

    public String getAddress1UncFlag01() {
        return address1UncFlag01;
    }

    public void setAddress1UncFlag01(String address1UncFlag01) {
        this.address1UncFlag01 = address1UncFlag01;
    }

    public String getAddress1A01() {
        return address1A01;
    }

    public void setAddress1A01(String address1a01) {
        address1A01 = address1a01;
    }

    public String getAddress1B01() {
        return address1B01;
    }

    public void setAddress1B01(String address1b01) {
        address1B01 = address1b01;
    }

    public String getPhoneRes1UncFlag01() {
        return phoneRes1UncFlag01;
    }

    public void setPhoneRes1UncFlag01(String phoneRes1UncFlag01) {
        this.phoneRes1UncFlag01 = phoneRes1UncFlag01;
    }

    public String getPhoneRes101() {
        return phoneRes101;
    }

    public void setPhoneRes101(String phoneRes101) {
        this.phoneRes101 = phoneRes101;
    }

    public String getPhoneResExt101() {
        return phoneResExt101;
    }

    public void setPhoneResExt101(String phoneResExt101) {
        this.phoneResExt101 = phoneResExt101;
    }

    public String getPhoneBus1UncFlag01() {
        return phoneBus1UncFlag01;
    }

    public void setPhoneBus1UncFlag01(String phoneBus1UncFlag01) {
        this.phoneBus1UncFlag01 = phoneBus1UncFlag01;
    }

    public String getPhoneBus101() {
        return phoneBus101;
    }

    public void setPhoneBus101(String phoneBus101) {
        this.phoneBus101 = phoneBus101;
    }

    public String getPhoneBusExt101() {
        return phoneBusExt101;
    }

    public void setPhoneBusExt101(String phoneBusExt101) {
        this.phoneBusExt101 = phoneBusExt101;
    }

    public String getAddressUncFlag201() {
        return addressUncFlag201;
    }

    public void setAddressUncFlag201(String addressUncFlag201) {
        this.addressUncFlag201 = addressUncFlag201;
    }

    public String getAddress2A01() {
        return address2A01;
    }

    public void setAddress2A01(String address2a01) {
        address2A01 = address2a01;
    }

    public String getAddress2B01() {
        return address2B01;
    }

    public void setAddress2B01(String address2b01) {
        address2B01 = address2b01;
    }

    public String getMobileNo1UncFlag01() {
        return mobileNo1UncFlag01;
    }

    public void setMobileNo1UncFlag01(String mobileNo1UncFlag01) {
        this.mobileNo1UncFlag01 = mobileNo1UncFlag01;
    }

    public String getMobileNo101() {
        return mobileNo101;
    }

    public void setMobileNo101(String mobileNo101) {
        this.mobileNo101 = mobileNo101;
    }

    public String getFaxNo1UncFlag01() {
        return faxNo1UncFlag01;
    }

    public void setFaxNo1UncFlag01(String faxNo1UncFlag01) {
        this.faxNo1UncFlag01 = faxNo1UncFlag01;
    }

    public String getFaxNo101() {
        return faxNo101;
    }

    public void setFaxNo101(String faxNo101) {
        this.faxNo101 = faxNo101;
    }

    public String getPostCode11() {
        return postCode11;
    }

    public void setPostCode11(String postCode11) {
        this.postCode11 = postCode11;
    }

    public String getPostCode12() {
        return postCode12;
    }

    public void setPostCode12(String postCode12) {
        this.postCode12 = postCode12;
    }

    public String getAcctNumber02() {
        return acctNumber02;
    }

    public void setAcctNumber02(String acctNumber02) {
        this.acctNumber02 = acctNumber02;
    }

    public String getAddress1UncFlag02() {
        return address1UncFlag02;
    }

    public void setAddress1UncFlag02(String address1UncFlag02) {
        this.address1UncFlag02 = address1UncFlag02;
    }

    public String getAddress1A02() {
        return address1A02;
    }

    public void setAddress1A02(String address1a02) {
        address1A02 = address1a02;
    }

    public String getAddress1B02() {
        return address1B02;
    }

    public void setAddress1B02(String address1b02) {
        address1B02 = address1b02;
    }

    public String getPhoneRes1UncFlag02() {
        return phoneRes1UncFlag02;
    }

    public void setPhoneRes1UncFlag02(String phoneRes1UncFlag02) {
        this.phoneRes1UncFlag02 = phoneRes1UncFlag02;
    }

    public String getPhoneRes102() {
        return phoneRes102;
    }

    public void setPhoneRes102(String phoneRes102) {
        this.phoneRes102 = phoneRes102;
    }

    public String getPhoneResExt102() {
        return phoneResExt102;
    }

    public void setPhoneResExt102(String phoneResExt102) {
        this.phoneResExt102 = phoneResExt102;
    }

    public String getPhoneBus1UncFlag02() {
        return phoneBus1UncFlag02;
    }

    public void setPhoneBus1UncFlag02(String phoneBus1UncFlag02) {
        this.phoneBus1UncFlag02 = phoneBus1UncFlag02;
    }

    public String getPhoneBus102() {
        return phoneBus102;
    }

    public void setPhoneBus102(String phoneBus102) {
        this.phoneBus102 = phoneBus102;
    }

    public String getPhoneBusExt102() {
        return phoneBusExt102;
    }

    public void setPhoneBusExt102(String phoneBusExt102) {
        this.phoneBusExt102 = phoneBusExt102;
    }

    public String getAddressUncFlag202() {
        return addressUncFlag202;
    }

    public void setAddressUncFlag202(String addressUncFlag202) {
        this.addressUncFlag202 = addressUncFlag202;
    }

    public String getAddress2A02() {
        return address2A02;
    }

    public void setAddress2A02(String address2a02) {
        address2A02 = address2a02;
    }

    public String getAddress2B02() {
        return address2B02;
    }

    public void setAddress2B02(String address2b02) {
        address2B02 = address2b02;
    }

    public String getMobileNo1UncFlag02() {
        return mobileNo1UncFlag02;
    }

    public void setMobileNo1UncFlag02(String mobileNo1UncFlag02) {
        this.mobileNo1UncFlag02 = mobileNo1UncFlag02;
    }

    public String getMobileNo102() {
        return mobileNo102;
    }

    public void setMobileNo102(String mobileNo102) {
        this.mobileNo102 = mobileNo102;
    }

    public String getFaxNo1UncFlag02() {
        return faxNo1UncFlag02;
    }

    public void setFaxNo1UncFlag02(String faxNo1UncFlag02) {
        this.faxNo1UncFlag02 = faxNo1UncFlag02;
    }

    public String getFaxNo102() {
        return faxNo102;
    }

    public void setFaxNo102(String faxNo102) {
        this.faxNo102 = faxNo102;
    }

    public String getPostCode21() {
        return postCode21;
    }

    public void setPostCode21(String postCode21) {
        this.postCode21 = postCode21;
    }

    public String getPostCode22() {
        return postCode22;
    }

    public void setPostCode22(String postCode22) {
        this.postCode22 = postCode22;
    }

    public String getAcctNumber03() {
        return acctNumber03;
    }

    public void setAcctNumber03(String acctNumber03) {
        this.acctNumber03 = acctNumber03;
    }

    public String getAddress1UncFlag03() {
        return address1UncFlag03;
    }

    public void setAddress1UncFlag03(String address1UncFlag03) {
        this.address1UncFlag03 = address1UncFlag03;
    }

    public String getAddress1A03() {
        return address1A03;
    }

    public void setAddress1A03(String address1a03) {
        address1A03 = address1a03;
    }

    public String getAddress1B03() {
        return address1B03;
    }

    public void setAddress1B03(String address1b03) {
        address1B03 = address1b03;
    }

    public String getPhoneRes1UncFlag03() {
        return phoneRes1UncFlag03;
    }

    public void setPhoneRes1UncFlag03(String phoneRes1UncFlag03) {
        this.phoneRes1UncFlag03 = phoneRes1UncFlag03;
    }

    public String getPhoneRes103() {
        return phoneRes103;
    }

    public void setPhoneRes103(String phoneRes103) {
        this.phoneRes103 = phoneRes103;
    }

    public String getPhoneResExt103() {
        return phoneResExt103;
    }

    public void setPhoneResExt103(String phoneResExt103) {
        this.phoneResExt103 = phoneResExt103;
    }

    public String getPhoneBus1UncFlag03() {
        return phoneBus1UncFlag03;
    }

    public void setPhoneBus1UncFlag03(String phoneBus1UncFlag03) {
        this.phoneBus1UncFlag03 = phoneBus1UncFlag03;
    }

    public String getPhoneBus103() {
        return phoneBus103;
    }

    public void setPhoneBus103(String phoneBus103) {
        this.phoneBus103 = phoneBus103;
    }

    public String getPhoneBusExt103() {
        return phoneBusExt103;
    }

    public void setPhoneBusExt103(String phoneBusExt103) {
        this.phoneBusExt103 = phoneBusExt103;
    }

    public String getAddressUncFlag203() {
        return addressUncFlag203;
    }

    public void setAddressUncFlag203(String addressUncFlag203) {
        this.addressUncFlag203 = addressUncFlag203;
    }

    public String getAddress2A03() {
        return address2A03;
    }

    public void setAddress2A03(String address2a03) {
        address2A03 = address2a03;
    }

    public String getAddress2B03() {
        return address2B03;
    }

    public void setAddress2B03(String address2b03) {
        address2B03 = address2b03;
    }

    public String getMobileNo1UncFlag03() {
        return mobileNo1UncFlag03;
    }

    public void setMobileNo1UncFlag03(String mobileNo1UncFlag03) {
        this.mobileNo1UncFlag03 = mobileNo1UncFlag03;
    }

    public String getMobileNo103() {
        return mobileNo103;
    }

    public void setMobileNo103(String mobileNo103) {
        this.mobileNo103 = mobileNo103;
    }

    public String getFaxNo1UncFlag03() {
        return faxNo1UncFlag03;
    }

    public void setFaxNo1UncFlag03(String faxNo1UncFlag03) {
        this.faxNo1UncFlag03 = faxNo1UncFlag03;
    }

    public String getFaxNo103() {
        return faxNo103;
    }

    public void setFaxNo103(String faxNo103) {
        this.faxNo103 = faxNo103;
    }

    public String getPostCode31() {
        return postCode31;
    }

    public void setPostCode31(String postCode31) {
        this.postCode31 = postCode31;
    }

    public String getPostCode32() {
        return postCode32;
    }

    public void setPostCode32(String postCode32) {
        this.postCode32 = postCode32;
    }

    public String getAcctNumber04() {
        return acctNumber04;
    }

    public void setAcctNumber04(String acctNumber04) {
        this.acctNumber04 = acctNumber04;
    }

    public String getAddress1UncFlag04() {
        return address1UncFlag04;
    }

    public void setAddress1UncFlag04(String address1UncFlag04) {
        this.address1UncFlag04 = address1UncFlag04;
    }

    public String getAddress1A04() {
        return address1A04;
    }

    public void setAddress1A04(String address1a04) {
        address1A04 = address1a04;
    }

    public String getAddress1B04() {
        return address1B04;
    }

    public void setAddress1B04(String address1b04) {
        address1B04 = address1b04;
    }

    public String getPhoneRes1UncFlag04() {
        return phoneRes1UncFlag04;
    }

    public void setPhoneRes1UncFlag04(String phoneRes1UncFlag04) {
        this.phoneRes1UncFlag04 = phoneRes1UncFlag04;
    }

    public String getPhoneRes104() {
        return phoneRes104;
    }

    public void setPhoneRes104(String phoneRes104) {
        this.phoneRes104 = phoneRes104;
    }

    public String getPhoneResExt104() {
        return phoneResExt104;
    }

    public void setPhoneResExt104(String phoneResExt104) {
        this.phoneResExt104 = phoneResExt104;
    }

    public String getPhoneBus1UncFlag04() {
        return phoneBus1UncFlag04;
    }

    public void setPhoneBus1UncFlag04(String phoneBus1UncFlag04) {
        this.phoneBus1UncFlag04 = phoneBus1UncFlag04;
    }

    public String getPhoneBus104() {
        return phoneBus104;
    }

    public void setPhoneBus104(String phoneBus104) {
        this.phoneBus104 = phoneBus104;
    }

    public String getPhoneBusExt104() {
        return phoneBusExt104;
    }

    public void setPhoneBusExt104(String phoneBusExt104) {
        this.phoneBusExt104 = phoneBusExt104;
    }

    public String getAddressUncFlag204() {
        return addressUncFlag204;
    }

    public void setAddressUncFlag204(String addressUncFlag204) {
        this.addressUncFlag204 = addressUncFlag204;
    }

    public String getAddress2A04() {
        return address2A04;
    }

    public void setAddress2A04(String address2a04) {
        address2A04 = address2a04;
    }

    public String getAddress2B04() {
        return address2B04;
    }

    public void setAddress2B04(String address2b04) {
        address2B04 = address2b04;
    }

    public String getMobileNo1UncFlag04() {
        return mobileNo1UncFlag04;
    }

    public void setMobileNo1UncFlag04(String mobileNo1UncFlag04) {
        this.mobileNo1UncFlag04 = mobileNo1UncFlag04;
    }

    public String getMobileNo104() {
        return mobileNo104;
    }

    public void setMobileNo104(String mobileNo104) {
        this.mobileNo104 = mobileNo104;
    }

    public String getFaxNo1UncFlag04() {
        return faxNo1UncFlag04;
    }

    public void setFaxNo1UncFlag04(String faxNo1UncFlag04) {
        this.faxNo1UncFlag04 = faxNo1UncFlag04;
    }

    public String getFaxNo104() {
        return faxNo104;
    }

    public void setFaxNo104(String faxNo104) {
        this.faxNo104 = faxNo104;
    }

    public String getPostCode41() {
        return postCode41;
    }

    public void setPostCode41(String postCode41) {
        this.postCode41 = postCode41;
    }

    public String getPostCode42() {
        return postCode42;
    }

    public void setPostCode42(String postCode42) {
        this.postCode42 = postCode42;
    }

    public String getAcctNumber05() {
        return acctNumber05;
    }

    public void setAcctNumber05(String acctNumber05) {
        this.acctNumber05 = acctNumber05;
    }

    public String getAddress1UncFlag05() {
        return address1UncFlag05;
    }

    public void setAddress1UncFlag05(String address1UncFlag05) {
        this.address1UncFlag05 = address1UncFlag05;
    }

    public String getAddress1A05() {
        return address1A05;
    }

    public void setAddress1A05(String address1a05) {
        address1A05 = address1a05;
    }

    public String getAddress1B05() {
        return address1B05;
    }

    public void setAddress1B05(String address1b05) {
        address1B05 = address1b05;
    }

    public String getPhoneRes1UncFlag05() {
        return phoneRes1UncFlag05;
    }

    public void setPhoneRes1UncFlag05(String phoneRes1UncFlag05) {
        this.phoneRes1UncFlag05 = phoneRes1UncFlag05;
    }

    public String getPhoneRes105() {
        return phoneRes105;
    }

    public void setPhoneRes105(String phoneRes105) {
        this.phoneRes105 = phoneRes105;
    }

    public String getPhoneResExt105() {
        return phoneResExt105;
    }

    public void setPhoneResExt105(String phoneResExt105) {
        this.phoneResExt105 = phoneResExt105;
    }

    public String getPhoneBus1UncFlag05() {
        return phoneBus1UncFlag05;
    }

    public void setPhoneBus1UncFlag05(String phoneBus1UncFlag05) {
        this.phoneBus1UncFlag05 = phoneBus1UncFlag05;
    }

    public String getPhoneBus105() {
        return phoneBus105;
    }

    public void setPhoneBus105(String phoneBus105) {
        this.phoneBus105 = phoneBus105;
    }

    public String getPhoneBusExt105() {
        return phoneBusExt105;
    }

    public void setPhoneBusExt105(String phoneBusExt105) {
        this.phoneBusExt105 = phoneBusExt105;
    }

    public String getAddressUncFlag205() {
        return addressUncFlag205;
    }

    public void setAddressUncFlag205(String addressUncFlag205) {
        this.addressUncFlag205 = addressUncFlag205;
    }

    public String getAddress2A05() {
        return address2A05;
    }

    public void setAddress2A05(String address2a05) {
        address2A05 = address2a05;
    }

    public String getAddress2B05() {
        return address2B05;
    }

    public void setAddress2B05(String address2b05) {
        address2B05 = address2b05;
    }

    public String getMobileNo1UncFlag05() {
        return mobileNo1UncFlag05;
    }

    public void setMobileNo1UncFlag05(String mobileNo1UncFlag05) {
        this.mobileNo1UncFlag05 = mobileNo1UncFlag05;
    }

    public String getMobileNo105() {
        return mobileNo105;
    }

    public void setMobileNo105(String mobileNo105) {
        this.mobileNo105 = mobileNo105;
    }

    public String getFaxNo1UncFlag05() {
        return faxNo1UncFlag05;
    }

    public void setFaxNo1UncFlag05(String faxNo1UncFlag05) {
        this.faxNo1UncFlag05 = faxNo1UncFlag05;
    }

    public String getFaxNo105() {
        return faxNo105;
    }

    public void setFaxNo105(String faxNo105) {
        this.faxNo105 = faxNo105;
    }

    public String getPostCode51() {
        return postCode51;
    }

    public void setPostCode51(String postCode51) {
        this.postCode51 = postCode51;
    }

    public String getPostCode52() {
        return postCode52;
    }

    public void setPostCode52(String postCode52) {
        this.postCode52 = postCode52;
    }

    public String getAcctNumber06() {
        return acctNumber06;
    }

    public void setAcctNumber06(String acctNumber06) {
        this.acctNumber06 = acctNumber06;
    }

    public String getAddress1UncFlag06() {
        return address1UncFlag06;
    }

    public void setAddress1UncFlag06(String address1UncFlag06) {
        this.address1UncFlag06 = address1UncFlag06;
    }

    public String getAddress1A06() {
        return address1A06;
    }

    public void setAddress1A06(String address1a06) {
        address1A06 = address1a06;
    }

    public String getAddress1B06() {
        return address1B06;
    }

    public void setAddress1B06(String address1b06) {
        address1B06 = address1b06;
    }

    public String getPhoneRes1UncFlag06() {
        return phoneRes1UncFlag06;
    }

    public void setPhoneRes1UncFlag06(String phoneRes1UncFlag06) {
        this.phoneRes1UncFlag06 = phoneRes1UncFlag06;
    }

    public String getPhoneRes106() {
        return phoneRes106;
    }

    public void setPhoneRes106(String phoneRes106) {
        this.phoneRes106 = phoneRes106;
    }

    public String getPhoneResExt106() {
        return phoneResExt106;
    }

    public void setPhoneResExt106(String phoneResExt106) {
        this.phoneResExt106 = phoneResExt106;
    }

    public String getPhoneBus1UncFlag06() {
        return phoneBus1UncFlag06;
    }

    public void setPhoneBus1UncFlag06(String phoneBus1UncFlag06) {
        this.phoneBus1UncFlag06 = phoneBus1UncFlag06;
    }

    public String getPhoneBus106() {
        return phoneBus106;
    }

    public void setPhoneBus106(String phoneBus106) {
        this.phoneBus106 = phoneBus106;
    }

    public String getPhoneBusExt106() {
        return phoneBusExt106;
    }

    public void setPhoneBusExt106(String phoneBusExt106) {
        this.phoneBusExt106 = phoneBusExt106;
    }

    public String getAddressUncFlag206() {
        return addressUncFlag206;
    }

    public void setAddressUncFlag206(String addressUncFlag206) {
        this.addressUncFlag206 = addressUncFlag206;
    }

    public String getAddress2A06() {
        return address2A06;
    }

    public void setAddress2A06(String address2a06) {
        address2A06 = address2a06;
    }

    public String getAddress2B06() {
        return address2B06;
    }

    public void setAddress2B06(String address2b06) {
        address2B06 = address2b06;
    }

    public String getMobileNo1UncFlag06() {
        return mobileNo1UncFlag06;
    }

    public void setMobileNo1UncFlag06(String mobileNo1UncFlag06) {
        this.mobileNo1UncFlag06 = mobileNo1UncFlag06;
    }

    public String getMobileNo106() {
        return mobileNo106;
    }

    public void setMobileNo106(String mobileNo106) {
        this.mobileNo106 = mobileNo106;
    }

    public String getFaxNo1UncFlag06() {
        return faxNo1UncFlag06;
    }

    public void setFaxNo1UncFlag06(String faxNo1UncFlag06) {
        this.faxNo1UncFlag06 = faxNo1UncFlag06;
    }

    public String getFaxNo106() {
        return faxNo106;
    }

    public void setFaxNo106(String faxNo106) {
        this.faxNo106 = faxNo106;
    }

    public String getPostCode61() {
        return postCode61;
    }

    public void setPostCode61(String postCode61) {
        this.postCode61 = postCode61;
    }

    public String getPostCode62() {
        return postCode62;
    }

    public void setPostCode62(String postCode62) {
        this.postCode62 = postCode62;
    }

    public String getAcctNumber07() {
        return acctNumber07;
    }

    public void setAcctNumber07(String acctNumber07) {
        this.acctNumber07 = acctNumber07;
    }

    public String getAddress1UncFlag07() {
        return address1UncFlag07;
    }

    public void setAddress1UncFlag07(String address1UncFlag07) {
        this.address1UncFlag07 = address1UncFlag07;
    }

    public String getAddress1A07() {
        return address1A07;
    }

    public void setAddress1A07(String address1a07) {
        address1A07 = address1a07;
    }

    public String getAddress1B07() {
        return address1B07;
    }

    public void setAddress1B07(String address1b07) {
        address1B07 = address1b07;
    }

    public String getPhoneRes1UncFlag07() {
        return phoneRes1UncFlag07;
    }

    public void setPhoneRes1UncFlag07(String phoneRes1UncFlag07) {
        this.phoneRes1UncFlag07 = phoneRes1UncFlag07;
    }

    public String getPhoneRes107() {
        return phoneRes107;
    }

    public void setPhoneRes107(String phoneRes107) {
        this.phoneRes107 = phoneRes107;
    }

    public String getPhoneResExt107() {
        return phoneResExt107;
    }

    public void setPhoneResExt107(String phoneResExt107) {
        this.phoneResExt107 = phoneResExt107;
    }

    public String getPhoneBus1UncFlag07() {
        return phoneBus1UncFlag07;
    }

    public void setPhoneBus1UncFlag07(String phoneBus1UncFlag07) {
        this.phoneBus1UncFlag07 = phoneBus1UncFlag07;
    }

    public String getPhoneBus107() {
        return phoneBus107;
    }

    public void setPhoneBus107(String phoneBus107) {
        this.phoneBus107 = phoneBus107;
    }

    public String getPhoneBusExt107() {
        return phoneBusExt107;
    }

    public void setPhoneBusExt107(String phoneBusExt107) {
        this.phoneBusExt107 = phoneBusExt107;
    }

    public String getAddressUncFlag207() {
        return addressUncFlag207;
    }

    public void setAddressUncFlag207(String addressUncFlag207) {
        this.addressUncFlag207 = addressUncFlag207;
    }

    public String getAddress2A07() {
        return address2A07;
    }

    public void setAddress2A07(String address2a07) {
        address2A07 = address2a07;
    }

    public String getAddress2B07() {
        return address2B07;
    }

    public void setAddress2B07(String address2b07) {
        address2B07 = address2b07;
    }

    public String getMobileNo1UncFlag07() {
        return mobileNo1UncFlag07;
    }

    public void setMobileNo1UncFlag07(String mobileNo1UncFlag07) {
        this.mobileNo1UncFlag07 = mobileNo1UncFlag07;
    }

    public String getMobileNo107() {
        return mobileNo107;
    }

    public void setMobileNo107(String mobileNo107) {
        this.mobileNo107 = mobileNo107;
    }

    public String getFaxNo1UncFlag07() {
        return faxNo1UncFlag07;
    }

    public void setFaxNo1UncFlag07(String faxNo1UncFlag07) {
        this.faxNo1UncFlag07 = faxNo1UncFlag07;
    }

    public String getFaxNo107() {
        return faxNo107;
    }

    public void setFaxNo107(String faxNo107) {
        this.faxNo107 = faxNo107;
    }

    public String getPostCode71() {
        return postCode71;
    }

    public void setPostCode71(String postCode71) {
        this.postCode71 = postCode71;
    }

    public String getPostCode72() {
        return postCode72;
    }

    public void setPostCode72(String postCode72) {
        this.postCode72 = postCode72;
    }

    public String getAcctNumber08() {
        return acctNumber08;
    }

    public void setAcctNumber08(String acctNumber08) {
        this.acctNumber08 = acctNumber08;
    }

    public String getAddress1UncFlag08() {
        return address1UncFlag08;
    }

    public void setAddress1UncFlag08(String address1UncFlag08) {
        this.address1UncFlag08 = address1UncFlag08;
    }

    public String getAddress1A08() {
        return address1A08;
    }

    public void setAddress1A08(String address1a08) {
        address1A08 = address1a08;
    }

    public String getAddress1B08() {
        return address1B08;
    }

    public void setAddress1B08(String address1b08) {
        address1B08 = address1b08;
    }

    public String getPhoneRes1UncFlag08() {
        return phoneRes1UncFlag08;
    }

    public void setPhoneRes1UncFlag08(String phoneRes1UncFlag08) {
        this.phoneRes1UncFlag08 = phoneRes1UncFlag08;
    }

    public String getPhoneRes108() {
        return phoneRes108;
    }

    public void setPhoneRes108(String phoneRes108) {
        this.phoneRes108 = phoneRes108;
    }

    public String getPhoneResExt108() {
        return phoneResExt108;
    }

    public void setPhoneResExt108(String phoneResExt108) {
        this.phoneResExt108 = phoneResExt108;
    }

    public String getPhoneBus1UncFlag08() {
        return phoneBus1UncFlag08;
    }

    public void setPhoneBus1UncFlag08(String phoneBus1UncFlag08) {
        this.phoneBus1UncFlag08 = phoneBus1UncFlag08;
    }

    public String getPhoneBus108() {
        return phoneBus108;
    }

    public void setPhoneBus108(String phoneBus108) {
        this.phoneBus108 = phoneBus108;
    }

    public String getPhoneBusExt108() {
        return phoneBusExt108;
    }

    public void setPhoneBusExt108(String phoneBusExt108) {
        this.phoneBusExt108 = phoneBusExt108;
    }

    public String getAddressUncFlag208() {
        return addressUncFlag208;
    }

    public void setAddressUncFlag208(String addressUncFlag208) {
        this.addressUncFlag208 = addressUncFlag208;
    }

    public String getAddress2A08() {
        return address2A08;
    }

    public void setAddress2A08(String address2a08) {
        address2A08 = address2a08;
    }

    public String getAddress2B08() {
        return address2B08;
    }

    public void setAddress2B08(String address2b08) {
        address2B08 = address2b08;
    }

    public String getMobileNo1UncFlag08() {
        return mobileNo1UncFlag08;
    }

    public void setMobileNo1UncFlag08(String mobileNo1UncFlag08) {
        this.mobileNo1UncFlag08 = mobileNo1UncFlag08;
    }

    public String getMobileNo108() {
        return mobileNo108;
    }

    public void setMobileNo108(String mobileNo108) {
        this.mobileNo108 = mobileNo108;
    }

    public String getFaxNo1UncFlag08() {
        return faxNo1UncFlag08;
    }

    public void setFaxNo1UncFlag08(String faxNo1UncFlag08) {
        this.faxNo1UncFlag08 = faxNo1UncFlag08;
    }

    public String getFaxNo108() {
        return faxNo108;
    }

    public void setFaxNo108(String faxNo108) {
        this.faxNo108 = faxNo108;
    }

    public String getPostCode81() {
        return postCode81;
    }

    public void setPostCode81(String postCode81) {
        this.postCode81 = postCode81;
    }

    public String getPostCode82() {
        return postCode82;
    }

    public void setPostCode82(String postCode82) {
        this.postCode82 = postCode82;
    }

    public String getAcctNumber09() {
        return acctNumber09;
    }

    public void setAcctNumber09(String acctNumber09) {
        this.acctNumber09 = acctNumber09;
    }

    public String getAddress1UncFlag09() {
        return address1UncFlag09;
    }

    public void setAddress1UncFlag09(String address1UncFlag09) {
        this.address1UncFlag09 = address1UncFlag09;
    }

    public String getAddress1A09() {
        return address1A09;
    }

    public void setAddress1A09(String address1a09) {
        address1A09 = address1a09;
    }

    public String getAddress1B09() {
        return address1B09;
    }

    public void setAddress1B09(String address1b09) {
        address1B09 = address1b09;
    }

    public String getPhoneRes1UncFlag09() {
        return phoneRes1UncFlag09;
    }

    public void setPhoneRes1UncFlag09(String phoneRes1UncFlag09) {
        this.phoneRes1UncFlag09 = phoneRes1UncFlag09;
    }

    public String getPhoneRes109() {
        return phoneRes109;
    }

    public void setPhoneRes109(String phoneRes109) {
        this.phoneRes109 = phoneRes109;
    }

    public String getPhoneResExt109() {
        return phoneResExt109;
    }

    public void setPhoneResExt109(String phoneResExt109) {
        this.phoneResExt109 = phoneResExt109;
    }

    public String getPhoneBus1UncFlag09() {
        return phoneBus1UncFlag09;
    }

    public void setPhoneBus1UncFlag09(String phoneBus1UncFlag09) {
        this.phoneBus1UncFlag09 = phoneBus1UncFlag09;
    }

    public String getPhoneBus109() {
        return phoneBus109;
    }

    public void setPhoneBus109(String phoneBus109) {
        this.phoneBus109 = phoneBus109;
    }

    public String getPhoneBusExt109() {
        return phoneBusExt109;
    }

    public void setPhoneBusExt109(String phoneBusExt109) {
        this.phoneBusExt109 = phoneBusExt109;
    }

    public String getAddressUncFlag209() {
        return addressUncFlag209;
    }

    public void setAddressUncFlag209(String addressUncFlag209) {
        this.addressUncFlag209 = addressUncFlag209;
    }

    public String getAddress2A09() {
        return address2A09;
    }

    public void setAddress2A09(String address2a09) {
        address2A09 = address2a09;
    }

    public String getAddress2B09() {
        return address2B09;
    }

    public void setAddress2B09(String address2b09) {
        address2B09 = address2b09;
    }

    public String getMobileNo1UncFlag09() {
        return mobileNo1UncFlag09;
    }

    public void setMobileNo1UncFlag09(String mobileNo1UncFlag09) {
        this.mobileNo1UncFlag09 = mobileNo1UncFlag09;
    }

    public String getMobileNo109() {
        return mobileNo109;
    }

    public void setMobileNo109(String mobileNo109) {
        this.mobileNo109 = mobileNo109;
    }

    public String getFaxNo1UncFlag09() {
        return faxNo1UncFlag09;
    }

    public void setFaxNo1UncFlag09(String faxNo1UncFlag09) {
        this.faxNo1UncFlag09 = faxNo1UncFlag09;
    }

    public String getFaxNo109() {
        return faxNo109;
    }

    public void setFaxNo109(String faxNo109) {
        this.faxNo109 = faxNo109;
    }

    public String getPostCode91() {
        return postCode91;
    }

    public void setPostCode91(String postCode91) {
        this.postCode91 = postCode91;
    }

    public String getPostCode92() {
        return postCode92;
    }

    public void setPostCode92(String postCode92) {
        this.postCode92 = postCode92;
    }

    public String getAcctNumber10() {
        return acctNumber10;
    }

    public void setAcctNumber10(String acctNumber10) {
        this.acctNumber10 = acctNumber10;
    }

    public String getAddress1UncFlag10() {
        return address1UncFlag10;
    }

    public void setAddress1UncFlag10(String address1UncFlag10) {
        this.address1UncFlag10 = address1UncFlag10;
    }

    public String getAddress1A10() {
        return address1A10;
    }

    public void setAddress1A10(String address1a10) {
        address1A10 = address1a10;
    }

    public String getAddress1B10() {
        return address1B10;
    }

    public void setAddress1B10(String address1b10) {
        address1B10 = address1b10;
    }

    public String getPhoneRes1UncFlag10() {
        return phoneRes1UncFlag10;
    }

    public void setPhoneRes1UncFlag10(String phoneRes1UncFlag10) {
        this.phoneRes1UncFlag10 = phoneRes1UncFlag10;
    }

    public String getPhoneRes110() {
        return phoneRes110;
    }

    public void setPhoneRes110(String phoneRes110) {
        this.phoneRes110 = phoneRes110;
    }

    public String getPhoneResExt110() {
        return phoneResExt110;
    }

    public void setPhoneResExt110(String phoneResExt110) {
        this.phoneResExt110 = phoneResExt110;
    }

    public String getPhoneBus1UncFlag10() {
        return phoneBus1UncFlag10;
    }

    public void setPhoneBus1UncFlag10(String phoneBus1UncFlag10) {
        this.phoneBus1UncFlag10 = phoneBus1UncFlag10;
    }

    public String getPhoneBus110() {
        return phoneBus110;
    }

    public void setPhoneBus110(String phoneBus110) {
        this.phoneBus110 = phoneBus110;
    }

    public String getPhoneBusExt110() {
        return phoneBusExt110;
    }

    public void setPhoneBusExt110(String phoneBusExt110) {
        this.phoneBusExt110 = phoneBusExt110;
    }

    public String getAddressUncFlag210() {
        return addressUncFlag210;
    }

    public void setAddressUncFlag210(String addressUncFlag210) {
        this.addressUncFlag210 = addressUncFlag210;
    }

    public String getAddress2A10() {
        return address2A10;
    }

    public void setAddress2A10(String address2a10) {
        address2A10 = address2a10;
    }

    public String getAddress2B10() {
        return address2B10;
    }

    public void setAddress2B10(String address2b10) {
        address2B10 = address2b10;
    }

    public String getMobileNo1UncFlag10() {
        return mobileNo1UncFlag10;
    }

    public void setMobileNo1UncFlag10(String mobileNo1UncFlag10) {
        this.mobileNo1UncFlag10 = mobileNo1UncFlag10;
    }

    public String getMobileNo110() {
        return mobileNo110;
    }

    public void setMobileNo110(String mobileNo110) {
        this.mobileNo110 = mobileNo110;
    }

    public String getFaxNo1UncFlag10() {
        return faxNo1UncFlag10;
    }

    public void setFaxNo1UncFlag10(String faxNo1UncFlag10) {
        this.faxNo1UncFlag10 = faxNo1UncFlag10;
    }

    public String getFaxNo110() {
        return faxNo110;
    }

    public void setFaxNo110(String faxNo110) {
        this.faxNo110 = faxNo110;
    }

    public String getPostCode101() {
        return postCode101;
    }

    public void setPostCode101(String postCode101) {
        this.postCode101 = postCode101;
    }

    public String getPostCode102() {
        return postCode102;
    }

    public void setPostCode102(String postCode102) {
        this.postCode102 = postCode102;
    }

    public String getLastAcctseq() {
        return lastAcctseq;
    }

    public void setLastAcctseq(String lastAcctseq) {
        this.lastAcctseq = lastAcctseq;
    }

    public String getFuncSw() {
        return funcSw;
    }

    public void setFuncSw(String funcSw) {
        this.funcSw = funcSw;
    }

}
