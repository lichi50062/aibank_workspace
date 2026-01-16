package com.tfb.aibank.chl.creditcardactivities.ot006.task;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.activityonline.ActivityOnlineCacheManager;
import com.tfb.aibank.chl.creditcardactivities.resource.SystemConfigResource;
import com.tfb.aibank.common.code.AIBankConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcardactivities.error.ErrorCode;
import com.tfb.aibank.chl.creditcardactivities.ot006.model.NCAOT006020Rq;
import com.tfb.aibank.chl.creditcardactivities.ot006.model.NCAOT006020Rs;
import com.tfb.aibank.chl.creditcardactivities.ot006.model.NCAOT006Activity;
import com.tfb.aibank.chl.creditcardactivities.ot006.model.NCAOT006Cache;
import com.tfb.aibank.chl.creditcardactivities.ot006.service.NCAOT006Service;
import com.tfb.aibank.chl.creditcardactivities.resource.CreditCardResource;
import com.tfb.aibank.chl.creditcardactivities.resource.dto.CEW223RRequest;
import com.tfb.aibank.chl.creditcardactivities.resource.dto.CEW223RRequestRepeat;
import com.tfb.aibank.chl.creditcardactivities.resource.dto.CEW223RResponse;
import com.tfb.aibank.chl.creditcardactivities.resource.dto.CEW223RResponseRepeat;
import com.tfb.aibank.chl.creditcardactivities.resource.dto.AddActivetyOnlineRecordRequest;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NCCOT006020Task.java
 * 
 * <p>Description:信用卡活動登錄 020 結果頁</p>
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
public class NCAOT006020Task extends AbstractAIBankBaseTask<NCAOT006020Rq, NCAOT006020Rs> {

    private static final IBLog logger = IBLog.getLog(NCAOT006020Task.class);

    @Autowired
    private CreditCardResource creditCardResource;

    @Autowired
    private ActivityOnlineCacheManager activityOnlineCacheManager;

