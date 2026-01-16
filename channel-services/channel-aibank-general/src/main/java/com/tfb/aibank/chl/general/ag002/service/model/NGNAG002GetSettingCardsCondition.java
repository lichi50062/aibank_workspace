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
package com.tfb.aibank.chl.general.ag002.service.model;

import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NGNAG002GetSettingCardsCondition.java
 * 
 * <p>Description:取得畫面牌卡設定資料 - Condition</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NGNAG002GetSettingCardsCondition {

    /** 使用者 */
    private AIBankUser loginUser;

    /**
     * @return the loginUser
     */
    public AIBankUser getLoginUser() {
        return loginUser;
    }

    /**
     * @param loginUser
     *            the loginUser to set
     */
    public void setLoginUser(AIBankUser loginUser) {
        this.loginUser = loginUser;
    }

}
