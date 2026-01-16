package com.tfb.aibank.biz.user.repository.entities;

import java.util.Date;

import com.tfb.aibank.biz.user.repository.entities.support.W8benInfoLogEntityListener;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

//@formatter:off
/**
* @(#)W8benInfoLogEntity.java
* 
* <p>Description: W-8BEN簽署畫面資料檔 Entity</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/02/15, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Entity
@Table(name = "W8BEN_INFO_LOG")
@EntityListeners(W8benInfoLogEntityListener.class)
public class W8benInfoLogEntity {

    /**
     * 主檔鍵值
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "W8BEN_INFO_LOG_SEQ")
    @SequenceGenerator(name = "W8BEN_INFO_LOG_SEQ", sequenceName = "W8BEN_INFO_LOG_SEQ", allocationSize = 1)
    @Column(name = "MASTER_KEY") // NUMBER
    private Integer masterKey;

    /**
     * 公司鍵值
     */
    @Basic
    @Column(name = "COMPANY_KEY") // NUMBER
    private Integer companyKey;

    /**
     * 使用者鍵值
     */
    @Basic
    @Column(name = "USER_KEY") // NUMBER
    private Integer userKey;

    /**
     * 平台(行銀：0、網銀：1)
     */
    @Basic
    @Column(name = "PLATFORM") // VARCHAR2
    private String platform;

    /**
     * 文件編號
     */
    @Basic
    @Column(name = "DOC_NO") // VARCHAR2
    private String docNo;

    /**
     * 文件版本
     */
    @Basic
    @Column(name = "DOC_VER") // VARCHAR2
    private String docVer;

    /**
     * 最終受益人姓名-英文
     */
    @Basic
    @Column(name = "ENG_NAME") // VARCHAR2
    private String engName;

    /**
     * 國籍
     */
    @Basic
    @Column(name = "COUNTRY_COD") // VARCHAR2
    private String countryCod;

    /**
     * 戶籍地址
     */
    @Basic
    @Column(name = "BUS_ADDR") // VARCHAR2
    private String busAddr;

    /**
     * 現在居住地址
     */
    @Basic
    @Column(name = "CURR_ADDR") // VARCHAR2
    private String currAddr;

    /**
     * U.S. taxpayer identification number
     */
    @Basic
    @Column(name = "ITIN") // VARCHAR2
    private String itin;

    /**
     * Foreign tax identifying number
     */
    @Basic
    @Column(name = "FTIN") // VARCHAR2
    private String ftin;

    /**
     * Check if FTIN not legally required
     */
    @Basic
    @Column(name = "FTIN_CHECK") // VARCHAR2
    private String ftinCheck;

    /**
     * Reference numbers
     */
    @Basic
    @Column(name = "REFERENCE_NUM") // VARCHAR2
    private String reffrenceNum;

    /**
     * Date of birth (MM-DD-YYYY)
     */
    @Basic
    @Column(name = "BDAY") // VARCHAR2
    private String bDay;

    /**
     * 英文國家名
     */
    @Basic
    @Column(name = "ENG_COUNTRY_NAME") // VARCHAR2
    private String engCountryName;

    /**
     * 租稅協議第幾條
     */
    @Basic
    @Column(name = "TAX_AGREEMENT_NUM") // VARCHAR2
    private String taxAgreementNum;

    /**
     * 費率
     */
    @Basic
    @Column(name = "RATE") // VARCHAR2
    private String rate;

    /**
     * 收入類型說明
     */
    @Basic
    @Column(name = "INCOME_TYPE") // VARCHAR2
    private String incomeType;

    /**
     * 額外條件說明
     */
    @Basic
    @Column(name = "EXTRA_CONDITIONS") // VARCHAR2
    private String extraConditions;

    /**
     * 交易時間
     */
    @Basic
    @Column(name = "TX_DATE") // TIMESTAMP(6)
    private Date txDate;

    /**
     * 建立時間
     */
    @Basic
    @Column(name = "CREATE_TIME") // TIMESTAMP(6)
    private Date createTime;

    /**
     * CLIENT IP
     */
    @Basic
    @Column(name = "CLIENT_IP") // VARCHAR2
    private String clientIp;

    /**
     * CLIENT PORT
     */
    @Basic
    @Column(name = "CLIENT_PORT") // VARCHAR2
    private String clientPort;

    /**
     * 交易存取記錄追蹤編號
     */
    @Basic
    @Column(name = "TRACE_ID") // VARCHAR2
    private String traceId;

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getDocVer() {
        return docVer;
    }

    public void setDocVer(String docVer) {
        this.docVer = docVer;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getCountryCod() {
        return countryCod;
    }

    public void setCountryCod(String countryCod) {
        this.countryCod = countryCod;
    }

    public String getBusAddr() {
        return busAddr;
    }

    public void setBusAddr(String busAddr) {
        this.busAddr = busAddr;
    }

    public String getCurrAddr() {
        return currAddr;
    }

    public void setCurrAddr(String currAddr) {
        this.currAddr = currAddr;
    }

    public String getItin() {
        return itin;
    }

    public void setItin(String itin) {
        this.itin = itin;
    }

    public String getFtin() {
        return ftin;
    }

    public void setFtin(String ftin) {
        this.ftin = ftin;
    }

    public String getFtinCheck() {
        return ftinCheck;
    }

    public void setFtinCheck(String ftinCheck) {
        this.ftinCheck = ftinCheck;
    }

    public String getReffrenceNum() {
        return reffrenceNum;
    }

    public void setReffrenceNum(String reffrenceNum) {
        this.reffrenceNum = reffrenceNum;
    }

    public String getbDay() {
        return bDay;
    }

    public void setbDay(String bDay) {
        this.bDay = bDay;
    }

    public String getEngCountryName() {
        return engCountryName;
    }

    public void setEngCountryName(String engCountryName) {
        this.engCountryName = engCountryName;
    }

    public String getTaxAgreementNum() {
        return taxAgreementNum;
    }

    public void setTaxAgreementNum(String taxAgreementNum) {
        this.taxAgreementNum = taxAgreementNum;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public String getExtraConditions() {
        return extraConditions;
    }

    public void setExtraConditions(String extraConditions) {
        this.extraConditions = extraConditions;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getMasterKey() {
        return masterKey;
    }

    public void setMasterKey(Integer masterKey) {
        this.masterKey = masterKey;
    }

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    /**
     * @return the clientIp
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * @param clientIp
     *            the clientIp to set
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * @return the clientPort
     */
    public String getClientPort() {
        return clientPort;
    }

    /**
     * @param clientPort
     *            the clientPort to set
     */
    public void setClientPort(String clientPort) {
        this.clientPort = clientPort;
    }

    /**
     * @return the traceId
     */
    public String getTraceId() {
        return traceId;
    }

    /**
     * @param traceId
     *            the traceId to set
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

}