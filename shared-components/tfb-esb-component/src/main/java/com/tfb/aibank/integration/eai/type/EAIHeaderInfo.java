package com.tfb.aibank.integration.eai.type;

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)EAIHeaderInfo.java
 * 
 * <p>Description:電文表頭(HTLID、HDRVQ1...)預設資訊</p>
 * <p>添加內容，請依照名稱升幂排序</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/20, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum EAIHeaderInfo implements IEnum {

    AML002("2004112", "", "是否「虛擬貨幣交易平台之境外帳戶」查詢(AML002)"),

    CIP121159("2004011", "", "各類通知事項申請及維護"),

    EB032280("2004111", "", "客戶辦理投資有價證券資訊提供聲明書維護"),

    EB032151("2004215", "", "客戶基本資料維護(非臨櫃)(EB032151)"),

    EB032179("2004011", "", "優惠次數查詢╱修改上行電文(EB032179)"),

    EB032333("2004012", "", ""),

    EB12010026("2004111", "", "約定自動扣繳設定(EB12010026)"),

    EB12010029("2004111", "", "約定自動扣繳設定(EB12010029)"),

    EB12020002("2004215", "", "零存整付定存明細查詢"),

    EB12020003("4112", "", "外幣定存續約變更", true),

    EB12020011("2004111", "", "數位存款開戶"),

    EB122351("2004111", "", "查詢約定自動扣繳資訊(EB122351)"),

    EB141153("4112", "", "定存到期預約設定╱變更", true),

    EB172650("2004215", "", "查詢ID(EB172650)"),

    EB512167("2004215", "", "無卡提款功能維護"),

    EB550912("4112", "", "綜合存款轉存綜合定期性存款", true),

    EB550922("4112", "", "外幣即時定存解約", true),

    EB552175("2004111", "", "查詢「簽訂基金電子契約」(EB552175)"),

    EB553910A("4112", "", "預約-繳自行信用卡費", true),

    EB553911("4112", "", "綜合存款轉存綜合定期性存款", true),

    EB553921("4112", "", "綜合存款轉存綜合定期性存款（外幣）", true),

    EB553955("4112", "", "台轉外、外轉台、外轉外即時", true),

    EB555789("2004215", "", "取得貸款帳號(EB555789)"),

    EB5557891("2004215", "", ""),

    EB612173("2004111", "", "網銀國外匯款匯款申請資料建檔及維護(EB612173)"),

    EB613930("4112", "", "預約轉帳", true),

    EB620968("2004111", "", "查詢匯款進度(EB620968)"),

    EB622676("2004111", "", "查詢行內匯款性質"),

    EB94("2004233", "", "網路銀行外匯扣帳電文(EB94)"),

    EBEAGLEEYE("2004112", "", "分期防詐名單收集及檢核(EBEAGLEEYE)"),

    EBFRE1("2004011", "", "外幣匯入款查詢"),

    EBHN002("2004215", "", "房貸可增貸額度"),

    EBLN010("2004012", "", "檢核是否符合速貸通資格"),

    FC032155("2004111", "", "本行客戶註記上行電文"),

    GD320140("2004115", "", ""),

    GWAPI051("2004011", "", "查詢他行戶名(GWAPI051)"),

    MVC110001("028WEB", "", "EMAIL驗證平台電文"),

    NF032333("2004233", "", "查詢帳號所屬分行代碼(NF032333)"),

    NJW020("028WEB", "NJMOBHQ", "投資類交易(債券、結構型商品)營業時間交易(NJW020)"),

    NJWEEA42("", "", "新增結構型商品交易金額限制檢核電文(NJWEEA42)"),

    NMP8YB("028WEB", "", "奈米投電文"),

    OA000403("2004011", "", "查詢本行戶名(OA000403)"),

    VN084N("2004111", "", "基金資產總覽"),

    ULCQRCODE("2004215", "", "金融卡解鎖序號(ULCQRCODE)"),

    UNKNOWN(UNKNOWN_STR_CODE, UNKNOWN_STR_CODE, "未知");

    /** 櫃代 */
    private String HTLID;
    /** */
    private String HDRVQ1;
    /** 說明 */
    private String memo;
    /** 自定義「櫃代」前綴 */
    private boolean isCombined;

    EAIHeaderInfo(String HTLID, String HDRVQ1, String memo) {
        this.HTLID = HTLID;
        this.HDRVQ1 = HDRVQ1;
        this.memo = memo;
        this.isCombined = false;
    }

    EAIHeaderInfo(String HTLID, String HDRVQ1, String memo, boolean isCombined) {
        this.HTLID = HTLID;
        this.HDRVQ1 = HDRVQ1;
        this.memo = memo;
        this.isCombined = isCombined;
    }

    public String getHTLID() {
        return HTLID;
    }

    public String getHDRVQ1() {
        return HDRVQ1;
    }

    @Override
    public String getMemo() {
        return memo;
    }

    /**
     * 判斷是否需要自定義櫃代前綴
     * 
     * @return
     */
    public boolean isCombined() {
        return this.isCombined;
    }
}
