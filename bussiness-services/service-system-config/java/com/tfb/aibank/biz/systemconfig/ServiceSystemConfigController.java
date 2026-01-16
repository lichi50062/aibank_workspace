package com.tfb.aibank.biz.systemconfig;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.tfb.aibank.biz.systemconfig.services.model.NCAOT006Activity;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.ibmb.base.BaseController;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.util.ValidateParamUtils;
import com.tfb.aibank.biz.systemconfig.services.ServiceFactory;
import com.tfb.aibank.biz.systemconfig.services.availtask.AvailableTaskService;
import com.tfb.aibank.biz.systemconfig.services.availtask.model.AvailableTaskResponse;
import com.tfb.aibank.biz.systemconfig.services.cache.CacheService;
import com.tfb.aibank.biz.systemconfig.services.cache.model.CacheInfoModel;
import com.tfb.aibank.biz.systemconfig.services.dic.DicService;
import com.tfb.aibank.biz.systemconfig.services.dic.model.DicModel;
import com.tfb.aibank.biz.systemconfig.services.errorcode.ErrorCodeService;
import com.tfb.aibank.biz.systemconfig.services.errorcode.model.ErrorCodeModel;
import com.tfb.aibank.biz.systemconfig.services.errorcode.model.ErrorInfoModel;
import com.tfb.aibank.biz.systemconfig.services.errorcode.model.TxSystemMapModel;
import com.tfb.aibank.biz.systemconfig.services.menu.model.MenuModel;
import com.tfb.aibank.biz.systemconfig.services.roletask.RoleTaskService;
import com.tfb.aibank.biz.systemconfig.services.roletask.model.TaskModel;
import com.tfb.aibank.biz.systemconfig.services.session.SessionService;
import com.tfb.aibank.biz.systemconfig.services.session.model.KickSessionRequest;
import com.tfb.aibank.biz.systemconfig.services.session.model.KickSessionResponse;
import com.tfb.aibank.biz.systemconfig.services.systemparam.SystemParamService;
import com.tfb.aibank.biz.systemconfig.services.systemparam.model.SystemParamModel;
import com.tfb.aibank.biz.systemconfig.services.tasktime.TaskTimeService;
import com.tfb.aibank.biz.systemconfig.services.tasktime.model.TaskTimeModel;
import com.tfb.aibank.chl.component.task.TaskPage;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;

