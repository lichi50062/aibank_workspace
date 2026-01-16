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
package com.tfb.aibank.chl.component.exchangerate;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

// @formatter:off
/**
 * @(#)ExchangeRateResource.java
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
@FeignClient(name = "aibank-biz-component-exchange-rate-to-foreign-exchange-service", url = "${aibank.common.feign.service-aibank-foreign-exchange-url:http://svc-biz-foreign-exchange:8080}")
@Component("ExchangeRateResource")
public interface ExchangeRateResource {

    /** 查詢資料表(EXCHANGE_RATE)，取得目前全部的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/exchange-rate/all/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ExchangeRate> getAllExchangeRateData();

    /** 查詢資料表(EXCHANGE_RATE)，依 RATE_TYPE 讀取資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/exchange-rate/{rateType}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ExchangeRate> getExchangeRatesByRateType(@PathVariable("rateType") String rateType);

    /** 查詢資料表(EXCHANGE_RATE)，取得臺轉外的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/exchange-rate/twd2Foreign/get", produces = MediaType.APPLICATION_JSON_VALUE)
    ExchangeRate twd2Foreign(@RequestParam("exchangeTypeNo") String exchangeTypeNo, @RequestParam("currencyEname1") String currencyEname1);

    /** 查詢資料表(EXCHANGE_RATE)，取得外轉外的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/exchange-rate/foreign2Foreign/get", produces = MediaType.APPLICATION_JSON_VALUE)
    ExchangeRate foreign2Foreign(@RequestParam("currencyEname1") String currencyEname1, @RequestParam("currencyEname2") String currencyEname2);

    /** 查詢資料表(EXCHANGE_RATE)，依rateType和exchangeTypeNo查詢資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/exchange-rate/rateType-exchangeTypeNo/get", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ExchangeRate> findExchangeRateByRateTypeAndExchangeTypeNo(@RequestParam("rateType") String rateType, @RequestParam("exchangeTypeNo") String exchangeTypeNo);
}
