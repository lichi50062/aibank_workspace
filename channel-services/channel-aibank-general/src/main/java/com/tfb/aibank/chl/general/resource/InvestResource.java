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
package com.tfb.aibank.chl.general.resource;

import com.tfb.aibank.chl.general.resource.dto.AJWEE010Req;
import com.tfb.aibank.chl.general.resource.dto.FubonStockApiRequest;
import com.tfb.aibank.chl.general.resource.dto.FubonStockApiResponse;
import com.tfb.aibank.chl.general.resource.dto.FubonStockTotalApiRequest;
import com.tfb.aibank.chl.general.resource.dto.FubonStockTotalApiResponse;
import com.tfb.aibank.chl.general.resource.dto.GD320140Req;
import com.tfb.aibank.chl.general.resource.dto.GoldPassBook;
import com.tfb.aibank.chl.general.resource.dto.N8088N1ResRep;
import com.tfb.aibank.chl.general.resource.dto.NJWEE010Req;
import com.tfb.aibank.chl.general.resource.dto.NMP8YBRes;
import com.tfb.aibank.chl.general.resource.dto.NR088N1Req;
import com.tfb.aibank.common.model.BondOverview;
import com.tfb.aibank.common.model.OdsVpbnd1002;
import com.tfb.aibank.common.model.StockOverview;
import com.tfb.aibank.common.model.StructuredOverview;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//@formatter:off
/**
* @(#)InvestmentResource.java
* 
* <p>Description: service-aibank-invest 的介面</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/02/29, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@FeignClient(name = "aibank-chl-general-to-invest-service", url = "${aibank.common.feign.service-aibank-invest-url:http://svc-biz-invest:8080}")
public interface InvestResource {

    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/stocks/fubon-stock/list", produces = MediaType.APPLICATION_JSON_VALUE)
    FubonStockApiResponse getFubonStockInfo(@RequestBody FubonStockApiRequest request);

    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/stocks/fubon-stock/get-total-stock-asset-in-twd", produces = MediaType.APPLICATION_JSON_VALUE)
    FubonStockTotalApiResponse getFubonStockTotalInfo(@RequestBody FubonStockTotalApiRequest request);

    // 奈米投契約明細查詢-個人投資資訊
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/nmi-contract-personal-details/get", produces = MediaType.APPLICATION_JSON_VALUE)
    NMP8YBRes queryNMP8YB(@RequestHeader("custId") String custId, @RequestParam("curAcctName") String curAcctName, @RequestParam("func") String func);

    // 查詢海外股票、ETF總覽
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/foreign-stocks/overview", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StockOverview> queryNR088N1(@RequestBody NR088N1Req request);

    // 查詢債券DBU帳戶總覽
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/foreign-bonds/overview/DBU", produces = MediaType.APPLICATION_JSON_VALUE)
    List<BondOverview> queryNJWEE010(@RequestBody NJWEE010Req request);

    // 查詢債券OBU帳戶總覽
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/foreign-bonds/overview/OBU", produces = MediaType.APPLICATION_JSON_VALUE)
    List<BondOverview> queryAJWEE010(@RequestBody AJWEE010Req request);

    // 查詢不保本外匯雙享利(DCI)
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/dci-products/get", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StructuredOverview> sendDCDBPBNK(@RequestHeader("custId") String custId);

    // 查詢結構式投資(SI)
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/si-products/get", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StructuredOverview> sendSPWEBINQ(@RequestHeader("custId") String custId);

    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/gold-passbooks/detail/get", produces = MediaType.APPLICATION_JSON_VALUE)
    List<GoldPassBook> queryGoldInfoForInvestCard(@RequestBody GD320140Req req);

    /** 查詢「外國債券(自營)庫存檔」 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/foreign-bonds/self/inventory/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<OdsVpbnd1002> queryOdsVpbnd1002(@RequestHeader("custId") String custId, @RequestParam(name = "product", required = false) String product);

    /** N8088N1 海外股票、ETF定期定額總覽明細 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/us-stocks/regular-quota/overview-detail/query", produces = MediaType.APPLICATION_JSON_VALUE)
    List<N8088N1ResRep> sendN8088N1(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestParam("nameCode") String nameCode, @RequestHeader(name = "x-aibank-eaichannel", required = false) String eaichannel);
}
