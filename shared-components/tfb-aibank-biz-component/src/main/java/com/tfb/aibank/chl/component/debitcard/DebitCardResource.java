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
package com.tfb.aibank.chl.component.debitcard;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)DebitCardResource.java
 * 
 * <p>Description:簽帳卡服務資源</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/06, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-debit-card-to-user-service", url = "${aibank.common.feign.service-aibank-user-url:http://svc-biz-user:8080}")
public interface DebitCardResource {

    /** 取得所有簽帳卡卡面 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/debit-card/picture/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<DebitCard> getAllDebitCardImg();

}
