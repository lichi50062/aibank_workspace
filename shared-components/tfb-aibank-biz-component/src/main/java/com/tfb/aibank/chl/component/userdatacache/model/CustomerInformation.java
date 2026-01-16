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

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)CustomerInformation.java
 * 
 * <p>Description:客戶基本資料維護</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/16, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(name = "客戶基本資料維護")
public class CustomerInformation {

    // 債清註記
    @Schema(name = "債清註記")
    private String advCod;
    // 新舊ＩＤ
    @Schema(name = "新舊ＩＤ")
    private String refIdno;
    // 中文戶名
    @Schema(name = "中文戶名")
    private String custName;
    // 出生創立日
    @Schema(name = "出生創立日")
    private String bday;
    // 組織型態
    @Schema(name = "組織型態")
    private String orgType;
    // 法人戶實際受益人
    @Schema(name = "法人戶實際受益人")
    private String orgCheck;
    // 上市上櫃
    @Schema(name = "上市上櫃")
    private String stockType;
    // 行業別
    @Schema(name = "行業別")
    private String iduCod;
    // 企業規模
    @Schema(name = "企業規模")
    private String entrpType;
    // 性別
    @Schema(name = "性別")
    private String sex;
    // 個人分類
    @Schema(name = "個人分類")
    private String custType;
    // 大陸居留證號碼
    @Schema(name = "大陸居留證號碼")
    private String residIdno;
    // 外國人居留證號碼
    @Schema(name = "外國人居留證號碼")
    private String residIdn01;
    // 外國人居留證核發日期
    @Schema(name = "外國人居留證核發日期")
    private String residDate;
    // 外國人居留證有效日期
    @Schema(name = "外國人居留證有效日期")
    private String residEffDate;
    // 信評單位
    @Schema(name = "信評單位")
    private String riskUnit;
    // 信用評等
    @Schema(name = "信用評等")
    private String riskRank;
    // 評等日期
    @Schema(name = "評等日期")
    private String rankDate;
    // 信用評分
    @Schema(name = "信用評分")
    private String riskRate;
    // 授信行業別
    @Schema(name = "授信行業別")
    private String iduCod2;
    // 內部信評評等日
    @Schema(name = "內部信評評等日")
    private String riskDate01;
    // 內部信評違約機率
    @Schema(name = "內部信評違約機率")
    private String riskRate01;
    // 消金房貸信評評等日
    @Schema(name = "消金房貸信評評等日")
    private String pdDate;
    // 消金房貸信評違約機率
    @Schema(name = "消金房貸信評違約機率")
    private String pdRate;
    // 消金房貸違約評等
    @Schema(name = "消金房貸違約評等")
    private String pdRisk;
    // 國家代號
    @Schema(name = "國家代號")
    private String ctryCod;
    // isoCode2
    @Schema(name = "isoCode2")
    private String isoCode2;
    // 學經歷
    @Schema(name = "學經歷")
    private String resume;
    // 企金戶況
    @Schema(name = "企金戶況")
    private String custSts;
    // 服務單位
    @Schema(name = "服務單位")
    private String company;
    // 職稱
    @Schema(name = "職稱")
    private String title;
    // 戶籍區號
    @Schema(name = "戶籍區號")
    private String busZip;
    // 戶籍地址-1
    @Schema(name = "戶籍地址-1")
    private String busAddr1;
    // 戶籍地址-2
    @Schema(name = "戶籍地址-2")
    private String busAddr2;
    // 通訊區號
    @Schema(name = "通訊區號")
    private String cttZip;
    // 通訊地址-1
    @Schema(name = "通訊地址-1")
    private String cttAddr1;
    // 通訊地址-2
    @Schema(name = "通訊地址-2")
    private String cttAddr2;
    // 扣繳憑單_功能
    @Schema(name = "扣繳憑單_功能")
    private String paySlip;
    // 扣繳憑單代號
    @Schema(name = "扣繳憑單代號")
    private String payCode;
    // 扣繳憑單_區號
    @Schema(name = "扣繳憑單_區號")
    private String payZip;
    // 扣繳憑單_地址-1
    @Schema(name = "扣繳憑單_地址-1")
    private String payAddr1;
    // 扣繳憑單_地址-2
    @Schema(name = "扣繳憑單_地址-2")
    private String payAddr2;
    // 電話代碼1
    @Schema(name = "電話代碼1")
    private String telCod1;
    // 電話號碼1
    @Schema(name = "電話號碼1")
    private String telNo1;
    // 電話號碼-轉接1
    @Schema(name = "電話號碼-轉接1")
    private String telExt1;
    // 電話號碼-類別1
    @Schema(name = "電話號碼-類別1")
    private String telTyp1;
    // 電話號碼-性質_日間1
    @Schema(name = "電話號碼-性質_日間1")
    private String telDay1;
    // 電話號碼-性質_晚間1
    @Schema(name = "電話號碼-性質_晚間1")
    private String telNight1;
    // 電話號碼-性質_語音1
    @Schema(name = "電話號碼-性質_語音1")
    private String telVoice1;
    // 電話號碼-性質_傳真1
    @Schema(name = "電話號碼-性質_傳真1")
    private String telFax1;
    // 電話代碼1
    @Schema(name = "電話代碼1")
    private String telCod2;
    // 電話號碼2
    @Schema(name = "電話號碼2")
    private String telNo2;
    // 電話號碼-轉接2
    @Schema(name = "電話號碼-轉接2")
    private String telExt2;
    // 電話號碼-類別2
    @Schema(name = "電話號碼-類別2")
    private String telTyp2;
    // 電話號碼-性質_日間2
    @Schema(name = "電話號碼-性質_日間2")
    private String telDay2;
    // 電話號碼-性質_晚間2
    @Schema(name = "電話號碼-性質_晚間2")
    private String telNight2;
    // 電話號碼-性質_語音2
    @Schema(name = "電話號碼-性質_語音2")
    private String telVoice2;
    // 電話號碼-性質_傳真2
    @Schema(name = "電話號碼-性質_傳真2")
    private String telFax2;
    // E-MAIL地址
    @Schema(name = "E-MAIL地址")
    private String emailAddr;
    // 不使用EMAIL註記
    @Schema(name = "不使用EMAIL註記")
    private String noEmailFlg;
    // 英文戶名
    @Schema(name = "英文戶名")
    private String engName;
    // 英文區號
    @Schema(name = "英文區號")
    private String engZip;
    // 英文地址_1
    @Schema(name = "英文地址_1")
    private String engAddr1;
    // 英文地址_2
    @Schema(name = "英文地址_2")
    private String engAddr2;
    // 登記資本額仟元
    @Schema(name = "登記資本額仟元")
    private String regCapVal;
    // 實收資本額年
    @Schema(name = "實收資本額年")
    private String capValYy;
    // 實收資本額月
    @Schema(name = "實收資本額月")
    private String capValMm;
    // 實收資本額仟元
    @Schema(name = "實收資本額仟元")
    private String capVal;
    // 員工人數
    @Schema(name = "員工人數")
    private String empCnt;
    // 核准日期
    @Schema(name = "核准日期")
    private String aprovDate;
    // 營業項目
    @Schema(name = "營業項目")
    private String busItem;
    // 聯絡人
    @Schema(name = "聯絡人")
    private String contactPson;
    // 負責人身份證號
    @Schema(name = "負責人身份證號")
    private String respId;
    // 負責人戶名
    @Schema(name = "負責人戶名")
    private String respName;
    // 負責人生日
    @Schema(name = "負責人生日")
    private String respBday;
    // 是否列印扣繳憑單
    @Schema(name = "是否列印扣繳憑單")
    private String prtSlip;
    // 在台母公司統編
    @Schema(name = "在台母公司統編")
    private String frCmpid;
    // 報送單位內部編號
    @Schema(name = "報送單位內部編號")
    private String frIntno;
    // 所在地註冊編號
    @Schema(name = "所在地註冊編號")
    private String frRegno;
    // 綜合電子帳單註記
    @Schema(name = "綜合電子帳單註記")
    private String billsCheck;
    // 申請單位
    @Schema(name = "申請單位")
    private String srcTyp;
    // 客戶特殊註記
    @Schema(name = "客戶特殊註記")
    private String lostFlg;
    // 電子帳單異動日
    @Schema(name = "電子帳單異動日")
    private String billsUpdDate;
    // 電子帳單申請日
    @Schema(name = "電子帳單申請日")
    private String billsStrDate;
    // 電子帳單終止日
    @Schema(name = "電子帳單終止日")
    private String billsEndDate;
    // 最近往來分行
    @Schema(name = "最近往來分行")
    private String lstTxBrh1;
    // 最近往來日期
    @Schema(name = "最近往來日期")
    private String lstTxDate1;
    // 最近往來分行
    @Schema(name = "最近往來分行")
    private String lstTxBrh2;
    // 最近往來日期
    @Schema(name = "最近往來日期")
    private String lstTxDate2;
    // 最近往來分行
    @Schema(name = "最近往來分行")
    private String lstTxBrh3;
    // 最近往來日期
    @Schema(name = "最近往來日期")
    private String lstTxDate3;
    // 最近往來分行
    @Schema(name = "最近往來分行")
    private String lstTxBrh4;
    // 最近往來日期
    @Schema(name = "最近往來日期")
    private String lstTxDate4;
    // 最近往來分行
    @Schema(name = "最近往來分行")
    private String lstTxBrh5;
    // 最近往來日期
    @Schema(name = "最近往來日期")
    private String lstTxDate5;
    // 最近往來分行
    @Schema(name = "最近往來分行")
    private String lstTxBrh6;
    // 最近往來日期
    @Schema(name = "最近往來日期")
    private String lstTxDate6;
    // 婚姻
    @Schema(name = "婚姻")
    private String marrage;
    // 子女人數
    @Schema(name = "子女人數")
    private String childNo;
    // 學歷
    @Schema(name = "學歷")
    private String education;
    // 職業
    @Schema(name = "職業")
    private String career;
    // 舊客戶統一編號
    @Schema(name = "舊客戶統一編號")
    private String oldCustNo;
    // ＥＤＭ註記
    @Schema(name = "ＥＤＭ註記")
    private String edmAppv;
    // ＤＭ註記
    @Schema(name = "ＤＭ註記")
    private String dmAppv;
    // ＴＭ註記
    @Schema(name = "ＴＭ註記")
    private String tmAppv;
    // ＳＭＳ註記
    @Schema(name = "ＳＭＳ註記")
    private String smsAppv;
    // 特殊戶況說明
    @Schema(name = "特殊戶況說明")
    private String custSts01;
    // 定存到期EMAIL通知
    @Schema(name = "定存到期EMAIL通知")
    private String tdNotify;
    // 禁治產之監護人
    @Schema(name = "禁治產之監護人")
    private String protName;
    // 稅率別
    @Schema(name = "稅率別")
    private String rateTyp;
    // 異動行
    @Schema(name = "異動行")
    private String mtnBrhRat;
    // 異動日
    @Schema(name = "異動日")
    private String mtnDateRat;
    // 稅率年度
    @Schema(name = "稅率年度")
    private String rateYyy;
    // 扣繳稅率％
    @Schema(name = "扣繳稅率％")
    private String rate;
    // 歸屬行
    @Schema(name = "歸屬行")
    private String fc55Brh;
    // 洗錢及資恐風險評估結果
    @Schema(name = "洗錢及資恐風險評估結果")
    private String ccdFlg;
    // 是否為PEP
    @Schema(name = "是否為PEP")
    private String pepFlg;
    // 是否黑名單客戶
    @Schema(name = "是否黑名單客戶")
    private String blkFlg;
    // 是否有負面消息
    @Schema(name = "是否有負面消息")
    private String negative;
    // 是否File SAR
    @Schema(name = "是否File SAR")
    private String fileSar;
    // 英文地址國家別
    @Schema(name = "英文地址國家別")
    private String engCtry;
    // 個人年所得
    @Schema(name = "個人年所得")
    private String salary;
    // 聯絡電話
    @Schema(name = "聯絡電話")
    private String conTel;
    // 聯絡電話分機
    @Schema(name = "聯絡電話分機")
    private String conTelExt;
    // 公司電話
    @Schema(name = "公司電話")
    private String compTel;
    // 公司電話分機
    @Schema(name = "公司電話分機")
    private String compTelExt;
    // 戶籍電話
    @Schema(name = "戶籍電話")
    private String resTel;
    // 戶籍電話分機
    @Schema(name = "戶籍電話分機")
    private String resTelExt;
    // 手機號碼
    @Schema(name = "手機號碼")
    private String mobile;
    // 傳真
    @Schema(name = "傳真")
    private String fax;
    // 現居房屋產權
    @Schema(name = "現居房屋產權")
    private String occupancy1;
    // 現職公司地址
    @Schema(name = "現職公司地址")
    private String defaultString6;
    // 現居地址-1
    @Schema(name = "現居地址-1")
    private String currAddr1;
    // 現居地址-2
    @Schema(name = "現居地址-2")
    private String currAddr2;
    // 現居電話
    @Schema(name = "現居電話")
    private String currTel;
    // 現居電話分機
    @Schema(name = "現居電話分機")
    private String currTelExt;

