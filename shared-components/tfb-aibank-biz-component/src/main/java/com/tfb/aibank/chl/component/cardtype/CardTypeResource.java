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
package com.tfb.aibank.chl.component.cardtype;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "aibank-biz-component-card-type-to-credit-card-service", url = "${aibank.common.feign.service-aibank-credit-card-url:http://svc-biz-credit-card:8080}")
@Component("CardTypeResource")
public interface CardTypeResource {

    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/card-type/list", produces = { MediaType.APPLICATION_JSON_VALUE })
    List<CardType> getCardTypes();

}