// @formatter:off
/**
 * @(#)ServiceSystemConfigController.java
 *
 * <p>Description:系統設定類 API 入口</p>
 * <p>每支API請填寫說明，格式及內容示意如左 @Operation(summary = "{動詞} + ({名詞} 或 {形容詞})")</p>
 * <p>此類別作為API的接口，負責轉發請求。應避免在此類別中對資料本身進行加工處理，亦禁止@Autowired其他資源。</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@RestController
public class ServiceSystemConfigController extends BaseController {

    @Autowired
    private ServiceFactory serviceFactory;

    @Operation(summary = "查詢目前全部 Cache Manager 狀態")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/caches/list", produces = { MediaType.APPLICATION_JSON_VALUE })
    public BaseServiceResponse<List<CacheInfoModel>> listCache() {
        CacheService service = serviceFactory.createCacheCodeCollection();
        return BaseServiceResponse.of(service.listCache());
    }

    @Operation(summary = "查詢指定 Cache 內容")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/caches/{cacheKey}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<String> getCacheContent(@PathVariable("cacheKey") String cacheKey) {

        CacheService service = serviceFactory.createCacheCodeCollection();
        String value = service.getCacheContent(cacheKey, false);

        return BaseServiceResponse.of(value);
    }

    @Operation(summary = "清除指定的 Cache 資料")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/caches/{cacheKey}/delete", produces = { MediaType.APPLICATION_JSON_VALUE })
    public BaseServiceResponse<Boolean> deleteCacheContent(@PathVariable("cacheKey") String cacheKey, HttpServletRequest request) {
        CacheService service = serviceFactory.createCacheCodeCollection();
        try {
            String requestURI = URLDecoder.decode(request.getRequestURI(), "UTF-8");
            return BaseServiceResponse.of(service.deleteCacheContent(cacheKey, requestURI));
        }
        catch (UnsupportedEncodingException e) {
            logger.error("error decoding request uri", e);
        }
        return BaseServiceResponse.of(false);
    }

    @Operation(summary = "清除全部的 Cache 資料")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/caches/delete/all", produces = { MediaType.APPLICATION_JSON_VALUE })
    public BaseServiceResponse<Boolean> deleteCacheAll(HttpServletRequest request) {
        CacheService service = serviceFactory.createCacheCodeCollection();
        return BaseServiceResponse.of(service.deleteAllCache());
    }

    @Operation(summary = "查詢指定 Category 下的選單項目")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/menus/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<MenuModel>> getMenu(@RequestParam(name = "categories") List<String> categories) {
        categories = ValidateParamUtils.validParamList(categories);
        return BaseServiceResponse.of(serviceFactory.createMenuCollection().findByCategory(categories));
    }

    @Operation(summary = "查詢MenuId 相關的關鍵字")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/menus/menu-keyword/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Map<String, List<String>>> getMenuKeywordsForSearch(@RequestBody List<String> menuIds) {
        menuIds = ValidateParamUtils.validParamList(menuIds);
        return BaseServiceResponse.of(serviceFactory.createMenuCollection().getMenuKeywordsForSearch(menuIds));
    }

    @Operation(summary = "查詢資料表(MENU_NAME)，取得目前全部的資料")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/menus-name/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Map<String, Map<String, String>>> getMenuNames() {
        return BaseServiceResponse.of(serviceFactory.createMenuCollection().findAllMenuNames());
    }

    @Operation(summary = "清除APP登入狀態")
    @PreAuthorize("permitAll()")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/clean-app-session/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<KickSessionResponse> kickSession(@RequestBody KickSessionRequest request) {
        return BaseServiceResponse.of(serviceFactory.createSessionCollection().kickSession(request));
    }

    @Operation(summary = "查詢資料表(TASK)，取得目前全部AIBANK的資料")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/tasks/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<TaskModel>> getTasks() {
        RoleTaskService service = serviceFactory.createRoleTaskCollection();
        return BaseServiceResponse.of(service.getAllAiBankTasks());
    }

    @Operation(summary = "查詢資料表(TASK_PAGE)，取得目前全部AIBANK頁面的資料")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/task-pages/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<TaskPage>> getAllTaskPages() {
        RoleTaskService service = serviceFactory.createRoleTaskCollection();
        return BaseServiceResponse.of(service.getAllAiBankTaskPages());
    }

    @Operation(summary = "查詢資料表(ROLE_TASK)，取得目前全部的資料")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/role-tasks/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Map<String, List<String>>> getRoleTasks() {
        RoleTaskService service = serviceFactory.createRoleTaskCollection();
        return BaseServiceResponse.of(service.findRoleTaskMapping());
    }

    @Operation(summary = "查詢資料表(ERROR_CODE)，取得指定 SYSTEM_ID 的資料")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/error-codes/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<ErrorCodeModel>> findErrorCodeBySystemIdAndLocale(@RequestParam("systemId") List<String> systemIdList, @RequestParam("locale") List<String> localeList) {
        systemIdList = ValidateParamUtils.validParamList(systemIdList);
        localeList = ValidateParamUtils.validParamList(localeList);
        ErrorCodeService service = serviceFactory.createErrorCodeCollection();
        return BaseServiceResponse.of(service.findBySystemIdAndLocale(systemIdList, localeList));
    }

    @Operation(summary = "查詢資料表(ERROR_CODE)，取得目前全部的資料")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/error/error-code/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<ErrorCodeModel>> getAllErrorCode() {
        ErrorCodeService service = serviceFactory.createErrorCodeCollection();
        return BaseServiceResponse.of(service.getAllErrorCode());
    }

    @Operation(summary = "查詢資料表(ERROR_INFO)，取得目前全部的資料")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/error/error-info/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<ErrorInfoModel>> getAllErrorInfo() {
        ErrorCodeService service = serviceFactory.createErrorCodeCollection();
        return BaseServiceResponse.of(service.getAllErrorInfo());
    }

    @Operation(summary = "查詢資料表(TX_SYSTEM_MAP)，取得目前全部的資料")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/error/tx-system-map/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<TxSystemMapModel>> getAllTxSystemMap() {
        ErrorCodeService service = serviceFactory.createErrorCodeCollection();
        return BaseServiceResponse.of(service.getAllTxSystemMap());
    }

    @Operation(summary = "查詢資料表(SYSTEM_PARAM)，取得目前全部的資料")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-param/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<SystemParamModel>> getAllSystemParam() {
        SystemParamService service = serviceFactory.createSystemParamCollection();
        return BaseServiceResponse.of(service.getAllData());
    }

    @Operation(summary = "查詢資料表(DIC)，取得目前全部的資料")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/dic/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<DicModel>> getAllDic() {
        DicService service = serviceFactory.createDicService();
        return BaseServiceResponse.of(service.getAllData());
    }

    @Operation(summary = "更新指定的資料表(SYSTEM_PARAM)紀錄")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/{category},{paramKey}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseServiceResponse<SystemParamModel>> updateSystemParam(@PathVariable("category") String category, @PathVariable("paramKey") String paramKey, @RequestBody SystemParamModel param) throws ActionException {
        SystemParamService systemParamService = serviceFactory.createSystemParamCollection();
        param.setCategory(category);
        param.setParamKey(paramKey);
        SystemParamModel accessRequest = accessControl(param, SystemParamModel.class);
        SystemParamModel result = systemParamService.updateParam(accessRequest);
        if (result != null) {
            return ResponseEntity.ok(BaseServiceResponse.of(result));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseServiceResponse.of(null));
    }

    @Operation(summary = "查詢資料表(TASK_TIME)，取得目前全部交易代號的可交易時間")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/task-time/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<TaskTimeModel>> getAllTaskTimes() {
        TaskTimeService service = serviceFactory.createTaskTimeService();
        return BaseServiceResponse.of(service.getAllData());
    }

    @Operation(summary = "查詢名單控管TASK")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/availableTask/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<AvailableTaskResponse> getAvailableTask() {
        AvailableTaskService service = serviceFactory.createAvailableTaskService();
        return BaseServiceResponse.of(service.getAvailableTask());
    }

    @Operation(summary = "查詢線上人數")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/session/online-user/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Integer> getOnlineUserCount() {
        SessionService service = serviceFactory.createSessionCollection();
        return BaseServiceResponse.of(service.queryOnlineUserCount());
    }

    @Operation(summary = "查詢線上登入人數")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/session/online-login-user/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Integer> getOnlineLoginUserCount() {
        SessionService service = serviceFactory.createSessionCollection();
        return BaseServiceResponse.of(service.queryOnlineLoginUserCount());
    }

    @Operation(summary = "達上限暫停登入Session更新")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/session/max-login-forbid/{maxLoginForbid}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> updateMaxLoginForbid(@PathVariable("maxLoginForbid") String maxLoginForbidy) {
        maxLoginForbidy = accessControl(maxLoginForbidy, String.class);
        return BaseServiceResponse.of(serviceFactory.createSessionCollection().setMaxLoginForbid(maxLoginForbidy));
    }

    @Operation(summary = "達上限暫停登入Session是否開啟狀態")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/session/max-login-forbid/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> queryMaxLoginForbid() {
        return BaseServiceResponse.of(serviceFactory.createSessionCollection().isMaxLoginForbidOpen());
    }

    @Operation(summary = "查詢資料表(SYSTEM_PARAM)，依指定條件查詢")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/{category},{paramKey}/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<SystemParamModel> getSystemParam(@PathVariable("category") String category, @PathVariable("paramKey") String paramKey) {
        SystemParamService service = serviceFactory.createSystemParamCollection();
        return BaseServiceResponse.of(service.findByCategoryAndParamKey(category, paramKey));
    }

    @Operation(summary = "查詢資料表(SYSTEM_PARAM)，依指定條件查詢")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @GetMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/{category}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<SystemParamModel>> getSystemParamList(@PathVariable("category") String category) {
        category = accessControl(category, String.class);
        SystemParamService service = serviceFactory.createSystemParamCollection();
        return BaseServiceResponse.of(service.findByCategory(category));
    }

    @Operation(summary = "查詢登錄按鈕開啟或關閉")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/ncaot006/query-activity-button", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> queryActivityButton() {
        return BaseServiceResponse.of(serviceFactory.createNCAOT006Service().queryActivityButton());
    }

    @Operation(summary = "NCAOT006用戶登錄狀態查詢")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/ncaot006/user-registered", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<String> queryUserRegistered(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("udiDup") @Schema(description = "誤別碼") String udiDup, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestParam("companyKind") @Schema(description = "公司類型") Integer companyKind, @RequestHeader("activityCode") @Schema(description = "活動代碼") String activityCode) {
        return BaseServiceResponse.of(serviceFactory.createNCAOT006Service().isUserRegistered(custId, udiDup, userId, companyKind, activityCode));
    }

    @Operation(summary = "NCAOT006用戶登錄設定")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/ncaot006/user-register-set", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> registerUser(@RequestHeader("custId") @Schema(description = "身份證字號") String custId, @RequestHeader("udiDup") @Schema(description = "誤別碼") String udiDup, @RequestHeader("userId") @Schema(description = "使用者代號") String userId, @RequestParam("companyKind") @Schema(description = "公司類型") Integer companyKind, @RequestHeader("activityCode") @Schema(description = "活動代碼") String activityCode, @RequestHeader("status") @Schema(description = "登錄狀態") String status) {
        return BaseServiceResponse.of(serviceFactory.createNCAOT006Service().setUserRegistered(custId, udiDup, userId, companyKind, activityCode, status));
    }

    @Operation(summary = "NCAOT006活動額滿狀態查詢")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/ncaot006/activity-full", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> queryActivityFull(@RequestHeader("activityCode") @Schema(description = "活動代碼") String activityCode) {
        return BaseServiceResponse.of(serviceFactory.createNCAOT006Service().isActivityFull(activityCode));
    }

    @Operation(summary = "NCAOT006活動額滿設定")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/ncaot006/activity-full-set", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> setActivityFull(@RequestHeader("activityCode") @Schema(description = "活動代碼") String activityCode) {
        return BaseServiceResponse.of(serviceFactory.createNCAOT006Service().setActivityFull(activityCode));
    }

    @Operation(summary = "NCAOT006活動資訊查詢")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/ncaot006/activity-info", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<List<NCAOT006Activity>> queryActivityInfo() {
        return BaseServiceResponse.of(serviceFactory.createNCAOT006Service().getCachedActivityInfo());
    }

    @Operation(summary = "NCAOT006活動資訊設定")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/ncaot006/activity-info-set", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> setActivityInfo(@RequestBody List<NCAOT006Activity> activities) {
        return BaseServiceResponse.of(serviceFactory.createNCAOT006Service().setCacheActivityInfo(activities));
    }

    @Operation(summary = "NCAOT006查詢熱門活動流程人數上限")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/ncaot006/total-access-count", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<String> queryTotalAccessCount() {
        return BaseServiceResponse.of(serviceFactory.createNCAOT006Service().queryTotalAccessCount());
    }

    @Operation(summary = "NCAOT006設定熱門活動流程人數統計")
    @PreAuthorize("hasAuthority('SCOPE_biz')")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/system-params/ncaot006/total-access-count-set", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseServiceResponse<Boolean> setTotalAccessCount(@RequestHeader String number) {
        return BaseServiceResponse.of(serviceFactory.createNCAOT006Service().setTotalAccessCount(number));
    }
}