    // 英文國名
    @Schema(name = "英文國名")
    private String nameEnUs;

    /**
     * @return the advCod
     */
    public String getAdvCod() {
        return advCod;
    }

    /**
     * @param advCod
     *            the advCod to set
     */
    public void setAdvCod(String advCod) {
        this.advCod = advCod;
    }

    /**
     * @return the refIdno
     */
    public String getRefIdno() {
        return refIdno;
    }

    /**
     * @param refIdno
     *            the refIdno to set
     */
    public void setRefIdno(String refIdno) {
        this.refIdno = refIdno;
    }

    /**
     * @return the custName
     */
    public String getCustName() {
        return custName;
    }

    /**
     * @param custName
     *            the custName to set
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * @return the bday
     */
    public String getBday() {
        return bday;
    }

    /**
     * @param bday
     *            the bday to set
     */
    public void setBday(String bday) {
        this.bday = bday;
    }

    /**
     * @return the orgType
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * @param orgType
     *            the orgType to set
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    /**
     * @return the orgCheck
     */
    public String getOrgCheck() {
        return orgCheck;
    }

    /**
     * @param orgCheck
     *            the orgCheck to set
     */
    public void setOrgCheck(String orgCheck) {
        this.orgCheck = orgCheck;
    }

    /**
     * @return the stockType
     */
    public String getStockType() {
        return stockType;
    }

