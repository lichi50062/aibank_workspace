package com.tfb.aibank.biz.user.services.countryname.model;
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
public class CountryNameVo {

    
    private String locale;
    
    private String name;
    
    private String code;
    
    private Long ipFrom;
    
    private Long ipTo;
    
    public CountryNameVo() {
        super();
    }
    
    public CountryNameVo(String locale, String name, String code) {
        super();
        this.locale = locale;
        this.name = name;
        this.code = code;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getIpFrom() {
        return ipFrom;
    }

    public void setIpFrom(Long ipFrom) {
        this.ipFrom = ipFrom;
    }

    public Long getIpTo() {
        return ipTo;
    }

    public void setIpTo(Long ipTo) {
        this.ipTo = ipTo;
    }

    @Override
    public String toString() {
        return "CountryNameVo [locale=" + locale + ", name=" + name + ", code=" + code + ", ipFrom=" + ipFrom + ", ipTo=" + ipTo + "]";
    }
 
    
}
