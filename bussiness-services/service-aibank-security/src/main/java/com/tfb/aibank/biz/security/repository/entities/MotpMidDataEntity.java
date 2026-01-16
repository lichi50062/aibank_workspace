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

import com.tfb.aibank.biz.security.repository.entities.support.MotpMidDataEntityListener;
import com.tfb.aibank.biz.security.services.mid.type.MidDataStatusType;

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

// @formatter:off
/**
 * @(#)MotpMidDataEntity.java
 * 
 * <p>Description:MOTP MID認證資料 - Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/06, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "MOTP_MID_DATA")
@EntityListeners(MotpMidDataEntityListener.class)
public class MotpMidDataEntity implements Serializable {

    private static final long serialVersionUID = -3513552103613198768L;

    /** MOTP裝置綁定鍵值 */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOTP_MID_DATA_SEQ")
    @SequenceGenerator(name = "MOTP_MID_DATA_SEQ", sequenceName = "MOTP_MID_DATA_SEQ", allocationSize = 1)
    @Column(name = "MOTP_MID_KEY")
    private Integer motpMidKey;

    /** 公司鍵值 */
    @Basic
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /** 使用者鍵值 */
    @Basic
    @Column(name = "USER_KEY")
    private Integer userKey;

    /** 行動裝置鍵值 */
    @Basic
    @Column(name = "DEVICE_INFO_KEY")
    private Integer deviceInfoKey;

    /** 行銀裝置ID */
    @Basic
    @Column(name = "DEVICE_UUID")
    private String deviceUuid;

    /** 驗證狀態 INIT/LOGIN_SUCCESS/LOGIN_FAIL/MID_AUTH/VERIFY_SUCCESS/VERIFY_FAIL */
    @Basic
    @Column(name = "MID_STATUS")
    @Enumerated(EnumType.STRING)
    private MidDataStatusType midStatus;

    /** 客戶證件號碼[身分證字號] */
    @Basic
    @Column(name = "LOGIN_MEMBER_NO")
    private String loginMemberNo;

    /** 認證電話號碼 */
    @Basic
    @Column(name = "LOGIN_MOBILE_NO")
    private String loginMobileNo;

    /** MID驗證編號 */
    @Basic
    @Column(name = "LOGIN_VERIFY_NO")
    private String loginVerifyNo;

    /** MID 交易條款版本號[帶入SYSTEM_PARAM,MOTP_MID_CURRENT_CLAUSE_VER] */
    @Basic
    @Column(name = "MID_CLAUSE_VER")
    private String midClauseVer;

    /** LOGIN執行結果 [S：執行成功 / F：執行失敗] */
    @Basic
    @Column(name = "LOGIN_RESULT_CODE")
    private String loginResultCode;

    /** LOGIN交易結果代碼 [0:成功，其他請參考錯誤代碼表] */
    @Basic
    @Column(name = "LOGIN_RETURN_CODE")
    private String loginReturnCode;

    /** LOGIN錯誤訊息 [LoginReturnCode <>0 才會有值] */
    @Basic
    @Column(name = "LOGIN_RETURN_DESC")
    private String loginReturnDesc;

    /** LOGIN通行證(90分鐘內有效) */
    @Basic
    @Column(name = "LOGIN_TOKEN")
    private String loginToken;

    /** LOGIN通行證取得時戳(UTC Time) (yyyy-MM-ddTHH:mm:ss.fffffffZ) */
    @Basic
    @Column(name = "LOGIN_TOKEN_CREATE_TIME")
    private Date loginTokenCreateTime;

    /** MID 交易序號 */
    @Basic
    @Column(name = "MID_REQ_SEQ")
    private String midReqSeq;

    /** MID 交易金鑰 */
    @Basic
    @Column(name = "MID_SESSION_KEY")
    private String midSessionKey;

    /** MID ＳＤＫ驗證代碼 */
    @Basic
    @Column(name = "MID_AUTH_CODE")
    private String midAuthCode;

    /** MID ＳＤＫ驗證描述 */
    @Basic
    @Column(name = "MID_AUTH_MSG")
    private String midAuthMsg;

    /** 驗證執行結果 [S：執行成功 / F：執行失敗] */
    @Basic
    @Column(name = "VERIFY_RESULT_CODE")
    private String verifyResultCode;

    /** 驗證結果代碼 [0:成功，其他請參考錯誤代碼表] */
    @Basic
    @Column(name = "VERIFY_RETURN_CODE")
    private String verifyReturnCode;

    /** 驗證錯誤訊息 [VerifyReturnCode <>0 才會有值] */
    @Basic
    @Column(name = "VERIFY_RETURN_DESC")
    private String verifyReturnDesc;

    /** 驗證回應序號 */
    @Basic
    @Column(name = "VERIFY_RSP_SEQ")
    private String verifyRspSeq;

    /** 驗證回應時間 */
    @Basic
    @Column(name = "VERIFY_RSP_TIME")
    private Date verifyRspTime;

    /** 驗證錯誤碼 [0:成功] */
    @Basic
    @Column(name = "VERIFY_MID_CODE")
    private String verifyMidCode;

    /** 驗證錯誤訊息 */
    @Basic
    @Column(name = "VERIFY_MID_MSG")
    private String verifyMidMsg;

    /** 建立日期 */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /** 更新日期 */
    @Basic
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /** MOTP綁定裝置鍵值 */
    @Basic
    @Column(name = "MOTP_DEVICE_KEY")
    private Integer motpDeviceKey;

    /**
     * @return the motpMidKey
     */
    public Integer getMotpMidKey() {
        return motpMidKey;
    }

    /**
     * @param motpMidKey
     *            the motpMidKey to set
     */
    public void setMotpMidKey(Integer motpMidKey) {
        this.motpMidKey = motpMidKey;
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
     * @return the deviceInfoKey
     */
    public Integer getDeviceInfoKey() {
        return deviceInfoKey;
    }

    /**
     * @param deviceInfoKey
     *            the deviceInfoKey to set
     */
    public void setDeviceInfoKey(Integer deviceInfoKey) {
        this.deviceInfoKey = deviceInfoKey;
    }

    /**
     * @return the deviceUuid
     */
    public String getDeviceUuid() {
        return deviceUuid;
    }

    /**
     * @param deviceUuid
     *            the deviceUuid to set
     */
    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    /**
     * @return the midStatus
     */
    public MidDataStatusType getMidStatus() {
        return midStatus;
    }

    /**
     * @param midStatus
     *            the midStatus to set
     */
    public void setMidStatus(MidDataStatusType midStatus) {
        this.midStatus = midStatus;
    }

    /**
     * @return the loginMemberNo
     */
    public String getLoginMemberNo() {
        return loginMemberNo;
    }

    /**
     * @param loginMemberNo
     *            the loginMemberNo to set
     */
    public void setLoginMemberNo(String loginMemberNo) {
        this.loginMemberNo = loginMemberNo;
    }

    /**
     * @return the loginMobileNo
     */
    public String getLoginMobileNo() {
        return loginMobileNo;
    }

    /**
     * @param loginMobileNo
     *            the loginMobileNo to set
     */
    public void setLoginMobileNo(String loginMobileNo) {
        this.loginMobileNo = loginMobileNo;
    }

    /**
     * @return the loginVerifyNo
     */
    public String getLoginVerifyNo() {
        return loginVerifyNo;
    }

    /**
     * @param loginVerifyNo
     *            the loginVerifyNo to set
     */
    public void setLoginVerifyNo(String loginVerifyNo) {
        this.loginVerifyNo = loginVerifyNo;
    }

    /**
     * @return the midClauseVer
     */
    public String getMidClauseVer() {
        return midClauseVer;
    }

    /**
     * @param midClauseVer
     *            the midClauseVer to set
     */
    public void setMidClauseVer(String midClauseVer) {
        this.midClauseVer = midClauseVer;
    }

    /**
     * @return the loginResultCode
     */
    public String getLoginResultCode() {
        return loginResultCode;
    }

    /**
     * @param loginResultCode
     *            the loginResultCode to set
     */
    public void setLoginResultCode(String loginResultCode) {
        this.loginResultCode = loginResultCode;
    }

    /**
     * @return the loginReturnCode
     */
    public String getLoginReturnCode() {
        return loginReturnCode;
    }

    /**
     * @param loginReturnCode
     *            the loginReturnCode to set
     */
    public void setLoginReturnCode(String loginReturnCode) {
        this.loginReturnCode = loginReturnCode;
    }

    /**
     * @return the loginReturnDesc
     */
    public String getLoginReturnDesc() {
        return loginReturnDesc;
    }

    /**
     * @param loginReturnDesc
     *            the loginReturnDesc to set
     */
    public void setLoginReturnDesc(String loginReturnDesc) {
        this.loginReturnDesc = loginReturnDesc;
    }

    /**
     * @return the loginToken
     */
    public String getLoginToken() {
        return loginToken;
    }

    /**
     * @param loginToken
     *            the loginToken to set
     */
    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    /**
     * @return the loginTokenCreateTime
     */
    public Date getLoginTokenCreateTime() {
        return loginTokenCreateTime;
    }

    /**
     * @param loginTokenCreateTime
     *            the loginTokenCreateTime to set
     */
    public void setLoginTokenCreateTime(Date loginTokenCreateTime) {
        this.loginTokenCreateTime = loginTokenCreateTime;
    }

    /**
     * @return the midReqSeq
     */
    public String getMidReqSeq() {
        return midReqSeq;
    }

    /**
     * @param midReqSeq
     *            the midReqSeq to set
     */
    public void setMidReqSeq(String midReqSeq) {
        this.midReqSeq = midReqSeq;
    }

    /**
     * @return the midSessionKey
     */
    public String getMidSessionKey() {
        return midSessionKey;
    }

    /**
     * @param midSessionKey
     *            the midSessionKey to set
     */
    public void setMidSessionKey(String midSessionKey) {
        this.midSessionKey = midSessionKey;
    }

    /**
     * @return the midAuthCode
     */
    public String getMidAuthCode() {
        return midAuthCode;
    }

    /**
     * @param midAuthCode
     *            the midAuthCode to set
     */
    public void setMidAuthCode(String midAuthCode) {
        this.midAuthCode = midAuthCode;
    }

    /**
     * @return the midAuthMsg
     */
    public String getMidAuthMsg() {
        return midAuthMsg;
    }

    /**
     * @param midAuthMsg
     *            the midAuthMsg to set
     */
    public void setMidAuthMsg(String midAuthMsg) {
        this.midAuthMsg = midAuthMsg;
    }

    /**
     * @return the verifyResultCode
     */
    public String getVerifyResultCode() {
        return verifyResultCode;
    }

    /**
     * @param verifyResultCode
     *            the verifyResultCode to set
     */
    public void setVerifyResultCode(String verifyResultCode) {
        this.verifyResultCode = verifyResultCode;
    }

    /**
     * @return the verifyReturnCode
     */
    public String getVerifyReturnCode() {
        return verifyReturnCode;
    }

    /**
     * @param verifyReturnCode
     *            the verifyReturnCode to set
     */
    public void setVerifyReturnCode(String verifyReturnCode) {
        this.verifyReturnCode = verifyReturnCode;
    }

    /**
     * @return the verifyReturnDesc
     */
    public String getVerifyReturnDesc() {
        return verifyReturnDesc;
    }

    /**
     * @param verifyReturnDesc
     *            the verifyReturnDesc to set
     */
    public void setVerifyReturnDesc(String verifyReturnDesc) {
        this.verifyReturnDesc = verifyReturnDesc;
    }

    /**
     * @return the verifyRspSeq
     */
    public String getVerifyRspSeq() {
        return verifyRspSeq;
    }

    /**
     * @param verifyRspSeq
     *            the verifyRspSeq to set
     */
    public void setVerifyRspSeq(String verifyRspSeq) {
        this.verifyRspSeq = verifyRspSeq;
    }

    /**
     * @return the verifyRspTime
     */
    public Date getVerifyRspTime() {
        return verifyRspTime;
    }

    /**
     * @param verifyRspTime
     *            the verifyRspTime to set
     */
    public void setVerifyRspTime(Date verifyRspTime) {
        this.verifyRspTime = verifyRspTime;
    }

    /**
     * @return the verifyMidCode
     */
    public String getVerifyMidCode() {
        return verifyMidCode;
    }

    /**
     * @param verifyMidCode
     *            the verifyMidCode to set
     */
    public void setVerifyMidCode(String verifyMidCode) {
        this.verifyMidCode = verifyMidCode;
    }

    /**
     * @return the verifyMidMsg
     */
    public String getVerifyMidMsg() {
        return verifyMidMsg;
    }

    /**
     * @param verifyMidMsg
     *            the verifyMidMsg to set
     */
    public void setVerifyMidMsg(String verifyMidMsg) {
        this.verifyMidMsg = verifyMidMsg;
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
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     *            the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

}
