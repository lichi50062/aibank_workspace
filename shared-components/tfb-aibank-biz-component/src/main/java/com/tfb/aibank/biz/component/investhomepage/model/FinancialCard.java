package com.tfb.aibank.biz.component.investhomepage.model;
//@formatter:off
import java.util.Date;

import com.ibm.tw.ibmb.annotations.FormatDate; /**
 * @(#)FinancialCard.java
 *
 * <p>Description:關鍵話題</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/05/27, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class FinancialCard extends FinancialCardJsonData {
    private Integer financialInformationKey;
    private String startDateString;
    private FinancialReport financialReport;
    private Integer cardKey;
    @FormatDate
    private Date startTime; // 上架時間
    @FormatDate
    private Date endTime; // 下架時間

    public Integer getFinancialInformationKey() {
        return financialInformationKey;
    }

    public void setFinancialInformationKey(Integer financialInformationKey) {
        this.financialInformationKey = financialInformationKey;
    }

    public String getStartDateString() {
        return startDateString;
    }

    public void setStartDateString(String startDateString) {
        this.startDateString = startDateString;
    }

    public FinancialReport getFinancialReport() {
        return financialReport;
    }

    public void setFinancialReport(FinancialReport financialReport) {
        this.financialReport = financialReport;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getCardKey() {
        return cardKey;
    }

    public void setCardKey(Integer cardKey) {
        this.cardKey = cardKey;
    }
}
