package com.tfb.aibank.chl.general.qu004.model;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.component.newfunctionintro.NewFunctionIntro;
import com.tfb.aibank.chl.general.qu001.model.MenuVo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

// @formatter:off
/**
 * @(#)NGNQU004010Rs.java
 * 
 * <p>Description:所有功能 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/04, MartyP
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNQU004010Rs implements RsData {

    /** 頭像設定 */
    private String avatar;

    /** 暱稱 */
    private String nickName;

    private List<MenuVo> allMenus;

    /**
     * 不過濾display = 1的 menu
     * */
    private List<MenuVo> allMenusForMapping;

    /* Key 為檢查項目，value => 0:不安全, 1:安全
     * */
    private Map<String, Integer> securityCheckMap;

    private List<NewFunctionIntro> newFunctionIntros;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<MenuVo> getAllMenus() {
        return allMenus;
    }

    public void setAllMenus(List<MenuVo> allMenus) {
        this.allMenus = allMenus;
    }

    public Map<String, Integer> getSecurityCheckMap() {
        return securityCheckMap;
    }

    public void setSecurityCheckMap(Map<String, Integer> securityCheckMap) {
        this.securityCheckMap = securityCheckMap;
    }

    public List<NewFunctionIntro> getNewFunctionIntros() {
        return newFunctionIntros;
    }

    public void setNewFunctionIntros(List<NewFunctionIntro> newFunctionIntros) {
        this.newFunctionIntros = newFunctionIntros;
    }

    public List<MenuVo> getAllMenusForMapping() {
        return allMenusForMapping;
    }

    public void setAllMenusForMapping(List<MenuVo> allMenusForMapping) {
        this.allMenusForMapping = allMenusForMapping;
    }
}
