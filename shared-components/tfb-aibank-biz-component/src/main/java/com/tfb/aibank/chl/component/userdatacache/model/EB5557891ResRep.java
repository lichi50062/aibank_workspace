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
import java.math.BigDecimal;
import java.util.Date;

import com.ibm.tw.commons.util.StringUtils;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB5557891ResRep.java
 * 
 * <p>Description:歸戶帳號電文(EB5557891)下行欄位 Repeat</p>
 * 包含以下格式欄位
 * <ul>
 *  <li>D001</li>
 *  <li>D002</li>
 *  <li>D003</li>
 *  <li>D004</li>
 *  <li>D005</li>
 *  <li>D007</li>
 *  <li>D008</li>
 *  <li>D009</li>
 * </ul>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/04, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "歸戶帳號電文(EB5557891)下行欄位 Repeat")
public class EB5557891ResRep implements Serializable {

    private static final long serialVersionUID = 1378714617216463500L;

    /** 帳號 */
    @Schema(description = "帳號")
    private String acno;

    /** 帳號類別 */
    @Schema(description = "帳號類別")
    private String type;

    /** 存單或可用餘額 */
    @Schema(description = "存單或可用餘額")
    private BigDecimal avalAmt;

    /** 學年度-學期 */
    @Schema(description = "學年度-學期")
    private String yearTerm;

    /** 存單號碼/定存序號 */
    @Schema(description = "存單號碼/定存序號")
    private String slipNo;

    /** 幣別 */
    @Schema(description = "幣別")
    private String curCod;

    /** 戶況 */
    @Schema(description = "戶況")
    private String actSts;

    /** 文件編號 */
    @Schema(description = "文件編號")
    private String docNo;

    /** 到期日 */
    @Schema(description = "到期日")
    private Date dueDate;

    /** 匯率 */
    @Schema(description = "匯率")
    private BigDecimal rate;

    /** 版塊 */
    @Schema(description = "版塊")
    private String clazz;

    /** 帳上餘額（原幣） */
    @Schema(description = "帳上餘額（原幣）")
    private BigDecimal actBal;

    /** 帳上餘額（折合台幣） */
    @Schema(description = "帳上餘額（折合台幣）")
    private BigDecimal actBalNT;

    /** 會計科目 */
    @Schema(description = "會計科目")
    private String glcdLoan;

    /** 原貸款金額 */
    @Schema(description = "原貸款金額")
    private BigDecimal oriLoanBal;

    /** 已繳期數 */
    @Schema(description = "已繳期數")
    private String loanTerm;

    /** 貸款類型 */
    @Schema(description = "貸款類型")
    private String loanTyp;

    /** 自動扣繳帳號 */
    @Schema(description = "自動扣繳帳號")
    private String acnoSa;

    /** 存單號碼 */
    @Schema(description = "存單號碼")
    private String tdNo;

    /** 應繳日 */
    @Schema(description = "應繳日")
    private String intCycle;

    /** 每期期金 */
    @Schema(description = "每期期金")
    private BigDecimal insAmt;

    /** 就貸：還款日 企業：交易日 */
    @Schema(description = "就貸：還款日 企業：交易日")
    private Date prnStrDate;

    /** 開戶日 */
    @Schema(description = "開戶日")
    private Date dateOpen;

    /** 部份銷帳 */
    @Schema(description = "部份銷帳")
    private String partRecv;

    /** ACH 註記 */
    @Schema(description = "ACH 註記")
    private String achFlg;

    /** 貸款帳號特殊狀況註記 */
    @Schema(description = "貸款帳號特殊狀況註記")
    private String specSts;

    /** 交易明細查詢 */
    @Schema(description = "交易明細查詢")
    private String flg302611;

    /** 還款試算查詢 */
    @Schema(description = "還款試算查詢")
    private String flg302602;

    /** 性質別 */
    @Schema(description = "性質別")
    private String lnTyp;

