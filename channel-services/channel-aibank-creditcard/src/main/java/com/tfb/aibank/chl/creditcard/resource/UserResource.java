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
package com.tfb.aibank.chl.creditcard.resource;

import com.tfb.aibank.chl.creditcard.resource.dto.RetriveDeviceStatusResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.creditcard.resource.dto.CEW466RRes;
import com.tfb.aibank.chl.creditcard.resource.dto.CardCostcoDues;
import com.tfb.aibank.chl.creditcard.resource.dto.ChangeCustDataRecordModel;
import com.tfb.aibank.chl.creditcard.resource.dto.ExecuteChangeCcUserPinRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.ExecuteChangeCcUserPinResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.ExecuteUserLogoutRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.ExecuteUserLogoutResponse;

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
@FeignClient(name = "aibank-chl-creditcard-to-user-service", url = "${aibank.common.feign.service-aibank-user-url:http://svc-biz-user:8080}")
public interface UserResource {

    /** 創建變更個人資料紀錄 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/custdatarecord/add", produces = MediaType.APPLICATION_JSON_VALUE)
    ChangeCustDataRecordModel createChangeCustDataRecord(@RequestHeader("custId") String custId, @RequestHeader("udiDup") String udiDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind, @RequestBody ChangeCustDataRecordModel model);

    /** 更新變更個人資料紀錄 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/custdatarecord/update", produces = MediaType.APPLICATION_JSON_VALUE)
    ChangeCustDataRecordModel updateChangeCustDataRecord(@RequestHeader("custId") String custId, @RequestHeader("udiDup") String udiDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind, @RequestBody ChangeCustDataRecordModel model);

    /** 信用卡客戶身分驗證 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/authentications/cccustomer/changepin/update", produces = MediaType.APPLICATION_JSON_VALUE)
    ExecuteChangeCcUserPinResponse executeChangeCcUserPin(@RequestBody ExecuteChangeCcUserPinRequest request);

    /** 登出 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/authentications/customer/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    ExecuteUserLogoutResponse executeUserLogout(@RequestBody ExecuteUserLogoutRequest request);

    /** 更新資料(COMPANY)email */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/company/email/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean updateCompanyEmail(@RequestHeader("custId") String custId, @RequestHeader("udiDup") String udiDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind, @RequestParam("newEmail") String email);

    /** 取得Costco會員資訊 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/costco-member/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW466RRes getCostcoMemberInfo(@RequestHeader("custId") String custId, @RequestParam("requestCode") String requestCode, @RequestParam("autoRenew") String autoRenew);

    /** 好市多會員自動續約通知 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/costco-member/notify", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW466RRes notifyCostcoMember(@RequestHeader("custId") String custId, @RequestParam("requestCode") String requestCode, @RequestParam("voabnd") String voabnd);

    /** 更新Costco好市多卡交易紀錄檔 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/costco-member/save", produces = MediaType.APPLICATION_JSON_VALUE)
    CardCostcoDues saveCardCostcoDues(@RequestBody CardCostcoDues model);

    /** 取得裝置綁定狀態 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/device-status/get", produces = MediaType.APPLICATION_JSON_VALUE)
    RetriveDeviceStatusResponse retriveDeviceStatus(@RequestHeader("deviceId") String deviceId, @RequestParam("phoneModel") String phoneModel, @RequestParam("phoneVersion") String phoneVersion);
}
