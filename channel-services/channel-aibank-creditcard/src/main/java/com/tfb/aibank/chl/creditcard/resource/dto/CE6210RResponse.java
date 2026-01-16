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
package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;
import java.util.Date;

// @formatter:off
/**
 * @(#)CE6210RResponse.java
 * 
 * <p>Description:CE6210R 信用卡戶會員基本資料 Rs</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CE6210RResponse {
    /** 持卡人ID */
    private String cardHoldId;

    /** 中文姓名 */
    private String cardHolderName;

    /** 出生年月 */
    private Date birthDay;

    /** 性別 */
    private String sex;

    /** 住家電話區碼 */
    private String phoneHomeZip;

    /** 住家電話 */
    private String phoneHome;

    /** 公司電話區碼 */
    private String phoneCompZip;

    /** 公司電話 */
    private String phoneComp;

    /** 公司電話分機 */
    private String phoneCompExt;

    /** 手機ㄧ */
    private String phoneCell1;

    /** 手機二 */
    private String phoneCell2;

    /** 其他電話 */
    private String phoneOther;

    /** EMAIL帳號 */
    private String eMail;

    /** 是否可使用IVR */
    private String ivrFunc;

    /** 密碼 */
    private String ivrPwd1;

    /** 客戶等級 */
    private String cardHoldType;

    /** 歸戶ID */
    private String accountId;

    /** 郵遞區號 */
    private String zipCode;

    /** 帳單地址 */
    private String address;

    /** 自扣行庫別 */
    private String accountBank;

    /** 自扣行庫名稱 */
    private String accountBankName;

    /** 自扣比例 */
    private String accountAutoRate;

    /** 自扣百分比 */
    private String accountAutoPercent;

    /** 遲繳狀態 */
    private String delinquencyCode;

    /** 消費月限額 */
    private BigDecimal creditAmt;

    /** 關帳日 */
    private Date cycleDay;

    /** 銀行註記 */
    private String cardHoldTypeBank;

    /** 卡號 */
    private String cardNo;

    /** 授信控管 */
    private String cardCtrlCode;

    /** 全行歸戶P&L 數值 */
    private BigDecimal pLA01;

    /** 全行歸戶P&L 正負號 */
    private String pLS01;

    /** 尊御卡歸戶P&L 數值 */
    private BigDecimal pLA02;

    /** 尊御卡歸戶P&L 正負號 */
    private String pLS02;

    /** 客戶貢獻度 */
    private String eipLvl;

    /** 身分代碼 */
    private String idCode;

    /** 身分說明 */
    private String idExp;

    /** 最近三個月AUM( 存投保月均額 ) */
    private BigDecimal l03mAum;

    /** 最近六個月AUM( 存投保月均額 ) */
    private BigDecimal l06mAum;

    /** 最近一年AUM( 存投保月均額 ) */
    private BigDecimal l12mAum;

    /** 資產總餘額 */
    private BigDecimal totalBal;

    /** 投保比(%) */
    private BigDecimal perInsure;

    /** 查詢代碼 */
    private String ivrPwd;

    /** 長戶名 */
    private String vplnam;

    public String getCardHoldId() {
        return cardHoldId;
    }

    public void setCardHoldId(String cardHoldId) {
        this.cardHoldId = cardHoldId;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneHomeZip() {
        return phoneHomeZip;
    }

    public void setPhoneHomeZip(String phoneHomeZip) {
        this.phoneHomeZip = phoneHomeZip;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public void setPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
    }

    public String getPhoneCompZip() {
        return phoneCompZip;
    }

    public void setPhoneCompZip(String phoneCompZip) {
        this.phoneCompZip = phoneCompZip;
    }

    public String getPhoneComp() {
        return phoneComp;
    }

    public void setPhoneComp(String phoneComp) {
        this.phoneComp = phoneComp;
    }

    public String getPhoneCompExt() {
        return phoneCompExt;
    }

    public void setPhoneCompExt(String phoneCompExt) {
        this.phoneCompExt = phoneCompExt;
    }

    public String getPhoneCell1() {
        return phoneCell1;
    }

    public void setPhoneCell1(String phoneCell1) {
        this.phoneCell1 = phoneCell1;
    }

    public String getPhoneCell2() {
        return phoneCell2;
    }

    public void setPhoneCell2(String phoneCell2) {
        this.phoneCell2 = phoneCell2;
    }

    public String getPhoneOther() {
        return phoneOther;
    }

    public void setPhoneOther(String phoneOther) {
        this.phoneOther = phoneOther;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getIvrFunc() {
        return ivrFunc;
    }

    public void setIvrFunc(String ivrFunc) {
        this.ivrFunc = ivrFunc;
    }

    public String getIvrPwd1() {
        return ivrPwd1;
    }

    public void setIvrPwd1(String ivrPwd1) {
        this.ivrPwd1 = ivrPwd1;
    }

    public String getCardHoldType() {
        return cardHoldType;
    }

    public void setCardHoldType(String cardHoldType) {
        this.cardHoldType = cardHoldType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    public String getAccountBankName() {
        return accountBankName;
    }

    public void setAccountBankName(String accountBankName) {
        this.accountBankName = accountBankName;
    }

    public String getAccountAutoRate() {
        return accountAutoRate;
    }

    public void setAccountAutoRate(String accountAutoRate) {
        this.accountAutoRate = accountAutoRate;
    }

    public String getAccountAutoPercent() {
        return accountAutoPercent;
    }

    public void setAccountAutoPercent(String accountAutoPercent) {
        this.accountAutoPercent = accountAutoPercent;
    }

    public String getDelinquencyCode() {
        return delinquencyCode;
    }

    public void setDelinquencyCode(String delinquencyCode) {
        this.delinquencyCode = delinquencyCode;
    }

    public BigDecimal getCreditAmt() {
        return creditAmt;
    }

    public void setCreditAmt(BigDecimal creditAmt) {
        this.creditAmt = creditAmt;
    }

    public Date getCycleDay() {
        return cycleDay;
    }

    public void setCycleDay(Date cycleDay) {
        this.cycleDay = cycleDay;
    }

    public String getCardHoldTypeBank() {
        return cardHoldTypeBank;
    }

    public void setCardHoldTypeBank(String cardHoldTypeBank) {
        this.cardHoldTypeBank = cardHoldTypeBank;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardCtrlCode() {
        return cardCtrlCode;
    }

    public void setCardCtrlCode(String cardCtrlCode) {
        this.cardCtrlCode = cardCtrlCode;
    }

    public BigDecimal getpLA01() {
        return pLA01;
    }

    public void setpLA01(BigDecimal pLA01) {
        this.pLA01 = pLA01;
    }

    public String getpLS01() {
        return pLS01;
    }

    public void setpLS01(String pLS01) {
        this.pLS01 = pLS01;
    }

    public BigDecimal getpLA02() {
        return pLA02;
    }

    public void setpLA02(BigDecimal pLA02) {
        this.pLA02 = pLA02;
    }

    public String getpLS02() {
        return pLS02;
    }

    public void setpLS02(String pLS02) {
        this.pLS02 = pLS02;
    }

    public String getEipLvl() {
        return eipLvl;
    }

    public void setEipLvl(String eipLvl) {
        this.eipLvl = eipLvl;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getIdExp() {
        return idExp;
    }

    public void setIdExp(String idExp) {
        this.idExp = idExp;
    }

    public BigDecimal getL03mAum() {
        return l03mAum;
    }

    public void setL03mAum(BigDecimal l03mAum) {
        this.l03mAum = l03mAum;
    }

    public BigDecimal getL06mAum() {
        return l06mAum;
    }

    public void setL06mAum(BigDecimal l06mAum) {
        this.l06mAum = l06mAum;
    }

    public BigDecimal getL12mAum() {
        return l12mAum;
    }

    public void setL12mAum(BigDecimal l12mAum) {
        this.l12mAum = l12mAum;
    }

    public BigDecimal getTotalBal() {
        return totalBal;
    }

    public void setTotalBal(BigDecimal totalBal) {
        this.totalBal = totalBal;
    }

    public BigDecimal getPerInsure() {
        return perInsure;
    }

    public void setPerInsure(BigDecimal perInsure) {
        this.perInsure = perInsure;
    }

    public String getIvrPwd() {
        return ivrPwd;
    }

    public void setIvrPwd(String ivrPwd) {
        this.ivrPwd = ivrPwd;
    }

    public String getVplnam() {
        return vplnam;
    }

    public void setVplnam(String vplnam) {
        this.vplnam = vplnam;
    }
}
