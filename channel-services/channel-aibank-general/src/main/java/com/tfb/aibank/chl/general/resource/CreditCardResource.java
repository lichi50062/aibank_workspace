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
package com.tfb.aibank.chl.general.resource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.general.resource.dto.CEW302RRes;
import com.tfb.aibank.chl.general.resource.dto.CEW303RRes;
import com.tfb.aibank.chl.general.resource.dto.CEW306RResponse;
import com.tfb.aibank.chl.general.resource.dto.CEW314RResponse;
import com.tfb.aibank.chl.general.resource.dto.CreditCardOverview;

// @formatter:off
/**
 * @(#)CreditCardResource.java
 * 
 * <p>Description:溝通 service-aibank-credit-card 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/27, MP
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-chl-general-to-credit-card-service", url = "${aibank.common.feign.service-aibank-credit-card-url:http://svc-biz-credit-card:8080}")
public interface CreditCardResource {

    /**
     * 查詢客戶信用卡消費未繳總覽(一般用戶)
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards/overview/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CreditCardOverview getUserCreditCardOverviewValue(@RequestHeader("custId") String custId, @RequestHeader("nameCode") String nameCode, @RequestParam("dbu") boolean dbu);

    /**
     * 查詢客戶信用卡消費未繳總覽(一般用戶) - A
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards/overview-a/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CreditCardOverview getUserCreditCardOverviewValueA(@RequestHeader("custId") String custId, @RequestHeader("nameCode") String nameCode, @RequestParam("dbu") boolean dbu);

    /**
     * 查詢信用卡帳務資訊
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/billing-information/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW303RRes sendCEW303R(@RequestHeader("custAcid") String custId, @RequestParam("custCdno") String cardNo);

    /**
     * 查詢正卡帳單明細
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/billing-details/master-card/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW314RResponse sendCEW314R(@RequestHeader("custId11") String custId11, @RequestParam("custIdBlmt") String custIdBlmt);

    /**
     * 查詢客戶信用卡消費未繳總覽(信用卡用戶)
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards/overview/card-member/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CreditCardOverview getCardMemberCreditCardOverview(@RequestHeader("custId") String custId);

    /**
     * 查詢客戶信用卡消費未繳總覽(信用卡用戶)
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards/overview-a/card-member/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CreditCardOverview getCardMemberCreditCardOverviewA(@RequestHeader("custId") String custId);

    /** 查詢信用卡卡片總覽電文 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/credit-cards/cards/overview/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW302RRes sendCEW302R(@RequestHeader("custACID") String custACID);

    /** 查詢信用卡紅利積點 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/bonuses/bonus-point/get", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW306RResponse sendCEW306R(@RequestHeader("custId") String custId);
}
