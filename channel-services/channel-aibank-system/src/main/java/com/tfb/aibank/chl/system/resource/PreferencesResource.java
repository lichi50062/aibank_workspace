package com.tfb.aibank.chl.system.resource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.system.resource.dto.KYCAnswerResponse;
import com.tfb.aibank.chl.system.resource.dto.UpdatePushTokenRequest;

// @formatter:off
/**
 * @(#)PreferencesResource.java
 * 
 * <p>Description:溝通 service-aibank-preferences 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/08, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-chl-system-to-preferences-service", url = "${aibank.common.feign.service-aibank-preferences-url:http://svc-biz-preferences:8080}")
public interface PreferencesResource {

    /** KYC填答結果查詢 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/kyc-answer/get", produces = MediaType.APPLICATION_JSON_VALUE)
    KYCAnswerResponse sendEB12020006KYC(@RequestHeader("custId") String custId, @RequestParam("nameCode") String nameCode, @RequestParam("funcCod") String funcCod, @RequestParam("func") String func);

    /**
     * 更新 FIRST_DOWNLOAD_RECORD 的 Push Token
     */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/first-download-record/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean updatePushToken(@RequestBody UpdatePushTokenRequest request);

}
