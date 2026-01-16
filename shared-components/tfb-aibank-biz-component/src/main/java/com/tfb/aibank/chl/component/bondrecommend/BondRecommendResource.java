package com.tfb.aibank.chl.component.bondrecommend;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.tfb.aibank.chl.component.bondrecommend.model.BondGroupInfo;
import com.tfb.aibank.chl.component.bondrecommend.model.BondHistoryNetValue;
import com.tfb.aibank.chl.component.bondrecommend.model.BondQueryCondition;
import com.tfb.aibank.chl.component.bondrecommend.model.BondRecommendListModel;
import com.tfb.aibank.chl.component.bondrecommend.model.OverseasBondReferPriceModel;
//@formatter:off
/**
* @(#)BondRecommendResource.java
* 
* <p>Description:BondRecommendResource</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/05/26, xwr
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@FeignClient(name = "aibank-biz-component-bond-recommend-to-invest-service", url = "${aibank.common.feign.service-aibank-invest-url:http://svc-biz-loan:8080}")
public interface BondRecommendResource {

	 /** 查詢{風險等級推介清單} */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/bonds/recommend-kyc-list/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BondRecommendListModel getKYCRecommendBondsList(@RequestParam("recommendKYC") String recommendKYC, @RequestParam("locale") Locale locale);

    /** 查詢包含群組訊息的債券資訊 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/bonds/bond-group-info/query", produces = MediaType.APPLICATION_JSON_VALUE)
    List<BondGroupInfo> queryBondInfoAndGroupInfo(@RequestBody BondQueryCondition condition);

    /** 商品代號 取得 海外債商品申購報價 資料 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/foreign-bonds/overseasbondreferprice/bondnos/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<OverseasBondReferPriceModel> getOverseasBondReferPriceByBondNos(@RequestBody List<String> bondNos);
    
	/** 查詢專屬推薦歷史淨值 */
	@GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/bonds/history-net-value/get", produces = MediaType.APPLICATION_JSON_VALUE)
	List<BondHistoryNetValue> getBondHistory(@RequestParam("bondNo") String bondNo,
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyyMMddHHmmssSSS") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyyMMddHHmmssSSS") Date endDate);
    
	 /** 查詢{風險等級推介清單} */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/bonds/recommend-kyc-count/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer getCountByRecommendKycAndLocaleAndTime(@RequestParam("recommendKYC") String recommendKYC, @RequestParam("locale") Locale locale);

}
