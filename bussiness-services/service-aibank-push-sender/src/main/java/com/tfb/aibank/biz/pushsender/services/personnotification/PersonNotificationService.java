package com.tfb.aibank.biz.pushsender.services.personnotification;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.biz.pushsender.repository.aib.PersonNotificationRecordRepository;
import com.tfb.aibank.biz.pushsender.repository.dto.PersonNotificationDeviceInfoDto;
import com.tfb.aibank.biz.pushsender.resource.PushResource;
import com.tfb.aibank.biz.pushsender.resource.dto.ReceivePushV2Request;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.type.RowIdPrefixType;

import feign.FeignException;
import feign.codec.DecodeException;

// @formatter:off
/**
 * @(#)PersonNotificationService.java
 * 
 * <p>Description:推播「個人化訊息」</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/01, Edward Tien    
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class PersonNotificationService {

    private static IBLog logger = IBLog.getLog(PersonNotificationService.class);

    private PersonNotificationRecordRepository repository;

    private PushResource pushResource;

    private SystemParamCacheManager sysParamCacheManager;

    public PersonNotificationService(PersonNotificationRecordRepository repository, PushResource pushResource, SystemParamCacheManager sysParamCacheManager) {
        this.repository = repository;
        this.pushResource = pushResource;
        this.sysParamCacheManager = sysParamCacheManager;
    }

    public void handle() {
        // 推播識別鍵值
        String pushKey = UUID.randomUUID().toString();
        // 系統時間
        Date sysDate = new Date();
        String datetime = DateFormatUtils.SQL_DATETIME_FORMAT.format(sysDate);
        String time = DateFormatUtils.SQL_TIME_FORMAT.format(sysDate);
        // 請取「等待發送」或「發送失敗」的訊息清單，更新狀態為「正在發送(SEND_STATUS=P)」，最多1000筆
        int resendCount = ConvertUtils.str2Int(this.sysParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "PUSH_RESEND_COUNT"), 2);
        long start = System.currentTimeMillis();
        // JPQL 沒辦法算 DOY, 算好再傳入
        int msgDoyEnd = DateUtils.getDayOfYear(sysDate);
        Date minStartDate = DateUtils.addHours(sysDate, -2);
        int msgDoyStart = DateUtils.getDayOfYear(minStartDate);

        // 是否跨年
        boolean isCrossYear = msgDoyStart > msgDoyEnd;
        
        int updateCnt = isCrossYear ? repository.updateSendStatusCrossYear(new Timestamp(sysDate.getTime()), datetime, time, resendCount, pushKey) : 
        	repository.updateSendStatus(new Timestamp(sysDate.getTime()), datetime, time, resendCount, pushKey);
        logger.info("【個人化訊息】更新傳送狀態=P的資料筆數，共 {} 筆，time: {}", updateCnt, System.currentTimeMillis() - start);
        // 讀取待發送訊息資料
        List<PersonNotificationDeviceInfoDto> dataList = isCrossYear ? repository.fetchPersonNotificationDataCrossYear(pushKey, msgDoyStart, msgDoyEnd) : 
        		repository.fetchPersonNotificationData(pushKey, msgDoyStart, msgDoyEnd);
        
        logger.info("【個人化訊息】此次處理的資料筆數，共 {} 筆", dataList.size());
        String appName = this.sysParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "PUSH_APPNAME");
        if (CollectionUtils.isNotEmpty(dataList)) {
            // 一個迴圈只判斷一次時間
            boolean inAllowTimeRange = isInAllowTimeRange();
            logger.debug("is in allow time range: {}", inAllowTimeRange);
            for (PersonNotificationDeviceInfoDto data : dataList) {
                int notifyPass = ConvertUtils.integer2Int(data.getNotifyPass());
                if ((notifyPass == 1) || (notifyPass == 2 && inAllowTimeRange)) {
                    // 逐筆處理
                    ReceivePushV2Request request = buildRequest(data, appName);
                    try {
                        pushResource.receive(request);
                    }
                    catch (FeignException e) {
                        if (!(e instanceof DecodeException)) {
                            try {
                                // 發生 FeignException 且不是 DecodeException，要回寫狀態為 F
                                repository.updateSendStatusByKey("F", data.getPersonNotificationRecordKey());
                            }
                            catch (Exception e1) {
                                logger.error("error update send status", e1);
                            }
                        }
                        logger.error("error sending message to push server", e);
                    }
                }
                else { // 在夜間勿擾模式中
                    repository.updateSendStatusByKey("F", data.getPersonNotificationRecordKey());
                }
            }
        }
    }

    private boolean isInAllowTimeRange() {
        Calendar cal = Calendar.getInstance();
        DateUtils.clearTime(cal);
        cal.set(Calendar.HOUR_OF_DAY, 8);
        Date startDate = cal.getTime();
        cal.set(Calendar.HOUR_OF_DAY, 22);
        Date endDate = cal.getTime();

        Date now = new Date();

        return !now.before(startDate) && !now.after(endDate);
    }

    private ReceivePushV2Request buildRequest(PersonNotificationDeviceInfoDto data, String appName) {

        ReceivePushV2Request request = new ReceivePushV2Request();
        request.setAppName(appName);
        request.setBroadcast(false);
        request.setPriority(data.getPriority());
        request.setRowId(RowIdPrefixType.PERSON.getPrefix() + data.getPersonNotificationRecordKey());
        request.setPushTarget(data.getPushToken());
        request.setPushTitle(data.getTitleMessage());
        request.setPushContent(data.getPushMessage());
        request.setPlatform(data.getDevicePlatform());

        return request;
    }

}
