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
package com.tfb.aibank.chl.general.resource;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.component.userdatacache.model.MissionDetail;
import com.tfb.aibank.chl.general.resource.dto.CampaignMappingDetailModel;
import com.tfb.aibank.chl.general.resource.dto.LineSubscribeCheckResponse;
import com.tfb.aibank.chl.general.resource.dto.MissionDetailMappingModel;
import com.tfb.aibank.chl.general.resource.dto.MissionListModel;
import com.tfb.aibank.chl.general.resource.dto.MissionMasterModel;
import com.tfb.aibank.chl.general.resource.dto.PushTxnModel;
import com.tfb.aibank.chl.general.resource.dto.UpdatePushTokenRequest;
import com.tfb.aibank.chl.general.resource.vo.faq.FaqTypeVo;
import com.tfb.aibank.chl.general.resource.vo.faq.GuideBizVo;

// @formatter:off
/**
 * @(#)PreferencesResource.java
 * 
 * <p>Description:溝通 service-aibank-preferences 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/06, Marty
 * <ol>
 *  <li>初版</li>
 * </ol>
 * v1.1, 2025/08/22, GitHub Copilot
 * <ol>
 *  <li>新增updatePushSettings方法</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-chl-general-to-preferences-service", url = "${aibank.common.feign.service-aibank-preferences-url:http://svc-biz-preferences:8080}")
public interface PreferencesResource {

    /** 常見問題項目大類 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/faq/faq-type/{locale}/list", produces = { MediaType.APPLICATION_JSON_VALUE })
    List<FaqTypeVo> queryAllFaqData(@PathVariable("locale") String locale);

    /** 操作教學業務類別 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/faq/guide-type/{locale}/list", produces = { MediaType.APPLICATION_JSON_VALUE })
    List<GuideBizVo> queryAllGuideData(@PathVariable("locale") String locale);

    /** 取得Line訂閱通知查詢 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/line-check/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public LineSubscribeCheckResponse subscribeCheck(@RequestHeader("custId") String custId);

    /** 任務牆活動主檔 MISSION_MASTER 查詢 By User */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mission/missionmaster/{companyKind}/get")
    MissionMasterModel queryMissionMasterByUser(@RequestHeader(value = "custId") String custId, @RequestHeader(value = "userId") String userId, @RequestHeader("uidDup") String uidDup, @PathVariable("companyKind") Integer companyKind);

    /** 任務牆活動主檔 MISSION_DETAIL 查詢 By User and level */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mission/missiondetaillevel/{companyKind}/get")
    List<MissionDetailMappingModel> queryMissionDetailByUserAndLevel(@RequestHeader(value = "custId") String custId, @RequestHeader(value = "userId") String userId, @RequestHeader("uidDup") String uidDup, @PathVariable("companyKind") Integer companyKind, @RequestParam("level") String level, @RequestParam("locale") String locale);

    /** 任務牆活動主檔 MISSION_DETAIL 查詢 By User */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mission/missiondetailonly/{companyKind}/get")
    List<MissionDetail> queryMissionDetailOnlyByUser(@RequestHeader(value = "custId") String custId, @RequestHeader(value = "userId") String userId, @RequestHeader("uidDup") String uidDup, @PathVariable("companyKind") Integer companyKind);
    /** 任務牆活動名單檔 MISSION_LIST 查詢 By User */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mission/missionlist/get")
    MissionListModel queryMissionListByUser(@RequestHeader(value = "companyUid") String companyUid);

    /** 抓取 CAMPAIGN_MAPPING_DETAIL By campaignId */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/campaign/campaignmappingdetailbycampaignid/get")
    List<CampaignMappingDetailModel> getCampaignMappingDetailByCampaignId(@RequestParam("campaignId") String campaignId);

    /** 更新 FIRST_DOWNLOAD_RECORD 的 Push Token */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/first-download-record/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean updatePushToken(@RequestBody UpdatePushTokenRequest request);

    /** 執行訊息通知設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/push-settings/execution", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean updatePushSettings(@RequestBody PushTxnModel model);

    /** 取得訊息通知設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/push-info/get", produces = MediaType.APPLICATION_JSON_VALUE)
    PushTxnModel getPushInfo(@RequestBody PushTxnModel request, @RequestHeader("deviceId") String deviceId);
}
