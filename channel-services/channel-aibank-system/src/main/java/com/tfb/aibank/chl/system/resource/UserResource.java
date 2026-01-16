package com.tfb.aibank.chl.system.resource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.component.devicebinding.model.QuickSearchResponse;
import com.tfb.aibank.chl.component.devicebinding.model.RetrieveUserBindingResponse;
import com.tfb.aibank.chl.component.userdatacache.model.EB032675Res;
import com.tfb.aibank.chl.system.resource.dto.GenerateTokenRequest;
import com.tfb.aibank.chl.system.resource.dto.GenerateTokenResponse;
import com.tfb.aibank.chl.system.resource.dto.GetSsoSettingRequest;
import com.tfb.aibank.chl.system.resource.dto.GetSsoSettingResponse;
import com.tfb.aibank.chl.system.resource.dto.GetTokenResponse;
import com.tfb.aibank.chl.system.resource.dto.KnowledgeCheckRequest;

// @formatter:off
/**
 * @(#)UserResource.java
 * 
 * <p>Description:溝通 service-aibank-user 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/22, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-chl-system-to-user-service", url = "${aibank.common.feign.service-aibank-user-url:http://svc-biz-user:8080}")
public interface UserResource {

    /** SSO-產生Token */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/info/add", produces = MediaType.APPLICATION_JSON_VALUE)
    GenerateTokenResponse generateToken(@RequestBody GenerateTokenRequest request);

    /** SSO-取得設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/sso/setting/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetSsoSettingResponse getSsoSetting(@RequestBody GetSsoSettingRequest request);

    /** SSO-取得使用者資訊 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/info/query", produces = MediaType.APPLICATION_JSON_VALUE)
    GetTokenResponse getToken(@RequestParam("token") String token, @RequestParam("channelId") String channelId);

    /** 取得使用者綁定資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/device-bindings/get", produces = MediaType.APPLICATION_JSON_VALUE)
    RetrieveUserBindingResponse retrieveUserBinding(@RequestHeader("custId") String custId, @RequestHeader("udiDup") String udiDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind, @RequestParam("locale") String locale);

    /** 新增交易高齡認知檢核 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/knowledge-check/add", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean addKnowledgeCheck(@RequestBody KnowledgeCheckRequest request);

    /** 查詢高資產客戶資訊 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer/invest-eligible-data/query", produces = MediaType.APPLICATION_JSON_VALUE)
    EB032675Res sendEB032675(@RequestHeader("custId") String custId);

    /** 更新推播Token */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/push/token/update", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean updateDevicePushStatus(@RequestHeader("deviceuuid") String deviceUuid, @RequestHeader("pushToken") String pushToken);

    /** 查詢免登速查狀態及資訊 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/quick-search/status/get", produces = MediaType.APPLICATION_JSON_VALUE)
    QuickSearchResponse getQuickSearchStatus(@RequestHeader("deviceuuid") String deviceuuid);
}
