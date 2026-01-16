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
package com.tfb.aibank.chl.component.customergroupid;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

// @formatter:off
/**
 * @(#)CustomerGroupIdResource.java
 * 
 * <p>Description:溝通 service-aibank-user 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27Marty
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-customer-group-id-to-user-service", url = "${aibank.common.feign.service-aibank-user-url:http://svc-biz-user:8080}")
@Component("CustomerGroupIdResource")
public interface CustomerGroupIdResource {

    /** 取得Table CUSTOMER_GROUP_ID(客群名單)全部資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/customer-group-id/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CustomerGroupId> getAllCustomerGroupIds();
}
