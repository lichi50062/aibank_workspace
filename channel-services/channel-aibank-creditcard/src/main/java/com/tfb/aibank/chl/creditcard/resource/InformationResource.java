package com.tfb.aibank.chl.creditcard.resource;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.creditcard.resource.dto.RateInfo;
import com.tfb.aibank.common.model.AccountDay;

//@formatter:off
/**
 * @(#)InformationResource.java
 * 
 * <p>Description:溝通 service-aibank-information 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@FeignClient(name = "aibank-chl-creditcard-to-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
public interface InformationResource {

    /** 查詢資料表(利率檔RATE_INFO)，依條件查詢 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/rate-info/query", produces = MediaType.APPLICATION_JSON_VALUE)
    List<RateInfo> queryRateInfos(@RequestParam(name = "rateKind") String rateKind, @RequestParam(name = "rateTypeNos", required = true) List<String> rateTypeNos);

    /** 查詢帳務日 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/account-day-datas/get", produces = MediaType.APPLICATION_JSON_VALUE)
    AccountDay getAccountDay();
}
