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
package com.tfb.aibank.component.accountgeneralrules;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "aibank-biz-component-account-general-rules-to-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
@Component("AccountGeneralRulesResource")
public interface AccountGeneralRulesResource {

    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/account-general-rules-datas/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AccountGeneralRules> getAllAccountGeneralRules();

}
