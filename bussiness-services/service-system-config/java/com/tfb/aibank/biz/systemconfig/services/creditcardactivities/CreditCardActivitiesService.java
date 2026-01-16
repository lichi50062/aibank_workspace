package com.tfb.aibank.biz.systemconfig.services.creditcardactivities;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.biz.systemconfig.services.model.NCAOT006Activity;
import com.tfb.aibank.common.code.AIBankConstants;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class CreditCardActivitiesService {
    private static final IBLog logger = IBLog.getLog(CreditCardActivitiesService.class);
    private static final String PREFIX = "NCAOT006:";
    private static final String USER_REGISTERED = PREFIX + "USER:REGISTERED:";
    private static final String ACTIVITY_FULL = PREFIX + "ACTIVITY:FULL:";
    private static final String ACTIVITY_KEY = PREFIX + "ALL_ACTIVITIES";
    private static final String ACTIVITY_COUNT_BUCKET = PREFIX + "COUNT:BUCKET:";
    private static final int COUNT_BUCKET_SIZE = 45;
    private final RedisTemplate<String, String> redisTemplate;
    private final SystemParamCacheManager systemParamCacheManager;

    public CreditCardActivitiesService(RedisTemplate<String, String> redisTemplate, SystemParamCacheManager systemParamCacheManager) {
        this.redisTemplate = redisTemplate;
        this.systemParamCacheManager = systemParamCacheManager;
    }

    private String getDistributedUserKey(String custIxd, String udiDup, String userIxd, Integer companyKind, String activityCode) {
        return USER_REGISTERED + custIxd + ":" + udiDup + ":" + userIxd + ":" + companyKind + ":" + activityCode;
    }

    /**
     * Redis 存入活動資訊
     *
     * @param activities 活動列表
     * @return 是否成功
     */
    public Boolean setCacheActivityInfo(List<NCAOT006Activity> activities) {
        logger.info("===========%n 存入活動資訊 {}", activities.size());
        if (CollectionUtils.isEmpty(activities)) {
            logger.warn("存入活動資訊-無傳值");
            return false;
        }

        Duration timeout = Duration.ofMinutes(40);

        try {
            Map<String, String> allActivitiesData = new HashMap<>();

            for (NCAOT006Activity activity : activities) {
                String prefix = activity.getActivityCode() + ":";
                allActivitiesData.put(prefix + "activityCode", activity.getActivityCode());
                allActivitiesData.put(prefix + "activityName", activity.getActivityName());
                allActivitiesData.put(prefix + "upTime", activity.getUpTime());
                allActivitiesData.put(prefix + "downTime", activity.getDownTime());
                allActivitiesData.put(prefix + "realDownTime", activity.getRealDownTime());
                allActivitiesData.put(prefix + "activityContent", activity.getActivityContent());
                allActivitiesData.put(prefix + "activityUrl", activity.getActivityUrl());
            }

            // 一次性寫入所有資料
            redisTemplate.opsForHash().putAll(ACTIVITY_KEY, allActivitiesData);
            redisTemplate.expire(ACTIVITY_KEY, timeout);

            logger.info("===========%n 存入活動資訊成功 {}", activities.size());
            return true;
        } catch (Exception e) {
            logger.error("===========%n 存入活動資訊失敗", e);
            return false;
        }
    }

    /**
     * 從 Redis 獲取活動資訊
     *
     * @return 活動列表
     */
    public List<NCAOT006Activity> getCachedActivityInfo() {

        // 一次查詢獲取所有活動資料
        Map<Object, Object> allActivitiesData = redisTemplate.opsForHash().entries(ACTIVITY_KEY);

        if (allActivitiesData.isEmpty()) {
            return Collections.emptyList();
        }

        List<NCAOT006Activity> activities = new ArrayList<>();

        // 按活動代碼分組處理
        Map<String, Map<String, String>> activitiesMap = new HashMap<>();

        for (Map.Entry<Object, Object> entry : allActivitiesData.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();

            // 格式：activityCode:field
            String[] keyParts = key.split(":", 2);
            if (keyParts.length == 2) {
                String activityCode = keyParts[0];
                String field = keyParts[1];

                activitiesMap.computeIfAbsent(activityCode, k -> new HashMap<>())
                        .put(field, value);
            }
        }

        // 轉換為活動物件
        for (Map<String, String> activityData : activitiesMap.values()) {
            NCAOT006Activity activity = new NCAOT006Activity();
            activity.setActivityCode(activityData.get("activityCode"));
            activity.setActivityName(activityData.get("activityName"));
            activity.setUpTime(activityData.get("upTime"));
            activity.setDownTime(activityData.get("downTime"));
            activity.setRealDownTime(activityData.get("realDownTime"));
            activity.setActivityContent(activityData.get("activityContent"));
            activity.setActivityUrl(activityData.get("activityUrl"));
            activities.add(activity);
        }

        logger.info("===========%n 從 Redis 獲取活動資訊 {} 筆", activities.size());
        return activities;
    }

    /**
     * 檢查活動是否已額滿
     *
     * @param activityCode 活動代碼
     * @return true:已額滿 false:未額滿
     */
    public Boolean isActivityFull(String activityCode) {
        logger.info("===========%n 檢查活動是否已額滿-activityCode {}", activityCode);
        long l = System.currentTimeMillis();
        if (StringUtils.isEmpty(activityCode)) {
            return false;
        }
        try {
            String key = ACTIVITY_FULL + activityCode;
            String value = redisTemplate.opsForValue().get(key);
            logger.info("===========%n 檢查活動是否已額滿 {}", value);
            long l1 = System.currentTimeMillis();
            if (l1 - l > 2000) {
                logger.info("===========%n 查詢額滿耗時: {}秒", String.format("%.3f", (l1 - l) / 1000.0));
            }
            return "Y".equals(value);
        } catch (Exception e) {
            logger.error("===========%n 檢查活動是否已額滿失敗: {}", activityCode, e);
            return false;
        }
    }

    /**
     * 設定活動為已額滿
     *
     * @param activityCode 活動代碼
     * @return 是否設定成功
     */
    public Boolean setActivityFull(String activityCode) {
        logger.info("===========%n 設定活動為已額滿 {}", activityCode);
        if (StringUtils.isEmpty(activityCode)) {
            logger.warn("設定活動為已額滿參數{}", activityCode);
            return false;
        }

        try {
            Duration timeout = Duration.ofMinutes(40);
            String key = ACTIVITY_FULL + activityCode;
            redisTemplate.opsForValue().set(key, "Y", timeout);
            return true;
        } catch (Exception e) {
            logger.error("===========%n 設定活動為已額滿失敗: {}", activityCode, e);
            return false;
        }
    }

    /**
     * 檢查用戶是否已登錄
     */
    public String isUserRegistered(String custIxd, String udiDup, String userIxd, Integer companyKind, String activityCode) {
        logger.info("===========%n Redis檢查用戶是否已登錄");

        try {
            long l = System.currentTimeMillis();
            long l1;
            String key = getDistributedUserKey(custIxd, udiDup, userIxd, companyKind, activityCode);
            String value = redisTemplate.opsForValue().get(key);
            if ("Y".equals(value)) {
                logger.info("===========%n Redis檢查用戶是否已登錄成功 - key: {}, value: {}", key, value);
                l1 = System.currentTimeMillis();
                if (l1 - l > 2000) {
                    logger.info("===========%n" +  udiDup +"查詢註冊耗時: {}秒", String.format("%.3f", (l1 - l) / 1000.0));
                }
                return "Y";
            } else {
                logger.info("===========%n Redis檢查用戶是否已登錄成功 - key: {}, value: {}", key, value);

                l1 = System.currentTimeMillis();
                if (l1 - l > 2000) {
                    logger.info("===========%n" +  udiDup +"查詢註冊耗時: {}秒", String.format("%.3f", (l1 - l) / 1000.0));
                }
                return "N";
            }
        } catch (Exception e) {
            logger.error("===========%n Redis檢查用戶是否已登錄-失敗", e);
            return "";
        }
    }

    /**
     * 設定用戶登錄狀態
     * 狀態轉換規則：
     * 1. default: 只能在 key 不存在時設置
     * 2. Y/N: 可以從 default 狀態轉換
     * 3. Y 狀態不能變更為 N
     */
    public Boolean setUserRegistered(String custIxd, String udiDup, String userIxd, Integer companyKind, String activityCode, String status) {
        logger.info("===========%n Redis設定用戶登錄狀態 - custId: {}, activityCode: {}, status: {}", custIxd, activityCode, status);

        if (StringUtils.isEmpty(custIxd) || StringUtils.isEmpty(activityCode) || StringUtils.isEmpty(status)) {
            logger.error("===========%n Redis設定用戶登錄狀態-參數不足");
            return false;
        }

        try {
            Duration timeout = Duration.ofMinutes(40);

            String key = getDistributedUserKey(custIxd, udiDup, userIxd, companyKind, activityCode);

            if ("Y".equals(status)) {
                redisTemplate.opsForValue().set(key, "Y", timeout);
                logger.info("===========%n 設置登錄狀態為Y {}", key);
                return true;
            }

            if ("N".equals(status)) {
                String current = redisTemplate.opsForValue().get(key);
                if (!"Y".equals(current)) {
                    logger.info("===========%n 設置登錄狀態為N {}", key);
                    redisTemplate.opsForValue().set(key, "N", timeout);
                    return true;
                }
                logger.info("===========%n 已登錄 保持狀態Y {}", key);
                return false;
            }
            logger.error("===========%n 無效的狀態值: {}", status);
            return false;

        } catch (Exception e) {
            logger.error("===========%n 設定用戶登錄狀態失敗 - custId: {}, activityCode: {}", custIxd, activityCode, e);
            return false;
        }
    }

    /**
     * 查詢人數統計數 - 使用分散計數器
     *
     * @return
     */
    public String queryTotalAccessCount() {
        logger.info("===========%n 查詢人數統計");
        try {
            long total = 0;
            for (int i = 0; i < COUNT_BUCKET_SIZE; i++) {
                String key = ACTIVITY_COUNT_BUCKET + i;
                String value = redisTemplate.opsForValue().get(key);
                if (value != null) {
                    total += Long.parseLong(value);
                }
            }
            logger.info("===========%n 查詢人數統計成功 {}", total);
            return String.valueOf(total);
        } catch (Exception e) {
            logger.error("===========%n 查詢人數統計失敗", e);
            return "0";
        }
    }

    /**
     * 設定人數統計 - 使用分散計數器
     *
     * @param number
     * @return
     */
    public Boolean setTotalAccessCount(String number) {
        logger.info("===========%n 設定人數統計 {}", number);
        try {
            Duration timeout = Duration.ofMinutes(40);

            int bucket = ThreadLocalRandom.current().nextInt(COUNT_BUCKET_SIZE);
            String key = ACTIVITY_COUNT_BUCKET + bucket;

            Long newValue = redisTemplate.opsForValue().increment(key, 1);
            redisTemplate.expire(key, timeout);

            logger.info("===========%n 設定人數統計成功 - bucket: {}, newValue: {}", bucket, newValue);
            return true;
        } catch (Exception e) {
            logger.error("===========%n 設定人數統計失敗", e);
            return false;
        }
    }

    /**
     * 登錄按鈕開關
     *
     * @return
     */
    public Boolean queryActivityButton() {
        logger.info("===========%n 登錄按鈕開關查詢");
        try {
            //yyyy/MM/dd HH:mm
            String hotActivityStartTime = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "HOT_ACTIVITY_BUTTON_START_TIME");
            //yyyy/MM/dd HH:mm
            String hotActivityEndTime = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "HOT_ACTIVITY_BUTTON_END_TIME");
            logger.info("===========%n 登錄按鈕開關查詢 hotActivityStartTime {} hotActivityEndTime {}", hotActivityStartTime, hotActivityEndTime);
            if (StringUtils.isEmpty(hotActivityStartTime) || StringUtils.isEmpty(hotActivityEndTime)) {
                logger.warn("活動時間未設置");
                return false;
            }

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            LocalDateTime start = LocalDateTime.parse(hotActivityStartTime, formatter);
            LocalDateTime end = LocalDateTime.parse(hotActivityEndTime, formatter);
            boolean isOn = now.isAfter(start) && now.isBefore(end);
            if (isOn) {
                logger.info("===========%n登錄按鈕開關開啟=========");
            } else {
                logger.info("===========%n登錄按鈕開關關閉=========");
            }
            return isOn;
        } catch (Exception e) {
            logger.error("===========%n 登錄按鈕開關查詢失敗", e);
            return false;
        }
    }

    /**
     * 設定預設狀態
     */
    private void setDefaultStatus(String key, Duration timeout) {
        Boolean result = redisTemplate.opsForValue().setIfAbsent(key, "default", timeout);
        if (Boolean.TRUE.equals(result)) {
            logger.info("===========%n 成功設置初始狀態 Default - key: {}", key);
        } else {
            logger.info("===========%n key已存在，保持原值 - key: {}", key);
        }
    }

    /**
     * 更新狀態
     */
    private boolean updateStatus(String key, String newStatus, Duration timeout) {
        logger.info("===========%n 更新狀態 - key: {}, newStatus: {}", key, newStatus);
        int maxRetries = 5;
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            Boolean success = redisTemplate.execute(new SessionCallback<Boolean>() {
                @Override
                @SuppressWarnings("unchecked")
                public Boolean execute(RedisOperations operations) throws DataAccessException {
                    operations.watch(key);

                    String currentValue = (String) operations.opsForValue().get(key);
                    if (currentValue == null) {
                        operations.unwatch();
                        logger.info("===========%n 當前狀態不允許改變 - key: {}, value: null", key);
                        return false;
                    }

                    // 檢查狀態轉換是否合法
                    if (!isValidStatusTransition(currentValue, newStatus)) {
                        operations.unwatch();
                        logger.info("===========%n 不允許的狀態轉換 - key: {}, from: {}, to: {}", key, currentValue, newStatus);
                        return false;
                    }

                    operations.multi();
                    operations.opsForValue().set(key, newStatus, timeout);

                    try {
                        List<Object> result = operations.exec();
                        boolean success = (result != null && !result.isEmpty());
                        if (success) {
                            logger.info("===========%n 成功更新狀態 - key: {}, from: {}, to: {}", key, currentValue, newStatus);
                        } else {
                            logger.info("===========%n 更新狀態失敗 - key: {}, currentValue: {}", key, currentValue);
                        }
                        return success;
                    } catch (Exception e) {
                        logger.error("===========%n 執行更新狀態時發生錯誤 - key: {}", key, e);
                        return false;
                    }
                }
            });

            if (Boolean.TRUE.equals(success)) {
                return true;
            }

            if (attempt < maxRetries) {
                logger.info("===========%n 第 {} 次更新失敗，重試中 - key: {}", attempt, key);
            }
        }
        logger.error("===========%n 超過最大重試次數，更新狀態失敗 - key: {}", key);
        return false;
    }

    /**
     * 檢查狀態轉換是否合法
     * 允許的轉換：
     * 1. default -> Y
     * 2. default -> N
     * 3. N -> Y
     * 不允許的轉換：
     * 1. Y -> N
     * 2. Y -> Y (相同狀態)
     * 3. N -> N (相同狀態)
     */
    private boolean isValidStatusTransition(String currentValue, String newStatus) {
        // 如果當前值和新值相同，不允許轉換
        if (currentValue.equals(newStatus)) {
            logger.info("===========%n 當前狀態與目標狀態相同，無需轉換 - currentValue: {}", currentValue);
            return false;
        }

        // 如果當前狀態是 default，允許轉換到 Y 或 N
        if ("default".equals(currentValue)) {
            return "Y".equals(newStatus) || "N".equals(newStatus);
        }

        // 如果當前狀態是 N，只允許轉換到 Y
        if ("N".equals(currentValue)) {
            return "Y".equals(newStatus);
        }

        // 如果當前狀態是 Y，不允許任何轉換
        if ("Y".equals(currentValue)) {
            logger.info("===========%n Y狀態不允許變更為其他狀態");
            return false;
        }

        logger.info("===========%n 未知的狀態轉換情況 - from: {}, to: {}", currentValue, newStatus);
        return false;
    }
}