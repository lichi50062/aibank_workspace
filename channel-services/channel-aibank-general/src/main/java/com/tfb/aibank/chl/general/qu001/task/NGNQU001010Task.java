package com.tfb.aibank.chl.general.qu001.task;

import java.util.Date;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.devicebinding.DeviceBindingService;
import com.tfb.aibank.chl.component.devicebinding.DeviceBindingUserResource;
import com.tfb.aibank.chl.component.devicebinding.model.CheckUserDeviceStatusCondition;
import com.tfb.aibank.chl.component.devicebinding.model.CheckUserDeviceStatusResult;
import com.tfb.aibank.chl.component.devicebinding.model.MbDeviceInfo;
import com.tfb.aibank.chl.component.devicebinding.model.RetrieveDeviceBindingResponse;
import com.tfb.aibank.chl.component.devicebinding.type.UserDeviceBindStatusType;
import com.tfb.aibank.chl.general.qu001.model.DataInput;
import com.tfb.aibank.chl.general.qu001.model.DataOutput;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001010Rq;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001010Rs;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001Cache;
import com.tfb.aibank.chl.general.qu001.model.UnreachableCustomerInfo;
import com.tfb.aibank.chl.general.qu001.service.NGNQU001Service;
import com.tfb.aibank.chl.general.resource.dto.NavigateSetting;
import com.tfb.aibank.chl.general.resource.dto.QuickSearchResponse;
import com.tfb.aibank.chl.general.type.NavigationType;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.session.vo.CompanyVo;
import com.tfb.aibank.chl.session.vo.UserVo;
import com.tfb.aibank.common.code.AIBankConstants;

// @formatter:off
/**
 * @(#)NGNQU001010Task.java
 *  
 * 首頁 僅處理進入畫面時的主要資訊 取得資訊： 1) 已登入/未登入 2) 未登入時取得是否有設定免登速查 3) 取得未讀訊息數量 4) 取得招呼語 5) 有登入時取得使用者暱稱
 *
 * <p>Description: 首頁</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>[新增說明]</li>
 * </ol>
 */
//@formatter:on
@Component
@Scope("prototype")
public class NGNQU001010Task extends AbstractAIBankBaseTask<NGNQU001010Rq, NGNQU001010Rs> {

    DataInput input = new DataInput();
    DataOutput output = new DataOutput();
    NGNQU001Cache cacheData = new NGNQU001Cache();
    @Autowired
    @Qualifier("NGNQU001Service")
    private NGNQU001Service service;
    @Autowired
    private DeviceBindingService deviceBindingService;
    @Autowired
    private DeviceBindingUserResource userResource;
    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    public final static String DEFAULT_CACHE_KEY = "default";

    @Override
    public void validate(NGNQU001010Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NGNQU001010Rq rqData, NGNQU001010Rs rsData) throws ActionException {

        input.setLocale(getLocale());

        // (未登入時)取得是否有設定免登速查
        checkCanQueryWithoutLogin();

        // 檢查裝置綁定狀態，決定cacheKey值
        checkBindingStatus(rsData);

        // 取得未讀訊息數量
        queryUnreadMessageCount();
        // 取得招呼語
        getRespectInfo();
        // 取得暱稱
        getUserNickname(getLoginUser());
        // 取首頁頂端3張牌卡設定
        getHomepageCardsToDisplay();
        this.service.getAllHomepageCards(output, null != getLoginUser() ? true : false);
        // 取得「新功能介紹」table資料
        service.getNewFunctionsIntro(getLoginUser(), input, output);

        if (isLoggedIn()) {
            UnreachableCustomerInfo unreachableCustomerInfo = null;
            try {
                unreachableCustomerInfo = getUnreachableCustomerInfo();
            }
            catch (Exception e) {
                logger.error("失聯戶取得失敗", e.getMessage());
            }
            rsData.setUnreachableCustomerInfo(unreachableCustomerInfo);
        }

        // 無障礙處理導頁流程
        getNavigateSetting(rqData, rsData);

        setRsData(rsData);
        setTaskCache();
    }

