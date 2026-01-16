package com.tfb.aibank.chl.general.ot001.task;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.countryname.IpLocationAndCountryNameCacheManager;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001101Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001101Rs;
import com.tfb.aibank.chl.general.ot001.task.service.DeviceBindingCache;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailData;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailType;
import com.tfb.aibank.chl.general.resource.PreferencesResource;
import com.tfb.aibank.chl.general.resource.SecurityResource;
import com.tfb.aibank.chl.general.resource.dto.LoginResponse;
import com.tfb.aibank.chl.general.resource.dto.PushCodeModel;
import com.tfb.aibank.chl.general.resource.dto.PushTxnModel;
import com.tfb.aibank.chl.general.resource.dto.QueryVerifyResultRequest;
import com.tfb.aibank.chl.general.resource.dto.QueryVerifyResultResponse;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NGNOT001101Task.java 
*  
* <p>Description:登入 FIDO 綁定初始化 QueryVerifyResult</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230522, JohnChang
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT001101Task extends AbstractAIBankBaseTask<NGNOT001101Rq, NGNOT001101Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001101Task.class);

    @Autowired
    LoginService loginService;

    @Autowired
    private SecurityResource securityResource;

    @Autowired
    private PreferencesResource preferencesResource;

    @Autowired
    private IpLocationAndCountryNameCacheManager ipLocationAndCountryCacheManager;

    @Override
    public void validate(NGNOT001101Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NGNOT001101Rq rqData, NGNOT001101Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001101 start==== rqData: {}", rqData);

        AIBankUser user = this.getLoginUser();

        LoginResponse loginResponse = getCache(LoginService.FIDO_INFO_KEY, LoginResponse.class);

        QueryVerifyResultRequest request = new QueryVerifyResultRequest();

        request.setCustId(user.getCustId());
        request.setUidDup(user.getUidDup());
        request.setUserId(user.getUserId());
        request.setCompanyKind(user.getCompanyKindType().getCode());
        request.setToken(loginResponse.getToken());
        request.setVerifyNo(loginResponse.getVerifyNo());
        request.setRegisterDevice(true);
        request.setDeviceId(getRequest().getDeviceIxd());
        request.setModel(getRequest().getModel());
        request.setDevicePlatform(this.getPlatformDisplay());
        request.setDevicePlatformVersion(getRequest().getVersion());
        request.setIp(getRequest().getClientIp());
        request.setLoginType(user.getCustomerType().isGeneral() ? 0 : 1);
        request.setLoginPasswordType(rqData.getLoginPasswordType());
        request.setPushToken(rqData.getPushToken());

        QueryVerifyResultResponse response = securityResource.queryVerifyResult(request);

        // 發送通知發送 Email, 空值預設為發送 Email 通知
        boolean sendEmail = rqData.getSendNotificationMethod() == null || 1 == rqData.getSendNotificationMethod().intValue();

        if ("0".equals(response.getStatus())) {

            if (sendEmail) {
                LoginMailData bindingMail = loginService.getLoginMailData(LoginMailType.BINDING_SUCC, getRequest().getClientIp(), user.getMarketingName(), getLocale(), null, getRequest().getPlatform() + " " + getRequest().getVersion(), getCountryName());
                sendMail(user.getCustId(), user.getUidDup(), user.getUserId(), user.getCompanyKind(), bindingMail.getSubject(), user.getEmail(), bindingMail.getParams());

                if (rqData.getLoginPasswordType() != 1) {
                    LoginMailData fastLoinMail = loginService.getLoginMailData(LoginMailType.BIO_SETTING_SUCC, getRequest().getClientIp(), user.getMarketingName(), getLocale(), null, getRequest().getPlatform() + " " + getRequest().getVersion(), getCountryName());
                    sendMail(user.getCustId(), user.getUidDup(), user.getUserId(), user.getCompanyKind(), fastLoinMail.getSubject(), user.getEmail(), fastLoinMail.getParams());
                }
            }

            // 開啟推播設定
            if (StringUtils.equals(rqData.getEnableNotification(), "Y")) {
                enabledNotification();
            }

            updateStatus(LoginService.TRANSFER_SUCC);
        }
        else {
            updateStatus(LoginService.TRANSFER_FAIL);

            if (sendEmail) {
                LoginMailData bindingMail = loginService.getLoginMailData(LoginMailType.BINDING_FAIL, getRequest().getClientIp(), user.getMarketingName(), getLocale(), null, getRequest().getPlatform() + " " + getRequest().getVersion(), getCountryName());
                sendMail(user.getCustId(), user.getUidDup(), user.getUserId(), user.getCompanyKind(), bindingMail.getSubject(), user.getEmail(), bindingMail.getParams());

                if (rqData.getLoginPasswordType() != 1) {
                    LoginMailData fastLoinMail = loginService.getLoginMailData(LoginMailType.BIO_SETTING_FAIL, getRequest().getClientIp(), user.getMarketingName(), getLocale(), null, getRequest().getPlatform() + " " + getRequest().getVersion(), getCountryName());
                    sendMail(user.getCustId(), user.getUidDup(), user.getUserId(), user.getCompanyKind(), fastLoinMail.getSubject(), user.getEmail(), fastLoinMail.getParams());
                }
            }
        }

        rsData.setSuccess(StringUtils.equals("0", response.getStatus()));
        rsData.setErrorCode(response.getError());
        rsData.setErrorDesc(response.getError_description());
    }

    /**
     * 開啟推播設定
     */
    private void enabledNotification() {

        logger.debug("NGNOT001101Task.enabledNotification 開啟推播設定");
        // A. 取得裝置 UUID 及 推播設定資訊
        String deviceUuid = getDeviceIxd();
        PushTxnModel request = loginService.prepareRequest(getLoginUser(), getClientIp(), getTraceId(), getAppVer());
        PushTxnModel pushdata = preferencesResource.getPushInfo(request, deviceUuid);
        request.setDeviceInfoKxy(pushdata.getDeviceInfoKxy());
        // B. 更新訊息設定狀態
        request.setNotifyFlag(1);
        request.setUpdateType("1");
        request.setOn(true);

        for (PushCodeModel m : pushdata.getList()) {
            if (!m.isParent()) {
                request.getList().add(m);
            }
        }

        try {
            Boolean resp = preferencesResource.updatePushSettings(request);

        }
        catch (ServiceException e) {
            logger.error("NGNOT001101Task.enabledNotification 推播設定開啟失敗,", e);
        }
    }

    protected void sendMail(String custId, String uidDup, String userId, int companyKind, String subject, String mailTo, Map<String, String> params) {
        if (StringUtils.isNotBlank(mailTo)) {
            sendTxnResultMail(custId, uidDup, userId, companyKind, subject, mailTo, params);
        }
    }

    /**
     * 更新 快登 cache 狀態
     * 
     * @param status
     */
    protected void updateStatus(int status) {
        DeviceBindingCache cache = getCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, DeviceBindingCache.class);
        if (cache != null) {
            cache.setFastLogin(status);
            setCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, cache);
        }
    }

    private String getCountryName() {
        return ipLocationAndCountryCacheManager.getCountryNameByIpLocationAndLocale(this.getClientIp(), getLocale()).getCountryName();
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