    /** 產品大類 */
    @Schema(description = "產品大類")
    private String atype;

    /** 產品子類 */
    @Schema(description = "產品子類")
    private String icat;

    /** 專案別 */
    @Schema(description = "專案別")
    private String stuSpNo;

    /** 分行別 */
    @Schema(description = "分行別")
    private String branchNo;

    /** 數位存款註記 */
    @Schema(description = "數位存款註記")
    private String digitalFlg;

    /** 帳戶名稱 */
    @Schema(description = "帳戶名稱")
    private String accName;

    /** 前端使用：是否為通提戶(Y/N) */
    @Schema(description = "前端使用：是否為通提戶(Y/N)")
    private String crossFlg;

    /** 前端使用：通提密碼變更錯誤次數 */
    @Schema(description = "前端使用：通提密碼變更錯誤次數")
    private String pwdErrCnt;

    /** 前端使用：是否為無摺戶(Y/N) */
    @Schema(description = "前端使用：是否為無摺戶(Y/N)")
    private String passbookFlg;

    /** 產品標幟 */
    @Schema(description = "產品標幟")
    private String prodInd;

    /** 透支總限額 */
    @Schema(description = "透支總限額")
    private BigDecimal odTotLimit;

    /** 存款透支帳號 */
    @Schema(description = "存款透支帳號")
    private String odAcno;

    /** 逾催呆註記 */
    @Schema(description = "逾催呆註記")
    private String waLnBadDebtInd;

    /** 債清註記 */
    @Schema(description = "債清註記")
    private String waLnBcType;

    /** 協商階段 */
    @Schema(description = "協商階段")
    private String waLnBcStag;

    /** 還款方式 */
    @Schema(description = "還款方式")
    private String defaultString18;

    /** 透支可用餘額 */
    @Schema(description = "透支可用餘額")
    private BigDecimal odavail1;

    /** 警調通報戶筆數+高風險失聯戶筆數+無效公司戶筆數(判斷>0=Y) */
    @Schema(description = "警調通報戶筆數+高風險失聯戶筆數+無效公司戶筆數(判斷>0=Y)")
    private BigDecimal alertCount;

    /** 衍生管制戶筆數(判斷>0=Y) */
    @Schema(description = "衍生管制戶筆數(判斷>0=Y)")
    private BigDecimal derivative;

    /** 疑似詐騙戶筆數(判斷>0=Y) */
    @Schema(description = "疑似詐騙戶筆數(判斷>0=Y)")
    private BigDecimal swindleCount;

    /** 聯防管制戶筆數(判斷>0=Y) */
    @Schema(description = "聯防管制戶筆數(判斷>0=Y)")
    private BigDecimal declineCount;

    /** 其他不良戶筆數(判斷>0=Y) */
    @Schema(description = "其他不良戶筆數(判斷>0=Y)")
    private BigDecimal othersCount;

    /** 凍結筆數(判斷>0=Y) */
    @Schema(description = "凍結筆數(判斷>0=Y)")
    private BigDecimal noOfStops;

    /** 止付筆數(判斷>0=Y) */
    @Schema(description = "止付筆數(判斷>0=Y)")
    private BigDecimal noOfStopWdl;

    /** 期貨扣帳(判斷>0=Y) */
    @Schema(description = "期貨扣帳(判斷>0=Y)")
    private String futuresDebit;

    /** 證券綜合戶(判斷Y=Y/其他=N) */
    @Schema(description = "證券綜合戶(判斷Y=Y/其他=N)")
    private String stockOmniAcc;

    /** 複委託戶(判斷Y=Y/其他=N) */
    @Schema(description = "複委託戶(判斷Y=Y/其他=N)")
    private String complexAcc;

    /** OSU國際證券業務(判斷Y=Y/其他=N) */
    @Schema(description = "OSU國際證券業務(判斷Y=Y/其他=N)")
    private String osuBusiness;

