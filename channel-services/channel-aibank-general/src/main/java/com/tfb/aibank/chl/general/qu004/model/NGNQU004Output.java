package com.tfb.aibank.chl.general.qu004.model;

import com.tfb.aibank.chl.component.newfunctionintro.NewFunctionIntro;
import com.tfb.aibank.chl.general.qu001.model.MenuVo;
import com.tfb.aibank.chl.general.qu005.model.NGNQU005Output;
import com.tfb.aibank.chl.general.resource.vo.faq.FaqTypeVo;
import com.tfb.aibank.chl.general.resource.vo.faq.GuideBizVo;

import java.util.List;
import java.util.Map;

/**
// @formatter:off
 * @(#)NGNQU004Output.java
 * 
 * <p>Description:所有服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/04, MartyP
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NGNQU004Output extends NGNQU005Output {

    private List<MenuVo> allMenus;

    /**
     * 不過濾display = 1的 menu
     * */
    private List<MenuVo> allMenusForMapping;

    /* Key 為檢查項目，value => 0:不安全, 1:安全
     * */
    private Map<String, Integer> securityCheckMap;


    private List<NewFunctionIntro> newFunctionIntros;

    /** 可供搜尋用以設定的功能列表 */
    private List<MenuVo> menusForSearching;

    private  List<FaqTypeVo> faqTypeVos;

    private List<GuideBizVo> guideBizVos;

    private List<String> popularKeywords;

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

    public List<MenuVo> getMenusForSearching() {
        return menusForSearching;
    }

    public void setMenusForSearching(List<MenuVo> menusForSearching) {
        this.menusForSearching = menusForSearching;
    }

    public List<FaqTypeVo> getFaqTypeVos() {
        return faqTypeVos;
    }

    public void setFaqTypeVos(List<FaqTypeVo> faqTypeVos) {
        this.faqTypeVos = faqTypeVos;
    }

    public List<GuideBizVo> getGuideBizVos() {
        return guideBizVos;
    }

    public void setGuideBizVos(List<GuideBizVo> guideBizVos) {
        this.guideBizVos = guideBizVos;
    }

    public List<String> getPopularKeywords() {
        return popularKeywords;
    }

    public void setPopularKeywords(List<String> popularKeywords) {
        this.popularKeywords = popularKeywords;
    }

    public List<MenuVo> getAllMenusForMapping() {
        return allMenusForMapping;
    }

    public void setAllMenusForMapping(List<MenuVo> allMenusForMapping) {
        this.allMenusForMapping = allMenusForMapping;
    }
}
