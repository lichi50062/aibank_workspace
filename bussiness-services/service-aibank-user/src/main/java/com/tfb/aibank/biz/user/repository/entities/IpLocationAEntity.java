package com.tfb.aibank.biz.user.repository.entities;

import java.io.Serializable;
import java.util.Objects;

import com.tfb.aibank.biz.user.repository.entities.pk.IpLocationEntityPk;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

/**
 * @(#)IpLocationAEntity.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/20, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Entity
@Table(name = "IP_LOCATION_A", schema = "DBUSERNEB")
@IdClass(IpLocationEntityPk.class)
public class IpLocationAEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "IP_FROM", precision = 12, scale = 0, nullable = false)
    private Long ipFrom;
    
    @Id
    @Column(name = "IP_TO", precision = 12, scale = 0, nullable = false)
    private Long ipTo;
    
    @Column(name = "COUNTRY_CODE", length = 3)
    private String countryCode;
    
    @Column(name = "COUNTRY_NAME", length = 100)
    private String countryName;
    
    public IpLocationAEntity() {
    }
    
    public IpLocationAEntity(Long ipFrom, Long ipTo) {
        this.ipFrom = ipFrom;
        this.ipTo = ipTo;
    }

    // Getters and Setters
    public Long getIpFrom() { return ipFrom; }
    public void setIpFrom(Long ipFrom) { this.ipFrom = ipFrom; }
    
    public Long getIpTo() { return ipTo; }
    public void setIpTo(Long ipTo) { this.ipTo = ipTo; }
    
    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }
    
    public String getCountryName() { return countryName; }
    public void setCountryName(String countryName) { this.countryName = countryName; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IpLocationAEntity that = (IpLocationAEntity) o;
        return Objects.equals(ipFrom, that.ipFrom) && Objects.equals(ipTo, that.ipTo);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(ipFrom, ipTo);
    }
}
