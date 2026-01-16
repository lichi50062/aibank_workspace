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
package com.tfb.aibank.chl.component.remititemlevelthree;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @formatter:off
/**
 * @(#)RemitItemLevelThreeResource.java
 * 
 * <p>Description:溝通 service-aibank-exchangerate 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-remit-item-level-three-to-foreign-exchange-service", url = "${aibank.common.feign.service-aibank-foreign-exchange-url:http://svc-biz-foreign-exchange:8080}")
@Component("RemitItemLevelThreeResource")
public interface RemitItemLevelThreeResource {

    /**
     * 查詢資料表(REMIT_ITEM_LEVEL_THREE)，取得目前全部的資料
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/remit-item-level3/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<RemitItemLevelThree> getAllRemitItemLevelThree(@RequestParam(value = "fetchBak", required = false) Boolean fetchBak);

    /**
     * 查詢資料表(REMIT_ITEM_LEVEL_THREE)，取得目前全部的資料
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/remit-item-in-level3/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<RemitItemLevelThree> getAllRemitItemInLevelThree(@RequestParam(value = "fetchBak", required = false) Boolean fetchBak);

    /**
     * 查詢資料表(REMIT_ITEM_LEVEL_THREE_LARGE)，取得目前全部的資料
     */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/remit-item-level3-large/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<RemitItemLevelThreeLarge> getAllRemitItemLevelThreeLarge();

    /** 查詢資料表(REMIT_ITEM_LEVEL_ONE)，取得目前全部的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/remit-items/level-one/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<RemitItemLevelOne> getAllRemitItemLevelOne();

    /** 查詢資料表(REMIT_ITEM_LEVEL_TWO)，取得目前全部的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/remit-items/level-two/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<RemitItemLevelTwo> getAllRemitItemLevelTwo();

}
