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
package com.tfb.aibank.chl.component.security.motp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.tfb.aibank.chl.component.security.motp.model.CreatePushOtpRequest;
import com.tfb.aibank.chl.component.security.motp.model.CreatePushOtpResponse;
import com.tfb.aibank.chl.component.security.motp.model.VerifyPushOtpRequest;
import com.tfb.aibank.chl.component.security.motp.model.VerifyPushOtpResponse;

// @formatter:off
/**
 * @(#)MotpSecurityResource.java
 * 
 * <p>Description:MOTP使用Security服務資源</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/16, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-security-motp-to-security-service", url = "${aibank.common.feign.service-aibank-security-url:http://svc-biz-security:8080}")
public interface MotpSecurityResource {

    /** 發送推播OTP */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/motp-auths/send", produces = MediaType.APPLICATION_JSON_VALUE)
    public CreatePushOtpResponse createPushOtp(@RequestBody CreatePushOtpRequest request);

    /** 驗證推播OTP */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/motp-auths/verify", produces = MediaType.APPLICATION_JSON_VALUE)
    public VerifyPushOtpResponse verifyPushOtp(@RequestBody VerifyPushOtpRequest request);

    /** 寫入MOTP Log */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1/motp-log/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> saveMotpLog(@RequestBody SaveMotpLogRequest request);
}
