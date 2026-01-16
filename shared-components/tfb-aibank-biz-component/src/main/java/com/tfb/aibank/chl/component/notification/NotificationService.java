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
package com.tfb.aibank.chl.component.notification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.notification.model.BillHunterNotify;
import com.tfb.aibank.chl.component.notification.model.CreatePersonalNotificationRequest;
import com.tfb.aibank.chl.component.notification.model.EmailNotify;
import com.tfb.aibank.chl.component.notification.model.SMSNotify;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.type.MailParamType;
import com.tfb.aibank.common.type.NotificationType;
import com.tfb.aibank.common.type.TxSourceType;

// @formatter:off
/**
 * @(#)NotificationService.java
 * 
 * <p>Description:提供紀錄「通知作業」相關服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/20, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NotificationService {

    private static final IBLog logger = IBLog.getLog(NotificationService.class);

    @Autowired
    private NotificationResource resource;

    /**
     * 寄送郵件
     * 
     * @param loginUser
     * @param notify
     */
    @Async
    public void sendMail(AIBankUser loginUser, EmailNotify notify) {
        sendMail(loginUser.getCustId(), loginUser.getUidDup(), loginUser.getUserId(), loginUser.getCompanyKind(), notify);
    }

    /**
     * 寄送郵件
     * 
     * @param custId
     * @param uidDup
     * @param userId
     * @param companyKind
     * @param notify
     */
    @Async
    public void sendMail(String custId, String uidDup, String userId, Integer companyKind, EmailNotify notify) {
        resource.sendMail(custId, uidDup, userId, companyKind, notify);
    }

    /**
     * 寄送交易結果Email通知
     * 
     * @param custId
     * @param uidDup
     * @param userId
     * @param companyKind
     * @param emailNotify
     */
    @Async
    public void sendTxResultMail(String custId, String uidDup, String userId, Integer companyKind, EmailNotify emailNotify) {
        resource.sendTxnResultMail(custId, uidDup, userId, companyKind, emailNotify);
    }

    /**
     * 發送簡訊
     * 
     * @param loginUser
     * @param notify
     */
    @Async
    public void sendSMS(AIBankUser loginUser, SMSNotify notify) {
        sendSMS(loginUser.getCustId(), loginUser.getUidDup(), loginUser.getUserId(), loginUser.getCompanyKind(), notify);
    }

    /**
     * 發送簡訊
     * 
     * @param custId
     * @param uidDup
     * @param userId
     * @param companyKind
     * @param notify
     */
    @Async
    public void sendSMS(String custId, String uidDup, String userId, Integer companyKind, SMSNotify notify) {
        resource.sendSMS(custId, uidDup, userId, companyKind, notify);
    }

    /**
     * 發送BillHunter補寄帳單
     * 
     * @param loginUser
     * @param notify
     */
    public void sendBillHunter(AIBankUser loginUser, BillHunterNotify notify) {
        sendBillHunter(loginUser.getCustId(), loginUser.getUidDup(), loginUser.getUserId(), loginUser.getCompanyKind(), notify);
    }

    /**
     * 發送BillHunter補寄帳單
     * 
     * @param custId
     * @param userId
     * @param companyKind
     * @param notify
     */
    public BillHunterNotify sendBillHunter(String custId, String uidDup, String userId, Integer companyKind, BillHunterNotify notify) {
        return resource.sendBillHunter(custId, uidDup, userId, companyKind, notify);
    }

    /**
     * 建立發送簡訊使用的物件
     * 
     * @param userId
     * @param masterKey
     * @param detailKey
     * @param message
     * @param phoneNumber
     * @param txId
     * @return
     */
    public SMSNotify buildSMSNotify(String userId, Integer masterKey, Integer detailKey, String message, String phoneNumber, String txId) {
        SMSNotify sms = new SMSNotify();
        sms.setUserId(userId);
        sms.setTxSource(TxSourceType.AI_BANK_FOR_NOTIFICATION.getCode());
        sms.setNotifyType(NotificationType.SMS.getCode());
        sms.setMasterKey(masterKey);
        sms.setDetailKey(detailKey);
        sms.setMessage(message);
        sms.setPhoneNumber(phoneNumber);
        sms.setTxId(txId);
        return sms;
    }

    /**
     * 建立發送簡訊使用的物件
     * 
     * @param userId
     * @param masterKey
     * @param detailKey
     * @param message
     * @param phoneNumber
     * @param txId
     * @return
     */
    public BillHunterNotify buildBillHunterNotify(String userId, Integer masterKey, Integer detailKey, String message, String phoneNumber, String txId) {
        BillHunterNotify notify = new BillHunterNotify();
        notify.setUserId(userId);
        notify.setTxSource(TxSourceType.AI_BANK_FOR_NOTIFICATION.getCode());
        notify.setNotifyType(NotificationType.BILL_HUNTER.getCode());
        return notify;
    }

    /**
     * 建立個人化通知訊息
     * 
     * @param request
     * @throws ActionException
     */
    @Async
    public void sendPushNotification4Personal(CreatePersonalNotificationRequest request) {
        resource.createPersonalNotification(request);
    }

    /**
     * 發送email
     * 
     * @param mailSender
     *            寄信人
     * @param mailRecipient
     *            收信人
     * @param subject
     *            主旨
     * @param params
     *            內容、參數等...
     */
    public void sendEmail(String mailSender, String mailRecipient, String subject, Map<String, Object> params) {
        EmailNotify emailObj = new EmailNotify();
        emailObj.setFrom(mailSender); // 寄件人
        emailObj.setTo(new ArrayList<String>(Arrays.asList(mailRecipient.split(";")))); // 收件人

        emailObj.setTxSource(TxSourceType.AI_BANK_FOR_NOTIFICATION.getCode());
        emailObj.setNotifyType(NotificationType.EMAIL.getCode());
        emailObj.setSubject(subject);

        // 防呆
        if (params == null) {
            params = new HashMap<String, Object>();
        }
        emailObj.setTemplateParams(params);
        if (logger.isDebugEnabled()) {
            logger.debug("信件內容參數={}", IBUtils.attribute2Str(params));
        }

        // 設置交易主檔鍵值
        if (params.containsKey(MailParamType.MASTER_KEY.getCode())) {
            emailObj.setMasterKey((Integer) params.get(MailParamType.MASTER_KEY.getCode()));
        }
        // 設置交易明細檔鍵值
        if (params.containsKey(MailParamType.DETAIL_KEY.getCode())) {
            emailObj.setDetailKey((Integer) params.get(MailParamType.DETAIL_KEY.getCode()));
        }
        // 若有指定模板代號，則取代
        if (params.containsKey(MailParamType.TEMPLATE_NAME.getCode())) {
            emailObj.setTemplateName((String) params.get(MailParamType.TEMPLATE_NAME.getCode()));
        }
        // 設置交易類型
        if (params.containsKey(MailParamType.TX_TYPE.getCode())) {
            emailObj.setTxType((String) params.get(MailParamType.TX_TYPE.getCode()));
        }
        // 設置交易代號
        if (params.containsKey(MailParamType.TXN_ID.getCode())) {
            emailObj.setTxId((String) params.get(MailParamType.TXN_ID.getCode()));
        }

        try {
            resource.sendMail(emailObj);
        }
        catch (ServiceException ex) {
            logger.error(String.format("發送Email產生例外。%s", ex.getMessage()), ex);
        }
    }

}
