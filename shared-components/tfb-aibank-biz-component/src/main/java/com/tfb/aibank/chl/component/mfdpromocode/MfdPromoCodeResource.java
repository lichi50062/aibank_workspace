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
package com.tfb.aibank.chl.component.mfdpromocode;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)MfdPromoCodeResource.java
 * 
 * <p>Description:溝通 service-aibank-mutual-fund 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/14, MP
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-mfd-promocode-to-mutual-fund-service", url = "${aibank.common.feign.service-aibank-mutual-fund-url:http://svc-biz-mutual-fund:8080}")
@Component("MfdPromoCodeResource")
public interface MfdPromoCodeResource {


    /** 取得所有「MFD_PROMO_CODE」在優惠時間內的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mutual-fund/mfd-promocode/get", produces = { MediaType.APPLICATION_JSON_VALUE })
    List<FundProjectDiscount> getAllEffectiveMfdPromoCodes();

}
