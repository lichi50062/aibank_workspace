package com.tfb.aibank.chl.common.pdf.declarationform.model;

public enum DeclarationFormEnum {
    CHECK_OPTION_1("checkbox", "國外匯入直接結售"), CHECK_OPTION_2("checkbox", "國內他行匯入結售"), CHECK_OPTION_3("checkbox", "由外匯存款提出結售"), CHECK_OPTION_4("checkbox", "以外幣現鈔或旅行支票結售"), CHECK_OPTION_5("checkbox", "其他(請說明)"), CHECK_OPTION_5_CONTENT("text", "其他-說明內容"), APPLY_DATE_YEAR("text", "申報日期(年)"), APPLY_DATE_MONTH("text", "申報日期(月)"), APPLY_DATE_DATE("text", "申報日期(日)"), DECLARANT_NAME("text", "申報義務人"), DECLARANT_ID_TYPE_1("checkbox", "申報義務人登記證號-(公司、有限合夥、行號:統一編號)"), DECLARANT_ID_TYPE_1_UNIFORM_NO("text", "公司、有限合夥、行號:統一編號"), DECLARANT_ID_TYPE_2("checkbox", "申報義務人登記證號-(團體、辦事處及事務所)"), DECLARANT_ID_TYPE_2_UNIFORM_NO("text", "統一編號"), DECLARANT_ID_TYPE_2_OTHERS_WITHOUT_UNIFORM_NO("text", "其他無統一編號者:設立登記主管機關"), DECLARANT_ID_TYPE_2_REGISTRATION_NO("text", "登記證號"), DECLARANT_ID_TYPE_3("checkbox", "申報義務人登記證號-個人(國民身分證、居留證效期一年以上)"), DECLARANT_ID_TYPE_3_ID_NO("checkbox", "申報義務人登記證號-身分證號或統一證號"), DECLARANT_ID_TYPE_3_BIRTH_YEAR("text", "申報義務人登記證號-出生日期(年)"), DECLARANT_ID_TYPE_3_BIRTH_MONTH("text", "申報義務人登記證號-出生日期(月)"), DECLARANT_ID_TYPE_3_BIRTH_DATE("text", "申報義務人登記證號-出生日期(日)"), DECLARANT_ID_TYPE_3_ISSUANCE_YEAR("text", "申報義務人登記證號-居留證發給日期(年)"), DECLARANT_ID_TYPE_3_ISSUANCE_MONTH("text", "申報義務人登記證號-居留證發給日期(月)"), DECLARANT_ID_TYPE_3_ISSUANCE_DATE("text", "申報義務人登記證號-居留證發給日期(日)"), DECLARANT_ID_TYPE_3_EXPIRY_YEAR("text", "申報義務人登記證號-居留證到期日期(年)"), DECLARANT_ID_TYPE_3_EXPIRY_MONTH("text", "申報義務人登記證號-居留證到期日期(月)"), DECLARANT_ID_TYPE_3_EXPIRY_DATE("text", "申報義務人登記證號-居留證到期日期(日)"), DECLARANT_ID_TYPE_4("checkbox", "申報義務人登記證號-非居住民(護照、入出境許可相關證明文件、居留證效期未滿一年)"), DECLARANT_ID_TYPE_4_BIRTH_YEAR("text", "申報義務人登記證號-非居住民-出生日期(年)"), DECLARANT_ID_TYPE_4_BIRTH_MONTH("text", "申報義務人登記證號-非居住民-出生日期(月)"), DECLARANT_ID_TYPE_4_BIRTH_DATE("text", "申報義務人登記證號-非居住民-出生日期(日)"), DECLARANT_ID_TYPE_4_NATIONALITY("text", "申報義務人登記證號-非居住民-國別"), DECLARANT_ID_TYPE_4_PASSPORT("text", "申報義務人登記證號-非居住民-護照或證照號碼"), DECLARANT_ID_TYPE_4_ISSUANCE_YEAR("text", "申報義務人登記證號-非居住民-居留證發給日期(年)"), DECLARANT_ID_TYPE_4_ISSUANCE_MONTH("text", "申報義務人登記證號-非居住民-居留證發給日期(月)"), DECLARANT_ID_TYPE_4_ISSUANCE_DATE("text", "申報義務人登記證號-非居住民-居留證發給日期(日)"), DECLARANT_ID_TYPE_4_EXPIRY_YEAR("text", "申報義務人登記證號-非居住民-居留證到期日期(年)"), DECLARANT_ID_TYPE_4_EXPIRY_MONTH("text", "申報義務人登記證號-非居住民-居留證到期日期(月)"), DECLARANT_ID_TYPE_4_EXPIRY_DATE("text", "申報義務人登記證號-非居住民-居留證到期日期(日)"), DECLARANT_RECEIPTS_OF_TRANSACTIONS_1("checkbox", "外匯收入或交易性質-出口貨品收入或對非居住民提供服務收入(勾選)"), DECLARANT_RECEIPTS_OF_TRANSACTIONS_1_CONTENT("text", "外匯收入或交易性質-出口貨品收入或對非居住民提供服務收入(內容)"), DECLARANT_RECEIPTS_OF_TRANSACTIONS_2("checkbox", "外匯收入或交易性質-其他匯入款項(勾選)"), DECLARANT_RECEIPTS_OF_TRANSACTIONS_2_CONTENT("text", "外匯收入或交易性質-其他匯入款項(內容)"), ANNOTATION_REMITTANCE("text", "右方註釋＠匯入/匯出"), ANNOTATION_IDTYPE("text", "右方註釋＠身份別 統一編號"), ANNOTATION_AMOUNT_WITHHELD("text", "右方註釋＠幣別 本次查扣金額"), ANNOTATION_WITHHELD_RESULT("text", "右方註釋＠查扣結果"), ANNOTATION_SUBMIT_NO("text", "右方註釋＠送件編號 字軌 日期"), ANNOTATION_CATEGORY("text", "右方註釋@結匯匯款分類 細分類 原匯款分類"), AMOUNT_REMITTED("text", "匯款金額"), REMITTED_FROM_COUNTRY("text", "匯款地區國別"), DECLARANT_SIGNATURE("text", "申報義務人(電子簽章)"), ADDRESS("text", "地址"), TELEPHONE("text", "電話"), CASE_NO("text", "送件編號"), EXCHANGE_NO("text", "外匯水單編號"), BANKING_DSF_SEAL_AND_DATE("text", "銀行業或外匯證券商簽章及日期"), BANKING_SIGNATURE("text", "銀行業或外匯證券商負責輔導申報義務人員簽章");

    private final String type;

    private final String desc;

    private DeclarationFormEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
