package com.tfb.aibank.chl.component.activityonline;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

//@formatter:off
/**
* @(#)CreditCardResource.java
* 
* <p>Description:CreditCardResource</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/12, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@FeignClient(name = "aibank-biz-component-activity-online-to-credit-card-service", url = "${aibank.common.feign.service-aibank-credit-card-url:http://svc-biz-credit-card:8080}")
public interface CreditCardResource {

    /** 查詢人數統計資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/cc-activity-online/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer getActivetyOnlineCount();
}