    /** 查詢同意(判斷Y=Y/其他=N) */
    @Schema(description = "查詢同意(判斷Y=Y/其他=N)")
    private String enqAgree;

    /** 證券查詢(判斷Y=Y/其他=N) */
    @Schema(description = "證券查詢(判斷Y=Y/其他=N)")
    private String stockEnq;

    /** 定存帳號 */
    @Schema(description = "定存帳號")
    private String tdAct;

    /** 分行別(自定義 非電文欄位) */
    @Schema(description = "分行別(自定義 非電文欄位)")
    private String displayBranchId;

    /** 利率 */
    @Schema(description = "利率")
    private BigDecimal intRate;

    /** 日盛88專戶 */
    @Schema(description = "日盛88專戶")
    private String happy88;

    /** 特殊帳戶註記欄位 */
    @Schema(description = "特殊帳戶註記欄位")
    private String specAcctInd1;

    /** rawData */
    @Schema(description = "rawData")
    private String data;

    /** 綜定存帳號 */
    @Schema(description = "綜定存帳號")
    private String defIntegerT1;

    /** 狀態 */
    @Schema(description = "狀態")
    private String toption;

    /** 透支帳號 */
    @Schema(description = "透支帳號")
    private String defIntegerT2;

    /** 薪轉委託單位代號 */
    @Schema(description = "薪轉委託單位代號")
    private String payrollCode;

    /** D005 下行欄位 Repeat */
    @Schema(description = "D005 下行欄位 Repeat")
    private EB5557891D005Rep eb5557891D005Rep;

    // ================== 以下為擴充欄位 ==============================

    /** 帳號類型 */
    @Schema(description = "帳號類型")
    private String acctType;

    /** 帳號子類型 */
    @Schema(description = "帳號子類型")
    private String acctSubType;

    /** 帳號類型名稱 */
    @Schema(description = "帳號類型名稱")
    private String acctTypeName;

    /** 帳號說明 */
    @Schema(description = "帳號說明")
    private String accountDesc;

    /** 轉出註記 */
    @Schema(description = "轉出註記")
    private boolean transOutFlag;

    /** 開啟轉帳服務註記 */
    @Schema(description = "開啟轉帳服務註記")
    private boolean openTransFlag = false;
    // ================== 以上為擴充欄位 ==============================

    /** 限約定轉入帳號註記 */
    @Schema(description = "限約定轉入帳號註記")
    public String getAcnoInFlag() {
        if (getEb5557891D005Rep() != null) {
            return getEb5557891D005Rep().getAcnoInFlag();
        }
        return StringUtils.EMPTY;
    }

    /** 外幣功能註記 */
    @Schema(description = "外幣功能註記")
    public String getExFlg() {
        if (getEb5557891D005Rep() != null) {
            return getEb5557891D005Rep().getExFlg();
        }
        return StringUtils.EMPTY;
    }

    /** 數位存款視訊註記 */
    @Schema(description = "數位存款視訊註記")
    public String getVideoFlg() {
        if (getEb5557891D005Rep() != null) {
            return getEb5557891D005Rep().getVideoFlg();
        }
        return StringUtils.EMPTY;
    }

    /** LIMITUP_FLAG 提高非約轉 帳戶註記 由EB5557891.D005.EX_FLAG取得，D001實際上沒有這個欄位 */
    @Schema(description = "LIMITUP_FLAG 提高非約轉 帳戶註記")
    public String getLimitupFlag() {
        return getExFlg();
    }

    /** DIGITAL_LIMITUP_FLAG 數三提高非約轉 帳戶註記 由EB5557891.D005.VideoFlag取得，D001實際上沒有這個欄位 */
    @Schema(description = "DIGITAL_LIMITUP_FLAG 數三提高非約轉 帳戶註記")
    public String getDigitalLimitupFlag() {
        return getVideoFlg();
    }

