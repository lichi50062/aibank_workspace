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
package com.ibm.tw.ibmb.component.crypto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)SecurityResource.java
 * 
 * <p>Description:溝通 service-aibank-security 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/24, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "common-crypto-to-security-service", url = "${aibank.common.feign.service-aibank-security-url:http://svc-biz-security:8080}")
public interface SecurityResource {

    /** E2EE取得 DB Kxy */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1/dbkey/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDBAccessKxy();

    /** E2EE取得 DB OTP Kxy */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1/otpkey/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getOTPAccessKxy();

}
