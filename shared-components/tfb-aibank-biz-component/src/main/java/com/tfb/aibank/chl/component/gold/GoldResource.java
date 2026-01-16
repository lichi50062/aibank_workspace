package com.tfb.aibank.chl.component.gold;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)GoldResource.java
 * 
 * <p>Description:溝通 service-aibank-information 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/29, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-gold-to-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
@Component("GoldResource")
public interface GoldResource {

    /** 查詢資料表(GOLD_RATE_HISTORY)，取得一年半的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/gold-rate-history/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<GoldRateHistory> getAllGoldRateHistory();

}
