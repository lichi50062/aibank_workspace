package com.tfb.aibank.chl.creditcard.ag013.model;

import com.ibm.tw.commons.type.IEnum;

import java.util.HashMap;
import java.util.Map;

// @formatter:off
/**
 * @(#)NCCAG013Fido2APIEnum.java
 * 
 * <p>Description:FIDO2 API 查詢會員狀態Enum</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/6/04, billchang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public enum NCCAG013Fido2APIEnum implements IEnum {
    SUCCESS("0", "成功"),
    ATTESTATION_STATUS_SUCCESS("0", "已綁定"),
    USERNAME_NOT_EXISTS("1002","使用者不存在"),
    STATUS_OK("ok","ok"),
    STATUS_FAIL("fail", "fail"),
    ACCESS_DENIED("VeriFIDO-A001", "取得授權失敗，無法取得 API Key"),
    TOKEN_VALIDATE_FAILURE("VeriFIDO-A002", "授權檢核失敗，API Key 驗證失敗"),
    READ_LOAD_ARCHIVE_FILE_FAILED("VeriFIDO-B001", "讀取檔案失敗"),
    PARAMETER_DATA_NOT_FOUND("VeriFIDO-B002", "電文參數異常，JSON 欄位重複"),
    PARAMETER_DATA_FORMAT_ERROR("VeriFIDO-B003", "電文參數轉換異常，JSON 轉換錯誤"),
    CONNECTION_TIMED_OUT("VeriFIDO-D001", "連線逾時"),
    CONNECTION_FAILURE("VeriFIDO-D002", "連線失敗"),
    MESSAGE_NOT_RECOGNIZED("VeriFIDO-E001", "result 電文不合法，RpTxId & TxId 與 option 電文不一致"),
    TXID_NOT_RECOGNIZED("VeriFIDO-E002", "TxId 不合法"),
    RPID_NOT_RECOGNIZED("VeriFIDO-E003", "RpId 不存在、未註冊完成、已停用"),
    RPMEMBER_NOT_RECOGNIZED("VeriFIDO-E004", "該使用者已被停用"),
    REGISTRATION_USERNAME_NOT_RECOGNIZED("VeriFIDO-E005", "電文 Username 與 Credential 資料不符合\n"),
    REQUIRED_TOKEN_DATA_ELEMENT_MISSING("VeriFIDO-F001", "授權 Token 資料不存在"),
    REQUIRED_PAYLOAD_DATA_ELEMENT_MISSING("VeriFIDO-F002", "電文缺少必要欄位"),
    DUPLICATE_DATA_ELEMENT("VeriFIDO-F003", "電文包含重複欄位"),
    TRANSACTION_TIMED_OUT("VeriFIDO-G001", "交易逾時"),
    TRANSIENT_SYSTEM_OVERLOAD("VeriFIDO-Z997", "瞬時系統過載"),
    TRANSIENT_SYSTEM_FAILURE("VeriFIDO-Z998", "瞬時系統故障"),
    PERMANENT_SYSTEM_FAILURE("VeriFIDO-Z999", "永久性系統故障");

    private static final Map<String, NCCAG013Fido2APIEnum> codeMap = new HashMap<>();

    static {
        for (NCCAG013Fido2APIEnum e : values()) {
            codeMap.put(e.code, e);
        }
    }

    private String code;
    private String memo;

    NCCAG013Fido2APIEnum(String code, String memo) {
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
        NCCAG013Fido2APIEnum match = codeMap.get(code);
        return match != null ? match.getMemo() : "未知錯誤";
    }
}
