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
package com.tfb.aibank.chl.model.account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.currencycode.CurrencyCode;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.util.AccountUtils;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)TransOutAccount.java
 * 
 * <p>Description:轉出帳號</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 * v1.1, 2024/01/03, Edward Tien
 * <ol>
 *  <li>添加帳號顯示規則</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "轉出帳號")
public class TransOutAccount implements ITransOutAccount, Serializable {

    private static final long serialVersionUID = 1L;

    /** 帳號 */
    @Schema(description = "帳號")
    private String acno;

    /** 帳戶名稱 */
    @Schema(description = "帳戶名稱")
    private String accName;

    /** 帳號類別 */
    @Schema(description = "帳號類別")
    private String type;

    /** 存單或可用餘額 */
    @Schema(description = "可用餘額")
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

    /** 限約定轉入帳號註記 */
    @Schema(description = "限約定轉入帳號註記")
    private String acnoInFlag;

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

    /** 外幣功能註記 */
    @Schema(description = "外幣功能註記")
    private String exFlg;

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

    /** LIMITUP_FLAG 提高非約轉 帳戶註記 由EB5557891.D005.EX_FLAG取得，D001實際上沒有這個欄位 */
    @Schema(description = "LIMITUP_FLAG 提高非約轉 帳戶註記")
    private String limitupFlag;

    /** DIGITAL_LIMITUP_FLAG 數三提高非約轉 帳戶註記 由EB5557891.D005.VideoFlag取得，D001實際上沒有這個欄位 */
    @Schema(description = "DIGITAL_LIMITUP_FLAG 數三提高非約轉 帳戶註記")
    private String digitalLimitupFlag;

    /** 利率 */
    @Schema(description = "利率")
    private BigDecimal intRate;

    /** 日盛88專戶 */
    @Schema(description = "日盛88專戶")
    private String happy88;

    /** 數位存款視訊註記 */
    @Schema(description = "數位存款視訊註記")
    private String videoFlg;

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

    /** 分行別 */
    @Schema(description = "分行別")
    private String branchNo;

    /** 數位存款註記 */
    @Schema(description = "數位存款註記")
    private String digitalFlg;

    // ================== 以下為擴充欄位(Biz 代入) ==============================
    /** 帳號類型 */
    @Schema(description = "帳號類型")
    private String acctType;

    /** 帳號類型 */
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
    private boolean openTransFlag;
    // ================== 以上為擴充欄位(Biz 代入) ==============================

    // ================== 以下為擴充欄位 ==============================

    /** 銀行代碼，EB5557891(歸戶帳號電文)，所有帳號都是自行 */
    @Schema(description = "銀行代碼")
    private String bankNo = AIBankConstants.TFB_BANK_CODE;

    /** 帳號暱稱 */
    @Schema(description = "帳號暱稱")
    private String acctNickName;

    /** 帳號識別鍵值 */
    @Schema(description = "帳號識別鍵值")
    private String accountKey;

    /** 幣別名稱 */
    @Schema(description = "幣別名稱")
    private String curName;

    /** 分行名稱 */
    @Schema(description = "分行名稱")
    private String branchName;

    /** 約定轉入帳號清單 */
    @Schema(description = "約定轉入帳號清單")
    private List<AgreedInAccount> agreedInAccounts;

    /** 常用帳號清單 */
    @Schema(description = "常用帳號清單")
    private List<FavoriteAccount> favoriteAccounts;

    /** 是否為台幣一本萬利 (科目別168且幣別是台幣調整為產品大類前三位是162、163且產品子類以01結尾) */
    @Schema(description = "是否為台幣一本萬利")
    private boolean is168TWD;

    /** 是否為一本萬利 (科目別168調整為產品大類前三位是162、163、152、153) */
    @Schema(description = "是否為一本萬利")
    private boolean is168Full;

    /** 是否為外幣一本萬利 (科目別168調整為產品大類前三位是152、153且產品子類不以01結尾 或前三位是162、163且子類以00結尾) */
    @Schema(description = "是否為外幣一本萬利")
    private boolean is168Foreign;

