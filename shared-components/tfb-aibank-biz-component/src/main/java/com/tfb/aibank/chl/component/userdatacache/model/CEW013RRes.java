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
import java.util.List;

// @formatter:off
/**
 * @(#)CEW013RResponse.java
 * 
 * <p>Description:申辦信用卡客戶資料查詢下行電文 Body</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/12, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CEW013RRes implements Serializable {

    private static final long serialVersionUID = 8899947380219840428L;

    public CEW013RRes() {
        // default constructor
    }

    /** 新舊戶 */
    private String hadCreditCard;

    /** 客戶等級 */
    private String vipLevel;

    /** 中文姓名 */
    private String custName;

    /** 出生年月日 */
    private String bday;

    /** 英文名字 */
    private String engName;

    /** 身分證字號 */
    private String custNo;

    /** 婚姻狀況 */
    private String marrage;

    /** 子女人數 */
    private String childNo;

    /** 教育程度 */
    private String education;

    /** 住宅現居年數 */
    private String liveYear;

    /** 住宅擁有者 */
    private String houseOwner;

    /** 戶籍區號 */
    private String busZip;

    /** 戶籍地址-1 */
    private String busAddr1;

    /** 戶籍地址-2 */
    private String busAddr2;

    /** 現居區號 */
    private String cttZip;

    /** 通訊地址-1 */
    private String cttAddr1;

    /** 通訊地址-2 */
    private String cttAddr2;

    /** 戶籍地電話 */
    private String busTel;

    /** 現居地電話(日) */
    private String telDay;

    /** 現居地電話(夜) */
    private String telNight;

    /** 行動電話 */
    private String mobile;

    /** 帳單區號 */
    private String payZip;

    /** 帳單地址 */
    private String payAddr;

    /** e-mail信箱 */
    private String emailAddr;

    /** 公司名稱 */
    private String company;

    /** 行業別 */
    private String iduCod2;

    /** 職稱 */
    private String title;

    /** 現職年收入 */
    private String income;

    /** 其他收入 */
    private String oincome;

    /** 公司地址 */
    private String cmpAddr;

    /** 公司郵遞區號 */
    private String cmpZip;

    /** 公司電話 */
    private String cmpTel;

    /** 年資/月 */
    private String years;

    /** 前服務公司 */
    private String preCmp;

    /** 前服務公司職稱 */
    private String preTitle;

    /** 前服務公司年資/月 */
    private String preYears;

    /** 前服務公司收入(萬元) */
    private String preIncome;

    /** 自動扣繳帳號 */
    private String autoPayAcc;

    /** 資料筆數 */
    private String occur;

    /** 卡片資訊 */
    private List<CEW013RRep> txRepeats;

    public String getHadCreditCard() {
        return hadCreditCard;
    }

    public void setHadCreditCard(String hadCreditCard) {
        this.hadCreditCard = hadCreditCard;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getBday() {
        return bday;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getMarrage() {
        return marrage;
    }

    public void setMarrage(String marrage) {
        this.marrage = marrage;
    }

    public String getChildNo() {
        return childNo;
    }

    public void setChildNo(String childNo) {
        this.childNo = childNo;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getLiveYear() {
        return liveYear;
    }

    public void setLiveYear(String liveYear) {
        this.liveYear = liveYear;
    }

    public String getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(String houseOwner) {
        this.houseOwner = houseOwner;
    }

    public String getBusZip() {
        return busZip;
    }

    public void setBusZip(String busZip) {
        this.busZip = busZip;
    }

    public String getBusAddr1() {
        return busAddr1;
    }

    public void setBusAddr1(String busAddr1) {
        this.busAddr1 = busAddr1;
    }

    public String getBusAddr2() {
        return busAddr2;
    }

    public void setBusAddr2(String busAddr2) {
        this.busAddr2 = busAddr2;
    }

    public String getCttZip() {
        return cttZip;
    }

    public void setCttZip(String cttZip) {
        this.cttZip = cttZip;
    }

    public String getCttAddr1() {
        return cttAddr1;
    }

    public void setCttAddr1(String cttAddr1) {
        this.cttAddr1 = cttAddr1;
    }

    public String getCttAddr2() {
        return cttAddr2;
    }

    public void setCttAddr2(String cttAddr2) {
        this.cttAddr2 = cttAddr2;
    }

    public String getBusTel() {
        return busTel;
    }

    public void setBusTel(String busTel) {
        this.busTel = busTel;
    }

    public String getTelDay() {
        return telDay;
    }

    public void setTelDay(String telDay) {
        this.telDay = telDay;
    }

    public String getTelNight() {
        return telNight;
    }

    public void setTelNight(String telNight) {
        this.telNight = telNight;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPayZip() {
        return payZip;
    }

    public void setPayZip(String payZip) {
        this.payZip = payZip;
    }

    public String getPayAddr() {
        return payAddr;
    }

    public void setPayAddr(String payAddr) {
        this.payAddr = payAddr;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIduCod2() {
        return iduCod2;
    }

    public void setIduCod2(String iduCod2) {
        this.iduCod2 = iduCod2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getOincome() {
        return oincome;
    }

    public void setOincome(String oincome) {
        this.oincome = oincome;
    }

    public String getCmpAddr() {
        return cmpAddr;
    }

    public void setCmpAddr(String cmpAddr) {
        this.cmpAddr = cmpAddr;
    }

    public String getCmpZip() {
        return cmpZip;
    }

    public void setCmpZip(String cmpZip) {
        this.cmpZip = cmpZip;
    }

    public String getCmpTel() {
        return cmpTel;
    }

    public void setCmpTel(String cmpTel) {
        this.cmpTel = cmpTel;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getPreCmp() {
        return preCmp;
    }

    public void setPreCmp(String preCmp) {
        this.preCmp = preCmp;
    }

    public String getPreTitle() {
        return preTitle;
    }

    public void setPreTitle(String preTitle) {
        this.preTitle = preTitle;
    }

    public String getPreYears() {
        return preYears;
    }

    public void setPreYears(String preYears) {
        this.preYears = preYears;
    }

    public String getPreIncome() {
        return preIncome;
    }

    public void setPreIncome(String preIncome) {
        this.preIncome = preIncome;
    }

    public String getAutoPayAcc() {
        return autoPayAcc;
    }

    public void setAutoPayAcc(String autoPayAcc) {
        this.autoPayAcc = autoPayAcc;
    }

    public String getOccur() {
        return occur;
    }

    public void setOccur(String occur) {
        this.occur = occur;
    }

    public List<CEW013RRep> getTxRepeats() {
        return txRepeats;
    }

    public void setTxRepeats(List<CEW013RRep> txRepeats) {
        this.txRepeats = txRepeats;
    }

}
