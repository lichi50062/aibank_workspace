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
package com.ibm.tw.ibmb.base.model;

// @formatter:off
/**
 * @(#)RsDataExtension.java
 * 
 * <p>Description:下傳資料的擴充抽象物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/09/09, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public abstract class RsDataExtension implements RsData {

    /** 是否進行「任務牆任務更新」 */
    private boolean execUpdateMissionWall;

    public boolean isExecUpdateMissionWall() {
        return execUpdateMissionWall;
    }

    public void setExecUpdateMissionWall(boolean execUpdateMissionWall) {
        this.execUpdateMissionWall = execUpdateMissionWall;
    }

}
