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
package com.ibm.tw.ibmb.component.menu;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @formatter:off
/**
 * @(#)MenuResource.java
 * 
 * <p>Description:溝通 service-aibank-system-config 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/24, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "common-menu-to-system-config-service", url = "${aibank.common.feign.service-aibank-system-config-url:http://svc-biz-system-config:8080}")
public interface MenuResource {

    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/menus/query", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Menu> getMenuByCategories(@RequestParam("categories") List<String> categories);

    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/menus-name/list", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Map<String, String>> getMenuNames();

}
