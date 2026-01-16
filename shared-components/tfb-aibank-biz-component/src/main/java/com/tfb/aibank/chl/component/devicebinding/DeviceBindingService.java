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
package com.tfb.aibank.chl.component.devicebinding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.component.devicebinding.model.CheckUserDeviceStatusCondition;
import com.tfb.aibank.chl.component.devicebinding.model.CheckUserDeviceStatusResult;
import com.tfb.aibank.chl.component.devicebinding.model.QuickSearchResponse;
import com.tfb.aibank.chl.component.devicebinding.model.RetrieveDeviceBindingResponse;
import com.tfb.aibank.chl.component.devicebinding.model.RetrieveMultiUserBindingResponse;
import com.tfb.aibank.chl.component.devicebinding.model.RetrieveUserBindingResponse;
import com.tfb.aibank.chl.component.devicebinding.model.ValidateDeviceBindingCondition;
import com.tfb.aibank.chl.component.devicebinding.model.ValidateDeviceBindingResult;
import com.tfb.aibank.chl.component.devicebinding.type.UserDeviceBindStatusType;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.error.AIBankErrorCode;

// @formatter:off
/**
 * @(#)DeviceBindingService.java
 * 
 * <p>Description:使用者與裝置綁定狀態</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/19, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class DeviceBindingService {

    @Autowired
    private DeviceBindingUserResource userResource;
    @Autowired
    private UserDataCacheService userDataCacheService;

    /**
     * 檢查使用者與裝置綁定狀態
     * 
     * @param condition
     * @param result
     * @throws ActionException
     */
    public void checkUserDeviceStatus(CheckUserDeviceStatusCondition condition, CheckUserDeviceStatusResult result) {

        AIBankUser loginUser = condition.getLoginUser();

        // 取得使用者綁定資料
        RetrieveUserBindingResponse userBinding = userResource.retrieveUserBinding(loginUser.getCustId(), loginUser.getUidDup(), loginUser.getUserId(), loginUser.getCompanyKind(), condition.getLocale());
        result.setUserBinding(userBinding);
        // 取得裝置綁定資料
        RetrieveDeviceBindingResponse deviceBinding = userResource.retrieveDeviceBinding(condition.getDeviceIxd());
        result.setDeviceBinding(deviceBinding);

        // 使用者未綁定
        if (!userBinding.isBind()) {

            if (deviceBinding.isBind()) {
                // 使用者未綁定任何裝置，且此裝置已被其他使用者綁定
                result.setStatus(UserDeviceBindStatusType.UNBIND_DEVICE_NOT_AVAILABLE);
                return;
            }

            // 使用者未綁定任何裝置，可進行綁定
            result.setStatus(UserDeviceBindStatusType.UNBIND);
            return;
        }

        // 比對使用者ID與目前裝置綁定之ID是否一致
        if (deviceBinding.isBind() && !StringUtils.equals(deviceBinding.getCustId(), loginUser.getCustId())) {
            // 使用者已綁定，但綁定裝置與目前使用裝置不同，且此裝置已被其他使用者綁定
            result.setStatus(UserDeviceBindStatusType.BINDED_DEVICE_NOT_AVAILABLE);
            return;
        }

        // 比對使用者綁定之裝置ID與目前裝置ID是否一致
        if (!StringUtils.equals(userBinding.getModel().getDeviceUuId(), condition.getDeviceIxd())) {
            // 使用者已綁定，但綁定裝置與目前使用裝置不同，且此裝置未綁定
            result.setStatus(UserDeviceBindStatusType.BINDED_DEVICE_NOT_MATCH);
            return;
        }

        // 使用者已綁定目前使用裝置，可繼續執行
        result.setStatus(UserDeviceBindStatusType.BINDED);
    }

    /**
     * 登入時使用
     */
    public void checkUserDeviceStatusForLogin(CheckUserDeviceStatusCondition condition, CheckUserDeviceStatusResult result) {

        // 取得使用者綁定資料
        RetrieveUserBindingResponse userBinding = userResource.retrieveUserBinding(condition.getLoginUser().getCustId(), condition.getLoginUser().getUidDup(), condition.getLoginUser().getUserId(), condition.getLoginUser().getCompanyKind(), condition.getLocale());
        result.setUserBinding(userBinding);
        // 取得裝置綁定資料
        RetrieveDeviceBindingResponse deviceBinding = userResource.retrieveDeviceBinding(condition.getDeviceIxd());
        result.setDeviceBinding(deviceBinding);

        // 裝置有綁定狀態
        if (deviceBinding.isBind()) {
            // 是否為本人
            if (StringUtils.equals(deviceBinding.getCustId(), condition.getLoginUser().getCustId())) {
                // 且目前綁定身份為信用卡會員
                if (deviceBinding.getModel().getLoginType().equals(1)) {
                    // 且目前綁定身份為信用卡會員
                    result.setStatus(UserDeviceBindStatusType.UNBIND);
                }
                else {
                    result.setStatus(UserDeviceBindStatusType.BINDED);
                }
            }
            else {
                result.setStatus(UserDeviceBindStatusType.BINDED);
            }
        }
        else {
            // 無人綁定，可綁
            result.setStatus(UserDeviceBindStatusType.UNBIND);
        }

    }

    /**
     * 客戶是否開啟快登
     * 
     * @param custId
     * @param userId
     * @param companyKind
     * @param output
     */
    public void isUserBindingLoginFirst(String deviceUuid, RetrieveDeviceBindingResponse output) {
        // 取得使用者綁定資料
        RetrieveDeviceBindingResponse userBindingRs = userResource.retrieveDeviceBinding(deviceUuid);
        output.setModel(userBindingRs.getModel());
    }

    /**
     * 檢查是否可以做裝置綁定
     * 
     * @param condition
     * @param result
     * @throws ActionException
     */
    public void validateDeviceBinding(ValidateDeviceBindingCondition condition, ValidateDeviceBindingResult result) throws ActionException {

        AIBankUser loginUser = condition.getLoginUser();
        CheckUserDeviceStatusResult checkUserDeviceStatusResult = condition.getCheckUserDeviceStatusResult();

        // 使用者已綁定目前使用裝置，可繼續執行
        if (checkUserDeviceStatusResult.getStatus().isBind()) {
            result.setValid(true);
            return;
        }

        // 且此裝置已被其他使用者綁定
        if (checkUserDeviceStatusResult.getDeviceBinding().isBind() && !StringUtils.equals(checkUserDeviceStatusResult.getDeviceBinding().getCustId(), loginUser.getCustId())) {
            // 身分別
            int loginType = ConvertUtils.integer2Int(checkUserDeviceStatusResult.getDeviceBinding().getModel().getLoginType(), 0);
            String loginTypeDesc = "";
            if (loginType == 0) {
                // 一般會員
                loginTypeDesc = I18NUtils.getMessage("task.permission.role.general");
            }
            else if (loginType == 1) {
                // 信用卡會員
                loginTypeDesc = I18NUtils.getMessage("task.permission.role.carduser");
            }
            // 身分證字號
            String custId = DataMaskUtil.maskCustId(checkUserDeviceStatusResult.getDeviceBinding().getCustId());
            // 使用者代碼
            String userId = DataMaskUtil.maskUserId(checkUserDeviceStatusResult.getDeviceBinding().getUserId());
            // 請先登入 {身分別} {身分證字號} ({使用者代碼}) 並解除與此裝置的綁定後，再進行設定
            throw new ActionException(AIBankErrorCode.ALREADY_BIND_BY_OTHER_USER_ERROR, loginTypeDesc, custId, userId);
        }

        // 檢核使用者是否為單一戶名
        userDataCacheService.checkIsSingleAccount(loginUser);

        // 檢核使用者是否為多使用者代碼客戶
        if (!userDataCacheService.getOpnCnFlag(loginUser)) {
            RetrieveMultiUserBindingResponse response = userResource.retrieveMultiUserBinding(loginUser.getCustId(), loginUser.getUserId(), loginUser.getCompanyKind(), loginUser.getUidDup());
            if (response.isOtherUserBind()) {
                // 身分別
                int loginType = response.getOtherUserLoginType();
                String loginTypeDesc = "";
                if (loginType == 0) {
                    // 一般會員
                    loginTypeDesc = I18NUtils.getMessage("task.permission.role.general");
                }
                else if (loginType == 1) {
                    // 信用卡會員
                    loginTypeDesc = I18NUtils.getMessage("task.permission.role.carduser");
                }
                // 身分證字號
                String custId = DataMaskUtil.maskCustId(loginUser.getCustId());
                // 使用者代碼
                String userId = DataMaskUtil.maskUserId(response.getOtherUserId());
                // 請先登入 {身分別} {身分證字號} ({使用者代碼}) 並解除與此裝置的綁定後，再進行設定
                throw new ActionException(AIBankErrorCode.ALREADY_BIND_BY_OTHER_USER_ERROR, loginTypeDesc, custId, userId);
            }
        }

        // 使用者未綁定，視為裝置綁定流程
        if (checkUserDeviceStatusResult.getStatus().isUnbind()) {
            result.setValid(false);
            return;
        }

        // 使用者已綁定，但綁定裝置與目前使用裝置不同，且此裝置未綁定，視為變更綁定流程
        if (checkUserDeviceStatusResult.getStatus().isBindedDeviceNotMatch()) {
            result.setValid(false);
            result.setChangeDevice(true);
        }

    }

    /**
     * 客戶是否開啟免登速查
     *
     * @param output
     */
    public void getUserQsearchFlag(String deviceUuid, RetrieveDeviceBindingResponse output) {
        // 取得使用者綁定資料
        QuickSearchResponse resp = userResource.getQuickSearchStatus(deviceUuid);
        String qsearchFlag = (null != resp && resp.haveQuickSearchOn()) ? StringUtils.Y : StringUtils.N;
        output.setQsearchFlag(qsearchFlag);
    }
}