    /** 薪轉委託單位代號 */
    @Schema(description = "薪轉委託單位代號")
    private String payrollCode;

    // ================== 以上為擴充欄位 ==============================

    /**
     * 準備組成帳號清單所需的基礎資訊
     * 
     * @param subClazz
     */
    public void prepareAccountInfo(TransOutAccount subClazz) {
        subClazz.setAcno(getAccountId());
        subClazz.setAccName(getAccountName());
        subClazz.setAcctNickName(getAcctNickName());
        subClazz.setBankNo(getBankNo());
        subClazz.setBranchNo(getBranchNo());
        subClazz.setBranchName(getBranchName());
        subClazz.setCurCod(getCurCod());
        subClazz.setCurName(getCurName());
        subClazz.setAvalAmt(getAvalAmt());
        subClazz.setActBal(getActBal());
        subClazz.setAccountKey(getAccountKey());
        subClazz.setDisplayBranchId(getDisplayBranchId());
    }

    /** 是否限使用約定轉入帳號 */
    public boolean isNeedAgreeIn() {
        return StringUtils.equals("Y", acnoInFlag);
    }

    /** 是否為數位存款帳號 */
    public boolean isDigital() {
        return StringUtils.equalsAny(digitalFlg, "0", "1", "2", "3", "4", "5");
    }

    /** 是否為數位一類存款帳號 */
    public boolean isDigital1() {
        return StringUtils.equalsAny(digitalFlg, "0", "1");
    }

    /** 是否為數位二類存款帳號 */
    public boolean isDigital2() {
        return StringUtils.equalsAny(digitalFlg, "2", "3");
    }

    /** 是否為數位三類存款帳號 */
    public boolean isDigital3() {
        return StringUtils.equalsAny(digitalFlg, "4", "5");
    }

    /** 是否為數位三類(自行信用卡驗證)存款帳號 */
    public boolean isDigital3Self() {
        return StringUtils.equals(digitalFlg, "4");
    }

    /** 是否為數位三類(他行帳戶驗證)存款帳號 */
    public boolean isDigital3Other() {
        return StringUtils.equals(digitalFlg, "5");
    }

    /** 是否限定只能做約轉交易 true=只能做約轉交易 false=不限定 */
    @Override
    public boolean isNonAgreedFlag() {
        return StringUtils.isY(getAcnoInFlag());
    }

    @Override
    public boolean isIncreaseFlag() {
        // 數存帳號
        if (this.isDigital()) {
            return StringUtils.isY(getVideoFlg());
        }
        // 一般帳號
        else {
            return StringUtils.isY(getExFlg());
        }
    }

    @Override
    public boolean isDigitalFlag() {
        return this.isDigital();
    }

    /** 外幣註記 Y:可執行外幣交易，EB5557891D001_RS.EX_FLG != N (若為空白，表可通過，也就是EX_FLG = ‘N’，才不能執行外幣轉帳) */
    @Override
    public boolean isForeignFlag() {
        return !StringUtils.isN(getExFlg());
    }

    @Override
    public String getAccountId() {
        return this.acno;
    }

    @Override
    public String getDisplayAccountId() {
        return AccountUtils.getDisplayAccountId(getBankNo(), getAccountId());
    }

    @Override
    public String getAccountName() {
        return this.accName;
    }

    @Override
    public String getDisplayAccountName() {
        if (StringUtils.isNotBlank(this.accName)) {
            return this.accName;
        }
        else {
            return this.branchName;
        }
    }

    /**
     * 有暱稱時回傳暱稱， 沒有時回帳號名稱
     */
    @Override
    public String getDisplayAcctNickname() {
        if (StringUtils.isNotBlank(this.acctNickName)) {
            return this.acctNickName;
        }
        return getDisplayAccountName();
    }

