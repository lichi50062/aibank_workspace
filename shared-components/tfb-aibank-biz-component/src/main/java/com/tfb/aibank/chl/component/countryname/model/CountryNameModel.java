package com.tfb.aibank.chl.component.countryname.model;

/**
 * @(#)CountryNameVo.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/20, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
public class CountryNameModel {

    
    private String countryCode;
    
    private String locale;
    
    private String countryName;

    public CountryNameModel() {}
    
    /**
     * 完整建構子
     */
    public CountryNameModel(String countryCode, String locale, String countryName) {
        this.countryCode = countryCode;
        this.locale = locale;
        this.countryName = countryName;
    }
   
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    
    
    
}
