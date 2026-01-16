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
package com.tfb.aibank.chl.component.mfdpromocode;

import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

// @formatter:off
/**
 * @(#)FundProjectDiscount.java
 * 
 * <p>Description:FundProjectDiscount 專案優惠查詢  Repeat</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/31,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FundProjectDiscount implements Serializable {

    private static final long serialVersionUID = 1935048973574105143L;

    /** 有效期間起 */
    private String projectSDate;
    private Date projectStartDate;
    /** 有效期間迄 */
    private String projectEDate;
    private Date projectEndDate;
    /** 優惠活動代碼 */
    private String projectCode;
    /** 優惠次數 */
    private String count;
    /** 已使用次數 */
    private String usedCount;
    /** 專案名稱 */
    private String projectName;
    /** 專案顯示用名稱 */
    private String projectDesc;
    /** 專案名稱 */
    private boolean plateformDiscount;
    /**
     * 申購方式
     * 單筆: 1, 非單筆: 2
     * null => 此筆是電文資料
     * */
    private Integer purchaseWay;

    private String locale;

    public String getProjectSDate() {
        return projectSDate;
    }

    public void setProjectSDate(String projectSDate) {
        this.projectSDate = projectSDate;
    }

    public String getProjectEDate() {
        return projectEDate;
    }

    public void setProjectEDate(String projectEDate) {
        this.projectEDate = projectEDate;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(String usedCount) {
        this.usedCount = usedCount;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public boolean isPlateformDiscount() {
        return plateformDiscount;
    }

    public void setPlateformDiscount(boolean plateformDiscount) {
        this.plateformDiscount = plateformDiscount;
    }

    public Integer getPurchaseWay() {
        return purchaseWay;
    }

    public void setPurchaseWay(Integer purchaseWay) {
        this.purchaseWay = purchaseWay;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FundProjectDiscount.class.getSimpleName() + "[", "]")
                .add("locale='" + locale + "'")
                .add("count='" + count + "'")
                .add("projectCode='" + projectCode + "'")
                .add("projectDesc='" + projectDesc + "'")
                .add("projectName='" + projectName + "'")
                .add("purchaseWay=" + purchaseWay)
                .add("usedCount='" + usedCount + "'")
                .toString();
    }
}
