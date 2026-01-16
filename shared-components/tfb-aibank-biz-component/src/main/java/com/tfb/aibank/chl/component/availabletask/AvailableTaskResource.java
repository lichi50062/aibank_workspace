/**
 * 
 */
package com.tfb.aibank.chl.component.availabletask;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

//@formatter:off
/**
* @(#)TaskResource.java
* 
* <p>Description:提供有誤別碼使用者登入時，限制其可使用的功能TASK清單</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/05, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@FeignClient(name = "aibank-biz-component-available-task-to-system-config-service", url = "${aibank.common.feign.service-aibank-system-config-url:http://svc-biz-system-config:8080}")
@Component("AvailableTaskResource")
public interface AvailableTaskResource {

    /** 查詢名單控管TASK */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/availableTask/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public AvailableTaskResponse getAvailableTask();

}