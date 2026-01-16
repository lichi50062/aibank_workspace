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
package com.tfb.aibank.chl.component.userdatacache;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.component.userdatacache.model.EB12020011Res;
import com.tfb.aibank.chl.component.userdatacache.model.NFEE074Req;
import com.tfb.aibank.chl.component.userdatacache.model.NJWEEN02Req;
import com.tfb.aibank.chl.component.userdatacache.model.NJWEEN02Res;
import com.tfb.aibank.chl.component.userdatacache.model.SDACTQ12Req;
import com.tfb.aibank.chl.component.userdatacache.model.SDACTQ12ResRep;
import com.tfb.aibank.chl.component.userdatacache.model.TrustContract;

// @formatter:off
/**
 * @(#)DepositResource.java
 * 
 * <p>Description:溝通 service-aibank-deposit 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-user-data-cache-to-deposit-service", url = "${aibank.common.feign.service-aibank-deposit-url:http://svc-biz-deposit:8080}")
public interface DepositResource {
    /**
     * 數位存款開戶
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/digital-account/open-account/get", produces = MediaType.APPLICATION_JSON_VALUE)
    EB12020011Res sendEB12020011(@RequestHeader("custId") String custId, @RequestParam("funcCod") String funcCod, @RequestParam("func") String func, @RequestParam("appCode") String appCode);

    /** 查詢 金錢信託契約查詢(委託人及監察人角度) */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/money-trust/contract/cust/get", produces = MediaType.APPLICATION_JSON_VALUE)
    List<TrustContract> sendNMWEB01(@RequestHeader("custId") String custId);

    /** 金錢信託-金錢信託定存單臺幣現值查詢(NMWEB03) */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/money-trust/fixed-total/get", produces = MediaType.APPLICATION_JSON_VALUE)
    BigDecimal sendNMWEB03(@RequestParam("contractNo") String contractNo);

    /**查詢 金錢基金臺幣總數 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/money-trust/fund/totalAmt/get", produces = MediaType.APPLICATION_JSON_VALUE)
    BigDecimal sendNFEE074(@RequestBody NFEE074Req req);

    /** 金錢信託-特金股票(海外/ETF)台幣現值查詢 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/money-trust/etf-total/get", produces = MediaType.APPLICATION_JSON_VALUE)
    BigDecimal doNMWEB05(@RequestParam("contractNo") String contractNo);

    /** 查詢 金錢信託債券網銀台幣總值DBU */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/money-trust/bond-value/DBU/get", produces = MediaType.APPLICATION_JSON_VALUE)
    NJWEEN02Res sendNJWEEN02(@RequestBody NJWEEN02Req request);

    /**
     * SDACTQ12 信託資產_SI產品約當臺幣
     * @param request
     * @return
     * */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/money-trust/si-info/get", produces = MediaType.APPLICATION_JSON_VALUE)
    SDACTQ12ResRep sendSDACTQ12(@RequestBody SDACTQ12Req request);
}
