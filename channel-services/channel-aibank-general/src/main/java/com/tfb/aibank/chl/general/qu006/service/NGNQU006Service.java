package com.tfb.aibank.chl.general.qu006.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.menu.Menu;
import com.ibm.tw.ibmb.component.menu.MenuCacheManager;
import com.ibm.tw.ibmb.type.MenuCategory;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.devicebinding.model.MbDeviceInfo;
import com.tfb.aibank.chl.component.devicebinding.model.RetrieveUserBindingResponse;
import com.tfb.aibank.chl.general.qu006.model.NGNQU006Data;
import com.tfb.aibank.chl.general.qu006.model.NGNQU006Output;
import com.tfb.aibank.chl.general.service.NGNService;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.type.LoginPasswdType;

// @formatter:off
/**
 * @(#)NGNQU006Service.java
 * 
 * <p>Description:CHL NGNQU005 服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/13, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NGNQU006Service extends NGNService {

    @Autowired
    private MenuCacheManager menuCacheManager;

    /**
     * 組成選單
     * 
     * @param aibankUser
     * @param userLocale
     * @param appVersion
     * @param dataOutput
     */
    public void buildOptionList(AIBankUser aibankUser, Locale userLocale, String appVersion, NGNQU006Output dataOutput) {

        boolean isEN = Locale.US.equals(userLocale);

        // 依登入身份讀取MENU
        List<Menu> allMenuList = new ArrayList<>();
        // 未登入
        if (aibankUser == null) {
            allMenuList = menuCacheManager.getMenusByCategory(isEN ? MenuCategory.AIDBU_PS_EN : MenuCategory.AIDBU_PS);
        }
        // 已登入
        else {
            // 一般會員登入
            if (aibankUser.getCustomerType().isGeneral()) {
                allMenuList = menuCacheManager.getMenusByCategory(isEN ? MenuCategory.AIDBU_PS_EN : MenuCategory.AIDBU_PS);
            }
            // 信用卡會員登入
            else if (aibankUser.getCustomerType().isCardMember()) {
                allMenuList = menuCacheManager.getMenusByCategory(isEN ? MenuCategory.AICCU_PS_EN : MenuCategory.AICCU_PS);
            }
        }

        // #8966 U-22017 過濾 MENU.IS_OPEN 不等於 1 的資料
        allMenuList = allMenuList.stream().filter(x -> x.getIsOpen() == 1).collect(Collectors.toList());

        // 依據版本號，過濾掉版本號大於APP版本號的功能
        allMenuList = allMenuList.stream().filter(menu -> {
            if (StringUtils.isBlank(appVersion) || StringUtils.isBlank(menu.getMenuVer())) {
                return true;
            }
            else {
                int result = IBUtils.compareAppVersion(menu.getMenuVer(), appVersion);
                return result < 1;
            }
        }).collect(Collectors.toList());

        // 一、先採集「功能群組」的項目
        List<Menu> menuRootList = new ArrayList<>();
        for (Menu menu : allMenuList) {
            if (StringUtils.equals("ROOT", menu.getParentMenuId())) {
                menuRootList.add(menu);
            }
        }
        // 二、再收集「功能群組」下的選單
        Map<String, List<Menu>> menuMap = new HashMap<>();
        for (Menu menu : allMenuList) {
            if (menuMap.get(menu.getParentMenuId()) == null) {
                menuMap.put(menu.getParentMenuId(), new ArrayList<>());
            }
            List<Menu> menuList = menuMap.get(menu.getParentMenuId());
            menuList.add(menu);
        }

        // 「功能群組」
        List<NGNQU006Data> rootDataList = new ArrayList<>();
        // 選單
        Map<String, List<NGNQU006Data>> menuDataMap = new HashMap<>();
        // 收集「功能群組」和選單資料
        if (CollectionUtils.isNotEmpty(menuRootList)) {
            IBUtils.sort(menuRootList, "orderNo", false);
            for (Menu root : menuRootList) {
                NGNQU006Data rootData = new NGNQU006Data(root, userLocale);
                rootData.setRoot(true);
                rootDataList.add(rootData);

                List<Menu> menuList = menuMap.get(rootData.getMenuId());
                if (CollectionUtils.isEmpty(menuList)) {
                    continue;
                }
                IBUtils.sort(menuList, "orderNo", false); // 排序

                if (menuDataMap.get(root.getMenuId()) == null) {
                    menuDataMap.put(root.getMenuId(), new ArrayList<>());
                }
                List<NGNQU006Data> dataList = menuDataMap.get(root.getMenuId());
                for (Menu menu : menuList) {
                    // 表示有子選單：有設定分類 (變更密碼/變更使用者代碼)
                    if (menu.getLinkType() == 2) {
                        List<Menu> subMenuList = menuMap.get(menu.getMenuId());
                        if (CollectionUtils.isEmpty(subMenuList)) {
                            continue;
                        }
                        NGNQU006Data data = new NGNQU006Data(menu, userLocale);
                        for (Menu subMenu : subMenuList) {
                            data.getSubMenus().add(new NGNQU006Data(subMenu, userLocale));
                        }
                        dataList.add(data);
                    }
                    // 表示無子選單
                    else {
                        dataList.add(new NGNQU006Data(menu, userLocale));
                    }
                }
            }
        }

        // 若選單資料為空，全部採用預設固定顯示
        if (CollectionUtils.isEmpty(rootDataList)) {
            buildDefaultOption(aibankUser, rootDataList, menuDataMap, userLocale);
        }

        dataOutput.setRootDataList(rootDataList);
        dataOutput.setMenuDataMap(menuDataMap);
    }

    /**
     * 組成預設固定選單
     * 
     * @param aibankUser
     * @param rootDataList
     * @param menuDataMap
     * @param userLocale
     */
    private void buildDefaultOption(AIBankUser aibankUser, List<NGNQU006Data> rootDataList, Map<String, List<NGNQU006Data>> menuDataMap, Locale userLocale) {

        // 登入與驗證設定
        NGNQU006Data rootData = new NGNQU006Data("NGN01", I18NUtils.getMessage("ngnqu006.loginandverify"), null);
        rootData.setRoot(true);
        rootDataList.add(rootData);
        menuDataMap.put(rootData.getMenuId(), new ArrayList<>());

        // 登入與驗證設定-快速登入
        menuDataMap.get(rootData.getMenuId()).add(new NGNQU006Data("NGN0101", getNameByTaskId("NPSAG007", userLocale, I18NUtils.getMessage("ngnqu006.txn.NPSAG007")), "NPSAG007"));
        // 登入與驗證設定-通知訊息
        menuDataMap.get(rootData.getMenuId()).add(new NGNQU006Data("NGN0102", getNameByTaskId("NPSAG009", userLocale, I18NUtils.getMessage("ngnqu006.txn.NPSAG009")), "NPSAG009"));
        // 登入與驗證設定-免登速查
        menuDataMap.get(rootData.getMenuId()).add(new NGNQU006Data("NGN0103", getNameByTaskId("NGNAG001", userLocale, I18NUtils.getMessage("ngnqu006.txn.NGNAG001")), "NGNAG001"));
        // 登入與驗證設定-動態密碼(MOTP)
        menuDataMap.get(rootData.getMenuId()).add(new NGNQU006Data("NGN0104", getNameByTaskId("NDSAG003", userLocale, I18NUtils.getMessage("ngnqu006.txn.NDSAG003")), "NDSAG003"));
        // 登入與驗證設定-行動裝置管理
        menuDataMap.get(rootData.getMenuId()).add(new NGNQU006Data("NGN0105", getNameByTaskId("NPSAG008", userLocale, I18NUtils.getMessage("ngnqu006.txn.NPSAG008")), "NPSAG008"));
        // 登入與驗證設定-螢幕截圖設定
        menuDataMap.get(rootData.getMenuId()).add(new NGNQU006Data("NGN0106", getNameByTaskId("NPSAG015", userLocale, I18NUtils.getMessage("ngnqu006.txn.NPSAG015")), "NPSAG015"));

        // 個人資訊管理
        rootData = new NGNQU006Data("NGN02", I18NUtils.getMessage("ngnqu006.personsetting"), null);
        rootData.setRoot(true);
        rootDataList.add(rootData);
        menuDataMap.put(rootData.getMenuId(), new ArrayList<>());

        // 一般會員登入
        if (aibankUser == null || aibankUser.getCustomerType().isGeneral()) {
            // 個人資訊管理-變更使用者代碼/密碼
            NGNQU006Data menuData = new NGNQU006Data("NGN0201", I18NUtils.getMessage("ngnqu006.changeuserUidandPin"), null);
            menuData.getSubMenus().add(new NGNQU006Data("NGN020101", getNameByTaskId("NPSAG004", userLocale, I18NUtils.getMessage("ngnqu006.txn.NPSAG004")), "NPSAG004")); // 使用者代碼
            menuData.getSubMenus().add(new NGNQU006Data("NGN020102", getNameByTaskId("NPSAG003", userLocale, I18NUtils.getMessage("ngnqu006.txn.NPSAG003")), "NPSAG003")); // 密碼
            menuDataMap.get(rootData.getMenuId()).add(menuData);
        }
        // 信用卡會員登入
        else if (aibankUser.getCustomerType().isCardMember()) {
            // 個人資訊管理-變更密碼
            menuDataMap.get(rootData.getMenuId()).add(new NGNQU006Data("NGN0201", getNameByTaskId("NCCAG010", userLocale, I18NUtils.getMessage("ngnqu006.txn.NCCAG010")), "NCCAG010"));
        }
        // 個人資訊管理-Email設定
        menuDataMap.get(rootData.getMenuId()).add(new NGNQU006Data("NGN0202", getNameByTaskId("NPSAG001", userLocale, I18NUtils.getMessage("ngnqu006.txn.NPSAG001")), "NPSAG001"));
        // 個人資訊管理-LINE帳號綁定
        menuDataMap.get(rootData.getMenuId()).add(new NGNQU006Data("NGN0203", getNameByTaskId("NGNOT001", userLocale, I18NUtils.getMessage("ngnqu006.txn.NGNOT001")), "NGNOT001"));
        // 個人資訊管理-共同行銷約定
        menuDataMap.get(rootData.getMenuId()).add(new NGNQU006Data("NGN0204", getNameByTaskId("NPSAG002", userLocale, I18NUtils.getMessage("ngnqu006.txn.NPSAG002")), "NPSAG002"));

        // 外觀設定
        rootData = new NGNQU006Data("NGN03", I18NUtils.getMessage("ngnqu006.appearrance"), null);
        rootData.setRoot(true);
        rootDataList.add(rootData);
        menuDataMap.put(rootData.getMenuId(), new ArrayList<>());

        // 外觀設定-字體大小
        menuDataMap.get(rootData.getMenuId()).add(new NGNQU006Data("NGN0301", getNameByTaskId("NPSAG012", userLocale, I18NUtils.getMessage("ngnqu006.txn.NPSAG012")), "NPSAG012"));
        // 外觀設定-語言
        menuDataMap.get(rootData.getMenuId()).add(new NGNQU006Data("NGN0302", getNameByTaskId("NPSAG011", userLocale, I18NUtils.getMessage("ngnqu006.txn.NPSAG011")), "NPSAG011"));
    }

    /**
     * 功能若為「快速登入」、「通知訊息」、「免登速查」、「動態密碼(MOTP)」、「螢幕截圖設定」需顯示選項狀態
     * 
     * @param aibankUser
     * @param userLocale
     * @param menuDataMap
     * @param enableScreenshot
     */
    public void setOptionStatus(AIBankUser aibankUser, Locale userLocale, Map<String, List<NGNQU006Data>> menuDataMap, boolean enableScreenshot) {
        // 表示未登入
        if (aibankUser == null) {
            return;
        }

        RetrieveUserBindingResponse retrieveUserBinding = userResource.retrieveUserBinding(aibankUser.getCustId(), aibankUser.getUidDup(), aibankUser.getUserId(), aibankUser.getCompanyKind(), userLocale.toString());
        MbDeviceInfo mbDeviceInfo = retrieveUserBinding.getModel();

        for (Map.Entry<String, List<NGNQU006Data>> entry : menuDataMap.entrySet()) {
            for (NGNQU006Data menu : entry.getValue()) {
                String taskId = menu.getTaskNo();
                if (StringUtils.isBlank(taskId)) {
                    continue;
                }
                switch (taskId) {
                case "NPSAG007": // 快速登入
                    if (mbDeviceInfo != null && mbDeviceInfo.getLoginPasswdType() != null) {
                        LoginPasswdType loginType = LoginPasswdType.find(mbDeviceInfo.getLoginPasswdType());
                        if (!loginType.equals(LoginPasswdType.UNKNOWN)) {
                            menu.setStatusDesc(loginType.getMemo());
                        }
                    }
                    break;
                case "NPSAG017": // 雙重驗證登入
                    if (mbDeviceInfo != null && mbDeviceInfo.getTwostepFlag() != null && mbDeviceInfo.getTwostepFlag() == 1) {
                        menu.setStatusDesc(I18NUtils.getMessage("ngnqu006.openflag")); // 已開啟
                    }
                    break;
                case "NPSAG009": // 通知設定
                    if (mbDeviceInfo != null && mbDeviceInfo.getNotifyFlag() != null && mbDeviceInfo.getNotifyFlag() == 1) {
                        menu.setStatusDesc(I18NUtils.getMessage("ngnqu006.openflag")); // 已開啟
                    }
                    break;
                case "NGNAG001": // 免登速查
                    if (mbDeviceInfo != null && mbDeviceInfo.getQsearchFlag() != null && mbDeviceInfo.getQsearchFlag() == 1) {
                        menu.setStatusDesc(I18NUtils.getMessage("ngnqu006.openflag")); // 已開啟
                    }
                    break;
                case "NDSAG003": // 動態密碼(MOTP)
                    if (mbDeviceInfo != null && mbDeviceInfo.getMotpFlag() != null && mbDeviceInfo.getMotpFlag() == 1) {
                        menu.setStatusDesc(I18NUtils.getMessage("ngnqu006.openflag")); // 已開啟
                    }
                    break;
                case "NPSAG015": // 螢幕截圖設定
                    if (enableScreenshot) {
                        menu.setStatusDesc(I18NUtils.getMessage("ngnqu006.allowScreenshots")); // 允許截圖
                    }
                    else {
                        menu.setStatusDesc(I18NUtils.getMessage("ngnqu006.screenshotsNotAllowed")); // 不允許截圖
                    }
                    break;
                }
            }
        }
    }

    private String getNameByTaskId(String taskId, Locale userLocale, String defaultValue) {
        String menuName = menuCacheManager.getTaskName(taskId, userLocale.toString());
        if (StringUtils.isBlank(menuName)) {
            return defaultValue;
        }
        return menuName;
    }
}
