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
package com.tfb.aibank.chl.general.resource.dto;

// @formatter:off
/**
 * @(#)AiFubonInsurDataModel.java
 *
 * <p>Description:台北富邦銀行內的富邦人壽保險資料和富邦人壽保單借款資料</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/03/22, Lillian.Tsai
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AiFubonInsurDataModel {

    /** SNAP_DATE */
    private String snapDate;

    /** 單位代碼 */
    private String unitCode;

    /** 保單號碼 */
    private String policyNo;

    /** 身份証重覆別 */
    private String idDup;

    /** 保單序號 */
    private int policySeq;

    /** 要保人 ID */
    private String applId;

    /** 要保人姓名 */
    private String applName;

    /** 身份証字號 */
    private String idNo;

    /** 被保人姓名 */
    private String insName;

    /** 險種代號 */
    private String item;

    /** 繳費年期 */
    private int premYear;

    /** 保額 */
    private int sa;

    /** 繳別 (D: 一次繳 A:年繳 Q:季繳 S:半年繳 M:月繳) */
    private String mop;

    /** 保險：表定保費(基本保費-優待保費) */
    private int prem;

    /** 生效日 */
    private String effDate;

    /** 申請日 */
    private String aplDate;

    /** 服務人員 ID */
    private String srvmanId;

    /** 員工姓名 */
    private String empyName;

    /** 主／附約別 */
    private String mainRidr;

    /** 契約狀態 (01 一般 02 催告 03 墊繳 04 繳清 05 展期 06 主被保人死亡 07 給付殘扶金 10 停效 11 效力停止 12 終止 13 拒保 14 延期 15 取消 16 自動墊繳 17 退票存證信函 20 滿期 21 解約 22 失效 23 頂替 24 轉新保單 25 死亡 26 契約撤銷 27 一級傷殘 28 解除契約 29 註銷 30 首期撤件) */
    private String cnctStat;

    /** 狀態中文 */
    private String cnctText;

    /** 狀態日期 */
    private String cnctDate;

    /** 保單年度 */
    private int poliYear;

    /** 計算日期 */
    private String calDate;

    /** 商品簡稱 */
    private String shrtName;

    /** 商品全稱 */
    private String cName;

    /** 換算保額 */
    private int newSa;

    /** 換算單位 */
    private String newUnit;

    /** 增額保費 */
    private int overPrem;

    /** 應繳保費(同PREM) */
    private int accuPrem;

    /** 帳戶價值／保價金 (每週五及月底營業日計算一次) */
    private int acctVal;

    /** 解約金 */
    private int surrAmt;

    /** 資料試算日 */
    private String confDate;

    /** 密戶否 (Y: 秘戶 N:非祕戶) */
    private String secrtYn;

    /** 投資起始日 */
    private String invtDate;

    /** 特殊險種註記(S、I：投資利變型 U：投資型保單-投資型保險(連結基金) F：投資型保單-連動債 空白：傳統型) */
    private String itemRemrk;

    /** 累計實繳保費 U類:總實收保費-累計提領金額 F類:總實收保費-累計提領金額 I,S類:總實收保費-累計提領金額(保價提領) 傳統型:同ACCU_ORIG以上只要為解約結案都放0 */
    private int paidPrem;

    /** 累計提領保費 */
    private int drawAmt;

    /** 已繳次數(實繳次數) */
    private int payTime;

    /** 可貸金額 */
    private int loanNet;

    /** 已貸金額 */
    private int loanAmt;

    /** 本月利率 */
    private int loanRate;

    /** 本期期數(for 吉利商品) */
    private int curPerd;

    /** 幣別 */
    private String currency;

    /** 累計台幣成本(F類商品的外幣保單才會將PAID_PREM換成台幣) */
    private int accuNtd;

    /** 進場匯率 */
    private int echgSrt;

    /** 投資保費 */
    private int invtAmt;

    /** 商品專案名稱 */
    private String sName;

    /** 約定外幣幣別 */
    private String pldtCur;

    /** 約定外幣金額 */
    private int pldtUnits;

    /** 累計實繳保費_折台 */
    private int paidPremNtd;

    /** 要保人生日 */
    private String abirthDat;

    /** 契約到期日 */
    private String exprDate;

    /** 無法提供解約金註記(值若為"-"代表此筆的ACCT_VAL不具參考價值 */
    private String surrRemrk;

    /** 保險代碼檔的SNAP_DATE */
    private String insutypDate;

    /** 新官網三大分類 */
    private String catMain3;

    /** 新版對帳單三大分類 */
    private String catBill;

    /** 對帳單金額 */
    private int billNtd;

    public String getSnapDate() {
        return snapDate;
    }

    public void setSnapDate(String snapDate) {
        this.snapDate = snapDate;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getIdDup() {
        return idDup;
    }

    public void setIdDup(String idDup) {
        this.idDup = idDup;
    }

    public int getPolicySeq() {
        return policySeq;
    }

    public void setPolicySeq(int policySeq) {
        this.policySeq = policySeq;
    }

    public String getApplId() {
        return applId;
    }

    public void setApplId(String applId) {
        this.applId = applId;
    }

    public String getApplName() {
        return applName;
    }

    public void setApplName(String applName) {
        this.applName = applName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getInsName() {
        return insName;
    }

    public void setInsName(String insName) {
        this.insName = insName;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPremYear() {
        return premYear;
    }

    public void setPremYear(int premYear) {
        this.premYear = premYear;
    }

    public int getSa() {
        return sa;
    }

    public void setSa(int sa) {
        this.sa = sa;
    }

    public String getMop() {
        return mop;
    }

    public void setMop(String mop) {
        this.mop = mop;
    }

    public int getPrem() {
        return prem;
    }

    public void setPrem(int prem) {
        this.prem = prem;
    }

    public String getEffDate() {
        return effDate;
    }

    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }

    public String getAplDate() {
        return aplDate;
    }

    public void setAplDate(String aplDate) {
        this.aplDate = aplDate;
    }

    public String getSrvmanId() {
        return srvmanId;
    }

    public void setSrvmanId(String srvmanId) {
        this.srvmanId = srvmanId;
    }

    public String getEmpyName() {
        return empyName;
    }

    public void setEmpyName(String empyName) {
        this.empyName = empyName;
    }

    public String getMainRidr() {
        return mainRidr;
    }

    public void setMainRidr(String mainRidr) {
        this.mainRidr = mainRidr;
    }

    public String getCnctStat() {
        return cnctStat;
    }

    public void setCnctStat(String cnctStat) {
        this.cnctStat = cnctStat;
    }

    public String getCnctText() {
        return cnctText;
    }

    public void setCnctText(String cnctText) {
        this.cnctText = cnctText;
    }

    public String getCnctDate() {
        return cnctDate;
    }

    public void setCnctDate(String cnctDate) {
        this.cnctDate = cnctDate;
    }

    public int getPoliYear() {
        return poliYear;
    }

    public void setPoliYear(int poliYear) {
        this.poliYear = poliYear;
    }

    public String getCalDate() {
        return calDate;
    }

    public void setCalDate(String calDate) {
        this.calDate = calDate;
    }

    public String getShrtName() {
        return shrtName;
    }

    public void setShrtName(String shrtName) {
        this.shrtName = shrtName;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getNewSa() {
        return newSa;
    }

    public void setNewSa(int newSa) {
        this.newSa = newSa;
    }

    public String getNewUnit() {
        return newUnit;
    }

    public void setNewUnit(String newUnit) {
        this.newUnit = newUnit;
    }

    public int getOverPrem() {
        return overPrem;
    }

    public void setOverPrem(int overPrem) {
        this.overPrem = overPrem;
    }

    public int getAccuPrem() {
        return accuPrem;
    }

    public void setAccuPrem(int accuPrem) {
        this.accuPrem = accuPrem;
    }

    public int getAcctVal() {
        return acctVal;
    }

    public void setAcctVal(int acctVal) {
        this.acctVal = acctVal;
    }

    public int getSurrAmt() {
        return surrAmt;
    }

    public void setSurrAmt(int surrAmt) {
        this.surrAmt = surrAmt;
    }

    public String getConfDate() {
        return confDate;
    }

    public void setConfDate(String confDate) {
        this.confDate = confDate;
    }

    public String getSecrtYn() {
        return secrtYn;
    }

    public void setSecrtYn(String secrtYn) {
        this.secrtYn = secrtYn;
    }

    public String getInvtDate() {
        return invtDate;
    }

    public void setInvtDate(String invtDate) {
        this.invtDate = invtDate;
    }

    public String getItemRemrk() {
        return itemRemrk;
    }

    public void setItemRemrk(String itemRemrk) {
        this.itemRemrk = itemRemrk;
    }

    public int getPaidPrem() {
        return paidPrem;
    }

    public void setPaidPrem(int paidPrem) {
        this.paidPrem = paidPrem;
    }

    public int getDrawAmt() {
        return drawAmt;
    }

    public void setDrawAmt(int drawAmt) {
        this.drawAmt = drawAmt;
    }

    public int getPayTime() {
        return payTime;
    }

    public void setPayTime(int payTime) {
        this.payTime = payTime;
    }

    public int getLoanNet() {
        return loanNet;
    }

    public void setLoanNet(int loanNet) {
        this.loanNet = loanNet;
    }

    public int getLoanAmt() {
        return loanAmt;
    }

    public void setLoanAmt(int loanAmt) {
        this.loanAmt = loanAmt;
    }

    public int getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(int loanRate) {
        this.loanRate = loanRate;
    }

    public int getCurPerd() {
        return curPerd;
    }

    public void setCurPerd(int curPerd) {
        this.curPerd = curPerd;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAccuNtd() {
        return accuNtd;
    }

    public void setAccuNtd(int accuNtd) {
        this.accuNtd = accuNtd;
    }

    public int getEchgSrt() {
        return echgSrt;
    }

    public void setEchgSrt(int echgSrt) {
        this.echgSrt = echgSrt;
    }

    public int getInvtAmt() {
        return invtAmt;
    }

    public void setInvtAmt(int invtAmt) {
        this.invtAmt = invtAmt;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getPldtCur() {
        return pldtCur;
    }

    public void setPldtCur(String pldtCur) {
        this.pldtCur = pldtCur;
    }

    public int getPldtUnits() {
        return pldtUnits;
    }

    public void setPldtUnits(int pldtUnits) {
        this.pldtUnits = pldtUnits;
    }

    public int getPaidPremNtd() {
        return paidPremNtd;
    }

    public void setPaidPremNtd(int paidPremNtd) {
        this.paidPremNtd = paidPremNtd;
    }

    public String getAbirthDat() {
        return abirthDat;
    }

    public void setAbirthDat(String abirthDat) {
        this.abirthDat = abirthDat;
    }

    public String getExprDate() {
        return exprDate;
    }

    public void setExprDate(String exprDate) {
        this.exprDate = exprDate;
    }

    public String getSurrRemrk() {
        return surrRemrk;
    }

    public void setSurrRemrk(String surrRemrk) {
        this.surrRemrk = surrRemrk;
    }

    public String getInsutypDate() {
        return insutypDate;
    }

    public void setInsutypDate(String insutypDate) {
        this.insutypDate = insutypDate;
    }

    public String getCatMain3() {
        return catMain3;
    }

    public void setCatMain3(String catMain3) {
        this.catMain3 = catMain3;
    }

    public String getCatBill() {
        return catBill;
    }

    public void setCatBill(String catBill) {
        this.catBill = catBill;
    }

    public int getBillNtd() {
        return billNtd;
    }

    public void setBillNtd(int billNtd) {
        this.billNtd = billNtd;
    }
}
