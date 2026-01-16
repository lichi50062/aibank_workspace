package com.tfb.aibank.chl.system.resource.dto;

import java.util.Calendar;

import com.tfb.aibank.chl.session.vo.EB5556981Response;

/**
 * // @formatter:off
/**
 * @(#)SsoLoginData.java
 * 
 * <p>Description:SSO 專用的電文物件資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/29, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class LoginData {
    
    private String idno;

    private String nameCod;

    private String userId;

    private String userIdLevel;

    private String ebillCheck;

    private Calendar ebillEndDate;

    private String billCheck;

    private Calendar billEndDate;

    private String salaryCheck;

    private String promoCheck;

    private String custName;

    private String emailAddr;

    private String mobileNo;

    private Calendar birthday;

    private String idCheck;

    private String massChk;

    private String namcodChk;

    private String investCod;

    private String error;

    private String iderrFlg;

    private Calendar pwChgDate;

    private Calendar useridChgDate;

    private Calendar ebillStrDate;

    private String mbSts;

    private String otpMobile;

    private String national;

    public LoginData() {
        super();
    }
    /**
     * 
     * @param eb5556981Response
     */
    public LoginData(EB5556981Response eb5556981Response) {
        super();
        if (eb5556981Response != null) {
            this.idno = eb5556981Response.getIdno();
            this.nameCod = eb5556981Response.getNameCod();
            this.userId = eb5556981Response.getUserId();
            this.userIdLevel = eb5556981Response.getUserIdLevel();
            this.ebillCheck = eb5556981Response.getEbillCheck();
            this.ebillEndDate = eb5556981Response.getEbillEndDate();
            this.billCheck = eb5556981Response.getBillCheck();
            this.billEndDate = eb5556981Response.getBillEndDate();
            this.salaryCheck = eb5556981Response.getSalaryCheck();
            this.promoCheck = eb5556981Response.getPromoCheck();
            this.custName = eb5556981Response.getCustName();
            this.emailAddr = eb5556981Response.getEmailAddr();
            this.mobileNo = eb5556981Response.getMobileNo();
            this.birthday = eb5556981Response.getBirthday();
            this.idCheck = eb5556981Response.getIdCheck();
            this.massChk = eb5556981Response.getMassChk();
            this.namcodChk = eb5556981Response.getNamcodChk();
            this.investCod = eb5556981Response.getInvestCod();
            this.error = eb5556981Response.getError();
            this.iderrFlg = eb5556981Response.getIderrFlg();
            this.pwChgDate = eb5556981Response.getPwChgDate();
            this.useridChgDate = eb5556981Response.getUseridChgDate();
            this.ebillStrDate = eb5556981Response.getEbillStrDate();
            this.mbSts = eb5556981Response.getMbSts();
            this.otpMobile = eb5556981Response.getOtpMobile();
            this.national = eb5556981Response.getNational();
        }
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIdLevel() {
        return userIdLevel;
    }

    public void setUserIdLevel(String userIdLevel) {
        this.userIdLevel = userIdLevel;
    }

    public String getEbillCheck() {
        return ebillCheck;
    }

    public void setEbillCheck(String ebillCheck) {
        this.ebillCheck = ebillCheck;
    }

    public Calendar getEbillEndDate() {
        return ebillEndDate;
    }

    public void setEbillEndDate(Calendar ebillEndDate) {
        this.ebillEndDate = ebillEndDate;
    }

    public String getBillCheck() {
        return billCheck;
    }

    public void setBillCheck(String billCheck) {
        this.billCheck = billCheck;
    }

    public Calendar getBillEndDate() {
        return billEndDate;
    }

    public void setBillEndDate(Calendar billEndDate) {
        this.billEndDate = billEndDate;
    }

    public String getSalaryCheck() {
        return salaryCheck;
    }

    public void setSalaryCheck(String salaryCheck) {
        this.salaryCheck = salaryCheck;
    }

    public String getPromoCheck() {
        return promoCheck;
    }

    public void setPromoCheck(String promoCheck) {
        this.promoCheck = promoCheck;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public String getIdCheck() {
        return idCheck;
    }

    public void setIdCheck(String idCheck) {
        this.idCheck = idCheck;
    }

    public String getMassChk() {
        return massChk;
    }

    public void setMassChk(String massChk) {
        this.massChk = massChk;
    }

    public String getNamcodChk() {
        return namcodChk;
    }

    public void setNamcodChk(String namcodChk) {
        this.namcodChk = namcodChk;
    }

    public String getInvestCod() {
        return investCod;
    }

    public void setInvestCod(String investCod) {
        this.investCod = investCod;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getIderrFlg() {
        return iderrFlg;
    }

    public void setIderrFlg(String iderrFlg) {
        this.iderrFlg = iderrFlg;
    }

    public Calendar getPwChgDate() {
        return pwChgDate;
    }

    public void setPwChgDate(Calendar pwChgDate) {
        this.pwChgDate = pwChgDate;
    }

    public Calendar getUseridChgDate() {
        return useridChgDate;
    }

    public void setUseridChgDate(Calendar useridChgDate) {
        this.useridChgDate = useridChgDate;
    }

    public Calendar getEbillStrDate() {
        return ebillStrDate;
    }

    public void setEbillStrDate(Calendar ebillStrDate) {
        this.ebillStrDate = ebillStrDate;
    }

    public String getMbSts() {
        return mbSts;
    }

    public void setMbSts(String mbSts) {
        this.mbSts = mbSts;
    }

    public String getOtpMobile() {
        return otpMobile;
    }

    public void setOtpMobile(String otpMobile) {
        this.otpMobile = otpMobile;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }
    
    
}
