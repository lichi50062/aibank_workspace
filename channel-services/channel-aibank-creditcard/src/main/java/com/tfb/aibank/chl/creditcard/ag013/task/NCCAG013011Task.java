package com.tfb.aibank.chl.creditcard.ag013.task;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.notification.model.CreatePersonalNotificationRequest;
import com.tfb.aibank.chl.creditcard.ag013.model.NCCAG013011Rq;
import com.tfb.aibank.chl.creditcard.ag013.model.NCCAG013011Rs;
import com.tfb.aibank.chl.creditcard.ag013.model.NCCAG013Fido2APIEnum;
import com.tfb.aibank.chl.creditcard.ag013.service.NCCAG013Service;
import com.tfb.aibank.chl.creditcard.resource.SecurityResource;
import com.tfb.aibank.chl.creditcard.resource.dto.Fido2PushNotifyRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.Fido2PushNotifyResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.RemoveUserFido2Request;
import com.tfb.aibank.chl.creditcard.resource.dto.RemoveUserFido2Response;
import com.tfb.aibank.chl.creditcard.resource.dto.UpdateCreditCardFido2FlagRequest;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.type.NotificationSendStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

// @formatter:off
/**
 * @(#)NCCAG013011Task.java
 *
 * <p>Description:信用卡/簽帳金融卡FIDO2綁定 011 解除綁定</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2025/04/23, Evans
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG013011Task extends AbstractAIBankBaseTask<NCCAG013011Rq, NCCAG013011Rs> {

    @Autowired
    private NCCAG013Service service;

    @Autowired
    private SecurityResource securityResource;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    private final String rpTxId = UUID.randomUUID().toString();

    @Override
    public void validate(NCCAG013011Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG013011Rq rqData, NCCAG013011Rs rsData) throws ActionException {

        AIBankUser user = getLoginUser();
        RemoveUserFido2Request disableUserFido2Request = new RemoveUserFido2Request();

        String rpId = systemParamCacheManager.getValue("AIBANK", "FIDO2_HEADER_RPID");
        String systemId = systemParamCacheManager.getValue("AIBANK", "FIDO2_REQUEST_EXTENSIONS_SYSTEMID");
        String operator = systemParamCacheManager.getValue("AIBANK", "FIDO2_API_OPERATOR");
        String operatorSource = systemParamCacheManager.getValue("AIBANK", "FIDO2_API_OPERATOR_SOURCE");

        disableUserFido2Request.setRpId(rpId);
        disableUserFido2Request.setRpTxId(rpTxId);
        disableUserFido2Request.setUsername(user.getCustId());
        disableUserFido2Request.setRpUsername(user.getCustId());
        disableUserFido2Request.setSystemId(systemId);
        disableUserFido2Request.setOperator(operator);
        disableUserFido2Request.setOperatorSource(operatorSource);

        // [FIDO2] API FIDO2 使用者註銷
        RemoveUserFido2Response disableUserFido2Response = securityResource.removeUserFido2(disableUserFido2Request);
        String code = disableUserFido2Response.getReturnCode();
        // 除了成功與重複解綁(1002)以外都報錯
        if (!NCCAG013Fido2APIEnum.SUCCESS.getCode().equals(code) &&
                !NCCAG013Fido2APIEnum.USERNAME_NOT_EXISTS.getCode().equals(code)) {
            // 失敗報錯
            String desc = service.getEPayErrorMessage(service.normalizeErrorCode(code), getLocale());
            logger.error("[FIDO2] FIDO2 使用者註銷失敗回傳錯誤代碼: {}, 錯誤訊息: {}", code, desc);
            ErrorStatus errorStatus = new ErrorStatus(IBSystemId.FIDO2.getSystemId(), "API " + code,
                    SeverityType.ERROR, desc);
            throw ExceptionUtils.getActionException(errorStatus);
        }

        // [FIDO2] 將 MB_DEVICE_INFO TABLE CREDIT_CARD_FIDO2_FLAG 註記設置為 0
        UpdateCreditCardFido2FlagRequest request = new UpdateCreditCardFido2FlagRequest();
        request.setCustId(user.getCustId());
        request.setUidDup(user.getUidDup());
        request.setUserId(user.getUserId());
        request.setCompanyKind(user.getCompanyKind());
        request.setDeviceIxd(getRequest().getDeviceIxd());
        request.setCreditCardFido2Flag(0);

        securityResource.updateCreditCardFido2Flag(request);

        // 發送解綁推播
        Fido2PushNotifyRequest fido2PushNotifyRequest = new Fido2PushNotifyRequest();
        fido2PushNotifyRequest.setPushCode("TX35");
        fido2PushNotifyRequest.setCustId(user.getCustId());
        fido2PushNotifyRequest.setUserId(user.getUserId());
        fido2PushNotifyRequest.setUidDup(user.getUidDup());
        fido2PushNotifyRequest.setCompanyKind(user.getCompanyKind());
        fido2PushNotifyRequest.setDeviceId(getRequest().getDeviceIxd());
        Fido2PushNotifyResponse pushSubscriptionStatus = securityResource.getPushSubscriptionStatus(fido2PushNotifyRequest);

        if(pushSubscriptionStatus != null && pushSubscriptionStatus.isEnable() && pushSubscriptionStatus.isSubscribe()){
            sendPushNotification(user, new Date(), pushSubscriptionStatus);
        }

        rsData.setSuccess(true);
    }

    /**
     * 發送解綁推播
     */
    private void sendPushNotification(AIBankUser user, Date bindingDate, Fido2PushNotifyResponse pushSubscriptionStatus) {

        CreatePersonalNotificationRequest request = new CreatePersonalNotificationRequest();

        Date now = new Date();

        request.setCustId(user.getCustId());
        request.setUserId(user.getUserId());
        request.setCompanyKind(user.getCompanyKind());

        // 是否發送推播
        request.setIsPush(service.checkIsPush(pushSubscriptionStatus.getNotifyFlag(), pushSubscriptionStatus.getNotifyPass()) ? 1 : 0);
        // 推播代碼
        request.setPushCode(pushSubscriptionStatus.getPushCode());
        // 業務別
        request.setBusType("3");
        // 推播優先序
        request.setPriority(9);
        // 標題訊息
        request.setTitleMessage(I18NUtils.getMessage("nccag013.push.title"));

        String dateTime = DateFormatUtils.CE_DATETIME_FORMAT_WITHOUT_SEC.format(bindingDate);

        // 推播訊息
        request.setPushMessage(I18NUtils.getMessage("nccag013.push.unbind.pushMessage", dateTime));

        // 內文訊息
        request.setMessage(I18NUtils.getMessage("nccag013.push.unbind.message", dateTime));

        logger.info("標題訊息 : " + request.getTitleMessage());
        logger.info("推播訊息 : " + request.getPushMessage());
        logger.info("內文訊息 : " + request.getMessage());

        // 傳送狀態
        request.setSendStatus(NotificationSendStatus.WAITING.getCode());
        // 狀態
        request.setStatus("O");
        // 是否已讀
        request.setIsRead(0);
        // 開始日期時間
        request.setStartDate(now);
        // 結束日期時間
        request.setEndDate(service.getEndDate());
        // 更新時間
        request.setUpdateTime(now);
        // 建立時間
        request.setCreateTime(now);

        sendPushNotification(request);
    }
}