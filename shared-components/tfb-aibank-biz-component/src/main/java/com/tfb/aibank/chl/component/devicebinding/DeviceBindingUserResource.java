/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.devicebinding;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.component.devicebinding.model.QuickSearchResponse;
import com.tfb.aibank.chl.component.devicebinding.model.RetrieveDeviceBindingResponse;
import com.tfb.aibank.chl.component.devicebinding.model.RetrieveMultiUserBindingResponse;
import com.tfb.aibank.chl.component.devicebinding.model.RetrieveUserBindingResponse;

// @formatter:off
/**
 * @(#)DeviceBindingUserResource.java
 * 
 * <p>Description:裝置綁定使用User服務資源</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/19, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-device-binding-to-information-service", url = "${aibank.common.feign.service-aibank-user-url:http://svc-biz-user:8080}")
public interface DeviceBindingUserResource {

    /** 取得使用者綁定資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/device-bindings/get", produces = MediaType.APPLICATION_JSON_VALUE)
    RetrieveUserBindingResponse retrieveUserBinding(@RequestHeader("custId") String custId, @RequestHeader("udiDup") String udiDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind, @RequestParam("locale") String locale);

    /** 取得裝置綁定資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/device-bindings/{deviceUuid}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    RetrieveDeviceBindingResponse retrieveDeviceBinding(@PathVariable("deviceUuid") String deviceUuid);

    /** 取得多使用者代碼客戶綁定狀態 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/device-bindings/multi-user-status/get", produces = MediaType.APPLICATION_JSON_VALUE)
    RetrieveMultiUserBindingResponse retrieveMultiUserBinding(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind, @RequestParam("uidDup") String uidDup);

    /** 取的推播訂閱資料 By 行動裝置設定檔鍵值和推播代碼 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/device-status/{deviceuuid}/{pushCode}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean retrivePushSubcriptionStatus(@PathVariable("deviceuuid") String deviceUuid, @PathVariable("pushCode") String pushCode, @RequestHeader("custId") String custId, @RequestHeader("udiDup") String udiDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind);

    /** 查詢免登速查狀態及資訊 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/quick-search/status/get", produces = MediaType.APPLICATION_JSON_VALUE)
    QuickSearchResponse getQuickSearchStatus(@RequestHeader("deviceuuid") String deviceuuid);
}
