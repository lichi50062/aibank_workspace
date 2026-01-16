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
package com.tfb.aibank.chl.component.mfddiscountinfo;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

// @formatter:off
/**
 * @(#)MfdInfoResource.java
 * 
 * <p>Description:溝通 service-aibank-mutual-fund 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/14, PCY
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-mfd-discount-info-to-mutual-fund-service", url = "${aibank.common.feign.service-aibank-mutual-fund-url:http://svc-biz-mutual-fund:8080}")
@Component("MfdDiscountInfoResource")
public interface MfdDiscountInfoResource {

    /** 取得全部基金優惠 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/mutual-funds/discount-infos/get", produces = MediaType.APPLICATION_JSON_VALUE)
    List<MfdDiscountInfo> getAllMfdDiscountInfos();

}
