package com.tfb.aibank.chl.general.resource.dto;

import java.util.Date;

// @formatter:off
/**
 * @(#)CustomerGroup.java
 *
 * <p>Description:[廣告管理名單檔/優惠專區管理名單]</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/08/16, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CustomerGroup {

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 活動代碼<br> Unica檔名
     */
    private String unicaCampaignCode;

    /**
     * 行銷組合代碼
     */
    private String treatmentCode;

    /**
     * 取得
     *
     * @return Date
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 設定
     *
     * @param createTime
     *         要設定的
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得
     *
     * @return String
     */
    public String getUnicaCampaignCode() {
        return this.unicaCampaignCode;
    }

    /**
     * 設定
     *
     * @param unicaCampaignCode
     *         要設定的
     */
    public void setUnicaCampaignCode(String unicaCampaignCode) {
        this.unicaCampaignCode = unicaCampaignCode;
    }

    public String getTreatmentCode() {
        return treatmentCode;
    }

    public void setTreatmentCode(String treatmentCode) {
        this.treatmentCode = treatmentCode;
    }

}
