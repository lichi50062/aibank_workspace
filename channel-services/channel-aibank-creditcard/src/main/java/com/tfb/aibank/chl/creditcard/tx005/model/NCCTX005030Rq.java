package com.tfb.aibank.chl.creditcard.tx005.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCTX005030Rq.java
 * 
 * <p>Description:額度調整 030 財力證明上傳頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/04, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCTX005030Rq implements RqData {

    /** 公司名稱 */
    private String company;
    /** 職稱 */
    private String jobTitle;
    /** 年資-年 */
    private String seniorityY;
    /** 年資-月 */
    private String seniorityM;
    /** 公司電話-區碼 */
    private String officeTelArea;
    /** 公司電話 */
    private String officeTel;
    /** 公司電話-分機 */
    private String officeTelExt;
    /** 公司地址 */
    private String officeAddress;
    /** 公司地址-縣市代碼 */
    private String cityCode1;
    /** 公司地址-縣市 */
    private String cityName;
    /** 公司地址-鄉鎮市區 */
    private String areaName;
    /** 公司地址-郵遞區號 */
    private String zipcode;
    /** 是否曾點擊「公司電話」欄位 */
    private boolean untouched;

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

    public String getSeniorityY() {
        return seniorityY;
    }

    public void setSeniorityY(String seniorityY) {
        this.seniorityY = seniorityY;
    }

    public String getSeniorityM() {
        return seniorityM;
    }

    public void setSeniorityM(String seniorityM) {
        this.seniorityM = seniorityM;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public boolean isUntouched() {
        return untouched;
    }

    public void setUntouched(boolean untouched) {
        this.untouched = untouched;
    }

    public String getOfficeTelArea() {
        return officeTelArea;
    }

    public void setOfficeTelArea(String officeTelArea) {
        this.officeTelArea = officeTelArea;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCityCode1() {
        return cityCode1;
    }

    public void setCityCode1(String cityCode1) {
        this.cityCode1 = cityCode1;
    }

}
