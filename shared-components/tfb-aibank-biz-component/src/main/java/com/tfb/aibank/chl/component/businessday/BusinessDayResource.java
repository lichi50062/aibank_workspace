package com.tfb.aibank.chl.component.businessday;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)BusinessDayResource.java
 * 
 * <p>Description:溝通 service-aibank-information 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/14, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-business-day-to-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
public interface BusinessDayResource {

    /** 取得所有營業日 (BUSINESS_DAY) */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/business-day-datas/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Date> getAllBusinessDay();


    /** 取得所有台北營業日 (TAIPEI_BUSINESS_DAY) */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/taipei-business-day-datas/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Date> getAllTaipeiBusinessDay();

}
