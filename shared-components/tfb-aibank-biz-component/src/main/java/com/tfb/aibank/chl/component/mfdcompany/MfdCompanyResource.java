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
package com.tfb.aibank.chl.component.mfdcompany;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)MfdCompanyResource.java
 * 
 * <p>Description:溝通 service-aibank-information 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/10, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-mfd-company-to-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
@Component("MfdCompanyResource")
public interface MfdCompanyResource {

    /** 查詢資料表(MFD_COMPANY)，取得目前全部的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mfd-company/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<MfdCompany> getAllMfdCompany();

}
