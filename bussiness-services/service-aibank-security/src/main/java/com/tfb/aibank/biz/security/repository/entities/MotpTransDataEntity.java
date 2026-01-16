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
package com.tfb.aibank.biz.security.repository.entities;

import java.io.Serializable;
import java.util.Date;

import com.tfb.aibank.biz.security.repository.entities.support.MotpTransDataEntityListener;
import com.tfb.aibank.biz.security.services.motp.type.MotpTxCarrierType;
import com.tfb.aibank.biz.security.services.motp.type.MotpTxStatus;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

//@formatter:off
/**
* @(#)MotpTransDataEntity.java
* 
* <p>Description:MOTP交易認證資訊</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/29, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Entity
@Table(name = "MOTP_TRANS_DATA")
@EntityListeners(MotpTransDataEntityListener.class)
public class MotpTransDataEntity implements Serializable {

    /**
     * <code>serialVersionUID</code> 的註解
     */
    private static final long serialVersionUID = 1L;

    /**
     * MOTP交易鍵值 流水編號
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOTP_TRANS_DATA_SEQ")
    @SequenceGenerator(name = "MOTP_TRANS_DATA_SEQ", sequenceName = "MOTP_TRANS_DATA_SEQ", allocationSize = 1)
    @Column(name = "MOTP_TRANS_KEY")
    private Integer motpTransKey;

    /**
     * 交易來源 MBANK/B2C
     */
    @Basic
    @Column(name = "CHANNEL")
    // @Enumerated(EnumType.STRING)
    private String channel;

    /**
     * MOTP產生方式 OFFLINE:離線載具(不留存驗證碼) PUSH_NOTIFY:推播載具
     */
    @Basic
    @Column(name = "VERIFY_CARRIER_TYPE")
    @Enumerated(EnumType.STRING)
    private MotpTxCarrierType verifyCarrierType;

    /** MOTP裝置綁定鍵值 */
    @Basic
    @Column(name = "MOTP_DEVICE_KEY")
    private Integer motpDeviceKey;

    /**
     * 交易來源裝置鍵值 網銀交易時，無裝置ID資訊
     */
    @Basic
    @Column(name = "FROM_DEVICE_UUID")
    private String fromDeviceUuid;

    /** 公司鍵值 */
    @Basic
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /** 使用者鍵值 */
    @Basic
    @Column(name = "USER_KEY")
    private Integer userKey;

    /**
     * 交易功能別 網行銀功能代碼
     */
    @Basic
    @Column(name = "TASK_ID")
    private String taskId;

    /**
     * 交易序號 五位亂數英文字串
     */
    @Basic
    @Column(name = "TX_CODE")
    private String txCode;

    /**
     * 交易參數因子 交易因子內容待定義
     */
    @Basic
    @Column(name = "TX_FACTOR")
    private String txFactor;

    /**
     * 認證狀態 INIT:交易建立MOTP初始狀態(需判斷是否已截止) VERIFY_SUCCESS:MOTP驗證成功(需判斷是否有授權時戳) VERIFY_FAIL_ONCE:MOTP驗證失敗一次 VERIFY_FAIL_TWICE:MOTP驗證失敗兩次 VERIFY_FAIL_THRICE:MOTP驗證失敗三次（交易被迫取消） EXPIRE:授權已超過有效截止時間 CANCEL:交易取消 SYSTEM_ERROR_MOTP:MOTP伺服器發生錯誤 SYSTEM_ERROR:系統發生錯誤
     */
    @Basic
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private MotpTxStatus status;

    /** 狀態錯誤描述 */
    @Basic
    @Column(name = "STATUS_DESC")
    private String statusDesc;

    /** MOTP驗證次數 */
    @Basic
    @Column(name = "AUTH_COUNT")
    private Integer authCount;

    /**
     * MOTP認證截止時間 全景API未提供(網行銀自行驗證)
     */
    @Basic
    @Column(name = "AUTH_DEADLINE")
    private Date authDeadline;

    /** 使用者驗證異動時戳 */
    @Basic
    @Column(name = "AUTH_TIMESTAMP")
    private Date authTimestamp;

    /**
     * Challenge TX_FACTOR+時間戳記
     */
    @Basic
    @Column(name = "CHALLENGE")
    private String challenge;

    /** 客戶IP */
    @Basic
    @Column(name = "CLIENT_IP")
    private String clientIp;

    /** 建立時間 */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /** 交易因子類型 */
    @Basic
    @Column(name = "TX_SEED_TYPE")
    // @Enumerated(EnumType.STRING)
    private String txSeedType;

    /**
     * @return the motpTransKey
     */
    public Integer getMotpTransKey() {
        return motpTransKey;
    }

    /**
     * @param motpTransKey
     *            the motpTransKey to set
     */
    public void setMotpTransKey(Integer motpTransKey) {
        this.motpTransKey = motpTransKey;
    }

    /**
     * @return the channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * @param channel
     *            the channel to set
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * @return the verifyCarrierType
     */
    public MotpTxCarrierType getVerifyCarrierType() {
        return verifyCarrierType;
    }

    /**
     * @param verifyCarrierType
     *            the verifyCarrierType to set
     */
    public void setVerifyCarrierType(MotpTxCarrierType verifyCarrierType) {
        this.verifyCarrierType = verifyCarrierType;
    }

    /**
     * @return the motpDeviceKey
     */
    public Integer getMotpDeviceKey() {
        return motpDeviceKey;
    }

    /**
     * @param motpDeviceKey
     *            the motpDeviceKey to set
     */
    public void setMotpDeviceKey(Integer motpDeviceKey) {
        this.motpDeviceKey = motpDeviceKey;
    }

    /**
     * @return the fromDeviceUuid
     */
    public String getFromDeviceUuid() {
        return fromDeviceUuid;
    }

    /**
     * @param fromDeviceUuid
     *            the fromDeviceUuid to set
     */
    public void setFromDeviceUuid(String fromDeviceUuid) {
        this.fromDeviceUuid = fromDeviceUuid;
    }

    /**
     * @return the companyKey
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * @param companyKey
     *            the companyKey to set
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * @return the userKey
     */
    public Integer getUserKey() {
        return userKey;
    }

    /**
     * @param userKey
     *            the userKey to set
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    /**
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * @param taskId
     *            the taskId to set
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the txCode
     */
    public String getTxCode() {
        return txCode;
    }

    /**
     * @param txCode
     *            the txCode to set
     */
    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }

    /**
     * @return the txFactor
     */
    public String getTxFactor() {
        return txFactor;
    }

    /**
     * @param txFactor
     *            the txFactor to set
     */
    public void setTxFactor(String txFactor) {
        this.txFactor = txFactor;
    }

    /**
     * @return the status
     */
    public MotpTxStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(MotpTxStatus status) {
        this.status = status;
    }

    /**
     * @return the statusDesc
     */
    public String getStatusDesc() {
        return statusDesc;
    }

    /**
     * @param statusDesc
     *            the statusDesc to set
     */
    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    /**
     * @return the authCount
     */
    public Integer getAuthCount() {
        return authCount;
    }

    /**
     * @param authCount
     *            the authCount to set
     */
    public void setAuthCount(Integer authCount) {
        this.authCount = authCount;
    }

    /**
     * @return the authDeadline
     */
    public Date getAuthDeadline() {
        return authDeadline;
    }

    /**
     * @param authDeadline
     *            the authDeadline to set
     */
    public void setAuthDeadline(Date authDeadline) {
        this.authDeadline = authDeadline;
    }

    /**
     * @return the authTimestamp
     */
    public Date getAuthTimestamp() {
        return authTimestamp;
    }

    /**
     * @param authTimestamp
     *            the authTimestamp to set
     */
    public void setAuthTimestamp(Date authTimestamp) {
        this.authTimestamp = authTimestamp;
    }

    /**
     * @return the challenge
     */
    public String getChallenge() {
        return challenge;
    }

    /**
     * @param challenge
     *            the challenge to set
     */
    public void setChallenge(String challenge) {
        this.challenge = challenge;
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
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the txSeedType
     */
    public String getTxSeedType() {
        return txSeedType;
    }

    /**
     * @param txSeedType
     *            the txSeedType to set
     */
    public void setTxSeedType(String txSeedType) {
        this.txSeedType = txSeedType;
    }

}
