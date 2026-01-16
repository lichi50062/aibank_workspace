package com.tfb.aibank.chl.creditcard.tx005.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.component.cityandarea.City;

// @formatter:off
/**
 * @(#)NCCTX005020Rs.java
 * 
 * <p>Description:額度調整 020 申請資料輸入頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/04, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCTX005020Rs implements RsData {

    /** 調整項目 */
    private AdjustItemType adjustItem;
    /** 縣市 */
    private List<City> cities;
    /** 公司名稱 */
    private String company;
    /** 職稱 */
    private String jobTitle;
    /** 公司電話-區碼 */
    private String officeTelArea;
    /** 公司電話 */
    private String officeTel;
    /** 公司電話-分機 */
    private String officeTelExt;

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getOfficeTelArea() {
        return officeTelArea;
    }

    public void setOfficeTelArea(String officeTelArea) {
        this.officeTelArea = officeTelArea;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public String getOfficeTelExt() {
        return officeTelExt;
    }

    public void setOfficeTelExt(String officeTelExt) {
        this.officeTelExt = officeTelExt;
    }

    public AdjustItemType getAdjustItem() {
        return adjustItem;
    }

    public void setAdjustItem(AdjustItemType adjustItem) {
        this.adjustItem = adjustItem;
    }

}
