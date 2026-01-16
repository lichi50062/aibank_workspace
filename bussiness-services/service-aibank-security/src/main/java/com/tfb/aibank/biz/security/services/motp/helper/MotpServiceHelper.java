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
package com.tfb.aibank.biz.security.services.motp.helper;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.biz.security.repository.MbDeviceInfoRepository;
import com.tfb.aibank.biz.security.repository.MotpDeviceInfoRepository;
import com.tfb.aibank.biz.security.repository.MotpTransDataRepository;
import com.tfb.aibank.biz.security.repository.MotpVerifyCarrierRepository;
import com.tfb.aibank.biz.security.repository.aib.PersonNotificationRecordRepository;
import com.tfb.aibank.biz.security.repository.aib.entities.PersonNotificationRecordEntity;
import com.tfb.aibank.biz.security.repository.entities.MbDeviceInfoEntity;
import com.tfb.aibank.biz.security.repository.entities.MotpDeviceInfoEntity;
import com.tfb.aibank.biz.security.repository.entities.MotpTransDataEntity;
import com.tfb.aibank.biz.security.repository.entities.MotpVerifyCarrierEntity;
import com.tfb.aibank.biz.security.services.motp.MotpPropertyService;
import com.tfb.aibank.biz.security.services.motp.MotpProxyService;
import com.tfb.aibank.biz.security.services.motp.bean.PushMessageRs;
import com.tfb.aibank.biz.security.services.motp.bean.VerifyOtpRs;
import com.tfb.aibank.biz.security.services.motp.helper.model.CreateMotpTransDataCondition;
import com.tfb.aibank.biz.security.services.motp.helper.model.CreateMotpTransDataResult;
import com.tfb.aibank.biz.security.services.motp.helper.model.FindMotpTransDataCondition;
import com.tfb.aibank.biz.security.services.motp.helper.model.FindMotpTransDataResult;
import com.tfb.aibank.biz.security.services.motp.helper.model.PreAuthCheckCondition;
import com.tfb.aibank.biz.security.services.motp.helper.model.PreAuthCheckResult;
import com.tfb.aibank.biz.security.services.motp.helper.model.SendOfflineOtpCondition;
import com.tfb.aibank.biz.security.services.motp.helper.model.SendOfflineOtpResult;
import com.tfb.aibank.biz.security.services.motp.helper.model.SendPushOtpCondition;
import com.tfb.aibank.biz.security.services.motp.helper.model.SendPushOtpResult;
import com.tfb.aibank.biz.security.services.motp.helper.model.VerifyPushOtpCondition;
import com.tfb.aibank.biz.security.services.motp.helper.model.VerifyPushOtpResult;
import com.tfb.aibank.biz.security.services.motp.type.MotpCarrierStatus;
import com.tfb.aibank.biz.security.services.motp.type.MotpDeviceStatus;
import com.tfb.aibank.biz.security.services.motp.type.MotpSendResult;
import com.tfb.aibank.biz.security.services.motp.type.MotpStatusType;
import com.tfb.aibank.biz.security.services.motp.type.MotpTxStatus;
import com.tfb.aibank.chl.type.BusType;
import com.tfb.aibank.chl.type.PushCodeType;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.type.MotpAuthVerifyType;
import com.tfb.aibank.common.type.MotpVerifyResultType;
import com.tfb.aibank.common.type.NotificationSendStatus;
import com.tfb.aibank.common.type.NotificationStatus;
import com.tfb.aibank.common.util.TxCodeUtils;
import com.tfb.aibank.component.motplog.MotpLogActionType;

