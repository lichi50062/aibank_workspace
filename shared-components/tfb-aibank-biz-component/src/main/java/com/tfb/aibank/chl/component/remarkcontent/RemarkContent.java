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
package com.tfb.aibank.chl.component.remarkcontent;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)RemarkContent.java
 * 
 * <p>Description:文案內容</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "文案內容")
public class RemarkContent implements Serializable {

    private static final long serialVersionUID = -1211838256358706768L;

    /** 資料鍵值 */
    @Schema(description = "資料鍵值")
    private Integer recordKey;

    /**
     * 類型，1:輔助說明；2:備註；3:條款
     */
    @Schema(description = "類型")
    private String remarkType;

    /**
     * 文案鍵值，以大寫命名，如有多個單字以_分隔
     */
    @Schema(description = "文案鍵值")
    private String remarkKey;

    /**
     * 版本
     */
    @Schema(description = "版本")
    private String version;

    /**
     * 生效起始時間
     */
    @Schema(description = "生效起始時間")
    private Date startTime;

    /**
     * 生效結束時間
     */
    @Schema(description = "生效結束時間")
    private Date endTime;

    /**
     * 語系
     */
    @Schema(description = "語系")
    private String locale;

    /**
     * 文案標題
     */
    @Schema(description = "文案標題")
    private String title;

    /**
     * 文案內容
     */
    @Schema(description = "文案內容")
    private String content;

    public String getRemarkType() {
        return remarkType;
    }

    public void setRemarkType(String remarkType) {
        this.remarkType = remarkType;
    }

    public String getRemarkKey() {
        return remarkKey;
    }

    public void setRemarkKey(String remarkKey) {
        this.remarkKey = remarkKey;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Integer getRecordKey() {
        return recordKey;
    }

    public void setRecordKey(Integer recordKey) {
        this.recordKey = recordKey;
    }

}
