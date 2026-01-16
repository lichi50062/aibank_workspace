package com.tfb.aibank.chl.general.ot001.task;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001080Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001080Rs;
import com.tfb.aibank.chl.general.ot001.task.service.DeviceBindingCache;
import com.tfb.aibank.chl.general.resource.SecurityResource;
import com.tfb.aibank.chl.general.resource.dto.DeleteMbDeviceInfoRequest;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.type.OnboardingType;

//@formatter:off
/**
* @(#)NGNOT001080Task.java 
* 
* <p>Description:移轉設定成功頁</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230622, JohnChang
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT001080Task extends AbstractAIBankBaseTask<NGNOT001080Rq, NGNOT001080Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001080Task.class);

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private SecurityResource securityResource;

    @Override
    public void validate(NGNOT001080Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT001080Rq rqData, NGNOT001080Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001080 start====");
        DeviceBindingCache cache = getCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, DeviceBindingCache.class);

        if (cache == null) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.DATA_NOT_FOUND);
        }

        rsData.setOnboardingType(getLoginUser().getOnboardingType());

        // MOTP 設定結果
        if (cache.getMotpSetting() == LoginService.IS_TRANSFER) {
            if (rqData.isMotpOk()) {
                cache.setMotpSetting(LoginService.TRANSFER_SUCC);
            }
            else {
                cache.setMotpSetting(LoginService.TRANSFER_FAIL);
            }
        }
        rsData.setFastLogin(cache.getFastLogin());
        rsData.setNotification(cache.getNotification());
        rsData.setQuickSearch(cache.getQuickSearch());
        rsData.setMotpSetting(cache.getMotpSetting());
        rsData.setNoCardwithDraw(cache.getNoCardwithDraw());
        rsData.setPhoneTransfer(cache.getPhoneTransfer());
        rsData.setTransferQuota(cache.getTransferQuota());

        // 有錯誤
        if (rsData.getFastLogin() == LoginService.TRANSFER_FAIL || //
                rsData.getNotification() == LoginService.TRANSFER_FAIL || //
                rsData.getQuickSearch() == LoginService.TRANSFER_FAIL || //
                rsData.getMotpSetting() == LoginService.TRANSFER_FAIL || //
                rsData.getNoCardwithDraw() == LoginService.TRANSFER_FAIL || //
                rsData.getPhoneTransfer() == LoginService.TRANSFER_FAIL || //
                rsData.getTransferQuota() == LoginService.TRANSFER_FAIL

        ) {
            rsData.setAnyItemFail(true);
        }

        AIBankUser user = getLoginUser();
        // 先寄通知信
        if (OnboardingType.isTransfer(user.getOnboardingType())) {
            sendMail(user, cache);
        }

        // 全部錯誤
        if (!(rsData.getFastLogin() == LoginService.TRANSFER_SUCC || //
                rsData.getNotification() == LoginService.TRANSFER_SUCC || //
                rsData.getQuickSearch() == LoginService.TRANSFER_SUCC || //
                rsData.getMotpSetting() == LoginService.TRANSFER_SUCC || //
                rsData.getNoCardwithDraw() == LoginService.TRANSFER_SUCC || //
                rsData.getPhoneTransfer() == LoginService.TRANSFER_SUCC || //
                rsData.getTransferQuota() == LoginService.TRANSFER_SUCC)) {

            DeleteMbDeviceInfoRequest request = new DeleteMbDeviceInfoRequest();
            request.setCustId(user.getCustId());
            request.setUidDup(user.getUidDup());
            request.setUserId(user.getUserId());
            request.setCompanyKind(user.getCompanyKind());
            securityResource.deleteMbDeviceInfo(request);
            throw new ActionException(AIBankErrorCode.TRANSFER_ALL_ERROR);
        }
        rsData.setShowChangeTip(getLoginUser().isShowChangeTip());
        rsData.setLoginType(getLoginUser().getCustomerType().isGeneral() ? "m1" : "m2");
    }

    private void sendMail(AIBankUser loginUser, DeviceBindingCache cache) {
        HashMap<String, Object> params = new HashMap<>();

        boolean hasSucc = false;
        boolean hasFail = false;
        String succItem = "";
        String failItem = "";

        String appLink = systemParamCacheManager.getValue("AIBANK", "TRANSFER_EMAIL_URL");

       //@formatter:off
        /*
        ngn.ot001.transfer.fastLogin=快速登入
        ngn.ot001.transfer.Notification=訊息通知設定
        ngn.ot001.transfer.QuickSearch=免登速查
        ngn.ot001.transfer.MotpSetting=推播動態密碼
        ngn.ot001.transfer.NoCardwithDraw=無卡提款
        ngn.ot001.transfer.PhoneTransfer=手機號碼收款設定
        ngn.ot001.transfer.TransferQuota=轉帳額度變更
        */
       //@formatter:on

        // 快速登入
        if (cache.getFastLogin() == LoginService.TRANSFER_SUCC) {
            succItem += I18NUtils.getMessage("ngn.ot001.transfer.fastLogin");
            succItem += "、";
            hasSucc = true;
        }
        else if (cache.getFastLogin() == LoginService.TRANSFER_FAIL) {
            failItem += I18NUtils.getMessage("ngn.ot001.transfer.fastLogin");
            failItem += "、";
            hasFail = true;
        }

        // 訊息通知設定
        if (cache.getNotification() == LoginService.TRANSFER_SUCC) {
            succItem += I18NUtils.getMessage("ngn.ot001.transfer.Notification");
            succItem += "、";
            hasSucc = true;
        }
        else if (cache.getNotification() == LoginService.TRANSFER_FAIL) {
            failItem += I18NUtils.getMessage("ngn.ot001.transfer.Notification");
            failItem += "、";
            hasFail = true;
        }

        // 免登速查
        if (cache.getQuickSearch() == LoginService.TRANSFER_SUCC) {
            succItem += I18NUtils.getMessage("ngn.ot001.transfer.QuickSearch");
            succItem += "、";
            hasSucc = true;
        }
        else if (cache.getQuickSearch() == LoginService.TRANSFER_FAIL) {
            failItem += I18NUtils.getMessage("ngn.ot001.transfer.QuickSearch");
            failItem += "、";
            hasFail = true;
        }

        // 推播動態密碼
        if (cache.getMotpSetting() == LoginService.TRANSFER_SUCC) {
            succItem += I18NUtils.getMessage("ngn.ot001.transfer.MotpSetting");
            succItem += "、";
            hasSucc = true;
        }
        else if (cache.getMotpSetting() == LoginService.TRANSFER_FAIL) {
            failItem += I18NUtils.getMessage("ngn.ot001.transfer.MotpSetting");
            failItem += "、";
            hasFail = true;
        }

        // 無卡提款
        if (cache.getNoCardwithDraw() == LoginService.TRANSFER_SUCC) {
            succItem += I18NUtils.getMessage("ngn.ot001.transfer.NoCardwithDraw");
            succItem += "、";
            hasSucc = true;
        }
        else if (cache.getNoCardwithDraw() == LoginService.TRANSFER_FAIL) {
            failItem += I18NUtils.getMessage("ngn.ot001.transfer.NoCardwithDraw");
            failItem += "、";
            hasFail = true;
        }

        // 手機號碼收款設定
        if (cache.getPhoneTransfer() == LoginService.TRANSFER_SUCC) {
            succItem += I18NUtils.getMessage("ngn.ot001.transfer.PhoneTransfer");
            succItem += "、";
            hasSucc = true;
        }
        else if (cache.getPhoneTransfer() == LoginService.TRANSFER_FAIL) {
            failItem += I18NUtils.getMessage("ngn.ot001.transfer.PhoneTransfer");
            failItem += "、";
            hasFail = true;
        }

        // 轉帳額度變更
        if (cache.getTransferQuota() == LoginService.TRANSFER_SUCC) {
            succItem += I18NUtils.getMessage("ngn.ot001.transfer.TransferQuota");
            succItem += "、";
            hasSucc = true;
        }
        else if (cache.getTransferQuota() == LoginService.TRANSFER_FAIL) {
            failItem += I18NUtils.getMessage("ngn.ot001.transfer.TransferQuota");
            failItem += "、";
            hasFail = true;
        }

        if (StringUtils.isNotBlank(succItem) && succItem.length() > 1) {
            succItem = succItem.substring(0, succItem.length() - 1);
        }
        if (StringUtils.isNotBlank(failItem) && failItem.length() > 1) {
            failItem = failItem.substring(0, failItem.length() - 1);
        }

        String result;
        if (hasSucc) {
            if (hasFail) {
                // 部分成功
                result = I18NUtils.getMessage("ngn.ot001.transfer.status.partial_success");
            }
            else {
                // 成功
                result = I18NUtils.getMessage("ngn.ot001.transfer.status.success");
            }
        }
        else {
            // 失敗
            result = I18NUtils.getMessage("ngn.ot001.transfer.status.failure");
        }

        String time = DateFormatUtils.CE_DATETIME_FORMAT.format(new Date());

        // ngn.ot001.transfer.subject=台北富邦行動銀行「原行動銀行APP移轉」設定{0}
        String subject = I18NUtils.getMessage("ngn.ot001.transfer.subject", result);

        params.put("status", hasFail ? "1" : "0");

        // ngn.ot001.transfer.title=「原行動銀行APP移轉」設定{0}
        params.put("title", I18NUtils.getMessage("ngn.ot001.transfer.title", result));

        // ngn.ot001.transfer.content=您於{0}透過台北富邦行動銀行進行「原行動銀行APP移轉」設定。
        params.put("content", I18NUtils.getMessage("ngn.ot001.transfer.content", time));

        // ngn.ot001.transfer.subTitle=原行動銀行APP移轉設定
        params.put("subTitle", I18NUtils.getMessage("ngn.ot001.transfer.subTitle"));
        params.put("result", result);
        params.put("time", time);
        params.put("hasSucc", hasSucc ? "1" : "0");
        params.put("transferSucc", succItem);
        params.put("hasFail", hasFail ? "1" : "0");
        params.put("transferFail", failItem);

        params.put("templateName", "NGNOT001_TRANSFER");

        // ngn.ot001.transfer.note=如部分項目移轉失敗，您可至「個人服務>登入與驗證設定」重新設定
        params.put("note", I18NUtils.getMessage("ngn.ot001.transfer.note", appLink));

        if (StringUtils.isNotBlank(loginUser.getEmail())) {
            sendTxnResultMailObj(loginUser.getCustId(), loginUser.getUidDup(), loginUser.getUserId(), loginUser.getCompanyKind(), subject, loginUser.getEmail(), params);
        }
        else {
            // ngn.ot001.transfer.sms=【台北富邦銀行】您於{0}透過原行動銀行進行APP移轉，設定{1}，如有疑問請洽本行客服。
            String sms = I18NUtils.getMessage("ngn.ot001.transfer.sms", time, result);

            sendResultMsg( //
                    loginUser.getCustId(), loginUser.getUidDup(), loginUser.getUserId(), loginUser.getCompanyKind(), //
                    sms, null, null, loginUser.getMobileNo());
        }
    }
}
