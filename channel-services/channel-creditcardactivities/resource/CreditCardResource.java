package com.tfb.aibank.chl.creditcardactivities.resource;

import com.tfb.aibank.chl.creditcardactivities.resource.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tfb.aibank.chl.creditcardactivities.resource.dto.AddActivetyOnlineRecordRequest;
import com.tfb.aibank.chl.creditcardactivities.resource.dto.CEW223RRequest;
import com.tfb.aibank.chl.creditcardactivities.resource.dto.CEW223RResponse;
import com.tfb.aibank.chl.creditcardactivities.resource.dto.CreditCardActivityResponse;

//@formatter:off
/**
 * @(#)CreditCardResource.java
 * 
 * <p>Description:溝通 service-aibank-credit-card 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/12/14, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@FeignClient(name = "aibank-chl-creditcardactivities-to-credit-card-service", url = "${aibank.common.feign.service-aibank-credit-card-url:http://svc-biz-credit-card:8080}")
public interface CreditCardResource {

    /** 新增一筆CC_ACTIVITY_ONLINESTATIS 紀錄 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/cc-activity-online/add", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean addActivetyOnlineRecord(@RequestBody AddActivetyOnlineRecordRequest request);

    /** 查詢信用卡活動 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/cc-activity/get", produces = { MediaType.APPLICATION_JSON_VALUE })
    CreditCardActivityResponse getCreditCardActivity();

    /** 查詢熱門信用卡活動 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/cc-hot-activity/get", produces = { MediaType.APPLICATION_JSON_VALUE })
    CreditCardHotActivityResponse getCreditCardHotActivity();

    /** 信用卡活動登錄/查詢 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/cc-activity-record/update", produces = MediaType.APPLICATION_JSON_VALUE)
    CEW223RResponse sendCEW223R(@RequestBody CEW223RRequest request);

}
