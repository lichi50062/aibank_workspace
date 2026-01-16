package com.tfb.aibank.biz.user.services.depositassets.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.tfb.aibank.component.accountinfo.AccountInfo;

import io.swagger.v3.oas.annotations.media.Schema;
import tw.com.ibm.mf.eb.EB555692RepeatType;

// @formatter:off
/**
 * @(#)EB555692Repeat.java
 * 
 * <p>Description:EB555692 網路銀行歸戶資產查詢 API下行欄位.</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/03, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "網路銀行歸戶資產查詢 Repeat")
public class EB555692Repeat {

    public EB555692Repeat() {
        // default constructor
    }

    public EB555692Repeat(EB555692RepeatType repeatType, AccountInfo accountInfo, Set<String> alertAccounts) {
        this.acno = repeatType.getACNO();
        this.acnoSa = repeatType.getACNOSA();
        this.nameCode = repeatType.getNAMECODE();
        this.prodCode = repeatType.getPRODCODE();
        this.prodSubCode = repeatType.getPRODSUBCODE();
        this.curCod = repeatType.getCURCOD();
        this.avalAmt = repeatType.getAVALAMT();
        this.lnTyp = repeatType.getLNTYP();
        this.waLnBadDebtInd = repeatType.getWALNBADDEBTIND();
        this.waLnBcType = repeatType.getWALNBCTYPE();
        this.waLnBcStag = repeatType.getWALNBCSTAG();
        this.alertcount = repeatType.getAlertcount();
        this.derivative = repeatType.getDerivative();
        this.swindleCount = repeatType.getSWINDLECOUNT();
        this.declineCount = repeatType.getDECLINECOUNT();
        this.othersCount = repeatType.getOTHERSCOUNT();
        this.noOfStops = repeatType.getNOOFSTOPS();
        this.noOfStopWdl = repeatType.getNOOFSTOPWDL();
        this.futuresDebit = repeatType.getFUTURESDEBIT();
        this.stockOmniAcc = repeatType.getSTOCKOMNIACC();
        this.complexAcc = repeatType.getCOMPLEXACC();
        this.osuBusiness = repeatType.getOSUBUSINESS();
        this.enqAgree = repeatType.getENQAGREE();
        this.stockEnq = repeatType.getSTOCKENQ();
        this.curAmt = repeatType.getCURAMT();
        this.projno = repeatType.getPROJNO();
        if (Objects.nonNull(accountInfo)) {
            this.type = accountInfo.getAccountType();
            this.subType = accountInfo.getAccountSubType();
        }
        if (CollectionUtils.isNotEmpty(alertAccounts) && !"32,50".contains(this.type)) {
            if (alertAccounts.contains(acnoSa)) {
                this.alertAccount = true;
            }
        }
    }

    /**
     * 帳戶類型 由 prodCode, prodSubCode 比對DB Table [ACCOUNT_INFO] 後取得的值
     */
    @Schema(description = "帳戶類型")
    private String type;
    @Schema(description = "帳戶子類型")
    private String subType;
    @Schema(description = "警示戶")
    private boolean alertAccount;
    /**
     * 帳號
     */
    @Schema(description = "帳號")
    private String acno;
    /**
     * 帳號參照
     */
    @Schema(description = "帳號參照")
    private String acnoSa;
    /**
     * 歸戶註記
     */
    @Schema(description = "歸戶註記")
    private String nameCode;
    /**
     * 產品大類
     */
    @Schema(description = "產品大類")
    private String prodCode;
    /**
     * 產品子類
     */
    @Schema(description = "產品子類")
    private String prodSubCode;
    /**
     * 幣別
     */
    @Schema(description = "幣別")
    private String curCod;
    /**
     * 可用餘額或餘額(原幣)
     */
    @Schema(description = "可用餘額或餘額(原幣)")
    private BigDecimal avalAmt;
    /**
     * 性質別
     */
    @Schema(description = "性質別")
    private String lnTyp;
    /**
     * 逾催呆註記
     */
    @Schema(description = "逾催呆註記")
    private String waLnBadDebtInd;
    /**
     * 債清註記
     */
    @Schema(description = "債清註記")
    private String waLnBcType;
    /**
     * 協商階段
     */
    @Schema(description = "協商階段")
    private String waLnBcStag;
    /**
     * 警調通報戶筆數+高風險失聯戶筆數+無效公司戶筆數(判斷>0=Y)
     */
    @Schema(description = "警調通報戶筆數+高風險失聯戶筆數+無效公司戶筆數(判斷>0=Y)")
    private BigDecimal alertcount;
    /**
     * 衍生管制戶筆數(判斷>0=Y)
     */
    @Schema(description = "衍生管制戶筆數(判斷>0=Y)")
    private BigDecimal derivative;
    /**
     * 疑似詐騙戶筆數(判斷>0=Y)
     */
    @Schema(description = "疑似詐騙戶筆數(判斷>0=Y)")
    private BigDecimal swindleCount;
    /**
     * 聯防管制戶筆數(判斷>0=Y)
     */
    @Schema(description = "聯防管制戶筆數(判斷>0=Y)")
    private BigDecimal declineCount;
    /**
     * 其他不良戶筆數(判斷>0=Y)
     */
    @Schema(description = "其他不良戶筆數(判斷>0=Y)")
    private BigDecimal othersCount;
    /**
     * 凍結筆數(判斷>0=Y)
     */
    @Schema(description = "凍結筆數(判斷>0=Y)")
    private BigDecimal noOfStops;
    /**
     * 止付筆數(判斷>0=Y)
     */
    @Schema(description = "止付筆數(判斷>0=Y)")
    private BigDecimal noOfStopWdl;
    /**
     * 期貨扣帳(判斷>0=Y)
     */
    @Schema(description = "期貨扣帳(判斷>0=Y)")
    private String futuresDebit;
    /**
     * 證券綜合戶(判斷Y=Y/其他=N)
     */
    @Schema(description = "證券綜合戶(判斷Y=Y/其他=N)")
    private String stockOmniAcc;
    /**
     * 複委託戶(判斷Y=Y/其他=N)
     */
    @Schema(description = "複委託戶(判斷Y=Y/其他=N)")
    private String complexAcc;
    /**
     * osu國際證券業務(判斷Y=Y/其他=N)
     */
    @Schema(description = "osu國際證券業務(判斷Y=Y/其他=N)")
    private String osuBusiness;
    /**
     * 查詢同意(判斷Y=Y/其他=N)
     */
    @Schema(description = "查詢同意(判斷Y=Y/其他=N)")
    private String enqAgree;
    /**
     * 證券查詢(判斷Y=Y/其他=N)
     */
    @Schema(description = "證券查詢(判斷Y=Y/其他=N)")
    private String stockEnq;
    /**
     * 帳上餘額
     */
    @Schema(description = "帳上餘額")
    private BigDecimal curAmt;
    /**
     * 專案別
     */
    @Schema(description = "專案別")
    private String projno;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAcno() {
        return acno;
    }

    public void setAcno(String acno) {
        this.acno = acno;
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getProdSubCode() {
        return prodSubCode;
    }

    public void setProdSubCode(String prodSubCode) {
        this.prodSubCode = prodSubCode;
    }

    public String getCurCod() {
        return curCod;
    }

    public void setCurCod(String curCod) {
        this.curCod = curCod;
    }

    public BigDecimal getAvalAmt() {
        return avalAmt;
    }

    public void setAvalAmt(BigDecimal avalAmt) {
        this.avalAmt = avalAmt;
    }

    public String getLnTyp() {
        return lnTyp;
    }

    public void setLnTyp(String lnTyp) {
        this.lnTyp = lnTyp;
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

    public BigDecimal getAlertcount() {
        return alertcount;
    }

    public void setAlertcount(BigDecimal alertcount) {
        this.alertcount = alertcount;
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

    public BigDecimal getCurAmt() {
        return curAmt;
    }

    public void setCurAmt(BigDecimal curAmt) {
        this.curAmt = curAmt;
    }

    public String getProjno() {
        return projno;
    }

    public void setProjno(String projno) {
        this.projno = projno;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public boolean isAlertAccount() {
        return alertAccount;
    }

    public void setAlertAccount(boolean alertAccount) {
        this.alertAccount = alertAccount;
    }

    public String getAcnoSa() {
        return acnoSa;
    }

    public void setAcnoSa(String acnoSa) {
        this.acnoSa = acnoSa;
    }
}