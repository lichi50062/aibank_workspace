package com.tfb.aibank.chl.general.qu001.model;

import com.ibm.tw.ibmb.base.model.RsData;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @formatter:off
/**
 * @(#)NGNQU001040Rs.java
 *
 * <p>Description: NGNQU001040Rs</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>[NGNQU001040Rs]</li>
 * </ol>
 */
//@formatter:on
@Component
public class NGNQU001040Rs implements RsData {

    /** 常用功能 */
    private List<UsualTaskVo> usualTasks;

    /** 可設定的功能列表 */
    private List<MenuVo> menusForSetting;

    /** 可供搜尋用以設定的功能列表 */
    private List<MenuVo> menusForSearching;

    public List<UsualTaskVo> getUsualTasks() {
        return usualTasks;
    }

    public void setUsualTasks(List<UsualTaskVo> usualTasks) {
        this.usualTasks = usualTasks;
    }

    public List<MenuVo> getMenusForSetting() {
        return menusForSetting;
    }

    public void setMenusForSetting(List<MenuVo> menusForSetting) {
        this.menusForSetting = menusForSetting;
    }

    public List<MenuVo> getMenusForSearching() {
        return menusForSearching;
    }

    public void setMenusForSearching(List<MenuVo> menusForSearching) {
        this.menusForSearching = menusForSearching;
    }
}
