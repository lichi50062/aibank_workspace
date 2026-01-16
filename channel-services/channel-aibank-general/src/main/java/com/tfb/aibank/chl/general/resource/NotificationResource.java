package com.tfb.aibank.chl.general.resource;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.general.qu001.model.SystemNotificationRecord;
import com.tfb.aibank.chl.general.resource.dto.TwoFactorAuthRequest;
import com.tfb.aibank.chl.general.resource.dto.TwoFactorAuthResponse;
import com.tfb.aibank.chl.general.resource.dto.UpdateTwoFactorAuthRequest;

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
@FeignClient(name = "aibank-chl-general-to-notification-service", url = "${aibank.common.feign.service-aibank-notification-url:http://svc-biz-notification:8080}")
public interface NotificationResource {

    /** "取得已登入客戶近3個月最多30則訊息，並判斷是否有未讀訊息") */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/personals/unread-count/query", produces = { MediaType.APPLICATION_JSON_VALUE })
    Integer getUnreadCountInThreeMonths(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestParam("uidDup") String uidDup, @RequestParam("companyKind") Integer companyKind);

    // 取得系統通知訊息
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-notifications/query", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<SystemNotificationRecord> getUnreadThreeMonthsFromSystemNotify();

    // 啟動雙重驗證
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/notification/trust", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    TwoFactorAuthResponse fireTwoFactorNotification(@RequestBody TwoFactorAuthRequest request);

    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/notification/two-factor/update")
    public Boolean updateTwoFactorAuthUpdateNotificationOnly(@RequestBody UpdateTwoFactorAuthRequest request);

}
