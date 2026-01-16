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
package com.tfb.aibank.chl.component.countryname;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tfb.aibank.chl.component.countryname.model.CountryNameModel;
import com.tfb.aibank.chl.component.countryname.model.IpLocationModel;
import com.tfb.aibank.chl.component.countryname.model.SystemControlModel;

@FeignClient(name = "aibank-biz-component-ip-location-and-country-name-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
@Component("CityAndAreaResource")
public interface IpLocationAndCountryNameResource {

    /**
     * TaskId, TableName 找出 SystemControl 設定
     * 
     * @param taskId
     * @param tableName
     * @return
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}v1.0/system-control-table/{taskId}/{tableName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SystemControlModel getSystemControlTable(@PathVariable("taskId") String taskId, @PathVariable("tableName") String tableName);

    /**
     * 找出 IpLocation SystemControl 設定
     * 
     * @return
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-control-table/ip-location", produces = MediaType.APPLICATION_JSON_VALUE)
    public SystemControlModel getIpLocationSystemControlTable();

    /**
     * 查詢所有 IpLocation
     * 
     * @param useFlag
     * @return
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/ip-location/{useFlag}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IpLocationModel> getIpLocationByUseFlag(@PathVariable("useFlag") String useFlag);

    /**
     * 依 Control Use Flag , Ip Address (V4) 取出 Ip
     * 
     * @param useFlag
     * @param ipAddress
     * @return
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/ip-location/{useFlag}/{ipAddress}", produces = MediaType.APPLICATION_JSON_VALUE)
    public IpLocationModel getIpLocation(@PathVariable("useFlag") String useFlag, @PathVariable("ipAddress") String ipAddress);

    /**
     * 
     * @param useFlag
     * @param ipAddress
     * @return
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/ip-location-address/{useFlag}/{ipAddress}/range", produces = MediaType.APPLICATION_JSON_VALUE)
    public IpLocationModel getIpLocationByRange(@PathVariable("useFlag") String useFlag, @PathVariable("ipAddress") String ipAddress);

    /**
     * 
     * @param countryCode
     * @param locale
     * @return
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/aibank-country-name/{countryCode}/{locale}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CountryNameModel getCountryNameByCountryCodeAndLocale(@PathVariable("countryCode") String countryCode, @PathVariable("locale") String locale);

    /**
     * 取出所有CountryName
     * 
     * @return
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/aibank-country-name", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CountryNameModel> getAllCountryName();

    /**
     * 依UseFlag, CountryCode 取出所有 IpLocation
     * 
     * @param useFlag
     * @param countryCode
     * @return
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/ip-location-all/use-flag/{useFlag}/country-code/{countryCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IpLocationModel> getIpLocationByUseFlagAndCountryCode(@PathVariable("useFlag") String useFlag, @PathVariable("countryCode") String countryCode);

}
