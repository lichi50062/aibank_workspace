package com.tfb.aibank.chl.system.resource.dto;
// @formatter:off
/**
 * @(#)ChkCoolingPeriodResponse.java
 *
 * <p>Description:查詢KYC填寫次數及上限 - Response</p>
 *
 * <p>Modify History:</p>
 * v1.0,  2024/05/08, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class KycCountsResponse {
    /** 當日填寫次數 */
    private String kycCounts;
    /** 當日填寫次數上限 */
    private String kycDayMaxCount;
    /** 近7日填寫次數 */
    private String kycWeekCounts;
    /** 當日填寫次數上限 */
    private String kycWeekMaxCount;
    /** 臨櫃待覆核註記 */
    private String isInReviewStatus;
    /** Pending註記 */
    private String isPending;

    /**
     * @return the kycCounts
     */
    public String getKycCounts() {
        return kycCounts;
    }

    /**
     * @param kycCounts
     *         the kycCounts to set
     */
    public void setKycCounts(String kycCounts) {
        this.kycCounts = kycCounts;
    }

    /**
     * @return the kycDayMaxCount
     */
    public String getKycDayMaxCount() {
        return kycDayMaxCount;
    }

    /**
     * @param kycDayMaxCount
     *         the kycDayMaxCount to set
     */
    public void setKycDayMaxCount(String kycDayMaxCount) {
        this.kycDayMaxCount = kycDayMaxCount;
    }

    /**
     * @return the kycWeekCounts
     */
    public String getKycWeekCounts() {
        return kycWeekCounts;
    }

    /**
     * @param kycWeekCounts
     *         the kycWeekCounts to set
     */
    public void setKycWeekCounts(String kycWeekCounts) {
        this.kycWeekCounts = kycWeekCounts;
    }

    /**
     * @return the kycWeekMaxCount
     */
    public String getKycWeekMaxCount() {
        return kycWeekMaxCount;
    }

    /**
     * @param kycWeekMaxCount
     *         the kycWeekMaxCount to set
     */
    public void setKycWeekMaxCount(String kycWeekMaxCount) {
        this.kycWeekMaxCount = kycWeekMaxCount;
    }

    
    public String getIsInReviewStatus() {
		return isInReviewStatus;
	}

	public void setIsInReviewStatus(String isInReviewStatus) {
		this.isInReviewStatus = isInReviewStatus;
	}

	/**
     * @return the isPending
     */
    public String getIsPending() {
        return isPending;
    }

    /**
     * @param isPending
     *         the isPending to set
     */
    public void setIsPending(String isPending) {
        this.isPending = isPending;
    }

}
