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
package com.tfb.aibank.chl.component.accountpromo;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)AccountPromoResource.java
 * 
 * <p>Description:溝通 service-aibank-deposit 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-account-promo-to-deposit-service", url = "${aibank.common.feign.service-aibank-deposit-url:http://svc-biz-deposit:8080}")
@Component("AccountPromoResource")
public interface AccountPromoResource {

    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/account-promo/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AccountPromo> getAllAccountPromos();

}
