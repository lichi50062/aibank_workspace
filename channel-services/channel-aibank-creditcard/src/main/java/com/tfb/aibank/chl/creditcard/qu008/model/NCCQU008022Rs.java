package com.tfb.aibank.chl.creditcard.qu008.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCQU008022Rs.java
 * 
 * <p>Description:信用卡分期管理 022 分期注意事項頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/02, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU008022Rs implements RsData {
    /**
     * 目標page代號 e.g. 010
     */
    private String targetPagePath;

    /**
     * @return the targetPagePath
     */
    public String getTargetPagePath() {
        return targetPagePath;
    }

    /**
     * @param targetPagePath
     *            the targetPagePath to set
     */
    public void setTargetPagePath(String targetPagePath) {
        this.targetPagePath = targetPagePath;
    }

}
