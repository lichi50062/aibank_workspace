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
package com.tfb.aibank.chl.system.resource.dto;

import java.io.Serializable;
import java.util.Date;

// @formatter:off
/**
 * @(#)AnnouncementModel.java
 * 
 * <p>Description:AI BANK系統公告管理 Model</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/26, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class Announcement implements Serializable {

    private static final long serialVersionUID = 8953753183361895884L;

    /** 資料鍵值 */
    private Integer annoKey;

    /** 系統公告內容 */
    private String annoContent;

    /** 系統公告標題 */
    private String annoTitle;

    /** 建立日期 */
    private Date createTime;

    /** 結束日 */
    private Date endDate;

    /** 開始日 */
    private Date startDate;

    /** 更新日期 */
    private Date updateTime;

    public Integer getAnnoKey() {
        return annoKey;
    }

    public void setAnnoKey(Integer annoKey) {
        this.annoKey = annoKey;
    }

    public String getAnnoContent() {
        return annoContent;
    }

    public void setAnnoContent(String annoContent) {
        this.annoContent = annoContent;
    }

    public String getAnnoTitle() {
        return annoTitle;
    }

    public void setAnnoTitle(String annoTitle) {
        this.annoTitle = annoTitle;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
