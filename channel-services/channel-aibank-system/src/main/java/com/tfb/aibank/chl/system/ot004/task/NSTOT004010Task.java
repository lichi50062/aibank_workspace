package com.tfb.aibank.chl.system.ot004.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.devicebinding.DeviceBindingService;
import com.tfb.aibank.chl.component.devicebinding.model.MbDeviceInfo;
import com.tfb.aibank.chl.component.devicebinding.model.RetrieveDeviceBindingResponse;
import com.tfb.aibank.chl.system.ot004.model.ErrorCodeForHandShake;
import com.tfb.aibank.chl.system.ot004.model.NSTOT004010Rq;
import com.tfb.aibank.chl.system.ot004.model.NSTOT004010Rs;
import com.tfb.aibank.chl.system.ot004.model.NSTOT004Output;
import com.tfb.aibank.chl.system.ot004.model.TaskForHandShake;
import com.tfb.aibank.chl.system.ot004.service.NSTOT004Service;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.component.dic.DicCacheManager;

// @formatter:off
/**
 * @(#)NSTOT004010Task.java
 * 
 * <p>Description:010 handshake之後向後端取值 </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NSTOT004010Task extends AbstractAIBankBaseTask<NSTOT004010Rq, NSTOT004010Rs> {

    @Autowired
    private DeviceBindingService deviceBindingService;

    @Autowired
    private NSTOT004Service service;

    private NSTOT004Output dataOutput = new NSTOT004Output();

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private DicCacheManager dicCacheManager;

    @Override
    public void validate(NSTOT004010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NSTOT004010Rq rqData, NSTOT004010Rs rsData) throws ActionException {

        // 錯誤代碼取得
        List<ErrorCodeForHandShake> errorDataByAppAndImp = new ArrayList<>();
        service.getErrorDateListByAppAndImp(getLocale(), errorDataByAppAndImp);
        rsData.setErrorCodes(errorDataByAppAndImp);

        String platform = this.getRequest().getPlatform();
        String currentAppVersion = this.getAppVer();

        List<TaskForHandShake> tasks = taskCacheManager.getAllTasks().stream().map(task -> {
            TaskForHandShake taskForHandShakeVo = new TaskForHandShake();
            taskForHandShakeVo.setStatus(Objects.equals(task.getStatus(), 1) ? "Y" : "N");
            taskForHandShakeVo.setTaskNo(task.getTaskId());
            taskForHandShakeVo.setTaskTimeout(task.getTaskTimeout());
            if (StringUtils.equalsIgnoreCase(platform, "ios")) {
                // 如果平台是 iOS，则检查最小版本
                taskForHandShakeVo.setCheckMinVersion(IBUtils.compareAppVersion(StringUtils.defaultString(currentAppVersion, "0"), StringUtils.defaultString(task.getIosMinVersion(), "0")) >= 0);
                // 如果不是最小版本 傳該裝置最小支援版本
                if (!taskForHandShakeVo.isCheckMinVersion())
                    taskForHandShakeVo.setMinVersion(task.getIosMinVersion());
            }
            else {
                // 否则，检查 Android 最小版本
                taskForHandShakeVo.setCheckMinVersion(IBUtils.compareAppVersion(StringUtils.defaultString(currentAppVersion, "0"), StringUtils.defaultString(task.getAndroidMinVersion(), "0")) >= 0);
                // 如果不是最小版本 傳該裝置最小支援版本
                if (!taskForHandShakeVo.isCheckMinVersion())
                    taskForHandShakeVo.setMinVersion(task.getAndroidMinVersion());
            }
            taskForHandShakeVo.setCanBeRecord(task.getCanBeRecord());
            return taskForHandShakeVo;
        }).toList();

        rsData.setTasks(tasks);

        // 系統參數取得
        rsData.setChatbotURL(service.getValue(AIBankConstants.CHANNEL_NAME, "CHATBOT_URL"));
        rsData.setOfficialWebURL(service.getValue(AIBankConstants.CHANNEL_NAME, "OFFICIAL_WEB_URL"));
        rsData.setInternetBankingURL(service.getValue(AIBankConstants.CHANNEL_NAME, "INTERNET_BANK_URL"));
        rsData.setServerHost(service.getValue(AIBankConstants.CHANNEL_NAME, "SERVER_HOST"));

        // 綁定資訊取得
        RetrieveDeviceBindingResponse bindingRs = new RetrieveDeviceBindingResponse();
        deviceBindingService.isUserBindingLoginFirst(getDeviceIxd(), bindingRs);
        Integer loginFlag = Optional.ofNullable(bindingRs.getModel()).map(MbDeviceInfo::getLoginFlag).orElse(0);
        rsData.setLoginFirst(Objects.equals(loginFlag, 1));
        // 查看是否有開啟免登速查
        deviceBindingService.getUserQsearchFlag(getDeviceIxd(), bindingRs);
        rsData.setQsearchFlag(bindingRs.getQsearchFlag());

        if (StringUtils.equalsIgnoreCase(platform, "ios")) {
            rsData.setAppStoreURL(service.getValue(AIBankConstants.CHANNEL_NAME, "IOS_APP_STORE_URL"));
        }
        else if (StringUtils.equalsIgnoreCase(platform, "android")) {
            rsData.setAppStoreURL(service.getValue(AIBankConstants.CHANNEL_NAME, "ANDROID_APP_STORE_URL"));
        }
        else {
            logger.info("查無對應平台的商店網址。platform = {}", platform);
        }

        // 讀取系統公告
        service.getAnnouncementList(dataOutput);
        if (CollectionUtils.isNotEmpty(dataOutput.getAnnoList())) {
            rsData.setAnno(dataOutput.getAnnoList().get(0));
        }

        // 常駐功能是否開啟優惠項目 Y/N
        rsData.setNgnqu003Open(service.getValue(AIBankConstants.CHANNEL_NAME, "NGNQU003_OPEN"));
        // 台網TWID-Portal 的 URL
        rsData.setTwidPortalURL(service.getValue(AIBankConstants.CHANNEL_NAME, "TWCA_API_PORTAL_URL"));
        // 台網TWID-憑證主旨過濾條件
        rsData.setTwidCertFilter(service.getValue(AIBankConstants.CHANNEL_NAME, "TWCA_API_CERT_FILTER"));

        // 取得 google map key
        rsData.setGoogleMapKey(service.getValue(AIBankConstants.CHANNEL_NAME, "GOOGLE_MAP_KEY"));

        // 從 DIC 取值
        service.getETFDataURL(dataOutput);
        rsData.setEtfDataURL(dataOutput.getEtfDataURL());

        if (StringUtils.equalsIgnoreCase(this.systemParamCacheManager.getValue("AIBANK", "ENABLE_UPDATE_FIRST_DL_RECORD", "false"), "true")) {
            // 更新 first download record
            service.updateFirstDownloadRecord(getRequest(), rqData);
        }

        // #7615 U-19923，新增開關控制是否要儲存個人化頁面
        String enableSavePersonalResultpage = service.getValue(AIBankConstants.CHANNEL_NAME, "ENABLE_SAVE_PERSONAL_RESULTPAGE");
        enableSavePersonalResultpage = "false";
        if (StringUtils.isNotBlank(enableSavePersonalResultpage)) {
            rsData.setEnableSavePersonalResultpage(StringUtils.equalsIgnoreCase(enableSavePersonalResultpage, "true"));
        }

        // #7600 pushToken 機制
        String pushToken = Optional.ofNullable(bindingRs.getModel()).map(MbDeviceInfo::getPushToken).orElse(StringUtils.EMPTY);
        rsData.setCurrentPushToken(pushToken);

        //款功能相關InApp功能參數對照mapping
        String loanFromNoMappingValue = dicCacheManager.getValue("LOAN", "FROMNO_MAPPING");
        rsData.setLoanFromNoMapping(loanFromNoMappingValue);
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
