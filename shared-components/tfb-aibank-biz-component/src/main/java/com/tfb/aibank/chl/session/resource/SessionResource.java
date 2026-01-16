/**
 * 
 */
package com.tfb.aibank.chl.session.resource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

//@formatter:off
/**
* @(#)SessionResource.java
* 
* <p>Description:Service-AIBank-User FeignClient</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/22, John
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@FeignClient(name = "aibank-biz-component-session-to-user-service", url = "${aibank.common.feign.service-aibank-user-url:http://svc-biz-user:8080}")
public interface SessionResource {

    /** 註冊登入Session */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/accounts/user-session/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean registerAccessSession(@RequestHeader("custId") String custId, @RequestHeader("uidDup") String uidDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind, @RequestParam("loginLogPk") Integer loginLogPk, @RequestParam("sessionId") String sessionId);

    /** 更新登入Session */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/accounts/user-session/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean updateAccessTime(@RequestHeader("custId") String custId, @RequestHeader("uidDup") String uidDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind, @RequestParam("sessionId") String sessionId);

}
