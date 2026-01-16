package com.tfb.aibank.chl.general.qu003.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NGNQU003020Rs.java
 * 
 * <p>Description:優惠 020 搜尋頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/14, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNQU003020Rs implements RsData {
    /**
     * {大家都在查}關鍵字
     */
    private List<String> keywords;

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
