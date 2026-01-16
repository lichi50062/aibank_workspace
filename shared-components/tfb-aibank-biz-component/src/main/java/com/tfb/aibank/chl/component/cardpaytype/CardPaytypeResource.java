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
package com.tfb.aibank.chl.component.cardpaytype;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "aibank-biz-component-card-pay-type-to-credit-card-service", url = "${aibank.common.feign.service-aibank-credit-card-url:http://svc-biz-credit-card:8080}")
@Component("CardTypeResource")
public interface CardPaytypeResource {

    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/card-paytype/list", produces = { MediaType.APPLICATION_JSON_VALUE })
    List<CardPaytype> getCardPaytypes();

}
