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
package com.tfb.aibank.chl.session;

import java.io.Serializable;

// @formatter:off
/**
 * @(#)BaseAIBankUserExtend.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/03, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BaseAIBankUserExtend<T> implements Serializable {

    /**
     * <code>serialVersionUID</code> 的註解
     */
    private static final long serialVersionUID = -6779650356256678248L;

    /**
     * for reference only
     */
    protected AIBankUser aiBankUser = null;

    protected T loginMsgRs = null;

    /**
     * 取得 b2cPibUser
     * 
     * @return 傳回 b2cPibUser。
     */
    public AIBankUser getB2cPibUser() {
        return aiBankUser;
    }

    /**
     * 設定 b2cPibUser
     * 
     * @param b2cPibUser
     *            要設定的 b2cPibUser。
     */
    public void setB2cPibUser(AIBankUser aiBankUser) {
        this.aiBankUser = aiBankUser;
    }

    /**
     * 取得 loginMsgRs
     * 
     * @return 傳回 loginMsgRs。
     */
    public T getLoginMsgRs() {
        return loginMsgRs;
    }

    /**
     * 設定 loginMsgRs
     * 
     * @param loginMsgRs
     *            要設定的 loginMsgRs。
     */
    public void setLoginMsgRs(T loginMsgRs) {
        this.loginMsgRs = loginMsgRs;
    }

}
