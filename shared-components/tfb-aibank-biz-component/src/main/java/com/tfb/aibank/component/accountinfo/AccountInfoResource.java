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
package com.tfb.aibank.component.accountinfo;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "aibank-biz-component-account-info-to-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
@Component("AccountInfoResource")
public interface AccountInfoResource {

    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/account-info-datas/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AccountInfo> getAllAccountInfo();

}
