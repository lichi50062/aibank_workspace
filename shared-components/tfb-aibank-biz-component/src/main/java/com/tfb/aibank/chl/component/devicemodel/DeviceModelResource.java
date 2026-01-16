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
package com.tfb.aibank.chl.component.devicemodel;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)DeviceModelResource.java
 * 
 * <p>Description:行動裝置型號對應表 Resource</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/13, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-device-model-to-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
@Component("DeviceModelResource")
public interface DeviceModelResource {

    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/device-models/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeviceModelData> getAllDeviceModelData();

}
