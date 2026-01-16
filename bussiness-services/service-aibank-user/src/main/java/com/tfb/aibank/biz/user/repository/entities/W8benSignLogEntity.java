package com.tfb.aibank.biz.user.repository.entities;

import java.util.Date;

import com.tfb.aibank.biz.user.repository.entities.support.W8benSignLogEntityListener;

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
* <p>Description: W-8BEN簽署Log檔 Entity</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/02/15, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Entity
@Table(name = "W8BEN_SIGN_LOG")
@EntityListeners(W8benSignLogEntityListener.class)
public class W8benSignLogEntity {

    /**
     * 主檔鍵值
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "W8BEN_SIGN_LOG_SEQ")
    @SequenceGenerator(name = "W8BEN_SIGN_LOG_SEQ", sequenceName = "W8BEN_SIGN_LOG_SEQ", allocationSize = 1)
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
     * W-8BEN簽署畫面資料檔KEY
     */
    @Basic
    @Column(name = "W8BEN_INFO_LOG_KEY") // NUMBER
    private Integer w8benInfoLogKey;

    /**
     * 平台(行銀：0、網銀：1)
     */
    @Basic
    @Column(name = "PLATFORM") // VARCHAR2
    private String platform;

    /**
     * 錯誤代碼
     */
    @Basic
    @Column(name = "ERROR_CODE") // VARCHAR2
    private String errorCode;

    /**
     * 錯誤信息
     */
    @Basic
    @Column(name = "ERROR_MSG") // VARCHAR2
    private String errorMsg;

    /**
     * 交易狀態(0：成功、1：失敗)
     */
    @Basic
    @Column(name = "TX_STATUS") // VARCHAR2
    private String txStatus;

    /**
     * 交易時間
     */
    @Basic
    @Column(name = "TX_DATE") // TIMESTAMP(6)
    private Date txDate;

    /**
     * 寄發通知信的email
     */
    @Basic
    @Column(name = "EMAIL") // VARCHAR2
    private String email;

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

    public Integer getW8benInfoLogKey() {
        return w8benInfoLogKey;
    }

    public void setW8benInfoLogKey(Integer w8benInfoLogKey) {
        this.w8benInfoLogKey = w8benInfoLogKey;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getTxStatus() {
        return txStatus;
    }

    public void setTxStatus(String txStatus) {
        this.txStatus = txStatus;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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