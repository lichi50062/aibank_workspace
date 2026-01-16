/**
 * 
 */
package com.tfb.aibank.chl.component.userdatacache.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

//@formatter:off
/**
* @(#)LoanAccount.java
* 
* <p>Description:EB555789 取得貸款帳號 Repeat</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class LoanAccount {

    /** 帳號 */
    private String acno;

    /** 帳號類別 */
    private String type;

    /** 存單或可用餘額 */
    private BigDecimal avalAmt;

    /** 學年度-學期 */
    private String yearTerm;

    /** 存單號碼 */
    private String slipNo;

    /** 幣別 */
    private String curCod;

    /** 戶況 */
    private String actSts;

    /** 文件編號 */
    private String docNo;

    /** 到期日 */
    private Calendar dueDate;

    /** 適用利率 */
    private BigDecimal rate;

    /** 版塊 */
    private String cLass;

    /** 帳上餘額（原幣） */
    private BigDecimal actBal;

    /** 帳上餘額（折合台幣） */
    private BigDecimal actBalNt;

    /** 會計科目 */
    private String glcdLoan;

    /** 原貸款金額 */
    private BigDecimal oriLoanBal;

    /** 已繳期數 */
    private String loanTerm;

    /** 貸款類型 */
    private String loanTyp;

    /** 自動扣繳帳號 */
    private String acnoSa;

    /** 限約定轉入帳號註記 */
    private String acnoInFlag;

    /** 存單號碼 */
    private String tdNo;

    /** 應繳日 */
    private String intCycle;

    /** 每期期金 */
    private BigDecimal insAmt;

    /** 交易日 */
    private Calendar prnStrDate;

    /** 部份銷帳 */
    private String partRecv;

    /** ACH 註記 */
    private String achFlag;

    /** 貸款帳號特殊狀況註記 */
    private String specSts;

    /** 交易明細查詢 */
    private String flag302611;

    /** 還款試算查詢 */
    private String flag302602;

    /** 性質別 */
    private String lnTyp;

    /** 產品大類 */
    private String aType;

    /** 產品子類 */
    private String iCat;

    /** 專案別 */
    private String stuSpNo;

    /** 分行別 */
    private String branchNo;

    /** 數位存款註記 */
    private String digitalFlg;

    /** 外幣功能註記 */
    private String exFlg;

    /** 帳戶名稱 */
    private String accName;

    /** 是否為通提戶(Y/N) */
    private String crossFlg;

    /** 通提密碼變更錯誤次數 */
    private String pwdErrCnt;

    /** 是否為無摺戶(Y/N) */
    private String passbookFlg;

    /** 產品標幟 */
    private String prodInd;

    /** 透支總限額 */
    private BigDecimal odTotLimit;

    /** 存款透支帳號 */
    private String odAcno;

    /** 逾催呆註記 */
    private String waLnBadDebtInd;

    /** 債清註記 */
    private String waLnBcType;

    /** 協商階段 */
    private String waLnBcStag;

    /** 還款方式 */
    private String defaultString18;

    /** 訂約額度 */
    private BigDecimal defaultString24;

    /** 透支可用餘額 */
    private BigDecimal odAvil1;

    /** 定存帳號 */
    private String tdAct;

    /** 剩餘期數 */
    private String restLoanTerm;

    /** 逾期期數 */
    private String arrsCtr;

    // ==============================
    /** 產品類型 */
    private String productType;

    /** 貸款類型 */
    private String loanType;

    /** 產品名稱 */
    private String productName;

    /** 貸款名稱 */
    private String loanName;

    /** 特殊貸款狀態 */
    private Boolean isSpecialStatus;

    /** 催呆帳狀態 */
    private Boolean isBadDebt;

    /** 協商狀態 */
    private Boolean isNegotiation;

    /** 貸款名稱 */
    private String accountAlias;

    /** 是否逾期 */
    private Boolean isOverDue;

    /** 學貸資料 */
    private List<StudentLoanData> studentLoan;

    /**
     * 
     */
    public String getAccountKey() {
        return this.acno + '_' + this.curCod;
    }

    /**
     * @return the acno
     */
    public String getAcno() {
        return acno;
    }

    /**
     * @param acno
     *            the acno to set
     */
    public void setAcno(String acno) {
        this.acno = acno;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the avalAmt
     */
    public BigDecimal getAvalAmt() {
        return avalAmt;
    }

    /**
     * @param avalAmt
     *            the avalAmt to set
     */
    public void setAvalAmt(BigDecimal avalAmt) {
        this.avalAmt = avalAmt;
    }

    /**
     * @return the yearTerm
     */
    public String getYearTerm() {
        return yearTerm;
    }

    /**
     * @param yearTerm
     *            the yearTerm to set
     */
    public void setYearTerm(String yearTerm) {
        this.yearTerm = yearTerm;
    }

    /**
     * @return the slipNo
     */
    public String getSlipNo() {
        return slipNo;
    }

    /**
     * @param slipNo
     *            the slipNo to set
     */
    public void setSlipNo(String slipNo) {
        this.slipNo = slipNo;
    }

    /**
     * @return the curCod
     */
    public String getCurCod() {
        return curCod;
    }

    /**
     * @param curCod
     *            the curCod to set
     */
    public void setCurCod(String curCod) {
        this.curCod = curCod;
    }

    /**
     * @return the actSts
     */
    public String getActSts() {
        return actSts;
    }

    /**
     * @param actSts
     *            the actSts to set
     */
    public void setActSts(String actSts) {
        this.actSts = actSts;
    }

    /**
     * @return the docNo
     */
    public String getDocNo() {
        return docNo;
    }

    /**
     * @param docNo
     *            the docNo to set
     */
    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    /**
     * @return the dueDate
     */
    public Calendar getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate
     *            the dueDate to set
     */
    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return the rate
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * @param rate
     *            the rate to set
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * @return the cLass
     */
    public String getcLass() {
        return cLass;
    }

    /**
     * @param cLass
     *            the cLass to set
     */
    public void setcLass(String cLass) {
        this.cLass = cLass;
    }

    /**
     * @return the actBal
     */
    public BigDecimal getActBal() {
        return actBal;
    }

    /**
     * @param actBal
     *            the actBal to set
     */
    public void setActBal(BigDecimal actBal) {
        this.actBal = actBal;
    }

    /**
     * @return the actBalNt
     */
    public BigDecimal getActBalNt() {
        return actBalNt;
    }

    /**
     * @param actBalNt
     *            the actBalNt to set
     */
    public void setActBalNt(BigDecimal actBalNt) {
        this.actBalNt = actBalNt;
    }

    /**
     * @return the glcdLoan
     */
    public String getGlcdLoan() {
        return glcdLoan;
    }

    /**
     * @param glcdLoan
     *            the glcdLoan to set
     */
    public void setGlcdLoan(String glcdLoan) {
        this.glcdLoan = glcdLoan;
    }

    /**
     * @return the oriLoanBal
     */
    public BigDecimal getOriLoanBal() {
        return oriLoanBal;
    }

    /**
     * @param oriLoanBal
     *            the oriLoanBal to set
     */
    public void setOriLoanBal(BigDecimal oriLoanBal) {
        this.oriLoanBal = oriLoanBal;
    }

    /**
     * @return the loanTerm
     */
    public String getLoanTerm() {
        return loanTerm;
    }

    /**
     * @param loanTerm
     *            the loanTerm to set
     */
    public void setLoanTerm(String loanTerm) {
        this.loanTerm = loanTerm;
    }

    /**
     * @return the loanTyp
     */
    public String getLoanTyp() {
        return loanTyp;
    }

    /**
     * @param loanTyp
     *            the loanTyp to set
     */
    public void setLoanTyp(String loanTyp) {
        this.loanTyp = loanTyp;
    }

    /**
     * @return the acnoSa
     */
    public String getAcnoSa() {
        return acnoSa;
    }

    /**
     * @param acnoSa
     *            the acnoSa to set
     */
    public void setAcnoSa(String acnoSa) {
        this.acnoSa = acnoSa;
    }

    /**
     * @return the acnoInFlag
     */
    public String getAcnoInFlag() {
        return acnoInFlag;
    }

    /**
     * @param acnoInFlag
     *            the acnoInFlag to set
     */
    public void setAcnoInFlag(String acnoInFlag) {
        this.acnoInFlag = acnoInFlag;
    }

    /**
     * @return the tdNo
     */
    public String getTdNo() {
        return tdNo;
    }

    /**
     * @param tdNo
     *            the tdNo to set
     */
    public void setTdNo(String tdNo) {
        this.tdNo = tdNo;
    }

    /**
     * @return the intCycle
     */
    public String getIntCycle() {
        return intCycle;
    }

    /**
     * @param intCycle
     *            the intCycle to set
     */
    public void setIntCycle(String intCycle) {
        this.intCycle = intCycle;
    }

    /**
     * @return the insAmt
     */
    public BigDecimal getInsAmt() {
        return insAmt;
    }

    /**
     * @param insAmt
     *            the insAmt to set
     */
    public void setInsAmt(BigDecimal insAmt) {
        this.insAmt = insAmt;
    }

    /**
     * @return the prnStrDate
     */
    public Calendar getPrnStrDate() {
        return prnStrDate;
    }

    /**
     * @param prnStrDate
     *            the prnStrDate to set
     */
    public void setPrnStrDate(Calendar prnStrDate) {
        this.prnStrDate = prnStrDate;
    }

    /**
     * @return the partRecv
     */
    public String getPartRecv() {
        return partRecv;
    }

    /**
     * @param partRecv
     *            the partRecv to set
     */
    public void setPartRecv(String partRecv) {
        this.partRecv = partRecv;
    }

    /**
     * @return the achFlag
     */
    public String getAchFlag() {
        return achFlag;
    }

    /**
     * @param achFlag
     *            the achFlag to set
     */
    public void setAchFlag(String achFlag) {
        this.achFlag = achFlag;
    }

    /**
     * @return the specSts
     */
    public String getSpecSts() {
        return specSts;
    }

    /**
     * @param specSts
     *            the specSts to set
     */
    public void setSpecSts(String specSts) {
        this.specSts = specSts;
    }

    /**
     * @return the flag302611
     */
    public String getFlag302611() {
        return flag302611;
    }

    /**
     * @param flag302611
     *            the flag302611 to set
     */
    public void setFlag302611(String flag302611) {
        this.flag302611 = flag302611;
    }

    /**
     * @return the flag302602
     */
    public String getFlag302602() {
        return flag302602;
    }

    /**
     * @param flag302602
     *            the flag302602 to set
     */
    public void setFlag302602(String flag302602) {
        this.flag302602 = flag302602;
    }

    /**
     * @return the lnTyp
     */
    public String getLnTyp() {
        return lnTyp;
    }

    /**
     * @param lnTyp
     *            the lnTyp to set
     */
    public void setLnTyp(String lnTyp) {
        this.lnTyp = lnTyp;
    }

    /**
     * @return the aType
     */
    public String getaType() {
        return aType;
    }

    /**
     * @param aType
     *            the aType to set
     */
    public void setaType(String aType) {
        this.aType = aType;
    }

    /**
     * @return the iCat
     */
    public String getiCat() {
        return iCat;
    }

    /**
     * @param iCat
     *            the iCat to set
     */
    public void setiCat(String iCat) {
        this.iCat = iCat;
    }

    /**
     * @return the stuSpNo
     */
    public String getStuSpNo() {
        return stuSpNo;
    }

    /**
     * @param stuSpNo
     *            the stuSpNo to set
     */
    public void setStuSpNo(String stuSpNo) {
        this.stuSpNo = stuSpNo;
    }

    /**
     * @return the branchNo
     */
    public String getBranchNo() {
        return branchNo;
    }

    /**
     * @param branchNo
     *            the branchNo to set
     */
    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    /**
     * @return the digitalFlg
     */
    public String getDigitalFlg() {
        return digitalFlg;
    }

    /**
     * @param digitalFlg
     *            the digitalFlg to set
     */
    public void setDigitalFlg(String digitalFlg) {
        this.digitalFlg = digitalFlg;
    }

    /**
     * @return the exFlg
     */
    public String getExFlg() {
        return exFlg;
    }

    /**
     * @param exFlg
     *            the exFlg to set
     */
    public void setExFlg(String exFlg) {
        this.exFlg = exFlg;
    }

    /**
     * @return the accName
     */
    public String getAccName() {
        return accName;
    }

    /**
     * @param accName
     *            the accName to set
     */
    public void setAccName(String accName) {
        this.accName = accName;
    }

    /**
     * @return the crossFlg
     */
    public String getCrossFlg() {
        return crossFlg;
    }

    /**
     * @param crossFlg
     *            the crossFlg to set
     */
    public void setCrossFlg(String crossFlg) {
        this.crossFlg = crossFlg;
    }

    /**
     * @return the pwdErrCnt
     */
    public String getPwdErrCnt() {
        return pwdErrCnt;
    }

    /**
     * @param pwdErrCnt
     *            the pwdErrCnt to set
     */
    public void setPwdErrCnt(String pwdErrCnt) {
        this.pwdErrCnt = pwdErrCnt;
    }

    /**
     * @return the passbookFlg
     */
    public String getPassbookFlg() {
        return passbookFlg;
    }

    /**
     * @param passbookFlg
     *            the passbookFlg to set
     */
    public void setPassbookFlg(String passbookFlg) {
        this.passbookFlg = passbookFlg;
    }

    /**
     * @return the prodInd
     */
    public String getProdInd() {
        return prodInd;
    }

    /**
     * @param prodInd
     *            the prodInd to set
     */
    public void setProdInd(String prodInd) {
        this.prodInd = prodInd;
    }

    /**
     * @return the odTotLimit
     */
    public BigDecimal getOdTotLimit() {
        return odTotLimit;
    }

    /**
     * @param odTotLimit
     *            the odTotLimit to set
     */
    public void setOdTotLimit(BigDecimal odTotLimit) {
        this.odTotLimit = odTotLimit;
    }

    /**
     * @return the odAcno
     */
    public String getOdAcno() {
        return odAcno;
    }

    /**
     * @param odAcno
     *            the odAcno to set
     */
    public void setOdAcno(String odAcno) {
        this.odAcno = odAcno;
    }

    /**
     * @return the waLnBadDebtInd
     */
    public String getWaLnBadDebtInd() {
        return waLnBadDebtInd;
    }

    /**
     * @param waLnBadDebtInd
     *            the waLnBadDebtInd to set
     */
    public void setWaLnBadDebtInd(String waLnBadDebtInd) {
        this.waLnBadDebtInd = waLnBadDebtInd;
    }

    /**
     * @return the waLnBcType
     */
    public String getWaLnBcType() {
        return waLnBcType;
    }

    /**
     * @param waLnBcType
     *            the waLnBcType to set
     */
    public void setWaLnBcType(String waLnBcType) {
        this.waLnBcType = waLnBcType;
    }

    /**
     * @return the waLnBcStag
     */
    public String getWaLnBcStag() {
        return waLnBcStag;
    }

    /**
     * @param waLnBcStag
     *            the waLnBcStag to set
     */
    public void setWaLnBcStag(String waLnBcStag) {
        this.waLnBcStag = waLnBcStag;
    }

    /**
     * @return the defaultString18
     */
    public String getDefaultString18() {
        return defaultString18;
    }

    /**
     * @param defaultString18
     *            the defaultString18 to set
     */
    public void setDefaultString18(String defaultString18) {
        this.defaultString18 = defaultString18;
    }

    /**
     * @return the odAvil1
     */
    public BigDecimal getOdAvil1() {
        return odAvil1;
    }

    /**
     * @param odAvil1
     *            the odAvil1 to set
     */
    public void setOdAvil1(BigDecimal odAvil1) {
        this.odAvil1 = odAvil1;
    }

    /**
     * @return the tdAct
     */
    public String getTdAct() {
        return tdAct;
    }

    /**
     * @param tdAct
     *            the tdAct to set
     */
    public void setTdAct(String tdAct) {
        this.tdAct = tdAct;
    }

    /**
     * @return the restLoanTerm
     */
    public String getRestLoanTerm() {
        return restLoanTerm;
    }

    /**
     * @param restLoanTerm
     *            the restLoanTerm to set
     */
    public void setRestLoanTerm(String restLoanTerm) {
        this.restLoanTerm = restLoanTerm;
    }

    /**
     * @return the arrsCtr
     */
    public String getArrsCtr() {
        return arrsCtr;
    }

    /**
     * @param arrsCtr
     *            the arrsCtr to set
     */
    public void setArrsCtr(String arrsCtr) {
        this.arrsCtr = arrsCtr;
    }

    /**
     * @return the productType
     */
    public String getProductType() {
        return productType;
    }

    /**
     * @param productType
     *            the productType to set
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * @return the loanType
     */
    public String getLoanType() {
        return loanType;
    }

    /**
     * @param loanType
     *            the loanType to set
     */
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     *            the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the loanName
     */
    public String getLoanName() {
        return loanName;
    }

    /**
     * @param loanName
     *            the loanName to set
     */
    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    /**
     * @return the isSpecialStatus
     */
    public Boolean isSpecialStatus() {
        return isBadDebt || isNegotiation;
    }

    /**
     * @param isSpecialStatus
     *            the isSpecialStatus to set
     */
    public void setIsSpecialStatus(Boolean isSpecialStatus) {
        this.isSpecialStatus = isSpecialStatus;
    }

    /**
     * @return the accountAlias
     */
    public String getAccountAlias() {
        return accountAlias;
    }

    /**
     * @param accountAlias
     *            the accountAlias to set
     */
    public void setAccountAlias(String accountAlias) {
        this.accountAlias = accountAlias;
    }

    /**
     * @return the isOverDue
     */
    public Boolean getIsOverDue() {
        return isOverDue;
    }

    /**
     * @param isOverDue
     *            the isOverDue to set
     */
    public void setIsOverDue(Boolean isOverDue) {
        this.isOverDue = isOverDue;
    }

    /**
     * @return the defaultString24
     */
    public BigDecimal getDefaultString24() {
        return defaultString24;
    }

    /**
     * @param defaultString24
     *            the defaultString24 to set
     */
    public void setDefaultString24(BigDecimal defaultString24) {
        this.defaultString24 = defaultString24;
    }

    public Boolean getSpecialStatus() {
        return isSpecialStatus;
    }

    public void setSpecialStatus(Boolean specialStatus) {
        isSpecialStatus = specialStatus;
    }

    public Boolean getOverDue() {
        return isOverDue;
    }

    public void setOverDue(Boolean overDue) {
        isOverDue = overDue;
    }

    @Override
    public String toString() {
        return "LoanAccount: {" + "acno='" + acno + '\'' + ", type='" + type + '\'' + ", avalAmt=" + avalAmt + ", yearTerm='" + yearTerm + '\'' + ", slipNo='" + slipNo + '\'' + ", curCod='" + curCod + '\'' + ", actSts='" + actSts + '\'' + ", docNo='" + docNo + '\'' + ", rate=" + rate + ", cLass='" + cLass + '\'' + ", actBal=" + actBal + ", actBalNt=" + actBalNt + ", glcdLoan='" + glcdLoan + '\'' + ", oriLoanBal=" + oriLoanBal + ", loanTerm='" + loanTerm + '\'' + ", loanTyp='" + loanTyp + '\'' + ", acnoSa='" + acnoSa + '\'' + ", acnoInFlag='" + acnoInFlag + '\'' + ", tdNo='" + tdNo + '\'' + ", intCycle='" + intCycle + '\'' + ", insAmt=" + insAmt + ", partRecv='" + partRecv + '\'' + ", achFlag='" + achFlag + '\'' + ", specSts='" + specSts + '\'' + ", flag302611='" + flag302611 + '\'' + ", flag302602='" + flag302602 + '\'' + ", lnTyp='" + lnTyp + '\'' + ", aType='" + aType + '\'' + ", iCat='" + iCat + '\'' + ", stuSpNo='" + stuSpNo + '\'' + ", branchNo='" + branchNo + '\'' + ", digitalFlg='" + digitalFlg + '\'' + ", exFlg='" + exFlg + '\'' + ", accName='" + accName + '\'' + ", crossFlg='" + crossFlg + '\'' + ", pwdErrCnt='" + pwdErrCnt + '\'' + ", passbookFlg='" + passbookFlg + '\'' + ", prodInd='" + prodInd + '\'' + ", odTotLimit=" + odTotLimit + ", odAcno='" + odAcno + '\'' + ", waLnBadDebtInd='" + waLnBadDebtInd + '\'' + ", waLnBcType='" + waLnBcType + '\'' + ", waLnBcStag='" + waLnBcStag + '\'' + ", defaultString18='" + defaultString18 + '\'' + ", defaultString24=" + defaultString24 + ", odAvil1=" + odAvil1 + ", tdAct='" + tdAct + '\'' + ", restLoanTerm='" + restLoanTerm + '\'' + ", arrsCtr='" + arrsCtr + '\'' + ", productType='" + productType + '\'' + ", loanType='" + loanType + '\'' + ", productName='" + productName + '\'' + ", loanName='" + loanName + '\'' + ", isSpecialStatus=" + isSpecialStatus + ", accountAlias='" + accountAlias + '\'' + ", isOverDue=" + isOverDue + '}';
    }

    /**
     * @return the studentLoan
     */
    public List<StudentLoanData> getStudentLoan() {
        return studentLoan;
    }

    /**
     * @param studentLoan
     *            the studentLoan to set
     */
    public void setStudentLoan(List<StudentLoanData> studentLoan) {
        this.studentLoan = studentLoan;
    }

    /**
     * 取得 14 位帳號
     * 
     * @return
     */
    public String getDisplayAcno() {
        if (this.acno != null && this.acno.length() == 16) {
            if ("00".equals(this.acno.substring(0, 2))) {
                return this.acno.substring(2);
            }
        }
        return this.acno;
    }

    /**
     * @return the isBadDebt
     */
    public Boolean getIsBadDebt() {
        return isBadDebt;
    }

    /**
     * @param isBadDebt
     *            the isBadDebt to set
     */
    public void setIsBadDebt(Boolean isBadDebt) {
        this.isBadDebt = isBadDebt;
    }

    /**
     * @return the isNegotiation
     */
    public Boolean getIsNegotiation() {
        return isNegotiation;
    }

    /**
     * @param isNegotiation
     *            the isNegotiation to set
     */
    public void setIsNegotiation(Boolean isNegotiation) {
        this.isNegotiation = isNegotiation;
    }

    /**
     * I02 新興企業貸款 只看呆帳
     * 
     * 其他帳號 呆帳 or 協商
     * 
     * @return
     */
    public boolean isSpecialStatusButI02() {
        if (StringUtils.equals(loanType, "I02")) {
            return isBadDebt;
        }
        else {
            return isBadDebt || isNegotiation;
        }
    }

}
