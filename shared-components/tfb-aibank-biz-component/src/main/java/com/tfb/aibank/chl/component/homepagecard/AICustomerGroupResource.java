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
package com.tfb.aibank.chl.component.homepagecard;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

//@formatter:off
/**
* @(#)HomepageCardResource.java
* 
* <p>Description:溝通 service-aibank-notification 的介面</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/27, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@FeignClient(name = "aibank-biz-component-home-page-card-to-notification-service", url = "${aibank.common.feign.service-aibank-notification-url:http://svc-biz-notification:8080}")
@Component("AICustomerGroupResource")
public interface AICustomerGroupResource {

    /** 使用者＋TA_GROUP 是否在 AI_CUSTOMER_GROUP 裏 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/ai-customer-group/query", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean isInAiCustomerGroup(@RequestHeader("custId") String custId, @RequestHeader("taGroup") String taGroup);
}
