package com.tfb.aibank.biz.component.videoauthapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;

// @formatter:off
/**
 * @(#)VideoAuthProperties.java
 * 
 * <p>Description:[視訊驗證參數檔]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/18, leiley 
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class VideoAuthProperties {

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    private static final String PLATFORM = "AIBANK";

    /**
     * 新增系統參數來控制視訊驗證狀態是否可以使用 ex true/false
     */
    private static final String VIDEO_AUTH_AVALIABLE_FLAG = "VIDEO_AUTH_AVALIABLE_FLAG";

    /**
     * [視訊系統API] 連線位置Url
     */
    private static final String VIDEO_AUTH_API_PATH = "VIDEO_AUTH_API_PATH";

    /**
     * [視訊系統API] 取得客服是否服務中Url ex '/fubon/checkServiceStatus'
     */
    private static final String VIDEO_AUTH_API_CHECK_SERVICE_STATUS_PATH = "VIDEO_AUTH_API_CHECK_SERVICE_STATUS_PATH";

    /**
     * [視訊系統API] 客服等待人數Url ex '/fubon/checkWaitCount'
     */
    private static final String VIDEO_AUTH_API_CHECK_WAIT_COUNT_PATH = "VIDEO_AUTH_API_CHECK_WAIT_COUNT_PATH";

    private static final String VIDEO_AUTH_API_CHECK_WAIT_COUNT_VQ = "VIDEO_AUTH_API_CHECK_WAIT_COUNT_VQ";
    private static final String VIDEO_AUTH_API_CHECK_WAIT_COUNT_SKILL = "VIDEO_AUTH_API_CHECK_WAIT_COUNT_SKILL";

    /**
     * [視訊系統SDK連線設定參數] 等待人數限制<依此判斷顯示畫面> ex 1
     */
    private static final String VIDEO_AUTH_RESTRICT_WAITING_NO = "VIDEO_AUTH_RESTRICT_WAITING_NO";

    /**
     * [視訊系統SDK連線設定參數-makeVideoCall] 主叫號碼 ex "8888"
     */
    private static final String VIDEO_AUTH_SDK_CALLER_NUM = "VIDEO_AUTH_SDK_CALLER_NUM";

    /**
     * [視訊系統SDK連線設定參數-makeVideoCall] 被叫號碼 ex "8888"
     */
    private static final String VIDEO_AUTH_SDK_CALLEE_NUM = "VIDEO_AUTH_SDK_CALLEE_NUM";

    /**
     * [視訊系統SDK連線設定參數-makeVideoCall] 執行模組固定為SIP ex "SIP"
     */
    private static final String VIDEO_AUTH_SDK_MODULE = "VIDEO_AUTH_SDK_MODULE";

    /**
     * [視訊系統SDK連線設定參數-makeVideoCall] 連線資訊 (connectMediaServer時使用) 行銀測試環境 'wss://109.crm.com.tw:8432/' 平台測試環境 'wss://109.crm.com.tw:8433/MediaService/'
     */
    private static final String VIDEO_AUTH_SDK_CONNECT_SERVER = "VIDEO_AUTH_SDK_" + PLATFORM + "_CONNECT_SERVER";

    private static final String VIDEO_TIMEOUT = "VIDEO_TIMEOUT";

    private static final String CATEGORY = "VA";

    /**
     * @return the videoAuthAvaliableFlag
     */
    public boolean getVideoAuthAvaliableFlag() {
        return Boolean.parseBoolean(systemParamCacheManager.getValue(CATEGORY, VIDEO_AUTH_AVALIABLE_FLAG));
    }

    /**
     * @return the videoAuthApiPath
     */
    public String getVideoAuthApiPath() {
        return systemParamCacheManager.getValue(CATEGORY, VIDEO_AUTH_API_PATH);
    }

    /**
     * @return the videoAuthApiCheckServiceStatusPath
     */
    public String getVideoAuthApiCheckServiceStatusPath() {
        return getVideoAuthApiPath() + systemParamCacheManager.getValue(CATEGORY, VIDEO_AUTH_API_CHECK_SERVICE_STATUS_PATH);
    }

    /**
     * @return the videoAuthApiCheckWaitCountPath
     */
    public String getVideoAuthApiCheckWaitCountPath() {
        return getVideoAuthApiPath() + systemParamCacheManager.getValue(CATEGORY, VIDEO_AUTH_API_CHECK_WAIT_COUNT_PATH);
    }

    /**
     * @return the videoAuthApiCheckWaitCountVQ
     */
    public String getVideoAuthApiCheckWaitCountVQ() {
        return systemParamCacheManager.getValue(CATEGORY, VIDEO_AUTH_API_CHECK_WAIT_COUNT_VQ);
    }

    /**
     * @return the videoAuthApiCheckWaitCountSkill
     */
    public String getVideoAuthApiCheckWaitCountSkill() {
        return systemParamCacheManager.getValue(CATEGORY, VIDEO_AUTH_API_CHECK_WAIT_COUNT_SKILL);
    }

    /**
     * @return the videoAuthRestrictWaitingNo
     */
    public Integer getVideoAuthRestrictWaitingNo() {
        return Integer.parseInt(systemParamCacheManager.getValue(CATEGORY, VIDEO_AUTH_RESTRICT_WAITING_NO));
    }

    /**
     * @return the videoAuthSdkCallerNum
     */
    public String getVideoAuthSdkCallerNum() {
        return systemParamCacheManager.getValue(CATEGORY, VIDEO_AUTH_SDK_CALLER_NUM);
    }

    /**
     * @return the videoAuthSdkCalleeNum
     */
    public String getVideoAuthSdkCalleeNum() {
        return systemParamCacheManager.getValue(CATEGORY, VIDEO_AUTH_SDK_CALLEE_NUM);
    }

    /**
     * @return the videoAuthSdkModule
     */
    public String getVideoAuthSdkModule() {
        return systemParamCacheManager.getValue(CATEGORY, VIDEO_AUTH_SDK_MODULE);
    }

    /**
     * @return the videoAuthSdkConnectServer
     */
    public String getVideoAuthSdkConnectServer() {
        return systemParamCacheManager.getValue(CATEGORY, VIDEO_AUTH_SDK_CONNECT_SERVER);
    }

    /**
     * @return the videoTimeout
     */
    public Integer getVideoTimeout() {
        return Integer.parseInt(systemParamCacheManager.getValue(CATEGORY, VIDEO_TIMEOUT));
    }
}