    @Autowired
    private SystemConfigResource systemConfigResource;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Override
    public void validate(NCAOT006020Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NCAOT006020Rq rqData, NCAOT006020Rs rsData) throws ActionException {
        AIBankUser aibankUser = getLoginUser();
        logger.info("===========%n 登錄request{}", rqData.getActivityCode());
        /** 熱門活動流程 */
        if (rqData.isHotActivity()) {
            logger.info("===========%n 信用卡登錄活動-熱門活動");
            rsData.setExceedLimit(false);

            /** 新增線上人數記錄 */
            AddActivetyOnlineRecordRequest requestOnLineRecord = new AddActivetyOnlineRecordRequest();
            requestOnLineRecord.setCustId(aibankUser.getCustIdWithDup());
            requestOnLineRecord.setUserId(aibankUser.getUserId());
            requestOnLineRecord.setCompanyKind(aibankUser.getCompanyKind());
            creditCardResource.addActivetyOnlineRecord(requestOnLineRecord);

            /** 快取人數上限判斷 */
            if (activityOnlineCacheManager.isExceedLimitForHotActivity()) {
                logger.info("===========%n 觸發登錄按鈕人數卡控");
                rsData.setExceedLimit(true);
                logger.info("===========%n 觸發登錄按鈕人數卡控 response {}", IBUtils.attribute2Str(rsData));
                return;
            }

            List<CEW223RRequestRepeat> repeat = new ArrayList<CEW223RRequestRepeat>();
            if (rqData.getActivityCode() == null || rqData.getActivityCode().size() == 0) {
                logger.error("===========%n 沒有選擇活動");
                throw new ActionException(ErrorCode.NO_ACTIVITY_SELECTED);
            }

            for (String activityCode : rqData.getActivityCode()) {
                String registeredStatus = systemConfigResource.queryUserRegistered(aibankUser.getCustId(), aibankUser.getUidDup(), aibankUser.getUserId(), aibankUser.getCompanyKind(), activityCode);
                if ("Y".equals(registeredStatus)) {
                    logger.info("===========%n 使用者登錄狀態已登錄 {}", activityCode);
                    List<NCAOT006Activity> ncaot006Activities = systemConfigResource.queryActivityInfo();
                    rsData.setSuccessactivity(ncaot006Activities);
                    rsData.setTxTime(DateFormatUtils.CE_DATETIME_FORMAT.format(new Date()));
                    logger.info("===========%n 使用者登錄狀態已登錄 response {}", IBUtils.attribute2Str(rsData));
                    return;
                }
                CEW223RRequestRepeat aRepeat = new CEW223RRequestRepeat();
                aRepeat.setUsced1(activityCode);
                aRepeat.setSelect("Y");
                repeat.add(aRepeat);
            }

            /** 1正式登錄 只有1回已額滿  2這個ID已登錄  3這個ID可登錄的所有活動 */
            CEW223RRequest request = new CEW223RRequest();
            request.setCustId(aibankUser.getCustId());
            request.setTxType("1");
            request.setRepeat(repeat);

            int selectedDelay = 0;
            try {
                String hotActivityDelay = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "HOT_ACTIVITY_DELAY");
                if ("Y".equals(hotActivityDelay)) {
                    logger.info("===========%n 執行抽牌邏輯");
                    int[] weights = convertToIntArray(systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "HOT_ACTIVITY_DELAY_WEIGHT"));
                    int[] delays = convertToIntArray(systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "HOT_ACTIVITY_DELAY_TIMES"));
                    logger.info("===========%n 抽牌權重: {}", Arrays.toString(weights));
                    logger.info("===========%n 抽牌延遲: {}", Arrays.toString(delays));
                    int totalWeight = Arrays.stream(weights).sum();
                    int randomWeight = new Random().nextInt(totalWeight);
                    int accumulatedWeight = 0;
                    for (int i = 0; i < weights.length; i++) {
                        accumulatedWeight += weights[i];
                        if (randomWeight < accumulatedWeight) {
                            selectedDelay = delays[i];
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                logger.warn("延遲時間計算錯誤，使用預設延遲 0ms", e);
                selectedDelay = 0;
            }

            logger.info("===========%n 非同步延遲 {} 毫秒後發送請求", selectedDelay);

            CEW223RResponse response = null;
            try {
                response = sendRequestWithDelay(request, selectedDelay).get();
            } catch (Exception e) {
                logger.error("===========%n 非同步請求失敗", e);
            }

            if (response == null) {
                logger.error("===========%n 活動登錄失敗");
                throw new ActionException(ErrorCode.ACTIVITY_REGISTER_ERROR);
            }

            /** 已額滿 */
            updateActivityForFull(rqData.getActivityCode(), response);

            /** 失敗活動 */
            List<NCAOT006Activity> failactivity = new ArrayList<NCAOT006Activity>();

            /** 成功活動 */
            List<NCAOT006Activity> successactivity = new ArrayList<NCAOT006Activity>();

            NCAOT006Cache cache = getCache(NCAOT006Service.NCAOT006_CACHE_KEY, NCAOT006Cache.class);

            for (CEW223RResponseRepeat aRepeat : response.getRepeat()) {

                NCAOT006Activity activity = getActivity(aRepeat.getUscde1(), cache.getActivity());

                if (activity == null) {
                    throw new ActionException(ErrorCode.ACTIVITY_REGISTER_ERROR);
                }
                activity.setStatus(aRepeat.getResult());
                if ("登錄成功".equals(aRepeat.getResult().trim()) || "活動重覆登錄".equals(aRepeat.getResult().trim())) {
                    logger.info("===========%n 登錄成功或活動重覆登錄");
                    activity.setRegisterTime(aRepeat.getDate());
                    successactivity.add(activity);
                    /** 寫redis登錄成功 */
                    systemConfigResource.setRegisterUser(aibankUser.getCustId(), aibankUser.getUidDup(), aibankUser.getUserId(), aibankUser.getCompanyKind(), aRepeat.getUscde1(), "Y");
                    logger.info("===========%n 登錄成功並寫入redis CustId {}、活動代碼 {}", aibankUser.getCustId(), aRepeat.getUscde1());
                } else {
                    /** 寫redis登錄失敗 */
                    systemConfigResource.setRegisterUser(aibankUser.getCustId(), aibankUser.getUidDup(), aibankUser.getUserId(), aibankUser.getCompanyKind(), aRepeat.getUscde1(), "N");
                    logger.info("===========%n 登錄失敗並寫入redis CustId {}、活動代碼 {}", aibankUser.getCustId(), aRepeat.getUscde1());
                    failactivity.add(activity);
                }
            }
            rsData.setFailactivity(failactivity);
            rsData.setSuccessactivity(successactivity);
            rsData.setTxTime(DateFormatUtils.CE_DATETIME_FORMAT.format(new Date()));
            logger.info("===========%n 信用卡登錄活動-熱門活動 response {}", IBUtils.attribute2Str(rsData));
        } else {
            logger.info("===========%n 正常登錄流程");
            //正常流程
            List<CEW223RRequestRepeat> repeat = new ArrayList<CEW223RRequestRepeat>();
            if (rqData.getActivityCode() == null || rqData.getActivityCode().size() == 0) {
                throw new ActionException(ErrorCode.NO_ACTIVITY_SELECTED);
            }

            for (String activityCode : rqData.getActivityCode()) {
                CEW223RRequestRepeat aRepeat = new CEW223RRequestRepeat();
                aRepeat.setUsced1(activityCode);
                aRepeat.setSelect("Y");
                repeat.add(aRepeat);
            }

            CEW223RRequest request = new CEW223RRequest();
            request.setCustId(aibankUser.getCustId());
            request.setTxType("1");
            request.setRepeat(repeat);

            CEW223RResponse response = creditCardResource.sendCEW223R(request);

            /** 失敗活動 */
            List<NCAOT006Activity> failactivity = new ArrayList<NCAOT006Activity>();

            /** 成功活動 */
            List<NCAOT006Activity> successactivity = new ArrayList<NCAOT006Activity>();

            NCAOT006Cache cache = getCache(NCAOT006Service.NCAOT006_CACHE_KEY, NCAOT006Cache.class);

            for (CEW223RResponseRepeat aRepeat : response.getRepeat()) {
                NCAOT006Activity activity = getActivity(aRepeat.getUscde1(), cache.getActivity());

                if (activity == null) {
                    throw new ActionException(ErrorCode.ACTIVITY_REGISTER_ERROR);
                }

                activity.setStatus(aRepeat.getResult());
                if (aRepeat.getResult().trim().equals("登錄成功")) {
                    activity.setRegisterTime(aRepeat.getDate());
                    successactivity.add(activity);
                } else {
                    failactivity.add(activity);
                }
            }
            rsData.setFailactivity(failactivity);
            rsData.setSuccessactivity(successactivity);
            rsData.setTxTime(DateFormatUtils.CE_DATETIME_FORMAT.format(new Date()));
        }
    }

