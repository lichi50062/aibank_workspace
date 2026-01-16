package com.tfb.aibank.chl.creditcard.ag013.task;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.notification.model.CreatePersonalNotificationRequest;
import com.tfb.aibank.chl.creditcard.ag013.model.NCCAG013040Rq;
import com.tfb.aibank.chl.creditcard.ag013.model.NCCAG013040Rs;
import com.tfb.aibank.chl.creditcard.ag013.model.NCCAG013Fido2SDKEnum;
import com.tfb.aibank.chl.creditcard.ag013.service.NCCAG013Service;
import com.tfb.aibank.chl.creditcard.resource.SecurityResource;
import com.tfb.aibank.chl.creditcard.resource.dto.Fido2PushNotifyRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.Fido2PushNotifyResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.UpdateCreditCardFido2FlagRequest;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.type.NotificationSendStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

// @formatter:off
/**
 * @(#)NCCAG013040Task.java
 * 
 * <p>Description:信用卡/簽帳金融卡FIDO2綁定 040 結果頁顯示</p>
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
public class NCCAG013040Task extends AbstractAIBankBaseTask<NCCAG013040Rq, NCCAG013040Rs> {

    @Autowired
    private NCCAG013Service service;

    @Autowired
    private SecurityResource securityResource;

    @Override
    public void validate(NCCAG013040Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG013040Rq rqData, NCCAG013040Rs rsData) throws ActionException {
        AIBankUser user = getLoginUser();

        Date now = new Date();
        LocalDateTime localDateTime = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        String txTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

        String code = rqData.getErrorCode();
        if (!NCCAG013Fido2SDKEnum.SUCCESS_0.getCode().equals(code) &&
                !NCCAG013Fido2SDKEnum.SUCCESS_1200.getCode().equals(code)) {
            // 失敗報錯
            String desc = service.getEPayErrorMessage(service.normalizeErrorCode(code), getLocale());
            logger.info("[FIDO2] FIDO2 SDK 回傳錯誤代碼: {}, 錯誤訊息: {}", code, desc);
            rsData.setErrorCode("SDK " + code);
            rsData.setErrorDesc(desc);
            rsData.setTxTime(txTime);
            return;
        }

        // [FIDO2] 若成功將 MB_DEVICE_INFO TABLE CREDIT_CARD_FIDO2_FLAG 註記設置為 1
        UpdateCreditCardFido2FlagRequest request = new UpdateCreditCardFido2FlagRequest();
        request.setCustId(user.getCustId());
        request.setUidDup(user.getUidDup());
        request.setUserId(user.getUserId());
        request.setCompanyKind(user.getCompanyKind());
        request.setDeviceIxd(getRequest().getDeviceIxd());
        request.setCreditCardFido2Flag(1);

        securityResource.updateCreditCardFido2Flag(request);

        // [FIDO2] 發送結果通知 (待確認)
        // 發送綁定推播
        Fido2PushNotifyRequest fido2PushNotifyRequest = new Fido2PushNotifyRequest();
        fido2PushNotifyRequest.setPushCode("TX35");
        fido2PushNotifyRequest.setCustId(user.getCustId());
        fido2PushNotifyRequest.setUserId(user.getUserId());
        fido2PushNotifyRequest.setUidDup(user.getUidDup());
        fido2PushNotifyRequest.setCompanyKind(user.getCompanyKind());
        fido2PushNotifyRequest.setDeviceId(getRequest().getDeviceIxd());
        Fido2PushNotifyResponse pushSubscriptionStatus = securityResource.getPushSubscriptionStatus(fido2PushNotifyRequest);

        if (pushSubscriptionStatus != null && pushSubscriptionStatus.isEnable() && pushSubscriptionStatus.isSubscribe()) {
            sendPushNotification(user, new Date(), pushSubscriptionStatus);
        }

        rsData.setErrorCode("0000");
        rsData.setErrorDesc(NCCAG013Fido2SDKEnum.SUCCESS_0.getMemo());
        rsData.setTxTime(txTime);
    }

    /**
     * 發送綁定推播
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
        request.setPushMessage(I18NUtils.getMessage("nccag013.push.bind.pushMessage", dateTime));

        // 內文訊息
        request.setMessage(I18NUtils.getMessage("nccag013.push.bind.message", dateTime));

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
