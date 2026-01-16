package com.tfb.aibank.chl.general.qu004.model;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.general.qu001.model.MenuVo;
import com.tfb.aibank.chl.general.resource.vo.faq.FaqTypeVo;
import com.tfb.aibank.chl.general.resource.vo.faq.GuideBizVo;
import org.springframework.stereotype.Component;

import java.util.List;

// @formatter:off
/**
 * @(#)NGNQU004020Rs.java
 * 
 * <p>Description:所有功能 020 查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/04, MartyP
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNQU004020Rs implements RsData {

    /** 可供搜尋用以設定的功能列表 */
    private List<MenuVo> menusForSearching;

    private  List<FaqTypeVo> faqTypeVos;

    private List<GuideBizVo> guideBizVos;

    private List<String> popularKeywords;

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
}
