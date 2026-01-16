package com.tfb.aibank.chl.common.pdf.declarationform.model;

import com.tfb.aibank.chl.common.pdf.declarationform.model.DeclarationForm.DeclarationPageEnum;

//@formatter:off
/**
* @(#)DeclarationFormEnum_20230701_SECOND.java
* 
* <p>Description:第二聯 (申報義務人留存備查)</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/20, Evan Wang  
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum DeclarationFormEnum_20230701_SECOND implements IDeclarationFormEnum {
    //@formatter:off
    CHECK_OPTION_1("checkbox", "國外匯入直接結售", 59.2f, 745.5f),
    CHECK_OPTION_2("checkbox", "國內他行匯入結售", 193.69f, 745.5f, 2.5f, 0f),
    CHECK_OPTION_3("checkbox", "由外匯存款提出結售", 346.18f, 745.5f, -2.5f, 0f),
    CHECK_OPTION_4("checkbox", "以外幣現鈔或旅行支票結售", 59.2f, 725.5f),
    CHECK_OPTION_5("checkbox", "其他(請說明)", 225.2f, 725.5f, 15f, 0f),
    CHECK_OPTION_5_CONTENT("text", "其他-說明內容", 341.18f, 720.5f),
    APPLY_DATE_YEAR("text", "申報日期(年)", 210.77f, 701.38f),
    APPLY_DATE_MONTH("text", "申報日期(月)", 265.77f, 701.38f),
    APPLY_DATE_DATE("text", "申報日期(日)", 317.77f, 701.38f),
    DECLARANT_NAME("text", "申報義務人", 200.77f, 685.38f),
    DECLARANT_ID_TYPE_1("checkbox", "申報義務人登記證號-(公司、有限合夥、行號:統一編號)", 80.8f,656.38f),
    DECLARANT_ID_TYPE_1_UNIFORM_NO("text", "公司、有限合夥、行號:統一編號", 100.8f,643.38f),
    DECLARANT_ID_TYPE_2("checkbox", "申報義務人登記證號-(團體、辦事處及事務所)", 80.7f,630.38f),
    DECLARANT_ID_TYPE_2_UNIFORM_NO("text", "統一編號", 215.77f,617.38f),
    DECLARANT_ID_TYPE_2_OTHERS_WITHOUT_UNIFORM_NO("text", "其他無統一編號者:設立登記主管機關", 283.77f,604.38f),
    DECLARANT_ID_TYPE_2_REGISTRATION_NO("text", "登記證號", 283.77f,581.38f),
    DECLARANT_ID_TYPE_3("checkbox", "申報義務人登記證號-個人(國民身分證、居留證效期一年以上)", 80.7f,565.38f),
    DECLARANT_ID_TYPE_3_ID_NO("checkbox", "申報義務人登記證號-身分證號或統一證號", 253.77f,541.38f),
    DECLARANT_ID_TYPE_3_BIRTH_YEAR("text", "申報義務人登記證號-出生日期(年)", 216.77f, 529.38f),
    DECLARANT_ID_TYPE_3_BIRTH_MONTH("text", "申報義務人登記證號-出生日期(月)", 273.77f, 529.38f),
    DECLARANT_ID_TYPE_3_BIRTH_DATE("text", "申報義務人登記證號-出生日期(日)", 325f, 529.38f),
    DECLARANT_ID_TYPE_3_ISSUANCE_YEAR("text", "申報義務人登記證號-居留證發給日期(年)", 259f, 516.38f),
    DECLARANT_ID_TYPE_3_ISSUANCE_MONTH("text", "申報義務人登記證號-居留證發給日期(月)", 316f, 516.38f),
    DECLARANT_ID_TYPE_3_ISSUANCE_DATE("text", "申報義務人登記證號-居留證發給日期(日)", 367.23f, 516.38f),
    DECLARANT_ID_TYPE_3_EXPIRY_YEAR("text", "申報義務人登記證號-居留證到期日期(年)", 259f, 503.38f),
    DECLARANT_ID_TYPE_3_EXPIRY_MONTH("text", "申報義務人登記證號-居留證到期日期(月)", 316f, 503.38f),
    DECLARANT_ID_TYPE_3_EXPIRY_DATE("text", "申報義務人登記證號-居留證到期日期(日)", 367.23f, 503.38f),
    DECLARANT_ID_TYPE_4("checkbox", "申報義務人登記證號-非居住民(護照、入出境許可相關證明文件、居留證效期未滿一年)", 80.7f,487.38f),
    DECLARANT_ID_TYPE_4_BIRTH_YEAR("text", "申報義務人登記證號-非居住民-出生日期(年)", 216.77f, 464.38f),
    DECLARANT_ID_TYPE_4_BIRTH_MONTH("text", "申報義務人登記證號-非居住民-出生日期(月)", 273.77f, 464.38f),
    DECLARANT_ID_TYPE_4_BIRTH_DATE("text", "申報義務人登記證號-非居住民-出生日期(日)", 325f, 464.38f),
    DECLARANT_ID_TYPE_4_NATIONALITY("text", "申報義務人登記證號-非居住民-國別", 196.77f, 451.38f),
    DECLARANT_ID_TYPE_4_PASSPORT("text", "申報義務人登記證號-非居住民-護照或證照號碼", 296.77f, 438.38f),
    DECLARANT_ID_TYPE_4_ISSUANCE_YEAR("text", "申報義務人登記證號-非居住民-居留證發給日期(年)", 259f, 425.38f),
    DECLARANT_ID_TYPE_4_ISSUANCE_MONTH("text", "申報義務人登記證號-非居住民-居留證發給日期(月)", 316f, 425.38f),
    DECLARANT_ID_TYPE_4_ISSUANCE_DATE("text", "申報義務人登記證號-非居住民-居留證發給日期(日)", 367.23f, 425.38f),
    DECLARANT_ID_TYPE_4_EXPIRY_YEAR("text", "申報義務人登記證號-非居住民-居留證到期日期(年)", 259f, 412.38f),
    DECLARANT_ID_TYPE_4_EXPIRY_MONTH("text", "申報義務人登記證號-非居住民-居留證到期日期(月)", 316f, 412.38f),
    DECLARANT_ID_TYPE_4_EXPIRY_DATE("text", "申報義務人登記證號-非居住民-居留證到期日期(日)", 367.23f, 412.38f),
    DECLARANT_RECEIPTS_OF_TRANSACTIONS_1("checkbox", "外匯收入或交易性質-出口貨品收入或對非居住民提供服務收入(勾選)", 80.7f,376.38f),
    DECLARANT_RECEIPTS_OF_TRANSACTIONS_1_CONTENT("text", "外匯收入或交易性質-出口貨品收入或對非居住民提供服務收入(內容)", 93.7f,343.38f),
    DECLARANT_RECEIPTS_OF_TRANSACTIONS_2("checkbox", "外匯收入或交易性質-其他匯入款項(勾選)", 80.7f,328.38f),
    DECLARANT_RECEIPTS_OF_TRANSACTIONS_2_CONTENT("text", "外匯收入或交易性質-其他匯入款項(內容)", 93.7f,307.38f),
    ANNOTATION_REMITTANCE("text", "右方註釋＠匯入/匯出", 429f,320.38f),
    ANNOTATION_IDTYPE("text", "右方註釋＠身份別 統一編號", 429f,310.38f),
    ANNOTATION_AMOUNT_WITHHELD("text", "右方註釋＠幣別 本次查扣金額", 429f,300.38f),
    ANNOTATION_WITHHELD_RESULT("text", "右方註釋＠查扣結果", 429f,290.38f),
    ANNOTATION_SUBMIT_NO("text", "右方註釋＠送件編號 字軌 日期", 429f,280.38f),
    ANNOTATION_CATEGORY("text", "右方註釋@結匯匯款分類 細分類 原匯款分類", 429f,270.38f),
    AMOUNT_REMITTED("text", "匯款金額", 192.77f,288.38f),
    REMITTED_FROM_COUNTRY("text", "匯款地區國別", 236.77f,270.38f),
    DECLARANT_SIGNATURE("text", "申報義務人(電子簽章)", 58.2f,178.38f),
    ADDRESS("text", "地址", 58.2f,131.88f),
    TELEPHONE("text", "電話", 300.2f,143.38f),
    CASE_NO("text", "送件編號", 138.2f,90.38f),
    EXCHANGE_NO("text", "外匯水單編號", 226.2f,77.38f),
    BANKING_DSF_SEAL_AND_DATE("text", "銀行業或外匯證券商簽章及日期", 376.2f,77.38f),
    BANKING_SIGNATURE("text", "銀行業或外匯證券商負責輔導申報義務人員簽章", 472.2f, 178.38f)
    ;
    
    //@formatter:on
    private static final float GLOBAL_OFFSET_X_FOR_SETTLEMENT_S = 0f;

    private static final float GLOBAL_OFFSET_X_FOR_SETTLEMENT_B = 0f;

    private static final float GLOBAL_OFFSET_Y_FOR_SETTLEMENT_S = 2f;

    private static final float GLOBAL_OFFSET_Y_FOR_SETTLEMENT_B = 20f;

    private final String DOC_VER = "20230701";

    private final DeclarationPageEnum DECLARATION_PAGE = DeclarationPageEnum.SECOND;

    private final String type;

    private final String desc;

    private final float x;

    private final float y;

    private final float offxForSettlmentB;

    private final float offyForSettlmentB;

    private DeclarationFormEnum_20230701_SECOND(String type, String desc, float x, float y) {
        this.type = type;
        this.desc = desc;
        this.x = x;
        this.y = y;
        this.offxForSettlmentB = 0;
        this.offyForSettlmentB = 0;
    }

    /**
     * 
     * @return
     */
    private DeclarationFormEnum_20230701_SECOND(String type, String desc, float x, float y, float offxForSettlmentB, float offyForSettlmentB) {
        this.type = type;
        this.desc = desc;
        this.x = x;
        this.y = y;
        this.offxForSettlmentB = offxForSettlmentB;
        this.offyForSettlmentB = offyForSettlmentB;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getX(String settlement) {
        float positionx = 0f;
        if ("S".equalsIgnoreCase(settlement)) {
            positionx = GLOBAL_OFFSET_X_FOR_SETTLEMENT_S + x;
        }
        else if ("B".equalsIgnoreCase(settlement)) {
            // 如果為結售則返回offset的值
            positionx = GLOBAL_OFFSET_X_FOR_SETTLEMENT_B + x + offxForSettlmentB;
        }
        return positionx;
    }

    @Override
    public float getY(String settlement) {
        float positiony = 0f;
        if ("S".equalsIgnoreCase(settlement)) {
            positiony = GLOBAL_OFFSET_Y_FOR_SETTLEMENT_S + y;
        }
        else if ("B".equalsIgnoreCase(settlement)) {
            // 如果為結售則返回offset的值
            positiony = GLOBAL_OFFSET_Y_FOR_SETTLEMENT_B + y + offyForSettlmentB;
        }
        return positiony;
    }

    @Override
    public boolean isThisDocVersion(String docVer, DeclarationPageEnum page) {
        return DOC_VER.equals(docVer) && DECLARATION_PAGE == page;
    }
}
