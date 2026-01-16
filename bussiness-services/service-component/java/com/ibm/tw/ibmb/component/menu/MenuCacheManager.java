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
package com.ibm.tw.ibmb.component.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableList;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;
import com.ibm.tw.ibmb.type.MenuCategory;
import com.ibm.tw.ibmb.util.IBUtils;

// @formatter:off
/**
 * @(#)MenuCacheManager.java
 * 
 * <p>Description:選單檔 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class MenuCacheManager extends AbstractCacheManager {

    @Autowired
    private MenuResource resource;

    /** 以 CATEGORY 為鍵值，取其下所有 MENU */
    private Map<String, List<Menu>> categoryCacheMap = new HashMap<>();

    /** 以 PARENT_MENU_ID 為鍵值，取其下所有 MENU */
    private Map<String, List<Menu>> menuCacheMapByParentMenuId = new HashMap<>();

    /** 以 TASK_ID 為鍵值，取其對應的 MENU */
    // private Map<String, Menu> taskIdCacheMap = new HashMap<>();

    /** 以 TASK_ID 為鍵值，收集 MENU，並且以 ORDER_NO 升幂排序 */
    private Map<String, Set<Menu>> taskIdMenuCacheMap = new HashMap<>();

    /**
     * 讀得全部 MENU
     * 
     * @return
     */
    public List<Menu> getAllMenus() {
        return this.categoryCacheMap.values().stream().flatMap(menus -> menus.stream()).toList();
    }

    /**
     * 依 CATEGORY 讀取其下所有 MENU
     * 
     * @param category
     * @return
     */
    public List<Menu> getMenusByCategory(String category) {
        return this.categoryCacheMap.getOrDefault(category, Collections.emptyList());
    }

    /**
     * 依 CATEGORY 讀取其下所有 MENU
     * 
     * @param category
     * @return
     */
    public List<Menu> getMenusByCategory(MenuCategory category) {
        return this.categoryCacheMap.getOrDefault(category.getCategory(), Collections.emptyList());
    }

    /**
     * 依 CATEGORY 讀取其下所有 MENU，並以 MENU_VER 過濾
     * 
     * @param category
     * @param appVersion
     * @return
     */
    public List<Menu> getMenusByCategoryFilterByVersion(String category, String appVersion) {
        return getMenusByCategory(category).stream().filter(menu -> compareVersion(appVersion, menu.getMenuVer())).collect(Collectors.toList());
    }

    /**
     * 以 MENU_VER 過濾
     * 
     * 若 MENU_VER 為空 回 true
     * 
     * 若 appVersion >= MENU_VER 回 true
     * 
     * @param appVersion
     * @param targetVersion
     * @return
     */
    private boolean compareVersion(String appVersion, String targetVersion) {
        if (StringUtils.isBlank(targetVersion))
            return true;
        return IBUtils.compareAppVersion(appVersion, targetVersion) > -1;
    }

    /**
     * 依 PARENT_MENU_ID 讀取其下全部所有 MENU
     * 
     * @param parentMenuId
     * @return
     */
    public List<Menu> getMenusByParentMenuId(String parentMenuId) {
        return this.menuCacheMapByParentMenuId.getOrDefault(parentMenuId, Collections.emptyList());
    }

    /**
     * 依 TASK_ID、LOCALE 讀取對應 MENU_NAME (每個 MENU 都包含各語系 MENU_NAME，此處不用考慮 CATEGORY)
     * 
     * @param taskId
     * @param userLocale
     * @return
     */
    public String getTaskName(String taskId, String userLocale) {
        List<Menu> menuList = getMenusByTaskId(taskId);
        if (CollectionUtils.isEmpty(menuList)) {
            return StringUtils.EMPTY;
        }
        else {
            String menuName = menuList.get(0).getMenuName(userLocale);
            if (StringUtils.isBlank(menuName)) {
                return StringUtils.EMPTY;
            }
        }
        return menuList.get(0).getMenuName(userLocale);
    }

    /**
     * 依 TASK_ID 收集所有對應 MENU，並且以 ORDER_NO 進行升幂排序 (此處不用考慮 CATEGORY)
     * 
     * @param taskId
     * @return
     */
    public List<Menu> getMenusByTaskId(String taskId) {
        Set<Menu> menus = this.taskIdMenuCacheMap.getOrDefault(taskId, null);
        return menus != null ? ImmutableList.copyOf(menus) : null;
    }

    /**
     * 依 MENU_ID 從指定 CATEGORY 的 MENU 記錄中讀取對應 MENU
     * 
     * @param category
     * @param menuId
     * @return
     */
    public Menu getMenuByMenuId(String category, String menuId) {
        List<Menu> menusByCategory = getMenusByCategory(category);
        Optional<Menu> opt = menusByCategory.stream().filter(x -> StringUtils.equals(x.getMenuId(), menuId)).findFirst();
        return opt.isPresent() ? opt.get() : null;
    }

    /**
     * 依 MENU_ID 從全部 MENU 記錄中讀取對應 MENU
     * 
     * @param menuId
     * @return
     */
    public Menu getMenuByMenuId(String menuId) {
        List<Menu> allMenus = getAllMenus();
        Optional<Menu> opt = allMenus.stream().filter(alm -> StringUtils.equals(alm.getMenuId(), menuId)).findFirst();
        return opt.isPresent() ? opt.get() : null;
    }

    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.MENU_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        // 依指定的 MENU.CATEGORY 讀取選單(MENU)
        String[] categoryNames = Arrays.stream(MenuCategory.values()).map(Enum::name).toArray(String[]::new);
        List<String> categories = Arrays.asList(categoryNames);

        List<Menu> menuList = resource.getMenuByCategories(categories);
        Map<String, List<Menu>> categoryCacheMap = new HashMap<>();
        Map<String, List<Menu>> menuCacheMapByParentMenuId = new HashMap<>();
        Map<String, Set<Menu>> taskIdMenuCacheMap = new HashMap<>();

        // Map<MENU_ID, Map<LOCALE, MENU_NAME>>
        Map<String, Map<String, String>> menuNameCacheMap = resource.getMenuNames();

        menuList.forEach(m -> {

            // 設置 Menu Name
            m.setMenuNameMap(menuNameCacheMap.getOrDefault(m.getMenuId(), new HashMap<>()));
            // 設置 Parent Menu Name
            m.setParentMenuNameMap(menuNameCacheMap.getOrDefault(m.getParentMenuId(), new HashMap<>()));

            // 處理 category -> List<menu> map
            List<Menu> categoryMenus = categoryCacheMap.get(m.getCategory());
            if (categoryMenus == null) {
                categoryMenus = new ArrayList<>();
                categoryCacheMap.put(m.getCategory(), categoryMenus);
            }
            categoryMenus.add(m);

            // 處理 menuId -> List<menu> map
            List<Menu> menuIdMenus = menuCacheMapByParentMenuId.get(m.getParentMenuId());
            if (menuIdMenus == null) {
                menuIdMenus = new ArrayList<>();
                menuCacheMapByParentMenuId.put(m.getParentMenuId(), menuIdMenus);
            }
            menuIdMenus.add(m);

            // 處理 task -> List<menu> map
            Set<Menu> taskMenuSet = taskIdMenuCacheMap.get(m.getTaskId());
            if (taskMenuSet == null) {
                taskMenuSet = new TreeSet<Menu>(Comparator.comparing(Menu::getOrderNo));
                taskIdMenuCacheMap.put(m.getTaskId(), taskMenuSet);
            }
            taskMenuSet.add(m);
        });
        this.categoryCacheMap = categoryCacheMap;
        this.menuCacheMapByParentMenuId = menuCacheMapByParentMenuId;
        this.taskIdMenuCacheMap = taskIdMenuCacheMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.categoryCacheMap.isEmpty() || this.menuCacheMapByParentMenuId.isEmpty() || this.taskIdMenuCacheMap.isEmpty();
    }

}
