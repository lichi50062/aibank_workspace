package com.tfb.aibank.chl.creditcardactivities.ot006.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.chl.creditcardactivities.resource.SystemConfigResource;
import com.tfb.aibank.chl.creditcardactivities.resource.dto.*;
import com.tfb.aibank.common.code.AIBankConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.activityonline.ActivityOnlineCacheManager;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcardactivities.error.ErrorCode;
import com.tfb.aibank.chl.creditcardactivities.ot006.model.NCAOT006010Rq;
import com.tfb.aibank.chl.creditcardactivities.ot006.model.NCAOT006010Rs;
import com.tfb.aibank.chl.creditcardactivities.ot006.model.NCAOT006Activity;
import com.tfb.aibank.chl.creditcardactivities.ot006.model.NCAOT006Cache;
import com.tfb.aibank.chl.creditcardactivities.ot006.service.NCAOT006Service;
import com.tfb.aibank.chl.creditcardactivities.resource.CreditCardResource;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.error.AIBankErrorCode;

// @formatter:off
/**
 * @(#)NCCOT006010Task.java
 *
 * <p>Description:信用卡活動登錄 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/12, John Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCAOT006010Task extends AbstractAIBankBaseTask<NCAOT006010Rq, NCAOT006010Rs> {

    protected static IBLog logger = IBLog.getLog(NCAOT006010Task.class);

    @Autowired
    private CreditCardResource creditCardResource;

    @Autowired
    private SystemConfigResource systemConfigResource;

    @Autowired
    private UserDataCacheService userDataCacheService;

    @Autowired
    private ActivityOnlineCacheManager activityOnlineCacheManager;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Override
    public void validate(NCAOT006010Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NCAOT006010Rq rqData, NCAOT006010Rs rsData) throws ActionException {
        AIBankUser loginUser = getLoginUser();
        logger.info("===========%n 信用卡登錄活動010-getReloadButton{}", rqData.getReloadButton());

        /** 附卡人引導至「共通錯誤頁」 */
        if (!userDataCacheService.isPrimaryCard(loginUser, getLocale())) {
            throw new ActionException(AIBankErrorCode.PRIMARY_CARDHOLDER_ONLY);
        }

        /** 是否為特殊戶或未有信用卡 */
        userDataCacheService.validateCreditCardStatus(getLoginUser());

        /** 未持有信用卡 */
        userDataCacheService.getAllCreditCards(loginUser, getLocale());
        try {

            /** 是否在熱門活動區間 */
            Boolean activityPeriod = isInActivityPeriod();
            rsData.setHotActivity(activityPeriod);

            List<NCAOT006Activity> activities;

            /** 檢查是否要走熱門活動流程 */
            if (!activityPeriod) {
                logger.info("===========%n 熱門活動流程");
                activities = handleNormalActivity(loginUser);
                rsData.setStatus(0);
                if (activities != null) {
                    rsData.setActivity(activities);
                    NCAOT006Cache cacheData = new NCAOT006Cache(activities);
                    setCache(NCAOT006Service.NCAOT006_CACHE_KEY, cacheData);
                }
                return;
            }

            Boolean checkWhiteListAndLimit = true;
            String hotActivityLimitCount = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "HOT_ACTIVITY_LIMIT_COUNT");
            String hotActivityWhiteList = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "HOT_ACTIVITY_WHITE_LIST");

            //判斷是否需要檢核白名單和限制
            if ("0".equals(hotActivityLimitCount) && "0".equals(hotActivityWhiteList)) {
               ;logger.info("===========%n 不需要檢核白名單和限制");
                activities = handleHotActivity(rqData, rsData, loginUser, checkWhiteListAndLimit);
                if (activities != null) {
                    rsData.setActivity(activities);
                    NCAOT006Cache cacheData = new NCAOT006Cache(activities);
                    setCache(NCAOT006Service.NCAOT006_CACHE_KEY, cacheData);
                }
            } else if (!"0".equals(hotActivityLimitCount) || !"0".equals(hotActivityWhiteList)) {
                logger.info("===========%n 需要檢核白名單和限制");
                // 需要檢核白名單和限制
                if (checkWhiteListAndLimit()) {
                    activities = handleHotActivity(rqData, rsData, loginUser, checkWhiteListAndLimit);
                    if (activities != null) {
                        rsData.setActivity(activities);
                        NCAOT006Cache cacheData = new NCAOT006Cache(activities);
                        setCache(NCAOT006Service.NCAOT006_CACHE_KEY, cacheData);
                    }
                } else {
                    logger.info("===========%n 舊流程");
                    activities = handleNormalActivity(loginUser);
                    rsData.setStatus(0);
                    if (activities != null) {
                        rsData.setActivity(activities);
                        NCAOT006Cache cacheData = new NCAOT006Cache(activities);
                        setCache(NCAOT006Service.NCAOT006_CACHE_KEY, cacheData);
                    }
                }
                logger.info("===========%n 010 response{}", IBUtils.attribute2Str(rsData));
            }
        } catch (ServiceException ex) {
            rsData.setStatus(1);
        }
    }

    /**
     * 處理熱門活動流程
     *
     * @param rqData                 請求資料
     * @param rsData                 回應資料
     * @param loginUser              登入使用者
     * @param checkWhiteListAndLimit 是否通過白名單和限制檢查
     * @return 活動清單
     * @throws ActionException
     */
    private List<NCAOT006Activity> handleHotActivity(NCAOT006010Rq rqData, NCAOT006010Rs rsData, AIBankUser loginUser, Boolean checkWhiteListAndLimit) throws ActionException {
        logger.info("===========%n 信用卡登錄活動-熱門活動");

        /** 登錄按鈕開關 */
        Boolean activityButton = systemConfigResource.queryActivityButton();
        rsData.setButtonSwitch(activityButton ? "ON" : "OFF");

        /** ON表示是重新整理 */
        if (rqData.getReloadButton() != null && "ON".equals(rqData.getReloadButton())) {
            return handleReloadButton(rqData, rsData, loginUser);
        }

        /** 檢查redis內是否有活動資訊 */
        List<NCAOT006Activity> activities = systemConfigResource.queryActivityInfo();
        logger.info("===========%n redis內信用卡登錄活動-熱門活動 {} 筆", activities.size());

        if (activities == null || activities.isEmpty()) {
            logger.info("===========%n redis內信用卡登錄活動-無");
            /** 查詢活動 */
            activities = getHotActivity();
            /** 放入redis */
            systemConfigResource.setActivityInfo(activities);
        }

        //處理每個活動的註冊狀態和額滿狀態
        processHotActivities(activities, loginUser);

        rsData.setStatus(0);
        return activities;
    }

    /**
     * 處理重新整理按鈕邏輯
     *
     * @param rqData    請求資料
     * @param rsData    回應資料
     * @param loginUser 登入使用者
     * @return 活動清單
     */
    private List<NCAOT006Activity> handleReloadButton(NCAOT006010Rq rqData, NCAOT006010Rs rsData, AIBankUser loginUser) {
        long startTime = System.currentTimeMillis();
        logger.info("===========%n 執行重新整理");
        logger.info("===========%n 信用卡登錄活動010-getReloadButton{}", rqData.getReloadButton());
        logger.info("===========%n 信用卡登錄活動010-getActivityCode{}", rqData.getActivityCode());
        logger.info("===========%n 重新整理時間", rqData.getActivityCode());
        //===============取活動資訊耗時================
        long startTime1 = System.currentTimeMillis();
        List<NCAOT006Activity> activitiesList = systemConfigResource.queryActivityInfo();
        long infoTime = System.currentTimeMillis() - startTime1;
        //===============取活動資訊耗時================
        long isFullTime = 0;
        long queryRegistered = 0;
        for (NCAOT006Activity ncaot006Activity : activitiesList) {
            if (rqData.getActivityCode().equals(ncaot006Activity.getActivityCode())) {
                //===============查詢額滿耗時================
                long startTime2 = System.currentTimeMillis();
                ncaot006Activity.setFull(systemConfigResource.queryActivityFull(ncaot006Activity.getActivityCode()));
                isFullTime = System.currentTimeMillis() - startTime2;
                //===============查詢額滿耗時================

                long startTime3 = System.currentTimeMillis();
                ncaot006Activity.setRegistered("Y".equals(systemConfigResource.queryUserRegistered(loginUser.getCustId(), loginUser.getUidDup(), loginUser.getUserId(), loginUser.getCompanyKind(), ncaot006Activity.getActivityCode())));
                queryRegistered = System.currentTimeMillis() - startTime3;

            }
        }

        rsData.setActivity(activitiesList);
        logger.info("===========%n 信用卡登錄活動-重新整理 response {}", IBUtils.attribute2Str(rsData));
        long l = System.currentTimeMillis() - startTime;
        if ( l > 3000) {
            logger.info("===========%n 查詢動資訊耗時: {}秒", String.format("%.3f", infoTime / 1000.0));
            logger.info("===========%n 查詢額滿耗時: {}秒", String.format("%.3f", isFullTime / 1000.0));
            logger.info("===========%n 查詢註冊耗時: {}秒", String.format("%.3f", queryRegistered / 1000.0));
            logger.info("===========%n 重新整理耗時: {}秒", String.format("%.3f", l / 1000.0));
        }
        return activitiesList;
    }

    /**
     * 處理熱門活動的註冊狀態和額滿狀態
     *
     * @param activities 活動清單
     * @param loginUser  登入使用者
     */
    private void processHotActivities(List<NCAOT006Activity> activities, AIBankUser loginUser) {
        logger.info("===========%n 處理熱門活動的註冊狀態和額滿狀態");
        for (NCAOT006Activity activity : activities) {
            String activityCode = activity.getActivityCode();

            /** 設定預設值（若尚未設定） */
            String alreadySet = systemConfigResource.queryUserRegistered(loginUser.getCustId(), loginUser.getUidDup(), loginUser.getUserId(), loginUser.getCompanyKind(), activityCode);

             if ("Y".equals(alreadySet)) {
                logger.info("===========%n 使用者已登錄");
                activity.setRegistered(true);
            } else {
                logger.info("===========%n 使用者未登錄");
                activity.setRegistered(false);
            }

            /** 查詢是否額滿 */
            if (systemConfigResource.queryActivityFull(activityCode)) {
                logger.info("===========%n 活動已額滿");
                activity.setFull(true);
            } else {
                activity.setFull(false);
            }
        }
    }

    /**
     * 處理正常活動流程
     *
     * @param loginUser 登入使用者
     * @return 活動清單
     * @throws ActionException
     */
    private List<NCAOT006Activity> handleNormalActivity(AIBankUser loginUser) throws ActionException {
        logger.info("===========%n 信用卡登錄活動-正常流程");

        /** 新增線上人數記錄 */
        AddActivetyOnlineRecordRequest requestOnLineRecord = new AddActivetyOnlineRecordRequest();
        requestOnLineRecord.setCustId(loginUser.getCustIdWithDup());
        requestOnLineRecord.setUserId(loginUser.getUserId());
        requestOnLineRecord.setCompanyKind(loginUser.getCompanyKind());
        creditCardResource.addActivetyOnlineRecord(requestOnLineRecord);

        /** 快取人數上限判斷 */
        if (activityOnlineCacheManager.isExceedLimit()) {
            throw new ActionException(ErrorCode.OVER_ACTIVITY_ONLINE_LIMIT);
        }

        List<NCAOT006Activity> activities = getActivity();

        /** 查詢已登錄活動 */
        CEW223RRequest request2 = new CEW223RRequest();
        request2.setCustId(loginUser.getCustId());
        request2.setTxType("2");
        CEW223RResponse response2 = creditCardResource.sendCEW223R(request2);

        /** 更新已登錄活動 */
        updateActivityforRegistered(activities, response2);

        // 移除已下架活動
        activities = updateActivityforRemoved(activities);

        logger.info("===========%n {}", response2);

        /** 查詢已額滿活動 */
        CEW223RRequest request3 = new CEW223RRequest();
        request3.setCustId(loginUser.getCustId());
        request3.setTxType("3");
        request3.setRepeat(getActivityRepeat(activities));
        CEW223RResponse response3 = creditCardResource.sendCEW223R(request3);
        updateActivityforFull(activities, response3);

        logger.info("===========%n {}", response3);

        return activities;
    }

    /**
     * 移除已下架活動
     *
     * @param activities
     * @return
     */
    private List<NCAOT006Activity> updateActivityforRemoved(List<NCAOT006Activity> activities) {
        if (logger.isDebugEnabled()) {
            logger.info("===========%n 「updateActivityforRemoved」inputed datas: {}", IBUtils.attribute2Str(activities));
        }

        List<NCAOT006Activity> filterActivity = new ArrayList<NCAOT006Activity>();

        // 今天的 00:00:00
        Date today = new Date();
        DateUtils.clearTime(today);

        for (NCAOT006Activity act : activities) {
            // 下架日在 00:00:00 之前的濾掉
            if (today.after(act.getRealDownTime())) {
                // 有登錄的保留
                if (act.isRegistered()) {
                    filterActivity.add(act);
                }
            } else {
                filterActivity.add(act);
            }
        }
        if (logger.isDebugEnabled()) {
            logger.info("===========%n 「updateActivityforRemoved」FILTERED datas: {}", IBUtils.attribute2Str(activities));
        }
        return filterActivity;
    }

    /**
     * 查詢已額滿活動準備的Repeat，有登錄過的資料不上送
     * ＊＊[#8133 有登錄過的不再上送，因此所有過期的(前次過濾已不包含)都不上送]
     *
     * @param activities
     * @return
     */
    private List<CEW223RRequestRepeat> getActivityRepeat(List<NCAOT006Activity> activities) {
        List<CEW223RRequestRepeat> repeat = new ArrayList<CEW223RRequestRepeat>();

        for (NCAOT006Activity activity : activities) {
            if (activity.isRegistered())
                continue;

            CEW223RRequestRepeat aRepeat = new CEW223RRequestRepeat();
            aRepeat.setUsced1(activity.getActivityCode());
            aRepeat.setSelect("Y");
            repeat.add(aRepeat);
        }
        logger.info("===========%n " + repeat);
        return repeat;
    }

    /**
     * 是否已選取
     *
     * @param activity
     * @param cew223r
     */
    private void updateActivityforRegistered(List<NCAOT006Activity> activity, CEW223RResponse cew223r) {

        if (activity == null || activity.size() == 0)
            return;

        if (cew223r == null || cew223r.getRepeat() == null || cew223r.getRepeat().size() == 0)
            return;

        for (NCAOT006Activity act : activity) {
            for (CEW223RResponseRepeat repeat : cew223r.getRepeat()) {
                if (act.getActivityCode() != null && act.getActivityCode().equals(repeat.getUscde1())) {
                    if (repeat.getUscde1().equals(act.getActivityCode())) {
                        act.setRegistered(true);
                        act.setRegisterTime(repeat.getDate().replace("-", "/"));
                    }
                }
            }
        }
    }

    /**
     * 是否已額滿
     *
     * @param activity
     * @param cew223r
     */
    private void updateActivityforFull(List<NCAOT006Activity> activity, CEW223RResponse cew223r) {

        if (activity == null || activity.size() == 0)
            return;

        if (cew223r == null || cew223r.getRepeat() == null || cew223r.getRepeat().size() == 0)
            return;

        for (NCAOT006Activity act : activity) {
            for (CEW223RResponseRepeat repeat : cew223r.getRepeat()) {
                if (act.getActivityCode() != null && act.getActivityCode().equals(repeat.getUscde1())) {
                    if ("登錄人數已額滿".equals(repeat.getResult().trim())) {
                        act.setFull(true);
                    }
                }
            }
        }
    }


    private List<NCAOT006Activity> getActivity() {

        CreditCardActivityResponse activities = creditCardResource.getCreditCardActivity();

        List<NCAOT006Activity> all = new ArrayList<NCAOT006Activity>();

        if (activities != null && activities.getActivity() != null && activities.getActivity().size() > 0) {
            for (CreditCardActivityResponseRepeat activity : activities.getActivity()) {

                NCAOT006Activity ncaot006Activity = new NCAOT006Activity();
                ncaot006Activity.setActivityCode(activity.getActivityCode());
                ncaot006Activity.setActivityContent(activity.getActivityContent());
                ncaot006Activity.setActivityName(activity.getActivityName());
                ncaot006Activity.setActivityUrl(activity.getActivityUrl());
                ncaot006Activity.setDownTime(DateFormatUtils.CE_DATE_FORMAT.format(activity.getDownTime()));
                ncaot006Activity.setRealDownTime(activity.getDownTime());
                ncaot006Activity.setUpTime(DateFormatUtils.CE_DATE_FORMAT.format(activity.getUpTime()));

                all.add(ncaot006Activity);
            }
        }

        return all;
    }

    private List<NCAOT006Activity> getHotActivity() {
        logger.info("===========%n 信用卡登錄活動-熱門活動-查詢活動資訊(DB)");
        CreditCardHotActivityResponse activities = creditCardResource.getCreditCardHotActivity();

        List<NCAOT006Activity> all = new ArrayList<NCAOT006Activity>();

        if (activities != null && activities.getActivity() != null && activities.getActivity().size() > 0) {
            for (CreditCardHotActivityResponseRepeat activity : activities.getActivity()) {

                NCAOT006Activity ncaot006Activity = new NCAOT006Activity();
                ncaot006Activity.setActivityCode(activity.getActivityCode());
                ncaot006Activity.setActivityContent(activity.getActivityContent());
                ncaot006Activity.setActivityName(activity.getActivityName());
                ncaot006Activity.setActivityUrl(activity.getActivityUrl());
                ncaot006Activity.setDownTime(DateFormatUtils.CE_DATE_FORMAT.format(activity.getDownTime()));
                ncaot006Activity.setRealDownTime(activity.getDownTime());
                ncaot006Activity.setUpTime(DateFormatUtils.CE_DATE_FORMAT.format(activity.getUpTime()));

                all.add(ncaot006Activity);
            }
        }
        logger.info("===========%n 信用卡登錄活動-熱門活動-查詢活動資訊(DB) {} 筆", all.size());
        return all;
    }

    /**
     * 信用卡熱門登錄活動時間查詢
     */
    public Boolean isInActivityPeriod() {
        logger.info("===========%n 信用卡熱門登錄活動時間查詢");
        try {

            LocalDateTime now = LocalDateTime.now();
            //yyyy/MM/dd HH:mm
            String hotActivityStartTime = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "HOT_ACTIVITY_START_TIME");
            //yyyy/MM/dd HH:mm
            String hotActivityEndTime = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "HOT_ACTIVITY_END_TIME");
            if (StringUtils.isEmpty(hotActivityStartTime) || StringUtils.isEmpty(hotActivityEndTime)) {
                logger.info("活動時間未設置");
                return false;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            LocalDateTime startTime = LocalDateTime.parse(hotActivityStartTime, formatter);
            LocalDateTime endTime = LocalDateTime.parse(hotActivityEndTime, formatter);
            logger.info("===========%n 活動時間", startTime);
            return now.isAfter(startTime) && now.isBefore(endTime);

        } catch (Exception e) {
            logger.error("===========%n 信用卡熱門登錄活動時間查詢失敗", e);
            return false;
        }
    }

    /**
     * 檢查白名單與限制條件
     *
     * @return true:通過檢查 false:未通過檢查
     */
    private Boolean checkWhiteListAndLimit() {
        logger.info("===========%n 檢查白名單與限制條件開始");
        try {
            //取得當前登入用戶
            AIBankUser loginUser = getLoginUser();
            String custId = getAESCipherUtils().encrypt(loginUser.getCustId());
            //檢查白名單設定
            String whiteList = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "HOT_ACTIVITY_WHITE_LIST");
            boolean hasWhiteListSetting = !StringUtils.isEmpty(whiteList) && !"0".equals(whiteList);
            boolean isInWhiteList = false;

            // 白名單檢查
            if (hasWhiteListSetting) {
                String[] whiteListArray = whiteList.split(",");
                isInWhiteList = Arrays.asList(whiteListArray).contains(custId);
                logger.info("===========%n 白名單檢查結果: {}", isInWhiteList);

                if (!isInWhiteList) {
                    logger.info("===========%n 用戶{}不在白名單中", custId);
                    return false;
                }
            }

            //檢查總次數限制設定
            String limitCountStr = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "HOT_ACTIVITY_LIMIT_COUNT");
            boolean hasLimitSetting = !StringUtils.isEmpty(limitCountStr) && !"0".equals(limitCountStr);
            boolean withinLimit = true;

            //總次數限制檢查
            if (hasLimitSetting) {
                try {
                    int limitCount = Integer.parseInt(limitCountStr);
                    int totalCount = Integer.parseInt(systemConfigResource.getTotalAccessCount());

                    if (totalCount == 0) {
                        //首次存取，設定計數為1
                        systemConfigResource.setTotalAccessCount("1");
                        logger.info("===========%n 初始化總存取次數為1");
                    } else {
                        //檢查是否超過限制
                        int currentCount = totalCount;
                        if (currentCount >= limitCount) {
                            logger.info("===========%n 已超過總次數限制 當前:{} 限制:{}", currentCount, limitCount);
                            withinLimit = false;
                        } else {
                            //更新計數
                            systemConfigResource.setTotalAccessCount(String.valueOf(currentCount + 1));
                            logger.info("===========%n 更新總存取次數為:{}", (currentCount + 1));
                        }
                    }
                } catch (NumberFormatException e) {
                    logger.error("===========%n 總次數限制數值格式錯誤", e);
                    return false;
                }
            }

            //判斷結果
            boolean result;
            if (hasWhiteListSetting && hasLimitSetting) {
                //同時設置白名單和次數限制時，需要都符合
                result = isInWhiteList && withinLimit;
                logger.info("===========%n 白名單和次數限制檢查結果:{}", result);
            } else if (hasWhiteListSetting) {
                //只設置白名單時，只檢查白名單
                result = isInWhiteList;
                logger.info("===========%n 僅白名單檢查結果:{}", result);
            } else if (hasLimitSetting) {
                //只設置次數限制時，只檢查次數
                result = withinLimit;
                logger.info("===========%n 僅次數限制檢查結果:{}", result);
            } else {
                //都沒設置時，返回true
                result = true;
                logger.info("===========%n 無限制條件，檢查通過");
            }

            //記錄檢查結果
            if (!result) {
                logger.info("===========%n 用戶:{} 未通過白名單或限制檢查", custId);
            }

            return result;

        } catch (Exception e) {
            logger.error("===========%n 檢查白名單和總次數限制失敗", e);
            return false;
        }
    }
}