    /**
     * 提高限額註記
     * 
     * @return
     */
    public boolean isIncreaseFlag() {
        // 數存帳號
        if (this.isDigitalFlag()) {
            return StringUtils.isY(getVideoFlg());
        }
        // 一般帳號
        else {
            return StringUtils.isY(getExFlg());
        }
    }

    /**
     * 是否為數位存款帳號
     * 
     * @return
     */
    public boolean isDigitalFlag() {
        return StringUtils.equalsAny(digitalFlg, "0", "1", "2", "3", "4", "5");
    }

    public String getAcno() {
        return acno;
    }

    public void setAcno(String acno) {
        this.acno = acno;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAvalAmt() {
        return avalAmt;
    }

    public void setAvalAmt(BigDecimal avalAmt) {
        this.avalAmt = avalAmt;
    }

    public String getYearTerm() {
        return yearTerm;
    }

    public void setYearTerm(String yearTerm) {
        this.yearTerm = yearTerm;
    }

    public String getSlipNo() {
        return slipNo;
    }

    public void setSlipNo(String slipNo) {
        this.slipNo = slipNo;
    }

    public String getCurCod() {
        return curCod;
    }

    public void setCurCod(String curCod) {
        this.curCod = curCod;
    }

    public String getActSts() {
        return actSts;
    }

    public void setActSts(String actSts) {
        this.actSts = actSts;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public BigDecimal getActBal() {
        return actBal;
    }

    public void setActBal(BigDecimal actBal) {
        this.actBal = actBal;
    }

    public BigDecimal getActBalNT() {
        return actBalNT;
    }

    public void setActBalNT(BigDecimal actBalNT) {
        this.actBalNT = actBalNT;
    }

    public String getGlcdLoan() {
        return glcdLoan;
    }

    public void setGlcdLoan(String glcdLoan) {
        this.glcdLoan = glcdLoan;
    }

    public BigDecimal getOriLoanBal() {
        return oriLoanBal;
    }

    public void setOriLoanBal(BigDecimal oriLoanBal) {
        this.oriLoanBal = oriLoanBal;
    }

    public String getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(String loanTerm) {
        this.loanTerm = loanTerm;
    }

    public String getLoanTyp() {
        return loanTyp;
    }

    public void setLoanTyp(String loanTyp) {
        this.loanTyp = loanTyp;
    }

    public String getAcnoSa() {
        return acnoSa;
    }

    public void setAcnoSa(String acnoSa) {
        this.acnoSa = acnoSa;
    }

    public String getTdNo() {
        return tdNo;
    }

    public void setTdNo(String tdNo) {
        this.tdNo = tdNo;
    }

    public String getIntCycle() {
        return intCycle;
    }

    public void setIntCycle(String intCycle) {
        this.intCycle = intCycle;
    }

    public BigDecimal getInsAmt() {
        return insAmt;
    }

    public void setInsAmt(BigDecimal insAmt) {
        this.insAmt = insAmt;
    }

    public Date getPrnStrDate() {
        return prnStrDate;
    }

    public void setPrnStrDate(Date prnStrDate) {
        this.prnStrDate = prnStrDate;
    }

    public Date getDateOpen() {
        return dateOpen;
    }

    public void setDateOpen(Date dateOpen) {
        this.dateOpen = dateOpen;
    }

    public String getPartRecv() {
        return partRecv;
    }

    public void setPartRecv(String partRecv) {
        this.partRecv = partRecv;
    }

    public String getAchFlg() {
        return achFlg;
    }

    public void setAchFlg(String achFlg) {
        this.achFlg = achFlg;
    }

    public String getSpecSts() {
        return specSts;
    }

    public void setSpecSts(String specSts) {
        this.specSts = specSts;
    }

    public String getFlg302611() {
        return flg302611;
    }

    public void setFlg302611(String flg302611) {
        this.flg302611 = flg302611;
    }

    public String getFlg302602() {
        return flg302602;
    }

    public void setFlg302602(String flg302602) {
        this.flg302602 = flg302602;
    }

    public String getLnTyp() {
        return lnTyp;
    }

    public void setLnTyp(String lnTyp) {
        this.lnTyp = lnTyp;
    }

    public String getAtype() {
        return atype;
    }

    public void setAtype(String atype) {
        this.atype = atype;
    }

    public String getIcat() {
        return icat;
    }

    public void setIcat(String icat) {
        this.icat = icat;
    }

    public String getStuSpNo() {
        return stuSpNo;
    }

    public void setStuSpNo(String stuSpNo) {
        this.stuSpNo = stuSpNo;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public String getDigitalFlg() {
        return digitalFlg;
    }

    public void setDigitalFlg(String digitalFlg) {
        this.digitalFlg = digitalFlg;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getCrossFlg() {
        return crossFlg;
    }

    public void setCrossFlg(String crossFlg) {
        this.crossFlg = crossFlg;
    }

    public String getPwdErrCnt() {
        return pwdErrCnt;
    }

    public void setPwdErrCnt(String pwdErrCnt) {
        this.pwdErrCnt = pwdErrCnt;
    }

    public String getPassbookFlg() {
        return passbookFlg;
    }

    public void setPassbookFlg(String passbookFlg) {
        this.passbookFlg = passbookFlg;
    }

    public String getProdInd() {
        return prodInd;
    }

    public void setProdInd(String prodInd) {
        this.prodInd = prodInd;
    }

    public BigDecimal getOdTotLimit() {
        return odTotLimit;
    }

    public void setOdTotLimit(BigDecimal odTotLimit) {
        this.odTotLimit = odTotLimit;
    }

    public String getOdAcno() {
        return odAcno;
    }

    public void setOdAcno(String odAcno) {
        this.odAcno = odAcno;
    }

    public String getWaLnBadDebtInd() {
        return waLnBadDebtInd;
    }

    public void setWaLnBadDebtInd(String waLnBadDebtInd) {
        this.waLnBadDebtInd = waLnBadDebtInd;
    }

    public String getWaLnBcType() {
        return waLnBcType;
    }

    public void setWaLnBcType(String waLnBcType) {
        this.waLnBcType = waLnBcType;
    }

    public String getWaLnBcStag() {
        return waLnBcStag;
    }

    public void setWaLnBcStag(String waLnBcStag) {
        this.waLnBcStag = waLnBcStag;
    }

    public String getDefaultString18() {
        return defaultString18;
    }

    public void setDefaultString18(String defaultString18) {
        this.defaultString18 = defaultString18;
    }

    public BigDecimal getOdavail1() {
        return odavail1;
    }

    public void setOdavail1(BigDecimal odavail1) {
        this.odavail1 = odavail1;
    }

    public BigDecimal getAlertCount() {
        return alertCount;
    }

    public void setAlertCount(BigDecimal alertCount) {
        this.alertCount = alertCount;
    }

    public BigDecimal getDerivative() {
        return derivative;
    }

    public void setDerivative(BigDecimal derivative) {
        this.derivative = derivative;
    }

    public BigDecimal getSwindleCount() {
        return swindleCount;
    }

    public void setSwindleCount(BigDecimal swindleCount) {
        this.swindleCount = swindleCount;
    }

    public BigDecimal getDeclineCount() {
        return declineCount;
    }

    public void setDeclineCount(BigDecimal declineCount) {
        this.declineCount = declineCount;
    }

    public BigDecimal getOthersCount() {
        return othersCount;
    }

    public void setOthersCount(BigDecimal othersCount) {
        this.othersCount = othersCount;
    }

    public BigDecimal getNoOfStops() {
        return noOfStops;
    }

    public void setNoOfStops(BigDecimal noOfStops) {
        this.noOfStops = noOfStops;
    }

    public BigDecimal getNoOfStopWdl() {
        return noOfStopWdl;
    }

    public void setNoOfStopWdl(BigDecimal noOfStopWdl) {
        this.noOfStopWdl = noOfStopWdl;
    }

    public String getFuturesDebit() {
        return futuresDebit;
    }

    public void setFuturesDebit(String futuresDebit) {
        this.futuresDebit = futuresDebit;
    }

    public String getStockOmniAcc() {
        return stockOmniAcc;
    }

    public void setStockOmniAcc(String stockOmniAcc) {
        this.stockOmniAcc = stockOmniAcc;
    }

    public String getComplexAcc() {
        return complexAcc;
    }

    public void setComplexAcc(String complexAcc) {
        this.complexAcc = complexAcc;
    }

    public String getOsuBusiness() {
        return osuBusiness;
    }

    public void setOsuBusiness(String osuBusiness) {
        this.osuBusiness = osuBusiness;
    }

    public String getEnqAgree() {
        return enqAgree;
    }

    public void setEnqAgree(String enqAgree) {
        this.enqAgree = enqAgree;
    }

    public String getStockEnq() {
        return stockEnq;
    }

    public void setStockEnq(String stockEnq) {
        this.stockEnq = stockEnq;
    }

    public String getTdAct() {
        return tdAct;
    }

    public void setTdAct(String tdAct) {
        this.tdAct = tdAct;
    }

    public String getDisplayBranchId() {
        return displayBranchId;
    }

    public void setDisplayBranchId(String displayBranchId) {
        this.displayBranchId = displayBranchId;
    }

    public String getHappy88() {
        return happy88;
    }

    public void setHappy88(String happy88) {
        this.happy88 = happy88;
    }

    public String getSpecAcctInd1() {
        return specAcctInd1;
    }

    public void setSpecAcctInd1(String specAcctInd1) {
        this.specAcctInd1 = specAcctInd1;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDefIntegerT1() {
        return defIntegerT1;
    }

    public void setDefIntegerT1(String defIntegerT1) {
        this.defIntegerT1 = defIntegerT1;
    }

    public String getToption() {
        return toption;
    }

    public void setToption(String toption) {
        this.toption = toption;
    }

    public String getDefIntegerT2() {
        return defIntegerT2;
    }

    public void setDefIntegerT2(String defIntegerT2) {
        this.defIntegerT2 = defIntegerT2;
    }

    public BigDecimal getIntRate() {
        return intRate;
    }

    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    /**
     * @return the accountTypeName
     */
    public String getAcctTypeName() {
        return acctTypeName;
    }

    /**
     * @param accountTypeName
     *            the accountTypeName to set
     */
    public void setAcctTypeName(String accountTypeName) {
        this.acctTypeName = accountTypeName;
    }

    /**
     * @return the accountType
     */
    public String getAcctType() {
        return acctType;
    }

    /**
     * @param accountType
     *            the accountType to set
     */
    public void setAcctType(String accountType) {
        this.acctType = accountType;
    }

    public String getAccountDesc() {
        return accountDesc;
    }

    public void setAccountDesc(String accountDesc) {
        this.accountDesc = accountDesc;
    }

    public String getAcctSubType() {
        return acctSubType;
    }

    public void setAcctSubType(String acctSubType) {
        this.acctSubType = acctSubType;
    }

    public void setTransOutFlag(boolean transOutFlag) {
        this.transOutFlag = transOutFlag;
    }

    public boolean isTransOutFlag() {
        return transOutFlag;
    }

    public EB5557891D005Rep getEb5557891D005Rep() {
        return eb5557891D005Rep;
    }

    public void setEb5557891D005Rep(EB5557891D005Rep eb5557891d005Rep) {
        eb5557891D005Rep = eb5557891d005Rep;
    }

    public boolean isOpenTransFlag() {
        return openTransFlag;
    }

    public void setOpenTransFlag(boolean openTransFlag) {
        this.openTransFlag = openTransFlag;
    }

    public String getPayrollCode() {
        return payrollCode;
    }

    public void setPayrollCode(String payrollCode) {
        this.payrollCode = payrollCode;
    }
}
