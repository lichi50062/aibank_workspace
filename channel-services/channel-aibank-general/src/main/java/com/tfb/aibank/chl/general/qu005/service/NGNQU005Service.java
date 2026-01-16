package com.tfb.aibank.chl.general.qu005.service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.base.model.ClientRequest;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.devicebinding.model.MbDeviceInfo;
import com.tfb.aibank.chl.component.devicebinding.model.RetrieveUserBindingResponse;
import com.tfb.aibank.chl.general.qu005.model.NGNQU005Output;
import com.tfb.aibank.chl.general.resource.dto.AppVersion;
import com.tfb.aibank.chl.general.resource.dto.ChangePasswordRecord;
import com.tfb.aibank.chl.general.resource.dto.ChangePasswordTips;
import com.tfb.aibank.chl.general.service.NGNService;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NGNQU005Service.java
 * 
 * <p>Description:CHL NGNQU005 服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/04, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NGNQU005Service extends NGNService {

    public void checkChangePwdStatus(AIBankUser aibankUser, NGNQU005Output dataOutput) throws ActionException {
        Date today = new Date();
        // 系統日期 - 六個月，作為比較基準日
        Date sixMonthsAgo = DateUtils.addMonths(today, -6);
        // 一般會員
        if (aibankUser.getCustomerType().isGeneral()) {
            Date pwChgDate = aibankUser.getPwChgDate();
            dataOutput.setPwChgDate(pwChgDate);
            if (pwChgDate == null) {
                // pwChgDate 無資料，視作半年內未變更密碼
                dataOutput.setChangePwdHalfYear(false);
            }
            else {
                // 設置半年內是否有變更密碼
                dataOutput.setChangePwdHalfYear(pwChgDate.compareTo(sixMonthsAgo) >= 0);
                // 上次變更密碼距今間隔X個月
                int month = DateUtils.getDaysBetween(pwChgDate, today) / 30;
                dataOutput.setMonth(month);
            }

            // 若半年內未變更密碼，進一步檢查是否為沿用舊密碼
            if (!dataOutput.isChangePwdHalfYear()) {
                List<ChangePasswordTips> changePasswordTipsList = userResource.getChangePasswordTips(aibankUser.getCustId(), aibankUser.getUidDup(), aibankUser.getUserId(), aibankUser.getCompanyKind());
                if (CollectionUtils.isNotEmpty(changePasswordTipsList)) {
                    ChangePasswordTips changePasswordTips = changePasswordTipsList.get(0);
                    if (changePasswordTips.getTxDate() != null) {
                        dataOutput.setKeepOldPwdHalfYear(changePasswordTips.getTxDate().compareTo(sixMonthsAgo) >= 0);
                        if (dataOutput.isKeepOldPwdHalfYear()) {
                            int month = DateUtils.getDaysBetween(changePasswordTips.getTxDate(), today) / 30;
                            dataOutput.setMonth(month);
                        }
                    }
                }
            }
        }
        // 信用卡會員
        else {
            List<ChangePasswordRecord> changePasswordRecordList = userResource.getChangePasswordRecord(aibankUser.getCustId(), aibankUser.getUidDup(), aibankUser.getUserId(), aibankUser.getCompanyKind());
            if (CollectionUtils.isNotEmpty(changePasswordRecordList)) {
                changePasswordRecordList = changePasswordRecordList.stream().filter(c -> StringUtils.equalsAny(c.getChangeItem(), "0", "2") && StringUtils.equals(c.getTxStatus(), "0")).sorted(Comparator.comparing(ChangePasswordRecord::getTxDate).reversed()).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(changePasswordRecordList)) {
                    ChangePasswordRecord changePasswordRecord = changePasswordRecordList.get(0);
                    if (changePasswordRecord.getTxDate() != null) {
                        // 設置半年內是否有變更密碼
                        dataOutput.setChangePwdHalfYear(changePasswordRecord.getTxDate().compareTo(sixMonthsAgo) >= 0);
                        // 上次變更密碼距今間隔X個月
                        int month = DateUtils.getDaysBetween(changePasswordRecord.getTxDate(), today) / 30;
                        dataOutput.setMonth(month);
                    }
                }
            }
        }
    }

    /**
     * 檢查裝置綁定資訊
     * 
     * @param aibankUser
     * @param userLocale
     * @param request
     * @param dataOutput
     */
    public void checkDeviceStatus(AIBankUser aibankUser, Locale userLocale, ClientRequest request, NGNQU005Output dataOutput) {
        RetrieveUserBindingResponse retrieveUserBinding = userResource.retrieveUserBinding(aibankUser.getCustId(), aibankUser.getUidDup(), aibankUser.getUserId(), aibankUser.getCompanyKind(), userLocale.toString());

        MbDeviceInfo mbDeviceInfo = retrieveUserBinding.getModel();
        // 有綁定裝置的資訊
        if (retrieveUserBinding.isBind()) {
            // 設置已綁定裝置
            dataOutput.setDeviceBinding(retrieveUserBinding.isBind());
            dataOutput.setDeviceName(retrieveUserBinding.getDeviceModel());

            if (mbDeviceInfo != null) {
                // 判斷是否設置 MOTP
                dataOutput.setEnableMOTP(mbDeviceInfo.getMotpFlag() != null && mbDeviceInfo.getMotpFlag() == 1);

                // 判斷是否設置生物辨識
                String currentDeviceIxd = request.getDeviceIxd();
                if (StringUtils.equals(currentDeviceIxd, mbDeviceInfo.getDeviceUuId()) && (mbDeviceInfo.getLoginFlag() != null && mbDeviceInfo.getLoginFlag() == 1) && mbDeviceInfo.getLoginPasswdType() != null) {
                    switch (mbDeviceInfo.getLoginPasswdType()) {
                    case 2:
                        dataOutput.setBiometricsDesc("Touch ID");
                        break;
                    case 3:
                        dataOutput.setBiometricsDesc("Face ID");
                        break;
                    case 4:
                        dataOutput.setBiometricsDesc(I18NUtils.getMessage("ngnqu005.biometrics")); // 生物辨識
                        break;
                    }
                    dataOutput.setEnableFIDO(StringUtils.isNotBlank(dataOutput.getBiometricsDesc()));
                }
            }
        }

        if (mbDeviceInfo != null) {
            // 判斷是否設定雙重登入驗證
            dataOutput.setTwoStepFlag(mbDeviceInfo.getTwostepFlag() != null && mbDeviceInfo.getTwostepFlag() == 1);
        }

        // 檢查APP版本
        String currentAppVer = request.getAppVer(); // 當前裝置上使用的APP版本
        dataOutput.setAppVersion(currentAppVer);
        AppVersion appVersion = null;
        String platform = request.getPlatform();
        if (StringUtils.equalsAnyIgnoreCase(platform, "ios", "android")) {
            appVersion = informationResource.getAppVersion(platform.toUpperCase());
            if (appVersion != null) {
                dataOutput.setDbVersion(appVersion.getAppVer());
                dataOutput.setAppIsLatestVersion(IBUtils.compareAppVersion(currentAppVer, dataOutput.getDbVersion()) >= 0);
            }
        }

        // 設置「手機作業系統」資訊
        dataOutput.setMobileOS(new StringBuilder(0).append(getDisplay(request.getPlatform())).append(" ").append(request.getVersion()).toString());

    }

    /**
     * OS會顯示ios或android，請調整為iOS、Android
     * 
     * @param platform
     * @return
     */
    private String getDisplay(String platform) {
        String mobileOS = StringUtils.trimToEmptyEx(platform);
        if (StringUtils.equalsIgnoreCase(platform, "ios")) {
            mobileOS = "iOS";
        }
        else if (StringUtils.equalsIgnoreCase(platform, "android")) {
            mobileOS = "Android";
        }
        return mobileOS;
    }
}