    @Override
    public String getAccountLabel1() {
        return new StringBuilder(0).append(getDisplayAcctNickname()).append("···").append(StringUtils.right(getAccountId(), 5)).toString();
    }

    @Override
    public String getAccountLabel2() {
        StringBuilder sb = new StringBuilder(0);
        sb.append(I18NUtils.getMessage("common.instant_balance")); // 即時餘額
        // 有涉及外幣，一律顯示幣別
        if (isForeignFlag()) {
            sb.append(" ").append(getCurName()).append(" ");
        }
        // 未涉及外幣
        else {
            // 臺幣帳號，一律用「$」
            if (StringUtils.equals(CurrencyCode.TWD, getCurCode())) {
                sb.append(StringUtils.SPACE).append("$");
            }
            else {
                sb.append(StringUtils.SPACE).append(getCurName()).append(StringUtils.SPACE);
            }
        }
        sb.append(IBUtils.formatMoneyStr(getActBal(), getCurCode()));
        return sb.toString();

    }

    @Override
    public String getAccountLabel2Invest() {
        StringJoiner joiner = new StringJoiner(" ");
        joiner.add(I18NUtils.getMessage("common.instant_balance_invest")); // 可投資餘額
        // 一律顯示幣別
        joiner.add(getCurName());
        // getOdTotLimit() 這邊取出的值已經是AvalAmt - odTotLimit
        joiner.add(IBUtils.formatMoneyStr(getOdTotLimit(), getCurCode()));
        return joiner.toString();
    }

    @Override
    public String getAccountDropdown1() {
        return new StringBuilder(0).append(getDisplayAcctNickname()).append(" ").append(getDisplayAccountId()).toString();
    }

    @Override
    public String getAccountDropdown2() {
        StringBuilder sb = new StringBuilder(0);
        sb.append(I18NUtils.getMessage("common.instant_balance")); // 即時餘額
        // 臺幣帳號，幣別用「$」
        if (StringUtils.equals(CurrencyCode.TWD, getCurCode())) {
            sb.append(StringUtils.SPACE).append("$");
        }
        // 外幣帳號，顯示幣別名稱
        else {
            sb.append(StringUtils.SPACE).append(getCurName()).append(StringUtils.SPACE);
        }
        sb.append(IBUtils.formatMoneyStr(getActBal(), getCurCode()));
        return sb.toString();
    }

    @Override
    public String getDisplayAccount1() {
        return new StringBuilder(0).append(getDisplayAcctNickname()).append(" ").append(getDisplayAccountId()).toString();
    }

    @Override
    public String getDisplayAccount2() {
        StringBuilder sb = new StringBuilder(0);
        sb.append(I18NUtils.getMessage("common.balance")); // 餘額
        // 有涉及外幣，一律顯示幣別
        if (isForeignFlag()) {
            sb.append(StringUtils.SPACE).append(getCurName()).append(StringUtils.SPACE);
        }
        // 未涉及外幣
        else {
            // 臺幣帳號，一律用「$」
            if (StringUtils.equals(CurrencyCode.TWD, getCurCode())) {
                sb.append(StringUtils.SPACE).append("$");
            }
            else {
                sb.append(StringUtils.SPACE).append(getCurName()).append(StringUtils.SPACE);
            }
        }
        sb.append(IBUtils.formatMoneyStr(getAvalAmt(), getCurCode()));
        return sb.toString();

    }

    @Override
    public String getCurCode() {
        return this.curCod;
    }

    @Override
    public String getProdCode() {
        return this.atype;
    }

    @Override
    public String getProdSubCode() {
        return this.icat;
    }

    public String getAcno() {
        return acno;
    }

