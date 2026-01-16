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
package com.tfb.aibank.biz.user.services.nopain;

import com.tfb.aibank.biz.user.repository.aib.entities.MbDevicePushInfoEntity;
import com.tfb.aibank.biz.user.repository.entities.CardUserProfileEntity;
import com.tfb.aibank.biz.user.repository.entities.CompanyEntity;
import com.tfb.aibank.biz.user.repository.entities.MbDeviceInfoEntity;
import com.tfb.aibank.biz.user.repository.entities.UserEntity;
import com.tfb.aibank.biz.user.repository.entities.UserLoginEntity;
import com.tfb.aibank.chl.session.vo.CardUserProfileVo;
import com.tfb.aibank.chl.session.vo.CompanyVo;
import com.tfb.aibank.chl.session.vo.MbDeviceInfoVo;
import com.tfb.aibank.chl.session.vo.UserLoginVo;
import com.tfb.aibank.chl.session.vo.UserVo;

// @formatter:off
/**
 * @(#)NoPainServerHelper.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/02, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NoPainServerHelper {

    /**
     * 複製 COMPANY
     * 
     * @param source
     * @param target
     */
    public static void copyCompanyEntity(CompanyEntity source, CompanyVo target) {
        if (source == null || target == null) {
            return;
        }

        target.setCompanyKey(source.getCompanyKey());
        target.setCompanyUid(source.getCompanyUid());
        target.setUidDup(source.getUidDup());
        target.setStatus(source.getStatus());
        target.setCompanyKind(source.getCompanyKind());
        target.setCompanyBUType(source.getCompanyBUType());
        target.setCompanyName(source.getCompanyName());
        target.setCompanyEname(source.getCompanyEname());
        target.setUaaLevel(source.getUaaLevel());
        target.setDefaultFlowSchemaKey(source.getDefaultFlowSchemaKey());
        target.setSchemaSuitOperatorKey(source.getSchemaSuitOperatorKey());
        target.setSchemaSuitCreateTime(source.getSchemaSuitCreateTime());
        target.setEstablishDate(source.getEstablishDate());
        target.setDefaultBranchId(source.getDefaultBranchId());
        target.setTel(source.getTel());
        target.setFax(source.getFax());
        target.setMobile(source.getMobile());
        target.setEmails(source.getEmails());
        target.setRetryFlag(source.getRetryFlag());
        target.setLoginFlag(source.getLoginFlag());
        target.setSalaryFlag(source.getSalaryFlag());
        target.setOnlinePayeeFlag(source.getOnlinePayeeFlag());
        target.setRoot1TxFlag(source.getRoot1TxFlag());
        target.setRoot2TxFlag(source.getRoot2TxFlag());
        target.setPayerAccountFlag(source.getPayerAccountFlag());
        target.setPayeeAccountFlag(source.getPayeeAccountFlag());
        target.setFaxFlag(source.getFaxFlag());
        target.setTwAtmFlag(source.getTwAtmFlag());
        target.setTwRemitFlag(source.getTwRemitFlag());
        target.setTwFxmlFlag(source.getTwFxmlFlag());
        target.setTwPayeeFlag(source.getTwPayeeFlag());
        target.setTwAmtQuota(source.getTwAmtQuota());
        target.setTwInterRemitQuota(source.getTwInterRemitQuota());
        target.setRegisterTime(source.getRegisterTime());
        target.setCancelTime(source.getCancelTime());
        target.setLastBranchId(source.getLastBranchId());
        target.setLastEditorKey(source.getLastEditorKey());
        target.setLastManagerKey(source.getLastManagerKey());
        target.setLastUpdateTime(source.getLastUpdateTime());
        target.setQueryOnlyFlag(source.getQueryOnlyFlag());
        target.setKycRevalidated(source.getKycRevalidated());
        target.setKycRevalidatedDate(source.getKycRevalidatedDate());
        target.setMassCheck(source.getMassCheck());
        target.setCustomeAuditId(source.getCustomeAuditId());

    }

    /**
     * 複製 USER_PROFILE
     * 
     * @param source
     * @param target
     */
    public static void copyUserEntity(UserEntity source, UserVo target) {
        if (source == null || target == null) {
            return;
        }

        target.setUserKey(source.getUserKey());
        target.setCompanyKey(source.getCompanyKey());
        target.setUserUuid(source.getUserUuid());
        target.setPaxword(source.getSecret());
        target.setNickName(source.getNickName());
        target.setAvatar(source.getAvatar());
        target.setUserCname(source.getUserCname());
        target.setUserEname(source.getUserEname());
        target.setStatus(source.getStatus());
        target.setUserType(source.getUserType());
        target.setLocale(source.getLocale());
        target.setOtpType(source.getOtpType());
        target.setOtpCert(source.getOtpCert());
        target.setTel1(source.getTel1());
        target.setTel2(source.getTel2());
        target.setTel3(source.getTel3());
        target.setFax1(source.getFax1());
        target.setFax2(source.getFax2());
        target.setNameCode(source.getNameCode());
        target.setMobile(source.getMobile());
        target.setEmails(source.getEmails());
        target.setTwEditQuota(source.getTwEditQuota());
        target.setTwVerifyQuota(source.getTwVerifyQuota());
        target.setTwPassQuota(source.getTwPassQuota());
        target.setTwDailyPassQuota(source.getTwDailyPassQuota());
        target.setFxEditQuota(source.getFxEditQuota());
        target.setFxVerifyQuota(source.getFxVerifyQuota());
        target.setFxPassQuota(source.getFxPassQuota());
        target.setFxDailyPassQuota(source.getFxDailyPassQuota());
        target.setTwPassDate(source.getTwPassDate());
        target.setTwTotalPassAmt(source.getTwTotalPassAmt());
        target.setFxPassDate(source.getFxPassDate());
        target.setFxTotalPassAmt(source.getFxTotalPassAmt());
        target.setHiddenFxAccount(source.getHiddenFxAccount());
        target.setTxDate(source.getTxDate());
        target.setHiddenLoanAccount(source.getHiddenLoanAccount());
    }

    /**
     * 複製 USER_LOGIN
     * 
     * @param source
     * @param target
     */
    public static void copyLoginEntity(UserLoginEntity source, UserLoginVo target) {
        if (source == null || target == null) {
            return;
        }

        target.setUserKey(source.getUserKey());
        target.setCompanyKey(source.getCompanyKey());
        target.setStatus(source.getStatus());
        target.setPwdErrorCount(source.getPwdErrorCount());
        target.setPwdErrorTime(source.getPwdErrorTime());
        target.setPwdChangeTime(source.getPwdChangeTime());
        target.setPwdForceChange(source.getPwdForceChange());
        target.setSessionId(source.getSessionId());
        target.setServerId(source.getServerId());
        target.setClientIp(source.getClientIp());
        target.setLoginTime(source.getLoginTime());
        target.setLastAccessTime(source.getLastAccessTime());
        target.setLastLoginTime(source.getLastLoginTime());
        target.setWarningMessage(source.getWarningMessage());
        target.setSignonToken(source.getSignonToken());
        target.setMainCoachMark(source.getMainCoachMark());
        target.setPmCoachMark(source.getPmCoachMark());
        target.setAcctMgrCoachMark(source.getAcctMgrCoachMark());
        target.setLoginResult(source.getLoginResult());
        target.setLastLoginResult(source.getLastLoginResult());
        target.setLastClientIp(source.getLastClientIp());
        target.setNationName(source.getNationName());
        target.setLastNationName(source.getLastNationName());
        target.setMfdShowtype(source.getMfdShowtype());
        target.setChannelId(source.getChannelId());
        target.setLastChannelId(source.getLastChannelId());
        target.setPopupTime(source.getPopupTime());

    }

    /**
     * 複製 CARD_USER_PROFILE
     * 
     * @param source
     * @param target
     */
    public static void copyCardUserEntity(CardUserProfileEntity source, CardUserProfileVo target) {
        if (source == null || target == null) {
            return;
        }

        target.setCardKey(source.getCardKey());
        target.setCompanyKey(source.getCompanyKey());
        target.setUserKey(source.getUserKey());
        target.setCardNo(source.getCardNo());
        target.setCardExpired(source.getCardExpired());
        target.setCardCvv2(source.getCardCvv2());
        target.setContractversion(source.getContractversion());
        target.setAccessLogKey(source.getAccessLogKey());
        target.setCreateTime(source.getCreateTime());
        target.setUpdateTime(source.getUpdateTime());
        target.setCardMasterFlag(source.getCardMasterFlag());
    }

    /**
     * 複製 MB_DEVICE_INFO
     * 
     * @param source
     * @param target
     */
    public static void copyMbDeviceInfoEntity(MbDeviceInfoEntity source, MbDevicePushInfoEntity pushInfo, MbDeviceInfoVo target) {
        if (source == null || pushInfo == null || target == null) {
            return;
        }

        target.setDeviceInfoKey(source.getDeviceInfoKey());
        target.setDeviceUuId(source.getDeviceUuId());
        target.setCompanyKey(source.getCompanyKey());
        target.setUserKey(source.getUserKey());
        target.setModel(source.getModel());
        target.setDevicePlatform(source.getDevicePlatform());
        target.setDevicePlatformVersion(source.getDevicePlatformVersion());
        target.setDeviceAlias(source.getDeviceAlias());
        target.setLoginFlag(source.getLoginFlag());
        target.setLoginAuthDate(source.getLoginAuthDate());

        target.setClientIp(source.getClientIp());
        target.setMsgFlag(source.getMsgFlag());
        target.setMsgPassword(source.getMsgPassword());
        target.setEnable(source.getEnable());
        target.setCreateTime(source.getCreateTime());
        target.setUpdateTime(source.getUpdateTime());
        target.setLoginType(source.getLoginType());
        target.setErrorFlag(source.getErrorFlag());
        target.setLoginPasswdType(source.getLoginPasswdType());
        target.setQsearchFlag(source.getQsearchFlag());
        target.setChgPwdUseridFlag(source.getChgPwdUseridFlag());
        target.setChgPwdUseridDate(source.getChgPwdUseridDate());
        target.setMotpFlag(source.getMotpFlag());
        target.setMotpFlagDate(source.getMotpFlagDate());
        target.setDirectEzLoginFlag(source.getDirectEzLoginFlag());
        if (pushInfo != null) {
            target.setNotifyFlag(pushInfo.getNotifyFlag());
            target.setNotifyAuthDate(pushInfo.getNotifyAuthDate());
            target.setNotifyPass(pushInfo.getNotifyPass());
            target.setPushToken(pushInfo.getPushToken());
        }
    }
}
