package com.tfb.aibank.chl.model.account;

import java.util.List;

// @formatter:off
/**
 * @(#)ITransOutAccount.java
 * 
 * <p>Description:「轉出帳號」界面，資料來源 EB5557891</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/04, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface ITransOutAccount extends IAccount {

    /** 帳號類型 */
    String getAcctType();

    /** 帳號子類型 */
    String getAcctSubType();

    /** 帳號類型名稱 */
    String getAcctTypeName();

    /** 帳號說明 */
    String getAcctDescription();

    /** 帳號暱稱 */
    String getAcctNickName();

    /** 產品大類 */
    String getProdCode();

    /** 產品子類 */
    String getProdSubCode();

    /** 轉出註記 Y:可轉出 */
    boolean isTransOutFlag();

    /** 開啟轉出服務註記 */
    boolean isOpenTransFlag();

    /** 非約轉註記 Y:可以進行非約轉 */
    boolean isNonAgreedFlag();

    /** 提高限額註記 Y:有提高限額 */
    boolean isIncreaseFlag();

    /** 數存註記 Y:表示為數位帳號 */
    boolean isDigitalFlag();

    /** 外幣註記 Y:可執行外幣交易 */
    boolean isForeignFlag();

    // 以下兩個欄位，待邏輯確認後再啟用
    // /** 定存註記 Y:可作定存 */
    // boolean isFixedFlag();

    // /** 信託註記 Y:可作信託 */
    // boolean isTrustFlag();

    /** 約定轉入帳號清單 */
    List<AgreedInAccount> getAgreedInAccounts();

    /** 帳號下拉選單，選取後顯示(第一行內容) */
    String getAccountLabel1();

    /** 帳號下拉選單，選取後顯示(第二行內容) */
    String getAccountLabel2();

    /** 帳號下拉選單，選取後顯示(第二行內容)
     *  [投資類交易帳號]
     *  <pre>
     *     {餘額}：存單或可用餘額 – 透支總限額
     *     EB5557891.AVAL_AMT - EB5557891.OD_TOT_LIMIT
     *  </pre>
     * */
    String getAccountLabel2Invest();

    /** 帳號下拉選單(第一行內容) */
    String getAccountDropdown1();

    /** 帳號下拉選單(第二行內容) */
    String getAccountDropdown2();

    /** 帳號顯示：確認頁、結果頁(第一行內容)，帳號名稱＋空格＋帳號 */
    String getDisplayAccount1();

    /** 帳號顯示：確認頁、結果頁(第二行內容)，餘額＋空格＋幣別＋空格＋金額 */
    String getDisplayAccount2();

}
