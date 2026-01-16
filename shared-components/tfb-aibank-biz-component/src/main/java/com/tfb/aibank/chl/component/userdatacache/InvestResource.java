package com.tfb.aibank.chl.component.userdatacache;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.component.overview.model.GD320140Req;
import com.tfb.aibank.chl.component.userdatacache.model.BondOverviewReq;
import com.tfb.aibank.chl.component.userdatacache.model.EB032280Res;
import com.tfb.aibank.chl.component.userdatacache.model.EB032282Res;
import com.tfb.aibank.chl.component.userdatacache.model.NKNE01Res;
import com.tfb.aibank.chl.component.userdatacache.model.NMP8YBReq;
import com.tfb.aibank.chl.component.userdatacache.model.PeopleSoftRes;
import com.tfb.aibank.common.model.BondOverview;
import com.tfb.aibank.common.model.GoldOverview;
import com.tfb.aibank.common.model.NmiOverview;
import com.tfb.aibank.common.model.OdsVpbnd1002;
import com.tfb.aibank.common.model.StockOverview;
import com.tfb.aibank.common.model.StructuredOverview;

//@formatter:off
/**
* @(#)InvestResource.java
* 
* <p>Description:溝通 service-aibank-invest 的介面</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02,
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@FeignClient(name = "aibank-biz-component-user-data-cache-to-invest-service", url = "${aibank.common.feign.service-aibank-invest-url:http://svc-biz-invest:8080}")
public interface InvestResource {

    /** 查詢「客戶辦理投資有價證券資訊提供聲明書維護」 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/statements/investment/{func}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    EB032280Res sendEB032280(@RequestHeader("custId") String custId, @PathVariable("func") String func, @RequestParam(value = "func03", required = false) String func03);

    /** 股票客戶查詢 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/stocks/customer-query/get", produces = MediaType.APPLICATION_JSON_VALUE)
    NKNE01Res sendNKNE01(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestParam("nameCode") String nameCode);

    /** 奈米投契約明細查詢-投資總覽 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/nmi/overview/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<NmiOverview> queryNMP8YB(@RequestBody NMP8YBReq request);

    /** 海外債券-投資總覽 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/bond/overview/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<BondOverview> getBondOverviewList(@RequestBody BondOverviewReq request);

    /** 查詢海外ETF/股票(單筆、定期定額) - 投資總覽 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/etf/overview/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StockOverview> getStockOverviewList(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestParam("nameCode") String nameCode);

    /** 查詢海外債券共同營業日 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/bond/business-days/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Date> getValidBusinessComDays();

    /** 海外債 營業日/營業時間判斷 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/bond/business-day/check", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean isValidBondBusinessDay();

    /** 查詢 債券 營業日 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/bond/business-day/get", produces = MediaType.APPLICATION_JSON_VALUE)
    Date getBondBusinessDay();

    /** 查詢結構式投資(SI) */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/si-products/get", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StructuredOverview> sendSPWEBINQ(@RequestHeader("custId") String custId);

    /** 查詢不保本外匯雙享利(DCI) */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/dci-products/get", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StructuredOverview> sendDCDBPBNK(@RequestHeader("custId") String custId);

    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/gold-passbooks/detail/get", produces = MediaType.APPLICATION_JSON_VALUE)
    List<GoldOverview> sendGD320140(@RequestBody GD320140Req request);

    /** 取得前次填答結果頁 第二部分 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/people-soft/get", produces = MediaType.APPLICATION_JSON_VALUE)
    List<PeopleSoftRes> getPeopleSoftList(@RequestHeader("custId") String custId, @RequestParam("locale") String locale, @RequestParam("idType") String idType);

    /** EB032282 高中職以上學歷註記 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/academic/note/get", produces = MediaType.APPLICATION_JSON_VALUE)
    EB032282Res sendEB032282(@RequestHeader(value = "custId") String custId, @RequestParam("func") String func, @RequestParam("busTyp") String busTyp);

    /** 查詢「外國債券(自營)庫存檔」 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/foreign-bonds/self/inventory/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<OdsVpbnd1002> queryOdsVpbnd1002(@RequestHeader("custId") String custId, @RequestParam(name = "product", required = false) String product);

}
