package com.tfb.aibank.biz.user.repository.entities.pk;

import java.io.Serializable;
import java.util.Objects;

/**
 * @(#)AIBankCountryNameEntityPk.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/20, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
public class AiBankCountryNameEntityPk implements Serializable {
        private static final long serialVersionUID = 1L;
        private String countryCode;
        private String locale;
        
        public AiBankCountryNameEntityPk() {}
        
        public AiBankCountryNameEntityPk(String countryCode, String locale) {
            this.countryCode = countryCode;
            this.locale = locale;
        }
        
        // Getters and Setters
        public String getCountryCode() { return countryCode; }
        public void setCountryCode(String countryCode) { this.countryCode = countryCode; }
        
        public String getLocale() { return locale; }
        public void setLocale(String locale) { this.locale = locale; }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AiBankCountryNameEntityPk that = (AiBankCountryNameEntityPk) o;
            return Objects.equals(countryCode, that.countryCode) && Objects.equals(locale, that.locale);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(countryCode, locale);
        }
}
