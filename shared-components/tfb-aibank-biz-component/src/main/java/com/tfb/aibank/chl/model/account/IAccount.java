package com.tfb.aibank.chl.model.account;

// @formatter:off
/**
 * @(#)IAccount.java
 * 
 * <p>Description:「帳號」界面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/04, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface IAccount {

    /** 帳號 */
    String getAccountId();

    /** 帳戶名稱 */
    String getAccountName();

    /** 幣別 */
    String getCurCode();

    /** 幣別 */
    String getCurName();

    /** 分行代碼 */
    String getBranchNo();

    /** 分行名稱 */
    String getBranchName();

    /** 帳號：顯示用，自行14碼、他行不進行處理 */
    String getDisplayAccountId();

    /** 帳號名稱：有則顯示，無則顯示分行名稱 */
    String getDisplayAccountName();

    /** 暱稱：有暱稱時顯示暱稱，無則顯示帳號名稱 */
    String getDisplayAcctNickname();

}
