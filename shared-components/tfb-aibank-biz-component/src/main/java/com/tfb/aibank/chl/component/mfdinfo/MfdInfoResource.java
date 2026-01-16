/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.mfdinfo;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

// @formatter:off
/**
 * @(#)MfdInfoResource.java
 * 
 * <p>Description:溝通 service-aibank-mutual-fund 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/14, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-mfd-info-to-mutual-fund-service", url = "${aibank.common.feign.service-aibank-mutual-fund-url:http://svc-biz-mutual-fund:8080}")
@Component("MfdInfoResource")
public interface MfdInfoResource {

    /** 查詢基金資訊(單筆) */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mutual-funds/info/{fundCode}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    MfdInfo getMfdInfo(@PathVariable("fundCode") String fundCode, @RequestParam("locale") String locale);

    /** 查詢基金資訊(多筆) */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/mutual-funds/info/list", produces = { MediaType.APPLICATION_JSON_VALUE })
    List<MfdInfo> getMfdInfoList(@RequestBody List<String> fundCodes, @RequestParam("locale") String locale);

    /** 查詢基金資訊(所有)" */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mutual-funds/all-mfd-info", produces = { MediaType.APPLICATION_JSON_VALUE })
    List<MfdInfo> getAllMfdInfo();
}
