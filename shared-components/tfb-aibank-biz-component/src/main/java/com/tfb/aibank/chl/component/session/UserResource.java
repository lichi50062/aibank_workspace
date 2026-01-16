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
package com.tfb.aibank.chl.component.session;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@formatter:off
/**
* @(#)UserResource.java
* 
* <p>Description:Service-AIBank-User FeignClient</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@FeignClient(name = "aibank-biz-component-user-to-user-service", url = "${aibank.common.feign.service-aibank-user-url:http://svc-biz-user:8080}")
public interface UserResource {

    /** 登出 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/authentications/customer/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    ExecuteUserLogoutResponse executeUserLogout(@RequestBody ExecuteUserLogoutRequest request);
}
