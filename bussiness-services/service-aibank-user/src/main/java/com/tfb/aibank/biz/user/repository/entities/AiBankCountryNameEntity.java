package com.tfb.aibank.biz.user.repository.entities;

import java.io.Serializable;
import java.util.Objects;

import com.tfb.aibank.biz.user.repository.entities.pk.AiBankCountryNameEntityPk;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

/**
 * @(#)CountryNameEntity.java
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
@Table(name = "AIBANK_COUNTRY_NAME", schema = "DBUSERNEB")
@IdClass(AiBankCountryNameEntityPk.class)
public class AiBankCountryNameEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "COUNTRY_CODE", length = 3, nullable = false)
    private String countryCode;
    
    @Id
    @Column(name = "LOCALE", length = 8, nullable = false)
    private String locale;
    
    @Column(name = "COUNTRY_NAME", length = 100, nullable = false)
    private String countryName;

    // Constructors
    public AiBankCountryNameEntity() {}
    
    public AiBankCountryNameEntity(String countryCode, String locale, String countryName) {
        this.countryCode = countryCode;
        this.locale = locale;
        this.countryName = countryName;
    }
    
    // Getters and Setters
    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }
    
    public String getLocale() { return locale; }
    public void setLocale(String locale) { this.locale = locale; }
    
    public String getCountryName() { return countryName; }
    public void setCountryName(String countryName) { this.countryName = countryName; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AiBankCountryNameEntity that = (AiBankCountryNameEntity) o;
        return Objects.equals(countryCode, that.countryCode) && Objects.equals(locale, that.locale);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(countryCode, locale);
    }
}
