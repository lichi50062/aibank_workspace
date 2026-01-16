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
package com.tfb.aibank.chl.component.missionwall;

import java.io.Serializable;

// @formatter:off
/**
 * @(#)MissionCompleteListModel.java
 * 
 * <p>Description:任務完成列表 Model</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/09/09, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class MissionCompleteList implements Serializable {

    private static final long serialVersionUID = 1L;

    public MissionCompleteList() {
        // default constructor
    }

    /**
     * 任務代碼
     */
    private String missionCode;

    /**
     * 頁面代號 ex : NDSTX003_050
     */
    private String pageId;

    /**
     * 前置任務等級
     */
    private String missionLevel;

    /**
     * 交易代號
     */
    private String taskId;

    /**
     * 取得任務代碼
     * 
     * @return String 任務代碼
     */
    public String getMissionCode() {
        return this.missionCode;
    }

    /**
     * 設定任務代碼
     * 
     * @param missionCode
     *            要設定的任務代碼
     */
    public void setMissionCode(String missionCode) {
        this.missionCode = missionCode;
    }

    /**
     * 取得前置任務等級
     * 
     * @return String 前置任務等級
     */
    public String getMissionLevel() {
        return this.missionLevel;
    }

    /**
     * 設定前置任務等級
     * 
     * @param missionLevel
     *            要設定的前置任務等級
     */
    public void setMissionLevel(String missionLevel) {
        this.missionLevel = missionLevel;
    }

    /**
     * 取得頁面代號
     * 
     * @return String 頁面代號
     */
    public String getPageId() {
        return this.pageId;
    }

    /**
     * 設定頁面代號
     * 
     * @param pageId
     *            要設定的頁面代號
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    /**
     * 取得交易代號
     * 
     * @return String 交易代號
     */
    public String getTaskId() {
        return this.taskId;
    }

    /**
     * 設定交易代號
     * 
     * @param taskId
     *            要設定的交易代號
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
