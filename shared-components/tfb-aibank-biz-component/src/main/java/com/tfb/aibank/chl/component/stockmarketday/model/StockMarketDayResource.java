package com.tfb.aibank.chl.component.stockmarketday.model;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

//@formatter:off
/**
* @(#)StockMarketDayResource.java
* 
* <p>Description:溝通 service-aibank-invest 的介面</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/18 Alex PY Li,
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@FeignClient(name = "aibank-biz-component-stock-market-day-cache-to-invest-service", url = "${aibank.common.feign.service-aibank-invest-url:http://svc-biz-invest:8080}")
public interface StockMarketDayResource {
    /** 海外ETF/海外股票市場交易日查詢 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/foreign-stocks/market-day/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StockMarketDay> getStockMarketDay();
}
