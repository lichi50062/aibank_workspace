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
package com.tfb.aibank.component.dic;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "aibank-biz-component-dic-to-system-config-service", url = "${aibank.common.feign.service-aibank-system-config-url:http://svc-biz-system-config:8080}")
public interface DicResource {

    /** 查詢資料表(DIC)，取得目前全部的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/dic/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<DicData> getAllDicData();

}
