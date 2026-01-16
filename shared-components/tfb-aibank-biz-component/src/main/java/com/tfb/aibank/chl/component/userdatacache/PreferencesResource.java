package com.tfb.aibank.chl.component.userdatacache;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.component.devicebinding.model.TrustDeviceInfo;
import com.tfb.aibank.chl.component.userdatacache.model.KycAnswerResponse;
import com.tfb.aibank.chl.component.userdatacache.model.MissionDetail;
import com.tfb.aibank.chl.component.userdatacache.model.MissionMaster;
import com.tfb.aibank.chl.component.userdatacache.model.UpdateEB12020006;

// @formatter:off
/**
 * @(#)PreferencesResource.java
 * 
 * <p>Description:溝通 service-aibank-preferences 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/08, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-component-to-preferences-service", url = "${aibank.common.feign.service-aibank-preferences-url:http://svc-biz-preferences:8080}")
public interface PreferencesResource {

    /** KYC填答結果查詢 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/kyc-answer/get", produces = MediaType.APPLICATION_JSON_VALUE)
    KycAnswerResponse sendEB12020006KYC(@RequestHeader("custId") String custId, @RequestParam("nameCode") String nameCode, @RequestParam("funcCod") String funcCod, @RequestParam("func") String func);

    /** KYC填答結果更新 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/kyc-answer/update", produces = MediaType.APPLICATION_JSON_VALUE)
    KycAnswerResponse updateEB12020006KYC(@RequestBody UpdateEB12020006 eb12020006);

    /** 查詢當前任務牆資訊(MISSION_LEVEL、MISSION_CODE) */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mission/mission-wall/current-task/{missionLevel},{missionCode}/query")
    List<MissionDetail> queryMissionDetailsByCondition(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestParam("uidDup") String uidDup, @RequestParam("companyKind") Integer companyKind, @PathVariable("missionLevel") String missionLevel, @PathVariable("missionCode") String missionCode);

    /** 更新任務牆活動明細檔(MISSION_DETAIL) */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/mission/mission-wail/mission-detail/update")
    MissionDetail updateMissionDetail(@RequestBody MissionDetail model);

    /** 查詢當前任務牆資訊(MISSION_LEVEL)，該次完成任務是否剛好為關卡完成任務 */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mission/mission-wall/mission-complete-count/{missionLevel}/query")
    Integer getCountByCondition(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @RequestParam("uidDup") String uidDup, @RequestParam("companyKind") Integer companyKind, @PathVariable("missionLevel") String missionLevel);

    /** 任務牆活動主檔 MISSION_MASTER 查詢 By User */
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/mission/missionmaster/{companyKind}/get")
    MissionMaster queryMissionMaster(@RequestHeader(value = "custId") String custId, @RequestHeader(value = "userId") String userId, @RequestHeader("uidDup") String uidDup, @PathVariable("companyKind") Integer companyKind);

    /** 任務牆活動主檔 MISSION_MASTER update */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/mission/missionmaster/update")
    MissionMaster updateMissionMaster(@RequestBody MissionMaster missionMasterModel);

    /** 取得雙重驗證登入設定 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/two-factor-settings/get", produces = MediaType.APPLICATION_JSON_VALUE)
    TrustDeviceInfo getTwoFactorInfo(@RequestBody TrustDeviceInfo request);

    /** 雙重驗證登入設定-更新 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/two-factor-settings/update", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean updateTwoFactorSettings(@RequestBody TrustDeviceInfo request);

    /** 雙重驗證登入設定-刪除 */
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/two-factor-settings/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean deleteTwoFactorInfo(@RequestBody TrustDeviceInfo request);
}