// @formatter:off
/**
 * @(#)MotpServiceHelper.java
 * 
 * <p>Description:MOTP服務輔助工具</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/15, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class MotpServiceHelper {

    private static final IBLog logger = IBLog.getLog(MotpServiceHelper.class);

    /** MOTP置換子 */
    private static final String MOTP_REPLACER = "{0}";
    /** 交易代碼置換子 */
    private static final String TX_CODE_REPLACER = "{1}";
    /** 有效時間置換子 */
    private static final String AUTH_DEADLINE_REPLACER = "{2}";
    /** 可驗證次數 */
    private static final Integer MOTP_VERIFY_LIMIT_TIMES = 3;

    @Autowired
    private MotpPropertyService motpPropertyService;

    @Autowired
    private MotpProxyService motpProxyService;

    @Autowired
    private MbDeviceInfoRepository mbDeviceInfoRepository;

    @Autowired
    private MotpDeviceInfoRepository motpDeviceInfoRepository;

    @Autowired
    private MotpTransDataRepository motpTransDataRepository;

    @Autowired
    private MotpVerifyCarrierRepository motpVerifyCarrierRepository;

    @Autowired
    private PersonNotificationRecordRepository personNotificationRecordRepository;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private MotpLogDataHelper motpLogDataHelper;

    /**
     * MOTP安控前置檢查
     * 
     * @param condition
     * @return
     * @throws ActionException
     */
    public PreAuthCheckResult preAuthCheck(PreAuthCheckCondition condition) throws ActionException {

        PreAuthCheckResult result = new PreAuthCheckResult();
        String logPrefix = "[MotpServiceHelper][preAuthCheck]";

        boolean isMotpAvaliable = motpPropertyService.isMotpAvaliable();
        logger.info("{} MOTP系統是否可以使用:{}", logPrefix, isMotpAvaliable);
        if (!isMotpAvaliable) {
            result.setMotpAuthVerifyType(MotpAuthVerifyType.MOTP_SERVICE_UNAVALIBLE);
            return result;
        }

        // 找出使用者已綁定的MOTP_DEVICE_INFO
        List<MotpDeviceInfoEntity> bindingList = motpDeviceInfoRepository.findByCompanyKeyAndUserKeyAndEnableAndGroup(condition.getCompanyKey(), condition.getUserKey(), MotpDeviceStatus.ENABLE, AIBankConstants.CHANNEL_NAME);
        if (CollectionUtils.isEmpty(bindingList)) {
            logger.info("{} 無已綁定的MOTP_DEVICE_INFO", logPrefix);
            result.setMotpAuthVerifyType(MotpAuthVerifyType.NO_BINDING_DEVICE);
            return result;
        }

        // 找到超過一筆已綁定的MOTP_DEVICE_INFO
        if (bindingList.size() > 1) {
            logger.info("{} 找到超過一筆已綁定的MOTP_DEVICE_INFO", logPrefix);
            result.setMotpAuthVerifyType(MotpAuthVerifyType.BINDING_STATUS_ABNORMAL);
            return result;
        }

        MotpDeviceInfoEntity bindingDeviceInfo = bindingList.get(0);
        result.setBindingDeviceInfo(bindingDeviceInfo);
        
        
        // 找MB_DEVICE_INFO
        MbDeviceInfoEntity bindingMbDeviceInfo = mbDeviceInfoRepository.findById(bindingDeviceInfo.getDeviceInfoKey()).orElse(null);
        result.setBindingMbDeviceInfo(bindingMbDeviceInfo);

        if (bindingMbDeviceInfo == null) {
            logger.info("{} 找不到MB_DEVICE_INFO，視為沒有綁定中的裝置", logPrefix);
            result.setMotpAuthVerifyType(MotpAuthVerifyType.NO_BINDING_DEVICE);
            return result;
        }
        else if (bindingMbDeviceInfo.getMotpFlag() == null && bindingMbDeviceInfo.getMotpFlag() == 0) {
            logger.info("{} MOTP_DEVICE_INFO與MB_DEVICE_INFO.MOTP_FLAG不一致，視為沒有綁定中的裝置", logPrefix);
            result.setMotpAuthVerifyType(MotpAuthVerifyType.NO_BINDING_DEVICE);
            return result;
        }

        logger.info("{} motp8380_1: "+ bindingDeviceInfo.getDeviceInfoKey(), logPrefix);
        logger.info("{} motpAuthDeviceBindingInfo VerifySuccess", logPrefix);
        result.setMotpAuthVerifyType(MotpAuthVerifyType.SUCCESS);
        return result;
    }

    /**
     * 產生MOTP認證資訊
     * 
     * @param condition
     * @return
     * @throws ActionException
     */
    public CreateMotpTransDataResult createMotpTransData(CreateMotpTransDataCondition condition) throws ActionException {

        CreateMotpTransDataResult result = new CreateMotpTransDataResult();
        String logPrefix = "[MotpServiceHelper][createMotpTransData]";

        MotpTransDataEntity transData = new MotpTransDataEntity();
        // 交易來源
        transData.setChannel(AIBankConstants.CHANNEL_NAME);
        // MOTP產生方式
        transData.setVerifyCarrierType(condition.getMotpTxCarrierType());
        // MOTP裝置綁定鍵值
        transData.setMotpDeviceKey(condition.getMotpDeviceKey());
        // 交易來源裝置鍵值
        transData.setFromDeviceUuid(condition.getDeviceIxd());
        // 公司鍵值
        transData.setCompanyKey(condition.getCompanyKey());
        // 使用者鍵值
        transData.setUserKey(condition.getUserKey());
        // 交易功能
        transData.setTaskId(condition.getTaskIxd());
        // 交易序號
        transData.setTxCode(TxCodeUtils.genTxCode());
        // 交易參數因子
        transData.setTxFactor(condition.getTxFactor());
        // 交易因子類型
        transData.setTxSeedType(condition.getTxSeedType());
        // 認證狀態
        transData.setStatus(MotpTxStatus.INIT);
        // MOTP認證截止時間
        Date createTime = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createTime);
        calendar.add(Calendar.SECOND, motpPropertyService.getMotpExpireTimes());
        Date deadline = calendar.getTime();
        transData.setAuthDeadline(deadline);
        // Challenge
        StringBuilder challenge = new StringBuilder(8192).append(condition.getTxFactor()).append("&").append(DateUtils.getSimpleDateTimeStr(createTime)).append("&").append(DateUtils.getSimpleDateTimeStr(deadline));
        logger.info("{} challenge={}", logPrefix, challenge);
        transData.setChallenge(challenge.toString());
        // 客戶IP
        transData.setClientIp(condition.getClientIp());
        // 建立時間
        transData.setCreateTime(createTime);
        // MOTP驗證次數
        transData.setAuthCount(0);
        transData = motpTransDataRepository.save(transData);
        result.setMotpTransData(transData);
        logger.info("{} 成功建立MOTP交易認證資訊", logPrefix);

        MotpVerifyCarrierEntity carrierData = new MotpVerifyCarrierEntity();
        // 交易資訊主檔資料
        carrierData.setMotpTransKey(transData.getMotpTransKey());
        // 推播OTP驗證碼狀態
        carrierData.setCarrierStatus(MotpCarrierStatus.INIT);
        // 建立時間
        carrierData.setCreateTime(new Date());
        // 推播訊息通知
        String title = condition.getTitle();
        title = StringUtils.replace(title, TX_CODE_REPLACER, transData.getTxCode());
        title = StringUtils.replace(title, AUTH_DEADLINE_REPLACER, DateUtils.getDateTimeStr(deadline));
        carrierData.setTitle(title);
        // 推播訊息內容
        String message = condition.getMessage();
        message = StringUtils.replace(message, TX_CODE_REPLACER, transData.getTxCode());
        message = StringUtils.replace(message, AUTH_DEADLINE_REPLACER, DateUtils.getDateTimeStr(deadline));
        carrierData.setMessage(message);
        // 彈跳視窗內容
        String custInfo = condition.getCustInfo();
        custInfo = StringUtils.replace(custInfo, TX_CODE_REPLACER, transData.getTxCode());
        custInfo = StringUtils.replace(custInfo, AUTH_DEADLINE_REPLACER, DateUtils.getDateTimeStr(deadline));
        carrierData.setPopup(custInfo);
        carrierData = motpVerifyCarrierRepository.save(carrierData);
        result.setCarrierData(carrierData);
        logger.info("{} 成功建立MOTP交易驗證之載具資訊", logPrefix);
        return result;
    }

    /**
     * 發送推播OTP認證
     * 
     * @param condition
     * @return
     * @throws ActionException
     */
    public SendPushOtpResult sendPushOtp(SendPushOtpCondition condition, String custId, String userId) throws ActionException {

        SendPushOtpResult result = new SendPushOtpResult();
        String logPrefix = "[MotpServiceHelper][sendPushOtp]";

        MotpSendResult sendResult = null;
        MotpTransDataEntity motpTransData = condition.getMotpTransData();
        MotpVerifyCarrierEntity carrierData = condition.getCarrierData();

        try {

            MotpLogDataModel motpLogData = motpLogDataHelper.createMotpLog(MotpLogActionType.API_PUSH_MESSAGE, custId, userId,

                    condition.getMotpTransData().getCompanyKey(), condition.getMotpTransData().getUserKey(),

                    "MotpProxyService", "callAPIPushMessage");

            // 全景MOTP API - 推播訊息
            PushMessageRs pushMessageRs = motpProxyService.callAPIPushMessage(condition.getAccount(), carrierData.getTitle(), carrierData.getMessage(), motpTransData.getChallenge(), condition.getGroup(), carrierData.getPopup(), new HashMap<>(), motpLogData);

            // 發送成功
            if (StringUtils.equals(MotpStatusType.MOTP_STATUS_OK.getCode(), pushMessageRs.getCode())) {
                String motp = pushMessageRs.getData().getOtp();
                logger.info("{} 成功發送推播OTP: {}", logPrefix, motp);
                carrierData.setMotp(motp);
                carrierData.setTitle(StringUtils.replace(carrierData.getTitle(), MOTP_REPLACER, motp));
                carrierData.setMessage(StringUtils.replace(carrierData.getMessage(), MOTP_REPLACER, motp));
                carrierData.setPopup(StringUtils.replace(carrierData.getPopup(), MOTP_REPLACER, motp));
                carrierData.setCarrierStatus(MotpCarrierStatus.SEND_SUCCESS);
                carrierData.setVerifyCount(0);
                sendResult = MotpSendResult.MOTP_SEND_SUCCESS;
            }
            // 發送失敗
            else if (StringUtils.equals(MotpStatusType.MOTP_GENERAL_ERROR.getCode(), pushMessageRs.getCode())) {
                logger.error("{} 發送推播OTP API回覆錯誤: {} {}", logPrefix, pushMessageRs.getCode(), pushMessageRs.getMessage());
                String motp = pushMessageRs.getData() != null ? pushMessageRs.getData().getOtp() : StringUtils.EMPTY;
                if (StringUtils.isNotBlank(motp)) {
                    logger.info("{} 發送推播OTP API回覆錯誤，但有回傳OTP: {}", logPrefix, motp);
                    carrierData.setMotp(motp);
                    carrierData.setTitle(StringUtils.replace(carrierData.getTitle(), MOTP_REPLACER, motp));
                    carrierData.setMessage(StringUtils.replace(carrierData.getMessage(), MOTP_REPLACER, motp));
                    carrierData.setPopup(StringUtils.replace(carrierData.getPopup(), MOTP_REPLACER, motp));
                    carrierData.setCarrierStatus(MotpCarrierStatus.SEND_FAIL_WITH_OTP);
                    carrierData.setCarrierStatusDesc(pushMessageRs.getMessage());
                    carrierData.setVerifyCount(0);
                    sendResult = MotpSendResult.MOTP_SEND_FAIL_WITH_OTP;
                }
                else {
                    logger.info("{} 發送推播OTP API回覆錯誤，且沒有回傳OTP，流程終止", logPrefix);
                    motpTransData.setStatus(MotpTxStatus.SYSTEM_ERROR_MOTP);
                    carrierData.setCarrierStatus(MotpCarrierStatus.SEND_FAIL_WITHOUT_OTP);
                    carrierData.setCarrierStatusDesc(pushMessageRs.getMessage());
                    sendResult = MotpSendResult.MOTP_SEND_FAIL_WITHOUT_OTP;
                }
            }
            else {
                logger.info("{} 發送推播OTP API回覆錯誤: {} {}，流程終止", logPrefix, pushMessageRs.getCode(), pushMessageRs.getMessage());
                motpTransData.setStatus(MotpTxStatus.SYSTEM_ERROR_MOTP);
                carrierData.setCarrierStatus(MotpCarrierStatus.SYSTEM_ERROR_PUSH_MOTP);
                carrierData.setCarrierStatusDesc(pushMessageRs.getMessage());
                sendResult = MotpSendResult.SYSTEM_ERROR_PUSH_MOTP;
            }

            logger.info("{} 更新MOTP發送結果 MOTP_VERIFY_CARRIER.CARRIER_STATUS:{}, CARRIER_STATUS_DESC:{}", logPrefix, carrierData.getCarrierStatus(), carrierData.getCarrierStatusDesc());
            motpVerifyCarrierRepository.save(carrierData);

            boolean isNewInboxRule = true;
            String newInboxRule = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "NEW_INBOX_MESSAGE_RULE");

            if (StringUtils.equals("N", newInboxRule)) {
                isNewInboxRule = false;
            }

            // 若為可繼續驗證狀態，寫入一筆記錄到PERSON_NOTIFICATION_RECORD
            if (sendResult.isValidStatus()) {
                logger.info("{} 成功發送推播OTP，寫入PERSON_NOTIFICATION_RECORD", logPrefix);
                Date now = new Date();
                PersonNotificationRecordEntity entity = new PersonNotificationRecordEntity();
                // 公司鍵值
                entity.setCompanyKey(motpTransData.getCompanyKey());
                // 使用者鍵值
                entity.setUserKey(motpTransData.getUserKey());
                // 是否要發送推播
                entity.setIsPush(0);
                // 推播代碼
                entity.setPushCode(PushCodeType.PERSONAL.getCode());
                // 業務別
                entity.setBusType(BusType.PERSONAL.getCode());
                // 推播優先序
                entity.setPriority(0);
                // 推播訊息
                entity.setPushMessage(carrierData.getMessage());
                // 標題訊息
                entity.setTitleMessage(carrierData.getTitle());
                // 重要訊息
                entity.setMessage(carrierData.getMessage());
                // 傳送狀態
                entity.setSendStatus(NotificationSendStatus.PROCESS.getCode());
                // 狀態
                entity.setStatus(NotificationStatus.OPEN.getCode());
                // 是否已讀
                entity.setIsRead(0);
                // 開始日期時間
                entity.setStartDate(now);
                // 結束日期時間
                entity.setEndDate(getEndDate());
                // 更新時間
                entity.setUpdateTime(now);
                // 播播時間
                entity.setPushTime(now);
                // 建立時間
                entity.setCreateTime(now);
                if (!isNewInboxRule) {
                    personNotificationRecordRepository.save(entity);
                }
            }
            // 不可繼續驗證狀態，更新MOTP_TRANS_DATA
            else {
                logger.info("{} 更新MOTP發送結果 MOTP_TRANS_DATA.STATUS:{}", logPrefix, motpTransData.getStatus());
                motpTransDataRepository.save(motpTransData);
            }

            result.setSendResult(sendResult);
            result.setExpireTime(motpPropertyService.getMotpExpireTimes());
        }
        catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ActionException e) {
            logger.error("[MotpServiceHelper][sendPushOtp] 發送推播OTP認證發生非預期錯誤", e);
            motpTransData.setStatus(MotpTxStatus.SYSTEM_ERROR);
            carrierData.setCarrierStatus(MotpCarrierStatus.SYSTEM_ERROR);
            carrierData.setCarrierStatusDesc("發送推播OTP認證發生非預期錯誤");
            motpTransDataRepository.save(motpTransData);
            motpVerifyCarrierRepository.save(carrierData);
            throw ExceptionUtils.getActionException(AIBankErrorCode.MOTP_SEND_OTP_UNKNOWN_ERROR);
        }
        return result;
    }

    private Date getEndDate() {
        String daysStr = systemParamCacheManager.getValue("AIBANK", "NOTIFICATION_DURATION");
        int day = 90;
        if (daysStr != null) {
            day = Integer.parseInt(daysStr);
        }
        return DateUtils.addDays(new Date(), day);
    }

    /**
     * 發送離線載具OTP認證
     * 
     * @param condition
     * @return
     * @throws ActionException
     */
    public SendOfflineOtpResult sendOfflineOtp(SendOfflineOtpCondition condition) throws ActionException {

        SendOfflineOtpResult result = new SendOfflineOtpResult();
        String logPrefix = "[MotpServiceHelper][sendOfflineOtp]";

        MotpTransDataEntity motpTransData = condition.getMotpTransData();
        MotpVerifyCarrierEntity carrierData = condition.getCarrierData();

        try {
            carrierData.setCarrierStatus(MotpCarrierStatus.GENERATE_SUCCESS);
            carrierData.setVerifyCount(0);
            motpVerifyCarrierRepository.save(carrierData);
            logger.info("{} 更新MOTP發送結果 MOTP_VERIFY_CARRIER.CARRIER_STATUS:{}, CARRIER_STATUS_DESC:{}", logPrefix, carrierData.getCarrierStatus(), carrierData.getCarrierStatusDesc());

            result.setSuccess(true);
            result.setExpireTime(motpPropertyService.getMotpExpireTimes());
        }
        catch (RuntimeException e) {
            logger.error("[MotpServiceHelper][sendOfflineOtp] 發送離線載具OTP認證發生非預期錯誤", e);
            motpTransData.setStatus(MotpTxStatus.SYSTEM_ERROR);
            carrierData.setCarrierStatus(MotpCarrierStatus.SYSTEM_ERROR);
            carrierData.setCarrierStatusDesc("發送離線載具OTP認證發生非預期錯誤");
            motpTransDataRepository.save(motpTransData);
            motpVerifyCarrierRepository.save(carrierData);
            throw ExceptionUtils.getActionException(AIBankErrorCode.MOTP_SEND_OTP_UNKNOWN_ERROR);
        }
        return result;
    }

    /**
     * 取得交易認證資訊
     * 
     * @param condition
     * @return
     * @throws ActionException
     */
    public FindMotpTransDataResult findMotpTransData(FindMotpTransDataCondition condition) throws ActionException {

        FindMotpTransDataResult result = new FindMotpTransDataResult();
        String logPrefix = "[MotpServiceHelper][getAuthData]";

        // 【FORTIFY：Access Control: Database】誤判，條件為主鍵
        MotpTransDataEntity motpTransData = motpTransDataRepository.findById(condition.getMotpTransDataKey()).orElse(null);

        // 找不到交易認證資訊
        if (motpTransData == null) {
            logger.info("{} 查無交易認證資訊", logPrefix);
            throw new ActionException(AIBankErrorCode.MOTP_TRANS_DATA_NOT_FOUND);
        }

        // 交易認證資訊狀態有誤
        if (motpTransData.getStatus() != MotpTxStatus.INIT) {
            logger.info("{} 交易認證資訊狀態有誤", logPrefix);
            throw new ActionException(AIBankErrorCode.MOTP_TRANS_DATA_STATUS_ERROR);
        }

        // 【FORTIFY：Access Control: Database】誤判，條件為主鍵
        MotpVerifyCarrierEntity carrierData = motpVerifyCarrierRepository.findById(condition.getMotpVerifyCarrierKey()).orElse(null);

        // 找不到交易驗證之載具資訊
        if (carrierData == null) {
            logger.info("{} 找不到交易驗證之載具資訊", logPrefix);
            throw new ActionException(AIBankErrorCode.MOTP_CARRIER_DATA_NOT_FOUND);
        }

        result.setMotpTransData(motpTransData);
        result.setCarrierData(carrierData);

        return result;
    }

    /**
     * 驗證推播OTP
     * 
     * @param condition
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws ActionException
     */
    public VerifyPushOtpResult verifyPushOtp(VerifyPushOtpCondition condition) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ActionException {

        VerifyPushOtpResult result = new VerifyPushOtpResult();
        String logPrefix = "[MotpServiceHelper][verifyPushOtp]";

        Date verifyTime = new Date();
        MotpVerifyResultType verifyResult = null;
        MotpTransDataEntity motpTransData = condition.getMotpTransData();
        MotpVerifyCarrierEntity carrierData = condition.getCarrierData();

        try {
            // 進入驗證，驗證次數+1，記錄驗證時間
            logger.info("{} 設定驗證檢核資訊", logPrefix);
            motpTransData.setAuthTimestamp(verifyTime);
            motpTransData.setAuthCount(motpTransData.getAuthCount() + 1);
            carrierData.setVerifyTimestamp(verifyTime);
            carrierData.setVerifyCount(carrierData.getVerifyCount() + 1);
            carrierData.setCarrierStatus(MotpCarrierStatus.VERIFYING);
            motpTransDataRepository.save(motpTransData);
            motpVerifyCarrierRepository.save(carrierData);

            // 驗證是否已逾時
            if (verifyTime.after(motpTransData.getAuthDeadline())) {

                logger.info("{} 驗證已逾時", logPrefix);
                motpTransData.setStatus(MotpTxStatus.EXPIRE);
                carrierData.setCarrierStatus(MotpCarrierStatus.EXPIRE);
                verifyResult = MotpVerifyResultType.MOTP_VERIFY_EXPIRE;
            }
            else {
                MotpLogDataModel motpLogData = motpLogDataHelper.createMotpLog(MotpLogActionType.API_VERIFY_OTP, condition.getAccount(), "",

                        condition.getMotpTransData().getCompanyKey(), condition.getMotpTransData().getUserKey(),

                        "MotpProxyService", "callAPIPushMessage");

                // 全景MOTP API - 驗證OTP
                logger.info("{} 發送全景API OTP驗證", logPrefix);
                VerifyOtpRs verifyOtpRs = motpProxyService.callAPIVerifyOtp(condition.getAccount(), condition.getGroup(), motpTransData.getChallenge(), condition.getUserInputOtp(), null, motpLogData);

                // 驗證成功，更新驗證次數與狀態
                if (StringUtils.equals(MotpStatusType.MOTP_STATUS_OK.getCode(), verifyOtpRs.getCode())) {
                    motpTransData.setStatus(MotpTxStatus.VERIFY_SUCCESS);
                    carrierData.setCarrierStatus(MotpCarrierStatus.VERIFY_SUCCESS);
                    verifyResult = MotpVerifyResultType.MOTP_VERIFY_SUCCESS;
                }
                // 驗證失敗，更新驗證失敗狀態，如超過可驗證次數，則回覆無法繼續驗證
                else if (StringUtils.equals(MotpStatusType.MOTP_OTP_INCORRECT.getCode(), verifyOtpRs.getCode())) {
                    carrierData.setCarrierStatus(MotpCarrierStatus.VERIFY_FAIL);
                    if (motpTransData.getAuthCount() >= MOTP_VERIFY_LIMIT_TIMES) {
                        motpTransData.setStatus(MotpTxStatus.VERIFY_FAIL_EXCEED_LIMITTIMES);
                        verifyResult = MotpVerifyResultType.MOTP_VERIFY_EXCEED_FAIL_TIMES;
                    }
                    else {
                        verifyResult = MotpVerifyResultType.MOTP_VERIFY_FAIL;
                    }
                }
                // 驗證逾時
                else if (StringUtils.equals(MotpStatusType.MOTP_PASSWORD_HAS_BEEN_EXPIRED.getCode(), verifyOtpRs.getCode())) {
                    motpTransData.setStatus(MotpTxStatus.EXPIRE);
                    carrierData.setCarrierStatus(MotpCarrierStatus.EXPIRE);
                    verifyResult = MotpVerifyResultType.MOTP_VERIFY_EXPIRE;
                }
                // 其他，回覆無法繼續驗證
                else {
                    motpTransData.setStatus(MotpTxStatus.SYSTEM_ERROR_MOTP);
                    carrierData.setCarrierStatus(MotpCarrierStatus.SYSTEM_ERROR_VALIDATE_MOTP);
                    carrierData.setCarrierStatusDesc("[" + verifyOtpRs.getCode() + "]" + verifyOtpRs.getMessage());
                    verifyResult = MotpVerifyResultType.MOTP_VERIFY_OTHER_FAIL;
                }

                result.setCode(verifyOtpRs.getCode());
                result.setMessage(verifyOtpRs.getMessage());
            }

            logger.info("{} 更新MOTP驗證結果 MOTP_TRANS_DATA.STATUS:{}", logPrefix, motpTransData.getStatus());
            logger.info("{} 更新MOTP驗證結果 MOTP_VERIFY_CARRIER.CARRIER_STATUS:{}, CARRIER_STATUS_DESC:{}", logPrefix, carrierData.getCarrierStatus(), carrierData.getCarrierStatusDesc());
            motpTransDataRepository.save(motpTransData);
            motpVerifyCarrierRepository.save(carrierData);

            result.setVerifyResult(verifyResult);
        }
        catch (RuntimeException e) {
            logger.error("[MotpServiceHelper][verifyPushOtp] 驗證推播OTP發生非預期錯誤", e);
            motpTransData.setStatus(MotpTxStatus.SYSTEM_ERROR);
            carrierData.setCarrierStatus(MotpCarrierStatus.SYSTEM_ERROR);
            carrierData.setCarrierStatusDesc("驗證推播OTP發生非預期錯誤");
            motpTransDataRepository.save(motpTransData);
            motpVerifyCarrierRepository.save(carrierData);
            throw ExceptionUtils.getActionException(AIBankErrorCode.MOTP_VERIFY_OTP_UNKNOWN_ERROR);
        }

        return result;
    }

}