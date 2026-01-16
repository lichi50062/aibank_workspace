package com.tfb.aibank.chl.system.ot014.model;

// @formatter:off
import java.util.Date;
import java.util.List;

import com.tfb.aibank.chl.system.resource.dto.CoolingPeriodResponse;
import com.tfb.aibank.chl.system.resource.dto.KycCountsResponse;
import com.tfb.aibank.chl.system.resource.dto.KycRiskLevelModel; /**
 * @(#)NSTOT014Output.java
 * 
 * <p>Description:投資風險偏好輔助說明 輸出專用物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/08, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NSTOT014Output {
    private String ebillCheck;
    private Date ebillStartDate;
    private Date ebillEndDate;
    private KycRiskLevelModel kycRiskLevelModel;
    private CoolingPeriodResponse coolingPeriodResponse;
    private KycCountsResponse kycCountsResponse;
    private Integer weekLimit;
    private Integer daliyLimit;
    private String email;
    private List<QAItem> qaList;
    private List<QAItem> qaList2;

    public String getEbillCheck() {
        return ebillCheck;
    }

    public void setEbillCheck(String ebillCheck) {
        this.ebillCheck = ebillCheck;
    }

    public Date getEbillStartDate() {
        return ebillStartDate;
    }

    public void setEbillStartDate(Date ebillStartDate) {
        this.ebillStartDate = ebillStartDate;
    }

    public Date getEbillEndDate() {
        return ebillEndDate;
    }

    public void setEbillEndDate(Date ebillEndDate) {
        this.ebillEndDate = ebillEndDate;
    }

    public KycRiskLevelModel getKycRiskLevelModel() {
        return kycRiskLevelModel;
    }

    public void setKycRiskLevelModel(KycRiskLevelModel kycRiskLevelModel) {
        this.kycRiskLevelModel = kycRiskLevelModel;
    }

    public CoolingPeriodResponse getCoolingPeriodResponse() {
        return this.coolingPeriodResponse;
    }

    public void setCoolingPeriodResponse(CoolingPeriodResponse coolingPeriodResponse) {
        this.coolingPeriodResponse = coolingPeriodResponse;
    }

    public KycCountsResponse getKycCountsResponse() {
        return kycCountsResponse;
    }

    public void setKycCountsResponse(KycCountsResponse kycCountsResponse) {
        this.kycCountsResponse = kycCountsResponse;
    }

    public List<QAItem> getQaList() {
        return qaList;
    }

    public void setQaList(List<QAItem> qaList) {
        this.qaList = qaList;
    }

    public List<QAItem> getQaList2() {
        return qaList2;
    }

    public void setQaList2(List<QAItem> qaList2) {
        this.qaList2 = qaList2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getWeekLimit() {
        return weekLimit;
    }

    public void setWeekLimit(Integer weekLimit) {
        this.weekLimit = weekLimit;
    }

    public Integer getDaliyLimit() {
        return daliyLimit;
    }

    public void setDaliyLimit(Integer daliyLimit) {
        this.daliyLimit = daliyLimit;
    }
}
