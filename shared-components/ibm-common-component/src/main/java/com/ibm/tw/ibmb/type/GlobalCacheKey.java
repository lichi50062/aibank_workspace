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
package com.ibm.tw.ibmb.type;

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)GlobalCacheKey.java
 * 
 * <p>Description:所有 CacheManager 的鍵值</p>
 * <p>命名後綴加上【_CACHE_KEY】</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/26, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum GlobalCacheKey implements IEnum {

    SYSTEM_PARAM_CACHE_KEY("系統參數資料"),

    CONTAC_US_CACHE_KEY("聯繫我們代碼檔"),

    CURRENCY_CODE_CACHE_KEY("幣別代碼檔"),

    BANK_CACHE_KEY("銀行檔"),

    E_TRANS_BANK_CACHE_KEY("全國繳銀行主檔"),

    BRANCH_CACHE_KEY("分行檔"),

    BANK_BRANCH_CACHE_KEY("銀行分行檔"),

    BRANCHMAP_CACHE_KEY("服務據點檔"),

    ACCOUNT_TYPE_CACHE_KEY("帳號科目別(人工維護)"),

    ACCOUNT_INFO_CACHE_KEY("帳號資料檔"),

    ACCOUNT_INFO_LOAN_CACHE_KEY("貸款帳號資料檔"),

    ACCOUNT_GENERAL_RULES_CACHE_KEY("帳號通則資料檔"),

    ACCOUNT_ALIAS_CACHE_KEY("帳號暱稱檔"),

    REMARK_CONTENT_CACHE_KEY("文案內容", true),

    ERROR_CODE_CACHE_KEY("錯誤代碼表"),

    BIZ_TYPE_CACHE_KEY("業務BizType資料"),

    BUSINESS_DAY_CACHE_KEY("營業日資料"),

    EXCHANGE_RATE_HISTORY_CACHE_KEY("歷史匯率檔"),

    RATE_CURRENCY_CACHE_KEY("幣別匯率排序檔"),

    TASK_CACHE_KEY("task 資訊"),

    TASK_PAGE_CACHE_KEY("taskPage 資訊"),

    MENU_CACHE_KEY("選單"),

    ROLE_TASK_CACHE_KEY("RoleTask資訊"),

    FX_INTEREST_RATE_CACHE_KEY("外幣存款利率檔"),

    TWD_INTEREST_RATE_CACHE_KEY("台幣存款利率檔"),

    HOMEPAGE_CARD_CACHE_KEY("首頁牌卡設定檔"),

    CARD_PICTURE_CACHE_KEY("卡面資料檔"),

    CARD_TYPE_CACHE_KEY("卡別資料檔"),

    CARD_PAY_TYPE_CACHE_KEY("支付類型資料檔"),

    FX_TRANS_CURRENCY_DISCOUNT_CACHE_KEY("換匯優惠檔"),

    TASK_TIME_CACHE_KEY("依各交易定義可執行即時交易之時間tasktime 資訊"),

    ACCOUNT_PROMO_CACHE_KEY("帳戶總覽推薦牌卡"),

    REMIT_ITEM_LEVEL_THREE_CACHE_KEY("匯款性質細項說明"),

    REMIT_ITEM_LEVEL_THREE_LARGE_CACHE_KEY("匯款性質(大額)細項說明"),

    FX_REMIT_DECLARE_CACHE_KEY("國外匯入匯款申報性質設定"),

    AVAILABLE_TASK_CACHE_KEY("控管可用TASK清單"),

    PAY_ITEMS_CACHE_KEY("繳款項目"),

    TAX_ITEM_TYPE_CACHE_KEY("繳稅繳款類別"),

    DEVICE_MODEL_CACHE_KEY("行動裝置型號對應表"),

    ACTIVITY_ONLINE_CACHE_KEY("活動登錄人數上限"),

    AIBANK_SERVICE_SETTING_CACHE_KEY("AI Bank滿意度問卷設定資料表"),

    AIBANK_SERVICE_QUESTION_OPTION_CACHE_KEY("AI Bank滿意度問卷選項"),

    NEW_FUNC_INTROS_CACHE_KEY("新功能介紹Table資料"),

    CITY_AREA_ZIPCODE_CACHE_KEY("縣市、鄉鎮市區、郵遞區號"),

    DICTIONARY_CACHE_KEY("電子字典資料"),

    BOND_INFO_CACHE_KEY("債券資訊"),

    SUSPEND_TASK_CACHE_KEY("暫停交易設定檔"),

    STOCK_INFO_CACHE_KEY("海外ETF/海外股票資訊檔"),

    STOCK_MARKET_DAY_CACHE_KEY("海外ETF/海外股票市場交易日"),

    FUND_CHECK_DATA_CACHE_KEY("基金可購買檢核用資料"),

    FUND_LIMIT_AMOUNT_CACHE_KEY("基金限額資料"),

    FUND_DIVIDEND_FREQ_CACHE_KEY("基金配息頻率與代碼"),

    MFD_PUNCHLINE_CACHE_KEY("基金警語比對檔"),

    MFD_INFO_CACHE_KEY("基金資訊", true),

    IPO_FUND_CACHE_KEY("募集中基金資訊"),

    STOCK_CODE_INFO_CACHE_KEY("海外ETF/海外股票代碼資訊檔"),

    MFD_PROMO_CODE_CACHE_KEY("基金平台優惠檔"),

    MFD_AREA_CACHE_KEY("基金地區"),

    MFD_COMPANY_CACHE_KEY("基金公司"),

    MFD_ALLOCATE_CACHE_KEY("基金配息方式"),

    MFD_DISCOUNT_IFNO_CACHE_KEY("基金優惠資訊檔"),

    PRODUCTS_RISK_PROPERTY_CACHE_KEY("海外ETF/海外股票 商品風險屬性"),

    GOLD_RATE_HISTORY_CACHE_KEY("黃金存摺歷史牌價"),

    AI_I18N_CACHE_KEY("頁面文案多語系檔"),

    DEBIT_CARD_CACHE_KEY("簽帳卡資料檔"),

    MFD_LIMIT_AMOUNT_CACHE_KEY("基金限額"),

    MISSION_COMPLETE_LIST_CACHE_KEY("任務完成列表"),

    IP_LOCATION_CACHE_KEY("國別IP表");

    /** 說明 */
    private String memo;

    /** 是否為漸進式快取 */
    private boolean incremental;

    GlobalCacheKey(String memo) {
        this.memo = memo;
        this.incremental = false;
    }

    GlobalCacheKey(String memo, boolean incremental) {
        this.memo = memo;
        this.incremental = incremental;
    }

    @Override
    public String getMemo() {
        return this.memo;
    }

    public boolean isIncremental() {
        return incremental;
    }
}
