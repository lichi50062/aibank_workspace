package com.tfb.aibank.chl.session.vo;

import java.util.Calendar;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB5556981SvcRsTypeVo.java
 * 
 * <p>Description:登入電文下行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/03, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB5556981Response {

    @Schema(description = "身份証字號")
    private String idno;

    @Schema(description = "戶名代碼")
    private String nameCod;

    @Schema(description = "使用者代碼")
    private String userId;

    @Schema(description = "等級")
    private String userIdLevel;

    @Schema(description = "Kyc註記")
    private String ebillCheck;

    @Schema(description = "Kyc優惠期限")
    private Calendar ebillEndDate;

    @Schema(description = "是否簽訂電子帳單")
    private String billCheck;

    @Schema(description = "電子帳單簽訂日判斷")
    private Calendar billEndDate;

    @Schema(description = "薪轉註記")
    private String salaryCheck;

    @Schema(description = "推廣註記")
    private String promoCheck;

    @Schema(description = "戶名")
    private String custName;

    @Schema(description = "電子郵件")
    private String emailAddr;

    @Schema(description = "行動電話號碼")
    private String mobileNo;

    @Schema(description = "生日")
    private Calendar birthday;

    @Schema(description = "身分別註記")
    private String idCheck;

    @Schema(description = "基金身分註記")
    private String massChk;

    @Schema(description = "單一戶名代碼註記")
    private String namcodChk;

    @Schema(description = "專業投資人註記")
    private String investCod;

    @Schema(description = "錯誤訊息代碼")
    private String error;

    @Schema(description = "ID比對註記")
    private String iderrFlg;

    @Schema(description = "前次密碼變更日期")
    private Calendar pwChgDate;

    @Schema(description = "前次使用者代碼變更日期")
    private Calendar useridChgDate;

    @Schema(description = "ＫＹＣ測驗日期")
    private Calendar ebillStrDate;

    @Schema(description = "行銀狀態")
    private String mbSts;

    @Schema(description = "OTP接收行動電話")
    private String otpMobile;

    @Schema(description = "國籍")
    private String national;

    @Schema(description = "email失聯")
    private String emailUnc;

    @Schema(description = "通訊地址失聯")
    private String addrUnc;

    /**
     * @return the idno
     */
    public String getIdno() {
        return idno;
    }

    /**
     * @param idno
     *            the idno to set
     */
    public void setIdno(String idno) {
        this.idno = idno;
    }

    /**
     * @return the nameCod
     */
    public String getNameCod() {
        return nameCod;
    }

    /**
     * @param nameCod
     *            the nameCod to set
     */
    public void setNameCod(String nameCod) {
        this.nameCod = nameCod;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the userIdLevel
     */
    public String getUserIdLevel() {
        return userIdLevel;
    }

    /**
     * @param userIdLevel
     *            the userIdLevel to set
     */
    public void setUserIdLevel(String userIdLevel) {
        this.userIdLevel = userIdLevel;
    }

    /**
     * @return the ebillCheck
     */
    public String getEbillCheck() {
        return ebillCheck;
    }

    /**
     * @param ebillCheck
     *            the ebillCheck to set
     */
    public void setEbillCheck(String ebillCheck) {
        this.ebillCheck = ebillCheck;
    }

    /**
     * @return the ebillEndDate
     */
    public Calendar getEbillEndDate() {
        return ebillEndDate;
    }

    /**
     * @param ebillEndDate
     *            the ebillEndDate to set
     */
    public void setEbillEndDate(Calendar ebillEndDate) {
        this.ebillEndDate = ebillEndDate;
    }

    /**
     * @return the billCheck
     */
    public String getBillCheck() {
        return billCheck;
    }

    /**
     * @param billCheck
     *            the billCheck to set
     */
    public void setBillCheck(String billCheck) {
        this.billCheck = billCheck;
    }

    /**
     * @return the billEndDate
     */
    public Calendar getBillEndDate() {
        return billEndDate;
    }

    /**
     * @param billEndDate
     *            the billEndDate to set
     */
    public void setBillEndDate(Calendar billEndDate) {
        this.billEndDate = billEndDate;
    }

    /**
     * @return the salaryCheck
     */
    public String getSalaryCheck() {
        return salaryCheck;
    }

    /**
     * @param salaryCheck
     *            the salaryCheck to set
     */
    public void setSalaryCheck(String salaryCheck) {
        this.salaryCheck = salaryCheck;
    }

    /**
     * @return the promoCheck
     */
    public String getPromoCheck() {
        return promoCheck;
    }

    /**
     * @param promoCheck
     *            the promoCheck to set
     */
    public void setPromoCheck(String promoCheck) {
        this.promoCheck = promoCheck;
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
     * @return the mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * @param mobileNo
     *            the mobileNo to set
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * @return the birthday
     */
    public Calendar getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     *            the birthday to set
     */
    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the idCheck
     */
    public String getIdCheck() {
        return idCheck;
    }

    /**
     * @param idCheck
     *            the idCheck to set
     */
    public void setIdCheck(String idCheck) {
        this.idCheck = idCheck;
    }

    /**
     * @return the massChk
     */
    public String getMassChk() {
        return massChk;
    }

    /**
     * @param massChk
     *            the massChk to set
     */
    public void setMassChk(String massChk) {
        this.massChk = massChk;
    }

    /**
     * @return the namcodChk
     */
    public String getNamcodChk() {
        return namcodChk;
    }

    /**
     * @param namcodChk
     *            the namcodChk to set
     */
    public void setNamcodChk(String namcodChk) {
        this.namcodChk = namcodChk;
    }

    /**
     * @return the investCod
     */
    public String getInvestCod() {
        return investCod;
    }

    /**
     * @param investCod
     *            the investCod to set
     */
    public void setInvestCod(String investCod) {
        this.investCod = investCod;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error
     *            the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the iderrFlg
     */
    public String getIderrFlg() {
        return iderrFlg;
    }

    /**
     * @param iderrFlg
     *            the iderrFlg to set
     */
    public void setIderrFlg(String iderrFlg) {
        this.iderrFlg = iderrFlg;
    }

    /**
     * @return the pwChgDate
     */
    public Calendar getPwChgDate() {
        return pwChgDate;
    }

    /**
     * @param pwChgDate
     *            the pwChgDate to set
     */
    public void setPwChgDate(Calendar pwChgDate) {
        this.pwChgDate = pwChgDate;
    }

    /**
     * @return the useridChgDate
     */
    public Calendar getUseridChgDate() {
        return useridChgDate;
    }

    /**
     * @param useridChgDate
     *            the useridChgDate to set
     */
    public void setUseridChgDate(Calendar useridChgDate) {
        this.useridChgDate = useridChgDate;
    }

    /**
     * @return the ebillStrDate
     */
    public Calendar getEbillStrDate() {
        return ebillStrDate;
    }

    /**
     * @param ebillStrDate
     *            the ebillStrDate to set
     */
    public void setEbillStrDate(Calendar ebillStrDate) {
        this.ebillStrDate = ebillStrDate;
    }

    /**
     * @return the mbSts
     */
    public String getMbSts() {
        return mbSts;
    }

    /**
     * @param mbSts
     *            the mbSts to set
     */
    public void setMbSts(String mbSts) {
        this.mbSts = mbSts;
    }

    /**
     * @return the otpMobile
     */
    public String getOtpMobile() {
        return otpMobile;
    }

    /**
     * @param otpMobile
     *            the otpMobile to set
     */
    public void setOtpMobile(String otpMobile) {
        this.otpMobile = otpMobile;
    }

    /**
     * @return the national
     */
    public String getNational() {
        return national;
    }

    /**
     * @param national
     *            the national to set
     */
    public void setNational(String national) {
        this.national = national;
    }

    /**
     * @return the emailUnc
     */
    public String getEmailUnc() {
        return emailUnc;
    }

    /**
     * @param emailUnc
     *            the emailUnc to set
     */
    public void setEmailUnc(String emailUnc) {
        this.emailUnc = emailUnc;
    }

    /**
     * @return the addrUnc
     */
    public String getAddrUnc() {
        return addrUnc;
    }

    /**
     * @param addrUnc
     *            the addrUnc to set
     */
    public void setAddrUnc(String addrUnc) {
        this.addrUnc = addrUnc;
    }
}
