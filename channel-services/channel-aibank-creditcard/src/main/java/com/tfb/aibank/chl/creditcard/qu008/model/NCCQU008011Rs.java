package com.tfb.aibank.chl.creditcard.qu008.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCQU008011Rs.java
 * 
 * <p>Description:信用卡分期管理 011 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/02, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU008011Rs implements RsData {
    /** 欲替換的目標Tab 0:已分期 , 1:可分期 */
    private String changeTabFlag;

    public String getChangeTabFlag() {
        return changeTabFlag;
    }

    public void setChangeTabFlag(String changeTabFlag) {
        this.changeTabFlag = changeTabFlag;
    }

}
