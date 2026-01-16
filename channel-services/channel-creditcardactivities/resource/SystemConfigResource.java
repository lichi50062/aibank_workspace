package com.tfb.aibank.chl.creditcardactivities.resource;

import com.tfb.aibank.chl.creditcardactivities.ot006.model.NCAOT006Activity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @(#)SystemParamResource.java
 * 
 * <p>Description:溝通 service-aibank-system-config 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/08/01, Lichi
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-chl-creditcardactivities-to-system-config-service", url = "${aibank.common.feign.service-aibank-system-config-url:http://svc-biz-system-config:8080}")
public interface SystemConfigResource {

    /** NCAOT006登錄按鈕狀態 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/ncaot006/query-activity-button", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean queryActivityButton();

    /** NCAOT006用戶登錄狀態查詢 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/ncaot006/user-registered", produces = MediaType.APPLICATION_JSON_VALUE)
    String queryUserRegistered(@RequestHeader("custId") String custId, @RequestHeader("udiDup") String udiDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind, @RequestHeader("activityCode") String activityCode);

    /** NCAOT006用戶登錄狀態設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/ncaot006/user-register-set", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean setRegisterUser(@RequestHeader("custId") String custId, @RequestHeader("udiDup") String udiDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind, @RequestHeader("activityCode") String activityCode, @RequestHeader("status") String status);

    /** NCAOT006活動額滿狀態查詢 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/ncaot006/activity-full", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean queryActivityFull(@RequestHeader("activityCode") String activityCode);

    /** NCAOT006活動額滿設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/ncaot006/activity-full-set", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean setActivityFull(@RequestHeader("activityCode") String activityCode);

    /** NCAOT006活動資訊查詢 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/ncaot006/activity-info", produces = MediaType.APPLICATION_JSON_VALUE)
    List<NCAOT006Activity> queryActivityInfo();

    /** NCAOT006活動資訊設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/ncaot006/activity-info-set", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean setActivityInfo(@RequestBody List<NCAOT006Activity> activities);

    /** NCAOT006查詢熱門活動流程人數上限 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/ncaot006/total-access-count", produces = MediaType.APPLICATION_JSON_VALUE)
    String getTotalAccessCount();

    /** NCAOT006設定熱門活動流程人數統計 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/ncaot006/total-access-count-set", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean setTotalAccessCount(@RequestHeader String number);
}