    private NCAOT006Activity getActivity(String activityCode, List<NCAOT006Activity> activity) {
        for (NCAOT006Activity a : activity) {
            if (a.getActivityCode().equals(activityCode)) {
                return a;
            }
        }
        return null;
    }

    /**
     * 更新已額滿
     * @param activityCode
     * @param cew223r
     */
    private void updateActivityForFull(List<String> activityCode, CEW223RResponse cew223r) {
        logger.info("===========%n 更新已額滿活動 {}", activityCode);
        if (activityCode == null || activityCode.size() == 0){
            logger.error("===========%n 沒有選擇活動");
            return;
        }

        if (cew223r == null || cew223r.getRepeat() == null || cew223r.getRepeat().size() == 0){
            logger.error("===========%n 沒有回傳活動資訊");
            return;
        }
        for (String atc : activityCode) {
            for (CEW223RResponseRepeat repeat : cew223r.getRepeat()) {
                if (atc != null && atc.equals(repeat.getUscde1())) {
                    if ("登錄人數已額滿".equals(repeat.getResult().trim())) {
                        systemConfigResource.setActivityFull(atc);
                    }
                }
            }
        }
    }

    @Async
    public CompletableFuture<CEW223RResponse> sendRequestWithDelay(CEW223RRequest request, int delayMs) {
        logger.info("===========%n 非同步延遲 {} 毫秒後發送請求", delayMs);
        try {
            if (delayMs > 0) {
                TimeUnit.MILLISECONDS.sleep(delayMs);
            }
            CEW223RResponse response = creditCardResource.sendCEW223R(request);
            return CompletableFuture.completedFuture(response);
        } catch (Exception e) {
            logger.error("===========%n 非同步延遲或請求失敗", e);
            return CompletableFuture.completedFuture(null); // 錯誤隔離
        }
    }

    /**
     * 將逗號分隔的數字String轉換為int[]
     */
    private int[] convertToIntArray(String str) {
        if (StringUtils.isBlank(str)) {
            return new int[0];
        }

        try {
            return Arrays.stream(str.split(","))
                    .map(String::trim)
                    .filter(StringUtils::isNotBlank)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (NumberFormatException e) {
            logger.error("===========%n 將逗號分隔的數字String轉換為int[]失敗: {}", str, e);
            return new int[0];
        }
    }
}
