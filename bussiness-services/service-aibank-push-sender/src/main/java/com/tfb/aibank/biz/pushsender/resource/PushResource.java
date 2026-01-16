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
package com.tfb.aibank.biz.pushsender.resource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ibm.tw.ibmb.component.feign.IxteinPushFeignClientConfig;
import com.tfb.aibank.biz.pushsender.resource.dto.ReceivePushV2Request;
import com.tfb.aibank.biz.pushsender.resource.dto.ReceivePushV2Response;

// @formatter:off
/**
 * @(#)PushResource.java
 * 
 * <p>Description:溝通 IMP 的界面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "service-aibank-push-sender-imp", url = "${aibank.common.feign.ixtein-push-url:http://svc-ixtein-push:8080}", configuration = IxteinPushFeignClientConfig.class)
public interface PushResource {

    @PostMapping(value = "/v2/receive", produces = MediaType.APPLICATION_JSON_VALUE)
    ReceivePushV2Response receive(@RequestBody ReceivePushV2Request request);

}
