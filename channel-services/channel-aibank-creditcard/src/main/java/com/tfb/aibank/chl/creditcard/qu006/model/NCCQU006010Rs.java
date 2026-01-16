package com.tfb.aibank.chl.creditcard.qu006.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCQU006010Rs.java
*
* <p>Description:點數回饋中心 功能首頁</p>
*
* <p>Modify History:</p>
* v1.0, 2023/08/10 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU006010Rs implements RsData {

    /**
     * 查詢失敗，可重試
     */
    private int isReload;

    /**
     * 好市多活動網頁URL
     */
    private String costoCampaignUrl;

    /**
     * 紅利活動網頁URL
     */
    private String bonusCampaignUrl;

    /**
     * 哩程活動網頁URL
     */
    private String milesCampaignUrl;

    /**
     * 福華活動網頁URL
     */
    private String howardCampaignUrl;

    /** 點數資料 */
    private List<NCCQU006010RsBonRepeats> bonData;

    /**
     * 富邦紅利點
     */
    private String redPoint;

    private String endPoint;

    private String endDate;

    /**
     * 世界無限點
     */
    private String unlimitPoint;

    /**
     * @return the isReload
     */
    public int getIsReload() {
        return isReload;
    }

    /**
     * @param isReload
     *            the isReload to set
     */
    public void setIsReload(int isReload) {
        this.isReload = isReload;
    }

    /**
     * @return the costoCampaignUrl
     */
    public String getCostoCampaignUrl() {
        return costoCampaignUrl;
    }

    /**
     * @param costoCampaignUrl
     *            the costoCampaignUrl to set
     */
    public void setCostoCampaignUrl(String costoCampaignUrl) {
        this.costoCampaignUrl = costoCampaignUrl;
    }

    /**
     * @return the bonusCampaignUrl
     */
    public String getBonusCampaignUrl() {
        return bonusCampaignUrl;
    }

    /**
     * @param bonusCampaignUrl
     *            the bonusCampaignUrl to set
     */
    public void setBonusCampaignUrl(String bonusCampaignUrl) {
        this.bonusCampaignUrl = bonusCampaignUrl;
    }

    /**
     * @return the milesCampaignUrl
     */
    public String getMilesCampaignUrl() {
        return milesCampaignUrl;
    }

    /**
     * @param milesCampaignUrl
     *            the milesCampaignUrl to set
     */
    public void setMilesCampaignUrl(String milesCampaignUrl) {
        this.milesCampaignUrl = milesCampaignUrl;
    }

    /**
     * @return the howardCampaignUrl
     */
    public String getHowardCampaignUrl() {
        return howardCampaignUrl;
    }

    /**
     * @param howardCampaignUrl
     *            the howardCampaignUrl to set
     */
    public void setHowardCampaignUrl(String howardCampaignUrl) {
        this.howardCampaignUrl = howardCampaignUrl;
    }

    /**
     * @return the bonData
     */
    public List<NCCQU006010RsBonRepeats> getBonData() {
        return bonData;
    }

    /**
     * @param bonData
     *            the bonData to set
     */
    public void setBonData(List<NCCQU006010RsBonRepeats> bonData) {
        this.bonData = bonData;
    }

    /**
     * @return the redPoint
     */
    public String getRedPoint() {
        return redPoint;
    }

    /**
     * @param redPoint
     *            the redPoint to set
     */
    public void setRedPoint(String redPoint) {
        this.redPoint = redPoint;
    }

    /**
     * @return the endPoint
     */
    public String getEndPoint() {
        return endPoint;
    }

    /**
     * @param endPoint
     *            the endPoint to set
     */
    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the unlimitPoint
     */
    public String getUnlimitPoint() {
        return unlimitPoint;
    }

    /**
     * @param unlimitPoint
     *            the unlimitPoint to set
     */
    public void setUnlimitPoint(String unlimitPoint) {
        this.unlimitPoint = unlimitPoint;
    }

}
