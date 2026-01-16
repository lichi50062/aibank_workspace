package com.tfb.aibank.chl.component.countryname.model;

/**
 * @(#)IpLocationAndCountryName.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/22, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
public class IpLocationAndCountryName {
    
    
    private Long ipFrom;
    
    private Long ipTo;
    
    private String countryCode = "";
    
    private String countryName = "";
    
    private String source = "";
    
    private String queryIp = "";

    public IpLocationAndCountryName() {
        super();
    }
    
    public IpLocationAndCountryName(String queryIp) {
        super();
        this.queryIp = queryIp;
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getQueryIp() {
        return queryIp;
    }

    public void setQueryIp(String queryIp) {
        this.queryIp = queryIp;
    }

    @Override
    public String toString() {
        return "IpLocationAndCountryNameModel [ipFrom=" + ipFrom + ", ipTo=" + ipTo + ", countryCode=" + countryCode + ", countryName=" + countryName + ", source=" + source + ", queryIp=" + queryIp + "]";
    }

    
    
}
