package com.tfb.aibank.chl.system.resource;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.system.resource.dto.AibankPersonalResultpage;
import com.tfb.aibank.chl.system.resource.dto.AibankTxnResultpage;
import com.tfb.aibank.chl.system.resource.dto.Announcement;
import com.tfb.aibank.chl.system.resource.dto.UserCardViewLogModel;
import com.tfb.aibank.chl.system.resource.dto.UserCardViewLogRequest;
import com.tfb.aibank.chl.system.resource.dto.WidgetRecordRequest;

// @formatter:off
/**
 * @(#)InformationResource.java
 * 
 * <p>Description:溝通 service-aibank-information 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/25, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-chl-system-to-information-service", url = "${aibank.common.feign.service-aibank-information-url:http://svc-biz-information:8080}")
public interface InformationResource {

    /** 紀錄交易結果頁畫面 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/records/txn-result-page/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean addTxnResultPageRecord(@RequestBody AibankTxnResultpage model);

    /** 查詢資料表(ANNOUNCEMENT)，取得符合條件的資料 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/announcement/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Announcement> getAnnouncementList();

    /** 紀錄個人化版位畫面 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/records/personal-result-page/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean addPersonalResultPageRecord(@RequestBody @Validated AibankPersonalResultpage request);

    /** 紀錄 Widget Record 資料 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/records/widget-record/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    String addWidgetRecord(@RequestBody @Validated WidgetRecordRequest request);

    /** 查詢用戶最後觀看廣告紀錄 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/user-card-views/lastest/get", produces = MediaType.APPLICATION_JSON_VALUE)
    UserCardViewLogModel findLastSeenCard(@RequestHeader("custId") String custId, @RequestParam("uidDup") String uidDup, @RequestHeader("userId") String userId, @RequestParam("companyKind") Integer companyKind, @RequestParam("txn") String txn);

    /** 查詢用戶最後觀看廣告紀錄 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/user-card-views/device-uuid-lastest/get", produces = MediaType.APPLICATION_JSON_VALUE)
    UserCardViewLogModel findLastSeenCard(@RequestHeader("deviceUuid") String deviceUuid, @RequestParam("txn") String txn);

    /** 紀錄用戶觀看廣告紀錄 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/user-card-views/card-view/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    void saveViewLog(@RequestBody UserCardViewLogRequest rq);

    /** 紀錄廣告成效點擊 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/card-kpi-records/click/save")
    void saveClickLog(@RequestParam("cardKey") Integer cardKey, @RequestParam("cardVersion") Integer cardVersion);
}
