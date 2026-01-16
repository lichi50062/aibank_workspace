package com.tfb.aibank.chl.creditcard.ag013.model;

import com.ibm.tw.commons.type.IEnum;

import java.util.HashMap;
import java.util.Map;
// @formatter:off
/**
 * @(#)NCCAG013Fido2SDKEnum.java
 * 
 * <p>Description:FIDO2 SDK errorCode Enum</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/6/26, billchang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public enum NCCAG013Fido2SDKEnum implements IEnum {
    SUCCESS_0("0", "成功"),
    SUCCESS_1200("1200", "成功"),

    FAIL_3("3", "物辨識取消"), // 與 9981 相同

    FAIL_6("6", "生物辨識被鎖定"), // 與 9985 相同

    FAIL_7("7", "Facet id 認證失敗"),

    FAIL_1403("1403", "無此裝置註冊資訊"), //伺服器端裝置已被移除

    FAIL_1404("1404", "無此裝置註冊資訊"), //伺服器端裝置已被移除

    FAIL_9981("9981", "生物辨識取消"),

    FAIL_9982("9982", "生物辨識失敗"),

    FAIL_9984("9984", "讀取密鑰失敗"),

    FAIL_9985("9985", "生物辨識被鎖定"),

    FAIL_9991("9991", "未知的錯誤"),

    FAIL_9992("9992", "JSON 解析失敗"),

    FAIL_9993("9993", "輸入參數不正確"),

    FAIL_9994("9994", "生物辨識未設定"),

    FAIL_9996("9996", "網路連線失敗"),
    // 以下錯誤desc皆為英文, 回傳的memo都改為未知錯誤替代
    FAIL_1001("1001", "未知錯誤"),
    FAIL_1002("1002", "未知錯誤"),
    FAIL_1003("1003", "未知錯誤"),
    FAIL_1004("1004", "未知錯誤"),
    FAIL_1005("1005", "未知錯誤"),
    FAIL_1006("1006", "未知錯誤"),
    FAIL_1007("1007", "未知錯誤"),
    FAIL_1011("1011", "未知錯誤"),
    FAIL_1012("1012", "未知錯誤"),
    FAIL_1013("1013", "未知錯誤"),
    FAIL_1021("1021", "未知錯誤"),
    FAIL_1022("1022", "未知錯誤"),
    FAIL_1023("1023", "未知錯誤"),
    FAIL_1024("1024", "未知錯誤"),
    FAIL_1031("1031", "未知錯誤"),
    FAIL_1032("1032", "未知錯誤"),
    FAIL_1101("1101", "未知錯誤"),
    FAIL_1102("1102", "未知錯誤"),
    FAIL_1103("1103", "未知錯誤"),
    FAIL_1104("1104", "未知錯誤"),
    FAIL_1111("1111", "未知錯誤"),
    FAIL_1112("1112", "未知錯誤"),
    FAIL_1113("1113", "未知錯誤"),
    FAIL_1115("1115", "未知錯誤"),
    FAIL_1121("1121", "未知錯誤"),
    FAIL_1122("1122", "未知錯誤"),
    FAIL_1131("1131", "未知錯誤"),
    FAIL_1132("1132", "未知錯誤"),
    FAIL_1133("1133", "未知錯誤"),
    FAIL_1141("1141", "未知錯誤"),
    FAIL_1142("1142", "未知錯誤"),
    FAIL_1143("1143", "未知錯誤"),
    FAIL_1151("1151", "未知錯誤"),
    FAIL_1152("1152", "未知錯誤"),
    FAIL_1153("1153", "未知錯誤"),
    FAIL_1155("1155", "未知錯誤"),
    FAIL_1161("1161", "未知錯誤"),
    FAIL_1162("1162", "未知錯誤"),
    FAIL_1163("1163", "未知錯誤"),
    FAIL_1164("1164", "未知錯誤"),
    FAIL_1165("1165", "未知錯誤"),
    FAIL_1171("1171", "未知錯誤"),
    FAIL_1172("1172", "未知錯誤"),
    FAIL_1173("1173", "未知錯誤"),
    FAIL_1175("1175", "未知錯誤"),
    FAIL_1181("1181", "未知錯誤"),
    FAIL_1182("1182", "未知錯誤"),
    FAIL_1183("1183", "未知錯誤"),
    FAIL_1191("1191", "未知錯誤"),
    FAIL_1195("1195", "未知錯誤"),
    FAIL_1201("1201", "未知錯誤"),
    FAIL_1202("1202", "未知錯誤"),
    FAIL_1203("1203", "未知錯誤"),
    FAIL_1205("1205", "未知錯誤"),
    FAIL_1211("1211", "未知錯誤"),
    FAIL_1212("1212", "未知錯誤"),
    FAIL_1213("1213", "未知錯誤"),
    FAIL_1221("1221", "未知錯誤"),
    FAIL_1222("1222", "未知錯誤"),
    FAIL_1225("1225", "未知錯誤"),
    FAIL_1231("1231", "未知錯誤"),
    FAIL_1232("1232", "未知錯誤"),
    FAIL_1233("1233", "未知錯誤"),
    FAIL_1241("1241", "未知錯誤"),
    FAIL_1242("1242", "未知錯誤"),
    FAIL_1243("1243", "未知錯誤"),
    FAIL_1245("1245", "未知錯誤"),
    FAIL_1251("1251", "未知錯誤"),
    FAIL_1252("1252", "未知錯誤"),
    FAIL_1253("1253", "未知錯誤"),
    FAIL_1265("1265", "未知錯誤"),
    FAIL_1272("1272", "未知錯誤"),
    FAIL_1281("1281", "未知錯誤"),
    FAIL_1282("1282", "未知錯誤"),
    FAIL_1283("1283", "未知錯誤"),
    FAIL_1284("1284", "未知錯誤"),
    FAIL_1295("1295", "未知錯誤"),
    FAIL_1296("1296", "未知錯誤"),
    FAIL_1297("1297", "未知錯誤"),
    FAIL_1298("1298", "未知錯誤"),
    FAIL_1299("1299", "未知錯誤"),
    FAIL_1300("1300", "未知錯誤"),
    FAIL_1311("1311", "未知錯誤"),
    FAIL_1312("1312", "未知錯誤"),
    FAIL_1313("1313", "未知錯誤"),
    FAIL_1314("1314", "未知錯誤"),
    FAIL_1315("1315", "未知錯誤"),
    FAIL_1322("1322", "未知錯誤"),
    FAIL_1331("1331", "未知錯誤"),
    FAIL_1341("1341", "未知錯誤"),
    FAIL_1342("1342", "未知錯誤"),
    FAIL_1343("1343", "未知錯誤"),
    FAIL_1344("1344", "未知錯誤"),
    FAIL_1351("1351", "未知錯誤"),
    FAIL_1352("1352", "未知錯誤"),
    FAIL_1353("1353", "未知錯誤"),
    FAIL_1361("1361", "未知錯誤"),
    FAIL_1362("1362", "未知錯誤"),
    FAIL_1363("1363", "未知錯誤"),
    FAIL_1364("1364", "未知錯誤"),
    FAIL_1365("1365", "未知錯誤"),
    FAIL_1366("1366", "未知錯誤"),
    FAIL_1367("1367", "未知錯誤"),
    FAIL_1371("1371", "未知錯誤"),
    FAIL_9971("9971", "未知錯誤"),
    FAIL_9997("9997", "未知錯誤"),
    FAIL_9998("9998", "未知錯誤"),
    FAIL_9999("9999", "未知錯誤"),

    // 以下錯誤皆為 FIDO2 API 回傳錯誤, 為方便區分回傳來源, 則把SDK回傳的memo都改為未知錯誤
    ACCESS_DENIED("VeriFIDO-A001", "未知錯誤"),
    TOKEN_VALIDATE_FAILURE("VeriFIDO-A002", "未知錯誤"),
    READ_LOAD_ARCHIVE_FILE_FAILED("VeriFIDO-B001", "未知錯誤"),
    PARAMETER_DATA_NOT_FOUND("VeriFIDO-B002", "未知錯誤"),
    PARAMETER_DATA_FORMAT_ERROR("VeriFIDO-B003", "未知錯誤"),
    CONNECTION_TIMED_OUT("VeriFIDO-D001", "未知錯誤"),
    CONNECTION_FAILURE("VeriFIDO-D002", "未知錯誤"),
    MESSAGE_NOT_RECOGNIZED("VeriFIDO-E001", "未知錯誤"),
    TXID_NOT_RECOGNIZED("VeriFIDO-E002", "未知錯誤"),
    RPID_NOT_RECOGNIZED("VeriFIDO-E003", "未知錯誤"),
    RPMEMBER_NOT_RECOGNIZED("VeriFIDO-E004", "未知錯誤"),
    REGISTRATION_USERNAME_NOT_RECOGNIZED("VeriFIDO-E005", "未知錯誤"),
    REQUIRED_TOKEN_DATA_ELEMENT_MISSING("VeriFIDO-F001", "未知錯誤"),
    REQUIRED_PAYLOAD_DATA_ELEMENT_MISSING("VeriFIDO-F002", "未知錯誤"),
    DUPLICATE_DATA_ELEMENT("VeriFIDO-F003", "未知錯誤"),
    TRANSACTION_TIMED_OUT("VeriFIDO-G001", "未知錯誤"),
    TRANSIENT_SYSTEM_OVERLOAD("VeriFIDO-Z997", "未知錯誤"),
    TRANSIENT_SYSTEM_FAILURE("VeriFIDO-Z998", "未知錯誤"),
    PERMANENT_SYSTEM_FAILURE("VeriFIDO-Z999", "未知錯誤");

    private static final Map<String, NCCAG013Fido2SDKEnum> codeMap = new HashMap<>();

    static {
        for (NCCAG013Fido2SDKEnum e : values()) {
            codeMap.put(e.code, e);
        }
    }
    private String code;
    private String memo;

    NCCAG013Fido2SDKEnum(String code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMemo() {
        return memo;
    }

    public static String getMemoByCode(String code) {
        NCCAG013Fido2SDKEnum match = codeMap.get(code);
        return match != null ? match.getMemo() : "未知錯誤";
    }
}