    public void setAcno(String acno) {
        this.acno = acno;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getAcctTypeName() {
        return acctTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.acctTypeName = accountTypeName;
    }

    public BigDecimal getAvalAmt() {
        return avalAmt;
    }

    public void setAvalAmt(BigDecimal avalAmt) {
        this.avalAmt = avalAmt;
    }

    public String getCurCod() {
        return curCod;
    }

    public void setCurCod(String curCod) {
        this.curCod = curCod;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
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

    @Override
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

    @Override
    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    @Override
    public String getAcctDescription() {
        return accountDesc;
    }

    public void setAccountDesc(String accountDesc) {
        this.accountDesc = accountDesc;
    }

    public void setAccType(String accountType) {
        this.acctType = accountType;
    }

    @Override
    public String getAcctType() {
        return acctType;
    }

    @Override
    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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

    public String getActSts() {
        return actSts;
    }

    public void setActSts(String actSts) {
        this.actSts = actSts;
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

    public String getAcnoInFlag() {
        return acnoInFlag;
    }

    public void setAcnoInFlag(String acnoInFlag) {
        this.acnoInFlag = acnoInFlag;
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

    public String getStuSpNo() {
        return stuSpNo;
    }

    public void setStuSpNo(String stuSpNo) {
        this.stuSpNo = stuSpNo;
    }

    public String getExFlg() {
        return exFlg;
    }

    public void setExFlg(String exFlg) {
        this.exFlg = exFlg;
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

    public String getLimitupFlag() {
        return limitupFlag;
    }

    public void setLimitupFlag(String limitupFlag) {
        this.limitupFlag = limitupFlag;
    }

    public String getDigitalLimitupFlag() {
        return digitalLimitupFlag;
    }

    public void setDigitalLimitupFlag(String digitalLimitupFlag) {
        this.digitalLimitupFlag = digitalLimitupFlag;
    }

    public BigDecimal getIntRate() {
        return intRate;
    }

    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    public String getHappy88() {
        return happy88;
    }

    public void setHappy88(String happy88) {
        this.happy88 = happy88;
    }

    public String getVideoFlg() {
        return videoFlg;
    }

    public void setVideoFlg(String videoFlg) {
        this.videoFlg = videoFlg;
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

    @Override
    public List<AgreedInAccount> getAgreedInAccounts() {
        return agreedInAccounts;
    }

    public void setAgreedInAccounts(List<AgreedInAccount> agreedInAccounts) {
        this.agreedInAccounts = agreedInAccounts;
    }

    public List<FavoriteAccount> getFavoriteAccounts() {
        return favoriteAccounts;
    }

    public void setFavoriteAccounts(List<FavoriteAccount> favoritePayeeAccounts) {
        this.favoriteAccounts = favoritePayeeAccounts;
    }

    public String getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }

    @Override
    public String getAcctNickName() {
        return acctNickName;
    }

    public void setAcctNickName(String acctNickName) {
        this.acctNickName = acctNickName;
    }

    public String getAccountDesc() {
        return accountDesc;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public void setAcctTypeName(String acctTypeName) {
        this.acctTypeName = acctTypeName;
    }

    public boolean is168Full() {
        return is168Full;
    }

    public void set168Full(boolean is168Full) {
        this.is168Full = is168Full;
    }

    public boolean is168TWD() {
        return is168TWD;
    }

    public void set168TWD(boolean is168twd) {
        is168TWD = is168twd;
    }

    public boolean is168Foreign() {
        return is168Foreign;
    }

    public void set168Foreign(boolean is168Foreign) {
        this.is168Foreign = is168Foreign;
    }

    @Override
    public String getAcctSubType() {
        return acctSubType;
    }

    public void setAcctSubType(String acctSubType) {
        this.acctSubType = acctSubType;
    }

    @Override
    public boolean isTransOutFlag() {
        return transOutFlag;
    }

    public void setTransOutFlag(boolean transOutFlag) {
        this.transOutFlag = transOutFlag;
    }

    @Override
    public boolean isOpenTransFlag() {
        return openTransFlag;
    }

    public void setOpenTransFlag(boolean openTransFlag) {
        this.openTransFlag = openTransFlag;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getPayrollCode() {
        return payrollCode;
    }

    public void setPayrollCode(String payrollCode) {
        this.payrollCode = payrollCode;
    }
}
