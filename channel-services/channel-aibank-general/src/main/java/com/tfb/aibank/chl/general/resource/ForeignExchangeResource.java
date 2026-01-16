package com.tfb.aibank.chl.general.resource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.general.resource.dto.FxTransDiscountId;

// @formatter:off
/**
 * @(#)ForeignExchangeResource.java
 * 
 * <p>Description:溝通 service-aibank-foreign-exchange 的介面</p>
 * 
 * <p>Modify History:</p>
 * <ol>v1.0, 2023/6/13, Marty
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-chl-general-to-foreign-exchange-service", url = "${aibank.common.feign.service-aibank-foreign-exchange-url:http://svc-biz-foreign-exchange:8080}")
public interface ForeignExchangeResource {

    /** 查詢使用者換匯優惠代碼 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/fx-trans-discount/user-code/get", produces = MediaType.APPLICATION_JSON_VALUE)
    FxTransDiscountId getUserFxTransDiscountId(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestParam("uidDup") String uidDup, @RequestParam("companyKind") Integer companyKind);

    /** 依傳入幣別，查詢資料表(EXCHANGE_RATE_HISTORY_RECORD)，取得使用者過去N月前匯成本 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/exchange-rate-history-rec/get", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, BigDecimal> getUserExchangeCostInNMonth(@RequestHeader("custId") String custId, @RequestHeader("nameCode") String nameCode, @RequestParam("inCurs") List<String> inCurs, @RequestParam("months") Integer months);

    /** 查詢資料表(FX_TRANS_CURRENCY_DISCOUNT)，依傳入條件取得目前換匯優惠資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/fx-trans-discount/query", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, BigDecimal> getFxTransCurrencyDiscountsByCodes(@RequestParam(name = "codes") List<String> codes, @RequestParam("rank") String rank, @RequestParam("locale") String locale);

}