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
package com.tfb.aibank.chl.component.cityandarea;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "aibank-biz-component-city-and-area-to-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
@Component("CityAndAreaResource")
public interface CityAndAreaResource {

    /** 查詢資料表(CITY)，取得目前全部的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/city/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<City> getAllCity();

    /** 查詢資料表(CITY_AREA)，取得目前全部的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/city-area/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CityArea> getAllCityArea();

    /** 查詢資料表(ZIPCODE)，取得目前全部的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/zipcode/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ZipCode> getAllZipcode();

}
