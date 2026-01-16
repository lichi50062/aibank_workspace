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
package com.tfb.aibank.chl.model.account;

import java.io.Serializable;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.common.code.AIBankConstants;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)FavoriteAccount.java
 * 
 * <p>Description:常用帳號</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/06, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "常用帳號")
public class FavoriteAccount implements Serializable {

    private static final long serialVersionUID = -4708193708758138631L;

    /** 轉入行庫 */
    @Schema(description = "轉入行庫")
    private String payeeBank;

    /** 轉入帳號 */
    @Schema(description = "轉入帳號")
    private String payeeAccount;

    /** 轉帳通知email */
    @Schema(description = "轉帳通知email")
    private String notifyEmail;

    /** 轉入帳號暱稱 */
    @Schema(description = "轉入帳號暱稱")
    private String accountAlias;

    /** 帳號順序 */
    @Schema(description = "帳號順序")
    private Integer accountSort;

    // ================== 以下為擴充欄位 ==============================
    /** 銀行名稱 */
    @Schema(description = "銀行名稱")
    private String bankName;

    /** 頭像角色 */
    @Schema(description = "頭像角色")
    private String avatarCharacter;
    // ================== 以上為擴充欄位 ==============================

    /**
     * @return the payeeBank
     */
    public String getPayeeBank() {
        return payeeBank;
    }

    /**
     * @param payeeBank
     *            the payeeBank to set
     */
    public void setPayeeBank(String payeeBank) {
        this.payeeBank = payeeBank;
    }

    /**
     * @return the payeeAccount
     */
    public String getPayeeAccount() {
        return payeeAccount;
    }

    /**
     * @param payeeAccount
     *            the payeeAccount to set
     */
    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

    /**
     * @return the notifyEmail
     */
    public String getNotifyEmail() {
        return notifyEmail;
    }

    /**
     * @param notifyEmail
     *            the notifyEmail to set
     */
    public void setNotifyEmail(String notifyEmail) {
        this.notifyEmail = notifyEmail;
    }

    /**
     * @return the accountAlias
     */
    public String getAccountAlias() {
        return accountAlias;
    }

    /**
     * @param accountAlias
     *            the accountAlias to set
     */
    public void setAccountAlias(String accountAlias) {
        this.accountAlias = accountAlias;
    }

    /**
     * @return the accountSort
     */
    public Integer getAccountSort() {
        return accountSort;
    }

    /**
     * @param accountSort
     *            the accountSort to set
     */
    public void setAccountSort(Integer accountSort) {
        this.accountSort = accountSort;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName
     *            the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * 顯示帳號名稱
     * 
     * @return
     */
    public String getDisplayAccountName() {
        return this.accountAlias;
    }

    /**
     * @return the avatarCharacter
     */
    public String getAvatarCharacter() {
        return avatarCharacter;
    }

    /**
     * @param avatarCharacter
     *            the avatarCharacter to set
     */
    public void setAvatarCharacter(String avatarCharacter) {
        this.avatarCharacter = avatarCharacter;
    }

    /**
     * 顯示頭像角色
     * 
     * @return
     */
    public String getDisplayAvatarCharacter() {
        if (StringUtils.isNotBlank(avatarCharacter)) {
            return avatarCharacter;
        }
        return AIBankConstants.AVATAR_CHARACTER_DEFAULT;
    }

}
