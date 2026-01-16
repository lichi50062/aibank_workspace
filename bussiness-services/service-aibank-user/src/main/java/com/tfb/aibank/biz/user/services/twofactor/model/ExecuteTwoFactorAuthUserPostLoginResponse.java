package com.tfb.aibank.biz.user.services.twofactor.model;

import com.tfb.aibank.biz.user.services.login.model.AbstractExecuteUserLoginResponse;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)ExecuteTwoFactorAuthUserPostLoginResponse.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/18, Benson
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "ExecuteTwoFactorAuthUserPostLoginResponse")
public class ExecuteTwoFactorAuthUserPostLoginResponse extends AbstractExecuteUserLoginResponse {

    public ExecuteTwoFactorAuthUserPostLoginResponse() {
        super();
    }
    
    public ExecuteTwoFactorAuthUserPostLoginResponse(ExecuteTwoFactorAuthUserPostLoginRequest request) {
        super();
        if (request == null) {
            return;
        }
        this.setUserVo(request.getUserVo());
        this.setCompanyVo(request.getCompanyVo());
        this.setLoginMsgRs(request.getLoginMsgRs());
        this.setCardUserProfileVo(request.getCardUserProfileVo());
        this.setMbDeviceInfoVo(request.getMbDeviceInfoVo());
        this.setUserLoginVo(request.getUserLoginVo());
        this.setNameCode(request.getNameCode());
        this.seteUid(request.geteUid());
        this.setNextTaskId(request.getNextTaskId());
        this.setNextParam(request.getNextParam());
        this.setNeedDisplayMergeContent(request.isNeedDisplayMergeContent());
        this.setMailType(request.getMailType());
        this.setSmsType(request.getSmsType());
        this.setPushType(request.getPushType());
        this.setMobileNo(request.getMobileNo());
        this.setBirthDay(request.getBirthDay());
        this.setEmail(request.getEmail());
        this.setEnableStatus(request.isEnableStatus());
        this.setLoginStatus(request.getLoginStatus());
        this.setLocationId(request.getLocationId());
        this.setChannel(request.getChannel());
        this.setPreLoginDate(request.getPreLoginDate());
        this.setSuccess(request.isSuccess());
        this.setTheSame(request.isTheSame());
        this.setBankUser(request.isBankUser());
        this.setCardEmail(request.getCardEmail());
        this.setMultiUser(request.isMultiUser());
        this.setCountryCode(request.getCountryCode());
        this.setCountryName(request.getCountryName());
        this.setUserKey(request.getUserKey());
        this.setCompanyKey(request.getCompanyKey());
        this.setShowChangeTip(request.isShowChangeTip());
        this.setInAccountCreditcardCheck(request.isInAccountCreditcardCheck());
        this.setSameBirthday(request.isSameBirthday());
        this.setLoginLogPk(request.getLoginLogPk());
        this.setTwoFactorAuth(request.isTwoFactorAuth());
        
    }
}
