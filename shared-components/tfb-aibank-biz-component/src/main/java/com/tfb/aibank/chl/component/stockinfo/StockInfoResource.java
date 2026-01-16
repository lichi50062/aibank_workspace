package com.tfb.aibank.chl.component.stockinfo;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

//@formatter:off
/**
 * @(#)StockInfoResource.java
 * 
 * <p>Description:溝通 service-aibank-information 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@FeignClient(name = "aibank-cachemgr-to-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
public interface StockInfoResource {

    /** 查詢資料表(STOCK_INFO)，取得符合全部資料 for cache */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/stock-info/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StockInfoCache> getAllStockInfoData();

}
