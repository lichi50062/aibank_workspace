package com.tfb.aibank.biz.component.investhomepage.model;

import java.util.Date;

import com.ibm.tw.ibmb.annotations.FormatDate;

// @formatter:off
/**
 * @(#)FinancialReport.java Data Object
 *
 * <p>Description:研究報告頁籤</p>
 *
 * <p>Modify History:</p>
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FinancialReport {

    private Integer reportKey; // 流水號
    /**
     * 分類，1投資週報;2事件評析;3股票市場;4債券市場;5外匯市場;6預設1;7預設2
     */
    private String category;

    /**
     * 子類別，1成熟市場;2新興市場;3產業;4投等市場;5非投等市場
     */
    private String subCategory;

    @FormatDate
    private Date startTime; // 上架時間

    @FormatDate
    private Date endTime; // 下架時間

    /**
     * 有研究資料
     */
    private boolean haveResearchData;

    /**
     * 理財資訊，FINANCIAL_INFORMATION.INFORMATION_KEY
     **/
    private Integer financialInformationKey;

    // Getters and Setters
    public Integer getReportKey() {
        return reportKey;
    }

    public void setReportKey(Integer reportKey) {
        this.reportKey = reportKey;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
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

    public Integer getFinancialInformationKey() {
        return financialInformationKey;
    }

    public void setFinancialInformationKey(Integer financialInformationKey) {
        this.financialInformationKey = financialInformationKey;
    }

    public boolean isHaveResearchData() {
        return haveResearchData;
    }

    public void setHaveResearchData(boolean haveResearchData) {
        this.haveResearchData = haveResearchData;
    }
}
