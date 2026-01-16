package com.tfb.aibank.biz.systemconfig.services.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

import com.tfb.aibank.biz.systemconfig.repository.MenuKeywordMappingRepository;
import com.tfb.aibank.biz.systemconfig.repository.MenuNameRepository;
import com.tfb.aibank.biz.systemconfig.repository.MenuRepository;
import com.tfb.aibank.biz.systemconfig.repository.entities.MenuEntity;
import com.tfb.aibank.biz.systemconfig.repository.entities.MenuKeywordMappingEntity;
import com.tfb.aibank.biz.systemconfig.services.menu.model.MenuModel;

public class MenuService {

    private MenuRepository menuRepository;

    private MenuNameRepository menuNameRepository;

    private MenuKeywordMappingRepository menuKeywordMappingRepository;

    public MenuService(MenuRepository menuRepository, MenuNameRepository menuNameRepository, MenuKeywordMappingRepository menuKeywordMappingRepository) {
        this.menuRepository = menuRepository;
        this.menuNameRepository = menuNameRepository;
        this.menuKeywordMappingRepository = menuKeywordMappingRepository;
    }

    public List<MenuModel> findByCategory(List<String> category) {
        return this.menuRepository.findByCategoryInOrderByOrderNo(category).stream().map(this::convert).toList();
    }

    public Map<String, Map<String, String>> findAllMenuNames() {
        Map<String, Map<String, String>> menuNameMap = new HashMap<>();
        this.menuNameRepository.findAll().forEach(m -> {
            Map<String, String> localeMap = menuNameMap.getOrDefault(m.getMenuId(), new HashMap<>());
            localeMap.put(m.getLocale(), m.getMenuName());
            menuNameMap.put(m.getMenuId(), localeMap);
        });
        return menuNameMap;
    }

    public MenuModel convert(MenuEntity entity) {
        MenuModel m = new MenuModel();
        m.setCategory(entity.getCategory());
        m.setIconClass(entity.getIconClass());
        m.setIsDisplay(entity.getIsDisplay());
        m.setIsRef(entity.getIsRef());
        m.setMenuId(entity.getMenuId());
        m.setMenuVer(entity.getMenuVer());
        m.setOrderNo(entity.getOrderNo());
        m.setParentMenuId(entity.getParentMenuId());
        m.setTaskId(entity.getTaskId());
        m.setLinkType(entity.getLinkType());
        m.setCanBeSearch(entity.getCanBeSearch());
        m.setMenuDesc(entity.getMenuDesc());
        m.setLinkParam(entity.getLinkParam());
        m.setIsNew(entity.getIsNew());
        m.setIsOpen(entity.getIsOpen());
        return m;
    }

    /*
     * 取得 MenuId 相關關鍵字
     */
    public Map<String, List<String>> getMenuKeywordsForSearch(List<String> menuIds) {
        List<MenuKeywordMappingEntity> entities = menuKeywordMappingRepository.findByMenuIdIn(menuIds);
        Map<String, List<String>> map = new HashMap<>();
        if (CollectionUtils.isNotEmpty(entities)) {
            Map<String, List<MenuKeywordMappingEntity>> menuIdMap = entities.stream().collect(Collectors.groupingBy(MenuKeywordMappingEntity::getMenuId));
            menuIdMap.forEach((key, val) -> {
                map.put(key, val.stream().map(MenuKeywordMappingEntity::getKeyword).toList());
            });
        }
        return map;
    }

}
