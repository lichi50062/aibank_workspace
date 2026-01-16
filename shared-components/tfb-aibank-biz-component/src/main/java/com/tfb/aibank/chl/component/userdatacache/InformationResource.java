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

import java.util.Date;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.component.userdatacache.model.FinancialMgmMemberLevel;
import com.tfb.aibank.chl.component.userdatacache.model.InvestmentNoticeSetting;
import com.tfb.aibank.common.model.AccountDay;

// @formatter:off
/**
 * @(#)InformationResource.java
 * 
 * <p>Description:溝通 service-aibank-information 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/15, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-user-data-cache-to-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
public interface InformationResource {

    /** 讀取分行別(5碼) */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/bank-branchs/code/get", produces = MediaType.APPLICATION_JSON_VALUE)
    String getBranchNo(@RequestParam("accNo") String accNo);

    /** 查詢帳務日 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/account-day-datas/get", produces = MediaType.APPLICATION_JSON_VALUE)
    AccountDay getAccountDay();

    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/business-day-datas/{startDate}/{endDate}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Date> businessDayInRange(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyyMMddHHmmssSSS") Date startDate, @PathVariable("endDate") @DateTimeFormat(pattern = "yyyyMMddHHmmssSSS") Date endDate);

    /** 查詢使用者投資類交易通知設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/investment-notice-setting/{uidDup},{companyKind}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    InvestmentNoticeSetting findInvestmentNoticeSetting(@RequestHeader("custId") String custId, @PathVariable("uidDup") String uidDup, @RequestHeader("userId") String userId, @PathVariable("companyKind") Integer companyKind);

    /** 發查 EB6705021 取得財管會員等級 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/fin-mamagement-member-lv/get", produces = MediaType.APPLICATION_JSON_VALUE)
    FinancialMgmMemberLevel getFinancialMgmMemberLevel(@RequestHeader("custIxd") String custIxd);

}
