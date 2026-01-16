/*
 * =========================================================================== IBM Confidential AIS Source Materials
 *
 *
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.repository.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@formatter:off
/**
* @(#)NightModeEntity.java
* 
* <p>Description:[夜間模式設定檔]</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/22, leiley 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Entity
@Table(name = "HOST_NIGHTMODE")
public class NightModeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系統日
     */
    @Id
    @Column(name = "SYS_DATE")
    private String sysDate;

    /**
     * 系統時間
     */
    @Basic
    @Column(name = "SYS_TIME")
    private String sysTime;

    /**
     * 帳務日
     */
    @Basic
    @Column(name = "POST_DATE")
    private String postDate;

    /**
     * 帳務日之次營業日
     */
    @Basic
    @Column(name = "NEXT_DATE")
    private String nextDate;

    /**
     * D: Day Mode / N: Night Mode
     */
    @Basic
    @Column(name = "REGION_MODE")
    private String regionMode;

    /**
     * Y: Day Mode & Repost 完成
     */
    @Basic
    @Column(name = "REPOST_FLAG")
    private String repostFlag;

    /**
     * 更新日
     */
    @Basic
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 系統日
     * 
     * @return
     */
    public String getSysDate() {
        return sysDate;
    }

    /**
     * 系統日
     * 
     * @param sysDate
     */
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    /**
     * 系統時間
     * 
     * @return
     */
    public String getSysTime() {
        return sysTime;
    }

    /**
     * 系統時間
     * 
     * @param sysTime
     */
    public void setSysTime(String sysTime) {
        this.sysTime = sysTime;
    }

    /**
     * 帳務日
     * 
     * @return
     */
    public String getPostDate() {
        return postDate;
    }

    /**
     * 帳務日
     * 
     * @param postDate
     */
    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    /**
     * 帳務日之次營業日
     * 
     * @return
     */
    public String getNextDate() {
        return nextDate;
    }

    /**
     * 帳務日之次營業日
     * 
     * @param nextDate
     */
    public void setNextDate(String nextDate) {
        this.nextDate = nextDate;
    }

    /**
     * D: Day Mode / N: Night Mode
     * 
     * @return
     */
    public String getRegionMode() {
        return regionMode;
    }

    /**
     * D: Day Mode / N: Night Mode
     * 
     * @param regionMode
     */
    public void setRegionMode(String regionMode) {
        this.regionMode = regionMode;
    }

    /**
     * Y: Day Mode & Repost 完成
     * 
     * @return
     */
    public String getRepostFlag() {
        return repostFlag;
    }

    /**
     * Y: Day Mode & Repost 完成
     * 
     * @param repostFlag
     */
    public void setRepostFlag(String repostFlag) {
        this.repostFlag = repostFlag;
    }

    /**
     * 更新時間
     * 
     * @return
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新時間
     * 
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
