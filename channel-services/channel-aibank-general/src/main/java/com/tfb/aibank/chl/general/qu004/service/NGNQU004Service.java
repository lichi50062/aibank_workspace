package com.tfb.aibank.chl.general.qu004.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.i18n.I18NCacheManager;
import com.ibm.tw.ibmb.component.i18n.I18nModel;
import com.ibm.tw.ibmb.component.menu.Menu;
import com.ibm.tw.ibmb.component.menu.MenuCacheManager;
import com.ibm.tw.ibmb.type.MenuCategory;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.newfunctionintro.NewFunctionIntro;
import com.tfb.aibank.chl.component.newfunctionintro.NewFunctionIntroCacheManager;
import com.tfb.aibank.chl.general.qu001.model.MenuVo;
import com.tfb.aibank.chl.general.qu001.utils.NGNQU001Util;
import com.tfb.aibank.chl.general.qu004.model.AccountSecurityCheckType;
import com.tfb.aibank.chl.general.qu004.model.NGNQU004Input;
import com.tfb.aibank.chl.general.qu004.model.NGNQU004Output;
import com.tfb.aibank.chl.general.qu005.service.NGNQU005Service;
import com.tfb.aibank.chl.general.resource.PreferencesResource;
import com.tfb.aibank.chl.general.resource.SystemConfigResource;
import com.tfb.aibank.chl.general.resource.vo.faq.FaqTypeVo;
import com.tfb.aibank.chl.general.resource.vo.faq.GuideBizVo;
import com.tfb.aibank.chl.general.utils.NGNUtils;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NGNQU004Service.java
 * 
 * <p>Description:CHL NGNQU004Service </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/04, MartyP
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NGNQU004Service extends NGNQU005Service {

    protected static IBLog logger = IBLog.getLog(NGNQU004Service.class.getName());

    @Autowired
    protected MenuCacheManager menuCacheManager;

    @Autowired
    protected SystemConfigResource systemConfigResource;

    @Autowired
    protected PreferencesResource preferencesResource;

    @Autowired
    protected NewFunctionIntroCacheManager newFunctionIntroCacheManager;

    @Autowired
    private NGNUtils ngnUtils;

    @Autowired
    private I18NCacheManager i18NCacheManager;

    /**
     * 取得TABLE [NEW_FUNCTION_INTRO] 有效資料
     */
    public void getNewFunctionsIntro(NGNQU004Input input, NGNQU004Output dataOutput) {
        // 只取對應到的語系
        List<NewFunctionIntro> intros = newFunctionIntroCacheManager.getNewFunctionIntrosByLocale(input.getLocale().toString());
        if (CollectionUtils.isNotEmpty(intros)) {

            // 檢查由Cache取出的資料是否在時間上是否為有效資料
            Date now = new Date();
            intros = intros.stream().filter(nfi -> now.after(nfi.getStartTime()) && (null == nfi.getEndTime() || now.before(nfi.getEndTime()))).collect(Collectors.toList());

            if (CollectionUtils.isNotEmpty(intros)) {
                IBUtils.sort(intros, "sort", false);
                dataOutput.setNewFunctionIntros(intros);
            }
        }
    }

    public void userAccountSecurityCheck(AIBankUser loginUser, NGNQU004Input input, NGNQU004Output dataOutput) throws ActionException {

        /*
         * Key 為檢查項目，value => 0:不安全, 1:安全
         */
        Map<String, Integer> securityCheckMap = new HashMap<>();

        // 「帳號安全區塊」密碼
        checkChangePwdStatus(loginUser, dataOutput);
        boolean pxdCheck = true;
        // 半年內未更新 or (半年內有更新但沿用舊密碼)
        if (!dataOutput.isChangePwdHalfYear() || (dataOutput.isChangePwdHalfYear() && dataOutput.isKeepOldPwdHalfYear())) {
            pxdCheck = false;
        }
        securityCheckMap.put(AccountSecurityCheckType.PXD_CHANGE.name(), pxdCheck ? 1 : 0);

        // 「帳號安全區塊」Email
        boolean emailCheck = true;
        // (A) 若未留存Email或 Email不正確，則視為非安全狀態
        if (StringUtils.isBlank(loginUser.getEmail()) || !ngnUtils.checkEmail(loginUser.getEmail())) {
            emailCheck = false;
        }
        securityCheckMap.put(AccountSecurityCheckType.SET_EMAIL.name(), emailCheck ? 1 : 0);

        checkDeviceStatus(loginUser, input.getLocale(), input.getClientRequest(), dataOutput);

        // 「交易驗證區塊」OTP
        // 一般會員
        // 若為信用卡會員登入，不會檢核推播動態密碼的項目，所以會從7項變6項。
        if (loginUser.getCustomerType().isGeneral()) {
            securityCheckMap.put(AccountSecurityCheckType.SET_PUSHOTP.name(), dataOutput.isEnableMOTP() ? 1 : 0);
        }

        // 「裝置設定區塊」FIDO
        securityCheckMap.put(AccountSecurityCheckType.FIDO.name(), dataOutput.isEnableFIDO() ? 1 : 0);

        // 「裝置設定區塊」雙重驗證登入
        securityCheckMap.put(AccountSecurityCheckType.TWO_STEP.name(), dataOutput.isTwoStepFlag() ? 1 : 0);

        // 「裝置設定區塊」裝置綁定
        securityCheckMap.put(AccountSecurityCheckType.DEVICE_BIND.name(), dataOutput.isDeviceBinding() ? 1 : 0);

        // 「軟體與系統區塊」APP版本
        securityCheckMap.put(AccountSecurityCheckType.APP_VERSION.name(), dataOutput.isAppIsLatestVersion() ? 1 : 0);

        // 「軟體與系統區塊」手機作業系統
        securityCheckMap.put(AccountSecurityCheckType.DEVICE_OS.name(), 1);

        dataOutput.setSecurityCheckMap(securityCheckMap);
    }

    /**
     * 取得所有可以提供使用者設定為常用功能的功能列表 <br>
     * A. 依據一般會員、信用卡會員登入顯示對應的選單內容 <br>
     * B. 取得功能選單，選單分為四層，分別為：業務大類、功能群組、功能節點、分類。
     * 
     * @param user
     * @param input
     * @param output
     * @param appVer
     * @param menuCategory
     */
    public void getAllMenusByUserType(AIBankUser user, NGNQU004Input input, NGNQU004Output output, String appVer, MenuCategory menuCategory) {

        getAllMenusForMapping(menuCategory, input, output, appVer);

        // 當前登入方式（身份）對應的所有選單 + IS_DISPLAY = 1(非隱藏功能)
        List<Menu> allMenusByCustomerType = this.menuCacheManager.getMenusByCategoryFilterByVersion(menuCategory.getCategory(), appVer).stream().filter(NGNQU001Util.menuIsDisplay).collect(Collectors.toList());
        // #8383 #8355 U-22017 新增顯示判斷 MENU.IS_OPEN = 1 才顯示
        allMenusByCustomerType = allMenusByCustomerType.stream().filter(x -> x.getIsOpen() == 1).collect(Collectors.toList());

        if (logger.isDebugEnabled()) {
            logger.debug("== getMenusForUserSetting ==, menuCategory:{}, allMenusByCustomerType: {}", menuCategory, allMenusByCustomerType);
        }

        if (CollectionUtils.isEmpty(allMenusByCustomerType)) {
            return;
        }
        // 業務大類MENU_IDs
        List<Menu> businessCateMenus = allMenusByCustomerType.stream().filter(NGNQU001Util.isBusinessCateAndIsDisplay).collect(Collectors.toList());

        // 依ParentMenuId為key的 map (不包含 parentMenuId = ROOT的資料)
        Map<String, List<Menu>> menuGroupMapByParentMenuId = allMenusByCustomerType.stream().filter(NGNQU001Util.getParentMenuIdsNotRootMenu).collect(Collectors.groupingBy(Menu::getParentMenuId));

        List<MenuVo> allMenus = new ArrayList<>();

        for (Menu bizCateMenu : businessCateMenus) {
            // 取得「功能群組」menus, 此層取parentMenuId = 業務大類menuId
            List<Menu> funGroupMenusInThisBizCate = menuGroupMapByParentMenuId.getOrDefault(bizCateMenu.getMenuId(), Collections.emptyList());
            if (logger.isDebugEnabled()) {
                logger.debug("= bizCateMenu =: {}", IBUtils.attribute2Str(bizCateMenu));
                logger.debug("= funGroupMenusInThisBizCate =: {}", IBUtils.attribute2Str(funGroupMenusInThisBizCate));
            }

            for (Menu funGroupMenu : funGroupMenusInThisBizCate) {
                if (logger.isDebugEnabled()) {
                    logger.debug("= funGroupMenu =: {}", IBUtils.attribute2Str(funGroupMenu));
                }
                // 取「功能節點」(實際上的功能點，但有可能下有再細分分類)
                List<Menu> funGrpNodes = menuGroupMapByParentMenuId.get(funGroupMenu.getMenuId());
                if (CollectionUtils.isNotEmpty(funGrpNodes)) {
                    // #8383 #8355 U-22017 新增顯示判斷 MENU.IS_OPEN = 1 才顯示
                    funGrpNodes = funGrpNodes.stream().filter(x -> x.getIsOpen() == 1).collect(Collectors.toList());
                }

                // 先查看此「功能群組」下是否有任何成員menu，沒有的話也要把自己當成一種menu
                if (CollectionUtils.isEmpty(funGrpNodes)) {
                    processMenuNodes(menuGroupMapByParentMenuId, bizCateMenu, funGroupMenu, null, allMenus, input.getLocale());
                }
                else {
                    processMenuNodes(menuGroupMapByParentMenuId, bizCateMenu, funGroupMenu, funGrpNodes, allMenus, input.getLocale());
                }
            }
        }
        output.setAllMenus(allMenus);
    }

    private void getAllMenusForMapping(MenuCategory menuCategory, NGNQU004Input input, NGNQU004Output output, String appVer) {
        // 當前登入方式（身份）對應的所有選單 + IS_DISPLAY = 1(非隱藏功能)
        List<Menu> allMenusByCustomerType = this.menuCacheManager.getMenusByCategoryFilterByVersion(menuCategory.getCategory(), appVer);
        // #8383 #8355 U-22017 新增顯示判斷 MENU.IS_OPEN = 1 才顯示
        allMenusByCustomerType = allMenusByCustomerType.stream().filter(x -> x.getIsOpen() == 1).collect(Collectors.toList());

        if (CollectionUtils.isNotEmpty(allMenusByCustomerType)) {
            List<MenuVo> menusForMapping = new ArrayList<>();
            for (var menu : allMenusByCustomerType) {
                MenuVo vo = NGNQU001Util.funGroupMenuToMenuVo(null, menu, input.getLocale());
                menusForMapping.add(vo);
            }
            output.setAllMenusForMapping(menusForMapping);
        }
    }

    /**
     * menuGroupMapByParentMenuId -> 以ParentMenuId為key分組的map bizCateMenu -> 業務大類 menu<br>
     * funGroupMenu -> 功能群組 menu<br>
     */
    protected void processMenuNodes(Map<String, List<Menu>> menuGroupMapByParentMenuId, Menu bizCateMenu, Menu funGroupMenu, List<Menu> funGrpNodes, List<MenuVo> menusForSetting, Locale locale) {

        if (CollectionUtils.isNotEmpty(funGrpNodes)) {
            for (Menu node : funGrpNodes) {

                MenuVo vo = NGNQU001Util.menuToMenuVo(bizCateMenu, funGroupMenu, node, locale);

                // 向menuGroupMapByParentMenuId取得 以當前vo的menuId為parentMenuId的資料
                List<Menu> nodeMenus = menuGroupMapByParentMenuId.get(vo.getMenuId());

                // 當前vo的menuId向下無menu，是最後node
                if (CollectionUtils.isEmpty(nodeMenus)) {
                    vo.setNodeFlag(0);
                    menusForSetting.add(vo);
                }
                else {
                    // 當前vo的menuId向下有menu，取最後一層
                    vo.setNodeFlag(1);

                    List<MenuVo> lastNodeVos = nodeMenus.stream().map(n -> {
                        MenuVo lastVo = NGNQU001Util.menuToMenuVo(bizCateMenu, funGroupMenu, n, locale);
                        lastVo.setParentMenuName(vo.getMenuName());
                        lastVo.setNodeFlag(0);
                        return lastVo;
                    }).collect(Collectors.toList());

                    vo.setNodeMenus(lastNodeVos);
                    menusForSetting.add(vo);
                }
            }
        }
        else {
            MenuVo vo = NGNQU001Util.funGroupMenuToMenuVo(bizCateMenu, funGroupMenu, locale);
            menusForSetting.add(vo);
        }

    }

    public void getMenusForUserSearching(AIBankUser user, NGNQU004Input input, NGNQU004Output output, String appVer, MenuCategory menuCategory) {

        // 當前登入方式（身份）對應的所有選單 + IS_DISPLAY = 1(非隱藏功能) + CanBeSearch = 1
        List<Menu> allMenusToSeach = this.menuCacheManager.getMenusByCategoryFilterByVersion(menuCategory.getCategory(), appVer);
        if (logger.isDebugEnabled()) {
            logger.debug("allMenusToSeach: {} ", IBUtils.attribute2Str(allMenusToSeach));
        }
        allMenusToSeach = allMenusToSeach.stream().filter(NGNQU001Util.menuCanBeSearch).collect(Collectors.toList());
        // #8383 #8355 U-22017 新增顯示判斷 MENU.IS_OPEN = 1 才顯示
        allMenusToSeach = allMenusToSeach.stream().filter(x -> x.getIsOpen() == 1).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(allMenusToSeach)) {
            return;
        }

        List<MenuVo> menusForSearch = new ArrayList<>();
        List<String> menuIds = allMenusToSeach.stream().map(Menu::getMenuId).collect(Collectors.toList());

        Map<String, List<String>> menuIdKeywordMap = systemConfigResource.getMenuKeywordsForSearch(menuIds);

        for (Menu menu : allMenusToSeach) {
            MenuVo menuVo = NGNQU001Util.menuToMenuVo(null, null, menu, input.getLocale());

            if (menuIdKeywordMap.containsKey(menuVo.getMenuId())) {
                menuVo.setKeywords(menuIdKeywordMap.get(menuVo.getMenuId()));
            }
            menusForSearch.add(menuVo);
        }
        output.setMenusForSearching(menusForSearch);
    }

    public void getFAQs(NGNQU004Input input, NGNQU004Output output) {

        List<FaqTypeVo> faqTypeVos = preferencesResource.queryAllFaqData(input.getLocale().toString());

        output.setFaqTypeVos(faqTypeVos);

        List<GuideBizVo> guideBizVos = preferencesResource.queryAllGuideData(input.getLocale().toString());

        output.setGuideBizVos(guideBizVos);
    }

    public void getPopularKeywords(NGNQU004Output dataOutput, Locale locale) {

        I18nModel i18nModel = i18NCacheManager.getSingleResult(locale, "MENU_KEYWORD", "FUCNTION_SEARCH_KEYWORD");

        if (i18nModel != null && i18nModel.getValue() != null) {
            List<String> kwordList = null;
            kwordList = Stream.of(i18nModel.getValue().split(",")).map(str -> StringUtils.trimToEmptyEx(str)).toList();
            dataOutput.setPopularKeywords(kwordList);
        }

    }

    public void prepare020Datas(AIBankUser loginUser, NGNQU004Input dataInput, NGNQU004Output dataOutput, String appVer, Locale locale, MenuCategory menuCategory) {
        // 組成可被搜尋的Menu list
        getMenusForUserSearching(loginUser, dataInput, dataOutput, appVer, menuCategory);
        // FAQ 和疑難排解
        getFAQs(dataInput, dataOutput);
        // 熱門關鍵字
        getPopularKeywords(dataOutput, locale);

    }
}
