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
package com.tfb.aibank.chl.component.exchangeratehistory;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)ExchangeRateHistoryResource.java
 * 
 * <p>Description:溝通 service-aibank-information 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-exchange-rate-history-to-foreign-exchange-service", url = "${aibank.common.feign.service-aibank-foreign-exchange-url:http://svc-biz-foreign-exchange:8080}")
@Component("ExchangeRateResource")
public interface ExchangeRateHistoryResource {

    /** 查詢資料表(EXCHANGE_RATE_HISTORY)，取得近N個工作日的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/exchange-rate-history/{days}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ExchangeRateHistory> getPreviousNDaysCloseRateSortByRateCurrency(@PathVariable("days") @Schema(description = "N天前的資料") Integer days);

    /** 查詢資料表(EXCHANGE_RATE_HISTORY)，取得TxTime在參數區間內的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/exchange-rate-history/query", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ExchangeRateHistory> getExchangeRateHistoryBetween(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyyMMddHHmmssSSS") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern = "yyyyMMddHHmmssSSS") Date endDate);

    /**
     * 查詢資料表(EXCHANGE_RATE_HISTORY)，取得臺對外幣，美元對外幣其TxTime在參數區間內的資料
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/exchange-rate-history/twd-and-usd/query", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ExchangeRateHistory> findTwdFxAndUsdFxByTxTimeBetween(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyyMMddHHmmssSSS") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern = "yyyyMMddHHmmssSSS") Date endDate);

    /** 查詢資料表(EXCHANGE_RATE_HISTORY)，取得[臺對外，美元對外]1週1月3月在區間內SELL最低和BUY最高資料Map */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/exchange-rate-history/high-low-inrange/list", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Map<String, ExRateHistoryMax>> getThreeRangesExRateHistoryRangeMap();
}