    private void getNavigateSetting(NGNQU001010Rq rqData, NGNQU001010Rs rsData) {
        NavigateSetting setting = rqData.getNavigateSetting();
        if (setting == null) {
            return;
        }
        NavigationType navigationType = NavigationType.find(setting.getType());
        if (NavigationType.UNKNOWN == navigationType) {
            return;
        }
        logger.debug("navigate setting: {}", setting);
        // SSO 需為 已登入
        switch (navigationType) {
        case LINK_SSO:
            rsData.setNavigateSetting(this.getLoginUser() != null ? setting : null);
            break;
        case LINK:
            rsData.setNavigateSetting(setting);
            break;
        default:
            break;
        }
    }

    private UnreachableCustomerInfo getUnreachableCustomerInfo() {
        AIBankUser aiBankUser = getLoginUser();
        UnreachableCustomerInfo unreachableCustomerInfo = new UnreachableCustomerInfo();
        if (aiBankUser.getCustomerType().isCardMember()) {
            UnreachableCustomerInfo unreachableCustomerInfoCard = new UnreachableCustomerInfo();
            unreachableCustomerInfoCard.setUnreachable(false);
            return unreachableCustomerInfoCard;
        }
        String emailUnc = aiBankUser.getLoginMsgRs().getEmailUnc();
        String addrUnc = aiBankUser.getLoginMsgRs().getAddrUnc();
        String mobile = aiBankUser.getMobileNo();
        String email = aiBankUser.getEmail();
        String chatBotUrl = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "CHATBOT_URL");
        String emailLink = "/aibank/nps/ag001/010";
        Date now = new Date();
        String nowDate = DateUtils.getISODateTimeStr(now);
        // Email和通訊地址失聯
        if ("Y".equals(emailUnc) && "Y".equals(addrUnc)) {
            unreachableCustomerInfo.setUnreachable(true);
            unreachableCustomerInfo.setTitle(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.title"));
            unreachableCustomerInfo.setMessage(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.message.C"));
            unreachableCustomerInfo.setLeftButtonText(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.left.button"));
            unreachableCustomerInfo.setRightButtonText(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.right.button.chatbot"));
            unreachableCustomerInfo.setLink(chatBotUrl);
            unreachableCustomerInfo.setNowDate(nowDate);
            return unreachableCustomerInfo;
        }
        // 只有Email失聯
        else if ("Y".equals(emailUnc)) {
            unreachableCustomerInfo.setUnreachable(true);
            unreachableCustomerInfo.setTitle(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.title"));
            unreachableCustomerInfo.setMessage(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.message.A"));
            unreachableCustomerInfo.setLeftButtonText(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.left.button"));
            unreachableCustomerInfo.setRightButtonText(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.right.button.email"));
            unreachableCustomerInfo.setLink(emailLink);
            unreachableCustomerInfo.setNowDate(nowDate);
            return unreachableCustomerInfo;
        }
        // 只有通訊地址失聯
        else if ("Y".equals(addrUnc)) {
            unreachableCustomerInfo.setUnreachable(true);
            unreachableCustomerInfo.setTitle(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.title"));
            unreachableCustomerInfo.setMessage(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.message.B"));
            unreachableCustomerInfo.setLeftButtonText(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.left.button"));
            unreachableCustomerInfo.setRightButtonText(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.right.button.chatbot"));
            unreachableCustomerInfo.setLink(chatBotUrl);
            unreachableCustomerInfo.setNowDate(nowDate);
            return unreachableCustomerInfo;
        }
        // Email和手機號碼都為空值
        if (StringUtils.isEmpty(email) && StringUtils.isEmpty(mobile)) {
            unreachableCustomerInfo.setUnreachable(true);
            unreachableCustomerInfo.setTitle(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.title"));
            unreachableCustomerInfo.setMessage(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.message.F"));
            unreachableCustomerInfo.setLeftButtonText(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.left.button"));
            unreachableCustomerInfo.setRightButtonText(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.right.button.chatbot"));
            unreachableCustomerInfo.setLink(chatBotUrl);
            unreachableCustomerInfo.setNowDate(nowDate);
            return unreachableCustomerInfo;
        }
        // 只有Email為空值
        else if (StringUtils.isEmpty(email)) {
            unreachableCustomerInfo.setUnreachable(true);
            unreachableCustomerInfo.setTitle(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.title"));
            unreachableCustomerInfo.setMessage(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.message.D"));
            unreachableCustomerInfo.setLeftButtonText(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.left.button"));
            unreachableCustomerInfo.setRightButtonText(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.right.button.email"));
            unreachableCustomerInfo.setLink(emailLink);
            unreachableCustomerInfo.setNowDate(nowDate);
            return unreachableCustomerInfo;
        }
        // 只有手機號碼為空值
        else if (StringUtils.isEmpty(mobile)) {
            unreachableCustomerInfo.setUnreachable(true);
            unreachableCustomerInfo.setTitle(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.title"));
            unreachableCustomerInfo.setMessage(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.message.E"));
            unreachableCustomerInfo.setLeftButtonText(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.left.button"));
            unreachableCustomerInfo.setRightButtonText(I18NUtils.getMessage("ngn.qu001.unreachableCustomerInfo.right.button.chatbot"));
            unreachableCustomerInfo.setLink(chatBotUrl);
            unreachableCustomerInfo.setNowDate(nowDate);
            return unreachableCustomerInfo;
        }
        unreachableCustomerInfo.setUnreachable(false);
        return unreachableCustomerInfo;
    }

    private void setTaskCache() {
        setCache(NGNQU001Cache.class.getCanonicalName(), cacheData);
    }

    private void setRsData(NGNQU001010Rs rsData) {
        rsData.setLoggedIn(isLoggedIn());
        if (isLoggedIn()) {
            rsData.setCustomerBirthday(service.isUserBirthday(getLoginUser()));
        }
        rsData.setUnreadCount(output.getUnreadCount());
        rsData.setSystemNoticeRecItemNos(output.getSystemNoticeRecItemNos());
        rsData.setRespectInfo(output.getRespectInfo());
        rsData.setUserNickname(output.getUserNickname());
        rsData.setDisplayHomepageCardIds(output.getDisplayHomepageCardIds());
        rsData.setDisplayHomepageCardIdsDefault(output.getDisplayHomepageCardIdsDefault());
        rsData.setAllHomepageCardIds(output.getAllHomepageCardIds());
        rsData.setNewFunctionIntros(output.getNewFunctionIntros());
    }

    /**
     * (未登入時)取得是否有設定免登速查
     */
    private void checkCanQueryWithoutLogin() throws ActionException {
        if (!isLoggedIn()) {
            // 沒登入才繼續流程
            String deviceUuid = getRequest().getDeviceIxd();
            logger.debug("== checkCanQueryWithoutLogin, deviceUuid: {} ==", deviceUuid);
            this.service.getQSearchStatus(deviceUuid, getLocale(), output);
            logger.debug("== checkCanQueryWithoutLogin, QuickSearchData: {} ==", output.getQuickSearchData());
            cacheData.setQsearchData(output.getQuickSearchData()); // 這份放cache
        }
    }

    private void getHomepageCardsToDisplay() {
        // 有登入或是有設定免登速查
        if ((isLoggedIn() && null != getLoginUser()) || (null != output.getQuickSearchData() && output.getQuickSearchData().haveQuickSearchOn())) {
            this.service.fillUserQueryParam(getLoginUser(), input, output);
            this.service.getUserConfigedHomepageCards(input, output);
            // 如果沒有取到HomepageCardUser的資料時，取預設資料
            if (CollectionUtils.isEmpty(output.getDisplayHomepageCardIds())) {
                this.service.getDefaultHomepageCardIdToShowV2(output);
            }
        }
        else {
            this.service.getDefaultHomepageCardIdToShowV2(output);
        }
    }

    /**
     * 取三個月內是否有未讀取訊息
     */
    private void queryUnreadMessageCount() {
        AIBankUser aiBankUser = null;

        if (isLoggedIn() && null != getLoginUser()) {
            aiBankUser = getLoginUser();
        }
        else if (input.isMsgPermission()) {
            if (input.getAiBankUserNoneLoggedIn() != null) {
                aiBankUser = input.getAiBankUserNoneLoggedIn();
            }
            else if (null != output.getQuickSearchData() && output.getQuickSearchData().haveQuickSearchOn()) {
                QuickSearchResponse qSearchData = output.getQuickSearchData();

                UserVo userVo = new UserVo();
                userVo.setUserUuid(qSearchData.getUserId());

                AIBankUser aiBankUserNoneLoggedIn = new AIBankUser(userVo);
                aiBankUserNoneLoggedIn.setCustId(qSearchData.getCustId());

                CompanyVo companyVo = new CompanyVo();
                companyVo.setCompanyKind(qSearchData.getCompanyKind());
                companyVo.setUidDup(qSearchData.getUidDup());
                aiBankUserNoneLoggedIn.setCompanyVo(companyVo);
                input.setAiBankUserNoneLoggedIn(aiBankUserNoneLoggedIn);

                aiBankUser = aiBankUserNoneLoggedIn;
            }
        }

        try {
            input.setLocale(getLocale());
            input.setDeviceUuid(getRequest().getDeviceIxd());
            service.getUnreadMessageCountInThreeMonth(aiBankUser, input, output);
        }
        catch (ServiceException e) {
            logger.error("getUnreadMessageCountInThreeMonth err", e);
        }
        service.getSystemNotiRecs(output);
    }

    /**
     * 取得招呼語資料
     */
    private void getRespectInfo() {
        service.getRespectInfo(getLoginUser(), input, output);
    }

    private void getUserNickname(AIBankUser user) {
        if (null != user && null != user.getUserData()) {
            output.setUserNickname(StringUtils.toHalfChar(StringUtils.defaultString(getLoginUser().getUserData().getNickName())));
        }
    }

    private void checkBindingStatus(NGNQU001010Rs rsData) {

        boolean hasPermission = false;
        Integer userKey = null;
        RetrieveDeviceBindingResponse deviceBinding = null;

        if ((isLoggedIn() && null != getLoginUser())) {

            userKey = getLoginUser().getUserKey();

            // 檢查使用者與裝置綁定狀態
            CheckUserDeviceStatusCondition condition = new CheckUserDeviceStatusCondition();
            condition.setDeviceIxd(getDeviceIxd());
            condition.setLoginUser(getLoginUser());
            condition.setLocale(getLocale().toString());
            CheckUserDeviceStatusResult result = new CheckUserDeviceStatusResult();
            deviceBindingService.checkUserDeviceStatus(condition, result);

            // 狀態
            UserDeviceBindStatusType status = result.getStatus();

            // 綁定裝置與當下使用裝置一致
            if (status.isBind()) {
                logger.debug("***綁定裝置與當下使用裝置一致***");
                hasPermission = true;
            }
        }
        else {
            deviceBinding = userResource.retrieveDeviceBinding(getDeviceIxd());
            if (deviceBinding != null && deviceBinding.getModel() != null) {
                MbDeviceInfo model = deviceBinding.getModel();
                if (model.getMsgFlag() == 0) {
                    hasPermission = true;
                    userKey = deviceBinding.getModel().getUserKey();
                }
            }
        }

        // 前端公告訊習識別使用者處理[cacheKey]
        String cacheKey = DEFAULT_CACHE_KEY;
        if (hasPermission) {
            cacheKey = genCacheKeyByUserKey(userKey);
        }
        // 在此設置使用者有沒有讀取訊息權限，無論登入或未登入
        input.setMsgPermission(hasPermission);
        if (getLoginUser() == null && deviceBinding != null && hasPermission) {
            // 沒登入但有權限

            UserVo userVo = new UserVo();
            userVo.setUserUuid(deviceBinding.getUserId());

            AIBankUser aiBankUserNoneLoggedIn = new AIBankUser(userVo);
            aiBankUserNoneLoggedIn.setCustId(deviceBinding.getCustId());

            CompanyVo companyVo = new CompanyVo();
            companyVo.setCompanyKind(deviceBinding.getCompanyKind());
            companyVo.setUidDup(deviceBinding.getDup());
            aiBankUserNoneLoggedIn.setCompanyVo(companyVo);
            input.setAiBankUserNoneLoggedIn(aiBankUserNoneLoggedIn);
        }
        rsData.setCacheKey(cacheKey);
    }

    /**
     * 加密userKey當cacheKey
     *
     * @param userKey
     * @return
     */
    public String genCacheKeyByUserKey(Integer userKey) {
        String cacheKey = DEFAULT_CACHE_KEY;
        if (userKey == null)
            return cacheKey;
        try {
            cacheKey = getAESCipherUtils().encrypt(userKey.toString());
        }
        catch (Exception e) {
            logger.warn("userkey加密成cacheKey發生了問題", e);
        }
        return cacheKey;
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
