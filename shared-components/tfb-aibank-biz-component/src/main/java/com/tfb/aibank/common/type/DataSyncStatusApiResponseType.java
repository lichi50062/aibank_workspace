/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.common.type;

import java.util.Arrays;

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

import static com.tfb.aibank.common.type.UpdateDataSyncStatusType.UPDATE_INSUR_TYPE;
import static com.tfb.aibank.common.type.UpdateDataSyncStatusType.UPDATE_SECUR_TYPE;

//@formatter:off
/**
* @(#)DataSyncStatusApiResponseType.java
* 
* <p>Description: 更新資料彙整 api response return codes</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/04/23, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum DataSyncStatusApiResponseType implements IEnum {

    // 富壽
    SUCCESS_INSUR("0000", "成功"),

    SYSTEM_ERROR("1001", "系統錯誤"),

    INVALID_SYSTEM_CODE("1002", "無效的系統代號"),

    INSUFFICIENT_PARAMS("1003", "參數不足"),

    AUTH_TOKEN_EXPIRED("1004", "驗證碼逾期"),

    INVALID_AUTH_TOKEN("1005", "無效的驗證碼"),

    NON_CUSTOMER("1006", "非客戶"),

    // 富證
//    SUCCESS_SECUR("S000", "更新成功（同意/取消）"),

    INVALID_PARAM_FORMAT("E901", "傳入參數格式不符"),

//    NOT_AGREEMENT_SYSTEM_TYPE("E902", "非約定系統別"),

    DATA_ANOMALY("E903", "資料異常"),

    FAILED_SECUR("E904", "更新失敗（同意/取消）"),

//    CUSTOMER_ACCOUNT_VALIDATION_FAILED("E401", "客戶帳號檢核失敗(非證券戶)"),

    UNKNOWN_ERROR("E999", "異常"),

    VALIDATION_FAILED("X401", "驗證未通過"),

    // 富證 V2
    SUCCESS_SECUR("S000", "更新成功（同意/取消）"),
    SYSTEM_ERROR_SECUR("QASS9999", "系統錯誤"),
    NOT_AGREEMENT_SYSTEM_TYPE("QASB0011", "非約定系統別"),
    CUSTOMER_ACCOUNT_VALIDATION_FAILED("QASB0021", "未有證券、複委託、國內外期貨任一帳戶"),
    TOKEN_EXPIRED_OR_INVALID("QASB0001", "External JWT 驗證失敗"),
    FIELD_REQUIRED("QASF9005", "欄位名稱不可為空"),
    DATABASE_ACCESS_ERROR("QASF9009", "資料庫存取錯誤");

    private String code;

    private String memo;

    private DataSyncStatusApiResponseType(String code, String memo) {
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

    public static DataSyncStatusApiResponseType findByCode(String code) {
        return Arrays.stream(values()).filter(syncType -> StringUtils.equals(syncType.getCode(), code)).findFirst().orElse(null);
    }
}
