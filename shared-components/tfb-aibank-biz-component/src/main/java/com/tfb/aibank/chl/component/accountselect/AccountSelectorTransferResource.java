package com.tfb.aibank.chl.component.accountselect;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.component.accountselect.model.RecentlyFxTransferRecordResponse;
import com.tfb.aibank.chl.component.accountselect.model.RecentlyTwTransferRecordResponse;

//@formatter:off
/**
* @(#)DepositResource.java
* 
* <p>Description:DepositResource</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, Marty Pan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@FeignClient(name = "aibank-biz-component-account-select-to-transfer-service", url = "${aibank.common.feign.service-aibank-transfer-url:http://svc-biz-transfer:8080}")
public interface AccountSelectorTransferResource {

    /** 取得最近臺幣轉帳紀錄(最多5筆) */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/tw-trans-records/recently-records/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public RecentlyTwTransferRecordResponse getRecentlyTwTransferRecord(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind, @RequestParam("uidDup") String uidDup);

    /** 取得最近外幣轉帳紀錄(最多5筆) */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/fx-trans-records/recently-records/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public RecentlyFxTransferRecordResponse getRecentlyFxTransferRecord(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind, @RequestParam("uidDup") String uidDup);

}
