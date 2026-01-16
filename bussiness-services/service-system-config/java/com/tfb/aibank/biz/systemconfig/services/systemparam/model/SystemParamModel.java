package com.tfb.aibank.biz.systemconfig.services.systemparam.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 系統參數
 * 
 * @author Edward Tien
 */
@Schema(description = "系統參數")
public class SystemParamModel {

    /** 分類 */
    @Schema(description = "分類")
    private String category;

    /** 說明 */
    @Schema(description = "說明")
    private String memo;

    /** 鍵值 */
    @Schema(description = "鍵值")
    private String paramKey;

    /** 參數值 */
    @Schema(description = "參數值")
    private String paramValue;

    /** 是否為密碼欄位，0：否；1：是 */
    @Schema(description = "是否為密碼欄位，0：否；1：是")
    private int passwordFlag;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public int getPasswordFlag() {
        return passwordFlag;
    }

    public void setPasswordFlag(int passwordFlag) {
        this.passwordFlag = passwordFlag;
    }

}
