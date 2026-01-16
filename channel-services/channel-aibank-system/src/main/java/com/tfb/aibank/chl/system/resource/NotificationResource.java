package com.tfb.aibank.chl.system.resource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.system.resource.dto.UpdateDeviceAuthTranRequest;
import com.tfb.aibank.chl.system.resource.dto.UpdateTwoFactorAuthRequest;

//@formatter:off
/**
 * @(#)NotificationResource.java
 *
 * <p>Description:溝通 service-aibank-notification 的介面</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/05/22, MartyPan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@FeignClient(name = "aibank-chl-system-to-notification-service", url = "${aibank.common.feign.service-aibank-notification-url:http://svc-biz-notification:8080}")
public interface NotificationResource {

    /**
     * 網銀非約轉-驗證推播API-更新
     */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/motp-notification/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Boolean updateDeviceAuthTran(@RequestBody UpdateDeviceAuthTranRequest request);

    /**
     * 使用者＋TA_GROUP 是否在 AI_CUSTOMER_GROUP 裏
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/ai-customer-group/query")
    Boolean isInAiCustomerGroup(@RequestHeader("custId") String custId, @RequestHeader("taGroup") String taGroup);

    /**
     * 使用者＋TA_GROUP 是否在 AI_CUSTOMER_GROUP 裏
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/ai-customer-group/device-uuid/query")
    Boolean isInAiCustomerGroup(@RequestHeader("deviceUuid") String deviceUuid, @RequestHeader("taGroup") String taGroup, @RequestParam("isLoggedIn") Boolean isLoggedIn);

    /** 雙重驗證登入API 查詢或更新雙重驗證記錄檔 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/notification/two-factor/update")
    Boolean twoFactorAuthUpdate(@RequestBody UpdateTwoFactorAuthRequest request);
}