    /**
     * @param stockType
     *            the stockType to set
     */
    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    /**
     * @return the iduCod
     */
    public String getIduCod() {
        return iduCod;
    }

    /**
     * @param iduCod
     *            the iduCod to set
     */
    public void setIduCod(String iduCod) {
        this.iduCod = iduCod;
    }

    /**
     * @return the entrpType
     */
    public String getEntrpType() {
        return entrpType;
    }

    /**
     * @param entrpType
     *            the entrpType to set
     */
    public void setEntrpType(String entrpType) {
        this.entrpType = entrpType;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     *            the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the custType
     */
    public String getCustType() {
        return custType;
    }

    /**
     * @param custType
     *            the custType to set
     */
    public void setCustType(String custType) {
        this.custType = custType;
    }

    /**
     * @return the residIdno
     */
    public String getResidIdno() {
        return residIdno;
    }

    /**
     * @param residIdno
     *            the residIdno to set
     */
    public void setResidIdno(String residIdno) {
        this.residIdno = residIdno;
    }

    /**
     * @return the residIdn01
     */
    public String getResidIdn01() {
        return residIdn01;
    }

    /**
     * @param residIdn01
     *            the residIdn01 to set
     */
    public void setResidIdn01(String residIdn01) {
        this.residIdn01 = residIdn01;
    }

    /**
     * @return the residDate
     */
    public String getResidDate() {
        return residDate;
    }

    /**
     * @param residDate
     *            the residDate to set
     */
    public void setResidDate(String residDate) {
        this.residDate = residDate;
    }

    /**
     * @return the residEffDate
     */
    public String getResidEffDate() {
        return residEffDate;
    }

    /**
     * @param residEffDate
     *            the residEffDate to set
     */
    public void setResidEffDate(String residEffDate) {
        this.residEffDate = residEffDate;
    }

    /**
     * @return the riskUnit
     */
    public String getRiskUnit() {
        return riskUnit;
    }

    /**
     * @param riskUnit
     *            the riskUnit to set
     */
    public void setRiskUnit(String riskUnit) {
        this.riskUnit = riskUnit;
    }

    /**
     * @return the riskRank
     */
    public String getRiskRank() {
        return riskRank;
    }

    /**
     * @param riskRank
     *            the riskRank to set
     */
    public void setRiskRank(String riskRank) {
        this.riskRank = riskRank;
    }

    /**
     * @return the rankDate
     */
    public String getRankDate() {
        return rankDate;
    }

    /**
     * @param rankDate
     *            the rankDate to set
     */
    public void setRankDate(String rankDate) {
        this.rankDate = rankDate;
    }

    /**
     * @return the riskRate
     */
    public String getRiskRate() {
        return riskRate;
    }

    /**
     * @param riskRate
     *            the riskRate to set
     */
    public void setRiskRate(String riskRate) {
        this.riskRate = riskRate;
    }

    /**
     * @return the iduCod2
     */
    public String getIduCod2() {
        return iduCod2;
    }

    /**
     * @param iduCod2
     *            the iduCod2 to set
     */
    public void setIduCod2(String iduCod2) {
        this.iduCod2 = iduCod2;
    }

    /**
     * @return the riskDate01
     */
    public String getRiskDate01() {
        return riskDate01;
    }

    /**
     * @param riskDate01
     *            the riskDate01 to set
     */
    public void setRiskDate01(String riskDate01) {
        this.riskDate01 = riskDate01;
    }

    /**
     * @return the riskRate01
     */
    public String getRiskRate01() {
        return riskRate01;
    }

    /**
     * @param riskRate01
     *            the riskRate01 to set
     */
    public void setRiskRate01(String riskRate01) {
        this.riskRate01 = riskRate01;
    }

    /**
     * @return the pdDate
     */
    public String getPdDate() {
        return pdDate;
    }

    /**
     * @param pdDate
     *            the pdDate to set
     */
    public void setPdDate(String pdDate) {
        this.pdDate = pdDate;
    }

    /**
     * @return the pdRate
     */
    public String getPdRate() {
        return pdRate;
    }

    /**
     * @param pdRate
     *            the pdRate to set
     */
    public void setPdRate(String pdRate) {
        this.pdRate = pdRate;
    }

    /**
     * @return the pdRisk
     */
    public String getPdRisk() {
        return pdRisk;
    }

    /**
     * @param pdRisk
     *            the pdRisk to set
     */
    public void setPdRisk(String pdRisk) {
        this.pdRisk = pdRisk;
    }

    /**
     * @return the ctryCod
     */
    public String getCtryCod() {
        return ctryCod;
    }

    /**
     * @param ctryCod
     *            the ctryCod to set
     */
    public void setCtryCod(String ctryCod) {
        this.ctryCod = ctryCod;
    }

    /**
     * @return the resume
     */
    public String getResume() {
        return resume;
    }

    /**
     * @param resume
     *            the resume to set
     */
    public void setResume(String resume) {
        this.resume = resume;
    }

    /**
     * @return the custSts
     */
    public String getCustSts() {
        return custSts;
    }

    /**
     * @param custSts
     *            the custSts to set
     */
    public void setCustSts(String custSts) {
        this.custSts = custSts;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company
     *            the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the busZip
     */
    public String getBusZip() {
        return busZip;
    }

    /**
     * @param busZip
     *            the busZip to set
     */
    public void setBusZip(String busZip) {
        this.busZip = busZip;
    }

    /**
     * @return the busAddr1
     */
    public String getBusAddr1() {
        return busAddr1;
    }

    /**
     * @param busAddr1
     *            the busAddr1 to set
     */
    public void setBusAddr1(String busAddr1) {
        this.busAddr1 = busAddr1;
    }

    /**
     * @return the busAddr2
     */
    public String getBusAddr2() {
        return busAddr2;
    }

    /**
     * @param busAddr2
     *            the busAddr2 to set
     */
    public void setBusAddr2(String busAddr2) {
        this.busAddr2 = busAddr2;
    }

    /**
     * @return the cttZip
     */
    public String getCttZip() {
        return cttZip;
    }

    /**
     * @param cttZip
     *            the cttZip to set
     */
    public void setCttZip(String cttZip) {
        this.cttZip = cttZip;
    }

    /**
     * @return the cttAddr1
     */
    public String getCttAddr1() {
        return cttAddr1;
    }

    /**
     * @param cttAddr1
     *            the cttAddr1 to set
     */
    public void setCttAddr1(String cttAddr1) {
        this.cttAddr1 = cttAddr1;
    }

    /**
     * @return the cttAddr2
     */
    public String getCttAddr2() {
        return cttAddr2;
    }

    /**
     * @param cttAddr2
     *            the cttAddr2 to set
     */
    public void setCttAddr2(String cttAddr2) {
        this.cttAddr2 = cttAddr2;
    }

    /**
     * @return the paySlip
     */
    public String getPaySlip() {
        return paySlip;
    }

    /**
     * @param paySlip
     *            the paySlip to set
     */
    public void setPaySlip(String paySlip) {
        this.paySlip = paySlip;
    }

    /**
     * @return the payCode
     */
    public String getPayCode() {
        return payCode;
    }

    /**
     * @param payCode
     *            the payCode to set
     */
    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    /**
     * @return the payZip
     */
    public String getPayZip() {
        return payZip;
    }

    /**
     * @param payZip
     *            the payZip to set
     */
    public void setPayZip(String payZip) {
        this.payZip = payZip;
    }

    /**
     * @return the payAddr1
     */
    public String getPayAddr1() {
        return payAddr1;
    }

    /**
     * @param payAddr1
     *            the payAddr1 to set
     */
    public void setPayAddr1(String payAddr1) {
        this.payAddr1 = payAddr1;
    }

    /**
     * @return the payAddr2
     */
    public String getPayAddr2() {
        return payAddr2;
    }

    /**
     * @param payAddr2
     *            the payAddr2 to set
     */
    public void setPayAddr2(String payAddr2) {
        this.payAddr2 = payAddr2;
    }

    /**
     * @return the telCod1
     */
    public String getTelCod1() {
        return telCod1;
    }

    /**
     * @param telCod1
     *            the telCod1 to set
     */
    public void setTelCod1(String telCod1) {
        this.telCod1 = telCod1;
    }

    /**
     * @return the telNo1
     */
    public String getTelNo1() {
        return telNo1;
    }

    /**
     * @param telNo1
     *            the telNo1 to set
     */
    public void setTelNo1(String telNo1) {
        this.telNo1 = telNo1;
    }

    /**
     * @return the telExt1
     */
    public String getTelExt1() {
        return telExt1;
    }

    /**
     * @param telExt1
     *            the telExt1 to set
     */
    public void setTelExt1(String telExt1) {
        this.telExt1 = telExt1;
    }

    /**
     * @return the telTyp1
     */
    public String getTelTyp1() {
        return telTyp1;
    }

    /**
     * @param telTyp1
     *            the telTyp1 to set
     */
    public void setTelTyp1(String telTyp1) {
        this.telTyp1 = telTyp1;
    }

    /**
     * @return the telDay1
     */
    public String getTelDay1() {
        return telDay1;
    }

    /**
     * @param telDay1
     *            the telDay1 to set
     */
    public void setTelDay1(String telDay1) {
        this.telDay1 = telDay1;
    }

    /**
     * @return the telNight1
     */
    public String getTelNight1() {
        return telNight1;
    }

    /**
     * @param telNight1
     *            the telNight1 to set
     */
    public void setTelNight1(String telNight1) {
        this.telNight1 = telNight1;
    }

    /**
     * @return the telVoice1
     */
    public String getTelVoice1() {
        return telVoice1;
    }

    /**
     * @param telVoice1
     *            the telVoice1 to set
     */
    public void setTelVoice1(String telVoice1) {
        this.telVoice1 = telVoice1;
    }

    /**
     * @return the telFax1
     */
    public String getTelFax1() {
        return telFax1;
    }

    /**
     * @param telFax1
     *            the telFax1 to set
     */
    public void setTelFax1(String telFax1) {
        this.telFax1 = telFax1;
    }

    /**
     * @return the telCod2
     */
    public String getTelCod2() {
        return telCod2;
    }

    /**
     * @param telCod2
     *            the telCod2 to set
     */
    public void setTelCod2(String telCod2) {
        this.telCod2 = telCod2;
    }

    /**
     * @return the telNo2
     */
    public String getTelNo2() {
        return telNo2;
    }

    /**
     * @param telNo2
     *            the telNo2 to set
     */
    public void setTelNo2(String telNo2) {
        this.telNo2 = telNo2;
    }

    /**
     * @return the telExt2
     */
    public String getTelExt2() {
        return telExt2;
    }

    /**
     * @param telExt2
     *            the telExt2 to set
     */
    public void setTelExt2(String telExt2) {
        this.telExt2 = telExt2;
    }

    /**
     * @return the telTyp2
     */
    public String getTelTyp2() {
        return telTyp2;
    }

    /**
     * @param telTyp2
     *            the telTyp2 to set
     */
    public void setTelTyp2(String telTyp2) {
        this.telTyp2 = telTyp2;
    }

    /**
     * @return the telDay2
     */
    public String getTelDay2() {
        return telDay2;
    }

    /**
     * @param telDay2
     *            the telDay2 to set
     */
    public void setTelDay2(String telDay2) {
        this.telDay2 = telDay2;
    }

    /**
     * @return the telNight2
     */
    public String getTelNight2() {
        return telNight2;
    }

    /**
     * @param telNight2
     *            the telNight2 to set
     */
    public void setTelNight2(String telNight2) {
        this.telNight2 = telNight2;
    }

    /**
     * @return the telVoice2
     */
    public String getTelVoice2() {
        return telVoice2;
    }

    /**
     * @param telVoice2
     *            the telVoice2 to set
     */
    public void setTelVoice2(String telVoice2) {
        this.telVoice2 = telVoice2;
    }

    /**
     * @return the telFax2
     */
    public String getTelFax2() {
        return telFax2;
    }

    /**
     * @param telFax2
     *            the telFax2 to set
     */
    public void setTelFax2(String telFax2) {
        this.telFax2 = telFax2;
    }

    /**
     * @return the emailAddr
     */
    public String getEmailAddr() {
        return emailAddr;
    }

    /**
     * @param emailAddr
     *            the emailAddr to set
     */
    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    /**
     * @return the noEmailFlg
     */
    public String getNoEmailFlg() {
        return noEmailFlg;
    }

    /**
     * @param noEmailFlg
     *            the noEmailFlg to set
     */
    public void setNoEmailFlg(String noEmailFlg) {
        this.noEmailFlg = noEmailFlg;
    }

    /**
     * @return the engName
     */
    public String getEngName() {
        return engName;
    }

    /**
     * @param engName
     *            the engName to set
     */
    public void setEngName(String engName) {
        this.engName = engName;
    }

    /**
     * @return the engZip
     */
    public String getEngZip() {
        return engZip;
    }

    /**
     * @param engZip
     *            the engZip to set
     */
    public void setEngZip(String engZip) {
        this.engZip = engZip;
    }

    /**
     * @return the engAddr1
     */
    public String getEngAddr1() {
        return engAddr1;
    }

    /**
     * @param engAddr1
     *            the engAddr1 to set
     */
    public void setEngAddr1(String engAddr1) {
        this.engAddr1 = engAddr1;
    }

    /**
     * @return the engAddr2
     */
    public String getEngAddr2() {
        return engAddr2;
    }

    /**
     * @param engAddr2
     *            the engAddr2 to set
     */
    public void setEngAddr2(String engAddr2) {
        this.engAddr2 = engAddr2;
    }

    /**
     * @return the regCapVal
     */
    public String getRegCapVal() {
        return regCapVal;
    }

    /**
     * @param regCapVal
     *            the regCapVal to set
     */
    public void setRegCapVal(String regCapVal) {
        this.regCapVal = regCapVal;
    }

    /**
     * @return the capValYy
     */
    public String getCapValYy() {
        return capValYy;
    }

    /**
     * @param capValYy
     *            the capValYy to set
     */
    public void setCapValYy(String capValYy) {
        this.capValYy = capValYy;
    }

    /**
     * @return the capValMm
     */
    public String getCapValMm() {
        return capValMm;
    }

    /**
     * @param capValMm
     *            the capValMm to set
     */
    public void setCapValMm(String capValMm) {
        this.capValMm = capValMm;
    }

    /**
     * @return the capVal
     */
    public String getCapVal() {
        return capVal;
    }

    /**
     * @param capVal
     *            the capVal to set
     */
    public void setCapVal(String capVal) {
        this.capVal = capVal;
    }

    /**
     * @return the empCnt
     */
    public String getEmpCnt() {
        return empCnt;
    }

    /**
     * @param empCnt
     *            the empCnt to set
     */
    public void setEmpCnt(String empCnt) {
        this.empCnt = empCnt;
    }

    /**
     * @return the aprovDate
     */
    public String getAprovDate() {
        return aprovDate;
    }

    /**
     * @param aprovDate
     *            the aprovDate to set
     */
    public void setAprovDate(String aprovDate) {
        this.aprovDate = aprovDate;
    }

    /**
     * @return the busItem
     */
    public String getBusItem() {
        return busItem;
    }

    /**
     * @param busItem
     *            the busItem to set
     */
    public void setBusItem(String busItem) {
        this.busItem = busItem;
    }

    /**
     * @return the contactPson
     */
    public String getContactPson() {
        return contactPson;
    }

    /**
     * @param contactPson
     *            the contactPson to set
     */
    public void setContactPson(String contactPson) {
        this.contactPson = contactPson;
    }

    /**
     * @return the respId
     */
    public String getRespId() {
        return respId;
    }

    /**
     * @param respId
     *            the respId to set
     */
    public void setRespId(String respId) {
        this.respId = respId;
    }

    /**
     * @return the respName
     */
    public String getRespName() {
        return respName;
    }

    /**
     * @param respName
     *            the respName to set
     */
    public void setRespName(String respName) {
        this.respName = respName;
    }

    /**
     * @return the respBday
     */
    public String getRespBday() {
        return respBday;
    }

    /**
     * @param respBday
     *            the respBday to set
     */
    public void setRespBday(String respBday) {
        this.respBday = respBday;
    }

    /**
     * @return the prtSlip
     */
    public String getPrtSlip() {
        return prtSlip;
    }

    /**
     * @param prtSlip
     *            the prtSlip to set
     */
    public void setPrtSlip(String prtSlip) {
        this.prtSlip = prtSlip;
    }

    /**
     * @return the frCmpid
     */
    public String getFrCmpid() {
        return frCmpid;
    }

    /**
     * @param frCmpid
     *            the frCmpid to set
     */
    public void setFrCmpid(String frCmpid) {
        this.frCmpid = frCmpid;
    }

    /**
     * @return the frIntno
     */
    public String getFrIntno() {
        return frIntno;
    }

    /**
     * @param frIntno
     *            the frIntno to set
     */
    public void setFrIntno(String frIntno) {
        this.frIntno = frIntno;
    }

    /**
     * @return the frRegno
     */
    public String getFrRegno() {
        return frRegno;
    }

    /**
     * @param frRegno
     *            the frRegno to set
     */
    public void setFrRegno(String frRegno) {
        this.frRegno = frRegno;
    }

    /**
     * @return the billsCheck
     */
    public String getBillsCheck() {
        return billsCheck;
    }

    /**
     * @param billsCheck
     *            the billsCheck to set
     */
    public void setBillsCheck(String billsCheck) {
        this.billsCheck = billsCheck;
    }

    /**
     * @return the srcTyp
     */
    public String getSrcTyp() {
        return srcTyp;
    }

    /**
     * @param srcTyp
     *            the srcTyp to set
     */
    public void setSrcTyp(String srcTyp) {
        this.srcTyp = srcTyp;
    }

    /**
     * @return the lostFlg
     */
    public String getLostFlg() {
        return lostFlg;
    }

    /**
     * @param lostFlg
     *            the lostFlg to set
     */
    public void setLostFlg(String lostFlg) {
        this.lostFlg = lostFlg;
    }

    /**
     * @return the billsUpdDate
     */
    public String getBillsUpdDate() {
        return billsUpdDate;
    }

    /**
     * @param billsUpdDate
     *            the billsUpdDate to set
     */
    public void setBillsUpdDate(String billsUpdDate) {
        this.billsUpdDate = billsUpdDate;
    }

    /**
     * @return the billsStrDate
     */
    public String getBillsStrDate() {
        return billsStrDate;
    }

    /**
     * @param billsStrDate
     *            the billsStrDate to set
     */
    public void setBillsStrDate(String billsStrDate) {
        this.billsStrDate = billsStrDate;
    }

    /**
     * @return the billsEndDate
     */
    public String getBillsEndDate() {
        return billsEndDate;
    }

    /**
     * @param billsEndDate
     *            the billsEndDate to set
     */
    public void setBillsEndDate(String billsEndDate) {
        this.billsEndDate = billsEndDate;
    }

    /**
     * @return the lstTxBrh1
     */
    public String getLstTxBrh1() {
        return lstTxBrh1;
    }

    /**
     * @param lstTxBrh1
     *            the lstTxBrh1 to set
     */
    public void setLstTxBrh1(String lstTxBrh1) {
        this.lstTxBrh1 = lstTxBrh1;
    }

    /**
     * @return the lstTxDate1
     */
    public String getLstTxDate1() {
        return lstTxDate1;
    }

    /**
     * @param lstTxDate1
     *            the lstTxDate1 to set
     */
    public void setLstTxDate1(String lstTxDate1) {
        this.lstTxDate1 = lstTxDate1;
    }

    /**
     * @return the lstTxBrh2
     */
    public String getLstTxBrh2() {
        return lstTxBrh2;
    }

    /**
     * @param lstTxBrh2
     *            the lstTxBrh2 to set
     */
    public void setLstTxBrh2(String lstTxBrh2) {
        this.lstTxBrh2 = lstTxBrh2;
    }

    /**
     * @return the lstTxDate2
     */
    public String getLstTxDate2() {
        return lstTxDate2;
    }

    /**
     * @param lstTxDate2
     *            the lstTxDate2 to set
     */
    public void setLstTxDate2(String lstTxDate2) {
        this.lstTxDate2 = lstTxDate2;
    }

    /**
     * @return the lstTxBrh3
     */
    public String getLstTxBrh3() {
        return lstTxBrh3;
    }

    /**
     * @param lstTxBrh3
     *            the lstTxBrh3 to set
     */
    public void setLstTxBrh3(String lstTxBrh3) {
        this.lstTxBrh3 = lstTxBrh3;
    }

    /**
     * @return the lstTxDate3
     */
    public String getLstTxDate3() {
        return lstTxDate3;
    }

    /**
     * @param lstTxDate3
     *            the lstTxDate3 to set
     */
    public void setLstTxDate3(String lstTxDate3) {
        this.lstTxDate3 = lstTxDate3;
    }

    /**
     * @return the lstTxBrh4
     */
    public String getLstTxBrh4() {
        return lstTxBrh4;
    }

    /**
     * @param lstTxBrh4
     *            the lstTxBrh4 to set
     */
    public void setLstTxBrh4(String lstTxBrh4) {
        this.lstTxBrh4 = lstTxBrh4;
    }

    /**
     * @return the lstTxDate4
     */
    public String getLstTxDate4() {
        return lstTxDate4;
    }

    /**
     * @param lstTxDate4
     *            the lstTxDate4 to set
     */
    public void setLstTxDate4(String lstTxDate4) {
        this.lstTxDate4 = lstTxDate4;
    }

    /**
     * @return the lstTxBrh5
     */
    public String getLstTxBrh5() {
        return lstTxBrh5;
    }

    /**
     * @param lstTxBrh5
     *            the lstTxBrh5 to set
     */
    public void setLstTxBrh5(String lstTxBrh5) {
        this.lstTxBrh5 = lstTxBrh5;
    }

    /**
     * @return the lstTxDate5
     */
    public String getLstTxDate5() {
        return lstTxDate5;
    }

    /**
     * @param lstTxDate5
     *            the lstTxDate5 to set
     */
    public void setLstTxDate5(String lstTxDate5) {
        this.lstTxDate5 = lstTxDate5;
    }

    /**
     * @return the lstTxBrh6
     */
    public String getLstTxBrh6() {
        return lstTxBrh6;
    }

    /**
     * @param lstTxBrh6
     *            the lstTxBrh6 to set
     */
    public void setLstTxBrh6(String lstTxBrh6) {
        this.lstTxBrh6 = lstTxBrh6;
    }

    /**
     * @return the lstTxDate6
     */
    public String getLstTxDate6() {
        return lstTxDate6;
    }

    /**
     * @param lstTxDate6
     *            the lstTxDate6 to set
     */
    public void setLstTxDate6(String lstTxDate6) {
        this.lstTxDate6 = lstTxDate6;
    }

    /**
     * @return the marrage
     */
    public String getMarrage() {
        return marrage;
    }

    /**
     * @param marrage
     *            the marrage to set
     */
    public void setMarrage(String marrage) {
        this.marrage = marrage;
    }

    /**
     * @return the childNo
     */
    public String getChildNo() {
        return childNo;
    }

    /**
     * @param childNo
     *            the childNo to set
     */
    public void setChildNo(String childNo) {
        this.childNo = childNo;
    }

    /**
     * @return the education
     */
    public String getEducation() {
        return education;
    }

    /**
     * @param education
     *            the education to set
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * @return the career
     */
    public String getCareer() {
        return career;
    }

    /**
     * @param career
     *            the career to set
     */
    public void setCareer(String career) {
        this.career = career;
    }

    /**
     * @return the oldCustNo
     */
    public String getOldCustNo() {
        return oldCustNo;
    }

    /**
     * @param oldCustNo
     *            the oldCustNo to set
     */
    public void setOldCustNo(String oldCustNo) {
        this.oldCustNo = oldCustNo;
    }

    /**
     * @return the edmAppv
     */
    public String getEdmAppv() {
        return edmAppv;
    }

    /**
     * @param edmAppv
     *            the edmAppv to set
     */
    public void setEdmAppv(String edmAppv) {
        this.edmAppv = edmAppv;
    }

    /**
     * @return the dmAppv
     */
    public String getDmAppv() {
        return dmAppv;
    }

    /**
     * @param dmAppv
     *            the dmAppv to set
     */
    public void setDmAppv(String dmAppv) {
        this.dmAppv = dmAppv;
    }

    /**
     * @return the tmAppv
     */
    public String getTmAppv() {
        return tmAppv;
    }

    /**
     * @param tmAppv
     *            the tmAppv to set
     */
    public void setTmAppv(String tmAppv) {
        this.tmAppv = tmAppv;
    }

    /**
     * @return the smsAppv
     */
    public String getSmsAppv() {
        return smsAppv;
    }

    /**
     * @param smsAppv
     *            the smsAppv to set
     */
    public void setSmsAppv(String smsAppv) {
        this.smsAppv = smsAppv;
    }

    /**
     * @return the custSts01
     */
    public String getCustSts01() {
        return custSts01;
    }

    /**
     * @param custSts01
     *            the custSts01 to set
     */
    public void setCustSts01(String custSts01) {
        this.custSts01 = custSts01;
    }

    /**
     * @return the tdNotify
     */
    public String getTdNotify() {
        return tdNotify;
    }

    /**
     * @param tdNotify
     *            the tdNotify to set
     */
    public void setTdNotify(String tdNotify) {
        this.tdNotify = tdNotify;
    }

    /**
     * @return the protName
     */
    public String getProtName() {
        return protName;
    }

    /**
     * @param protName
     *            the protName to set
     */
    public void setProtName(String protName) {
        this.protName = protName;
    }

    /**
     * @return the rateTyp
     */
    public String getRateTyp() {
        return rateTyp;
    }

    /**
     * @param rateTyp
     *            the rateTyp to set
     */
    public void setRateTyp(String rateTyp) {
        this.rateTyp = rateTyp;
    }

    /**
     * @return the mtnBrhRat
     */
    public String getMtnBrhRat() {
        return mtnBrhRat;
    }

    /**
     * @param mtnBrhRat
     *            the mtnBrhRat to set
     */
    public void setMtnBrhRat(String mtnBrhRat) {
        this.mtnBrhRat = mtnBrhRat;
    }

    /**
     * @return the mtnDateRat
     */
    public String getMtnDateRat() {
        return mtnDateRat;
    }

    /**
     * @param mtnDateRat
     *            the mtnDateRat to set
     */
    public void setMtnDateRat(String mtnDateRat) {
        this.mtnDateRat = mtnDateRat;
    }

    /**
     * @return the rateYyy
     */
    public String getRateYyy() {
        return rateYyy;
    }

    /**
     * @param rateYyy
     *            the rateYyy to set
     */
    public void setRateYyy(String rateYyy) {
        this.rateYyy = rateYyy;
    }

    /**
     * @return the rate
     */
    public String getRate() {
        return rate;
    }

    /**
     * @param rate
     *            the rate to set
     */
    public void setRate(String rate) {
        this.rate = rate;
    }

    /**
     * @return the fc55Brh
     */
    public String getFc55Brh() {
        return fc55Brh;
    }

    /**
     * @param fc55Brh
     *            the fc55Brh to set
     */
    public void setFc55Brh(String fc55Brh) {
        this.fc55Brh = fc55Brh;
    }

    /**
     * @return the ccdFlg
     */
    public String getCcdFlg() {
        return ccdFlg;
    }

    /**
     * @param ccdFlg
     *            the ccdFlg to set
     */
    public void setCcdFlg(String ccdFlg) {
        this.ccdFlg = ccdFlg;
    }

    /**
     * @return the pepFlg
     */
    public String getPepFlg() {
        return pepFlg;
    }

    /**
     * @param pepFlg
     *            the pepFlg to set
     */
    public void setPepFlg(String pepFlg) {
        this.pepFlg = pepFlg;
    }

    /**
     * @return the blkFlg
     */
    public String getBlkFlg() {
        return blkFlg;
    }

    /**
     * @param blkFlg
     *            the blkFlg to set
     */
    public void setBlkFlg(String blkFlg) {
        this.blkFlg = blkFlg;
    }

    /**
     * @return the negative
     */
    public String getNegative() {
        return negative;
    }

    /**
     * @param negative
     *            the negative to set
     */
    public void setNegative(String negative) {
        this.negative = negative;
    }

    /**
     * @return the fileSar
     */
    public String getFileSar() {
        return fileSar;
    }

    /**
     * @param fileSar
     *            the fileSar to set
     */
    public void setFileSar(String fileSar) {
        this.fileSar = fileSar;
    }

    /**
     * @return the engCtry
     */
    public String getEngCtry() {
        return engCtry;
    }

    /**
     * @param engCtry
     *            the engCtry to set
     */
    public void setEngCtry(String engCtry) {
        this.engCtry = engCtry;
    }

    /**
     * @return the salary
     */
    public String getSalary() {
        return salary;
    }

    /**
     * @param salary
     *            the salary to set
     */
    public void setSalary(String salary) {
        this.salary = salary;
    }

    /**
     * @return the conTel
     */
    public String getConTel() {
        return conTel;
    }

    /**
     * @param conTel
     *            the conTel to set
     */
    public void setConTel(String conTel) {
        this.conTel = conTel;
    }

    /**
     * @return the conTelExt
     */
    public String getConTelExt() {
        return conTelExt;
    }

    /**
     * @param conTelExt
     *            the conTelExt to set
     */
    public void setConTelExt(String conTelExt) {
        this.conTelExt = conTelExt;
    }

    /**
     * @return the compTel
     */
    public String getCompTel() {
        return compTel;
    }

    /**
     * @param compTel
     *            the compTel to set
     */
    public void setCompTel(String compTel) {
        this.compTel = compTel;
    }

    /**
     * @return the compTelExt
     */
    public String getCompTelExt() {
        return compTelExt;
    }

    /**
     * @param compTelExt
     *            the compTelExt to set
     */
    public void setCompTelExt(String compTelExt) {
        this.compTelExt = compTelExt;
    }

    /**
     * @return the resTel
     */
    public String getResTel() {
        return resTel;
    }

    /**
     * @param resTel
     *            the resTel to set
     */
    public void setResTel(String resTel) {
        this.resTel = resTel;
    }

    /**
     * @return the resTelExt
     */
    public String getResTelExt() {
        return resTelExt;
    }

    /**
     * @param resTelExt
     *            the resTelExt to set
     */
    public void setResTelExt(String resTelExt) {
        this.resTelExt = resTelExt;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     *            the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax
     *            the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the occupancy1
     */
    public String getOccupancy1() {
        return occupancy1;
    }

    /**
     * @param occupancy1
     *            the occupancy1 to set
     */
    public void setOccupancy1(String occupancy1) {
        this.occupancy1 = occupancy1;
    }

    /**
     * @return the defaultString6
     */
    public String getDefaultString6() {
        return defaultString6;
    }

    /**
     * @param defaultString6
     *            the defaultString6 to set
     */
    public void setDefaultString6(String defaultString6) {
        this.defaultString6 = defaultString6;
    }

    /**
     * @return the currAddr1
     */
    public String getCurrAddr1() {
        return currAddr1;
    }

    /**
     * @param currAddr1
     *            the currAddr1 to set
     */
    public void setCurrAddr1(String currAddr1) {
        this.currAddr1 = currAddr1;
    }

    /**
     * @return the currAddr2
     */
    public String getCurrAddr2() {
        return currAddr2;
    }

    /**
     * @param currAddr2
     *            the currAddr2 to set
     */
    public void setCurrAddr2(String currAddr2) {
        this.currAddr2 = currAddr2;
    }

    /**
     * @return the currTel
     */
    public String getCurrTel() {
        return currTel;
    }

    /**
     * @param currTel
     *            the currTel to set
     */
    public void setCurrTel(String currTel) {
        this.currTel = currTel;
    }

    /**
     * @return the currTelExt
     */
    public String getCurrTelExt() {
        return currTelExt;
    }

    /**
     * @param currTelExt
     *            the currTelExt to set
     */
    public void setCurrTelExt(String currTelExt) {
        this.currTelExt = currTelExt;
    }

    /**
     * @return the nameEnUs
     */
    public String getNameEnUs() {
        return nameEnUs;
    }

    /**
     * @param nameEnUs
     *            the nameEnUs to set
     */
    public void setNameEnUs(String nameEnUs) {
        this.nameEnUs = nameEnUs;
    }

    public String getIsoCode2() {
        return isoCode2;
    }

    public void setIsoCode2(String isoCode2) {
        this.isoCode2 = isoCode2;
    }
}
