/*
 * ===========================================================================
 *
 * IBM Confidential
 *
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections.MapUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.gson.GsonHelper;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.base.model.ClientRequest;
import com.ibm.tw.ibmb.base.model.RqData;
import com.ibm.tw.ibmb.base.model.RsData;
import com.ibm.tw.ibmb.base.model.RsDataExtension;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.ibm.tw.ibmb.component.errorcode.ErrorInfoData;
import com.ibm.tw.ibmb.component.menu.Menu;
import com.ibm.tw.ibmb.component.menu.MenuCacheManager;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.model.B2CWebUser;
import com.ibm.tw.ibmb.task.AbstractBaseTask;
import com.ibm.tw.ibmb.type.LoginSystemType;
import com.ibm.tw.ibmb.type.MenuCategory;
import com.ibm.tw.ibmb.type.SessionKey;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.availabletask.AvailableTaskCacheManager;
import com.tfb.aibank.chl.component.availabletask.CcAvalibleTaskEntityVo;
import com.tfb.aibank.chl.component.availabletask.UidDupAvailableTaskEntityVo;
import com.tfb.aibank.chl.component.contractlog.ContractLog;
import com.tfb.aibank.chl.component.contractlog.ContractLogService;
import com.tfb.aibank.chl.component.log.AccessLogService;
import com.tfb.aibank.chl.component.log.MBAccessLog;
import com.tfb.aibank.chl.component.missionwall.MissionCompleteList;
import com.tfb.aibank.chl.component.missionwall.MissionCompleteListCacheManager;
import com.tfb.aibank.chl.component.notification.EmailAttachment;
import com.tfb.aibank.chl.component.notification.NotificationService;
import com.tfb.aibank.chl.component.notification.model.CreatePersonalNotificationRequest;
import com.tfb.aibank.chl.component.notification.model.EmailNotify;
import com.tfb.aibank.chl.component.notification.model.SMSNotify;
import com.tfb.aibank.chl.component.security.EmailOtpSecurityGuard;
import com.tfb.aibank.chl.component.security.TxSecurityGuard;
import com.tfb.aibank.chl.component.security.TxSecurityResult;
import com.tfb.aibank.chl.component.security.TxSecurityService;
import com.tfb.aibank.chl.component.security.motp.bean.MotpAuthInitData;
import com.tfb.aibank.chl.component.security.motp.bean.txseed.MotpTxSeed;
import com.tfb.aibank.chl.component.security.motp.bean.txseed.MotpTxSeedCommon;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthInitData;
import com.tfb.aibank.chl.component.security.otp.bean.txseed.OtpTxSeed;
import com.tfb.aibank.chl.component.security.otp.bean.txseed.OtpTxSeedCommon;
import com.tfb.aibank.chl.component.suspendtask.SuspendTask;
import com.tfb.aibank.chl.component.suspendtask.SuspendTaskCacheManager;
import com.tfb.aibank.chl.component.task.Task;
import com.tfb.aibank.chl.component.task.TaskCacheManager;
import com.tfb.aibank.chl.component.task.TaskPage;
import com.tfb.aibank.chl.component.task.TaskPageCacheManager;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.component.userdatacache.model.MissionDetail;
import com.tfb.aibank.chl.component.userdatacache.model.MissionMaster;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.session.resource.SessionResource;
import com.tfb.aibank.chl.session.type.CustomerType;
import com.tfb.aibank.chl.type.PreAuthType;
import com.tfb.aibank.chl.type.ResultCodeType;
import com.tfb.aibank.chl.type.TxSecurityStepType;
import com.tfb.aibank.chl.type.TxSecurityType;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.error.MbErrorCode;
import com.tfb.aibank.common.model.TxnNotifyInfo;
import com.tfb.aibank.common.model.TxnResult;
import com.tfb.aibank.common.type.MailParamType;
import com.tfb.aibank.common.type.NotificationType;
import com.tfb.aibank.common.type.TaskPermissionType;
import com.tfb.aibank.common.type.TxSourceType;
import com.tfb.aibank.common.type.TxStatusType;
import com.tfb.aibank.component.session.LocalSessionManager;

import jakarta.servlet.http.HttpSession;

// @formatter:off
/**
 * @(#)AbstractAIBankBaseTask.java
 * 
 * <p>Description:AIBank 專案共用 Channel Service Base 物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/12, Horance Chou   
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public abstract class AbstractAIBankBaseTask<R extends RqData, S extends RsData> extends AbstractBaseTask<R, S> {

    /**
     * session 中的 user 物件
     */
    private B2CWebUser<String> bc2WebUser;

    private AIBankUser aibankUser;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private MenuCacheManager menuCacheManager;

    @Autowired
    protected TaskCacheManager taskCacheManager;

    @Autowired
    protected TaskPageCacheManager taskPageCacheManager;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private TxSecurityService txSecurityService;

    @Autowired
    private ContractLogService contractLogService;

    @Autowired
    private SessionResource sessionResource;

    @Autowired
    protected AvailableTaskCacheManager availableTaskCacheManager;

    @Autowired
    private SuspendTaskCacheManager suspendTaskCacheManager;

    @Autowired
    protected UserDataCacheService userDataCacheService;

    @Autowired
    private MissionCompleteListCacheManager missionCompleteListCacheManager;

    @Autowired
    private AccessLogService accessLogService;

    private MBAccessLog accessLog = new MBAccessLog();

    private String sessionId;

    private String clientIp;

    private String sourcePort;

    private AESCipherUtils aesCipherUtils;

    private TxSecurityType securityType;

    @Autowired
    private LocalSessionManager sessionManager;

    /**
     * 是否已登入
     *
     * @return
     */
    public boolean isLoggedIn() {
        AIBankUser loginUser = null;
        try {
            loginUser = getLoginUser();
        }
        catch (Exception ex) {
            logger.error("判斷是否已登入，若抛出例外，不影響程序。");
            logger.error(ex.getMessage(), ex);
            return false;
        }
        return loginUser != null;
    }

    @Override
    public void validateSession(String taskId) {

        /** 允許重複登入 */
        boolean allowDuplicateLogin = getSystemParam(AIBankConstants.CHANNEL_NAME, "ALLOW_DUPLICATE_LOGIN", false);

        /** 允許 不檢查 */
        if (allowDuplicateLogin) {
            return;
        }

        final String ignoreTaskList = "NGNOT002,NGNOT001,NSTOT004,NSTOT002";

        if (ignoreTaskList.contains(taskId)) {
            return;
        }

        getLoginUser();
        if (this.aibankUser != null) {
            try {
                sessionResource.updateAccessTime(this.aibankUser.getCustId(), this.aibankUser.getUidDup(), this.aibankUser.getUserId(), this.aibankUser.getCompanyKindType().getCode(), sessionId);
            }
            catch (Exception ex) {
                logger.error("", ex);
                throw ex;
            }
        }
    }

    @Override
    public void validateDupUid(String taskId) throws ActionException {
        getLoginUser();
        if (this.aibankUser != null) {
            // 非存戶，不用檢查
            if (!this.aibankUser.getCustomerType().equals(CustomerType.GENERAL)) {
                return;
            }

            // ID重號客戶，限制特定功能無法使用
            if (!"0".equals(this.aibankUser.getUidDup())) {
                UidDupAvailableTaskEntityVo uidVo = availableTaskCacheManager.getUidDupAvailableTask(taskId);
                if (uidVo != null && uidVo.getAvalibleFlag() == 0) {
                    throw ExceptionUtils.getActionException(AIBankErrorCode.UIDDUP_TASK_NOT_ALLOW);
                }
            }

            CcAvalibleTaskEntityVo ccTask = availableTaskCacheManager.getCcAvailableTask(taskId);
            // A. 檢核該功能是否不提供黑名單客戶使用
            if (ccTask == null || ccTask.getAvalibleFlag() == 1) {
                return;
            }
            // (B) 不在黑名單內，進入該功能
            if (!this.aibankUser.isInAccountCreditcardCheck()) {
                return;
            }

            if (this.aibankUser.isSameBirthday()) {
                return;
            }
            throw ExceptionUtils.getActionException(MbErrorCode.MB2200);

        }
    }

    /**
     * 是否需留存HTML
     *
     * @param taskId
     * @param pageId
     * @throws ActionException
     */
    @Override
    public void savePageHTML(String taskId, String pageId) throws ActionException {
        TaskPage taskPage = taskPageCacheManager.getTaskPagesById(taskId + "_" + pageId);
        if (taskPage != null && StringUtils.isY(taskPage.getSaveFlag())) {
            getResponse().setTraceId(getTraceId()); // 在 ClientResponse 設置 交易存取記錄追蹤編號，將 traceId 傳至前端，觸發事件記錄HTML
        }
    }

    @Override
    public void updateMissionWall(String taskId, String pageId, S rsData) throws ActionException {
        // 已登入 & RsData 繼承 RsDataExtension 且 execUpdateMissionWall = true，才進一步判斷是否執行任務牆任務更新
        if (isLoggedIn() && rsData instanceof RsDataExtension && ((RsDataExtension) rsData).isExecUpdateMissionWall()) {
            try {
                List<MissionCompleteList> dataList = missionCompleteListCacheManager.getMissionCompleteListByPageId(taskId + "_" + pageId);
                if (CollectionUtils.isEmpty(dataList)) {
                    logger.debug("當前頁面沒有在任務牆清單中，不會觸發任務牆更新機制。Task : {}、Page : {}", taskId, pageId);
                }
                else {
                    for (MissionCompleteList data : dataList) {
                        String missionLevel = data.getMissionLevel();
                        String missionCode = data.getMissionCode();
                        if (logger.isDebugEnabled()) {
                            logger.debug("Task : {}、Page : {}、MissionLevel：{}、MissionCode：{}", taskId, pageId, missionLevel, missionCode);
                        }
                        List<MissionDetail> missionDetails = userDataCacheService.queryMissionDetails(getLoginUser(), missionLevel, missionCode);
                        if (!CollectionUtils.isEmpty(missionDetails)) {
                            MissionDetail missionDetail = missionDetails.get(0); // 有且僅會有一筆
                            missionDetail.setCompleteTime(new Date());
                            userDataCacheService.saveMissionDetail(missionDetail);

                            // 檢查該次完成任務是否剛好為關卡完成任務
                            Integer count = userDataCacheService.getCountByCondition(aibankUser, missionLevel);
                            logger.info("getCountByCondition：missionLevel：{}, count：{}", missionLevel, count);
                            if (count != null) {
                                MissionMaster missionMaster = userDataCacheService.queryMissionMaster(aibankUser);
                                if (missionMaster != null) {
                                    if (StringUtils.equals(missionLevel, "0") && count == 1) {
                                        missionMaster.setLevel1CompleteTime(new Date());
                                        missionMaster.setMissionLevel("1");
                                    }
                                    else if (StringUtils.notEquals(missionLevel, "0") && count >= 3) {
                                        if (StringUtils.equals(missionLevel, "1")) {
                                            missionMaster.setLevel2CompleteTime(new Date());
                                            missionMaster.setMissionLevel("2");
                                        }
                                        else if (StringUtils.equals(missionLevel, "2")) {
                                            missionMaster.setLevel3CompleteTime(new Date());
                                            missionMaster.setMissionLevel("3");
                                        }
                                    }
                                    userDataCacheService.saveMissionMaster(missionMaster);
                                }
                            }
                            break;
                        }
                    }
                }
            }
            catch (Exception ex) { // fortify issue: Poor Error Handling: Overly Broad Catch; need to catch all Exceptions
                logger.error("任務牆任務更新：若發生任何錯誤，皆不影響主流程，也不須導至錯誤頁，但須要留存相關LOG資訊以供問題釐清。");
                logger.error(ex.getMessage(), ex);
            }
        }
        else {
            logger.debug("當前頁面RsData沒有繼承 RsDataExtension 或 rsData.isExecUpdateMissionWall() == false，不會觸發任務牆更新機制。");
        }
    }

    /**
     * 取得登入者
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public AIBankUser getLoginUser() {
        if (this.bc2WebUser == null) {
            HttpSession session = getHttpServletRequest().getSession(false);
            // fortify: Redundant Null Check
            Gson gson = GsonHelper.newInstance();
            if (session != null) {
                this.sessionId = session.getId();
                this.bc2WebUser = gson.fromJson((String) session.getAttribute(SessionKey.LOGIN_USER.name()), B2CWebUser.class);
            }
            if (null == bc2WebUser) {
                if (!supportGuestOP()) {
                    logger.error("沒有支援「未登入交易」：{}", this.taskId);
                    throw new RuntimeException(ExceptionUtils.getActionException(CommonErrorCode.USER_NOT_LOGIN));
                }
            }
            else {
                this.aibankUser = gson.fromJson(bc2WebUser.getUserData(), AIBankUser.class);
            }
        }
        return this.aibankUser;
    }

    /**
     * 更新登入者
     *
     * @return
     */
    protected void setLoginUser(AIBankUser aibankUser, String[] taskIds) {
        this.aibankUser = aibankUser;
        if (this.bc2WebUser == null) {
            this.bc2WebUser = new B2CWebUser<String>();
        }
        Gson gson = GsonHelper.newInstance();
        aibankUser.addTaskIds(taskIds);
        String str = gson.toJson(aibankUser);
        this.bc2WebUser.setUserData(str);
        // 將session 標示為 ALIVE (供後踢前判斷)
        setToSession(SessionKey.ALIVE, String.valueOf(System.currentTimeMillis()));
        setToSession(SessionKey.LOGIN_USER, this.bc2WebUser);
    }

    /**
     * 更新 AIBankUser 物件
     *
     * @param aibankUser
     */
    protected void setLoginUser(AIBankUser aibankUser) {
        this.aibankUser = aibankUser;
    }

    @Override
    public void reStore() throws ActionException {
        // 確保 user 物件資料有異動的時候會寫回 session

        if (aibankUser != null) {
            Gson gson = GsonHelper.newInstance();
            String str = gson.toJson(aibankUser);
            this.bc2WebUser.setUserData(str);
            setToSession(SessionKey.LOGIN_USER, this.bc2WebUser);
        }
        else {
            if (logger.isTraceEnabled()) {
                logger.trace("embwUser is null, skip restore");
            }
        }
        super.reStore();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.ibm.tw.ibmb.task.AbstractBaseTask#processLoggingContext()
     */
    @Override
    protected void processLoggingContext() {
        super.processLoggingContext();
        this.getClientIpPort();
        getRequest().setClientIp(clientIp);
        populateMBAccessLog();
        if (isLoggedIn()) {
            MDC.put(MDCKey.custid.name(), getLoginUser().getCustId());
            MDC.put(MDCKey.dup.name(), getLoginUser().getUidDup());
            MDC.put(MDCKey.userid.name(), getLoginUser().getUserId());
        }

    }

    protected void populateMBAccessLog() {
        this.accessLog.setAccessLogKey(MDC.get(MDCKey.traceId.name()));
        this.accessLog.setAccessTime(new Date());
        ClientRequest cliReq = getRequest();
        this.accessLog.setAppVersion(cliReq.getAppVer());
        this.accessLog.setChannelId(getChannelId().getChannelId());
        this.accessLog.setClientIp(getClientIp());
        if (isLoggedIn()) {
            AIBankUser loginUser = getLoginUser();
            if (loginUser != null) {
                this.accessLog.setCompanyKey(loginUser.getCompanyKey());
                this.accessLog.setCompanyKind(loginUser.getCompanyKind());
                this.accessLog.setNameCode(loginUser.getUserData().getNameCode());
                this.accessLog.setUserKey(loginUser.getUserKey());
                this.accessLog.setMaskUserId(DataMaskUtil.maskUserId(loginUser.getUserId()));
                try {
                    this.accessLog.setEncCustId(getAESCipherUtils().encrypt(loginUser.getCustId()));
                }
                catch (CryptException e) {
                    logger.error("對身分證字號進行加密，發生錯誤。", e);
                }
                if (loginUser.getCompanyKindType().isCreditCardMember()) {
                    this.accessLog.setMassChk(Integer.toString(loginUser.getCompanyKind()));
                }
                else {
                    this.accessLog.setMassChk(loginUser.getMassCheckTrimmed());
                }
            }
        }
        this.accessLog.setDeviceId(cliReq.getDeviceIxd());
        this.accessLog.setDevicePlatform(cliReq.getPlatform());
        this.accessLog.setDevicePlatformVersion(cliReq.getVersion());
        this.accessLog.setErrorCode("0000");
        this.accessLog.setErrorDesc("Success");
        this.accessLog.setErrorSystemId(IBSystemId.SVC.getSystemId());
        this.accessLog.setLocale(cliReq.getLocale());
        this.accessLog.setModel(cliReq.getModel());
        this.accessLog.setNetwork(cliReq.getNetwork());
        this.accessLog.setResultCode(ResultCodeType.RESULT_OK.getResultCode());
        this.accessLog.setResVersion(cliReq.getRuntimeVer());
        this.accessLog.setServerName(IBUtils.getInstanceId(getEnvironment()));
        this.accessLog.setTaskId(getTaskId());
        this.accessLog.setBusType(StringUtils.substring(getTaskId(), 1, 3).toUpperCase()); // 業務別 (交易代碼的第二三碼)
        this.accessLog.setFuncType(StringUtils.substring(getTaskId(), 3, 5).toUpperCase()); // 功能類別 (交易代碼的第四五碼)
        this.accessLog.setEntryPoint(cliReq.getEntryPoint()); // 交易點擊量版位 0:所有服務 1:常用功能 2:訊息通知 3:首頁牌卡 4:其他(關聯交易)
        this.accessLog.setClientPort(this.sourcePort);
    }

    /**
     * 提供登入前沒有 Login User 情境寫入 MBAccessLog 相關欄位
     * 
     * @param custId
     */
    protected void populatePreLoginMBAccessLogByCustId(String custId) {
        // custid
        if (StringUtils.isNotBlank(custId)) {
            MDC.put(MDCKey.custid.name(), custId);
        }
    }

    /**
     * 提供登入前沒有 Login User 情境寫入 MBAccessLog 相關欄位
     * 
     * @param companyKind
     */
    protected void populatePreLoginMBAccessLogByCompany(Integer companyKind) {
        if (companyKind != null) {
            this.accessLog.setCompanyKind(companyKind.intValue());
        }
    }

    /**
     * 提供登入前沒有 Login User 情境寫入 MBAccessLog 相關欄位
     * 
     * @param userId
     */
    protected void populatePreLoginMBAccessLogByUserId(String userId) {
        // userId
        if (StringUtils.isNotBlank(userId)) {
            MDC.put(MDCKey.userid.name(), userId);
        }
    }

    /**
     * 取得 header 中的 x-sport
     *
     * @return
     */
    protected void getClientIpPort() {
        String sportInHeader = this.getHttpServletRequest().getHeader("x-sport");
        if (StringUtils.isNotBlank(sportInHeader)) {
            String[] ioPorts = StringUtils.split(sportInHeader, ":", 2);
            String clientIp = StringUtils.trimToEmptyEx(ioPorts[0]);
            this.clientIp = clientIp;
            String sourcePort = StringUtils.trimToEmptyEx(ioPorts[1]);
            MDC.put(MDCKey.clientport.name(), sourcePort);
            this.sourcePort = sourcePort;
        }
        else {
            String clientIp = StringUtils.trimToEmptyEx(this.getHttpServletRequest().getHeader("x-forwarded-for"));
            if (StringUtils.isBlank(clientIp)) {
                clientIp = getRequest().getClientIp();
            }
            this.clientIp = clientIp;
        }
        MDC.put(MDCKey.clientip.name(), clientIp);
    }

    protected void logoutUser() {
        try {
            HttpSession session = getHttpServletRequest().getSession();
            session.removeAttribute(SessionKey.LOGIN_USER.name());
            if (logger.isTraceEnabled()) {
                List<String> nameList = Arrays.asList(CollectionUtils.toArray(session.getAttributeNames(), new String[0]));
                logger.trace("current keys in session " + session.getId() + " : " + nameList);
            }
        }
        catch (ServiceException e) {
            logger.warn("error removing login user");
        }
        finally {
            this.aibankUser = null;
        }
    }

    public AIBankUser refreshLoginUser() {
        this.aibankUser = null;
        return getLoginUser();
    }

    /**
     * 取得系統參數中的 timeout 時間
     *
     * @return
     */
    @Override
    public int getTimeout() {
        Task task = this.taskCacheManager.getTaskById(getTaskId());
        return task == null ? 10 : ConvertUtils.integer2Int(task.getTaskTimeout(), 10);
    }

    @Override
    protected void validateUserState(boolean isRequireLogin, boolean requireACL, boolean hasSession, B2CWebUser<?> user) throws ActionException {
        super.validateUserState(isRequireLogin, requireACL, hasSession, user);

        // 針對 Task 進行檢核
        validateTaskStatus(getTaskId());
        validateTaskInMenus(getTaskId());
        validateSuspendTask(getTaskId());
    }

    @Override
    protected String isUserHasPermission(B2CWebUser<?> user) throws ActionException {
        return checkTaskPermission(getLoginUser(), taskId);
    }

    /**
     * 檢查使用者是否有此交易的權限
     *
     * @param user
     * @param taskId
     * @return null - 代表有權限 string - 代表該身份不可執行
     * @throws ActionException
     */
    private String checkTaskPermission(AIBankUser loginUser, String taskId) throws ActionException {
        // 非交易頁面，不需檢查交易權限
        Task task = taskCacheManager.getTaskById(taskId);
        if (task == null) {
            return null;
        }
        // 以 TASK & ROLE_TASK 檢查交易權限
        TaskPermissionType permission = loginUser.getTaskPermission(taskId, task);
        if (permission.hasPermission()) {
            return null;
        }
        else {
            logger.error("交易權限檢查異常, user key = {}, task id = {}", loginUser.getUserKey(), taskId);
        }

        if (loginUser.getCustomerType().isGeneral()) {
            // 一般會員
            return I18NUtils.getMessage("task.permission.role.general");
        }
        else {
            // 信用卡會員
            return I18NUtils.getMessage("task.permission.role.carduser");
        }
    }

    private void validateTaskStatus(String taskId) throws ActionException {
        Task task = this.taskCacheManager.getTaskById(getTaskId());
        if (task == null) {
            throwActionException(CommonErrorCode.INVALID_TASK);
        }
        if (task != null && ConvertUtils.integer2Int(task.getStatus()) != 1) {
            throwActionException(CommonErrorCode.TASK_DISABLED);
        }
    }

    /**
     * 檢查當前交易是否存在於選單內
     * 
     * @param taskId
     */
    private void validateTaskInMenus(String taskId) throws ActionException {
        List<Menu> menuList = menuCacheManager.getMenusByCategory(getMenuCategory().getCategory());
        List<Menu> filterByTaskId = menuList.stream().filter(x -> StringUtils.equals(x.getTaskId(), taskId)).collect(Collectors.toList());
        // #8383 #8355 U-22017 以 TASK_ID 反查 MENU，任一筆 MENU.IS_OPEN != 1，則抛出例外
        if (!CollectionUtils.isEmpty(filterByTaskId)) {
            if (!(filterByTaskId.stream().allMatch(x -> x.getIsOpen() == 1))) {
                throw ExceptionUtils.getActionException(CommonErrorCode.TASK_NOT_IN_THE_MENU);
            }
        }
    }

    /**
     * 檢核當前交易是否為「暫停交易」狀態，
     * 
     * @param taskId
     * @throws ActionException
     */
    private void validateSuspendTask(String taskId) throws ActionException {
        SuspendTask suspendTask = suspendTaskCacheManager.getSuspendTask(taskId);
        if (suspendTask != null && suspendTask.getSuspendData() != null && MapUtils.isNotEmpty(suspendTask.getSuspendData().getMessages())) {
            String message = suspendTask.getSuspendData().getMessages().get(getLocale().toString());
            throw new ActionException(AIBankErrorCode.SUSPEND_TASK.getSystemId(), AIBankErrorCode.SUSPEND_TASK.getErrorCode(), SeverityType.ERROR, message);
        }
    }

    @Override
    public boolean isRequireACL() {
        // default 同 requireLogin , 交易可自行 override
        return isRequireLogin();
    }

    /**
     * 各交易需實作其對應 biz code, 供底層呼叫 NANO API 驗證是否有權限進行交易
     *
     * @return
     */
    protected String getACLBizCode() {
        return "";
    }

    @Override
    public String getSecuritySPEL() {
        return "hasAuthority('SCOPE_chl')";
    }

    @Override
    public void setChannelId(LoginSystemType channelId) {
        super.setChannelId(channelId);
    }

    /**
     * 將 RsData 屬性轉換成 Map<String, String>，型別若為 JavaBean 須各交易自行處理。
     *
     * @param rsData
     * @return
     */
    protected Map<String, String> getMailParamsByRsData(S rsData) {
        Map<String, String> dataMap = new HashMap<>();
        Map<String, Object> bean2Map = GsonHelper.bean2Map(rsData);
        for (Map.Entry<String, Object> entry : bean2Map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (GsonHelper.isPrimitive(value) || value instanceof String) {
                dataMap.put(key, value.toString());
            }
        }
        return dataMap;
    }

    /**
     * 寄送交易結果Email通知
     *
     * @param subject
     *            主旨
     * @param mailTo
     *            收件人
     * @param params
     *            內容參數
     * @throws ActionException
     */
    protected void sendTxnResultMailObj(TxnNotifyInfo txnResultNotify) {
        AIBankUser loginUser = getLoginUser();
        if (loginUser == null) {
            logger.warn("不為登入的狀態");
            return;
        }
        if (StringUtils.isBlank(txnResultNotify.getMailTo())) {
            logger.warn("無收件人，停止寄送交易結果通知。");
            return;
        }
        sendTxnResultMailObj(loginUser.getCustId(), loginUser.getUidDup(), loginUser.getUserId(), loginUser.getCompanyKind(), txnResultNotify.getSubject(), txnResultNotify.getMailTo(), txnResultNotify.getParams());
    }

    /**
     * 寄送交易結果Email通知
     *
     * @param subject
     *            主旨
     * @param mailTo
     *            收件人
     * @param params
     *            內容參數
     * @throws ActionException
     */
    protected void sendTxnResultMailObj(String subject, String mailTo, Map<String, Object> params) {
        AIBankUser loginUser = getLoginUser();
        if (loginUser == null) {
            logger.warn("不為登入的狀態");
            return;
        }
        if (StringUtils.isBlank(mailTo)) {
            logger.warn("無收件人，停止寄送交易結果通知。");
            return;
        }
        sendTxnResultMailObj(loginUser.getCustId(), loginUser.getUidDup(), loginUser.getUserId(), loginUser.getCompanyKind(), subject, mailTo, params);
    }

    /**
     * 寄送交易結果Email通知
     *
     * @param subject
     *            主旨
     * @param mailTo
     *            收件人
     * @param params
     *            內容參數
     * @throws ActionException
     */
    protected void sendTxnResultMail(String subject, String mailTo, Map<String, String> originalMap) {

        Map<String, Object> objParams = originalMap.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> Objects.requireNonNullElse(e.getValue(), "")));

        sendTxnResultMailObj(subject, mailTo, objParams);
    }

    /**
     * 寄送交易結果Email通知
     *
     * @param custId
     * @param uidDup
     * @param userId
     * @param companyKind
     * @param subject
     * @param mailTo
     * @param params
     *            Map<String, String>
     */
    protected void sendTxnResultMail(String custId, String uidDup, String userId, Integer companyKind, String subject, String mailTo, Map<String, String> params) {

        Map<String, Object> objParams = params.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> Objects.requireNonNullElse(e.getValue(), "")));

        sendTxnResultMailObj(custId, uidDup, userId, companyKind, subject, mailTo, objParams);
    }

    /**
     * 寄送交易結果Email通知
     *
     * @param custId
     * @param uidDup
     * @param userId
     * @param companyKind
     * @param subject
     * @param mailTo
     * @param params
     *            Map<String, Object>
     */
    protected void sendTxnResultMailObj(String custId, String uidDup, String userId, Integer companyKind, String subject, String mailTo, Map<String, Object> params) {
        sendTxnResultMailObj(custId, uidDup, userId, companyKind, subject, mailTo, params, null);
    }

    /**
     * 寄送交易結果Email通知
     *
     * @param custId
     * @param uidDup
     * @param userId
     * @param companyKind
     * @param subject
     * @param mailTo
     * @param params
     *            Map<String, Object> params
     * @param emailAttachments
     */
    protected void sendTxnResultMailObj(String custId, String uidDup, String userId, Integer companyKind, String subject, String mailTo, Map<String, Object> params, List<EmailAttachment> emailAttachments) {

        EmailNotify emailObj = new EmailNotify();
        emailObj.setLocale(getLocale().toString());
        emailObj.setFrom(systemParamCacheManager.getValue("NOTIFY", "SENDER_EMAIL", "mbank@fubon.com")); // 寄件人
        emailObj.setTo(new ArrayList<String>(Arrays.asList(mailTo.split(";"))));

        emailObj.setUserId(userId);
        emailObj.setTxSource(TxSourceType.AI_BANK_FOR_NOTIFICATION.getCode());
        emailObj.setNotifyType(NotificationType.EMAIL.getCode());
        emailObj.setSubject(subject);
        emailObj.setTemplateName(getTaskId()); // 模板(Template)檔案名稱(.vm)，預設取交易代號
        // 設定信件參數, 預設應該有交易名稱
        if (params == null) {
            params = new HashMap<String, Object>();
        }

        if (!params.containsKey(MailParamType.TXN_ID.getCode())) {
            emailObj.setTxId(getTaskId());
        }
        else {
            emailObj.setTxId((String) params.get(MailParamType.TXN_ID.getCode()));
        }

        // 若沒有設置交易名稱，在此賦予
        if (!params.containsKey(MailParamType.TXN_NAME.getCode())) {
            params.put(MailParamType.TXN_NAME.getCode(), getTaskName());
        }

        emailObj.setTemplateParams(params);

        // 設置交易主檔鍵值
        if (params.containsKey(MailParamType.MASTER_KEY.getCode())) {
            emailObj.setMasterKey(ConvertUtils.str2Int((String) params.get(MailParamType.MASTER_KEY.getCode())));
        }
        // 設置交易明細檔鍵值
        if (params.containsKey(MailParamType.DETAIL_KEY.getCode())) {
            emailObj.setDetailKey(ConvertUtils.str2Int((String) params.get(MailParamType.DETAIL_KEY.getCode())));
        }
        // 若有指定模板代號，則取代
        if (params.containsKey(MailParamType.TEMPLATE_NAME.getCode())) {
            emailObj.setTemplateName((String) params.get(MailParamType.TEMPLATE_NAME.getCode()));
        }
        // 設置交易類型
        if (params.containsKey(MailParamType.TX_TYPE.getCode())) {
            emailObj.setTxType((String) params.get(MailParamType.TX_TYPE.getCode()));
        }

        // 附件設定
        if (!CollectionUtils.isEmpty(emailAttachments)) {
            emailObj.setAttachment(emailAttachments);
        }

        try {
            notificationService.sendTxResultMail(custId, uidDup, userId, companyKind, emailObj);
        }
        catch (ServiceException ex) {
            logger.warn(String.format("發送交易結果通知郵件失敗，不中止程序, 交易名稱:%s", getTaskName()), ex);
        }
    }

    /**
     * 寄送Email
     *
     * @param emailNotify
     */
    protected void sendMail(EmailNotify emailNotify) {
        AIBankUser loginUser = getLoginUser();
        try {
            notificationService.sendMail(loginUser, emailNotify);
        }
        catch (ServiceException ex) {
            logger.warn(String.format("發送通知郵件失敗:%s", getTaskName()), ex);
        }
    }

    /**
     * 發送簡訊通知
     *
     * @param message
     *            簡訊內容
     * @throws ActionException
     */
    protected void sendResultMsg(String message) {
        AIBankUser loginUser = getLoginUser();
        // 依登入身份，取得存留銀行的行動電話號碼
        sendResultMsg(message, null, null, loginUser.getMobileNo());
    }

    /**
     * 發送簡訊通知
     *
     * @param message
     *            簡訊內容
     * @param mobileNumber
     *            行動電話號碼
     * @throws ActionException
     */
    protected void sendResultMsg(String message, String mobileNumber) {
        sendResultMsg(message, null, null, mobileNumber);
    }

    /**
     * 發送簡訊通知
     *
     * @param message
     *            簡訊內容
     * @param masterKey
     *            交易主檔鍵值
     * @param detailKey
     *            交易明細檔鍵值
     * @throws ActionException
     */
    protected void sendResultMsg(String message, Integer masterKey, Integer detailKey) throws ActionException {

        AIBankUser loginUser = getLoginUser();

        // 依登入身份，取得存留銀行的行動電話號碼
        sendResultMsg(message, masterKey, detailKey, loginUser.getMobileNo());
    }

    /**
     * 發送簡訊通知
     *
     * @param message
     *            簡訊內容
     * @param masterKey
     *            交易主檔鍵值
     * @param detailKey
     *            交易明細檔鍵值
     * @param mobileNumber
     *            行動電話號碼
     * @throws ActionException
     */
    protected void sendResultMsg(String message, Integer masterKey, Integer detailKey, String mobileNumber) {
        AIBankUser loginUser = getLoginUser();
        sendResultMsg(loginUser.getCustId(), loginUser.getUidDup(), loginUser.getUserId(), loginUser.getCompanyKind(), message, masterKey, detailKey, mobileNumber);
    }

    protected void sendResultMsg(String custId, String uidDup, String userId, Integer companyKind, String message, Integer masterKey, Integer detailKey, String mobileNumber) {
        // 防呆
        if (StringUtils.isBlank(mobileNumber)) {
            logger.warn("客戶未留存行動電話，發送簡訊通作動作中止。交易名稱：{}，訊息內容：{}", getTaskName(), message);
            return;
        }

        SMSNotify sms = notificationService.buildSMSNotify(userId, masterKey, detailKey, message, mobileNumber, getTaskId());

        try {
            notificationService.sendSMS(custId, uidDup, userId, companyKind, sms);
        }
        catch (ServiceException ex) {
            logger.warn(String.format("發送簡訊通知失敗，不中止程序:%s", getTaskName()), ex);
        }
    }

    /**
     * 建立個人化通知訊息
     *
     * @param request
     * @throws ActionException
     */
    protected void sendPushNotification(CreatePersonalNotificationRequest request) {
        try {
            AIBankUser loginUser = getLoginUser();
            request.setCustId(loginUser.getCustId());
            request.setUserId(loginUser.getUserId());
            request.setUidDup(loginUser.getUidDup());
            request.setCompanyKind(loginUser.getCompanyKind());
            notificationService.sendPushNotification4Personal(request);
        }
        catch (ServiceException ex) {
            logger.warn(String.format("發送個人化通知失敗，不中止程序:%s", getTaskName()), ex);
        }
    }

    /**
     * 檢查交易安控
     *
     * @throws ActionException
     */
    @Override
    protected final void checkTxSecurityType() throws ActionException {

        // 共用元件TASK不需處理
        boolean skipCheck = StringUtils.startsWith(getTaskId(), "NST");
        if (skipCheck) {
            return;
        }

        // 已登入交易010頁執行
        if (isRequireLogin() && StringUtils.equals("010", getMethod())) {
            TxSecurityGuard lastTxSecurityGuard = getFromSession(SessionKey.TX_SECURITY_GUARD, TxSecurityGuard.class);

            // 檢查交易安控
            TxSecurityGuard txSecurityGuard = new TxSecurityGuard();
            txSecurityGuard.setTaskId(getTaskId());
            txSecurityGuard.setDeviceIxd(getDeviceIxd());
            txSecurityService.checkTxSecurityType(txSecurityGuard, getLoginUser(), false);
            txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.INIT_OK);

            String taskId = getTaskId();

            if (lastTxSecurityGuard != null && PreAuthType.AUTH_SUCC_2.equals(lastTxSecurityGuard.getPreAuth()) && //
                    StringUtils.equals(lastTxSecurityGuard.getPreAuthTaskId(), taskId)) {
                txSecurityGuard.setPreAuthTaskId(lastTxSecurityGuard.getPreAuthTaskId());
                txSecurityGuard.setPreAuth(PreAuthType.AUTH_SUCC_2);
                txSecurityGuard.setTxSecurityType(lastTxSecurityGuard.getTxSecurityType());
            }
            // 暫存
            setToSession(SessionKey.TX_SECURITY_GUARD, txSecurityGuard);

            logger.debug("### TxSecurityGuard checkTxSecurityType ### {}", txSecurityGuard.getInfo());
        }

    }

    /**
     * 啟動交易安控驗證
     *
     * @throws ActionException
     */
    protected final void initTxSecurity() throws ActionException {
        initTxSecurity(null, null, false);
    }

    protected final void initTxSecurity(String taskName) throws ActionException {
        initTxSecurity(taskName, null, false);
    }

    protected final void initTxSecurity(String taskName, String taskId, boolean preAuth) throws ActionException {

        TxSecurityGuard txSecurityGuard = getFromSession(SessionKey.TX_SECURITY_GUARD, TxSecurityGuard.class);
        logger.info("＃initTxSecurity＃, txSecurityGuard is null: {}", null == txSecurityGuard);
        if (null != txSecurityGuard) {
            logger.info("＃initTxSecurity＃, txSecurityGuard ==>: {}", IBUtils.attribute2Str(txSecurityGuard));
        }

        if (null == txSecurityGuard || txSecurityGuard.getDeviceIxd() == null) {
            logger.info("＃initTxSecurity＃, txSecurityGuard is null");
            // 欲啟動安控卻無暫存資料時，預設檢查交易安控
            // For登入使用安控流程
            txSecurityGuard = new TxSecurityGuard();
            txSecurityGuard.setTaskId(getTaskId());
            txSecurityGuard.setDeviceIxd(getDeviceIxd());
            txSecurityService.checkTxSecurityType(txSecurityGuard, getLoginUser(), true);
            txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.INIT_OK);
        }

        String currentTaskId = getTaskId();
        logger.info("＃initTxSecurity＃, preAuth:{}, taskId:{}", preAuth, taskId);

        if (preAuth) {
            // NSTOT008 Called
            // 預先驗證交易代號
            txSecurityGuard.setPreAuthTaskId(taskId);
            // 預先驗證
            txSecurityGuard.setPreAuth(PreAuthType.PRE_AUTH_1);
            // 使用交易的安控設定 (NSTOT008 Only)
            txSecurityGuard.setTaskId(getTaskId());
            txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.INIT_OK);
            txSecurityService.checkTxSecurityType(txSecurityGuard, getLoginUser(), false);
        }
        else {
            if (PreAuthType.AUTH_SUCC_2.equals(txSecurityGuard.getPreAuth()) && //
                    StringUtils.isNoneBlank(currentTaskId) && //
                    StringUtils.equals(currentTaskId, txSecurityGuard.getPreAuthTaskId())) {
                txSecurityGuard.setTaskId(currentTaskId);
                txSecurityGuard.setPreAuth(PreAuthType.SEQ_TXN_INIT_3);
                // 使用交易的安控設定
                txSecurityService.checkTxSecurityType(txSecurityGuard, getLoginUser(), false);
                setToSession(SessionKey.TX_SECURITY_GUARD, txSecurityGuard);
                return;
            }
            txSecurityGuard.setPreAuth(PreAuthType.NOT_PRE_AUTH_0);
            txSecurityGuard.setPreAuthTaskId(null);
        }

        logger.info("＃initTxSecurity＃, txSecurityGuard:{}", IBUtils.attribute2Str(txSecurityGuard));

        // TASK設定延遲初始化安控時，在此步驟檢查交易安控機制
        if (txSecurityGuard.isLazyInit() && txSecurityGuard.getTxSecurityType().isUnknown()) {
            txSecurityService.checkTxSecurityType(txSecurityGuard, getLoginUser(), true);
            txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.INIT_OK);
        }

        if (txSecurityGuard.getTxSecurityType().isOtp()) {

            OtpTxSeed txSeed = getOtpAuthInitData();
            if (null == txSeed) {
                logger.error("交易未提供安控資料");
                throw ExceptionUtils.getActionException(AIBankErrorCode.TX_SECURITY_DATA_CHECK_ERROR);
            }

            // 設定簡訊OTP驗證初始資料
            OtpAuthInitData initData = new OtpAuthInitData();
            initData.setTaskId(getTaskId());
            initData.setTaskName(StringUtils.isNotBlank(taskName) ? taskName : getTaskName());
            initData.setOtpMobile(txSecurityGuard.getOtpMobile());
            initData.setSendMessage(txSeed.getSendMessage());
            initData.setTxFactors(txSeed.getTxFactors());
            initData.setLocale(getLocale().toString());
            initData.setTxnSendFlag(txSeed.isTxnSendFlag());
            initData.setTxCode(txSeed.getTxCode());
            initData.setExpireTime(txSeed.getExpireTime());
            txSecurityGuard.setOtpAuthInitData(initData);
            txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.OTP_OPTION_READY);
        }
        else if (txSecurityGuard.getTxSecurityType().isMotp()) {

            MotpTxSeed txSeed = getMotpAuthInitData(taskName);
            if (null == txSeed) {
                logger.error("交易未提供安控資料");
                throw ExceptionUtils.getActionException(AIBankErrorCode.TX_SECURITY_DATA_CHECK_ERROR);
            }

            // 設定MOTP驗證初始資料
            MotpAuthInitData initData = new MotpAuthInitData();
            initData.setDeviceIxd(getDeviceIxd());
            initData.setTaskIxd(txSeed.getTaskId());
            initData.setTitle(txSeed.getTitle());
            initData.setMessage(txSeed.getMessage());
            initData.setCustInfo(escapeBracket(JsonUtils.getJson(txSeed.getCustInfo())));
            initData.setTxFactor(txSeed.getTxFactor());
            initData.setTxSeedType(txSeed.getMotpTxSeedType().name());
            initData.setClientIp(getClientIp());
            txSecurityGuard.setMotpAuthInitData(initData);
            txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.MOTP_OPTION_READY);
        }

        // 暫存
        setToSession(SessionKey.TX_SECURITY_GUARD, txSecurityGuard);
        logger.debug("### TxSecurityGuard initTxSecurity ### {}", txSecurityGuard.getInfo());
    }

    /**
     * 避開大括號
     *
     * @return
     */
    private String escapeBracket(String str) {
        int s = str.indexOf("{");
        int e = str.lastIndexOf("}");
        return str.substring(s + 1, e);

    }

    /**
     * 啟動Email-OTP交易安控驗證
     *
     * @throws ActionException
     */
    protected final void initEmailOtpTxSecurity(String email) throws ActionException {

        EmailOtpSecurityGuard cache = new EmailOtpSecurityGuard();
        cache.setIsHasOriginEmail(StringUtils.isBlank(email));
        cache.setOriginEmail(email);
        setToSession(SessionKey.EMAIL_OTP, cache);
    }

    /**
     * 不執行交易安控，只有交易設定預設不檢查時有效
     *
     * @throws ActionException
     */
    protected final void cancelTxSecurity() throws ActionException {

        TxSecurityGuard txSecurityGuard = getFromSession(SessionKey.TX_SECURITY_GUARD, TxSecurityGuard.class);
        if (null == txSecurityGuard) {
            logger.warn("Session資料遺失or交易未呼叫初始化");
        }
        else if (txSecurityGuard.isLazyInit()) {
            txSecurityGuard = new TxSecurityGuard();
            txSecurityGuard.setTaskId(getTaskId());
            txSecurityGuard.setDeviceIxd(getDeviceIxd());
            txSecurityService.checkTxSecurityType(txSecurityGuard, getLoginUser(), false);
            txSecurityGuard.setTxSecurityType(TxSecurityType.NONE);
            txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.INIT_OK);
        }

        // 暫存
        setToSession(SessionKey.TX_SECURITY_GUARD, txSecurityGuard);
    }

    /**
     * 重置交易安控，交易需要重復使用交易安控時呼叫
     *
     * <pre>
     * NCCQU001 信用卡總覽
     * </pre>
     *
     * @throws ActionException
     */
    protected final void resetTxSecurity() throws ActionException {

        TxSecurityGuard txSecurityGuard = getFromSession(SessionKey.TX_SECURITY_GUARD, TxSecurityGuard.class);
        if (null == txSecurityGuard) {
            logger.error("Session資料遺失or交易未呼叫初始化");
            throw ExceptionUtils.getActionException(AIBankErrorCode.TX_SECURITY_DATA_CHECK_ERROR);
        }

        txSecurityGuard.setTaskId(getTaskId());
        txSecurityGuard.setDeviceIxd(getDeviceIxd());
        txSecurityService.checkTxSecurityType(txSecurityGuard, getLoginUser(), true);
        txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.INIT_OK);
        // 暫存
        setToSession(SessionKey.TX_SECURITY_GUARD, txSecurityGuard);
        logger.debug("### TxSecurityGuard resetTxSecurity ### {}", txSecurityGuard.getInfo());
    }

    /**
     * 立即檢視是否本交易是否可正常使用安控
     *
     * @throws ActionException
     */
    protected final void checkTxSecurity(String taskId) throws ActionException {

        TxSecurityGuard txSecurityGuard = getFromSession(SessionKey.TX_SECURITY_GUARD, TxSecurityGuard.class);
        if (null == txSecurityGuard) {
            logger.error("Session資料遺失or交易未呼叫初始化");
            throw ExceptionUtils.getActionException(AIBankErrorCode.TX_SECURITY_DATA_CHECK_ERROR);
        }

        txSecurityGuard.setTaskId(taskId);
        txSecurityGuard.setDeviceIxd(getDeviceIxd());
        txSecurityService.checkTxSecurityType(txSecurityGuard, getLoginUser(), true);

    }

    /**
     * 交易提供OTP安控資料
     *
     * @return
     */
    protected OtpTxSeed getOtpAuthInitData() {
        return new OtpTxSeedCommon();
    }

    /**
     * 交易提供MOTP安控資料
     *
     * @return
     */
    protected MotpTxSeed getMotpAuthInitData(String taskName) {
        return new MotpTxSeedCommon(getTaskId(), StringUtils.isNotBlank(taskName) ? taskName : getTaskName());
    }

    /**
     * 交易提供MOTP安控資料
     *
     * @return
     */
    protected MotpTxSeed getMotpAuthInitData() {
        return new MotpTxSeedCommon(getTaskId(), getTaskName());
    }

    /**
     * 交易前確認安控驗證狀態
     *
     * @return
     * @throws ActionException
     */
    protected final TxSecurityResult doTxConfirmCheck() throws ActionException {

        TxSecurityGuard txSecurityGuard = getFromSession(SessionKey.TX_SECURITY_GUARD, TxSecurityGuard.class);
        setToSession(SessionKey.TX_SECURITY_GUARD, "{}");

        if (null == txSecurityGuard) {
            logger.error("Session資料遺失or交易未呼叫初始化");
            throw ExceptionUtils.getActionException(AIBankErrorCode.TX_SECURITY_DATA_CHECK_ERROR);
        }

        // 延續驗證
        if (PreAuthType.SEQ_AUTH_FINISH_5.equals(txSecurityGuard.getPreAuth()) && //
                txSecurityGuard.getTxSecurityType().isOtp() && //
                txSecurityGuard.getTxSecurityStepType().isOTPAuthSuccess()) {

            return new TxSecurityResult();
        }

        this.securityType = txSecurityGuard.getTxSecurityType();

        // 記錄 Access Log 的 securityType 欄位
        // this.accessLog.setSecurityType(txSecurityGuard.getTxSecurityType().getType());

        logger.debug("### TxSecurityGuard doTxConfirmCheck ### {}", txSecurityGuard.getInfo());
        if (txSecurityGuard.getTxSecurityType().isOtp() && !txSecurityGuard.getTxSecurityStepType().isOTPAuthSuccess()) {
            logger.error("OTP安控驗證失敗");
            throw ExceptionUtils.getActionException(AIBankErrorCode.TX_SECURITY_DATA_CHECK_ERROR);
        }
        else if (txSecurityGuard.getTxSecurityType().isMotp() && !txSecurityGuard.getTxSecurityStepType().isMOTPAuthSuccess()) {
            logger.error("MOTP安控驗證失敗");
            throw ExceptionUtils.getActionException(AIBankErrorCode.TX_SECURITY_DATA_CHECK_ERROR);
        }

        // 回傳交易安控驗證結果，提供各交易取得安控驗證中關聯的Table鍵值
        TxSecurityResult result = new TxSecurityResult();
        result.setTxSecurityType(txSecurityGuard.getTxSecurityType());
        if (txSecurityGuard.getTxSecurityType().isOtp()) {
            result.setOtpKey(txSecurityGuard.getOtpAuthKeepData().getModel().getOtpKey());
        }
        else if (txSecurityGuard.getTxSecurityType().isMotp()) {
            result.setMotpTransKey(txSecurityGuard.getMotpAuthKeepData().getCreatePushOtpResponse().getMotpTransDataKey());
        }
        return result;
    }

    /**
     * 取得安控驗證 SecurityType
     *
     * @return
     */
    protected final String getSecurityType() {
        if (this.securityType == null) {
            return null;
        }

        TxSecurityType tmpSecurityType = this.securityType;
        this.securityType = null;

        switch (tmpSecurityType) {
        case NONE:
            return null;
        case OTP:
            return "0";
        case MOTP:
            return "4";
        default:
            return null;
        }
    }

    /**
     * 交易前確認安控驗證狀態
     *
     * @return
     * @throws ActionException
     */
    protected final void doTxConfirmCheckEmail() throws ActionException {
        EmailOtpSecurityGuard cache = getFromSession(SessionKey.EMAIL_OTP, EmailOtpSecurityGuard.class);
        setToSession(SessionKey.EMAIL_OTP, "{}");

        if (null == cache) {
            logger.error("Session資料遺失or交易未呼叫初始化");
            throw ExceptionUtils.getActionException(AIBankErrorCode.TX_SECURITY_DATA_CHECK_ERROR);
        }
        logger.debug("### TxSecurityGuard doTxConfirmCheck ### {}", cache.getTxSecurityStepType());
        if (!cache.getTxSecurityStepType().isOTPAuthSuccess()) {
            logger.error("OTP安控驗證失敗");
            throw ExceptionUtils.getActionException(AIBankErrorCode.TX_SECURITY_DATA_CHECK_ERROR);
        }

    }

    /**
     * 新增一筆「審閱條款紀錄」
     *
     * @param remarkKey
     *            文案鍵值
     * @param pageId
     *            頁面代碼
     */
    public void saveContractLog(String remarkKey, String pageId) {
        saveContractLog(remarkKey, pageId, null);
    }

    /**
     * 新增一筆「審閱條款紀錄」
     *
     * @param remarkKey
     *            文案鍵值
     * @param pageId
     *            頁面代碼
     * @param memo
     *            額外資訊
     */
    public void saveContractLog(String remarkKey, String pageId, String memo) {
        contractLogService.saveContractLog(createContractLog(remarkKey, pageId, memo), aibankUser);
    }

    /**
     * 建立一個新的「審閱條款紀錄」物件
     *
     * @param remarkKey
     *            文案鍵值
     * @param pageId
     *            頁面代碼
     * @param memo
     *            額外資訊
     * @return
     */
    private ContractLog createContractLog(String remarkKey, String pageId, String memo) {
        ContractLog contractLog = new ContractLog();
        contractLog.setRemarkKey(remarkKey);
        contractLog.setPageId(getTaskId() + "_" + pageId);
        contractLog.setActionType(1);
        contractLog.setClientIp(getClientIp());
        contractLog.setSessionId(getSessionId());
        contractLog.setCreateTime(new Date());
        contractLog.setMemo(memo);
        return contractLog;
    }

    /**
     * 建立一個(預設)交易結果資訊物件
     *
     * @param txStatus
     *            交易狀態，0:交易成功、1:交易失敗、2:預約交易成功、3:預約交易失敗、4:等候回應時間過長
     * @param hostTxTime
     *            交易時間
     * @param systemId
     * @param errorCode
     * @param errorDesc
     * @return
     */
    public TxnResult createTxnResult(String txStatus, Date hostTxTime, String systemId, String errorCode, String errorDesc) {
        // 交易狀態，頁面只判斷 0｜1
        if (StringUtils.equals(txStatus, TxStatusType.RESERVED.getCode())) {
            txStatus = TxStatusType.SUCCESS.getCode();
        }
        else if (StringUtils.equals(txStatus, TxStatusType.CANCELED.getCode())) {
            txStatus = TxStatusType.FAIL.getCode();
        }

        // 交易結果
        String txStatusDesc = I18NUtils.getMessage("common.txn_result.tx_status.desc", ConvertUtils.str2Int(txStatus));
        // 錯誤訊息
        String txErrorDesc = StringUtils.EMPTY;
        if (StringUtils.notEquals(TxStatusType.SUCCESS.getCode(), txStatus)) {
            // 從 DB.AI_ERROR_CODE 讀取錯誤訊息
            errorDesc = StringUtils.defaultIfBlank(getErrorDesc(systemId, errorCode), errorDesc);
            // 格式：{0} (錯誤代碼 {1}{2})
            txErrorDesc = I18NUtils.getMessage("common.txn_result.tx_error.desc", errorDesc, StringUtils.isEmpty(systemId) ? StringUtils.EMPTY : systemId, errorCode);
        }
        TxnResult txnResult = new TxnResult(txStatus, txStatusDesc, hostTxTime, txErrorDesc);

        // 交易狀態非成功，從 DB.AI_ERROR_INFO 查詢，對應錯誤代碼是否有轉導按鈕
        if (StringUtils.notEquals(TxStatusType.SUCCESS.getCode(), txStatus)) {
            ErrorInfoData errorInfoData = IBUtils.getErrorInfoData(errorCodeCacheManager, systemId, errorCode, getLocale(), getFromPage(), getChannelId().getChannelId());
            if (errorInfoData != null) {
                txnResult.setDirectButtonName1(errorInfoData.getDirectButtonName1());
                txnResult.setDirectTaskId1(errorInfoData.getDirectTaskId1());
                txnResult.setDirectButtonName2(errorInfoData.getDirectButtonName2());
                txnResult.setDirectTaskId2(errorInfoData.getDirectTaskId2());
            }
        }
        return txnResult;
    }

    @Override
    protected void handleFinally() {
        this.asyncLogProcessor.doB2CAccessLog(GsonHelper.newInstance().toJson(accessLog));

        // 寫DB參數有定義且要求寫入時，調用寫DB API 20250307 David Huang
        String write2DB = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "WRITE_WBPLUS_ACCESSLOG_IN_DB");
        if (StringUtils.isNotBlank(write2DB) && StringUtils.equalsIgnoreCase(write2DB, "Y")) {
            this.accessLogService.addWBPlusAccessLog(accessLog);
        }
    }

    @Override
    protected ActionException handleException(Exception e) {
        ActionException aex = super.handleException(e);
        this.accessLog.setErrorCode(aex.getErrorCode());
        this.accessLog.setErrorDesc(IBUtils.getDescWithParams(aex.getErrorDesc(), (Object[]) aex.getParams()));
        this.accessLog.setErrorSystemId(aex.getSystemId());
        this.accessLog.setResultCode((aex.isTimeout() ? ResultCodeType.RESULT_UNKNOWN : ResultCodeType.RESULT_FAILED).getResultCode());
        return aex;
    }

    /**
     * 取得交易名稱
     *
     * @return
     */
    protected String getTaskName() {
        return menuCacheManager.getTaskName(getTaskId(), getLocale().toString());
    }

    /**
     * 取得交易存取記錄追蹤編號
     *
     * @return
     */
    protected String getTraceId() {
        return MDC.get(MDCKey.traceId.name());
    }

    public boolean getSystemParam(String category, String kxyName, boolean defaultVal) {
        String value = systemParamCacheManager.getValue(category, kxyName, String.valueOf(defaultVal));

        if (StringUtils.equalsIgnoreCase("TRUE", value)) {
            return true;
        }
        if (StringUtils.equalsIgnoreCase("YES", value)) {
            return true;
        }
        return StringUtils.equalsIgnoreCase("1", value);
    }

    @Override
    public String getClientIp() {
        return this.clientIp;
    }

    public String getSourcePort() {
        return this.sourcePort;
    }

    /**
     * 取得日誌記錄物件
     *
     * @return
     */
    public MBAccessLog getAccessLog() {
        return accessLog;
    }

    public AESCipherUtils getAESCipherUtils() {
        if (aesCipherUtils == null) {
            SecretKeyProvider<?> provider = SecretKeyProviderFactory.getInstance().getProvider(AIBankConstants.CRYPTO_SECRET_KEY_PROVIDER_DEFAULT);
            aesCipherUtils = new AESCipherUtils(provider);
        }
        return aesCipherUtils;
    }

    /**
     * 取得交易頁面名稱檔
     */
    protected TaskPage getTaskPage(String pageId) {
        return taskPageCacheManager.getTaskPagesById(pageId);
    }

    /**
     * 取得交易頁面名稱
     */
    protected String getTaskPageName() {
        return taskPageCacheManager.getTaskPageName(getPageId());
    }

    /**
     * 取得交易頁面名稱
     */
    protected String getTaskPageName(String pageId) {
        return taskPageCacheManager.getTaskPageName(pageId);
    }

    /**
     * 執行「通知作業」，
     *
     * @throws ActionException
     */
    protected void doTxnResultNotify(TxnNotifyInfo emailInfo, TxnNotifyInfo smsInfo) throws ActionException {
        if (emailInfo == null) {
            logger.error("doTxnResultNotify() emailInfo 不得為空值。");
            throw new ActionException("emailInfo 不得為空值。", CommonErrorCode.UNKNOWN_EXCEPTION);
        }
        if (smsInfo == null) {
            logger.error("doTxnResultNotify() smsInfo 不得為空值。");
            throw new ActionException("smsInfo 不得為空值。", CommonErrorCode.UNKNOWN_EXCEPTION);
        }

        AIBankUser loginUser = getLoginUser();
        String mailTo = StringUtils.isNotBlank(emailInfo.getMailTo()) ? emailInfo.getMailTo() : loginUser.getEmail();
        if (StringUtils.isNotBlank(mailTo)) {
            this.sendTxnResultMailObj(emailInfo.getSubject(), mailTo, emailInfo.getParams());
        }
        else {
            // 若無指定行動電話，則取登入資訊記錄的行動電話
            String mobileNo = StringUtils.isNotBlank(smsInfo.getMobileNo()) ? smsInfo.getMobileNo() : loginUser.getMobileNo();
            if (StringUtils.isNotBlank(mobileNo)) {
                this.sendResultMsg(smsInfo.getMessage(), smsInfo.getMasterKey(), smsInfo.getDetailKey(), mobileNo);
            }
            else {
                logger.info("doTxnResultNotify() 客戶沒有留存電子郵件信箱和行動電話資訊。");
            }
        }
    }

    /**
     * 登入成功後變更 Session
     * 
     * @return
     */
    protected final String changeToNewSession() {
        // 取得目前 session
        HttpSession session = getHttpServletRequest().getSession(false);
        // 在這裡要變更 session
        Map<String, Object> oldSessionObjects = new HashMap<>();
        Collections.list(session.getAttributeNames()).forEach(key -> {
            oldSessionObjects.put(key, session.getAttribute(key));
            session.removeAttribute(key);
        });
        logger.debug("old session id: " + session.getId());
        // 清除舊 session
        session.invalidate();
        logger.debug("current session object map: " + oldSessionObjects);
        HttpSession newSession = getHttpServletRequest().getSession(true);
        newSession.setMaxInactiveInterval(3600);
        String newSID = newSession.getId();
        logger.debug("new session id after login: " + newSID);

        this.setSessionId(newSID);
        oldSessionObjects.forEach((k, v) -> {
            newSession.setAttribute(k, v);
        });

        sessionManager.addSessionId(newSID);
        getRequest().setSessionId(newSID);
        return newSID;

    }

    /**
     * 登入流程共用 invalid session
     */
    protected void invalidSessionBeforeLogin() {
        // fortify: Session Fixation, invalid session before login
        HttpSession session = this.getHttpServletRequest().getSession(false);
        String currentSessionId = session.getId();
        if (session != null) {
            session.invalidate();
            logger.debug("fortify: invalid session {} before login", currentSessionId);
        }
        session = this.getHttpServletRequest().getSession(true);
        String newSessionId = session.getId();
        this.setSessionId(newSessionId);
        logger.debug("fortify: create new session after login: {}", this.getSessionId());
        moveCache(currentSessionId, newSessionId);
    }

    /**
     * 依登入狀態、語系，讀取 MENU Category
     * 
     * @return
     */
    protected MenuCategory getMenuCategory() {
        AIBankUser loginUser = getLoginUser();
        Locale userLocale = getLocale();
        // 未登入選單
        MenuCategory menuCategory = Locale.US.equals(userLocale) ? MenuCategory.AIDBU_EN : MenuCategory.AIDBU;
        // 登入後選單
        if (null != loginUser) { // 表示已登入
            boolean isGeneral = loginUser.getCustomerType().isGeneral();
            menuCategory = isGeneral ? MenuCategory.AIDBU : MenuCategory.AICCU;
            if (Locale.US.equals(userLocale)) {
                menuCategory = isGeneral ? MenuCategory.AIDBU_EN : MenuCategory.AICCU_EN;
            }
        }
        return menuCategory;
    }

}
