package com.tfb.aibank.chl.general.qu003.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NGNQU003010Rs.java
 * 
 * <p>Description:優惠 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/14
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNQU003011Rs implements RsData {

    private boolean actionResult;

    public boolean isActionResult() {
        return actionResult;
    }

    public void setActionResult(boolean actionResult) {
        this.actionResult = actionResult;
    }
}
