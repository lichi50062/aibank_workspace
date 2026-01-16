package com.tfb.aibank.chl.model.account;

// @formatter:off
/**
 * @(#)ITransInAccount.java
 * 
 * <p>Description:「轉入帳號」界面，資料來源 EB5556911 </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/04, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface ITransInAccount extends IAccount {

    /** 帳號暱稱 */
    String getAccountAlias();

    /** 頭像類型 */
    String getAvatarType();

    /** 指定類型 1:常用，2:約定 */
    String getDesignateType();

    /** 帳號下拉選單，選取後顯示 */
    String getAccountLabel();

    /** 帳號下拉選單 */
    String getAccountDropdown();
}
