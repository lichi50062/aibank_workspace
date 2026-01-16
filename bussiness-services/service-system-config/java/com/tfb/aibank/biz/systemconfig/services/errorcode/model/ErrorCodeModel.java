package com.tfb.aibank.biz.systemconfig.services.errorcode.model;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)ErrorCodeModel.java
 * 
 * <p>Description:錯誤代碼</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "錯誤代碼")
public class ErrorCodeModel {

    @Schema(description = "錯誤碼系統別")
    private String systemId;

    @Schema(description = "錯誤碼")
    private String errorCode;

    @Schema(description = "頁面代碼")
    private String pageId;

    @Schema(description = "語系")
    private String locale;

    @Schema(description = "嚴重程度")
    private String severity;

    @Schema(description = "錯誤註記，0:引導；1:錯誤")
    private Integer errorFlag;

    @Schema(description = "錯誤標題")
    private String title;

    @Schema(description = "內部錯誤訊息(客服看)")
    private String internalDesc;

    @Schema(description = "外部錯誤訊息(使用者看)")
    private String externalDesc;

    @Schema(description = "顯示方式 0: 顯示DB錯誤訊息 1: 顯示主機錯誤訊息")
    private Integer displayFlag;

    @Schema(description = "資料建立時間")
    private Date createTime;

    @Schema(description = "資料更新時間")
    private Date updateTime;

    @Schema(description = "更新者代碼")
    private Integer updateUserKey;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(Integer displayFlag) {
        this.displayFlag = displayFlag;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getExternalDesc() {
        return externalDesc;
    }

    public void setExternalDesc(String externalDesc) {
        this.externalDesc = externalDesc;
    }

    public String getInternalDesc() {
        return internalDesc;
    }

    public void setInternalDesc(String internalDesc) {
        this.internalDesc = internalDesc;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUserKey() {
        return updateUserKey;
    }

    public void setUpdateUserKey(Integer updateUserKey) {
        this.updateUserKey = updateUserKey;
    }

    public Integer getErrorFlag() {
        return errorFlag;
    }

    public void setErrorFlag(Integer errorFlag) {
        this.errorFlag = errorFlag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

}